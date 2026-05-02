package fuzs.bettertridents.common;

import fuzs.bettertridents.common.config.CommonConfig;
import fuzs.bettertridents.common.config.ServerConfig;
import fuzs.bettertridents.common.handler.LoyalDropsHandler;
import fuzs.bettertridents.common.handler.TridentAttachmentHandler;
import fuzs.bettertridents.common.init.ModLootTables;
import fuzs.bettertridents.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.context.ItemComponentsContext;
import fuzs.puzzleslib.common.api.core.v1.context.PackRepositorySourcesContext;
import fuzs.puzzleslib.common.api.event.v1.BuildCreativeModeTabContentsCallback;
import fuzs.puzzleslib.common.api.event.v1.entity.living.LivingDeathCallback;
import fuzs.puzzleslib.common.api.event.v1.entity.living.LivingDropsCallback;
import fuzs.puzzleslib.common.api.event.v1.entity.living.LivingExperienceDropCallback;
import fuzs.puzzleslib.common.api.event.v1.server.LootTableLoadCallback;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Repairable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterTridents implements ModConstructor {
    public static final String MOD_ID = "bettertridents";
    public static final String MOD_NAME = "Better Tridents";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID)
            .server(ServerConfig.class)
            .common(CommonConfig.class);
    public static final Identifier BOOSTED_IMPALING_ID = id("boosted_impaling");
    public static final Identifier TRIDENT_RECIPE_ID = id("trident_recipe");

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        LivingDropsCallback.EVENT.register(LoyalDropsHandler::onLivingDrops);
        LivingExperienceDropCallback.EVENT.register(LoyalDropsHandler::onLivingExperienceDrop);
        LootTableLoadCallback.EVENT.register(ModLootTables::onLootTableLoad);
        LivingDeathCallback.EVENT.register(TridentAttachmentHandler::onLivingDeath);
        LivingDropsCallback.EVENT.register(TridentAttachmentHandler::onLivingDrops);
        BuildCreativeModeTabContentsCallback.buildCreativeModeTabContents(CreativeModeTabs.INGREDIENTS)
                .register((CreativeModeTab creativeModeTab, CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) -> {
                    output.accept(ModRegistry.TRIDENT_FRAGMENT_ITEM.value());
                });
    }

    @Override
    public void onAddDataPackFinders(PackRepositorySourcesContext context) {
        context.registerBuiltInPack(BOOSTED_IMPALING_ID, Component.literal("Impaling When Wet"), true);
        context.registerBuiltInPack(TRIDENT_RECIPE_ID, Component.literal("Trident Recipe"), true);
    }

    @Override
    public void onRegisterItemComponentPatches(ItemComponentsContext context) {
        if (!BetterTridents.CONFIG.getHolder(CommonConfig.class).isAvailable() || !BetterTridents.CONFIG.get(
                CommonConfig.class).repairTridents) {
            return;
        }

        context.registerItemComponentsPatch(Items.TRIDENT,
                (DataComponentGetter components, DataComponentMap.Builder builder, HolderLookup.Provider registries, Item item) -> {
                    builder.set(DataComponents.REPAIRABLE,
                            new Repairable(HolderSet.direct(Items.PRISMARINE_SHARD.builtInRegistryHolder())));
                });
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}

package fuzs.bettertridents.neoforge;

import fuzs.bettertridents.common.BetterTridents;
import fuzs.bettertridents.common.data.recipes.ModRecipeProvider;
import fuzs.bettertridents.common.data.loot.ModEntityInjectionLootProvider;
import fuzs.bettertridents.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.Mod;

@Mod(BetterTridents.MOD_ID)
public class BetterTridentsNeoForge {

    public BetterTridentsNeoForge() {
        ModConstructor.construct(BetterTridents.MOD_ID, BetterTridents::new);
        DataProviderBuilder.of(BetterTridents.MOD_ID)
                .addLootProvider(ModEntityInjectionLootProvider::new, LootContextParamSets.ENTITY);
        DataProviderBuilder.ofBuiltIn(BetterTridents.BOOSTED_IMPALING_ID, PackType.SERVER_DATA)
                .addWorldBootstrap(Registries.ENCHANTMENT, ModRegistry::bootstrapEnchantments);
        DataProviderBuilder.ofBuiltIn(BetterTridents.TRIDENT_RECIPE_ID, PackType.SERVER_DATA)
                .addRecipeProvider(ModRecipeProvider::new);
    }
}

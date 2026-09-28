package fuzs.bettertridents.neoforge;

import fuzs.bettertridents.common.BetterTridents;
import fuzs.bettertridents.common.data.loot.ModEntityInjectionLootProvider;
import fuzs.bettertridents.common.data.recipes.ModRecipeProvider;
import fuzs.bettertridents.common.handler.ModifyEnchantmentsHandler;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.Mod;

import java.util.List;

@Mod(BetterTridents.MOD_ID)
public class BetterTridentsNeoForge {

    public BetterTridentsNeoForge() {
        ModConstructor.construct(BetterTridents.MOD_ID, BetterTridents::new);
        DataProviderBuilder.of(BetterTridents.MOD_ID)
                .addLootProvider(ModEntityInjectionLootProvider::new, LootContextParamSets.ENTITY);
        DataProviderBuilder.ofBuiltIn(BetterTridents.TRIDENT_RECIPE_ID, PackType.SERVER_DATA)
                .addRecipeProvider(ModRecipeProvider::new);
    }

    @SuppressWarnings("unchecked")
    public static Enchantment modifyEnchantment(ResourceKey<Enchantment> key, Enchantment enchantment, RegistryOps.RegistryInfoLookup lookup) {
        Enchantment.Builder builder = Enchantment.enchantment(enchantment.definition());
        builder.exclusiveWith(enchantment.exclusiveSet());
        // copy the original effects so the builder starts out as a copy of the enchantment being modified
        builder.effectMapBuilder.addAll(enchantment.effects());
        enchantment.effects().forEach((TypedDataComponent<?> component) -> {
            if (component.value() instanceof List<?> valueList) {
                builder.getEffectsList((DataComponentType<List<Object>>) component.type()).addAll(valueList);
            }
        });
        if (ModifyEnchantmentsHandler.modifyEnchantment(key, builder, lookup)) {
            // keep the original description instead of deriving it from the resource key
            return new Enchantment(enchantment.description(),
                    enchantment.definition(),
                    enchantment.exclusiveSet(),
                    builder.effectMapBuilder.build());
        } else {
            return null;
        }
    }
}

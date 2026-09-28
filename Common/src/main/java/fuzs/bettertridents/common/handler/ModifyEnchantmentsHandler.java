package fuzs.bettertridents.common.handler;

import fuzs.bettertridents.common.advancements.critereon.WetEntityPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

public final class ModifyEnchantmentsHandler {

    private ModifyEnchantmentsHandler() {
        // NO-OP
    }

    public static boolean modifyEnchantment(ResourceKey<Enchantment> key, Enchantment.Builder builder, RegistryOps.RegistryInfoLookup lookup) {
        if (key == Enchantments.IMPALING) {
            modifyImpaling(builder, lookup);
            return true;
        } else {
            return false;
        }
    }

    private static void modifyImpaling(Enchantment.Builder builder, RegistryOps.RegistryInfoLookup lookup) {
        // Additionally, apply to entities that are in water or rain, next to the vanilla sensitive_to_impaling entity type.
        HolderGetter<EntityType<?>> entityTypes = lookup.lookup(Registries.ENTITY_TYPE).orElseThrow();
        builder.getEffectsList(EnchantmentEffectComponents.DAMAGE).clear();
        builder.withEffect(EnchantmentEffectComponents.DAMAGE,
                new AddValue(LevelBasedValue.perLevel(2.5F)),
                AnyOfCondition.anyOf(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity()
                                        .entityType(EntityTypePredicate.of(entityTypes,
                                                EntityTypeTags.SENSITIVE_TO_IMPALING))
                                        .build()),
                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity()
                                        .put(WetEntityPredicate.CODEC, WetEntityPredicate.INSTANCE))));
    }
}

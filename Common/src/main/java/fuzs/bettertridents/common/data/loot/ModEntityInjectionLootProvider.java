package fuzs.bettertridents.common.data.loot;

import fuzs.bettertridents.common.init.ModLootTables;
import fuzs.bettertridents.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModEntityInjectionLootProvider extends AbstractLootSubProvider {

    public ModEntityInjectionLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.output.accept(ModLootTables.ELDER_GUARDIAN_INJECTION,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(ModRegistry.TRIDENT_FRAGMENT_ITEM.value()))));
    }
}

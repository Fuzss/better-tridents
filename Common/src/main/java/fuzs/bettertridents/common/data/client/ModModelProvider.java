package fuzs.bettertridents.common.data.client;

import fuzs.bettertridents.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModRegistry.TRIDENT_FRAGMENT_ITEM.value(), ModelTemplates.FLAT_ITEM);
    }
}

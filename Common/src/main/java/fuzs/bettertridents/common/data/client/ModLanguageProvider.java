package fuzs.bettertridents.common.data.client;

import fuzs.bettertridents.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.TRIDENT_FRAGMENT_ITEM.value(), "Trident Fragment");
        this.add(ModRegistry.LOYAL_ITEM_ENTITY_TYPE.value(), "Loyal Item");
        this.add(ModRegistry.LOYAL_EXPERIENCE_ORB_ENTITY_TYPE.value(), "Loyal Experience Orb");
    }
}

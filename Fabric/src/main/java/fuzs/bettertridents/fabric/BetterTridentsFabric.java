package fuzs.bettertridents.fabric;

import fuzs.bettertridents.common.BetterTridents;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class BetterTridentsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(BetterTridents.MOD_ID, BetterTridents::new);
    }
}

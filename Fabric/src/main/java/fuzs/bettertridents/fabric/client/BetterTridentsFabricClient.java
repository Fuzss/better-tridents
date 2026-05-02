package fuzs.bettertridents.fabric.client;

import fuzs.bettertridents.common.BetterTridents;
import fuzs.bettertridents.common.client.BetterTridentsClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class BetterTridentsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(BetterTridents.MOD_ID, BetterTridentsClient::new);
    }
}

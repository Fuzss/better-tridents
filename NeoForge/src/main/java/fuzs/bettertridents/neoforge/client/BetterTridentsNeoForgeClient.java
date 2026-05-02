package fuzs.bettertridents.neoforge.client;

import fuzs.bettertridents.common.BetterTridents;
import fuzs.bettertridents.common.client.BetterTridentsClient;
import fuzs.bettertridents.common.data.client.ModLanguageProvider;
import fuzs.bettertridents.common.data.client.ModModelProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = BetterTridents.MOD_ID, dist = Dist.CLIENT)
public class BetterTridentsNeoForgeClient {

    public BetterTridentsNeoForgeClient() {
        ClientModConstructor.construct(BetterTridents.MOD_ID, BetterTridentsClient::new);
        DataProviderHelper.registerDataProviders(BetterTridents.MOD_ID, ModLanguageProvider::new,
                ModModelProvider::new
        );
    }
}

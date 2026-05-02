package fuzs.bettertridents.neoforge;

import fuzs.bettertridents.common.BetterTridents;
import fuzs.bettertridents.common.data.ModDatapackRegistriesProvider;
import fuzs.bettertridents.common.data.ModRecipeProvider;
import fuzs.bettertridents.common.data.loot.ModEntityInjectionLootProvider;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.minecraft.server.packs.PackType;
import net.neoforged.fml.common.Mod;

@Mod(BetterTridents.MOD_ID)
public class BetterTridentsNeoForge {

    public BetterTridentsNeoForge() {
        ModConstructor.construct(BetterTridents.MOD_ID, BetterTridents::new);
        DataProviderHelper.registerDataProviders(BetterTridents.MOD_ID, ModEntityInjectionLootProvider::new);
        DataProviderHelper.registerDataProviders(BetterTridents.BOOSTED_IMPALING_ID,
                PackType.SERVER_DATA,
                ModDatapackRegistriesProvider::new);
        DataProviderHelper.registerDataProviders(BetterTridents.TRIDENT_RECIPE_ID,
                PackType.SERVER_DATA,
                ModRecipeProvider::new);
    }
}

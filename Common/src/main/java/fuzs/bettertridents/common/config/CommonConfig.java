package fuzs.bettertridents.common.config;

import fuzs.puzzleslib.common.api.config.v3.Config;
import fuzs.puzzleslib.common.api.config.v3.ConfigCore;

public class CommonConfig implements ConfigCore {
    @Config(description = "Tridents can be repaired in an anvil using prismarine shards.")
    public boolean repairTridents = true;
}

package fuzs.bettertridents.common.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public final class WetEntityPredicate implements EntitySubPredicate {
    public static final WetEntityPredicate INSTANCE = new WetEntityPredicate();
    public static final Codec<WetEntityPredicate> CODEC = MapCodec.unitCodec(INSTANCE);

    private WetEntityPredicate() {
        // NO-OP
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        return entity.isInWaterOrRain();
    }
}

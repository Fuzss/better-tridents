package fuzs.bettertridents.common.client;

import fuzs.bettertridents.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.EntityRenderersContext;
import net.minecraft.client.renderer.entity.ExperienceOrbRenderer;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;

public class BetterTridentsClient implements ClientModConstructor {

    @Override
    public void onRegisterEntityRenderers(EntityRenderersContext context) {
        context.registerEntityRenderer(ModRegistry.LOYAL_ITEM_ENTITY_TYPE.value(), ItemEntityRenderer::new);
        context.registerEntityRenderer(ModRegistry.LOYAL_EXPERIENCE_ORB_ENTITY_TYPE.value(),
                ExperienceOrbRenderer::new
        );
    }
}

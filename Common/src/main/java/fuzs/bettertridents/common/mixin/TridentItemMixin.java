package fuzs.bettertridents.common.mixin;

import fuzs.bettertridents.common.BetterTridents;
import fuzs.bettertridents.common.config.ServerConfig;
import fuzs.bettertridents.common.init.ModRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TridentItem.class)
abstract class TridentItemMixin extends Item {

    public TridentItemMixin(Properties properties) {
        super(properties);
    }

    @ModifyVariable(method = "releaseUsing", at = @At("STORE"), ordinal = 0)
    public ThrownTrident releaseUsing(ThrownTrident trident, ItemStack itemStack, Level level, LivingEntity entity) {
        if (!BetterTridents.CONFIG.get(ServerConfig.class).returnTridentToSlot) {
            return trident;
        }

        if (entity instanceof Player player && entity.getUseItem() == itemStack) {
            if (entity.getUsedItemHand() == InteractionHand.OFF_HAND) {
                ModRegistry.TRIDENT_SLOT_ATTACHMENT_TYPE.set(trident, 40);
            } else {
                int selectedInventorySlot = player.getInventory().getSelectedSlot();
                ModRegistry.TRIDENT_SLOT_ATTACHMENT_TYPE.set(trident, selectedInventorySlot);
            }
        }

        return trident;
    }
}

package io.github.christhabot.shield_reset_sound.mixin.client;

import io.github.christhabot.shield_reset_sound.ShieldResetSoundClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin
{
    float lastAttackCooldownProgress = 0f;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;

        float progress = self.getAttackCooldownProgress(0.5f);

        if (progress >= 1.0f && lastAttackCooldownProgress < 1.0f) {
            ItemStack stack = self.getMainHandStack();
            if (stack != null && !stack.isEmpty()) {
                ShieldResetSoundClient.playSound(stack.getItem(), false);
            } else {
                ItemStack off = self.getOffHandStack();
                if (off != null && !off.isEmpty()) {
                    ShieldResetSoundClient.playSound(off.getItem(), false);
                }
            }
        }

        lastAttackCooldownProgress = progress;
    }
}

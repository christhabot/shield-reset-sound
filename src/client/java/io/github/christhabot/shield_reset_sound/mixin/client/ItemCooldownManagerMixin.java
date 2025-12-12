package io.github.christhabot.shield_reset_sound.mixin.client;

import io.github.christhabot.shield_reset_sound.ShieldResetSoundClient;
import io.github.christhabot.shield_reset_sound.ShieldResetSoundConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ItemCooldownManager.class)
public class ItemCooldownManagerMixin {
	@Unique
	private static final Logger LOGGER = LoggerFactory.getLogger("shield_reset_sound");

	@Unique
	private static ShieldResetSoundConfig getConfig() {
		return AutoConfig.getConfigHolder(ShieldResetSoundConfig.class).getConfig();
	}

	@Inject(method = "onCooldownUpdate(Lnet/minecraft/util/Identifier;)V", at = @At("HEAD"))
	private void onCooldownReset(Identifier groupId, CallbackInfo ci) {
        Item item = Registries.ITEM.get(groupId);
        if (item != null) {
            ShieldResetSoundClient.playSound(item, true);
        } else {
            LOGGER.warn("No item found for identifier: {}", groupId);
        }
    }
}

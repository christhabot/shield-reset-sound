package io.github.christhabot.shield_reset_sound.mixin.client;

import io.github.christhabot.shield_reset_sound.ShieldResetSoundConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.sound.SoundEvent;
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

	@Unique private long lastPlayTime = 0;

	@Unique
	private static ShieldResetSoundConfig getConfig() {
		return AutoConfig.getConfigHolder(ShieldResetSoundConfig.class).getConfig();
	}

	@Unique
	private static final Identifier SHIELD_GROUP = Identifier.of("minecraft", "shield");

	@Inject(method = "onCooldownUpdate(Lnet/minecraft/util/Identifier;)V", at = @At("HEAD"))
	private void onCooldownReset(Identifier groupId, CallbackInfo ci) {
        if (!SHIELD_GROUP.equals(groupId)) return;

        
        long now = System.currentTimeMillis();
        if (now - lastPlayTime < 100L) return; 
        lastPlayTime = now;

		MinecraftClient mc = MinecraftClient.getInstance();
		if (mc == null || mc.player == null) return;

        
        String soundName = getConfig().sound.replace("minecraft:", "");
        if (soundName.equals("block.note_block")) {
            int firstDot = soundName.indexOf('.');
            int secondDot = -1;

            if (firstDot != -1) {
                secondDot = soundName.indexOf('.', firstDot + 1);
            }

            int dotIndex = secondDot; 

            if (dotIndex != -1) {
                soundName = soundName.substring(0, dotIndex);
            }
            soundName = soundName + "." + getConfig().noteblock_type;
        }

        try {
            LOGGER.info("Trying to play sound: {}", soundName);
            SoundEvent shieldBlockSound = SoundEvent.of(Identifier.of("minecraft", soundName));
            mc.player.playSound(shieldBlockSound, getConfig().volume / 100f, getConfig().pitch / 100f);
        } catch (Exception e) {
            LOGGER.error("Failed to play shield reset sound", e);
        }
    }
}

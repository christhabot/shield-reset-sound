package io.github.christhabot.shield_reset_sound;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Unique;

public class ShieldResetSoundClient implements ClientModInitializer
{
	@Unique
	private static final Logger LOGGER = LoggerFactory.getLogger("shield_reset_sound");

	@Unique private static long lastPlayTime = 0;

	@Unique
	private static ShieldResetSoundConfig getConfig() {
		return AutoConfig.getConfigHolder(ShieldResetSoundConfig.class).getConfig();
	}

	@Override
	public void onInitializeClient() {

	}

	static ShieldResetSoundConfig.SoundOptions config(int index) {
        return switch (index) {
            case 1 -> getConfig().soundConfig1;
            case 2 -> getConfig().soundConfig2;
            case 3 -> getConfig().soundConfig3;
            case 4 -> getConfig().soundConfig4;
            case 5 -> getConfig().soundConfig5;
            default -> throw new IllegalArgumentException("Invalid sound index");
        };
	}



	public static void playSound(Item item, Boolean hudType)
	{

	}

	public static void playSoundConfirm(int idx) {
		long now = System.currentTimeMillis();
		if (now - lastPlayTime < 100L) return;
		lastPlayTime = now;

		MinecraftClient mc = MinecraftClient.getInstance();
		if (mc == null || mc.player == null) return;


		String soundName = config(idx).sound.replace("minecraft:", "");
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
			soundName = soundName + "." + config(idx).noteblock_type;
		}

		try {
			LOGGER.info("Trying to play sound: {}", soundName);
			SoundEvent shieldBlockSound = SoundEvent.of(Identifier.of("minecraft", soundName));
			mc.player.playSound(shieldBlockSound, config(idx).volume / 100f, config(idx).pitch / 100f);
		} catch (Exception e) {
			LOGGER.error("Failed to play reset sound", e);
		}
	}
}
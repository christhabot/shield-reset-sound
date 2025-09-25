package io.github.christhabot.shield_reset_sound;

import net.fabricmc.api.ModInitializer;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShieldResetSound implements ModInitializer {
	public static final String MOD_ID = "shield-reset-sound";

	
	
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		
		
		

		LOGGER.info("Hello Fabric world!");
		AutoConfig.register(ShieldResetSoundConfig.class, GsonConfigSerializer::new);
	}
}
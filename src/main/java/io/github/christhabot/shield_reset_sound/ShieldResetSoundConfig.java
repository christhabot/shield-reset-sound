package io.github.christhabot.shield_reset_sound;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "ShieldResetSound")
public class ShieldResetSoundConfig implements ConfigData {

	// ------------------------------
	// Sound Config 1
	// ------------------------------
	@ConfigEntry.Gui.CollapsibleObject
	public SoundOptions soundConfig1 = new SoundOptions();

	// ------------------------------
	// Sound Config 2
	// ------------------------------
	@ConfigEntry.Gui.CollapsibleObject
	public SoundOptions soundConfig2 = new SoundOptions();

	// ------------------------------
	// Sound Config 3
	// ------------------------------
	@ConfigEntry.Gui.CollapsibleObject
	public SoundOptions soundConfig3 = new SoundOptions();

	// ------------------------------
	// Sound Config 4
	// ------------------------------
	@ConfigEntry.Gui.CollapsibleObject
	public SoundOptions soundConfig4 = new SoundOptions();

	// ------------------------------
	// Sound Config 5
	// ------------------------------
	@ConfigEntry.Gui.CollapsibleObject
	public SoundOptions soundConfig5 = new SoundOptions();


	public static class SoundOptions {
		@ConfigEntry.Gui.Tooltip
		public String sound = "block.note_block";

		@ConfigEntry.Gui.Tooltip
		public String noteblock_type = "snare";

		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public float volume = 100f;

		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.BoundedDiscrete(min = 0, max = 500)
		public float pitch = 100f;
	}


}

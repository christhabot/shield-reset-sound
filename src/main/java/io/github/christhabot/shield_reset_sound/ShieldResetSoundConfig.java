package io.github.christhabot.shield_reset_sound;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "ShieldResetSound")
public class ShieldResetSoundConfig implements ConfigData {
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

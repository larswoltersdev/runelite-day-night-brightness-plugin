package com.daynightbrightness;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("daynightbrightness")
public interface DayNightBrightnessConfig extends Config
{
	@ConfigItem(
		keyName = "loginMessageEnabled",
		name = "Enable Login Message",
		description = "Enable the login message",
		position = 1
	)
	default boolean loginMessageEnabled()
	{
		return true;
	}

	@ConfigItem(
		keyName = "dayBrightnessValue",
		name = "Day Time Brightness",
		description = "The day time brightness value (0 = darkest, 30 = brightest)",
		position = 2
	)
	default int dayBrightness()
	{
		return 30;
	}

	@ConfigItem(
		keyName = "nightBrightnessValue",
		name = "Night Time Brightness",
		description = "The night time brightness value (0 = darkest, 30 = brightest)",
		position = 3
	)
	default int nightBrightness()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "dayHour",
		name = "Day Hour",
		description = "The hour when it will be day",
		position = 4
	)
	default int dayHour()
	{
		return 8;
	}

	@ConfigItem(
		keyName = "nightHour",
		name = "Night Hour",
		description = "The hour when it will be night",
		position = 5
	)
	default int nightHour()
	{
		return 22;
	}
}

package com.daynightbrightness;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DayNightBrightnessPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DayNightBrightnessPlugin.class);
		RuneLite.main(args);
	}
}
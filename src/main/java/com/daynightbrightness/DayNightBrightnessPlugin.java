package com.daynightbrightness;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.time.LocalTime;

@Slf4j
@PluginDescriptor(
	name = "Day & Night Brightness"
)
public class DayNightBrightnessPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private DayNightBrightnessConfig config;

	@Inject
	private ConfigManager configManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Day & Night Brightness started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Day & Night Brightness stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
			LocalTime currentTime = LocalTime.now();

			if (currentTime.isAfter(LocalTime.of(config.dayHour(), 0)) && currentTime.isBefore(LocalTime.of(config.nightHour(), 0))) {
				// Run the script to set the brightness to the configured 'day' value
				client.runScript(3966, 15, config.dayBrightness());
			} else {
				// Run the script to set the brightness to the configured 'night' value
				client.runScript(3966, 15, config.nightBrightness());
			}
		}
	}

	@Provides
	DayNightBrightnessConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DayNightBrightnessConfig.class);
	}
}

package com.daynightbrightness;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.chat.ChatMessageManager;
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
	private static final String DAY_MESSAGE = "It's a beautiful day with a cloudless sky.";
	private static final String NIGHT_MESSAGE = "It's a beautiful night with a sky full of stars.";

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
		setClientBrightness(LocalTime.now());
	}

	@Provides
	DayNightBrightnessConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DayNightBrightnessConfig.class);
	}

	public void setClientBrightness(LocalTime currentTime) {
		if (currentTime.isAfter(LocalTime.of(config.dayHour(), 0)) && currentTime.isBefore(LocalTime.of(config.nightHour(), 0))) {
			setDayBrightness();
		} else {
			setNightBrightness();
		}
	}

	public void setDayBrightness() {
		client.runScript(3966, 15, config.dayBrightness());
		sendChatMessage(DAY_MESSAGE);
	}

	public void setNightBrightness() {
		client.runScript(3966, 15, config.nightBrightness());
		sendChatMessage(NIGHT_MESSAGE);
	}

	private void sendChatMessage(String chatMessage)
	{
		if (config.loginMessageEnabled()) {
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", chatMessage, null);
		}
	}
}

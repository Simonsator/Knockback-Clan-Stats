package de.simonsator.partyandfriends.clan.extensions.stats.knockback;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;
import net.md_5.bungee.config.Configuration;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KBStat implements ClanStat {
	private final String NAME;
	private final KBStatsConnection CONNECTION;
	private final Matcher KILLS_MESSAGE;
	private final Matcher STRIKES_MESSAGE;
	private final Matcher DEATHS_MESSAGE;

	public KBStat(Configuration messages, KBStatsConnection con) {
		CONNECTION = con;
		NAME = messages.getString("ClanStats.Name");
		KILLS_MESSAGE = Pattern.compile("[KILLED]", Pattern.LITERAL).matcher(messages.getString("ClanStats.Kills"));
		DEATHS_MESSAGE = Pattern.compile("[DEATHS]", Pattern.LITERAL).matcher(messages.getString("ClanStats.Deaths"));
		STRIKES_MESSAGE = Pattern.compile("[STRIKES]", Pattern.LITERAL).matcher(messages.getString("ClanStats.Strikes"));
	}

	@Override
	public void stats(OnlinePAFPlayer pSender, Clan pClan) {
		List<PAFPlayer> players = pClan.getAllPlayers();
		List<PlayerData> playerData = new ArrayList<>();
		for (PAFPlayer player : players) {
			PlayerData data = CONNECTION.getPlayerData(player.getUniqueId());
			if (data != null)
				playerData.add(data);
		}
		int deaths = 0;
		int strikes = 0;
		int kills = 0;
		for (PlayerData data : playerData) {
			deaths += data.DEATHS;
			kills += data.KILLS;
			strikes += data.STRIKES;
		}
		pSender.sendMessage(KILLS_MESSAGE.replaceFirst(kills + ""));
		pSender.sendMessage(DEATHS_MESSAGE.replaceFirst(deaths + ""));
		pSender.sendMessage(STRIKES_MESSAGE.replaceFirst(strikes + ""));
	}

	@Override
	public String getName() {
		return NAME;
	}

}

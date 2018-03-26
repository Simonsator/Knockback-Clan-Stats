package de.simonsator.partyandfriends.clan.extensions.stats.quicksurvivalgames;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QSGStat implements ClanStat {
	private final String NAME;
	private final QSGStatsConnection CONNECTION;
	private final Matcher KILLS_MESSAGE;
	private final Matcher WON_MESSAGE;
	private final Matcher DEATHS_MESSAGE;
	private final Matcher PLAYED_MESSAGE;

	public QSGStat(Configuration messages, QSGStatsConnection con) {
		CONNECTION = con;
		NAME = messages.getString("ClanStats.Name");
		KILLS_MESSAGE = Pattern.compile("[KILLED]", Pattern.LITERAL).matcher(messages.getString("ClanStats.Kills"));
		DEATHS_MESSAGE = Pattern.compile("[DEATHS]", Pattern.LITERAL).matcher(messages.getString("ClanStats.Deaths"));
		WON_MESSAGE = Pattern.compile("[WON]", Pattern.LITERAL).matcher(messages.getString("ClanStats.Won"));
		PLAYED_MESSAGE = Pattern.compile("[PLAYED]", Pattern.LITERAL).matcher(messages.getString("ClanStats.Played"));
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
		int wins = 0;
		int played = 0;
		int kills = 0;
		for (PlayerData data : playerData) {
			deaths += data.DEATHS;
			kills += data.KILLS;
			played += data.PLAYED;
		}
		pSender.sendMessage(KILLS_MESSAGE.replaceFirst(kills + ""));
		pSender.sendMessage(DEATHS_MESSAGE.replaceFirst(deaths + ""));
		pSender.sendMessage(WON_MESSAGE.replaceFirst(wins + ""));
		pSender.sendMessage(PLAYED_MESSAGE.replaceFirst(played + ""));
	}

	@Override
	public String getName() {
		return NAME;
	}

}

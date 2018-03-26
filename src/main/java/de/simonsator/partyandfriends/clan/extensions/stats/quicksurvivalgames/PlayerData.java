package de.simonsator.partyandfriends.clan.extensions.stats.quicksurvivalgames;

public class PlayerData {
	public final int DEATHS;
	public final int KILLS;
	public final int WINS;
	public final int PLAYED;

	public PlayerData(int kills, int deaths, int pWins, int pPlayed) {
		KILLS = kills;
		DEATHS = deaths;
		WINS = pWins;
		PLAYED = pPlayed;
	}
}

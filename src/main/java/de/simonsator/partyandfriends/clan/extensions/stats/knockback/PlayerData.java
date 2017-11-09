package de.simonsator.partyandfriends.clan.extensions.stats.knockback;

public class PlayerData {
	public final int DEATHS;
	public final int KILLS;
	public final int STRIKES;

	public PlayerData(int kills, int deaths, int strikes) {
		KILLS = kills;
		DEATHS = deaths;
		STRIKES=strikes;
	}
}

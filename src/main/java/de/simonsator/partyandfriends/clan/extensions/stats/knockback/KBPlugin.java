package de.simonsator.partyandfriends.clan.extensions.stats.knockback;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.clan.commands.subcommands.Stats;
import de.simonsator.partyandfriends.clan.extensions.stats.knockback.configuration.KBConfig;
import de.simonsator.partyandfriends.clan.extensions.stats.knockback.configuration.KBMessages;
import de.simonsator.partyandfriends.utilities.Language;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

public class KBPlugin extends PAFExtension {
	private Configuration messages;
	private Configuration config;
	private KBStatsConnection connection;

	@Override
	public void onEnable() {
		try {
			config = new KBConfig(new File(getConfigFolder(), "config.yml")).getCreatedConfiguration();
			messages = new KBMessages(Language.OWN, new File(getConfigFolder(), "messages.yml")).getCreatedConfiguration();
			connection = new KBStatsConnection(config.getString("database.db"), "jdbc:mysql://" + config.getString("database.host") + ":" + config.getInt("database.port"), config.getString("database.user"), config.getString("database.password"));
			((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(new KBStat(messages, connection), this);
		} catch (IOException e) {
		}
	}

}

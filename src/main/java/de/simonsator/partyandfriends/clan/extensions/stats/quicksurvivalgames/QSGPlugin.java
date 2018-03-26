package de.simonsator.partyandfriends.clan.extensions.stats.quicksurvivalgames;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.clan.commands.subcommands.Stats;
import de.simonsator.partyandfriends.clan.extensions.stats.quicksurvivalgames.configuration.QSGConfig;
import de.simonsator.partyandfriends.clan.extensions.stats.quicksurvivalgames.configuration.QSGMessages;
import de.simonsator.partyandfriends.utilities.Language;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

public class QSGPlugin extends PAFExtension {
	private Configuration messages;
	private Configuration config;
	private QSGStatsConnection connection;

	@Override
	public void onEnable() {
		try {
			config = new QSGConfig(new File(getConfigFolder(), "config.yml"), this).getCreatedConfiguration();
			messages = new QSGMessages(Language.OWN, new File(getConfigFolder(), "messages.yml"), this).getCreatedConfiguration();
			connection = new QSGStatsConnection(config.getString("database.db"), "jdbc:mysql://" + config.getString("database.host") + ":" + config.getInt("database.port"), config.getString("database.user"), config.getString("database.password"));
			((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(new QSGStat(messages, connection), this);
		} catch (IOException ignored) {
		}
	}

}

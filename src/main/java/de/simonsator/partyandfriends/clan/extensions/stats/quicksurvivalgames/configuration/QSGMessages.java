package de.simonsator.partyandfriends.clan.extensions.stats.quicksurvivalgames.configuration;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;

import java.io.File;
import java.io.IOException;

public class QSGMessages extends LanguageConfiguration {
	public QSGMessages(Language pLanguage, File pFile, PAFExtension pPlugin) throws IOException {
		super(pLanguage, pFile, pPlugin);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("ClanStats.Name", "Knockback");
		set("ClanStats.Kills", "&7The clan has killed &a[KILLED] &7players.");
		set("ClanStats.Deaths", "&7The clan died &a[DEATHS]&7 times.");
		set("ClanStats.Won", "&7The clan has won &a[WON]&7 games.");
		set("ClanStats.Played", "&7The clan has played &a[PLAYED]&7 games.");
	}
}

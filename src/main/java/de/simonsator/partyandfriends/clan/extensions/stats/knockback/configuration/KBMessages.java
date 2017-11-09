package de.simonsator.partyandfriends.clan.extensions.stats.knockback.configuration;

import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;

import java.io.File;
import java.io.IOException;

public class KBMessages extends LanguageConfiguration {
	public KBMessages(Language pLanguage, File pFile) throws IOException {
		super(pLanguage, pFile);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("ClanStats.Name", "Knockback");
		set("ClanStats.Kills", "&7The clan has killed &a[KILLED] &7players.");
		set("ClanStats.Deaths", "&7The clan died &a[DEATHS]&7 times.");
		set("ClanStats.Strikes", "&7The clan has &a[STRIKES]&7 strikes.");
	}

	public void reloadConfiguration() throws IOException {
		configuration = (new KBMessages(LANGUAGE.OWN, FILE)).getCreatedConfiguration();
	}
}

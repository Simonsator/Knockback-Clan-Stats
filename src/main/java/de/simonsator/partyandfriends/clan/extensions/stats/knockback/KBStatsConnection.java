package de.simonsator.partyandfriends.clan.extensions.stats.knockback;

import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class KBStatsConnection extends SQLCommunication {
	protected KBStatsConnection(String pDatabase, String pURL, String pUserName, String pPassword) {
		super(pDatabase, pURL, pUserName, pPassword);
	}

	public PlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select KILLS, DEATHS, STRIKES from `"
					+ DATABASE + "`." + "Knockback WHERE UUID='" + pUUID.toString() + "' LIMIT 1");
			if (rs.next())
				return new PlayerData(rs.getInt("KILLS"), rs.getInt("DEATHS"), rs.getInt("STRIKES"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, stmt);
		}
		return null;
	}
}

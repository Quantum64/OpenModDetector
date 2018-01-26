package co.q64.omd.base.manager;

public interface DatabaseQueries {
	//formatter:off
	public static final String CREATE = 
			  "CREATE TABLE IF NOT EXISTS ModDetector ("
			+ "player varchar(36) NOT NULL, "
			+ "amount float NOT NULL, "
			+ "PRIMARY KEY 	(player)) "
			+ "ENGINE=InnoDB DEFAULT CHARSET=utf8";
	public static final String UPDATE =
			  "INSERT INTO ModDetector (player, mods) VALUES (?, ?) "
			+ "ON DUPLICATE KEY UPDATE "
			+ "mods = ?";
	public static final String GET = 
			  "SELECT mods FROM ModDetector "
			+ "WHERE player = ?";
	public static final String DELETE =
			  "DELETE FROM ModDetector "
			+ "WHERE player = ?";
	//formatter:on
}

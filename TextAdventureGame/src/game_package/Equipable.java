package game_package;

public class Equipable {
	private String name;
	private String description;
	private int def;
	private int atk;
	
	public Equipable(String name, String desc, int def, int atk) {
		this.name = name;
		this.description = desc;
		this.def = def;
		this.atk = atk;
	}
}

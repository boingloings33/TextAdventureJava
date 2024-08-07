package game_package;

import java.util.UUID;

public class Item {
	private String name;
	private String description;
	private String id = UUID.randomUUID().toString();
	private int hpBoost;
	private int attackBoost;
	private int defenseBoost;

	public Item(String name, String description, int hpBoost, int attackBoost, int defenseBoost) {
		this.name = name;
		this.description = description;
		this.hpBoost = hpBoost;
		this.attackBoost = attackBoost;
		this.defenseBoost = defenseBoost;
	}
	
	public int getHpBoost() {
		return this.hpBoost;
	}
	
	public int getAttackBoost() {
		return this.attackBoost;
	}
	
	public int getDefenseBoost() {
		return this.defenseBoost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getId() {
		return this.id;
	}
}

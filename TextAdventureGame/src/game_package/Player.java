package game_package;

import java.util.Scanner;

public class Player {
	private int lvl = 1;
	private int maxHp = 35;
	private int hp = maxHp;
	private int xp = 0;
	private int atk = 1;
	private int weaponAtk = 0;
	private int armorDef = 0;
	private int def = 1;
	private int crit = 1;
	private String name;
	private boolean isExploring = true;
	private boolean isInBattle = false;
	private Equipable weapon;
	private Equipable armor;
	private GeneratedRooms generatedRooms = new GeneratedRooms();
	private Room[] rooms = generatedRooms.getRooms();
	private Room currentRoom = rooms[0];
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setXP(int xp) {
		this.xp = xp;
	}

	public int getXP() {
		return this.xp;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getAtk() {
		return this.atk;
	}
	
	public void setWeaponAtk(int weaponAtk) {
		this.weaponAtk = atk;
	}

	public int getWeaponAtk() {
		return this.weaponAtk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getDef() {
		return this.def;
	}
	
	public void setArmorDef(int armorDef) {
		this.armorDef = armorDef;
	}

	public int getArmorDef() {
		return this.armorDef;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getLvl() {
		return this.lvl;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMaxHp() {
		return this.maxHp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public void setCrit(int crit) {
		this.crit = crit;
	}
	
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	public int getCrit() {
		return this.crit;
	}

	public void setArmor(Equipable armor) {
		this.armor = armor;
	}
	
	public Equipable getArmor() {
		return this.armor;
	}
	
	public void setWeapon(Equipable weapon) {
		this.weapon = weapon;
	}
	
	public Equipable getWeapon() {
		return this.weapon;
	}
	
	public void setIsExploring(boolean isExploring) {
		this.isExploring = isExploring;
		this.isInBattle = !isInBattle;
	}

	public boolean getIsExplorting() {
		return this.isExploring;
	}

	public void setIsInBattle(boolean isInBattle) {
		this.isInBattle = isInBattle;
		this.isExploring = !isExploring;
	}
	
	public boolean getIsInBattle() {
		return this.isInBattle;
	}
	
	public boolean isAlive() {
		return hp > 0;
	}
	
	public void death() {
		System.out.println("You died!");
		System.out.println("You find yourself back at the start of the cave. I guess something must have revived you...");
		this.hp = this.maxHp / 2;
		this.isExploring = true;
		this.currentRoom = this.rooms[0];
	}
	
	public void playerSetup(Scanner input) {
		System.out.println(
				"After spending weeks wandering the Goose Creek, a heavy rain crashes down as you stumble into a small cavern... ");
		System.out.println("Upon entering, {more story stuff here}...... ");
		System.out.println("Enter your name:");
		this.name = input.nextLine();
		System.out.println(this.name + " is it? Well here's to hoping you try harder than your parents did.");
		System.out.println("Take this dagger, it's dangerous in here.");
	}
}

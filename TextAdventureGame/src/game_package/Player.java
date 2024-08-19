package game_package;

import java.util.Scanner;

public class Player {

	public final static int[] LEVELS_XP_REQUIRED = { 20, 300, 700 };

	private Item weapon = null;
	private Item armor = null;
	private Item[] playerSlots = new Item[2];
	private Item[] inventory = new Item[2];
	private GeneratedRooms generatedRooms = new GeneratedRooms();
	private Room[] rooms = generatedRooms.getRooms();
	private Room currentRoom = rooms[0];

	private int level = 1;
	private int maxHp = 35;
	private int hp = maxHp;
	private int xp = 0;
	private int atk = 1;
	private int weaponAtk;
	private int armorDef;
	private int def = 1;
	private int crit = 1;
	private String name;
	private boolean isExploring = true;
	private boolean isInBattle = false;
	private boolean inventoryFull = false;

	// Player general traits
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setLvl(int lvl) {
		this.level = lvl;
	}

	public int getLvl() {
		return this.level;
	}

	public void setXP(int xp) {
		this.xp += xp;
		for (int i = 0; i < LEVELS_XP_REQUIRED.length; i++) {
			if (this.level == i + 1 && this.xp >= LEVELS_XP_REQUIRED[i]) {
				levelUp();
			}
		}
	}

	public int getXP() {
		return this.xp;
	}

	// Player combat traits
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

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getAtk() {
		return this.atk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getDef() {
		return this.def;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public int getCrit() {
		return this.crit;
	}

	// Player weapon and armor
	public void setArmor(Item armor) {
		this.armor = armor;
		this.armorDef = armor.getDefenseBoost();
		this.playerSlots[0] = armor;
	}

	public Item getArmor() {
		return this.armor;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
		this.playerSlots[1] = weapon;
		if (this.weapon != null) {
			this.weaponAtk = weapon.getAttackBoost();
		}
	}

	public Item getWeapon() {
		return this.weapon;
	}

	public void setWeaponAtk(int weaponAtk) {
		this.weaponAtk = atk;
	}

	public int getWeaponAtk() {
		return this.weaponAtk;
	}

	public void setArmorDef(int armorDef) {
		this.armorDef = armorDef;
	}

	public int getArmorDef() {
		return this.armorDef;
	}

	// Player state
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}

	public Room getCurrentRoom() {
		return this.currentRoom;
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

	public void levelUp() {
		this.level = this.level + 1;
		this.maxHp = this.maxHp + 10;
		this.atk = this.atk + 1;
		this.def = this.def + 1;
		System.out.println("You leveled up!");
	}

	// Player inventory
	public void setInventory(Item item) {
		Scanner input = new Scanner(System.in);
		String selection;
		this.inventoryFull = true;

		for (int i = 0; i < this.inventory.length; i++) {
			if (this.inventory[i] == null) {
				this.inventory[i] = item;
				this.inventoryFull = false;
				break;
			}
		}

		while (this.inventoryFull) {
			System.out.println("Inventory is full! Enter 'Destroy' + slot number to make more room.");
			System.out.println("Or type 'pass' to ignore the loot");
			selection = input.nextLine();
			if (selection.startsWith(MenuItems.DESTROY)) {
				destroy(selection);
				setInventory(item);
			} else if (selection.equals(MenuItems.PASS)) {
				System.out.println(MenuItems.IGNORE);
				break;
			} else {
				System.out.println(MenuItems.INVALID);

			}
		}
	}

	public Item[] getInventory() {
		return this.inventory;
	}

	public Item[] getPlayerSlots() {
		return this.playerSlots;
	}

	public void setInventoryIsFull(boolean isFull) {
		this.inventoryFull = isFull;
	}

	public boolean getInvetoryIsFull() {
		return this.inventoryFull;
	}

	public void lootEnemy() {
		Scanner input = new Scanner(System.in);
		String selection;
		GeneratedLoot generated = new GeneratedLoot();
		Item loot = generated.generateEnemyLoot();
		boolean isLooting = true;

		if (loot != null) {
			while (isLooting) {
				String lootName = loot.getName();
				System.out.println("The enemy has dropped " + lootName);
				System.out.println("Would you like to loot it?");
				selection = input.nextLine().toLowerCase();
				if (selection.equals("yes")) {
					this.setInventory(loot);
					System.out.println("You loot the " + lootName + "!");
					isLooting = false;
				} else if (selection.equals(MenuItems.NO)) {
					System.out.println(MenuItems.PASS);
					isLooting = false;
				} else {
					System.out.println(MenuItems.INVALID);
				}
			}
		} else {
			System.out.println("The enemy dropped no loot. How unfortunate...");
		}
	}

	// Player misc
	public boolean isAlive() {
		return hp > 0;
	}

	public void death() {
		System.out.println("You died!");
		System.out
				.println("You find yourself back at the start of the cave. I guess something must have revived you...");
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
		setInventory(GeneratedWeapons.bronzeDagger);
	}

	public void removeWeapon() {
		if (this.weapon != null) {
			for (int i = 0; i < this.inventory.length; i++) {
				if (this.inventory[i] == null) {
					this.inventory[i] = this.weapon;
					System.out.println("You have unsheathed your " + this.weapon.getName());
					setWeapon(null);
					break;
				} else if (this.inventoryFull) {
					System.out.println(MenuItems.FULL);
				}
			}
		} else {
			System.out.println("You have no weapon equipped!");
		}
	}

	public void removeArmor() {
		if (this.armor != null) {
			for (int i = 0; i < this.inventory.length; i++) {
				if (this.inventory[i] == null) {
					this.inventory[i] = this.armor;
					System.out.println("You have removed your " + this.armor.getName());
					setArmor(null);
					break;
				} else {
					System.out.println(MenuItems.FULL);
				}
			}
		} else {
			System.out.println("You have no armor equipped!");
		}
	}

	public void equip(String selection) {
		Item currentWeapon = this.weapon;
		Item currentArmor = this.armor;
		int numberSelection;

		if (Character.isDigit(selection.charAt(selection.length() - 1))) {
			numberSelection = selection.charAt(selection.length() - 1) - '0' - 1;
			if (numberSelection < 0 || numberSelection > this.inventory.length - 1
					|| this.inventory[numberSelection] == null) {
				System.out.println("Invalid selection!");
			} else if (this.inventory[numberSelection].getItemType() == ItemType.WEAPON) {
				setWeapon(this.inventory[numberSelection]);
				this.inventory[numberSelection] = currentWeapon;
				System.out.println(this.weapon.getName() + " has been equipped!");
			} else if (this.inventory[numberSelection].getItemType() == ItemType.ARMOR) {
				setArmor(this.inventory[numberSelection]);
				this.inventory[numberSelection] = currentArmor;
				System.out.println(this.weapon.getName() + " has been equipped!");
			} else {
				System.out.println(this.inventory[numberSelection] + " is not equipable!");
			}
		} else {
			System.out.println(MenuItems.INVALID);
		}
	}

	public void destroy(String selection) {
		int numberSelection;
		if (Character.isDigit(selection.charAt(selection.length() - 1))) {
			numberSelection = selection.charAt(selection.length() - 1) - '0' - 1;
			if (numberSelection < 0 || numberSelection > this.inventory.length - 1
					|| this.inventory[numberSelection] == null) {
				System.out.println(MenuItems.INVALID);
			} else if (this.inventory[numberSelection].getIsEaten()) {
				this.inventory[numberSelection] = null;
				this.inventoryFull = false;
			} else {
				System.out.println("You throw away the " + this.inventory[numberSelection].getName());
				this.inventory[numberSelection] = null;
				this.inventoryFull = false;
			}
		}
	}

	// Player consumption test
	public void eatFoot(String selection) {
		int numberSelection = numberSelection(selection, ItemType.REAGENT);
		if (numberSelection < this.inventory.length + 1) {
			System.out.println("You eat the " + this.inventory[numberSelection].getName() + "!");
			this.hp = this.hp + this.inventory[numberSelection].getHpBoost();
			System.out.println("You now have " + this.getHp() + " health remaining!");
			this.inventory[numberSelection].setIsEaten(true);
			destroy(selection);
		}
	}
	
	// Helper functions
	public int numberSelection(String selection, ItemType itemType) {
		int numberSelection;
		if (Character.isDigit(selection.charAt(selection.length() - 1))) {
			numberSelection = selection.charAt(selection.length() - 1) - '0' - 1;
			if (numberSelection < 0 || numberSelection > this.inventory.length - 1
					|| this.inventory[numberSelection] == null || this.inventory[numberSelection].getItemType() != itemType) {
				System.out.println(MenuItems.INVALID);
				return this.inventory.length + 1;
			} else {
				return numberSelection;
			}
		} else {
			System.out.println(MenuItems.INVALID);
			return this.inventory.length + 1;
		} 
	}
	
	public int numberSelection(String selection) {
		int numberSelection;
		if (Character.isDigit(selection.charAt(selection.length() - 1))) {
			numberSelection = selection.charAt(selection.length() - 1) - '0' - 1;
			if (numberSelection < 0 || numberSelection > this.inventory.length - 1
					|| this.inventory[numberSelection] == null) {
				System.out.println(MenuItems.INVALID);
				return this.inventory.length + 1;
			} else {
				return numberSelection;
			}
		} else {
			System.out.println(MenuItems.INVALID);
			return this.inventory.length + 1;
		} 
	}
}

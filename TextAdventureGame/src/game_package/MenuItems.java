package game_package;

import java.util.Arrays;

public class MenuItems {
	public final static String NORTH = "north";
	public final static String EAST = "east";
	public final static String SOUTH = "south";
	public final static String WEST = "west";
	public final static String ATTACK = "attack";
	public final static String ROOM = "room";
	public final static String HELP = "help";
	public final static String WEAPON = "weapon";
	public final static String ARMOR = "armor";
	public final static String INVENTORY = "bag";
	public final static String SLOTS = "slots";
	public final static String EXAMINE = "examine";
	public final static String LEVEL  = "level";
	public final static String EQUIP  = "equip";
	public final static String DESTROY  = "destroy";
	public final static String PASS  = "pass";
	public final static String NO  = "no";
	public final static String EAT  = "eat";
	public final static String HEALTH  = "health";
	
	public final static String FULL  = "Your bag is full!";
	public final static String IGNORE  = "Although tempting, you ignore the loot.";
	public final static String INVALID  = "Invalid selection!";
	


	
	public final static String REMOVE_WEAPON = "remove weapon";
	public final static String REMOVE_ARMOR = "remove armor";

	public void listInvalidSelection() {
		System.out.println("Invalid Selection!");
	}

	public void listHelp() {
		System.out.println("Enter 'help' to list commnads");
	}

	public void listCommands() {
		System.out.println("========================================================");
		System.out.printf("%-20s%s\n", "COMMAND", "EFFECT");
		System.out.println("--------------------------------------------------------");
		System.out.printf("%-20s%s\n", ROOM, "| Examine the room you are currently in.");
		System.out.printf("%-20s%s\n", INVENTORY, "| View inventory");
		System.out.printf("%-20s%s\n", SLOTS, "| View equipped weapons and armor");
		System.out.printf("%-20s%s\n", LEVEL, "| View current level/xp");
		System.out.printf("%-20s%s\n", NORTH, "| Move north one room");
		System.out.printf("%-20s%s\n", EAST, "| Move east one room");
		System.out.printf("%-20s%s\n", SOUTH, "| Move south one room");
		System.out.printf("%-20s%s\n", WEST, "| Move west one room");
		System.out.printf("%-20s%s\n", ATTACK + " + enemy name", "| Initiate attack on an enemy");
		System.out.printf("%-20s%s\n", EQUIP + " + slot number", "| Equip armor or weapon from inventory");
		System.out.printf("%-20s%s\n", REMOVE_WEAPON, "| Remove currently equipped weapon");
		System.out.printf("%-20s%s\n", REMOVE_ARMOR, "| Remove currently equipped ARMOR");
		System.out.println("========================================================");
	}

	public void listDamageToEnemy(Enemy selectedEnemy, int damageGiven) {
		if (selectedEnemy.getHp() > 0) {
			System.out.println("You hit the " + selectedEnemy.getName() + " for " + damageGiven
					+ " damage, and  it now has " + selectedEnemy.getHp() + " health remaining");
		} else {
			System.out.println("You hit the " + selectedEnemy.getName() + " for " + damageGiven + " damage.");
		}
	}

	public void listDamageToPlayer(Player user, Enemy selectedEnemy, int damageGiven) {
		if (user.getHp() > 0) {
			System.out.println("The " + selectedEnemy.getName() + " has hit you for " + damageGiven
					+ " damage, and you now have " + user.getHp() + " health reamaining.");
		} else {
			System.out.println("The " + selectedEnemy.getName() + " has hit you for " + damageGiven + " damage");
		}
	}

	public void listBattleOptions() {
		System.out.println("1: Attack");
		System.out.println("2: Use Health Potion");
		System.out.println("3: Attempt to flee");
	}

	public void listApproachEnemy(Enemy selectedEnemy) {
		System.out.println("You approach the " + selectedEnemy.getName() + " with your dagger drawn.");
	}

	public void listRoom(Room room, String enemyNames, String itemNames) {
		System.out.println("Room items:  " + itemNames);
		System.out.println("Room location: " + room.getLocation());
		System.out.println("Room coordinates: " + Arrays.toString(room.getCords()));
		if (room.getEnemies().size() > 0) {
			System.out.println("Enemies:  " + enemyNames);
		} else {
			System.out.println("Enemies: None");
		}
		System.out.println("Available Exits: " + room.getAvailableExits());
	}

	public void listBadExit(String selection) {
		System.out.println("You attempt to exit " + selection + ", but there's a literal fucking wall in the way");
	}

	public void listMovement(String selection) {
		System.out.println("You moved " + selection + "!");
	}

	public void listEquippedWeapon(Player user) {
		if (user.getWeapon() != null) {
			System.out.println("You currently have a " + user.getWeapon().getName() + " equipped");
			System.out.println("It has an attack bonus of +" + user.getWeaponAtk());
		} else {
			System.out.println("You have no weapon equipped!");
		}
	}

	public void listEquippedArmor(Player user) {
		if (user.getArmor() != null) {
			System.out.println("You currently have a " + user.getArmor().getName() + " equipped");
		} else {
			System.out.println("You have no armor equipped!");
		}
	}

	public void listInventory(Player user) {

		String itemName;
		for (int i = 0; i < user.getInventory().length; i++) {
			if (user.getInventory()[i] == null) {
				itemName = "empty";
			} else {
				itemName = user.getInventory()[i].getName();
			}
			System.out.printf(i + 1 + ": [%s] ", itemName);
		}
		System.out.println("");
	}

	public void listPlayerSlots(Player user) {

		if (user.getPlayerSlots()[0] != null) {
			System.out.printf("Armor: [%s]\n", user.getPlayerSlots()[0].getName());
		} else {
			System.out.println("Armor: [empty]");
		}

		if (user.getPlayerSlots()[1] != null) {
			System.out.printf("Weapon: [%s]\n", user.getPlayerSlots()[1].getName());
		} else {
			System.out.println("Weapon: [empty]");
		}

	}
	
	public void listLevel(Player user) {
		int xpLeft;
		xpLeft = Player.LEVELS_XP_REQUIRED[user.getLvl() - 1] - user.getXP();
		System.out.println("You are currently level " + user.getLvl() + " and need " + xpLeft + " xp to level up!");
	}
	
	public void listHealth (Player user) {
		System.out.println("You currently have " + user.getHp() + " health.");
	}
}

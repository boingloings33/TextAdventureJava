package game_package;
import java.util.Arrays;

public class MenuItems {
	public final static String NORTH = "north";
	public final static String EAST = "east";
	public final static String SOUTH = "south";
	public final static String WEST = "west";
	public final static String ATTACK = "attack";
	public final static String EXAMINE = "examine";
	public final static String HELP = "help";
	
	public void listInvalidSelection() {
		System.out.println("Invalid Selection!");
	}

	public void listHelp() {
		System.out.println("Enter 'help' to list commnads");
	}

	public void listCommands() {
		System.out.println(
				"Enter 'examine' to examine room\nEnter 'north' or 'south' or 'east' or 'west' to enter next room");
	}

	public void listDamageToEnemy(Enemy selectedEnemy, int damageGiven) {
		System.out.println("You hit the " + selectedEnemy.getName() + " for " + damageGiven
				+ " damage, and  it now has " + selectedEnemy.getHp() + " health remaining");
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
}

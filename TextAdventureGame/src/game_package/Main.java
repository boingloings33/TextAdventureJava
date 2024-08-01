package game_package;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Main {
	static MenuItems commands = new MenuItems();
	public static void main(String[] arg) {
		String selection = "";
		Scanner input = new Scanner(System.in);
		Player user = new Player();
		
		user.playerSetup(input);
		commands.listHelp();

		while (user.getIsExplorting()) {
			selection = input.nextLine().toLowerCase();
			if (selection.equals("help")) {
				commands.listCommands();
			} else if (selection.equals("examine")) {
				examineRoom(user.getCurrentRoom());
			} else if (selection.equals("north") || selection.equals("east") || selection.equals("south")
					|| selection.equals("west")) {
				 user.setCurrentRoom(moveRooms(user.getCurrentRoom(), input, selection)); 
			} else if (selection.startsWith("attack")) {
				enterBattle(user, user.getCurrentRoom(), selection);
			} else {
				commands.listInvalidSelection();
				commands.listHelp();
			}

		}
	}

	private static void examineRoom(Room room) {

		// Get enemy names
		String[] enemyNamesArr = new String[room.getEnemies().size()];
		String enemyNames;
		for (int i = 0; i < room.getEnemies().size(); i++) {
			enemyNamesArr[i] = room.getEnemies().get(i).getName();
		}
		enemyNames = String.join(", ", enemyNamesArr);

		// Get item names
		String[] itemNamesArr = new String[room.getItems().length];
		String itemNames;
		for (int i = 0; i < room.getItems().length; i++) {
			itemNamesArr[i] = room.getItems()[i];
		}
		itemNames = String.join(", ", itemNamesArr);

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

	private static Room moveRooms(Room currentRoom, Scanner input, String selection) {
		GeneratedRooms generatedRooms = new GeneratedRooms();
		Room[] rooms = generatedRooms.getRooms();
		Room updatedRoom = currentRoom;

		if (selection.equals("north") && currentRoom.getNorthExit() == true) {
			updatedRoom = matchCords(currentRoom, updatedRoom, rooms, selection, 0, 1);
		} else if (selection.equals("east") && currentRoom.getEasthExit() == true) {
			updatedRoom = matchCords(currentRoom, updatedRoom, rooms, selection, 1, 0);
		} else if (selection.equals("south") && currentRoom.getSouthExit() == true) {
			updatedRoom = matchCords(currentRoom, updatedRoom, rooms, selection, 0, -1);
		} else if (selection.equals("west") && currentRoom.getWestExit() == true) {
			updatedRoom = matchCords(currentRoom, updatedRoom, rooms, selection, -1, 0);
		} else {
			System.out.println("You attempt to exit " + selection + ", but there's a literal fucking wall in the way");

		}

		return updatedRoom;
	}

	private static Room matchCords(Room currentRoom, Room updatedRoom, Room[] rooms, String selection,
			int cordMovementX, int cordMovementY) {
		int[] currentChords;
		int[] updatedChords;
		currentChords = currentRoom.getCords();

		System.out.println("You moved " + selection + "!");
		updatedChords = new int[] { currentChords[0] + cordMovementX, currentChords[1] + cordMovementY };
		for (int i = 0; i < rooms.length; i++) {
			if (Arrays.equals(rooms[i].getCords(), updatedChords)) {
				updatedRoom = rooms[i];
			}
		}

		return updatedRoom;
	}

	private static Enemy enterBattle(Player user, Room currentRoom, String selection) {
		List<Enemy> roomEnemies = currentRoom.getEnemies();
		Enemy selectedEnemy = null;
		for (int i = 0; i < roomEnemies.size(); i++) {
			if (selection.contains(roomEnemies.get(i).getName().toLowerCase())) {
				user.setIsInBattle(true);
				selectedEnemy = roomEnemies.get(i);
				battle(selectedEnemy, user, selection);
				break;
			} else if (!selection.contains(roomEnemies.get(i).getName().toLowerCase())) {
				System.out.println("You try to attack a " + selection.substring(selection.lastIndexOf(" ") + 1)
						+ " even though there isn't one in the room...");
				break;
			}
		}

		return selectedEnemy;

	}

	private static void battle(Enemy selectedEnemy, Player user, String selection) {

		Scanner input = new Scanner(System.in);
		int battleSelection;
		int damageGiven;
		boolean isPlayerTurn;

		if (selection.contains("attack")) {
			isPlayerTurn = true;
			commands.listApproachEnemy(selectedEnemy);
		} else {
			isPlayerTurn = false;
		}

		while (user.isAlive() && selectedEnemy.isAlive()) {
			if (isPlayerTurn) {
				commands.listBattleOptions();
				battleSelection = input.nextInt();
				if (battleSelection == 1) {
					damageGiven = attack(selectedEnemy, user, isPlayerTurn);
					selectedEnemy.setHp(selectedEnemy.getHp() - damageGiven);
					commands.listDamageToEnemy(selectedEnemy, damageGiven);
					isPlayerTurn = false;
				}
			}
			if (!isPlayerTurn) {
				damageGiven = attack(selectedEnemy, user, isPlayerTurn);
				user.setHp(user.getHp() - damageGiven);

				if (user.getHp() > 0) {
					commands.listDamageToPlayer(user, selectedEnemy, damageGiven);
					isPlayerTurn = true;
				} else {
					commands.listDamageToPlayer(user, selectedEnemy, damageGiven);
					user.death();
					break;
				}

			}
		}

	}

	private static int attack(Enemy selectedEnemy, Player user, boolean isPlayerTurn) {
		int damageGiven;

		if (isPlayerTurn) {
			if (user.getAtk() + user.getLvl() + user.getWeaponAtk() > selectedEnemy.getDef() / 4) {
				damageGiven = user.getAtk() + user.getWeaponAtk() + user.getWeaponAtk() - selectedEnemy.getDef() / 4;
			} else {
				damageGiven = 1;
			}
		} else if (!isPlayerTurn) {
			if (selectedEnemy.getAtk() > user.getDef() / 4) {
			}
			damageGiven = (selectedEnemy.getAtk() + selectedEnemy.getLvl()) + selectedEnemy.getLvl() / 3
					- (user.getDef() / 8 + user.getArmorDef());
		} else {
			damageGiven = 1;
		}
		return damageGiven;
	}
}

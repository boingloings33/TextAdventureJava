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
			if (selection.equals(MenuItems.HELP)) {
				commands.listCommands();
			} else if (selection.equals(MenuItems.ROOM)) {
				examineRoom(user.getCurrentRoom());
			} else if (selection.equals(MenuItems.NORTH) || selection.equals(MenuItems.EAST)
					|| selection.equals(MenuItems.SOUTH) || selection.equals(MenuItems.WEST)) {
				user.setCurrentRoom(moveRooms(user.getCurrentRoom(), input, selection));
			} else if (selection.startsWith(MenuItems.ATTACK)) {
				enterBattle(user, user.getCurrentRoom(), selection);
			} else if (selection.equals(MenuItems.WEAPON)) {
				commands.listEquippedWeapon(user);
			} else if (selection.equals(MenuItems.ARMOR)) {
				commands.listEquippedArmor(user);
			} else if (selection.equals(MenuItems.INVENTORY)){
				commands.listInventory(user);
			} else if (selection.equals(MenuItems.EQUIPS)){
				commands.listPlayerSlots(user);
			} else if (selection.equals(MenuItems.LEVEL)){
				commands.listLevel(user);
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
		commands.listRoom(room, enemyNames, itemNames);
	}

	private static Room moveRooms(Room currentRoom, Scanner input, String selection) {
		GeneratedRooms generatedRooms = new GeneratedRooms();
		Room[] rooms = generatedRooms.getRooms();
		Room updatedRoom = currentRoom;

		if (selection.equals(MenuItems.NORTH) && currentRoom.getNorthExit() == true) {
			updatedRoom = matchCords(currentRoom, updatedRoom, rooms, selection, 0, 1);
		} else if (selection.equals(MenuItems.EAST) && currentRoom.getEasthExit() == true) {
			updatedRoom = matchCords(currentRoom, updatedRoom, rooms, selection, 1, 0);
		} else if (selection.equals(MenuItems.SOUTH) && currentRoom.getSouthExit() == true) {
			updatedRoom = matchCords(currentRoom, updatedRoom, rooms, selection, 0, -1);
		} else if (selection.equals(MenuItems.WEST) && currentRoom.getWestExit() == true) {
			updatedRoom = matchCords(currentRoom, updatedRoom, rooms, selection, -1, 0);
		} else {
			commands.listBadExit(selection);
		}

		return updatedRoom;
	}

	private static Room matchCords(Room currentRoom, Room updatedRoom, Room[] rooms, String selection,
			int cordMovementX, int cordMovementY) {
		int[] currentChords;
		int[] updatedChords;
		currentChords = currentRoom.getCords();

		commands.listMovement(selection);
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

		if (selection.contains(MenuItems.ATTACK)) {
			isPlayerTurn = true;
			commands.listApproachEnemy(selectedEnemy);
		} else {
			isPlayerTurn = false;
		}

		while (user.isAlive() && selectedEnemy.isAlive()) {
			if (isPlayerTurn) {
				commands.listBattleOptions();
				battleSelection = input.nextInt();
				if (battleSelection == 1 && selectedEnemy.isAlive()) {
					damageGiven = attack(selectedEnemy, user, isPlayerTurn);
					selectedEnemy.setHp(selectedEnemy.getHp() - damageGiven);
					commands.listDamageToEnemy(selectedEnemy, damageGiven);
					isPlayerTurn = false;
				}
			}
			if (!selectedEnemy.isAlive()) {
				isPlayerTurn = true;
				selectedEnemy.death(user);
				break;
			}
			if (!isPlayerTurn) {
				damageGiven = attack(selectedEnemy, user, isPlayerTurn);
				user.setHp(user.getHp() - damageGiven);
				if (user.isAlive()) {
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
				damageGiven = user.getAtk() + user.getWeaponAtk() - selectedEnemy.getDef() / 4;
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

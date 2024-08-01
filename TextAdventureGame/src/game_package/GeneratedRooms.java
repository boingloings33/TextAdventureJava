package game_package;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneratedRooms {
	private Enemy rat = new Enemy("Rat", generateRandom(1, 2), 10, 1, 1, 1);
	private Enemy bat = new Enemy("Bat", generateRandom(1, 2), 5, 1, 1, 1);
	private Room room00 = new Room(new int[] { 0, 0 }, new String[] { "Rusty dagger", "Empty Bowl" },
			generateEnemyList(rat, rat), "The Cave", true, false, false, false);
	private Room room01 = new Room(new int[] { 0, 1 }, new String[] { "Empty Cup" }, generateEnemyList(rat, rat, bat),
			"The Cave", false, false, true, false);
	private Room[] rooms = { room00, room01 };
	
	public int generateRandom(int num1, int num2) {
		int randomNumber = ThreadLocalRandom.current().nextInt(num1, num2 + 1);
		return randomNumber;
	}
	public List<Enemy> generateEnemyList(Enemy... enemyArr) {
		List<Enemy> enemies = Arrays.asList(enemyArr);
		return enemies;
	}
	
	public Room[] getRooms() {
		return this.rooms;
	}
	
}

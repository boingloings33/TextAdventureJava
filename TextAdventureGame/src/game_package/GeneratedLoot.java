package game_package;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GeneratedLoot {
	
	int COMMON = 3;
	int UNCOMMON = 7000;
	int RARE = 9500;
	int MEGA_RARE = 9800;
	int MAX_NUMBER = 10000;
	
	public Item generateRandomFood() {
		Random rand = new Random();
		int randomNumber;
		Item bread = new Item("Bread", "x", 5, 0, 0, ItemType.REAGENT);
		Item apple = new Item("Apple", "x", 3, 0, 0, ItemType.REAGENT);
		Item cheese = new Item("Cheese", "x", 7, 0, 0, ItemType.REAGENT);
		
		Item[] items = {bread, apple, cheese};
		
		randomNumber = rand.nextInt(items.length);
		return items[randomNumber];
		
	}

	public Item generateEnemyLoot() {
		Item food = generateRandomFood();
		int randomNumber;
		Random rand = new Random();
		randomNumber = rand.nextInt(MAX_NUMBER);
		
		if(randomNumber > COMMON) {
			return food;
		} else {
			return null;
		}
	}
}

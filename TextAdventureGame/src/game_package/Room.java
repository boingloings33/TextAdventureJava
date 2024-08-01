package game_package;

import java.util.ArrayList;
import java.util.List;

public class Room {
	private int[] coordinates = new int[2];
	private String[] items;
	List<Enemy> enemies = new ArrayList<>();
	private String location;
	private boolean northExit;
	private boolean eastExit;
	private boolean southExit;
	private boolean westExit;

	public Room(int[] cords, String[] items, List<Enemy> enemies, String location, boolean northExit, boolean eastExit,
			boolean southExit, boolean westExit) {
		this.coordinates = cords;
		this.items = items;
		this.enemies = enemies;
		this.location = location;
		this.northExit = northExit;
		this.eastExit = eastExit;
		this.southExit = southExit;
		this.westExit = westExit;
	}

	public String getLocation() {
		return this.location;
	}

	public List<Enemy> getEnemies() {
		return this.enemies;
	}

	public String[] getItems() {
		return this.items;
	}

	public int[] getCords() {
		return this.coordinates;
	}

	public boolean getNorthExit() {
		return this.northExit;
	}

	public boolean getEasthExit() {
		return this.eastExit;
	}

	public boolean getSouthExit() {
		return this.southExit;
	}

	public boolean getWestExit() {
		return this.westExit;
	}

	public String getAvailableExits() {

		boolean[] exitBooleans = { this.northExit, this.eastExit, this.southExit, this.westExit };
		String[] exitNames = {"North", "East", "South", "West"};
		List<String> availableExitsList = new ArrayList<>();
		
		for(int i = 0; i < exitBooleans.length; i++) {
			if (exitBooleans[i] == true) {
				availableExitsList.add(exitNames[i]);
			}
		}
		
		String availableExits = String.join(", ", availableExitsList);
		return availableExits;

	}

}

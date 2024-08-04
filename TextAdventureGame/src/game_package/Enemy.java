package game_package;
import java.util.List;
import java.util.UUID;


public class Enemy {
	private String name;
	private int maxHp;
	private int hp;
	private int lvl;
	private int atk;
	private int def;
	private int crit;
	String id = UUID.randomUUID().toString();


	public Enemy(String name, int lvl, int maxHp, int atk, int def, int crit) {
		this.name = name;
		this.lvl = lvl;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.atk = atk;
		this.def = def;
		this.crit = crit;
	}

	public boolean isAlive() {
		return hp > 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
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

	public int getLvl() {
		return this.lvl;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHp() {
		return this.hp;
	}

	public int getMaxHp() {
		return this.maxHp;
	}

	public int getCrit() {
		return this.crit;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void death(Player user) {
		List<Enemy> enemyList = user.getCurrentRoom().getEnemies();
		System.out.println("You have slain the " + this.name + "!");
		for (int i = 0; i < enemyList.size(); i++) {
			if(enemyList.get(i).getId() == this.id) {
				enemyList.remove(i);
			}
		}
		user.getCurrentRoom().setEnemies(enemyList);
		user.setIsExploring(true);
		
	}
	

}

package SpaceExplorer;

public class Game {
	private static Game instance;
	
	private Game() {}
	
	public static Game getCurrentGame() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		System.out.println("Space Explorer");
	}
}
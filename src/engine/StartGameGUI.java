package engine;

import java.io.IOException;

import model.characters.Hero;

public class StartGameGUI {
    public StartGameGUI() {
    }

    public void startGame() throws IOException {
    	Game game = new Game();
        game.loadHeroes("Heroes.csv");
      
            Hero h = game.availableHeroes.get(0);
            game.startGame(h);
        
    }
}

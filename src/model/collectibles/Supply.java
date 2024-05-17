package model.collectibles;

import model.characters.Hero;
import model.world.CharacterCell;
import engine.Game;
import model.characters.Character;

public class Supply implements Collectible {
	private Hero hero; // Reference to the Hero object

    public void setHero(Hero hero) {
        this.hero = hero;
    }
	@Override
	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);
	}

	@Override
	public void use(Hero h) {
		h.getSupplyInventory().remove(this);
	}
	public String toString() {
		  if (hero != null) {
	            return String.valueOf(hero.getSupplyInventory().size());
	        } else {
	            return "0"; // Default value if hero is not set
	        }
	}
}



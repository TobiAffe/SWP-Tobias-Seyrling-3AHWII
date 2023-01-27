package schiffe_versenken;

import java.util.ArrayList;

public class ShipMain {
	public static void main(String[] args) {

		Playground p = new Playground(100, 10);
		
		ArrayList<Ship> ships = new ArrayList<Ship>();
		// Schlachtschiff
		for (int i = 0; i < 1; i++) {
			ships.add(new Ship(5));
		}

		// Zerstörer
		for (int i = 0; i < 3; i++) {
			ships.add(new Ship(3));
		}

		// Kreuzer
		for (int i = 0; i < 2; i++) {
			ships.add(new Ship(4));
		}

		// U-Boote
		for (int i = 0; i < 4; i++) {
			ships.add(new Ship(2));
		}
		
		while(true) {
		p.initPlayground();
		p = ShipFactory.create(p, ships);
		System.out.println(p);
		}
	}
}

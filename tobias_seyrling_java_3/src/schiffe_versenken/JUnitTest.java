package schiffe_versenken;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class JUnitTest {
	
	Playground p = new Playground(10, 10);
	static ArrayList<Ship> ships = new ArrayList<Ship>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		p.initPlayground();
		p = ShipFactory.create(p, ships);
		System.out.println(p);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@RepeatedTest(10000)
	void testCreate() {
		for(int y = 0; y < p.getPlayground().length; y++) {
			for(int x = 0; x < p.getPlayground()[y].length; x++) {
				if(p.getPlayground()[x][y] instanceof ShipPart) {
					shipPartExists((ShipPart)p.getPlayground()[x][y]);
				}
			}
		}
	}
	
	void shipPartExists(ShipPart shipPart) {
		for (int i = 0; i < ships.size(); i++) {
			for (int j = 0; j < ships.get(i).getShipParts().size(); j++) {
				if(ships.get(i).getShipParts().get(j).equals(shipPart)) {
					return;
				}
			}
		}
		fail("ShipPart doesn't exist or is not positioned correctly");
	}
	
}

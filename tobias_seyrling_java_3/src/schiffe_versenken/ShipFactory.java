package schiffe_versenken;

import java.util.ArrayList;

public class ShipFactory {

	static public Playground init(Playground p) {

		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.clear();
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

		for (int y = 0; y < p.getPlayground().length; y++) {
			for (int x = 0; x < p.getPlayground()[y].length; x++) {
				p.getPlayground()[y][x] = new Water();
			}
		}
		for (int i = 0; i < ships.size(); i++) {
			int posX, posY = 0;
			int count = 0;
			do {
				posX = (int) (Math.random() * p.getPlayground().length - 1);
				posY = (int) (Math.random() * p.getPlayground()[i].length - 1);
				ships.get(i).setDirection();
				count++;
				if (count > p.getPlayground().length * 100) {
					ShipFactory.init(p);
					count = 0;
				}
			} while (!shipCheck(posX, posY, p, ships.get(i)));
			for (int j = 0; j < ships.get(i).getLength(); j++) {
				ShipPart shipPart = new ShipPart(ships.get(i).getDirection() ? posX + j : posX,
						!ships.get(i).getDirection() ? posY + j : posY, false, ships.get(i));
				ships.get(i).addShipPart(shipPart);
				p.getPlayground()[shipPart.getPosX()][shipPart.getPosY()] = shipPart;
			}
		}
		return p;
	}

	private static boolean shipCheck(int posX, int posY, Playground p, Ship ship) {
		for (int i = 0; i < ship.getLength(); i++) {
			if (!shipPartCheck(ship.getDirection() ? posX + i : posX, !ship.getDirection() ? posY + i : posY, p)) {
				return false;
			}
		}
		return true;
	}

	private static boolean shipPartCheck(int posX, int posY, Playground p) {
		return check(posX, posY, p, true) && check(posX, posY + 1, p, false) && check(posX - 1, posY + 1, p, false)
				&& check(posX + 1, posY + 1, p, false) && check(posX - 1, posY, p, false)
				&& check(posX + 1, posY, p, false) && check(posX, posY - 1, p, false)
				&& check(posX - 1, posY - 1, p, false) && check(posX + 1, posY - 1, p, false);
	}

	private static boolean check(int posX, int posY, Playground p, boolean isMiddleCheckPart) {
		if (posX < 0 || posY < 0 || posY >= p.getPlayground().length || posX >= p.getPlayground()[posY].length) {
			return !isMiddleCheckPart;
		}
		return !(p.getPlayground()[posX][posY] instanceof ShipPart);
	}
}

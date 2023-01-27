package schiffe_versenken;

import java.util.ArrayList;

public class ShipFactory {

	static public Playground create(Playground p, ArrayList<Ship> ships) {
		
		int posX, posY = 0;
		int count = 0;
		for (int i = 0; i < ships.size(); i++) {
			do {
				posX = (int) (Math.random() * p.getPlayground().length - 1);
				posY = (int) (Math.random() * p.getPlayground()[0].length - 1);
				ships.get(i).setDirection();
				count++;
				if (count > 1000) {
					System.out.println("ERROR");
					for (int j = 0; j < ships.get(i).getLength(); j++) {
						ships.get(i).getShipParts().clear();
					}
					p.initPlayground();
					count = 0;
					create(p,ships);
					return p;
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
		return check(posX, posY, p, true) && 
				check(posX, posY + 1, p, false) && 
				check(posX - 1, posY + 1, p, false)
				&& check(posX + 1, posY + 1, p, false) && 
				check(posX - 1, posY, p, false)
				&& check(posX + 1, posY, p, false) && 
				check(posX, posY - 1, p, false)
				&& check(posX - 1, posY - 1, p, false) && 
				check(posX + 1, posY - 1, p, false);
	}

	private static boolean check(int posX, int posY, Playground p, boolean isMiddleCheckPart) {
		if (posX < 0 || posY < 0 || posX >= p.getPlayground().length || posY >= p.getPlayground()[posX].length) {
			return !isMiddleCheckPart;
		}
		return !(p.getPlayground()[posX][posY] instanceof ShipPart);
	}
}

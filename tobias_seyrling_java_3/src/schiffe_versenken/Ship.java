package schiffe_versenken;

import java.util.ArrayList;

public class Ship {
	private ArrayList<ShipPart> shipParts = new ArrayList<ShipPart>();
	private int length;
	private boolean direction; //true -> wagrecht //false -> senkrecht
	private boolean sunken;
	
	public Ship(int length) {
		setDirection();
		this.length = length;;
	}
	
	public boolean isSunken() {
		boolean s = true;
		for (int i = 0; i < shipParts.size(); i++) {
			if(shipParts.get(i).isVisible()) {
				s = false;
			}
		}
		return false;
	}
	
	public ArrayList<ShipPart> getShipParts() {
		return shipParts;
	}
	
	public void addShipPart(ShipPart shipPart) {
		this.shipParts.add(shipPart);
	}
	
	public boolean getDirection() {
		return direction;
	}
	
	public void setDirection() {
		this.direction = Math.random() > 0.5;
	}

	public int getLength() {
		return length;
	}
	
	
}

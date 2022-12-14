package schiffe_versenken;

public class ShipPart extends Field {
	private Ship ship;
	private int posX;
	private int posY;
	
	public ShipPart(int posX, int posY, boolean visible, Ship ship) {
		this.ship = ship;
		this.posX = posX;
		this.posY = posY;
		this.visible = visible;
	}
		
	public String toString() {
		return super.toString() + "O";
	}

	public String print() {
		if(visible)
			return "O";
		else 
			return "#";
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	
}

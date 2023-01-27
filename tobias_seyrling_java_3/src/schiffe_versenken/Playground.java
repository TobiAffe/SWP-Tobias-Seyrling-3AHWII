package schiffe_versenken;

import java.util.ArrayList;

public class Playground {
	private Field[][] playground;
	
	public Field[][] getPlayground() {
		return playground;
	}

	public Playground(int lengthY, int lengthX) {;
		this.playground = new Field[lengthX][lengthY];
	}
	
	public String toString() {
		String str = "";
		for (int y = 0; y < playground.length; y++) {
			for (int x = 0; x < playground[y].length; x++) {
				str += playground[y][x] + "| ";
			}
			str += "\n";
		}
		return str;
	}
	
	public String print() {
		String str = "   ";
		for(int i = 0; i < playground.length; i++) {
			str += String.format("%02d|", i+1);
		} str += "\n";
		for (int y = 0; y < playground.length; y++) {
			str += String.format("%02d|", y+1);
			for (int x = 0; x < playground[y].length; x++) {
				str += " " + playground[y][x].print() + "|";
			}
			str += "\n";
		}
		return str;
	}
	
	public void initPlayground() {
		for (int y = 0; y < playground.length; y++) {
			for (int x = 0; x < playground[y].length; x++) {
				playground[y][x] = new Water();
			}
		}
	}
}

package java.schiffe_versenken;

public class Ship extends Field{
	
	private int[][] shipPartPos;
	private int length;
	private int orientation;
	static char symbol = 'X';
	
	public Ship(int length, int orientation) {
		shipPartPos = new int[length][2];
		this.length = length;
	}
	
	
}

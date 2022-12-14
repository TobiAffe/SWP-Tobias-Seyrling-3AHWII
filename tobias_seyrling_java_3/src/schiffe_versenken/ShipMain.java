package schiffe_versenken;

import java.util.ArrayList;

public class ShipMain {
	public static void main(String[] args) {

		Playground p = new Playground(10, 10);
		ShipFactory.init(p);

		System.out.println(p);
	}
}

package schiffe_versenken;

public class Water extends Field{
	
	public String toString() {
		return super.toString() + " ";
	}

	public String print() {
		if(visible)
			return " ";
		else 
			return "#";
	}
	
}

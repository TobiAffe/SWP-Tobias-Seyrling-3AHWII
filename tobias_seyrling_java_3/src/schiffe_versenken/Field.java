package schiffe_versenken;

public abstract class Field{
	boolean visible;

	public String toString() {
		return visible ? "_" : "*";
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public abstract String print();
}

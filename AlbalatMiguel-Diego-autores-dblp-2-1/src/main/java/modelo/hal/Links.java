package modelo.hal;

public class Links {
	public Self self;
	public Links() {}
	public Links(Self self) {
		super();
		this.self = self;
	}

	public Self getSelf() {
		return self;
	}

	public void setSelf(Self self) {
		this.self = self;
	}
	
	
}

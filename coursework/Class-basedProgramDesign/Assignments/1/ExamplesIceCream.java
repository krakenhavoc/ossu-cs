import tester.*;

interface IIceCream {
}

class EmptyServing implements IIceCream {
	boolean cone;
	
	EmptyServing(boolean cone) {
		this.cone = cone;
	}
}

class Scooped implements IIceCream {
	String flavor;
	IIceCream more;
	
	Scooped(String flavor, IIceCream more) {
		this.flavor = flavor;
		this.more		= more;
	}
}

class ExamplesIceCream {
	IIceCream cup = new Scooped("mint chip", new Scooped("coffee", new Scooped("black raspberry", new Scooped("caramel swirl", new EmptyServing(false)))));
	IIceCream cone = new Scooped("chocolate", new Scooped("vanilla", new Scooped("strawberry", new EmptyServing(true))));
}
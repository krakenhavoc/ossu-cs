import tester.*;

interface IHousing {
}

class Hut implements IHousing {
	int capacity;
	int population;
	
	Hut(int capacity, int population) {
		this.capacity		= capacity;
		this.population = population;
	}
}

class Inn implements IHousing {
	String name;
	int capacity;
	int population;
	int stalls;
	
	Inn(String name, int capacity, int population, int stalls) {
		this.name				= name;
		this.capacity		= capacity;
		this.population	= population;
		this.stalls			= stalls;
	}
}

class Castle implements IHousing {
	String name;
	String owner;
	int population;
	int carriageHouse;
	
	Castle(String name, String owner, int population, int carriageHouse) {
		this.name						= name;
		this.owner					= owner;
		this.population			= population;
		this.carriageHouse	= carriageHouse;
	}
}

interface ITransportation{

}

class Horse implements ITransportation {
	IHousing from;
	IHousing to;
	String name;
	String color;
	
	Horse(IHousing from, IHousing to, String name, String color) {
		this.from		= from;
		this.to			= to;
		this.name		= name;
		this.color	= color;
	}
}

class Carriage implements ITransportation {
	IHousing from;
	IHousing to;
	int tonnage;
	
	Carriage(IHousing from, IHousing to, int tonnage) {
		this.from			= from;
		this.to				= to;
		this.tonnage	= tonnage;
	}
}

class ExamplesTravel {
	IHousing hovel = new Hut(5, 1);
	IHousing winterfell = new Castle("Winterfell", "Stark", 500, 6);
	IHousing crossroads = new Inn("Inn At The Crossroads", 40, 20, 12);
	IHousing hobbitHouse = new Hut(20, 20);
	IHousing winterhold = new Castle("College of Winterhold", "Arch-Mage Savos Aren", 500, 0);
	IHousing beeAndBarb = new Inn("The Bee and Barb", 20, 14, 2);
	
	ITransportation horse1 = new Horse(hovel, hobbitHouse, "Galaxia", "White");
	ITransportation horse2 = new Horse(beeAndBarb, winterhold, "Shadowmere", "Black");
	ITransportation carriage1 = new Carriage(crossroads, winterfell, 4);
	ITransportation carriage2 = new Carriage(winterfell, winterhold, 20);
}

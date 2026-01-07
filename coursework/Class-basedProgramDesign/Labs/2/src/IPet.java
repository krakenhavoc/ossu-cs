import tester.Tester;

//to represent a pet owner
class Owner {
	String name;
	IPet pet;
	int age;

	Owner(String name, IPet pet, int age) {
		this.name = name;
		this.pet = pet;
		this.age = age;
	}

	// is this Owner older than the given Owner?
	boolean isOlder(Owner other) {
		return this.age > other.age;
	}

	// does the name of this Owner's pet match the given name?
	boolean sameNamePet(Owner other) {
		return this.pet.getName() == other.pet.getName();
	}
	
	Owner perish() {
		return new Owner(this.name, new NoPet(), this.age);
	}
}

//to represent a pet
interface IPet {
	String getName();
}

//to represent a pet cat
class Cat implements IPet {
	String name;
	String kind;
	boolean longhaired;

	Cat(String name, String kind, boolean longhaired) {
		this.name = name;
		this.kind = kind;
		this.longhaired = longhaired;
	}

	public String getName() {
		return this.name;
	}
}

//to represent a pet dog
class Dog implements IPet {
	String name;
	String kind;
	boolean male;

	Dog(String name, String kind, boolean male) {
		this.name = name;
		this.kind = kind;
		this.male = male;
	}

	public String getName() {
		return this.name;
	}
}

class NoPet implements IPet {
	NoPet() {
	}

	public String getName() {
		return "";
	}
}

class ExamplesOwner {
	IPet nala = new Cat("Nala", "Domestic Shorthair", false);
	IPet pazuzu = new Cat("Pazuzu", "Mainecoon", true);
	IPet luna = new Dog("Luna", "Cockapoo", false);
	IPet flynn = new Dog("Flynn", "Mixed Goodboy", true);
	IPet none = new NoPet();

	Owner luke = new Owner("Luke", nala, 30);
	Owner seema = new Owner("Seema", pazuzu, 40);
	Owner matt = new Owner("Matt", luna, 28);
	Owner rach = new Owner("Rachel", flynn, 25);
	Owner bev = new Owner("Beverly", none, 60);

	boolean testMotMeetsFuelEfficiency(Tester t) {
		return t.checkExpect(luke.sameNamePet(luke), true)
				&& t.checkExpect(luke.sameNamePet(seema), false)
				&& t.checkExpect(matt.sameNamePet(bev), false);
	}
}
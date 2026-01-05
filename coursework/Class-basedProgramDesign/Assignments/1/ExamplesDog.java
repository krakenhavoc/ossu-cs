import tester.*;

class Dog {
  String name;
  String breed;
  int yob;
  String state; // rep. as two-letter abbreviation
  boolean hypoallergenic;
  
  // the constructor
  Dog(String name, String breed, int yob, String state, boolean hypoallergenic) {
  	this.name 					= name;
  	this.breed 					= breed;
  	this.yob 						= yob;
  	this.state 					= state;
  	this.hypoallergenic = hypoallergenic;
  }
}

class ExamplesDog {
	Dog huffle 		= new Dog("Hufflepuff", "Wheaten Terrier", 2012, "TX", true);
	Dog pearl 		= new Dog("Pearl", "Labrador Retriever", 2016, "MA", false);
	Dog hercules 	= new Dog("Flynn", "Mixed Goodboy", 2021, "SC", false);
	Dog luna 			= new Dog("Luna", "Cockapoo", 2020, "SC", true);
}
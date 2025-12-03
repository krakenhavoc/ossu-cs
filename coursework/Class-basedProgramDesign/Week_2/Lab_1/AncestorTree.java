package Week_2.Lab_1;

interface AncestorTree {
    
}

class unknown implements AncestorTree {
    
}

class Person implements AncestorTree {
    String name;
    AncestorTree mother;
    AncestorTree father;

    Person(String name, AncestorTree mother, AncestorTree father) {
        this.name   = name;
        this.mother = mother;
        this.father = father;
    }
}

class ExamplesAncestorTree {
    AncestorTree unknown = new unknown();

    AncestorTree grandMother = new Person("Alice", unknown, unknown);
    AncestorTree grandFather = new Person("Bob", unknown, unknown);

    AncestorTree mother = new Person("Cathy", grandMother, grandFather);
    AncestorTree father = new Person("David", unknown, unknown);

    AncestorTree child = new Person("Eva", mother, father);
}
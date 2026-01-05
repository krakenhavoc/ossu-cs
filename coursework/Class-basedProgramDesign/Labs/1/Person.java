package Week_2.Lab_1;

class Person {
    String name;
    int age;
    String gender;
    Address address;

    Person(String name, int age, String gender, Address address) {
        this.name   = name;
        this.age    = age;
        this.gender = gender;
        this.address = address;
    }
}

class Address {
    String city;
    String state;

    Address(String city, String state) {
        this.city   = city;
        this.state  = state;
    }
}
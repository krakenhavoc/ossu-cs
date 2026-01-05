package Week_2.Lab_1;

class DeliMenu {
    
}

interface MenuItem {

}

class Soup implements MenuItem {
    String name;
    int price;
    boolean isVegetarian;

    Soup(String name, int price, boolean isVegetarian) {
        this.name = name;
        this.price = price;
        this.isVegetarian = isVegetarian;
    }
}

class Salad implements MenuItem {
    String name;
    int price;
    boolean isVegetarian;
    String dressing;

    Salad(String name, int price, boolean isVegetarian, String dressing) {
        this.name = name;
        this.price = price;
        this.isVegetarian = isVegetarian;
        this.dressing = dressing;
    }
}

class Sandwich implements MenuItem {
    String name;
    int price;
    String bread;
    String filling1;
    String filling2;

    Sandwich(String name, int price, String bread, String filling1, String filling2) {
        this.name = name;
        this.price = price;
        this.bread = bread;
        this.filling1 = filling1;
        this.filling2 = filling2;
    }
}

class Examples{
    Examples(){}

    MenuItem tomato = new Soup("Tomato Soup", 5, true);
    MenuItem chickenNoodle = new Soup("Chicken Noodle Soup", 7, false);

    MenuItem caesarSalad = new Salad("Caesar Salad", 8, false, "Caesar");
    MenuItem gardenSalad = new Salad("Garden Salad", 6, true, "Ranch");

    MenuItem turkeySandwich = new Sandwich("Turkey Sandwich", 10, "Wheat", "Turkey", "Lettuce");
    MenuItem capreseSandwich = new Sandwich("Caprese Sandwich", 9, "Ciabatta", "Tomato", "Mozzarella");
}
import tester.Tester;

class Book {
  String title;
  String author;
  int price;

  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }
}

interface ILoBook {
  int count();

  int totalPrice();

  ILoBook cheaperThan(int price);
}

class MtLoBook implements ILoBook {
  MtLoBook() {
  }

  public int count() {
    return 0;
  }

  public int totalPrice() {
    return 0;
  }

  public ILoBook cheaperThan(int price) {
    return this;
  }
}

class ConsLoBook implements ILoBook {
  Book first;
  ILoBook rest;

  ConsLoBook(Book first, ILoBook rest) {
    this.first = first;
    this.rest = rest;
  }

  public int count() {
    return 1 + rest.count();
  }

  public int totalPrice() {
    return this.first.price + this.rest.totalPrice();
  }

  public ILoBook cheaperThan(int price) {
    if (this.first.price < price) {
      return new ConsLoBook(this.first, this.rest.cheaperThan(price));
    }
    else {
      return this.rest.cheaperThan(price);
    }
  }
}

class ExamplesILoBook {
  ExamplesILoBook() {
  }

  Book hp1 = new Book("HP1", "JKR", 20);
  Book hp2 = new Book("HP2", "JKR", 30);
  Book hp3 = new Book("HP3", "JKR", 40);

  ILoBook mtList = new MtLoBook();
  ILoBook hpList1 = new ConsLoBook(hp1, mtList);
  ILoBook hpList3 = new ConsLoBook(hp1, new ConsLoBook(hp2, new ConsLoBook(hp3, mtList)));
  ILoBook hpList4 = new ConsLoBook(hp1, this.hpList3);

  boolean testILoBookCount(Tester t) {
    return t.checkExpect(mtList.count(), 0)
        && t.checkExpect(hpList3.count(), 3)
        && t.checkExpect(hpList1.count(), 1)
        && t.checkExpect(hpList4.count(), 4);
  }

  boolean testILoBookTotalPrice(Tester t) {
    return t.checkExpect(mtList.count(), 0)
        && t.checkExpect(hpList1.totalPrice(), 20)
        && t.checkExpect(hpList3.totalPrice(), 90)
        && t.checkExpect(hpList4.totalPrice(), 110);
  }

  boolean testILoBookCheaperThan(Tester t) {
    return t.checkExpect(mtList.cheaperThan(0), mtList)
        && t.checkExpect(hpList1.cheaperThan(21), hpList1)
        && t.checkExpect(hpList1.cheaperThan(1), mtList)
        && t.checkExpect(hpList3.cheaperThan(40), new ConsLoBook(hp1, new ConsLoBook(hp2, mtList)))
        && t.checkExpect(hpList3.cheaperThan(50), hpList3);
  }
}

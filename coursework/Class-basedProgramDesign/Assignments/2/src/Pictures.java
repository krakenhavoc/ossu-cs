import tester.Tester;

interface IPicture {
  int getWidth();

  int countShapes();

  int comboDepth();

  IPicture mirror();

  String pictureRecipe(int depth);
}

class Shape implements IPicture {
  String kind;
  int size;

  Shape(String kind, int size) {
    this.kind = kind;
    this.size = size;
  }

  public int getWidth() {
    return this.size;
  }

  public int countShapes() {
    return 1;
  }

  public int comboDepth() {
    return 0;
  }

  public IPicture mirror() {
    return this;
  }

  public String pictureRecipe(int depth) {
    return this.kind;
  }
}

class Combo implements IPicture {
  String name;
  IPicture operation;

  Combo(String name, IPicture operation) {
    this.name = name;
    this.operation = operation;
  }

  public int getWidth() {
    return this.operation.getWidth();
  }

  public int countShapes() {
    return this.operation.countShapes();
  }

  public int comboDepth() {
    return 1 + this.operation.comboDepth();
  }

  public IPicture mirror() {
    return new Combo(this.name, this.operation.mirror());
  }

  public String pictureRecipe(int depth) {
    if (depth <= 0) {
      return this.name;
    }
    else {
      return this.operation.pictureRecipe(depth);
    }
  }
}

class Scale implements IPicture {
  IPicture picture;

  Scale(IPicture picture) {
    this.picture = picture;
  }

  public int getWidth() {
    return this.picture.getWidth() * 2;
  }

  public int countShapes() {
    return this.picture.countShapes();
  }

  public int comboDepth() {
    return this.picture.comboDepth();
  }

  public IPicture mirror() {
    return new Scale(this.picture.mirror());
  }

  public String pictureRecipe(int depth) {
    return "scale(" + this.picture.pictureRecipe(depth - 1) + ")";
  }
}

class Beside implements IPicture {
  IPicture picture1;
  IPicture picture2;

  Beside(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2;
  }

  public int getWidth() {
    return this.picture1.getWidth() + this.picture2.getWidth();
  }

  public int countShapes() {
    return this.picture1.countShapes() + this.picture2.countShapes();
  }

  public int comboDepth() {
    if (this.picture1.comboDepth() > this.picture2.comboDepth()) {
      return this.picture1.comboDepth();
    }
    else {
      return this.picture2.comboDepth();
    }
  }

  public IPicture mirror() {
    return new Beside(this.picture2.mirror(), this.picture1.mirror());
  }

  public String pictureRecipe(int depth) {
    return "beside(" + this.picture1.pictureRecipe(depth - 1) + ", " + this.picture2.pictureRecipe(depth - 1) + ")";
  }
}

class Overlay implements IPicture {
  IPicture picture1;
  IPicture picture2;

  Overlay(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2;
  }

  public int getWidth() {
    if (this.picture1.getWidth() > this.picture2.getWidth()) {
      return this.picture1.getWidth();
    }
    else {
      return this.picture2.getWidth();
    }
  }

  public int countShapes() {
    return this.picture1.countShapes() + this.picture2.countShapes();
  }

  public int comboDepth() {
    if (this.picture1.comboDepth() > this.picture2.comboDepth()) {
      return this.picture1.comboDepth();
    }
    else {
      return this.picture2.comboDepth();
    }
  }

  public IPicture mirror() {
    return new Overlay(this.picture1.mirror(), this.picture2.mirror());
  }

  public String pictureRecipe(int depth) {
    return "overlay(" + this.picture1.pictureRecipe(depth - 1) + ", " + this.picture2.pictureRecipe(depth - 1) + ")";
  }
}

class ExamplesPictures {
  IPicture circle = new Shape("circle", 20);
  IPicture square = new Shape("square", 30);
  IPicture bigCircle = new Combo("bigCircle", new Scale(circle));
  IPicture squareOnCircle = new Combo("square on circle", new Overlay(square, bigCircle));
  IPicture doubledSquareOnCircle = new Combo("doubled square on circle", new Beside(squareOnCircle, squareOnCircle));
  IPicture quadSquareOnCircle = new Combo("quadrupled square on circle",
      new Overlay(doubledSquareOnCircle, doubledSquareOnCircle));
  IPicture bigCircleOnDoubledSquareOnCircle = new Combo("bigCircle on doubled square on circle",
      new Overlay(bigCircle, doubledSquareOnCircle));

  boolean testGetWidth(Tester t) {
    return t.checkExpect(circle.getWidth(), 20)
        && t.checkExpect(square.getWidth(), 30)
        && t.checkExpect(bigCircle.getWidth(), 40)
        && t.checkExpect(squareOnCircle.getWidth(), 40)
        && t.checkExpect(doubledSquareOnCircle.getWidth(), 80);
  }

  boolean testCountShapes(Tester t) {
    return t.checkExpect(circle.countShapes(), 1)
        && t.checkExpect(bigCircle.countShapes(), 1)
        && t.checkExpect(squareOnCircle.countShapes(), 2)
        && t.checkExpect(doubledSquareOnCircle.countShapes(), 4);
  }

  boolean testComboDepth(Tester t) {
    return t.checkExpect(circle.comboDepth(), 0)
        && t.checkExpect(bigCircle.comboDepth(), 1)
        && t.checkExpect(squareOnCircle.comboDepth(), 2)
        && t.checkExpect(doubledSquareOnCircle.comboDepth(), 3)
        && t.checkExpect(quadSquareOnCircle.comboDepth(), 4)
        && t.checkExpect(bigCircleOnDoubledSquareOnCircle.comboDepth(), 4);
  }

  boolean testMirror(Tester t) {
    return t.checkExpect(circle.mirror(), circle)
        && t.checkExpect(bigCircle.mirror(), bigCircle)
        && t.checkExpect(squareOnCircle.mirror(), squareOnCircle)
        && t.checkExpect(doubledSquareOnCircle.mirror(),
            new Combo("doubled square on circle", new Beside(squareOnCircle.mirror(), squareOnCircle.mirror())))
        && t.checkExpect(quadSquareOnCircle.mirror(),
            new Combo("quadrupled square on circle", new Overlay(
                new Combo("doubled square on circle", new Beside(squareOnCircle.mirror(), squareOnCircle.mirror())),
                new Combo("doubled square on circle", new Beside(squareOnCircle.mirror(), squareOnCircle.mirror())))));
  }

  boolean testPictureRecipe(Tester t) {
    return t.checkExpect(circle.pictureRecipe(0), "circle")
        && t.checkExpect(circle.pictureRecipe(4), "circle")
        && t.checkExpect(doubledSquareOnCircle.pictureRecipe(2),
            "beside(overlay(square, bigCircle), overlay(square, bigCircle))")
        && t.checkExpect(doubledSquareOnCircle.pictureRecipe(3),
            "beside(overlay(square, scale(circle)), overlay(square, scale(circle)))");
  }
}

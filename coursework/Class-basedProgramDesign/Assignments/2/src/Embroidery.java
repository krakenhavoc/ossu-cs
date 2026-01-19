import tester.Tester;

interface Motif {
  double averageDifficulty();

  double totalDifficulty();

  int count();

  String embroideryInfo();
}

class CrossStitchMotif implements Motif {
  String description;
  double difficulty;

  CrossStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  public double averageDifficulty() {
    return this.difficulty;
  }

  public double totalDifficulty() {
    return this.difficulty;
  }

  public int count() {
    return 1;
  }

  public String embroideryInfo() {
    return this.description + " (cross stitch)";
  }
}

class ChainStitchMotif implements Motif {
  String description;
  double difficulty;

  ChainStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  public double averageDifficulty() {
    return this.difficulty;
  }

  public double totalDifficulty() {
    return this.difficulty;
  }

  public int count() {
    return 1;
  }

  public String embroideryInfo() {
    return this.description + " (chain stitch)";
  }
}

class EmbroideryPiece {
  String name;
  Motif motif;

  EmbroideryPiece(String name, Motif motif) {
    this.name = name;
    this.motif = motif;
  }

  public double averageDifficulty() {
    return this.motif.averageDifficulty();
  }

  public String embroideryInfo() {
    return this.name + ": " + this.motif.embroideryInfo() + ".";
  }
}

interface ILoMotif {
  double averageDifficulty();

  double sumDifficulty();

  int count();

  String getEmbroideryInfo();
}

class MtLoMotif implements ILoMotif {
  MtLoMotif() {
  }

  public double averageDifficulty() {
    return 0;
  }

  public double sumDifficulty() {
    return 0;
  }

  public int count() {
    return 0;
  }

  public String getEmbroideryInfo() {
    return "";
  }

}

class ConsLoMotif implements ILoMotif {
  Motif first;
  ILoMotif rest;

  ConsLoMotif(Motif first, ILoMotif rest) {
    this.first = first;
    this.rest = rest;
  }

  public double averageDifficulty() {
    int totalCount = this.count();
    if (totalCount == 0) {
      return 0.0;
    }
    return this.sumDifficulty() / totalCount;
  }

  public double sumDifficulty() {
    return this.first.totalDifficulty() + this.rest.sumDifficulty();
  }

  public int count() {
    return this.first.count() + this.rest.count();
  }

  public String getEmbroideryInfo() {
    if (this.rest instanceof MtLoMotif) {
      return this.first.embroideryInfo();
    }
    else {
      return this.first.embroideryInfo() + ", " + this.rest.getEmbroideryInfo();
    }
  }
}

class GroupMotif implements Motif {
  String description;
  ILoMotif motifs;

  GroupMotif(String description, ILoMotif motifs) {
    this.description = description;
    this.motifs = motifs;
  }

  public double averageDifficulty() {
    return this.motifs.averageDifficulty();
  }

  public double totalDifficulty() {
    return this.motifs.sumDifficulty();
  }

  public int count() {
    return this.motifs.count();
  }

  public String embroideryInfo() {
    return this.motifs.getEmbroideryInfo();
  }
}

class ExamplesEmbroidery {
  ILoMotif mtLoMotif = new MtLoMotif();
  Motif daisy = new CrossStitchMotif("daisy", 3.2);
  Motif poppy = new ChainStitchMotif("poppy", 4.75);
  Motif rose = new CrossStitchMotif("rose", 5.0);
  Motif flowers = new GroupMotif("flowers",
      new ConsLoMotif(rose, new ConsLoMotif(poppy, new ConsLoMotif(daisy, mtLoMotif))));
  Motif tree = new ChainStitchMotif("tree", 3.0);
  Motif bird = new CrossStitchMotif("bird", 4.5);
  Motif nature = new GroupMotif("nature",
      new ConsLoMotif(bird, new ConsLoMotif(tree, new ConsLoMotif(flowers, mtLoMotif))));
  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover", nature);

  boolean testTotalDifficulty(Tester t) {
    return t.checkExpect(nature.totalDifficulty(), 20.45);
  }

  boolean testCount(Tester t) {
    return t.checkExpect(pillowCover.motif.count(), 5);
  }

  boolean testAverageDifficulty(Tester t) {
    return t.checkExpect(mtLoMotif.averageDifficulty(), 0.0)
        && t.checkExpect(daisy.averageDifficulty(), 3.2)
        && t.checkInexact(flowers.averageDifficulty(), 4.32, 0.01)
        && t.checkExpect(pillowCover.averageDifficulty(), 4.09);
  }

  boolean testEmroideryInfo(Tester t) {
    return t.checkExpect(pillowCover.embroideryInfo(),
        "Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch), daisy (cross stitch).");
  }
}

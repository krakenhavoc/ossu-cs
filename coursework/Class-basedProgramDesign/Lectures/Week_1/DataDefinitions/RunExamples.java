package Week_1.DataDefinitions;

public class RunExamples {
    public static void main(String[] args) {
        ExamplesBooks ex = new ExamplesBooks();
        System.out.println("Author: " + ex.pat.name + ", yob: " + ex.pat.yob);
        System.out.println("Publisher: " + ex.doubleday.name + ", country: " + ex.doubleday.countyOfOperation + ", year: " + ex.doubleday.yearEstablished);
        System.out.println("Book: " + ex.beaches.title + ", price: " + ex.beaches.price);
    }
}

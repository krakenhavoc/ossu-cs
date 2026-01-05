package Week_1.Unions;

public class RunExamples {
    public static void main(String[] args) {
        ExamplesIStation ex = new ExamplesIStation();
        System.out.println("Harvard (TStop): " + ((TStop)ex.harvard).name + ", line: " + ((TStop)ex.harvard).line + ", price: " + ((TStop)ex.harvard).price);
        System.out.println("Back Bay (CommStation) express? " + ((CommStation)ex.backbay).express);
    }
}

package BDD;
public class Node {
    public String variable;    // podľa ktorého písmenka spracovávam
    public String formula;     // samotný string ( napr. AB+AC+BD  )

    Node left;          // actual = 0
    Node right;         // actual = 1

    // KONŠTRUKTOR
    public Node(){
    }
    public Node(String formula){
        this.formula = formula;
        left = null;
        right = null;
    }
    public Node(String formula, String variable){
        this.variable = variable;
        this.formula = formula;
        left = null;
        right = null;
    }
}

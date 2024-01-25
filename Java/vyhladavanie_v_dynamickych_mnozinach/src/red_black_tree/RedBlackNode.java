package red_black_tree;

public class RedBlackNode {
    public int data;
    public RedBlackNode ancestor; // parent
    public RedBlackNode left;
    public RedBlackNode right;
    public boolean color;   // TRUE = BLACK  // FALSE = RED

    public static final boolean BLACK = true;
    public static final boolean RED = false;

    private String meno_a_priezvisko;    // DOPLNKOVÉ DÁTA
    public String getMeno_a_priezvisko() {
        return meno_a_priezvisko;
    }
    public void setMeno_a_priezvisko(String meno_a_priezvisko) {
        this.meno_a_priezvisko = meno_a_priezvisko;
    }

    public RedBlackNode(int data){
        this.data = data;
        this.ancestor = null;
        this.left = null;
        this.right = null;
        this.color = RED;  // RED = false
    }
    public RedBlackNode(int data, RedBlackNode left, RedBlackNode right){
        this.data = data;
        this.ancestor = null;
        this.left = left;
        this.right = right;
        this.color = RED;  // RED = false
    }
    public String print_color(){
        if(this.color == BLACK){
            return "(BLACK)";
        }
        return "(RED)";
    }
    public boolean hasLeftChild(){
        if (left == null){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean hasRightChild(){
        if (right == null){
            return false;
        }
        else{
            return true;
        }
    }

}

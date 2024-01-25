package avl_tree;

public class Node {
    public int data;
    public int height;              // VÝŠKA NODEu je vzdialenosť k listu
    public Node ancestor = null;    // REFERENCIA NA PREDCHODCU (parenta)
    public Node left = null;
    public Node right = null;


    private String meno_a_priezvisko;    // DOPLNKOVÉ DÁTA
    public String getMeno_a_priezvisko() {
        return meno_a_priezvisko;
    }
    public void setMeno_a_priezvisko(String meno_a_priezvisko) {
        this.meno_a_priezvisko = meno_a_priezvisko;
    }


    //public int balanceFactor;
    // KONŠTRUKTOR
    public Node(int data){
        this.data = data;
        height = 0; // novo pridaný prvok ma vždy výšku 0
        ancestor = null;
        left = null;
        right = null;
        setMeno_a_priezvisko(null);

        //balanceFactor = -666;
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

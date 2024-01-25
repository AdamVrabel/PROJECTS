/**
 * DSA - PROJEKT 1
 *
 * @author Adam Vrabeľ
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("DSA PROJEKT - 1");
        System.out.println(" ");

        //////////// TESTOVACI PROGRAM ////////////
        Test test = new Test();
        int testNumber = 100000;

        test.avl_tree(testNumber);
        test.rb_tree(testNumber);
        test.hash_table_linear(testNumber);
        test.hash_table_chaining(testNumber);
        ///////////////////////////////////////////


        //Program run = new Program();
        //run.avl_tree();
        //run.hash_table_chaining();
        //run.hash_table_linear();
        //run.rb_tree();

    }
}
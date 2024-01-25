import avl_tree.*;
import hash_table_chaining.*;
import hash_table_linear_probing.*;
import red_black_tree.*;

import java.util.ArrayList;
import java.util.Random;

public class Test {
    private static Random random = new Random();
    private long startTime = 0;
    private double time = 0;
    private void startTimer(){
        startTime = System.currentTimeMillis();
    }
    private void stopTimer(){
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }



    //////////// AVL TREE ////////////
    public void avl_tree(int n){
        System.out.println("_____________________________________________________________________________");
        System.out.println("TESTOVACÍ PROGRAM - AVL TREE");
        System.out.println();

        // n = 10000;
        AvlTree strom = null;

        //////////////////////////////////////////////////////////////////////////////////////////
        // TEST AVL 1 (od 0 do n)
        // INSERT
        startTimer();
            //testAvl_to_n(n);
            strom = new AvlTree();
            for(int i = 0; i < n; i++){
                strom.insertAvl(i);
            }
            //System.out.println("Výška stromu: " + strom.root.height);
        stopTimer();
        System.out.println("\t INSERT -> OD 0 DO " + n + " prvkov: \t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        // SEARCH
        startTimer();
            for(int i = 0; i < n; i++){
                //strom.search(i);      // AJ S VÝPISOM
                strom.searchNode(i);    // BEZ VÝPISU, VRÁTI NODE alebo NULL
            }
            //System.out.println("Výška stromu: " + strom.root.height);
        stopTimer();
        System.out.println("\t SEARCH -> " +n+ " PRVKOV: \t\t\t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        // DELETE
        startTimer();
            for(int i = 0; i < n; i++){
                strom.delete(i);
            }
        stopTimer();
        System.out.println("\t DELETE -> " +n+ " PRVKOV: \t\t\t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        System.out.println(" ");

        //////////////////////////////////////////////////////////////////////////////////////////
        // TEST AVL 2 (od n do 0)
        startTimer();
            //testAvl_from_n(n);
            strom = new AvlTree();
            for(int i = n; i > 0; i--){
                strom.insertAvl(i);
            }
            //System.out.println("Výška stromu: " + strom.root.height);
        stopTimer();
        System.out.println("\t INSERT -> OD "+ n +" DO 0 prvkov: \t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        // SEARCH
        startTimer();
            for(int i = n; i > 0; i--){
                //strom.search(i);      // AJ S VÝPISOM
                strom.searchNode(i);    // BEZ VÝPISU, VRÁTI NODE alebo NULL
            }
            //System.out.println("Výška stromu: " + strom.root.height);
        stopTimer();
        System.out.println("\t SEARCH -> " +n+ " PRVKOV: \t\t\t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        // DELETE
        startTimer();
            for(int i = 0; i < n; i++){
                strom.delete(i);
            }
        stopTimer();
        System.out.println("\t DELETE -> " +n+ " PRVKOV: \t\t\t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        System.out.println(" ");

        //////////////////////////////////////////////////////////////////////////////////////////
        // TEST AVL 3 (random number n-inserts)
        // OSOBITNE BEZ STOPIEK SI VYGENERUJEM NÁHODNÉ ČÍSLA NA TEST (v rozmedzi od 0 do N*1000)
        ArrayList <Integer> arrayListInt = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            arrayListInt.add(random.nextInt(n*1000));
        }
        startTimer();
            //testAvl_random(n, arrayListInt);
            strom = new AvlTree();
            for(int i = 0; i < n; i++){
                strom.insertAvl(arrayListInt.get(i));
            }
            //System.out.println("Výška stromu: " + strom.root.height);
        stopTimer();
        System.out.println("\t INSERT -> " +n+ " NÁHODNE VYGENEROVANÝCH PRVKOV: \t" + time + "ms  [" + (time/1000.0) + "sec]");
        // SEARCH
        startTimer();
        for(int i = 0; i < n; i++){
            //strom.search(random.nextInt(n));          // AJ S VÝPISOM
            //strom.searchNode(random.nextInt(n));      // BEZ VÝPISU, VRÁTI NODE alebo NULL
            //strom.search(arrayListInt.get(i));        // AJ S VÝPISOM
            strom.searchNode(arrayListInt.get(i));      // BEZ VÝPISU, VRÁTI NODE alebo NULL
        }
        //System.out.println("Výška stromu: " + strom.root.height);
        stopTimer();
        System.out.println("\t SEARCH -> " +n+ " NÁHODNE VYGENEROVANÝCH PRVKOV: \t" + time + "ms  [" + (time/1000.0) + "sec]");
        // DELETE
        startTimer();
            for(int i = 0; i < n; i++){
                //strom.delete(random.nextInt(n));        // GENERUJE NÁHODNÉ ČÍSLA
                strom.delete(arrayListInt.get(i));      // ZMAŽE CELY STROM (postupne všetky predgenerovane čísla, ktoré sa vložili)
            }
        stopTimer();
        System.out.println("\t DELETE -> " +n+ " NÁHODNE VYGENEROVANÝCH PRVKOV: \t" + time + "ms  [" + (time/1000.0) + "sec]");
        //System.out.println(" ");

        System.out.println("_____________________________________________________________________________");


    }
    //////////// AVL TREE END ////////////

    //////////// RED BLACK TREE ////////////
    public void rb_tree(int n){
        System.out.println("_____________________________________________________________________________");
        System.out.println("TESTOVACÍ PROGRAM - RED BLACK TREE");
        System.out.println();

        // n = 10000;
        RedBlackTree strom = null;

        //////////////////////////////////////////////////////////////////////////////////////////
        // TEST RED-BLACK 1 (od 0 do n)
        // INSERT
        startTimer();
        strom = new RedBlackTree();
        for(int i = 0; i < n; i++){
            strom.insert(i);
        }
        stopTimer();
        System.out.println("\t INSERT -> OD 0 DO " + n + " prvkov: \t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        // SEARCH
        startTimer();
        for(int i = 0; i < n; i++){
            //strom.search(i);      // AJ S VÝPISOM
            strom.searchNode(i);    // BEZ VÝPISU, VRÁTI NODE alebo NULL
        }
        stopTimer();
        System.out.println("\t SEARCH -> " +n+ " PRVKOV: \t\t\t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        // DELETE
        startTimer();
        for(int i = 0; i < n; i++){
            strom.deleteNode(i);
        }
        stopTimer();
        System.out.println("\t DELETE -> " +n+ " PRVKOV: \t\t\t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        System.out.println(" ");

        //////////////////////////////////////////////////////////////////////////////////////////
        // TEST RED-BLACK 2 (od n do 0)
        startTimer();
        strom = new RedBlackTree();
        for(int i = n; i > 0; i--){
            strom.insert(i);
        }
        stopTimer();
        System.out.println("\t INSERT -> OD "+ n +" DO 0 prvkov: \t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        // SEARCH
        startTimer();
        for(int i = n; i > 0; i--){
            //strom.search(i);      // AJ S VÝPISOM
            strom.searchNode(i);    // BEZ VÝPISU, VRÁTI NODE alebo NULL
        }
        //System.out.println("Výška stromu: " + strom.root.height);
        stopTimer();
        System.out.println("\t SEARCH -> " +n+ " PRVKOV: \t\t\t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        // DELETE
        startTimer();
        for(int i = 0; i < n; i++){
            strom.deleteNode(i);
        }
        stopTimer();
        System.out.println("\t DELETE -> " +n+ " PRVKOV: \t\t\t\t\t\t\t" + time + "ms  [" + (time/1000.0) + "sec]");
        System.out.println(" ");

        //////////////////////////////////////////////////////////////////////////////////////////
        // TEST RED-BLACK 3 (random number n-inserts)
        // OSOBITNE BEZ STOPIEK SI VYGENERUJEM NÁHODNÉ ČÍSLA NA TEST (v rozmedzi od 0 do N*1000)
        ArrayList <Integer> arrayListInt = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            arrayListInt.add(random.nextInt(n*1000));
        }
        startTimer();
        strom = new RedBlackTree();
        for(int i = 0; i < n; i++){
            strom.insert(arrayListInt.get(i));
        }
        stopTimer();
        System.out.println("\t INSERT -> " +n+ " NÁHODNE VYGENEROVANÝCH PRVKOV: \t" + time + "ms  [" + (time/1000.0) + "sec]");
        // SEARCH
        startTimer();
        for(int i = 0; i < n; i++){
            //strom.search(random.nextInt(n));          // AJ S VÝPISOM
            //strom.searchNode(random.nextInt(n));      // BEZ VÝPISU, VRÁTI NODE alebo NULL
            //strom.search(arrayListInt.get(i));        // AJ S VÝPISOM
            strom.searchNode(arrayListInt.get(i));      // BEZ VÝPISU, VRÁTI NODE alebo NULL
        }
        //System.out.println("Výška stromu: " + strom.root.height);
        stopTimer();
        System.out.println("\t SEARCH -> " +n+ " NÁHODNE VYGENEROVANÝCH PRVKOV: \t" + time + "ms  [" + (time/1000.0) + "sec]");
        // DELETE
        startTimer();
        for(int i = 0; i < n; i++){
            //strom.delete(random.nextInt(n));        // GENERUJE NÁHODNÉ ČÍSLA
            strom.deleteNode(arrayListInt.get(i));      // ZMAŽE CELY STROM (postupne všetky predgenerovane čísla, ktoré sa vložili)
        }
        stopTimer();
        System.out.println("\t DELETE -> " +n+ " NÁHODNE VYGENEROVANÝCH PRVKOV: \t" + time + "ms  [" + (time/1000.0) + "sec]");
        //System.out.println(" ");

        System.out.println("_____________________________________________________________________________");
    }
    //////////// RED BLACK TREE END ////////////

    //////////// HASH TABLE LINEAR ////////////
    public void hash_table_linear(int n){
        System.out.println("_____________________________________________________________________________");
        System.out.println("TESTOVACÍ PROGRAM - HASH TABLE LINEAR PROBING");

        HashTableLinearProbing hashTableLinear = new HashTableLinearProbing(16);
        // n = 10000;

        // TEST HASH TABLE LINEAR PROBING
        // OSOBITNE BEZ STOPIEK SI VYGENERUJEM n NÁHODNÝCH STRINGOV (aby to neovplyvnilo čas vkladania)
        ArrayList <String> arrayListString = new ArrayList<String>();
        for(int i = 0; i < n; i++){
            arrayListString.add(getRandomString(15));
        }

        startTimer();
            for(int i = 0; i < n; i++){
                hashTableLinear.insert(arrayListString.get(i));
            }
        stopTimer();
        System.out.println( n + " NÁHODNE VYGENEROVANÝCH STRINGOV.");
        System.out.println();
        System.out.println("\t INSERT " + n + " prvkov: " + time + "ms  [" + (time/1000.0) + "sec]");
        startTimer();
            for(int i = 0; i < n; i++){
                //hashTableLinear.search(arrayListString.get(i));     // toto vypíše aj ČI NAŠLO ČI NENAŠLO
                hashTableLinear.searchItem(arrayListString.get(i));   // toto len vráti true alebo false
            }
        stopTimer();
        System.out.println("\t SEARCH " + n + " prvkov: " + time + "ms  [" + (time/1000.0) + "sec]");
        startTimer();
            for(int i = 0; i < n; i++){
                hashTableLinear.delete(arrayListString.get(i));
            }
        stopTimer();
        System.out.println("\t DELETE " + n + " prvkov: " + time + "ms  [" + (time/1000.0) + "sec]");
        System.out.println("_____________________________________________________________________________");
        System.out.println(" ");
    }
    //////////// HASH TABLE LINEAR END ////////////

    //////////// HASH TABLE CHAINING ////////////
    public void hash_table_chaining(int n){
        System.out.println("_____________________________________________________________________________");
        System.out.println("TESTOVACÍ PROGRAM - HASH TABLE CHAINING");

        HashTableChaining hashTableChaining = new HashTableChaining(16);
        // n = 10000;

        // TEST HASH TABLE CHAINING
        // OSOBITNE BEZ STOPIEK SI VYGENERUJEM n NÁHODNÝCH STRINGOV (aby to neovplyvnilo čas vkladania)
        ArrayList <String> arrayListString = new ArrayList<String>();
        for(int i = 0; i < n; i++){
            arrayListString.add(getRandomString(15));
        }

        startTimer();
        for(int i = 0; i < n; i++){
            hashTableChaining.insert(arrayListString.get(i));
        }
        stopTimer();
        System.out.println( n + " NÁHODNE VYGENEROVANÝCH STRINGOV.");
        System.out.println();
        System.out.println("\t INSERT " + n + " prvkov: " + time + "ms  [" + (time/1000.0) + "sec]");
        startTimer();
        for(int i = 0; i < n; i++){
            //hashTableChaining.search(arrayListString.get(i));     // toto vypíše aj ČI NAŠLO ČI NENAŠLO
            hashTableChaining.searchItem(arrayListString.get(i));   // toto len vráti true alebo false
        }
        stopTimer();
        System.out.println("\t SEARCH " + n + " prvkov: " + time + "ms  [" + (time/1000.0) + "sec]");
        startTimer();
        for(int i = 0; i < n; i++){
            hashTableChaining.delete(arrayListString.get(i));
        }
        stopTimer();
        System.out.println("\t DELETE " + n + " prvkov: " + time + "ms  [" + (time/1000.0) + "sec]");
        System.out.println("_____________________________________________________________________________");
        System.out.println(" ");
    }
    //////////// HASH TABLE CHAINING END ////////////



    // GENERÁTOR NÁHODNÝCH STRINGov
    private String getRandomString(int n){
        // vyberie náhodny znak z AlphaNumericString
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                //+ "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generuje náhodne číslo
            // 0 - AlphaNumericString.length()
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // pridaj znak jedem po druhom
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

}

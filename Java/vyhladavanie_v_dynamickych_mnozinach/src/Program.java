import avl_tree.*;
import hash_table_chaining.*;
import hash_table_linear_probing.HashTableLinearProbing;
import red_black_tree.RedBlackTree;

import java.util.Random;

public class Program {



    // AVL STROM
    void avl_tree(){
        AvlTree strom = new AvlTree();

        if(false){ /////////
            strom.insertAvl(10);
            strom.insertAvl(2);
            strom.insertAvl(16);
            strom.insertAvl(1);
            strom.insertAvl(3);
            strom.insertAvl(12);
            strom.insertAvl(20);
            strom.insertAvl(0);
            strom.insertAvl(4);
            strom.insertAvl(11);
            strom.insertAvl(17);
            strom.insertAvl(22);
            strom.insertAvl(18);

            strom.delete(16);

            //strom.insertAvl(15);

            strom.delete(10);

        }

        if(false){
            strom.insertAvl(5);
            strom.insertAvl(2);
            strom.insertAvl(7);
            strom.insertAvl(1);
            strom.insertAvl(4);
            strom.insertAvl(3);
            strom.insertAvl(6);
            strom.insertAvl(9);
            strom.insertAvl(8);
            strom.insertAvl(10);
        }
        if(false){
            strom.insertAvl(6);
            strom.insertAvl(3);
            strom.insertAvl(11);
            strom.insertAvl(1);
            strom.insertAvl(4);
            strom.insertAvl(9);
            strom.insertAvl(14);
            strom.insertAvl(9);
            strom.insertAvl(0);
            strom.insertAvl(2);
            strom.insertAvl(5);
            strom.insertAvl(10);
            strom.insertAvl(12);
            strom.insertAvl(16);
            strom.insertAvl(13);

            strom.delete(6);
            strom.delete(10);
            strom.delete(14);
            strom.delete(1);
            strom.delete(3);
            strom.delete(11);
            strom.delete(4);
            strom.delete(0);
            strom.delete(2);
            strom.delete(5);
            strom.delete(12);
            strom.delete(16);
            strom.delete(13);
            strom.delete(9);
            strom.insertAvl(5);
            // VYMAZE CELY STROM
        }

        //strom.delete(11);

        if(false){
            strom.insertAvl(5);
            strom.insertAvl(4);
            strom.insertAvl(2);
            strom.insertAvl(1);
            strom.insertAvl(3);
            strom.insertAvl(7);
            strom.insertAvl(6);
            strom.insertAvl(9);
            strom.insertAvl(8);
            strom.insertAvl(10);
            strom.insertAvl(13);
            strom.insertAvl(15);
            strom.insertAvl(17);

            strom.delete(20);

            strom.insertAvl(5);
            strom.insertAvl(10);
            strom.insertAvl(20);

            strom.printAvl(strom.root);
        }
    }

    void hash_table_chaining(){
        System.out.println("HASHOVACIE TABULKY");


        //System.out.println("Random String: " + HashTableChaining.getRandomNames());
        HashTableChaining hashTableChaining = new HashTableChaining(16);

        //for(int i = 0; i < 1000000; i++){
        //    hashTableChaining.insert(HashTableChaining.getRandomString(15));
        //}
        hashTableChaining.insert("Adam Vrabel",5);
        hashTableChaining.insert("Fero Keres", 3);
        hashTableChaining.insert("Adam Vrabelo", 2);
        hashTableChaining.insert("Jako", 13);
        hashTableChaining.insert("Koko", 14);
        hashTableChaining.insert("Fiko", 15);
        hashTableChaining.insert("Loko", 16);
        hashTableChaining.insert("Rytmus", 16);     // PO TOMTO BY SA MALO ZVACSIT
        hashTableChaining.insert("Temeraf", 119);   // TU MA UZ VELKOST 32

        hashTableChaining.delete("Rytolo");
        hashTableChaining.delete("Loko");
        hashTableChaining.delete("Koko");
        hashTableChaining.delete("Jako");

        hashTableChaining.insert("Teto", 0);


    }

    void hash_table_linear(){

        HashTableLinearProbing hashTableLinear = new HashTableLinearProbing(10);

//        hashTableLinear.insert("CISLO 1", 51);
//        hashTableLinear.insert("CISLO 2", 1);
//        hashTableLinear.insert("CISLO 3", 21);
//        hashTableLinear.insert("CISLO 4", 101);
//
//
//        hashTableLinear.delete("CISLO 2", 1);
//
//        hashTableLinear.insert("NOVE", 41);




    }

    void rb_tree(){

        RedBlackTree strom = new RedBlackTree();

        for(int i = 0; i < 5; i++){
            strom.insert(i);
        }
        for(int i = 0; i < 5; i++){
            strom.deleteNode(i);
        }
        //strom.search(4);
        //strom.deleteNode(4);
        //strom.search(4);


        /*
        strom.insert(8);    // UKAZE PRIPAD 1
        strom.insert(5);
        strom.insert(18);
        strom.insert(15);   // UKAZE PRIPAD 3
        strom.insert(17);   // UKAZE PRIPAD 4A a hned 5A
         */

        /*
        strom.insert(8);    // UKAZE PRIPAD 1
        strom.insert(5);
        strom.insert(18);
        strom.insert(7);    // UKAZE PRIPAD 3
        strom.insert(6);    // UKAZE PRIPAD 4B a hned 5B
         */

        /*
        strom.insert(8);    // PRIPAD 1
        strom.insert(18);
        strom.insert(5);
        strom.insert(15);   // PRIPAD 3
        strom.insert(17);   // UKAZE PRIPAD 4A a hned 5A
        strom.insert(25);   // PRIPAD 3
        strom.insert(40);   // PRIPAD 5B
        strom.insert(80);   // PRIPAD 3 a hned 4B, 5B (s 25kou)
         */


        /*
        // DELETE RED-BLACK examples
        strom.insert(12);
        strom.insert(6);
        strom.insert(15);
        strom.insert(8);
        strom.insert(13);
        strom.insert(23);
        strom.deleteNode(6);    // MÁ LEN RIGHT CHILD  // 6. PRÍPAD
        */

        /*
        strom.insert(17);
        strom.insert(9);
        strom.insert(19);
        strom.insert(18);
        strom.insert(75);
        strom.deleteNode(9);    // NEMÁ DETI  // 5. 6. PRÍPAD
         */

        /*
        strom.insert(17);
        strom.insert(9);
        strom.insert(19);
        strom.insert(3);
        strom.insert(12);
        strom.insert(18);
        strom.insert(75);
        strom.deleteNode(75);   // VYMAZANY BOL RED, VVSETKO OK
         */
    }
}

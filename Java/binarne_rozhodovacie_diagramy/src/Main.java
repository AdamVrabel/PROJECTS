import BDD.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("DSA PROJEKT - 2");
        System.out.println(" ");

        BinaryDecisionDiagram diagram = new BinaryDecisionDiagram();

        String myFormula = "AB+AC+BC";
        String poradie = "ABCD";

        myFormula = "AB+AC";
        //myFormula = "AB+CD+EF+GH";
        //poradie = "ACEGBDFH";
        //poradie = "ABCDEFGH";
        diagram.BDD_create(myFormula, poradie);

        int maximalnyPocetNodeov = ((int) Math.pow(2, 8)) -1 + 2;   // 2^pocetNodeov - 1 + 2;
        System.out.println("FORMULA: [" + myFormula + "]    " + "MAXIMALNY POCET NODEOV: [" + maximalnyPocetNodeov + "]");
        System.out.println("POCET NODEOV: [" + (diagram.numberOfNodes+2) + "]        PORADIE V STROME: " + Arrays.toString(diagram.poradieSpracovania) );

        /*
        TESTS testClass = new TESTS();
        String vygenerovanaFormula = testClass.vygenerujFormulu(3);
        String zjednodusenaFromula = diagram.zjednodusFormulu(vygenerovanaFormula);

        System.out.println("VYGENEROVANA FORMULA = [ " + vygenerovanaFormula + " ]");
        System.out.println("ZJEDNODUSENA FORMULA = [ " + zjednodusenaFromula + " ]");
        */


        /*
        HashMap<String, Node> hashMap = new HashMap<>();

        Node node1 = new Node("AB+CD", "A");
        Node node2 = new Node("CD", "E");
        Node node3 = new Node("BC+AD+A", "A");

        hashMap.put("AB+CD", node1);
        hashMap.put("CD", node2);
        hashMap.put("BC+AD+A", node3);

        System.out.println("ADRESS node2: \t\t\t\t" + node2);
        Node tmpNode = hashMap.get("CD");
        System.out.println("ADRESS node2 FROM hashMap: \t" + tmpNode);
        //System.out.println("PREMENNA podla ktorej je spracovany node2: " + hashMap.get("CD").variable);
        */

        ///*
        String toSimpleFormula = "BA+AAAACBBB+!AA!A!A!AAA";
        System.out.println("BEFORE: "+toSimpleFormula);
        //myFormula = "AB+!C!A!F!D+A!CA+AABCACABBA";
        //myFormula = "!D!D!C!B!B!A";

        toSimpleFormula = diagram.zjednodusFormulu(toSimpleFormula);

        System.out.println("AFTER: "+toSimpleFormula);

        toSimpleFormula = diagram.dosadDoFormuly(toSimpleFormula,"B", "0");

        System.out.println("AFTER: "+toSimpleFormula);
        //*/



    }
}
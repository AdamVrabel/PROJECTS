package BDD;


import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

/**
 * DSA - PROJEKT 2
 *
 * @author Adam Vrabeľ
 */
public class TESTS {
    static Random rand = new Random();
    private static final DecimalFormat numberFORMATER = new DecimalFormat("0.00");
    private long startTime = 0;
    private double time = 0;
    private void startTimer(){
        startTime = System.currentTimeMillis();
    }
    private void stopTimer(){
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    private void printTimer(){
        System.out.println("### CAS TRVANIA TESTU: " + time + "ms  [" + (time/1000.0) + "sec] ###");
    }

    public static void main(String[] args) {
        System.out.println("DSA PROJEKT - 2");
        System.out.println(" ");

        //////////// TESTY ////////////
        TESTS program = new TESTS();

        program.test();
        program.test1();
        program.test2();

        // TEST ČASOVEJ NÁROČNOSTI
        //program.time_test_create();
        //program.time_test_use();


        ///////////////////////////////////////////
        //program.testTRY();

        // PRIESTOROVÁ NÁROČNOSŤ /////////////////////////////////////////////////////////////////
        long total = Runtime.getRuntime().totalMemory();
        long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("Celková pamat: " + total/1024/1024 + "MB");
        System.out.println("Pouzitá pamat: " + used/1024/1024 + "MB");
        //////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * <p>funkcia <b>test()</b> testuje <b>VÝSLEDKY PRE BDD_USE()</b></p>
     */
    void test(){
        System.out.println("--------------------------------------------------------------");
        System.out.println("TEST - SKONTROLUJE BDD_USE() PRE FORMULU AB+C");
        System.out.println();

        BinaryDecisionDiagram BDD = new BinaryDecisionDiagram();
        boolean findError = false;

        BDD.BDD_create("AB+C", "ABC");

        if(BDD.BDD_use("000") != '0'){
            System.out.println("ERROR, for A=0, B=0, C=0 result should be 0.");
            findError = true;
        }
        if(BDD.BDD_use("001") != '1'){
            System.out.println("ERROR, for A=0, B=0, C=1 result should be 1.");
            findError = true;
        }
        if(BDD.BDD_use("010") != '0'){
            System.out.println("ERROR, for A=0, B=1, C=0 result should be 0.");
            findError = true;
        }
        if(BDD.BDD_use("011") != '1'){
            System.out.println("ERROR, for A=0, B=1, C=1 result should be 1.");
            findError = true;
        }
        if(BDD.BDD_use("100") != '0'){
            System.out.println("ERROR, for A=1, B=0, C=0 result should be 0.");
            findError = true;
        }
        if(BDD.BDD_use("101") != '1'){
            System.out.println("ERROR, for A=1, B=0, C=1 result should be 1.");
            findError = true;
        }
        if(BDD.BDD_use("110") != '1'){
            System.out.println("ERROR, for A=1, B=1, C=0 result should be 1.");
            findError = true;
        }
        if(BDD.BDD_use("111") != '1'){
            System.out.println("ERROR, for A=1, B=1, C=1 result should be 1.");
            findError = true;
        }


        if(!findError)
            System.out.println("CELY TEST PREBEHOL VPORIADKU !");

        System.out.println("--------------------------------------------------------------");
    }

    /**
     * <p>funkcia <b>test1()</b> slúži na testovanie <b>ZÁKLADNEJ FUNKCIONALITY BDD</b></p>
     * <p>MÁM DOPREDU URČENÚ FORMULU a PORADIE (neoptimálne)</p>
     * <li>VYKONÁM BDD_create(formula, poradie)</li>
     * <li>VYKONÁM BDD_create_with_best_order(formula)</li>
     *
     * <p>POROVNÁM POČET NODEov V BDD VYTVORENÝCH neoptimálnym a optimálnym poradím.</p>
     */
    void test1(){
        System.out.println("--------------------------------------------------------------");
        System.out.println("TEST ZAKLADNEJ FUNKCIONALITY");
        System.out.println();

        int maximalnyPocetNodeov = ((int) Math.pow(2, 8)) -1 + 2;   // 2^8 - 1 + 2;
        String formula = "AB+CD+EF+GH";
        String poradie = "ACGBEFDH";
        String cislaNaDosadenie = "10010001";

//        formula = "A!AB+!BCD+!CE!AEF+GH!I!A+A";
//        poradie = "GHCDEABFI";
//        cislaNaDosadenie = "000100111";
//        cislaNaDosadenie = "111";
//        cislaNaDosadenie = "111111";

        BinaryDecisionDiagram nonOptimalDiagram = new BinaryDecisionDiagram();
        BinaryDecisionDiagram optimalDiagram = null;

        System.out.println("POVODNA FORMULA: " + formula);

        // NON OPTIMAL BDD
        nonOptimalDiagram.BDD_create(formula, poradie);
        System.out.println("NEOPTIMALNY BDD  [POCET NODEov: " + (nonOptimalDiagram.numberOfNodes+2) + "]  [PORADIE: " + String.valueOf(nonOptimalDiagram.poradieSpracovania) + "]");  // + 2 NODEy (NULOVÝ A JEDNOTKOVÝ)

        // REDUCTION RATE NON OPTIMAL BDD
        int pocetNodeovNonOptimal = nonOptimalDiagram.numberOfNodes + 2;    // + 0 a 1 NODE
        double reductionRate = 100 - ((double) pocetNodeovNonOptimal*100 / maximalnyPocetNodeov);
        System.out.println("NEOPTIMALNY BDD  [REDUCTION RATE: "+numberFORMATER.format(reductionRate)+" %]");    // numberFORMATER.format(reductionRate) ZFORMATUJE NA 2 DESATINE MIESTA


        // OPTIMAL BDD
        optimalDiagram = BinaryDecisionDiagram.BDD_create_with_best_order(formula);
        System.out.println("OPTIMALNY BDD    [POCET NODEov: " + (optimalDiagram.numberOfNodes+2) + "]  [PORADIE: " + String.valueOf(optimalDiagram.poradieSpracovania) + "]");  // + 2 NODEy (NULOVÝ A JEDNOTKOVÝ)

        // REDUCTION RATE OPTIMAL BDD
        int pocetNodeovOptimal = optimalDiagram.numberOfNodes + 2;  // + 0 a 1 NODE
        reductionRate = 100 - ((double) pocetNodeovOptimal*100 / maximalnyPocetNodeov);
        System.out.println("OPTIMALNY BDD    [REDUCTION RATE: "+numberFORMATER.format(reductionRate)+" %]");    // numberFORMATER.format(reductionRate) ZFORMATUJE NA 2 DESATINE MIESTA


        // NAHRUBO DOSADENIE DO FORMULY, DOSADENIE CEZ BDD_USE
        // NON OPTIMAL BDD
        char vysledokDOSADENIM = BinaryDecisionDiagram.nahruboDosadDoFormuly(formula, poradie, cislaNaDosadenie);
        char vysledokBDD_USE = nonOptimalDiagram.BDD_use(cislaNaDosadenie);
        System.out.println("NEOPTIMALNY BDD  [VYSLEDOK DOSADENIM: "+vysledokDOSADENIM+"]  [VYSLEDOK BDD_USE: "+vysledokBDD_USE+"]");

        // OPTIMAL BDD (BEST_BDD)
        String poradieOptimal = String.valueOf(optimalDiagram.poradieSpracovania);  // ZÍSKAM PORADIE OPTIMALNEHO BDD
        vysledokDOSADENIM = BinaryDecisionDiagram.nahruboDosadDoFormuly(formula, poradieOptimal, cislaNaDosadenie);
        vysledokBDD_USE = optimalDiagram.BDD_use(cislaNaDosadenie);
        System.out.println("OPTIMALNY BDD    [VYSLEDOK DOSADENIM: "+vysledokDOSADENIM+"]  [VYSLEDOK BDD_USE: "+vysledokBDD_USE+"]");


        System.out.println("--------------------------------------------------------------");
        System.out.println();

    }

    /**
     * <p>funkcia <b>test2()</b> vytvorí <b>100 BDD STROMOV</b></p>
     * <p>stromy sú vytvárané pomocou <b>BDD_create_with_best_order</b></p>
     * <p>s určeným množstvom premenných <b>"pocetPremennych"</b></p>
     */
    void test2(){
        int pocetPremennych = 13;

        int maximalnyPocetNodeov = ((int) Math.pow(2, pocetPremennych)) -1 + 2;   // 2^pocetNodeov - 1 + 2;
        String formula;
        String cislaNaDosadenie = "1011010100111";   // FUNKCIA vygenerujPoradieCisel() MI GENERUJE TOLKO NAHODNYCH 0 A 1 AKO JE pocetPremennych
        char vysledokDOSADENIM;
        char vysledokBDD_USE;


        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("TEST PRE NAHODNE FORMULY S POČTOM " +
                pocetPremennych+" PREMENNYCH " +
                "- 100 krat VYTVORENIE BEST_BDD()"
        );
        System.out.println();


        double totalReductionRate = 0;

        startTimer();
        for(int i = 0; i < 100; i++){
            System.out.println("CISLO TESTU [" + (i+1) + "]");

            formula = vygenerujFormulu(pocetPremennych);
            System.out.println("VYGENEROVANA FORMULA: [" + formula + "]");

            BinaryDecisionDiagram diagram = BinaryDecisionDiagram.BDD_create_with_best_order(formula);
            System.out.println("MAXIMALNY POCET NODEOV: [" + maximalnyPocetNodeov + "]");
            System.out.println("POCET NODEOV: [" + (diagram.numberOfNodes+2) + "]        PORADIE V STROME: " + Arrays.toString(diagram.poradieSpracovania) );

            // REDUCTION RATE NON OPTIMAL BDD
            int pocetNodeov = diagram.numberOfNodes + 2;    // + 0 a 1 NODE
            double actualReductionRate = 100 - ((double) pocetNodeov*100 / maximalnyPocetNodeov);
            totalReductionRate += actualReductionRate;  // PRIPOCITAVAM AKTUALNU A NA KONCI JU VYDELÍM POČTOM OPAKOVANÍ FOR CYKLU
            System.out.println("AKTUALNY BDD  [REDUCTION RATE: "+numberFORMATER.format(actualReductionRate)+" %]");    // numberFORMATER.format(reductionRate) ZFORMATUJE NA 2 DESATINE MIESTA

            // DOSADZANIE CISEL V PORADI
            cislaNaDosadenie = vygenerujPoradieCisel(pocetPremennych);  // VRATI STRING Z NAHODNYM VYBEROM 0 A 1 S DLZKOU pocetPremennych
            String poradieBDD = String.valueOf(diagram.poradieSpracovania);  // ZÍSKAM PORADIE OPTIMALNEHO BDD

            vysledokDOSADENIM = BinaryDecisionDiagram.nahruboDosadDoFormuly(formula, poradieBDD, cislaNaDosadenie);
            vysledokBDD_USE = diagram.BDD_use(cislaNaDosadenie);
            System.out.println("DO BDD ["+formula+"] S PORADIM ["+poradieBDD+"] DOSADZAM ["+cislaNaDosadenie+"]");
            System.out.println("[VYSLEDOK DOSADENIM: "+vysledokDOSADENIM+"]  [VYSLEDOK BDD_USE: "+vysledokBDD_USE+"]");


            System.out.println();
        }
        stopTimer();
        printTimer();

        totalReductionRate = totalReductionRate / 100;
        System.out.println("### CELKOVÁ REDUCTION RATE: ["+numberFORMATER.format(totalReductionRate)+" %] ###");    // numberFORMATER.format(reductionRate) ZFORMATUJE NA 2 DESATINE MIESTA



        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }


    // ČASOVÁ NÁROČNOSŤ
    void time_test_create(){
        // TEST PRE FORMULU S 13, 14, 15, 16, 17 PREMENNÝMI,  1000krát BDD_create()

        // PREDGENEROVANIE FORMÚL A VYTVORENIE PORADÍ, ABY NEOVPLYVNILI MERANIE ČASU
        String formula13 = vygenerujFormulu(13);
        String poradie13 = "ABCDEFGHIJKLM";
        String formula14 = vygenerujFormulu(14);
        String poradie14 = "ABCDEFGHIJKLMN";
        String formula15 = vygenerujFormulu(15);
        String poradie15 = "ABCDEFGHIJKLMNO";
        String formula16 = vygenerujFormulu(16);
        String poradie16 = "ABCDEFGHIJKLMNOP";
        String formula17 = vygenerujFormulu(17);
        String poradie17 = "ABCDEFGHIJKLMNOPQ";


        startTimer();
        for(int i = 0; i < 1000; i++){
            BinaryDecisionDiagram diagram = new BinaryDecisionDiagram();
            diagram.BDD_create(formula13, poradie13);
        }
        stopTimer();
        System.out.print("13 premenných  ");
        printTimer();

        startTimer();
        for(int i = 0; i < 1000; i++){
            BinaryDecisionDiagram diagram = new BinaryDecisionDiagram();
            diagram.BDD_create(formula14, poradie14);
        }
        stopTimer();
        System.out.print("14 premenných  ");
        printTimer();

        startTimer();
        for(int i = 0; i < 1000; i++){
            BinaryDecisionDiagram diagram = new BinaryDecisionDiagram();
            diagram.BDD_create(formula15, poradie15);
        }
        stopTimer();
        System.out.print("15 premenných  ");
        printTimer();

        startTimer();
        for(int i = 0; i < 1000; i++){
            BinaryDecisionDiagram diagram = new BinaryDecisionDiagram();
            diagram.BDD_create(formula16, poradie16);
        }
        stopTimer();
        System.out.print("16 premenných  ");
        printTimer();

        startTimer();
        for(int i = 0; i < 1000; i++){
            BinaryDecisionDiagram diagram = new BinaryDecisionDiagram();
            diagram.BDD_create(formula17, poradie17);
        }
        stopTimer();
        System.out.print("17 premenných  ");
        printTimer();

    }

    void time_test_use(){
        // TEST PRE FORMULU S 13, 14, 15, 16, 17 PREMENNÝMI,  1000krát BDD_use()

        // PREDGENEROVANIE FORMÚL A VYTVORENIE PORADÍ, ABY NEOVPLYVNILI MERANIE ČASU
        String formula13 = vygenerujFormulu(13);
        String poradie13 = "ABCDEFGHIJKLM";
        String formula14 = vygenerujFormulu(14);
        String poradie14 = "ABCDEFGHIJKLMN";
        String formula15 = vygenerujFormulu(15);
        String poradie15 = "ABCDEFGHIJKLMNO";
        String formula16 = vygenerujFormulu(16);
        String poradie16 = "ABCDEFGHIJKLMNOP";
        String formula17 = vygenerujFormulu(17);
        String poradie17 = "ABCDEFGHIJKLMNOPQ";

        String cislaNaDosadenie = "10110100101000101";


        BinaryDecisionDiagram diagram;

        diagram = new BinaryDecisionDiagram();
        diagram.BDD_create(formula13, poradie13);
        startTimer();
        for(int i = 0; i < 1000; i++){
            diagram.BDD_use(cislaNaDosadenie);
        }
        stopTimer();
        System.out.print("13 premenných  ");
        printTimer();

        diagram = new BinaryDecisionDiagram();
        diagram.BDD_create(formula14, poradie14);
        startTimer();
        for(int i = 0; i < 1000; i++){
            diagram.BDD_use(cislaNaDosadenie);
        }
        stopTimer();
        System.out.print("14 premenných  ");
        printTimer();

        diagram = new BinaryDecisionDiagram();
        diagram.BDD_create(formula15, poradie15);
        startTimer();
        for(int i = 0; i < 1000; i++){
            diagram.BDD_use(cislaNaDosadenie);
        }
        stopTimer();
        System.out.print("15 premenných  ");
        printTimer();

        diagram = new BinaryDecisionDiagram();
        diagram.BDD_create(formula16, poradie16);
        startTimer();
        for(int i = 0; i < 1000; i++){
            diagram.BDD_use(cislaNaDosadenie);
        }
        stopTimer();
        System.out.print("16 premenných  ");
        printTimer();

        diagram = new BinaryDecisionDiagram();
        diagram.BDD_create(formula17, poradie17);
        startTimer();
        for(int i = 0; i < 1000; i++){
            diagram.BDD_use(cislaNaDosadenie);
        }
        stopTimer();
        System.out.print("17 premenných  ");
        printTimer();
    }



    void testTRY(){
        BinaryDecisionDiagram diagram = new BinaryDecisionDiagram();

        String formula = "AB+C";
        String poradie = "ABC";
        String naDosadenie = "000";

        formula = "A!AB+!BCD+!CE!AEF+GH!I!A";
        poradie = "ADCBEFGHI";
        naDosadenie = "000100111";

        formula = vygenerujFormulu(4);
        poradie = "ABCD";
        naDosadenie = "1011";

        diagram.BDD_create(formula, poradie);
    }


    // VYGENERUJE CELU FORMULU
    public String vygenerujFormulu(int pocetPremennych){
        String formula;

        // POCET SUCTOV V ROZMEDZI  5 - 10
        int pocetSuctov = rand.nextInt(5, 11);   // ORIGIN-VRÁTANE, BOUND-BEZ
        String[] newFormula = new String[pocetSuctov];

        int pocetNasobeni;  // POCET V NASOBENI NAHODNY PRE KAZDY SUB-STRING V ROZMEDZÍ (pocetZnakov až 2*pocetZnakov)

        // FORMULA BUDE MAŤ pocetSuctov CASTI ODDELENYCH CEZ +
        for(int i = 0; i < pocetSuctov; i++){

            // NÁHODNÉ GENEROVANIE pocetNasobeni ZNAKOV V ROZMEDZÍ (pocetPremennych/2 až 2*pocetPremennych)
            pocetNasobeni = rand.nextInt(pocetPremennych/2, 2*pocetPremennych);   // ORIGIN-VRÁTANE, BOUND-BEZ
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < pocetNasobeni; x++) {

                // NÁHODNE VYGENEROVANIE NEGÁCIE
                int chanceToNegation = rand.nextInt(4);
                if(chanceToNegation == 2){  // NAHODNE DÁ ALEBO NEDÁ NEGÁCIU PRED VYGENEROVANÉ PÍSMENO
                    sb.append("!");
                }

                char randomCharFromRange = generateRandomLetterInRange( pocetPremennych );  // VYGENERUJE PISMENO OD A(65) DO 65+pocetPremennych-1
                sb.append(randomCharFromRange); // PRIDANIE VYGENEROVANEHO PISMENA Z ROZSAHU pocetPremennych

            }

            newFormula[i] = sb.toString();
        }


        // SPOJÍ NÁHODNE VYGENEROVANÉ PODSTRINGY NAPR: AB!CD!AEF CEZ + DO JEDNEJ CELEJ FORMULY
        StringJoiner sj = new StringJoiner("+");
        for(String s : newFormula){
            sj.add(s);
        }
        formula =  sj.toString();

        // KONTROLA CONTAINS
        // KTORY POZRIE ČI CELA FORMULA OBSAHUJE VSETKYCH X PREMENNYCH
        // AK NEOBSAHUJE, PRIDÁ JU NA KONIEC
        // VO FOR PREJDE VSETKÝCH X PÍSMEN A KTORÉ NIESU V CELEJ FORMULE, TIE PRIDÁ NAKONIEC
        for(int i = 0; i < pocetPremennych; i++){
            char pismeno = (char)(65+i);
            if(!formula.contains(String.valueOf(pismeno))){
                //System.out.println("FORMULA NEOBSAHUJE: " + pismeno);
                formula = formula + pismeno;
            }
        }


        //System.out.println("VYGENEROVANA FORMULA = [ " + formula + " ]");
        return formula;
    }

    // VYGENERUJE PISMENO OD A(65) DO 65+pocetPremennych-1
    char generateRandomLetterInRange(int pocetPremennych) {
        int max = 65+pocetPremennych;
        int range = max - 65;
        int asciiValue = rand.nextInt(range) + 65;
        return (char) asciiValue;
    }

    // VRATI STRING Z 0 A 1 S DLZKOU pocetPremennych
    String vygenerujPoradieCisel(int pocetPremennych){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < pocetPremennych; i++){
            // NÁHODNE VYGENEROVANIE 0 alebo 1
            int randomChance = rand.nextInt(2);
            if(randomChance == 0){  // NAHODNE DÁ ALEBO NEDÁ NEGÁCIU PRED VYGENEROVANÉ PÍSMENO
                sb.append("0");
            }
            if(randomChance == 1){  // NAHODNE DÁ ALEBO NEDÁ NEGÁCIU PRED VYGENEROVANÉ PÍSMENO
                sb.append("1");
            }
        }

        return sb.toString();
    }
}

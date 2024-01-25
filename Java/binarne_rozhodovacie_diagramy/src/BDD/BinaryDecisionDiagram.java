package BDD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DSA - PROJEKT 2
 *
 * @author Adam Vrabeľ
 */
public class BinaryDecisionDiagram {
    public int numberOfNodes;                   // POČET NODEOV V CELOM STROME (BEZ zeroNode A oneNode)
    public char[] poradieSpracovania = null;  // PORADIE AKO BUDEM POSTUPNE SPRÁCOVAVAŤ PREMENNÉ
    public Node root;              // KOREŇ STROMU


    // V CELOM STROME JE IBA JEDEN NULOVÝ A IBA JEDEN JEDNOTKOVÝ NODE
    Node zeroNode;         // NULOVÝ NODE
    Node oneNode;          // JEDNOTKOVÝ NODE


    // HASH-MAPA KDE SA UKLADAJU VYTVORENÉ NODE-y
    // PRI KAZDOM VYTVÁRANÍ SA SKONTROLUJE ČI NEEXISTUJE ROVNAKÝ NODE (S ROVNAKOU FORMULOU)
    //      AK EXISTUJE TAK SA NAPOJÍ TEN UŽ VYTVORENÝ
    //      AK NEEXISTUJE TAK AŽ VTEDY SA VYTVORÍ
    public HashMap<String, Node> hashMap = new HashMap<>();




    public BinaryDecisionDiagram(){

        root = null;
        numberOfNodes = 0;
        zeroNode = new Node("0");
        oneNode = new Node("1");
    }


    // REKURZÍVNE VYTVÁRANIE NODE
    public Node BDD_insertNode(String formula, int indexZanorenia){

        // REKURZIA NA VOLANIE VYTVÁRANIA BDD_insertNode
        // indexZanorenia začína na 0 a každým ďalším zanorením +1

        // AK JE FORMULA == 0
        // AK JE FORMULA == 1
        // AK JE FORMULA UŽ V SLOVNÍKU
        //  VRÁTI EXISTUJÚCU, RESPEKTIVE zeroNode, oneNode

        // spracovavanePismeno = písmeno podľa indexuZanorenia v poradí

        // VYTVORÍ NOVÝ NODE
        //      DO DOCASNYCH STRINGOV FORMUL DOSADÍ 0 a 1 (actualFormulawZero, actualFormulawOne)
        //      INKREMENTUJE indexZanorenia
        //      a volá rekurzívne BDD_insertNode s dosadenými formulami a o 1 väčším indexomZanorenia

        if(formula.equals("0")){
            return zeroNode;
        }
        if(formula.equals("1")){
            return oneNode;
        }

        String spracovavanePismeno = String.valueOf(poradieSpracovania[indexZanorenia]);    // AKTUALNE SPRACOVAVANE PÍSMENO
        Node newNode;   // referencia na novo vytvorený node ak sa vytvorí

        // POKIAL FORMULA NEOBSAHUJE spracovanePismeno VO FORMULI
        while(!formula.contains(spracovavanePismeno)){
            indexZanorenia++;   // TAK MOZEM POSUNÚŤ indexZanorenia o 1 (pretože vo formuli po dosadeni 0 alebo 1 nič neovplyvní)
            spracovavanePismeno = String.valueOf(poradieSpracovania[indexZanorenia]);   // A NASTAVÍM NOVE SPRACOVÁVANÉ PÍSMENO
            // OD TOHTO MOMENTU POKRAČUJEM S POSUNUTÝM PÍSMENOM NA SPRACOVANIE
        }

        // KONTROLA ČI SA NODE S DANOU FORMULOU UŽ NENACHÁDZA V HASHMAPE
        if(hashMap.get(formula) == null){   // AK SA NENACHÁDZA NODE S DANOU FORMULOU V HASH MAPE
            newNode = new Node(formula, spracovavanePismeno);   // VYTVORÍ NOVÝ NODE
            numberOfNodes++;
            hashMap.put(formula, newNode);  // AK SA VYTVORÍ NOVÝ NODE, PRIDÁ SA DO HASH-MAPY
        }
        else{   // AK SA NACHÁDZA NODE S DANOU FORMULOU V HASH MAPE
            Node controlNodeInHashMap = hashMap.get(formula);
            if( controlNodeInHashMap.variable.equals(spracovavanePismeno) ){    // AK JE AKTUALNE SPRACOVANE PISMENO ROVNAKE S TÝM SPRACOVÁVANÝM V NODE
                return controlNodeInHashMap;    // TAK NAPOJ NODE Z HASHMAPY
            }
            else{   // AK NIEJE AKTUALNE SPRACOVANE PISMENO ROVNAKE S TÝM SPRACOVÁVANÝM V NODE
                newNode = new Node(formula, spracovavanePismeno);   // TAK AJ TAK VYTVOR NODE
                numberOfNodes++;
                hashMap.put(formula, newNode);  // AK SA VYTVORÍ NOVÝ NODE, PRIDÁ SA DO HASH-MAPY
            }
        }


        // DOSADENIA CISEL 0 A 1 DO FORMULE
        //String actualFormulawZero = dosadDoFormuly(formula, "A", "0");
        String actualFormulawZero = dosadDoFormuly(formula, spracovavanePismeno, "0");
        //System.out.println(" Formula s 0: " + actualFormulawZero);

        //String actualFormulawOne =  dosadDoFormuly(formula, "A", "1");
        String actualFormulawOne =  dosadDoFormuly(formula, spracovavanePismeno, "1");
        //System.out.println(" Formula s 1: " + actualFormulawOne);


        indexZanorenia++;   // IDEM O UROVEN NIŽŠIE

        // REKURZIA
        newNode.left = BDD_insertNode(actualFormulawZero, indexZanorenia);
        newNode.right = BDD_insertNode(actualFormulawOne, indexZanorenia);

        return newNode;
    }


    // FORMULA MUSÍ POZOSTÁVAŤ IBA Z PÍSMEN, +, a !
    public void BDD_create(String formula, String poradie){
        // formula = je počiatočná formula (AB+AC+BD)
        // poradie = je poradie ako postupne spracúvam formulu (ktoré pismenka postupne dosádzam)
        // indexZanorenia je index v poli poradieSpracovania (ktoré písmeno aktuálne spracovávam)

        // VYTVORÍ POLE SPRACOVANIA ZNAKOV
        // ZJEDNODUSI VSTUPNÚ FORMULU (ZORADÍ ABECEDNE, VYMAŽE DUPLICITNE PREMENNÉ V POD-STRINGOCH ROZDELENÝCH CEZ "+")
        //
        // AK JE FORMULA INÁ AKO "0" ALEBO "1"
        // A ROOT JE NULL
        //  VOLÁ REKURZÍVNE VYTVÁRANIE BDD_insertNode

        poradieSpracovania = poradie.toCharArray(); // nastavím poradie spracovania premenných (A,B,C...)

        //System.out.println("POVODNA FORMULA: " + formula);

        formula = zjednodusFormulu(formula);    // ZJEDNODUŠÍ FORMULU (ZORADI ABECEDNE, DUPLICITNÉ VYMAŽE)
        //System.out.println("ZJEDNODUSENA FORMULA: " + formula);


        // ZAČÍNAM V ROOTE
        if(root == null){
            // AK FORMULA JE 0 - napoj zeroNode
            if(formula.equals("0")){
                root = zeroNode;
                return;
            }
            // AK FORMULA JE 1 - napoj oneNode
            if(formula.equals("1")){
                root = oneNode;
                return;
            }
            // AK JE FORMULA INÁ AKO "0" ALEBO "1"

            // ZAČIATOK REKURZÍVNEHO VYTVARANIA
            //Node newNode = new Node(formula, spracovavanePismeno);
            root = BDD_insertNode(formula, 0);
        }
        else{
            System.out.println("ROOT NIEJE NULL");
        }
    }


    // DOSADÍ DO VYTVORENÉHO BDD DANÉ ČÍSLA V PORADÍ AKÉ MÁ BDD
    //  VRÁTI CHAR      '1' = ak dojde na oneNode   '0' = ak dojde na zeroNode     'E' = ak error
    public char BDD_use(String poradieCisel){

        if(root == null){
            System.out.println("BDD_use --> root je null");
            return 'E'; // ERROR BDD_use
        }

        int[] arrayPoradie = new int[poradieCisel.length()];
        // PREVOD ZNAKOV V STRINGU NA i-tom INDEXE NA CISLO
        for(int i = 0; i < poradieCisel.length(); i++){
            char readedChar = poradieCisel.charAt(i);
            int readedInt = Character.getNumericValue(readedChar);
            arrayPoradie[i] = readedInt;
        }
        //System.out.println("PORADIE V BDD: " + Arrays.toString(poradieSpracovania));
        //System.out.println("BDD USE --> NACITANE PORADIE NA DOSADZANIE: " + Arrays.toString(arrayPoradie));
        //System.out.println();

        Node actual = root; // VYTVORENIE DOCASNEHO UKAZOVATELA NA NODE
        int actualNumber;  // CISLO KTORE SA PRAVE DOSADZA

        String spracovavanePISMENO;

        // DOSADZA MAXIMALNE TOLKO CISEL AKO MÁM DLHY STRING
        for(int i = 0; i < poradieCisel.length(); i++){
            actualNumber = arrayPoradie[i];
            //System.out.println(i + ". DOSADENE: " + actualNumber);

            // KONTROLA CI SA PISMENO (z poradia) NACHADZA V AKTUALNOM STRINGU
            // AK SA NENACHADZA MOZEME IST DOSADZAT DALSIE CISLO ZA DASIE PISMENO V PORADÍ
            spracovavanePISMENO = String.valueOf(poradieSpracovania[i]);
            if(actual.formula.contains(spracovavanePISMENO)){
                if(actualNumber == 0){  // IDEM LEFT
                    actual = actual.left;
                }
                if(actualNumber == 1){  // IDEM RIGHT
                    actual = actual.right;
                }
            }
            if(actual.formula.equals("1")){ // VYSLEDOK JE 1
                //System.out.println("VO FORMULI: " + root.formula + " PO DOSADENI POSTUPNE: " + poradieCisel);
                //System.out.println("VYSLEDOK JE 1");
                return '1';
            }
            if(actual.formula.equals("0")){ // VYSLEDOK JE 0
                //System.out.println("VO FORMULI: " + root.formula + " PO DOSADENI POSTUPNE: " + poradieCisel);
                //System.out.println("VYSLEDOK JE 0");
                return '0';
            }

        }   // KONIEC FOR CYKLU

        //System.out.println("BDD_use --> zadal si málo znakov, nedošlo vo formuli až k výsledku");
        return 'E'; // ERROR BDD_use
    }


    // NÁJDE NAJLEPŠIE PORADIE PRE ZADANÚ FORMULU A VRÁTI BDD, ktorý má najlepšie poradie (najmenej nodeov)
    public static BinaryDecisionDiagram BDD_create_with_best_order(String formula){

        // SPOČÍTANIE KOĽKO MÁM PÍSMENOK

        //formula = "AB+!ABC+D!F";
        String makeOrder = formula;

        // VYMAZE VSETKY ZNAKY OKREM OD A-Z
        makeOrder = formula.replaceAll("[^A-Z]+", "");

        // ZORADÍ STRING ABECEDNE
        char[] tempArray = makeOrder.toCharArray();
        Arrays.sort(tempArray);
        makeOrder = new String(tempArray);

        // VYMAZE DUPLICITNÉ PISMENA
        Pattern patternDoubleChar = Pattern.compile("([A-Z])\\1+");     // NÁJDE OD A-Z ktoré idú 2 a VIAC krát zasebou
        Matcher matcherDoubleChar = patternDoubleChar.matcher(makeOrder);
        if (matcherDoubleChar.find()) {
            makeOrder = matcherDoubleChar.replaceAll("$1");   // NAHRADI VIACERO ROVNAKÝCH ZNAKOV JEDNÝM
        }

        //System.out.println("VO FORMULI: " + formula + " SA NACHADZAJU TIETO PREMENNE: " + makeOrder);


        // VYGENEROVANIE PORADÍ

        // VO FOR CYKLE BUDEM POSUVAŤ ZNAKY O 1  = "ABCD" , "BCDA" , "CDAB" , "DABC"
        // A VYTVÁRAŤ BDD S DANÝM PORADÍM A KONTROLOVAŤ KTORÝ MÁ NAJMENEJ NODEOV

        // makeOrder je string s abecedne zoradenými písmenami z formuly
        String posunutePoradie = makeOrder;
        BinaryDecisionDiagram bestBDD = null;      // TO BUDE TEN NODE KTORÝ BUDE MAŤ NAJMENEJ NODEOV
        BinaryDecisionDiagram actualBDD = null;    // aktualny BDD

        for(int i = 0; i < makeOrder.length(); i++){ // VYKONÁ TOLKO FOR CYKLOV KOLKO MÁ STRING PÍSMEN
            actualBDD = new BinaryDecisionDiagram();
            if(i == 0){
                //System.out.println("POUŽITÉ PORADIE V BDD: " + posunutePoradie);    // PRE VYPISANIE AJ TOHO PRVEHO (NEPOSUNUTÉHO ESTE)
                actualBDD.BDD_create(formula, posunutePoradie);
                bestBDD = actualBDD;    // V PRVOM PRÍPADE LEN PRIDÁM
                continue;
            }
            posunutePoradie = posunutePoradie.substring(1) + posunutePoradie.charAt(0);
            //System.out.println("POUŽITÉ PORADIE V BDD: " + posunutePoradie);
            actualBDD.BDD_create(formula, posunutePoradie);
            if(actualBDD.numberOfNodes < bestBDD.numberOfNodes){    // AK MÁ AKTUALNY BDD S POSUNUTÝM PORADÍM MENEJ NODEOV
                bestBDD = actualBDD;                                // TAK PRIRADÍM AKTUÁLNY AKO BESTBDD
            }
        }

        /*
        System.out.println(
                "# BEST BDD" +
                " FORMULA: " + formula +
                " NAJLEPSIE PORADIE: " + Arrays.toString(bestBDD.poradieSpracovania) +
                " POCET NODEOV: " + bestBDD.numberOfNodes
                );
         */

        return bestBDD;
    }


    /////////////////////////////////////// STRING FUNCTIONS ///////////////////////////////////////
    // ZORADÍ AKÝKOĽVEK STRING ABECEDNE PO ZNAKOCH
    public String sortStringAZ(String inputString){
        char[] tempArray = inputString.toCharArray();
        Arrays.sort(tempArray);

        return new String(tempArray);   // VRÁTI NOVÝ ZORADENÝ STRING OD A-Z
    }

    // ZJEDNODUSI CELU FORMULU, ZORADÍ ABECEDNE, VYMAŽE DUPLICITNE
    public String zjednodusFormulu(String formula){


        // ROZDELÍ CEZ DELIMITER + NA POD-STRINGY

        // ITERUJEM CEZ POD-STRINGY (medzi +)
        //      ak su !, najdem vymazem ich a ulozim do tmpNegations (pole stringov "!A" "!B"...)
        //      ZORADI ABECENDE V POD-STINGU
        //      VYMAZE DUPLICITNE
        //      DOSADI NA ABECEDNE MIESTO NEGÁCIE Z tmpNegations    (NEGACIE SU PRED napr !AA!BB...)

        String simpleFormula = null;
        //System.out.println("Formula on start: " + formula);     // AB + BA + !AAB!B + BABA

        // ROZDELÍ formulu CEZ + na POD-STRINGY
        Pattern pattern = Pattern.compile("\\+");   // čo je delimiter, v mojom prípade +
        String[] split = pattern.split(formula);
        //System.out.println("split.length = " + split.length);
        //System.out.println();

        // ITERUJEM CEZ POD-STRINGY (medzi +)
        for(int i = 0; i < split.length; i++){
            //System.out.println("   PODSTRING NA ZACIATKU: " + split[i]);
            ArrayList<String> tmpNegations = new ArrayList<>();  // SEM SA ULOZIA NAJDENE NEGACIE V PODSTRINGU "!A", "!B" ...

            // 1 - VYMAZE VSETKY NEGACIE V PODSTINGOCH A ULOZI ICH DO tmpNegations //////////////////////////////////////////////////////
            //      pokial su !, najdem vymazem ich a ulozim do tmpNegations (pole stringov "!A" "!B"...)
            while(split[i].contains("!")){
                for(int x = 0; x < split[i].length(); x++){          // prechádzam po znakoch rozdelený reťazec
                    char actualChar = split[i].charAt(x);
                    if(actualChar ==  '!'){                                 // AK NASLO NEGACIU
                        String negacia = "!" + split[i].charAt(x + 1);
                        if(!tmpNegations.contains(negacia) ){             // AK TAKA NEGACIA ESTE NIEJE ULOZENA V tmpNegations
                            tmpNegations.add( negacia );                  // TAK DO ArrayList tmpNegations uloží "! a nasledujuce pismeno"
                            split[i] = split[i].replaceAll(negacia, "" );     // VYMAZE VŠETKY !A, !B ...
                            break;  // vyskoci z for cyklu a kontroluje či sa v celom splite nachadza ešte !
                        }
                    }
                }   // END prechádzam po znakoch rozdelený reťazec
            }// end if contains !
            //System.out.println("   1. VYMAZANE \"!\": " + split[i]);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // 2A - ZORADÍ ABECEDNE PODSTRING (z BAD urobí ABD) //////////////////
            split[i] = sortStringAZ(split[i]);
            //System.out.println("   2A. ZORADENE ABECEDNE: " + split[i]);
            //////////////////////////////////////////////////////////////////////

            // 2B AK SÚ V PODSTRINGU ROVNAKÉ PÍSMENÁ ZASEBOU TAK TO SPOJÍ DO JEDNÉHO    (z AAAB urobí AB) //////////////////////////
            Pattern patternDoubleChar = Pattern.compile("([A-Z])\\1+");     // NÁJDE OD A-Z ktoré idú 2 a VIAC krát zasebou
            Matcher matcherDoubleChar = patternDoubleChar.matcher(split[i]);
            if (matcherDoubleChar.find()) {
                //System.out.println("# NASLO PATTERN = 2 ALEBO VIAC KRÁT SA NACHADZA PISMENO !");
                //System.out.println("## BEFORE DELETE PATTERN: " + split[i]);
                String replaced = matcherDoubleChar.replaceAll("$1");   // NAHRADI VIACERO ROVNAKÝCH ZNAKOV JEDNÝM
                split[i] = replaced;
                //System.out.println("## AFTER DELETE PATTERN:  " + split[i]);
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // 3  POKIAL NIEJE tmpNegations PRÁZDNE, TAK PONAPAJAJ NEGACIE
            for(String actualNegation : tmpNegations){
                String znakNegovany = actualNegation.substring(1,2);    // Z NAPR "!A" VYBERIE "A"
                // EXTRAHUJE ZNAK Z POĽA tmpNegations      z "!A" vyberie "A"
                //Pattern patternNegations = Pattern.compile("A");
                Pattern patternNegations = Pattern.compile(znakNegovany);
                //Pattern patternNegations = Pattern.compile( split[i].charAt(x) );
                Matcher matcherNegations = patternNegations.matcher(split[i]);
                if(matcherNegations.find()){    // AK NAŠLO A, PRIDÁ PRED NEHO NEGÁCIU Z tmpNegations A -> !AA
                    StringBuffer sb = new StringBuffer();
                    //matcherNegations.appendReplacement(sb, "!A"+"A");
                    matcherNegations.appendReplacement(sb, (actualNegation+znakNegovany) );
                    matcherNegations.appendTail(sb);
                    split[i] = sb.toString();
                }
                else{   // AK NENAJDE V STRINGU A, B, ..., tak nevie ako pred to umiestnit tu negaciu ......
                    // NAJDE MIESTO ABECEDNE KAM MÁ VLOŽIT NEGOVANY PRVOK
                    // Znak (znakNegovany bez negacie) neexistuje v reťazci
                    String input = split[i];

                    int indexOfInsertion = -5;
                    // NÁJDE MIESTO PODLA ASCII KAM SA MÁ UMIESTNIT NEGOVANY PRVOK V STRINGU (ABECEDNE MIESTO PRE NEHO)
                    for (int z = 0; z < input.length(); z++) {
                        char charNegovany = znakNegovany.charAt(0); // KONVERTUJE STRING NA CHAR
                        // if aktualny znak je ! tak pozeraj na +1 index ale ak ano tak vrat miesto toho aktualneho
                        if(input.charAt(z) == '!' ){    // AK POZERAM NA ! TAK ZISŤUJ O JEDNO MIESTO ĎALEJ
                            if (input.charAt(z+1) > charNegovany) {
                                indexOfInsertion = z;   // NAJDE MIESTO PRE VLOZENIE NEGOVANEHO PRVKU
                                break;
                            }
                        }
                        else{   // AK ČÍTAM ZNAK KTORÝ NIEJE '!'
                            if (input.charAt(z) > charNegovany) {
                                indexOfInsertion = z;   // NAJDE MIESTO PRE VLOZENIE NEGOVANEHO PRVKU
                                break;
                            }
                        }

                    }
                    if(indexOfInsertion == -5){ // AK PREŠLO CELÝ STRING A NENAŠLO ABECEDNE MIESTO PRE VLOŽENIE
                        indexOfInsertion = input.length();  // MIESTO PRE VLOZENIE NEGOVANEHO PRVKU = NA KONIEC
                    }

                    String beforeInsertion = input.substring(0, indexOfInsertion);
                    String afterInsertion = input.substring(indexOfInsertion);
                    String output = beforeInsertion + actualNegation + afterInsertion;
                    //System.out.println(output);
                    split[i] = output;
                }
            }
            //System.out.println("   3. DOSADENE NEGACIE: " + split[i]);
            //////////////////////////////////////////////////////////


            //System.out.println();
        }// end iterovanie cez pod-stringy


        // SPOJI SPLITNUTY STRING A RETURNNE
        // ZOSTAVÍ STRING NASPAT CEZ DELIMITER "+"
        StringJoiner sj = new StringJoiner("+");
        for(String s : split){
            sj.add(s);
        }
        simpleFormula =  sj.toString();
        return simpleFormula;
    }

    // DOSADÍ DO FORMULY ZA DANÝ ZNAK DANÉ ČÍSLO, A ZJEDNODUŠÍ PODĽA BOOLEOVSKÝCH OPERÁCIÍ
    public String dosadDoFormuly(String formula, String dosadZa, String cisloNaDosadenie){

        // DOSADENIE ČÍSEL DO CELEHO STRINGU ZA SPRACOVAVANE PISMENO
        if(formula.contains("!"+dosadZa)){  // NEGACIA
            if(cisloNaDosadenie.equals("0")){
                formula = formula.replaceAll("!"+dosadZa, "1");
            }
            if(cisloNaDosadenie.equals("1")){
                formula = formula.replaceAll("!"+dosadZa, "0");
            }
        }
        if(formula.contains(dosadZa)){
            formula = formula.replaceAll(dosadZa, cisloNaDosadenie);
        }


        Pattern pattern = Pattern.compile("\\+");   // čo je delimiter, v mojom prípade +
        String[] split = pattern.split(formula);
        //System.out.println("split.length = " + split.length);

        // ITERUJEM CEZ POD-STRINGY (medzi +)
        for(int i = 0; i < split.length; i++){
            // AK JE TAM 0 tak CELY JE 0
            // AK JE TAM 1 TAK 1 ZMAZ
            if(split[i].contains("0")){
                split[i] = "0";
            }
            else if(split[i].contains("1")){
                if(!split[i].equals("1")){   // AK V POD-STRINGU JE VIAC AKO IBA 1
                    split[i] = split[i].replaceAll("1", "");    // VYMAZE VSETKY JEDNOTKY
                    // NAPR: ...+1+... >>> ...+1+...    ALEBO   ...+1AB+... >>> ...+AB+...
                }
            }

        }

        // ZOSTAVÍ STRING NASPAT CEZ DELIMITER "+"
        StringJoiner sj = new StringJoiner("+");
        for(String s : split){
            sj.add(s);
        }
        formula =  sj.toString();


        // HLADA V CELOM STRINGU
        // AK NAJDE 1+čokolvek, cele = 1
        if(formula.contains("1")){
            formula = "1";
            /*
            if(formula.contains("1+")){

            }
            if(formula.contains("+1+")){

            }
            if(formula.contains("+1")){

            }
            */

        }
        // AK NAJDE 0+ČOKOLVEK, vymaze 0
        else if(formula.contains("0")){
            formula = formula.replaceAll("\\+0\\+", "+");
            formula = formula.replaceAll("0\\+", "");
            formula = formula.replaceAll("\\+0", "");
            /*
            if(formula.contains("+0+")){
                formula = formula.replaceAll("\\+0\\+", "+");
            }
            if(formula.contains("0+")){
                formula = formula.replaceAll("0\\+", "");
            }
            if(formula.contains("+0")){
                formula = formula.replaceAll("\\+0", "");
            }
            */

        }



        return formula;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // DOSTANE FORMULU, PORADIE PREMENNYCH, PORADIE CISEL NA DOSADENIE
    // NAHRUBO DOSADÍ PODLA BOOLOVSKYCH PRAVIDIEL DO FORMULY A VRÁTI VÝSLEDOK
    public static char nahruboDosadDoFormuly(String formula, String poradie, String naDosadenie){
        BinaryDecisionDiagram tmp = new BinaryDecisionDiagram(); //iba pre pouzitie funkcie dosadDoFormuly()

        // DOSADÍ MI DO FORMULY CISLA "naDosadenie" V PORADIÍ "poradie"
        String vysledokNahruboDosadeny = formula;
        for(int i = 0; i < poradie.length(); i++){
            if(i == naDosadenie.length()){
               return 'E'; // DOSLI MI CISLA NA DOSADZANIE, A FORMULA ESTE NIEJE 1 ALEBO 0
            }

            String znakNaDosadenie = Character.toString(poradie.charAt(i));
            String cisloNaDosadenie = Character.toString(naDosadenie.charAt(i));
            vysledokNahruboDosadeny = tmp.dosadDoFormuly(vysledokNahruboDosadeny, znakNaDosadenie, cisloNaDosadenie);

            // PRIDAL SOM, NEVIEM CI TO JE OK, SKONTROLOVAT
            if(vysledokNahruboDosadeny.equals("1"))
                return '1';
            if(vysledokNahruboDosadeny.equals("0"))
                return '0';
        }

        if(vysledokNahruboDosadeny.equals("1")){
            //System.out.println("JE TO JEDNA");
            return '1';
        }
        if(vysledokNahruboDosadeny.equals("0")){
            //System.out.println("JE TO NULA");
            return '0';
        }

        return 'E';
    }

}

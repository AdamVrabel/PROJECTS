package hash_table_chaining;

// HASH TABULKA (CHAINING riesenie kolizii) (NAPÁJANIE DO ZOZNAMU)

import java.util.Random;

public class HashTableChaining {
    public int size;
    public int filled;
    public Item[] array;    // POLE ITEMOV

    private int numberOfColision = 0; // cisto pre mna, kolko kolizii

    public HashTableChaining(){
    }
    public HashTableChaining(int size){
        this.size = size;
        this.filled = 0;
        this.array = new Item[size];
    }

    // SIMPLE HASH FUNKCIA
    // KEY MODULO VELKOST_HASH_TABULKY
    private int hash(int key, int size){   // HASH FUNKCIA, ktorá nám z KEY urobí index na ulozenie prvku do HASHTABULKY
        return key % size;
    }

    private double fillFactor(){    // VRÁTI double FILLFACTOR (faktor naplnenia)   FILLED/SIZE
        return (double)filled / (double)size;
    }

    private void resizeHashTable(int newSize){
        // PRI ZVAČŠOVANÍ size*2
        // PRI ZMENŠOVANÍ size/2
        //HashTableChaining newHashTableChaining = new HashTableChaining(2*size);
        HashTableChaining newHashTableChaining = new HashTableChaining(newSize);
        numberOfColision = 0;
        Item actual = null;

        // CITANIE PRVKOV ZO STAREJ TABULKY A PRIDAVANIE DO NOVEJ (zvacsenej)
        for(int i = 0; i < this.array.length; i++){  // ITERUJEM CEZ CELE POLE
            if(array[i] != null){   // VOJDEM DO POLA IBA AK EXISTUJE PRVOK

                actual = array[i];
                newHashTableChaining.insert(actual.value);   // INSERTNI PRVOK DO NOVEJ HASHTABULKY
                //System.out.println("# (" + actual.value + ")  mapujem do novej HashTabulky");

                while( actual.next != null ){   // prechadzanie spajanym zoznamom
                    actual = actual.next;

                    newHashTableChaining.insert(actual.value);   // INSERTNI PRVOK DO NOVEJ HASHTABULKY
                    //newHashTableChaining.insert(actual.value, actual.key);   // INSERTNI PRVOK DO NOVEJ HASHTABULKY
                    //System.out.println("# (" + actual.value + ")  mapujem do novej HashTabulky");
                }
            }
        }

        // NASTAVENIE NOVEJ TABULKY NA AKTUALNU
        this.size = newHashTableChaining.size;
        this.filled = newHashTableChaining.filled;
        this.array = newHashTableChaining.array;
        this.numberOfColision = newHashTableChaining.numberOfColision;

        //System.out.println("RESIZING COMPLETE !");
    }


    public int getNumberFromString(String actualString){
        //System.out.println("Zadany string: " + actualString);
        int intFromString = actualString.hashCode();
        //System.out.println("Int zo stringu: " + intFromString);
        intFromString = Math.abs(intFromString);
        //System.out.println("Int po absolutnej hodnote: " + intFromString);
        //System.out.println("Po mod 10: " + intFromString%10);

        return intFromString;
    }

    public void insert(String stringToInsert, int key){
        //int key = getNumberFromString(stringToInsert);

        int index;
        index = hash(key, size);    // KEY UKAZUJE NA INDEX V POLI KAM MAME VLOZIT PRVOK

        if( array[index] == null ){   // AK V POLI na indexe KEY NIEJE ZIADNY PRVOK (nenastala kolizia)
            array[index] = new Item(stringToInsert, key);
        }
        else{   // KOLIZIA - CHAINING
            //System.out.println("# KOLIZIA #");
            numberOfColision++;

            Item actual = array[index];

            // AK PRVOK S ROVNAKÝMI DATAMI UŽ EXISTUJE, TAK SA NEPRIDÁ
            if( (actual.key == key) && (stringToInsert.equals(actual.value)) ){
                //System.out.println("Prvok " + dataToInsert + " uz je v tabulke, takze sa neprida !");
                return;
            }

            while( actual.next != null){    // posun na dalsi prvok s rovnakym indexom ktory nieje null
                actual = actual.next;
                // AK PRVOK S ROVNAKÝMI DATAMI UŽ EXISTUJE, TAK SA NEPRIDÁ
                if( (actual.key == key)  && (stringToInsert.equals(actual.value)) ){
                    //System.out.println("Prvok " + dataToInsert + " uz je v tabulke, takze sa neprida !");
                    return;
                }
            }
            actual.next = new Item(stringToInsert, key);  // pridanie prvku na prazdne miesto, s indexom hash(KEY)
            actual.next.before = actual;
        }

        filled++;

        if( fillFactor() > 0.5 ){ // AK JE NAPLNENA NA VIAC AKO POLOVICU JE POTREBNE ZVACSIT TABULKU
            resizeHashTable(2*size);
        }
    }

    public void insert(String stringToInsert){
        int key = getNumberFromString(stringToInsert);
        insert(stringToInsert, key);
    }


    public void search(String stringToSearch){
        int key = getNumberFromString(stringToSearch);

        Item item = searchItem(stringToSearch);

        if(item != null){   // PRVOK NAJDENY
            System.out.println("Hladany:\""+ stringToSearch + "\" KEY:[" + key + "]  Prvok sa našiel !  Najdeny: \"" + item.value + "\" KEY:[" + item.key + "]");
        }
        else{
            System.out.println("Hladany:\"" + stringToSearch + "\"  sa nenašiel !");
        }
    }

    // FUNKCIA NAJDE A VRATI REFERENCIU NA ITEM
    public Item searchItem(String stringToSearch){
        int key = getNumberFromString(stringToSearch);

        int index = hash(key, size);    // index UKAZUJE NA INDEX V POLI KDE BY MAL BYT ULOZENY udaj
        Item actual = null;

        if( filled != 0 ){  // ak má hash tabulka aspon 1 prvok v sebe uložený
            if( array[index] != null ){ // ak na danom indexe je zapisany udaj
                // HLADAJ PRVOK
                actual = array[index];
                if(actual.key == key){
                    //System.out.println("Hladany:"+ stringToSearch + " key:[" + key + "]  Prvok sa našiel !  Najdeny:" + actual.value + " key:[" + actual.key + "]");
                    return actual;
                }
                while( actual.next != null ){
                    actual = actual.next;
                    if(actual.key == key){
                        //System.out.println("Hladany:"+ stringToSearch + " key:[" + key + "]  Prvok sa našiel !  Najdeny:" + actual.value + " key:[" + actual.key + "] ");
                        return actual;
                    }
                }
            }
        }
        //System.out.println("Hladany:" + stringToSearch + "  sa nenašiel !");
        return null;    // AK NENAJDE PRVOK, VRATI NULL
    }

    public void delete(String stringToDelete){
        int key = getNumberFromString(stringToDelete);

        int index = hash(key, size);

        Item toDelete = searchItem(stringToDelete);
        if( toDelete != null){  // prvok na vymazanie EXISTUJE v tabulke
            //System.out.println("Vymazavam: " + toDelete.value + " key:["+ toDelete.key +"]");
            // VYMAZAVANIE
            if( (toDelete.next == null) && (toDelete.before == null) ){  // VYMAZAVAM PRVY PRVOK V POLI (bez kolizii)
                array[index] = null;
            }
            else if( (toDelete.next != null) && (toDelete.before == null) ){   // VYMAZAVAM PRVY PRVOK V POLI (s aspoň 1 koliziou)
                array[index] = toDelete.next;
                toDelete.next.before = null;    // vymazem referenciu na vymazany prvok
            }
            else if( (toDelete.next != null) && (toDelete.before != null) ){  // VYMAZAVAM V STREDE LINKEDLISTU (mam aj before aj next)
                toDelete.before.next = toDelete.next;
                toDelete.next.before = toDelete.before;
            }
            else if ( (toDelete.next == null) && (toDelete.before != null) ) {  // VYMAZAVAM POSLEDNY PRVOK V LINKEDLISTE
                toDelete.before.next = null;
            }
            filled--;


            // ZMENŠOVANIE TABUĽKY IBA AK VÝRAZNE KLESNE FAKTOR NAPLNENIA
            if( fillFactor() < 0.2 ){ // AK JE NAPLNENA NA MENEJ AKO POLOVICU STAREJ VEĽKOSTI (keďže zväčšujeme 2x) JE POTREBNE ZMENŠIŤ TABUĽKU
                resizeHashTable(size/2);
            }
        }
        else{
            System.out.println("Prvok "+ stringToDelete + " sa nenachadza v hash Tabulke - nemoze byt vymazany");
        }
    }

    public static String getRandomNames(){
        String[] firstNames = {"John", "Emma", "Olivia", "Ava", "Isabella", "Sophia", "Robin"};
        String[] lastNames = {"Doe", "Smith", "Johnson", "Williams", "Jones", "Brown", "Hood"};

        Random random = new Random();

        return firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];

    }
}

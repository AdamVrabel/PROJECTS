package hash_table_linear_probing;

// HASH TABULKA (LINEAR - PROBING riesenie kolizii)

public class HashTableLinearProbing {
    public int size;
    public int filled;
    public Item[] array;    // POLE ITEMOV

    private int numberOfColision = 0; // cisto pre mna, kolko kolizii

    public HashTableLinearProbing(){
    }
    public HashTableLinearProbing(int size){
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

        int index = hash(key, size);    // KEY UKAZUJE NA INDEX V POLI KAM MAME VLOZIT PRVOK

        if( array[index] == null || array[index].value.equals("#DELETED#")){   // AK V POLI na indexe KEY NIEJE ZIADNY PRVOK (nenastala kolizia) alebo tam je prvok oznaceny ako vymazany
            array[index] = new Item(stringToInsert, key);
        }
        else{   // KOLIZIA - LINEAR PROBING
            //System.out.println("# KOLIZIA #");

            // AK PRVOK S ROVNAKÝMI DATAMI UŽ EXISTUJE, TAK SA NEPRIDÁ
            if( (array[index].key == key) && (stringToInsert.equals(array[index].value)) ){
                //System.out.println("Prvok \"" + stringToInsert + "\" uz je v tabulke, takze sa neprida !");
                return;
            }

            while( array[index] != null){    // posun na dalsi prvok s rovnakym indexom ktory nieje null
                if(array[index].value.equals("#DELETED#")){ // AK JE PRVOK OZNACENY AKO VYMAZANY, BREAK, MOZEM SEM VLOZIT NOVY
                    break;
                }
                index = (index + 1) % size; // POSUNIE O 1 MIESTO V TABULKE

                // AK PRVOK S ROVNAKÝMI DATAMI UŽ EXISTUJE, TAK SA NEPRIDÁ
                if( array[index] != null && (array[index].key == key) && (stringToInsert.equals(array[index].value)) ){
                    //System.out.println("Prvok \"" + stringToInsert + "\" uz je v tabulke, takze sa neprida !");
                    return;
                }

            }
            numberOfColision++;
            array[index] = new Item(stringToInsert, key);   // VLOZENIE PRVKU NA SPRAVNU POZICIU

            //////////////////////////
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

    public void resizeHashTable(int newSize){
        // PRI ZVAČŠOVANÍ size*2
        // PRI ZMENŠOVANÍ size/2
        //HashTableLinearProbing newHashTableLinear = new HashTableLinearProbing(2*size);
        HashTableLinearProbing newHashTableLinear = new HashTableLinearProbing(newSize);
        numberOfColision = 0;

        Item actual = null;

        // CITANIE PRVKOV ZO STAREJ TABULKY A PRIDAVANIE DO NOVEJ (zvacsenej)
        for(int i = 0; i < this.array.length; i++){  // ITERUJEM CEZ CELE POLE
            if(array[i] != null){   // VOJDEM DO POLA IBA AK EXISTUJE PRVOK

                newHashTableLinear.insert(array[i].value);   // INSERTNI PRVOK DO NOVEJ HASHTABULKY (stringom generuje kluce)
                //newHashTableLinear.insert(array[i].value, array[i].key);   // INSERTNI PRVOK DO NOVEJ HASHTABULKY (kluce ostavaju)
                //System.out.println("# (" + array[i].value + ") KEY:[" + array[i].key + "]  mapujem do novej HashTabulky");

            }
        }

        // NASTAVENIE NOVEJ TABULKY NA AKTUALNU
        this.size = newHashTableLinear.size;
        this.filled = newHashTableLinear.filled;
        this.array = newHashTableLinear.array;
        this.numberOfColision = newHashTableLinear.numberOfColision;

        //System.out.println("RESIZING COMPLETE !");

    }

    public Item searchItem(String stringToSearch, int key){
        int index = hash(key, size);    // index UKAZUJE NA INDEX V POLI KDE BY MAL BYT ULOZENY udaj

        while( array[index] != null ){
            // KONTROLA CI JE TO ONO
            if((array[index].key == key) && (stringToSearch.equals(array[index].value)) ){
                //System.out.println("Hladany:\""+ stringToSearch + "\" KEY:[" + key + "]  Prvok sa našiel !  Najdeny: \"" + array[index].value + "\" KEY:[" + array[index].key + "]");
                return array[index];
            }
            index = (index + 1) % size; // POSUN NA DALSI INDEX V POLI
        }
        //System.out.println("Hladany:" + stringToSearch + "  sa nenašiel !");
        return null;

    }
    public Item searchItem(String stringToSearch){
        int key = getNumberFromString(stringToSearch);

        return searchItem(stringToSearch, key);
    }
    public void search(String stringToSearch){  // NAJDE STRING (vypocita sama key zo stringu)
        int key = getNumberFromString(stringToSearch);  // iba kvoli sout

        Item item = searchItem(stringToSearch);

        if(item != null){   // PRVOK NAJDENY
            System.out.println("Hladany:\""+ stringToSearch + "\" KEY:[" + key + "]  Prvok sa našiel !  Najdeny: \"" + item.value + "\" KEY:[" + item.key + "]");
        }
        else{
            System.out.println("Hladany:\"" + stringToSearch + "\"  sa nenašiel !");
        }
    }
    public void search(String stringToSearch, int key){ // NAJDE STRING (pomocou zadaneho KEY)

        Item item = searchItem(stringToSearch, key);

        if(item != null){   // PRVOK NAJDENY
            System.out.println("Hladany:\""+ stringToSearch + "\" KEY:[" + key + "]  Prvok sa našiel !  Najdeny: \"" + item.value + "\" KEY:[" + item.key + "]");
        }
        else{
            System.out.println("Hladany:\"" + stringToSearch + "\"  sa nenašiel !");
        }

    }


    public void delete(String stringToDelete, int key){ // VYMAZE STRING (pomocou zadaneho KEY)
        Item item = searchItem(stringToDelete, key);

        if(item != null){   // PRVOK NAJDENY
            //System.out.println("Hladany:\""+ stringToDelete + "\" KEY:[" + key + "]  Prvok sa našiel !  Najdeny: \"" + item.value + "\" KEY:[" + item.key + "]");
            //System.out.println("VYMAZAVAM !");

            item.value = "#DELETED#";   // OZNACI DANY PRVOK AKO VYMAZANY
            filled--;

            // ZMENŠOVANIE TABUĽKY IBA AK VÝRAZNE KLESNE FAKTOR NAPLNENIA
            if( fillFactor() < 0.2 ){ // AK JE NAPLNENA NA MENEJ AKO POLOVICU STAREJ VEĽKOSTI (keďže zväčšujeme 2x) JE POTREBNE ZMENŠIŤ TABUĽKU
                resizeHashTable(size/2);
            }
        }
        else{
            System.out.println("Hladany:\"" + stringToDelete + "\"  sa nenašiel !  NEMOZEM HO TEDA VYMAZAT");
        }

    }

    public void delete(String stringToDelete){
        int key = getNumberFromString(stringToDelete);
        delete(stringToDelete, key);
    }


}

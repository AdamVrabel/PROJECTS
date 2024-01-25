package hash_table_chaining;

// ITEM JE PACKAGE PRIVATE
class Item {

    // KEY A VALUE
    // VALUE SU DATA ( v mojom pripade mam String)
    // KEY je kľúč vyrobený z data
    // KEY poslem do hash funkcie a tá mi vráti index kam mam ulozit Item

    public String value;

    // PACKAGE PRIVATE
    int key;        // VYSLEDOK V HASH FUNKCII

    Item before = null;
    Item next = null;

    int vek_osoby;  // DOPLNKOVÉ DÁTA
    public int getVek_osoby() {
        return vek_osoby;
    }
    public void setVek_osoby(int vek_osoby) {
        this.vek_osoby = vek_osoby;
    }


    public Item(){
        key = -1;
        before = null;
        next = null;
    }
    public Item(String value){
        this.value = value;
        key = -1;
        before = null;
        next = null;
    }

    public Item(String value, int key){
        this.value = value;
        this.key = key;
        before = null;
        next = null;
    }

}

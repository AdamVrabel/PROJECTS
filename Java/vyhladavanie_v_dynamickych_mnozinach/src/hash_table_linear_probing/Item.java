package hash_table_linear_probing;

// ITEM JE PACKAGE PRIVATE
class Item {

    // KEY A VALUE
    // VALUE SU DATA (v mojom pripade mam String)
    // KEY je kľúč vyrobený z data
    // KEY poslem do hash funkcie a tá mi vráti index kam mam ulozit Item



    public String value;
    int key;        // VYSLEDOK V HASH FUNKCII

    int vek_osoby;  // DOPLNKOVÉ DÁTA
    public int getVek_osoby() {
        return vek_osoby;
    }
    public void setVek_osoby(int vek_osoby) {
        this.vek_osoby = vek_osoby;
    }

    public Item(String value, int key){
        this.value = value;
        this.key = key;
    }


    public Item(){
        key = -1;
    }

    public Item(String value){
        this.value = value;
        key = -1;
    }

}
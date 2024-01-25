package avl_tree;

public class AvlTree {
    public Node root;                   // HLAVNÝ UZOL

    public AvlTree(){          // CONSTRUCTOR
        root = null;
    }

    // ČÍTANIE STROMU
    public void printAvl(Node n) {      // INORDER READ,  (LEFT, ROOT, RIGHT)
        if(n != null) {
            printAvl(n.left);
            //System.out.println("Hodnota: " + n.data + " Vyska: " + n.height + " BF: " + n.balanceFactor);
            System.out.println("Hodnota: " + n.data + " Vyska: " + n.height);
            printAvl(n.right);
        }
    }
    void postorder(Node node){          // POSTORDER READ,  (LEFT, RIGHT, ROOT)
        if(node == null){
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " -> ");
    }

    void inorder(Node node){            // INORDER READ,  (LEFT, ROOT, RIGHT)
        if(node == null){
            return;
        }
        inorder(node.left);
        System.out.print(node.data + " -> ");
        inorder(node.right);
    }

    void preorder(Node node){           // PREORDER READ,  (ROOT, LEFT, RIGHT)
        if(node == null){
            return;
        }
        System.out.print(node.data + " -> ");
        preorder(node.left);
        preorder(node.right);
    }


    // VÝŠKA
    // height listu = 0
    // height = počet ciest od NODEu k listom (najvačšie možné číslo)

    // VRATI VÝŠKU, NEPOČÍTA JU
    private int height(Node n){     // VRÁTI VÝŠKU NODEu alebo -1 pre prázny podstrom
        if(n == null){
            return -1;
        }
        else{
            return n.height;
        }
    }
    // AKTUALIZÁCIA VÝŠKY, počítam od listov
    private void updateHeight(Node n){
        if (n != null){
            n.height = 1 + Math.max( height(n.left), height(n.right) );
        }
    }


    //////////// POMALÉ ////////////
    /*
    // PREPOČÍTA POSTUPNE VÝŠKY VŠETKÝCH NODEov (rekurzívne vojde najviac vlavo, vpravo, o jedno vyššie ...)
    // funkciu volám od NODEu a vypočíta výšku daného nodeu a všetkých pod ním
    public void calculateHeight(Node node){
        if(node != null) {
            calculateHeight(node.left);
            updateHeight(node);
            //System.out.println("Hodnota: " + node.data + " Vyska: " + node.height);
            calculateHeight(node.right);
            updateHeight(node);
        }

    }
    // BF(node) = H(node.right) - H(node.left)

    public void calculateBF(Node node){
        if(node != null) {
            updateBF(node); // TOTO NEVIEM ČI JE OK
            calculateBF(node.left);
            updateBF(node.left);
            calculateBF(node.right);
            updateBF(node.right);
        }
    }

    public int updateBF(Node node){
        if(node != null){

            int bf;

//            System.out.println("### PRVOK(" + node.data + "), HEIGHT(" + node.height + ")");
//            if(node.left != null){
//                //System.out.println("### PRVOK->LEFT(" + node.left.data + "), HEIGHT(" + node.left.height + ")");
//                System.out.println("### PRVOK->LEFT(" + node.left.data + "), HEIGHT(" + height(node.left) + ")");
//            }
//            if(node.right != null){
//                //System.out.println("### PRVOK->RIGHT(" + node.right.data + "), HEIGHT(" + height(node.right) + ")");
//                System.out.println("### PRVOK->RIGHT(" + node.right.data + "), HEIGHT(" + height(node.right) + ")");
//            }


            //System.out.println("LEFT H:" + height(node.left) + "   RIGHT H:" + height(node.right));
            bf = height(node.right) - height(node.left);
            //System.out.println("### BF = (" + bf + ")");

            node.balanceFactor = bf;
            return bf;

        }

        //return -666;
        return 0;
    }

    */
    //////////// POMALÉ ////////////

    // NOVY INSERT //
    public void insertAvl(int data){
        //System.out.println("INSERT NEW NODE: [" + data + "]");
        Node actual = null;
        Node ancestor = null;

        // AK STROM NEEXISTUJE, VYTVORÍM ROOT PRVOK
        if(this.root == null){          // PRVÉ VYTVORENIE STROMU
            //System.out.println("VYTVÁRAM PRVÝ KRÁT STROM, PRVOK: "+data);
            this.root = new Node(data);
            this.root.height = 0;       //ROOT NODE AK JE SAM MÁ VŽDY VÝŠKU 0
        }
        else{                           // AK UŽ STROM MÁ ASPOŇ 1 PRVOK
            actual = root;

            while(actual != null){      // HĽADAM KAM MAM PRIDAŤ NOVÝ NODE
                // AK V STROME UŽ HODNOTA JE, NEPRIDAVAM JU
                if(actual.data == data){
                    //System.out.println("HODNOTA " + data + " SA V STROME UŽ NACHÁDZA.");
                    return;
                }
                // AK JE PRIDAVANE DATA MENŠIE AKO ACTUAL-DATA, idem v strome doľava
                else if(data < actual.data) {
                    //System.out.println(data + " < " + actual.data + "  IDEM v strome DOĽAVA");
                    ancestor = actual;
                    actual = actual.left;
                }
                // AK JE PRIDAVANE DATA VAČŠIA AKO ACTUAL-DATA, idem v strome doprava
                // (data > actual.data)
                else{
                    //System.out.println(data + " > " + actual.data + "  IDEM v strome DOPRAVA");
                    ancestor = actual;
                    actual = actual.right;
                }
            }
            // v ANCESTOR je nájdená pozícia, pridám nový NODE na miesto
            if(data < ancestor.data){
                ancestor.left = new Node(data);
                ancestor.left.ancestor = ancestor;

                actual = ancestor.left;     // ACTUAL TERAZ UKAZUJE NA NOVO PRIDANÝ PRVOK
            }
            //(data > ancestor.data)
            else{
                ancestor.right = new Node(data);
                ancestor.right.ancestor = ancestor;

                actual = ancestor.right;    // ACTUAL TERAZ UKAZUJE NA NOVO PRIDANÝ PRVOK
            }

            // KONTROLA A VYVAŽENIE STROMU
            // VYVAZENIE PO VLOZENI OD LISTOV SMEROM KU KOREŇU

            // UPDATUJEM VÝŠKU OD NOVO PRIDANÉHO NODEU AŽ POKIAĽ NEVYJDEM DO ROOTU
            // NOVO VLOŽENÝ NODE MÁ VÝŠKU 0
            do {
                actual = actual.ancestor;
                updateHeight(actual);
                controlBalanceFactor(actual);   // KONTROLUJE BF IBA PRE ACTUAL
            } while (actual.ancestor != null);

        }//end ELSE
    }
    // NOVY INSERT END //


    // VKLADANIE DÁT - POMALÉ
    /*
    public void insertAvlSlow(int data){  // vloží prvok do stromu (ak sa v strome nenachádza), potom strom vyváži
        Node actual = null;
        Node ancestor = null;

        // AK STROM NEEXISTUJE, VYTVORÍM ROOT PRVOK
        if(this.root == null){  // PRVÉ VYTVORENIE STROMU
            //System.out.println("VYTVÁRAM PRVÝ KRÁT STROM, PRVOK: "+data);
            this.root = new Node(data);
            this.root.height = 0; //ROOT NODE AK JE SAM MÁ VŽDY VÝŠKU 0
        }
        else{   // AK UŽ STROM MÁ ASPOŇ 1 PRVOK
            actual = root;

            while(actual != null){  // HĽADAM KAM MAM PRIDAŤ NOVÝ NODE
                // AK V STROME UŽ HODNOTA JE, NEPRIDAVAM JU
                if(actual.data == data){
                    //System.out.println("HODNOTA " + data + " SA V STROME UŽ NACHÁDZA.");
                    return;
                }
                // AK JE PRIDAVANE DATA MENŠIE AKO ACTUAL-DATA, idem v strome doľava
                else if(data < actual.data) {
                    //System.out.println(data + " < " + actual.data + "  IDEM v strome DOĽAVA");
                    ancestor = actual;
                    actual = actual.left;
                }
                // AK JE PRIDAVANE DATA VAČŠIA AKO ACTUAL-DATA, idem v strome doprava
                // (data > actual.data)
                else{
                    //System.out.println(data + " > " + actual.data + "  IDEM v strome DOPRAVA");
                    ancestor = actual;
                    actual = actual.right;
                }
            }
            //v ANCESTOR je nájdená pozícia, pridám nový NODE na miesto
            if(data < ancestor.data){
                ancestor.left = new Node(data);
                ancestor.left.ancestor = ancestor;

                actual = ancestor.left; // ACTUAL TERAZ UKAZUJE NA NOVO PRIDANÝ PRVOK
            }
            //(data > ancestor.data)
            else{
                ancestor.right = new Node(data);
                ancestor.right.ancestor = ancestor;

                actual = ancestor.right; // ACTUAL TERAZ UKAZUJE NA NOVO PRIDANÝ PRVOK
            }

            //System.out.println("#### JE TO PEKNE PICI");
            // SEM KONTROLA A VYVAŽENIE STROMU
            //System.out.println("ACTUAL UKAZUJE NA PRVOK: " + actual.data);
            // ! calculateHeight(root);

            //controlBalanceFactor(actual);

            //calculateBF(root); // IBA PRE KONTROLU BF, v kode nepoužívať !!
            //calculateBF(root);  // TOTO POTOM PREČ - musí v chceckBalance skontrolovať BF podla výšok


            //printAvl(root);
            //System.out.println("############");
            //actual.height = getHeight(actual);

        }//end ELSE

    }//end insertAvlSlow()
     */

    // AK MAM PREPOČÍTANÉ VÝŠKY tak pre zistenie BalanceFactora stačí zavolať getBF
    public int getBF(Node node){
        if(node != null){
            return height(node.right) - height(node.left);
        }
        return 0;
    }

    // KONTROLUJE CI JE NODE VYVAZENY ALEBO NIE
    public void controlBalanceFactor(Node actual){
        int bf = getBF(actual);

        if(bf > 1 || bf < -1){ // JE HEAVY // JE NEVYVAŽENÝ

            // VOLAM VYVAZOVANIE vrati mi ten node ktorý vyvazilo (root podstromu)
            // actual.ancestor = doReBalance(actual);
            if(actual.ancestor == null){ // NEVYVÁŽENÝ JE KOREŇ
                root = doReBalance(actual);
            }
            else{
                //actual.ancestor = doReBalance(actual);
                int tmpData = actual.data;  // ULOZIM SI Z NODEU .data pred vyvazenim
                int tmpDataAncestor = actual.ancestor.data;

                //actual = doReBalance(actual);   //VYVAZENIE
                if (tmpData < tmpDataAncestor){
                    actual.ancestor.left = doReBalance(actual);
                }
                if (tmpData > tmpDataAncestor){
                    actual.ancestor.right = doReBalance(actual);
                }
            }
        }
    }// END controlBalanceFactor()

    // L-HEAVY // R-HEAVY // L-R-HEAVY // R-L-HEAVY //
    private Node doReBalance(Node node){

        // POZRIEM či node                              BF =  -2 ALEBO 2
        // TAK node.left alebo node.right POZERAM ČI    BF =  -1 ALEBO 1

        if( getBF(node) <= -2){             // 1. LEFT-HEAVY
            if( getBF(node.left) < 0 ){     // 2. LEFT-HEAVY
                // JE LEFT, LEFT HEAVY
                // UROB RIGHT ROZACIU OKOLO NODE
                node = rightRotation(node);
            }
            if ( getBF(node.left) > 0 ){    // 2. RIGHT-HEAVY
                // LEFT, RIGHT HEAVY
                // UROB LEFT-RIGHT ROTACIU OKOLO NODE
                node = leftRightRotation(node);
            }
        }
        if( getBF(node) >= 2 ){             // 1. RIGHT-HEAVY
            if( getBF(node.right) > 0 ){     // 2. RIGHT-HEAVY
                // JE RIGHT, RIGHT HEAVY
                // UROB LEFT ROZACIU OKOLO NODE
                node = leftRotation(node);
            }
            if ( getBF(node.right) < 0 ){    // 2. LEFT-HEAVY
                // RIGHT, LEFT HEAVY
                // UROB RIGHT-LEFT ROTACIU OKOLO NODE
                node = rightLeftRotation(node);
            }

        }
        return node; //vráti vyvaženy (nový root podstromu)
    }

    /////// ROTÁCIE ///////
    private Node rightRotation(Node node){
        Node newRoot = null;
        Node a, b, br;
        //Node c;

        // ANCESTORY
        Node aAnc, bAnc, brAnc;
        aAnc = node.ancestor;

        a = node;
        b = node.left;
        br = b.right;
        //c = node.left.left;

        newRoot = b;
        newRoot.right = a;
        newRoot.right.left = br;    // a.left = br; // TO ISTÉ

        // ANCESTORY
        newRoot.ancestor = aAnc; // node.ancestor
        newRoot.right.ancestor = newRoot;
        if ( newRoot.hasRightChild() && newRoot.right.hasLeftChild() ){
            newRoot.right.left.ancestor = newRoot.right;    //br.ancestor = newRoot.right;
        }

        // ADD UPDATE HEIGHT
        updateHeight(newRoot.right);        // a
        updateHeight(newRoot);              // b
        updateHeight(newRoot.ancestor);     // aAnc

        return newRoot;
    }
    private Node leftRightRotation(Node node){
        Node newRoot = null;
        Node a, b, c, cl, cr;

        // ANCESTORY
        Node aAnc, bAnc, cAnc, clAnc, crAnc;
        aAnc = node.ancestor;

        a = node;
        b = node.left;
        c = b.right;
        cl = c.left;
        cr = c.right;

        newRoot = c;
        newRoot.left = b;
        newRoot.right = a;

        newRoot.left.right = cl;
        newRoot.right.left = cr;

        // ANCESTORY
        newRoot.ancestor = aAnc;
        newRoot.left.ancestor = newRoot;
        newRoot.right.ancestor = newRoot;
        if( newRoot.hasLeftChild() && newRoot.left.hasRightChild() ){
            newRoot.left.right.ancestor = newRoot.left;
        }
        if ( newRoot.hasRightChild() && newRoot.right.hasLeftChild() ){
            newRoot.right.left.ancestor = newRoot.right;
        }

        // ADD UPDATE HEIGHT
        updateHeight(newRoot.right);        // a
        updateHeight(newRoot.left);         // b
        updateHeight(newRoot);              // c
        updateHeight(newRoot.ancestor);     // aAnc

        return newRoot;
    }
    public Node leftRotation(Node node){
        Node newRoot = null;
        Node a, b, bl;
        Node c;

        // ANCESTORY
        Node aAnc, bAnc, blAnc;
        aAnc = node.ancestor;

        a = node;
        b = node.right;
        bl = b.left;
        //c = node.right.right;

        newRoot = b;
        newRoot.left = a;
        newRoot.left.right = bl;    // a.right = bl; // TO ISTÉ

        // ANCESTORY
        newRoot.ancestor = aAnc;
        newRoot.left.ancestor = newRoot;
        if ( newRoot.hasLeftChild() && newRoot.left.hasRightChild() ){
            newRoot.left.right.ancestor = newRoot.left;
        }

        // ADD UPDATE HEIGHT
        updateHeight(newRoot.left);        // a
        updateHeight(newRoot);              // b
        updateHeight(newRoot.ancestor);     // aAnc

        return newRoot;
    }
    private Node rightLeftRotation(Node node){
        Node newRoot = null;
        Node a, b, c, cl, cr;

        // ANCESTORY
        Node aAnc, bAnc, cAnc, clAnc, crAnc;
        aAnc = node.ancestor;

        a = node;
        b = node.right;
        c = b.left;
        cl = c.left;
        cr = c.right;

        newRoot = c;
        newRoot.left = a;
        newRoot.right = b;

        newRoot.left.right = cl;
        newRoot.right.left = cr;

        // ANCESTORY
        newRoot.ancestor = aAnc;
        newRoot.left.ancestor = newRoot;
        newRoot.right.ancestor = newRoot;
        if ( newRoot.hasLeftChild() && newRoot.left.hasRightChild() ){
            newRoot.left.right.ancestor = newRoot.left;
        }
        if ( newRoot.hasRightChild() && newRoot.right.hasLeftChild() ){
            newRoot.right.left.ancestor = newRoot.right;
        }

        // ADD UPDATE HEIGHT
        updateHeight(newRoot.left);        // a
        updateHeight(newRoot.right);         // b
        updateHeight(newRoot);              // c
        updateHeight(newRoot.ancestor);     // aAnc

        return newRoot;
    }
    /////// ROTÁCIE ///////


    // SEARCH, AK SA NACHÁDZA V STROME VRÁTI REFERENCIU, INAK NULL
    public Node searchNode(int number){
        Node actual = root;

        if(root != null){      // STROM NIEJE PRAZDNY, má zmysel hľadať
            while(actual != null) {
                if (actual.data == number) {        // AK V actual.data je hľadaný number tak vráti ACTUAL
                    return actual;
                } else if (number < actual.data) {  // AK hľadaný number je menši tak sa posúvam vľavo
                    actual = actual.left;
                }
                else {                              // AK hľadaný number je väčší tak sa posúvam vpravo
                    actual = actual.right;
                }
            }
        }
        return null;    // vráti null keď prvok nenajde v strome
    }

    // SEARCH AJ S VYPISOM
    public void search(int number){

        if(searchNode(number) != null){
            System.out.println("PRVOK " + number + "JE V STROME !");
        }
        else{
            System.out.println("PRVOK "+ number +" sa nenasiel !");
        }
    }



    // VRÁTI NAJVIAC LAVÝ PRVOK ktorý nájde od zadaného nodeu
    public Node mostLeftChild(Node node){
        Node actual = node;

        while( actual.hasLeftChild() ){
            actual = actual.left;
        }

        return actual;
    }

    /// DELETE ///
    public void delete(int number){
        Node toDelete = searchNode(number);    // NAJDE ČI JE V STROME, ak je, vráti REFERENCIU, ak nieje, vrati NULL

        if( toDelete != null ){
            //VYMAŽ
            //System.out.println("Číslo " + number + " je v strome, bude vymazané ! " + "  [" + toDelete.data + "]");


            if( toDelete.ancestor == null ){    // VYMAZUJEM ROOT,   toDelete == root
                // VYMAZUJEM ROOT //
                if(( toDelete.left == null) && (toDelete.right == null) ){
                    root = null;
                    return;
                }
                if( toDelete.hasLeftChild() && (toDelete.right == null)){   // root ma iba leftChild

                    root = toDelete.left;
                    toDelete.left.ancestor = null;
                    // UPDATOVAŤ VÝŠKU NETREBA, lebo oldRoot.left mal výšku 0, teraz je to newRoot
                    //updateHeight(root);
                    //controlBalanceFactor(root);

                    return;
                }
                if( (toDelete.left == null) && toDelete.hasRightChild() ){   // root ma iba rightChild
                    root = toDelete.right;
                    toDelete.right.ancestor = null;
                    // UPDATOVAŤ VÝŠKU NETREBA, lebo oldRoot.right mal výšku 0, teraz je to newRoot
                    //updateHeight(root);
                    //controlBalanceFactor(root);

                    return;
                }
                if( toDelete.hasLeftChild() && toDelete.hasRightChild() ){   // root ma leftChild aj rightChild

                    Node mostLeftNode = mostLeftChild(toDelete.right);  // nájdem najmenši z vačších ako node NA DELETE
                    //System.out.println("Chcem zmazat root, root.right a najviac vlavo je: " + mostLeftNode.data);

                    toDelete.data = mostLeftNode.data; // PREKOPIRUJEM NAJMENSI NODE (iba cislo, nie referenciu), KT JE VASCSI OD toDelete
                    // VYMAZEM mostLeftNode

                    // AK mostLeftNode je list
                    if ( (mostLeftNode.left == null) && (mostLeftNode.right == null) ){
                        //toDelete.right = null;
                        if( mostLeftNode.ancestor == toDelete ){    // ak mostLeftNode je priamy potomok toDelete
                            toDelete.right = null;
                            // AKTUALIZUJEM ROOT (nic ine sa nezmenilo)
                            updateHeight(toDelete);
                            controlBalanceFactor(toDelete);
                            return;
                        }
                        else{                                       // ak mostLeftNode nieje priamy potomok toDelete
                            mostLeftNode.ancestor.left = null;  // "vymaze" mostLeftNode //.left preto lebo mostLeftNode nieje priamy potomok toDelete
                            // SKOCI NA UPDATE HEIGHT OD MostLeftNode.ANCESTOR
                        }
                    }
                    // AK mostLeftNode má LEFT-CHILD
                    if ( mostLeftNode.hasLeftChild() ) {
                        System.out.println("###TU BY SA NEMALO NIKDY DOSTAT");  // ak je to mostLeftNode tak vzdy .left == null
                    }
                    // AK mostLeftNode má RIGHT-CHILD
                    if ( mostLeftNode.hasRightChild() ){
                        if( mostLeftNode.ancestor == toDelete ){    // ak mostLeftNode je priamy potomok toDelete
                            toDelete.right = mostLeftNode.right;
                            mostLeftNode.right.ancestor = toDelete;

                            // AKTUALIZUJEM ROOT (nic ine sa nezmenilo)
                            updateHeight(toDelete);
                            controlBalanceFactor(toDelete);
                            return;
                        }
                        else{                                       // ak mostLeftNode nieje priamy potomok toDelete
                            mostLeftNode.ancestor.left = mostLeftNode.right;
                            mostLeftNode.right.ancestor = mostLeftNode.ancestor;
                            // SKOCI NA UPDATE HEIGHT OD MostLeftNode.ANCESTOR
                        }
                    }
                    // UPDATUJEM VÝŠKU OD VYMAZANÉHO NODEU AŽ POKIAL NEVYJDEM DO ROOTU
                    Node tmp = mostLeftNode.ancestor;
                    do {
                        updateHeight(tmp);
                        controlBalanceFactor(tmp);
                        tmp = tmp.ancestor;

                    } while (tmp != null);
                    return;
                }
                // VYMAZUJEM ROOT //
            }
            else {                              // VYMAZUJEM PRVOK (nie ROOT)
                int toDeleteData = toDelete.data;
                int toDeleteAncData = toDelete.ancestor.data;
                // toDelete je LIST
                if ( (toDelete.left == null) && (toDelete.right == null) ){ // NEMA ANI LEFT-child ANI RIGHT-child
                    //toDelete.ancestor = null;

                    //int toDeleteData = toDelete.data;
                    //int toDeleteAncData = toDelete.ancestor.data;
                    if(toDeleteData < toDeleteAncData){
                        toDelete.ancestor.left = null;
                    }
                    if(toDeleteData > toDeleteAncData){
                        toDelete.ancestor.right = null;
                    }

                    // UPDATE HEIGHT AND CONTROL BF
                    Node tmp = toDelete.ancestor;
                    do {
                        updateHeight(tmp);
                        controlBalanceFactor(tmp);
                        tmp = tmp.ancestor;

                    } while (tmp != null);
                    return;

                }//END toDelete je LIST

                // NIEJE TO LIST
                if( toDelete.hasRightChild() && (toDelete.left == null) ){  // MA IBA RIGHT-child
                    if(toDeleteData < toDeleteAncData){
                        toDelete.ancestor.left = toDelete.right;
                        toDelete.right.ancestor = toDelete.ancestor;    // NASTAVIM ANCESTOR - ZKONTROLOVAT ESTE
                    }
                    if(toDeleteData > toDeleteAncData){
                        toDelete.ancestor.right = toDelete.right;
                        toDelete.right.ancestor = toDelete.ancestor;    // NASTAVIM ANCESTOR - ZKONTROLOVAT ESTE
                    }
                    // UPDATE HEIGHT AND CONTROL BF
                    Node tmp = toDelete.ancestor;
                    do {
                        updateHeight(tmp);
                        controlBalanceFactor(tmp);
                        tmp = tmp.ancestor;

                    } while (tmp != null);
                    return;
                }
                if( toDelete.hasLeftChild() && (toDelete.right == null) ){  // MA IBA LEFT-child
                    if(toDeleteData < toDeleteAncData){
                        toDelete.ancestor.left = toDelete.left;
                        toDelete.left.ancestor = toDelete.ancestor;    // NASTAVIM ANCESTOR - ZKONTROLOVAT ESTE
                    }
                    if(toDeleteData > toDeleteAncData){
                        toDelete.ancestor.right = toDelete.left;
                        toDelete.left.ancestor = toDelete.ancestor;    // NASTAVIM ANCESTOR - ZKONTROLOVAT ESTE
                    }
                    // UPDATE HEIGHT AND CONTROL BF
                    Node tmp = toDelete.ancestor;
                    do {
                        updateHeight(tmp);
                        controlBalanceFactor(tmp);
                        tmp = tmp.ancestor;

                    } while (tmp != null);
                    return;
                }

                if( toDelete.hasLeftChild() && toDelete.hasRightChild() ){      // MA AJ RIGHT-child aj LEFT-child
                    Node mostLeftNode = mostLeftChild(toDelete.right);  // nájdem najmenši z vačších ako node NA DELETE
                    //System.out.println("Ten co chcem zmazat tak.right a najviac vlavo je: " + mostLeftNode.data);

                    toDelete.data = mostLeftNode.data; // PREKOPIRUJEM NAJMENSI NODE (iba cislo, nie referenciu), KT JE VASCSI OD toDelete
                    // VYMAZEM KOREKTNE mostLeftNode

                    // AK mostLeftNode je list
                    if ( (mostLeftNode.left == null) && (mostLeftNode.right == null) ){
                        //toDelete.right = null;
                        if( mostLeftNode.ancestor == toDelete ){    // ak mostLeftNode je priamy potomok toDelete
                            toDelete.right = null;
                            //mostLeftNode.ancestor.right = null;
                        }
                        else{                                       // ak mostLeftNode nieje priamy potomok toDelete
                            mostLeftNode.ancestor.left = null;  // "vymaze" mostLeftNode //.left preto lebo mostLeftNode nieje priamy potomok toDelete
                        }

                        // UPDATE HEIGHT AND CONTROL BF ak je MOSTLEFTNODE list
                        Node tmp = toDelete.ancestor;
                        do {
                            updateHeight(tmp);
                            controlBalanceFactor(tmp);
                            tmp = tmp.ancestor;

                        } while (tmp != null);
                        return;

                    }
                    if ( mostLeftNode.hasLeftChild() ) {
                        System.out.println("###TU BY SA NEMALO NIKDY DOSTAT");  // ak je to mostLeftNode tak vzdy .left == null
                    }
                    // AK mostLeftNode má RIGHT-CHILD
                    if ( mostLeftNode.hasRightChild() ){
                        if( mostLeftNode.ancestor == toDelete ){    // ak mostLeftNode je priamy potomok toDelete
                            toDelete.right = mostLeftNode.right;
                            mostLeftNode.right.ancestor = toDelete;
                        }
                        else{                                       // ak mostLeftNode nieje priamy potomok toDelete
                            mostLeftNode.ancestor.left = mostLeftNode.right;
                            mostLeftNode.right.ancestor = mostLeftNode.ancestor;
                        }
                        // UPDATE HEIGHT AND CONTROL BF
                        Node tmp = mostLeftNode.ancestor;
                        do {
                            updateHeight(tmp);
                            controlBalanceFactor(tmp);
                            tmp = tmp.ancestor;

                        } while (tmp != null);
                        return;
                        //mostLeftNode.ancestor.left = mostLeftNode.right;
                        //mostLeftNode.right.ancestor = mostLeftNode.ancestor;
                    }
                    ///////////////////////////////////////
                }
                //END NIEJE TO LIST

            }


            // PREPOCITAJ VYSKY
            // ! calculateHeight(root);

            // SKONTROLUJ BF, CI NETREBA ROTOVAT
            controlBalanceFactor(toDelete.ancestor);
        }
        else{
            // HODNOTA number NIEJE V STROME
            //System.out.println("Číslo \"" + number + "\" sa v strome NENACHÁDZA, nemôže byť VYMAZANÉ !");
            return;
        }




    }

}

package red_black_tree;

import avl_tree.Node;

/**
 * VLASTNOSTI ČERVENO ČIERNEHO STROMU:
 * <li>Každý uzol je buď červený alebo čierny.</li>
 * <li>Koreň je čierny.</li>
 * <li>Všetky listy NIL (null) sú čierne.</li>
 * <li>Červený uzol nesmie mať červené deti.</li>
 * <li>Všetky cesty od uzla k (null) listom nižšie obsahujú rovnaký počet čiernych uzlov.</li>
 */
public class RedBlackTree {
    public static final boolean BLACK = true;
    public static final boolean RED = false;

    public RedBlackNode root;
    //private final RedBlackNode NIL = new RedBlackNode();    // NULL-LEAF (list, ktorý je vlastne null)

    public RedBlackTree(){ root = null; }

    // PRINT STROMU
    public void printRB(RedBlackNode n) { // INORDER READ,  (LEFT, ROOT, RIGHT)
        if(n != null) {
            printRB(n.left);
            //System.out.println("Hodnota: " + n.data + " Vyska: " + n.height + " BF: " + n.balanceFactor);
            System.out.println("Hodnota: " + n.data + " Farba: " + n.print_color());
            printRB(n.right);
        }
    }


    // SEARCH, AK SA NACHÁDZA V STROME VRÁTI REFERENCIU, INAK NULL
    public RedBlackNode searchNode(int number){
        RedBlackNode actual = root;

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
            System.out.println("PRVOK " + number + " JE V STROME !");
        }
        else{
            System.out.println("PRVOK "+ number +" sa nenasiel !");
        }
    }


    public void insert(int data){   // vloží prvok do stromu (ak sa v strome nenachádza), potom strom vyváži
        RedBlackNode actual = null;
        RedBlackNode ancestor = null;

        // AK STROM NEEXISTUJE, VYTVORÍM ROOT PRVOK
        if(this.root == null){  // PRVÉ VYTVORENIE STROMU
            //System.out.println("VYTVÁRAM PRVÝ KRÁT STROM, PRVOK: "+data);
            this.root = new RedBlackNode(data);
            root.color = RED;
            // BUĎ
            // root.color = BLACK;
            // LEBO ROOT MA BYT CIERNY
            // ALEBO KONTROLA
            controlRedBlack(root);

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
                ancestor.left = new RedBlackNode(data);
                ancestor.left.ancestor = ancestor;

                actual = ancestor.left; // ACTUAL TERAZ UKAZUJE NA NOVO PRIDANÝ PRVOK
            }
            //(data > ancestor.data)
            else{
                ancestor.right = new RedBlackNode(data);
                ancestor.right.ancestor = ancestor;

                actual = ancestor.right; // ACTUAL TERAZ UKAZUJE NA NOVO PRIDANÝ PRVOK
            }

            // SEM KONTROLA A VYVAŽENIE RED BLACK STROMU
            //System.out.println("## "+ actual.data);
            actual.color = RED; // PRIDANÝ PRVOK ZAFARBÍM NA ČERVENO
            controlRedBlack(actual);   // SKONTROLUJE PRAVIDLA PRE RED BLACK STROM, NA PRIDANOM NODE
        }//end ELSE
    }
    private void controlRedBlack(RedBlackNode node){
        // PARENT = ANCESTOR
        RedBlackNode ancestor = node.ancestor;

        // PRIPAD 1: Ancestor je null, dostali sme sa do root, koniec rekurzie
        // Nový uzol je koreň
        if (ancestor == null) {
             node.color = BLACK;
            return;
        }

        // VLOZENY JE CERVENY, PARENT MUSI BYT CIERNY, AK JE, VSETKO OK
        // ANCESTOR JE CIERNY, PRIDANY JE CERVENY
        if (ancestor.color == BLACK) {
            return;
        }

        // ODTIAL, ANCESTOR JE CERVENY (PROBLEM = PRIDANY A AJ ANCESTOR JE CERVENY)
        RedBlackNode grandparent = ancestor.ancestor;

        // PRIPAD 2: GRANDPARENT JE NULL,= ANCESTOR JE ROOT;
        if (grandparent == null) {
            // KEDZE TATO METÓDA SA VOLÁ LEN NA CERVENYCH NODEoch (bud novo vložených alebo na grandparent(red))
            // STAČÍ PREFARBIŤ ANCESTOR (v tomto prípade ROOT) NA ČIERNO
            ancestor.color = BLACK;
            return;
        }

        // UNCLE JE DIEŤA GRANDPARENTA (nie actual.ancestor, ale na opačnej strane (left/right))
        RedBlackNode uncle = getUncle(ancestor);

        // PRÍPAD 3: UNCLE je ČERVENÝ ==> prefarbiť ANCESTOR, GRANDPARENT a UNCLE
        if (uncle != null && uncle.color == RED) {
            ancestor.color = BLACK;
            grandparent.color = RED;
            uncle.color = BLACK;
            // VOLÁM REKURZÍVNU FUNKCIU PRE GRANDPARENT
            // MOŽE BYŤ ROOT ALEBO MÁ ČERVENÉHO RODIČA, sú potrebné korekcie (vyváženosti, pravidla RED-BLACK)
            controlRedBlack(grandparent);
        }

        // ANCESTOR JE LEFT CHILD GRANDPARENTa
        else if (ancestor == grandparent.left) {
            // PRÍPAD 4a: UNCLE JE ČIERNY, a NOVY NODE JE GRANDPARENT.left.right (TROJUHOLNIK)
            if (node == ancestor.right) {
                rotateLeft(ancestor);   // ROTUJEM ANCESTOR DO OPAĆNEJ STRANY AKO JE ULOZENY NODE

                // ANCESTOR BUDE UKAZOVAŤ NA NOVÝ ROOT ROTOVANÉHO PODSTROMU
                ancestor = node;
            }

            // PRÍPAD 5a: UNCLE JE ČIERNY, a NOVY NODE JE GRANDPARENT.left.left (LINE)
            rotateRight(grandparent);   // ROTUJEM GRANDPARENT DO OPAČNEJ STRANY AKO JE ULOZENY NODE + RECOLOR

            // RECOLOR povodneho ANCESTORA a GRANDPARENTa
            ancestor.color = BLACK;
            grandparent.color = RED;
        }

        // ANCESTOR JE RIGHT CHILD GRANDPARENTa
        else {
            // PRÍPAD 4b: UNCLE JE ČIERNY, a NOVY NODE JE GRANDPARENT.right.left (TROJUHOLNIK)
            if (node == ancestor.left) {
                rotateRight(ancestor);  // ROTUJEM ANCESTOR DO OPAČNEJ STRANY AKO JE ULOZENY NODE

                // ANCESTOR BUDE UKAZOVAŤ NA NOVÝ ROOT ROTOVANÉHO PODSTROMU
                ancestor = node;
            }

            // PRÍPAD 5B: UNCLE JE ČIERNY, a NOVY NODE JE GRANDPARENT.right.right (LINE)
            rotateLeft(grandparent);

            // RECOLOR povodneho ANCESTORA a GRANDPARENTa
            ancestor.color = BLACK;
            grandparent.color = RED;
        }

    }

    // METÓDA GET UNCLE PRACUJE S ANCESTOROM (rodičom NODEu)
    // NÁJDE .ANCESTOR.ANCESTOR = GRANDPARENT
    // VRÁTI opačné dieťa GRANDPARENTa (ak je ancestor  granparent.left  vráti grandparent.right)
    private RedBlackNode getUncle(RedBlackNode ancestor) {
        RedBlackNode grandparent = ancestor.ancestor;
        if (grandparent.left == ancestor) {
            return grandparent.right;
        } else if (grandparent.right == ancestor) {
            return grandparent.left;
        }
        else{
            return null;
        }
    }


    /// ROTÁCIE ///
    private void rotateRight(RedBlackNode node) {
        RedBlackNode ancestor = node.ancestor;
        RedBlackNode leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.ancestor = node;
        }

        leftChild.right = node;
        node.ancestor = leftChild;

        // NAPOJENIE STROMU NA ZVYŠOK
        // NODE         je OLD LOCAL ROOT
        // LEFTCHILD    je NEW LOCAL ROOT

        if(ancestor == null) {   // SME V ROOTE
            root = leftChild;
        } else if (ancestor.left == node) {     // NODEy na rotovanie sú LEFT
            ancestor.left = leftChild;
        } else if (ancestor.right == node) {    // NODEy na rotovanie sú RIGHT
            ancestor.right = leftChild;
        }

        if (leftChild != null) {
            leftChild.ancestor = ancestor;
        }
    }

    private void rotateLeft(RedBlackNode node) {
        RedBlackNode ancestor = node.ancestor;
        RedBlackNode rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.ancestor = node;
        }

        rightChild.left = node;
        node.ancestor = rightChild;

        // NAPOJENIE STROMU NA ZVYŠOK
        // NODE         je OLD LOCAL ROOT
        // LEFTCHILD    je NEW LOCAL ROOT

        if(ancestor == null) {   // SME V ROOTE
            root = rightChild;
        } else if (ancestor.left == node) {     // NODEy na rotovanie sú LEFT
            ancestor.left = rightChild;
        } else if (ancestor.right == node) {    // NODEy na rotovanie sú RIGHT
            ancestor.right = rightChild;
        }

        if (rightChild != null) {
            rightChild.ancestor = ancestor;
        }
    }
    /// END ROTÁCIE ///

    /// DELETE ///
    // FUNKCIA NAPOJÍ SPRÁVNE ANCESTORY z old child na new child
    private void transplant(RedBlackNode ancestor, RedBlackNode oldChild, RedBlackNode newChild) {
        if (ancestor == null) {
            root = newChild;
        } else if (ancestor.left == oldChild) {
            ancestor.left = newChild;
        } else if (ancestor.right == oldChild) {
            ancestor.right = newChild;
        }

        if (newChild != null) {
            newChild.ancestor = ancestor;
        }
    }

    // VRÁTI NAJVIAC LAVÝ PRVOK ktorý nájde od zadaného nodeu
    public RedBlackNode mostLeftChild(RedBlackNode node){
        RedBlackNode actual = node;

        while( actual.hasLeftChild() ){
            actual = actual.left;
        }
        return actual;
    }

    // SURODENEC (SIBLING) JE DRUHE DIEŤA ANCESTORA AKO JE AKTUALNY NODE
    private RedBlackNode getSibling(RedBlackNode node) {
        if(node == null || node.ancestor == null){return null;}

        RedBlackNode ancestor = node.ancestor;
        if (node == ancestor.left) {
            return ancestor.right;
        } else if (node == ancestor.right) {
            return ancestor.left;
        }
        return null;
    }

    public void deleteNode(int number) {

        RedBlackNode node = searchNode(number);
        // AK NODE NIEJE V STROME, VYMAZANIE SKONČILO
        if (node == null) {
            return;
        }

        // NODE UKAZUJE NA PRVOK KTORÝ CHCEME VYMAZAŤ

        RedBlackNode movedUpNode;       // sem ulozime NODE, v ktorom zacnem opravovať RedBlack vlastnosti, po vymazani node
        boolean deletedNodeColor;       // farba vymazaneho node

        // NODE MÁ IBA LEFT CHILD
        if(node.hasLeftChild() && node.right == null) {
            transplant(node.ancestor, node, node.left);

            movedUpNode = node.left;    // moved-up node
            deletedNodeColor = node.color;
        }
        // NODE MÁ IBA RIGHT CHILD
        else if(node.left == null && node.hasRightChild()) {
            transplant(node.ancestor, node, node.right);

            movedUpNode = node.right;   // moved-up node
            deletedNodeColor = node.color;
        }

        // NODE NEMÁ POTOMKOV
        // NODE == RED  --> iba vymaž
        // NODE == BLACK --> nahrať NIL nodeom (od neho ešte fixAfterDelete() )

        else if(node.left == null && node.right == null){
            RedBlackNode newChild = node.color == BLACK ? new NilNode() : null;
            transplant(node.ancestor, node, newChild);

            movedUpNode = newChild;
            deletedNodeColor = node.color;
        }

        // NODE MÁ 2 DETI (AJ LEFT AJ RIGHT)
        else {
            // NÁJDE NAMENŠÍ PRVOK Z PRAVÉHO PODSTROMU
            RedBlackNode mostLeftNode = mostLeftChild(node.right);

            // SKOPÍRUJE iba data
            node.data = mostLeftNode.data;

            // DELETE MOST-LEFT-NODE        // MOZE MAT LEN PRAVE DIETA ALEBO ZIADNE
            if(mostLeftNode.hasRightChild()){
                transplant(mostLeftNode.ancestor, mostLeftNode, mostLeftNode.right);
                movedUpNode = mostLeftNode.right; // moved-up node
            }
            else{   // MOST-LEFT-CHILD NEMA ZIADNE DIETA
                RedBlackNode newChild = mostLeftNode.color == BLACK ? new NilNode() : null;
                transplant(mostLeftNode.ancestor, mostLeftNode, newChild);
                movedUpNode = newChild;
            }
            // END DELETE MOST-LEFT-NODE

            deletedNodeColor = mostLeftNode.color;
        }

        // POKIAL JE deletedNodeColor == RED    --> VŠETKO JE OK
        // POKIAL JE deletedNodeColor == BLACK  --> PROBLEM !
        if (deletedNodeColor == BLACK) {
            fixAfterDelete(movedUpNode);

            // VYMAZANIE dočasného NIL NODEU
            if (movedUpNode.getClass() == NilNode.class) {
                transplant(movedUpNode.ancestor, movedUpNode, null);
            }
        }
    }


    private void fixAfterDelete(RedBlackNode node) {

        // PRÍPAD 1: NODE JE ROOT, KONIEC REKURZIE
        if (node == root) {
            node.color = BLACK;
            return;
        }
        //////////////////////////////////////////

        // SIBLING JE DRUHE DIEŤA ANCESTORA AKO JE AKTUALNY NODE
        // ZÍSKAM sibling NODE (druhe dieťa ancestora node)
        RedBlackNode sibling = getSibling(node);    // získam súrodenca vymazaného uzla

        // PRÍPAD 2: SIBLING JE RED
        if (sibling.color == RED) {
            //handleRedSibling(node, sibling);
                // PREFARBENIE
                sibling.color = BLACK;
                node.ancestor.color = RED;
                // ROTÁCIA PODĽA TOHO, NA KTOREJ STRANE SA NACHÁDZA VYMAZANÝ (movedUpNode) NODE
                if (node == node.ancestor.left) {
                    rotateLeft(node.ancestor);
                } else {
                    rotateRight(node.ancestor);
                }
            sibling = getSibling(node); // získanie nového súrodenca po rotácii
            // node je stale ten isty co bol, node je movedUpNode
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////

        // PRÍPAD 3 a 4
        // SIBLING = BLACK, (sibling.left a sibling.right = BLACK)
        //  OBAJA SYNOVIA SÚRODENCA MAJÚ ČIERNU FARBU         //  AJ NULL je BLACK
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;    // zmením farbu súrodenca na červenú

            // PRÍPAD 3: SIBLING, DETI SIBLING = BLACK, ANCESTOR NODE = RED
            // PRÍPAD 3: AK FARBA RODIČA (movedUpNode) JE RED (sibling aj deti sibling sú BLACK)
            if (node.ancestor.color == RED) {
                node.ancestor.color = BLACK;
            }
            // PRÍPAD 4: SIBLING, DETI SIBLING = BLACK, ANCESTOR NODE = BLACK
            else {
                fixAfterDelete(node.ancestor);    // rekurzívna kontrola na rodiča
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////

        // PRÍPAD 5 a 6
        // SIBLING = BLACK, (aspoň jedno dieťa je RED)
        //  sibling má aspoň jedno červené dieťa
        else {
            boolean nodeIsLeftChild = (node == node.ancestor.left); // určenie, na ktorej strane sa uzol nachádza

            // PRÍPAD 5: SIBLING = BLACK, jedno dieťa SIBLING = BLACK
            // ---------> PREFARBENIE SIBLING, DETI SIBLING
            // ---------> ROTÁCIA SIBLING (aby bolo červené dieťa na vonkajšej strane nového súrodenca)
            // vonkajší synovec (dieťa sibling) = BLACK

            // ak je uzol ľavý syn a súrodenec má červeného potomka vľavo
            if (nodeIsLeftChild && isBlack(sibling.right)) {
                sibling.left.color = BLACK;
                sibling.color = RED;
                rotateRight(sibling);
                sibling = node.ancestor.right;
            }
            // ak je uzol pravý syn a súrodenec má červeného potomka vpravo
            else if (!nodeIsLeftChild && isBlack(sibling.left)) {
                sibling.right.color = BLACK;
                sibling.color = RED;
                rotateLeft(sibling);
                sibling = node.ancestor.left;
            }
            /////////////////////////////////////////////////////////////

            // PRÍPAD 6: SIBLING = BLACK, jedno dieťa SIBLING = RED
            // ---------> PREFARBENIE SIBLING, RODIČA VYMAZAVANÉHO, DETI SIBLING
            // ---------> ROTÁCIA RODIČA
            // vonkajší synovec (dieťa sibling) = RED

            // zmena farby súrodencov a predka
            sibling.color = node.ancestor.color;
            node.ancestor.color = BLACK;

            // ak je uzol ľavý syn a súrodenec má červeného potomka vpravo
            if (nodeIsLeftChild) {
                sibling.right.color = BLACK;
                rotateLeft(node.ancestor);
            }
            // ak je uzol pravý syn a súrodenec má červeného potomka vľavo
            else {
                sibling.left.color = BLACK;
                rotateRight(node.ancestor);
            }
            /////////////////////////////////////////////////////////////////////
        }
    }


    private boolean isBlack(RedBlackNode node) {
        return node == null || node.color == BLACK;
    }

    // DOČASNY null node pre vymazanie ak MOST-LEFT-CHILD NEMA ZIADNE DIETA (aby sme od NIL nodu mohli kontrolovať RB pravidla )
    private static class NilNode extends RedBlackNode {
        private NilNode() {
            super(0);
            this.color = BLACK;
        }
    }

    /// END DELETE ///



}

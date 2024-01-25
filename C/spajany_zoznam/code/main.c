// Adam Vrabel - FIIT STU - ZPRPR2
// PROJEKT 2

//VSTUPY A VYSTUPY
#include <stdio.h>
//PRACA S RETAZCAMI
#include <string.h>
//PRACA S PAMATOU
#include <stdlib.h>
//PRE KONVERZIU ZNAKOV tolower()
#include <ctype.h>

struct autor {
    char meno[201];
    char priezvisko[201];
    struct autor *next;
};

struct osoba {
    int id;
    char nazov_prispevku[151];  // MAX 150 ZNAKOV
    struct autor * mena_autorov;
    char typ_prezentovania[3];  // MAX 2 ZNAKY { PD, UD, PP, UP }
    char cas_prezentovania[5];  // MAX 4 ZNAKY
    char datum[9];              // MAX 8 ZNAKOV

    struct osoba *next;
};


int spocitaj_kolko_mien(char *kontroluj, int dlzka){

    if(kontroluj[0] == '\0'){   // AK JE STRING PRAZDNY VRATI POCET 0
        return 0;
    }
    int pocet_mien = 1;
    for(int i = 0; i < dlzka; i++){
        if (kontroluj[i] == '#'){
            pocet_mien++;
        }
    }

    return pocet_mien;          // INAK SPOCITA KOLKO JE #
}


struct osoba* prikaz_n(struct osoba * prva){

    // AK ZOZNAM EXISTOVAL --> UVOLNIT PAMAT

    // AK NIEJE MOZNE OTVORIT SUBOR --> VYPIS "Zaznamy neboli nacitane!" a UKONCI FUNKCIU

    // AK NACITA ZAZNAMY --> "Nacitalo sa X zaznamov"

    // UKAZOVATEL NA SUBOR
    FILE *subor = NULL;

    int pocet_zaznamov = 0;
    
    // POMOCNE
    struct osoba *pom, *temp;

    if(prva != NULL){
        //printf(">>>> Nie je prazdny, musim dealokovat !\n");

        // UVOLNENIE ZOZNAMU //
        struct osoba *temp_inside_del;    
        struct autor *temp_inside_del_autor;

        struct osoba *tmp_cpy_first = NULL;
        tmp_cpy_first = prva;
        // while(prva_osoba != NULL)
        // {
        //     temp_inside_del = prva_osoba;           // DO temp_inside ULOZIM AKTUALNU POLOZKU
        //     prva_osoba = prva_osoba->next;      // prva UZ UKAZUJE NA DALSIU POLOZKU
        //     free(temp_inside_del);                  // UVOLNIM
        // }

        while(tmp_cpy_first != NULL)
        {
            temp_inside_del = tmp_cpy_first;           // DO temp_inside ULOZIM AKTUALNU POLOZKU
            while(temp_inside_del->mena_autorov != NULL){
                temp_inside_del_autor = temp_inside_del->mena_autorov;
                temp_inside_del->mena_autorov = temp_inside_del->mena_autorov->next;
                free(temp_inside_del_autor);
            }
            tmp_cpy_first = tmp_cpy_first->next;      // prva UZ UKAZUJE NA DALSIU POLOZKU
            free(temp_inside_del);                  // UVOLNIM
        }
        // UVOLNENIE ZOZNAMU //
        // KONIEC DEALOKACIE V n-ku
    }
    
    //KONTROLA CI UZ BOL OTVORENY SUBOR
    
    //OTVORENIE A KONTROLA OTVORENIA SUBORU
    if((subor = fopen("OrganizacnePodujatia2.txt", "r")) == NULL){       
        //printf("Neotvoreny subor\n\n");
        printf("Zaznamy neboli nacitane !\n");
        // VYPIS PRE PROJEKT >>>> Zaznamy neboli nacitane!
        return NULL;
    }
    else{   // AK USPESNE OTVORI SUBOR
        //printf("PODARILO SA OTVORIT SUBOR\n");

        //////// VYPOCET KOLKO ZAZNAMOV JE V SUBORE
        char actual_line[300];
        //int pocet_zaznamov = 0;

        rewind(subor); // na zaciatok suboru
        while (!feof(subor)){
            //actual = fgetc(subor);
            fgets(actual_line, 300, subor);
            actual_line[strlen(actual_line)-1] = '\0'; // VYMAZE \n
            if( (strcmp(actual_line, "$$$")) == 0 ){
                //printf("je rovnaky\n");
                pocet_zaznamov++;
            }
        }
        //printf("POCET ZAZNAMOV = %d\n", pocet_zaznamov);
        rewind(subor);
        //////// KONIEC ... VYPOCET KOLKO ZAZNAMOV JE V SUBORE

        //////// NACITANIE UDAJOV ////////
        char delimiter[5]={0};               // PRE $$$
        char read_id[12]={0};                // VO VYSLEDKU 10 MIESTNE CELE CISLO DELITELNE 15
        int read_int_id = 0;                 // AK read_int_id == -666 tak nacitane cislo nieje delitelne 15                     
        char read_nazov_prispevku[152]={0};  // MAX 150 ZNAKOV
        char read_mena_autorov[202]={0};     // MAX 200 ZNAKOV
        char read_typ_prezentovania[4]={0};  // MAX 2 ZNAKY { PD, UD, PP, UP }  // AK NIEJE Z MNOZINY TAK STRING JE "NN"
        char read_cas_prezentovania[6]={0};  // MAX 4 ZNAKY
        char read_datum[10]={0};

        // PRE NACITANIE AUTOROV
        struct autor * autor_prvy = NULL;
        struct autor * temp_autor, * pom_autor;
        char * token = NULL; // PRE ROZDELENIE MIEN podla #

        rewind(subor);
        for(int i = 0; i < pocet_zaznamov; i++){
            // NACITANIE $$$
            fgets(delimiter, 5, subor);
            delimiter[strlen(delimiter)-1] = '\0'; // VYMAZE \n

            // NACITANIE, KONVERZIA A KONTROLA ID
            fgets(read_id, 12, subor);
            read_id[strlen(read_id)-1] = '\0';  // VYMAZE \n
            read_int_id = atoi(read_id);        // KONVERZIA NA INT
            if( (read_int_id % 15) != 0 || (read_int_id >= 1000000000)){      // KONTROLA CI JE DELITELNE 15 alebo MENSIE AKO 10 MIEST
                read_int_id = -666;
            }

            // NACITANIE NAZOV PRISPEVKU
            fgets(read_nazov_prispevku, 152, subor);
            read_nazov_prispevku[strlen(read_nazov_prispevku)-1] = '\0'; // VYMAZE \n

            // NACITANIE MENA AUTOROV
            fgets(read_mena_autorov, 202, subor);
            read_mena_autorov[strlen(read_mena_autorov)-1] = '\0'; // VYMAZE \n

            int pocet_mien = 0;
            pocet_mien = spocitaj_kolko_mien(read_mena_autorov, strlen(read_mena_autorov)+1);  // VRATIM SI POCET AUTOROV
            //printf("Pocet mien: %d\n\n", pocet_mien);

            // NACITANIE TYP PREZENTOVANIA
            fgets(read_typ_prezentovania, 4, subor);
            read_typ_prezentovania[strlen(read_typ_prezentovania)-1] = '\0'; // VYMAZE \n
            if( !((strcmp(read_typ_prezentovania, "PD") == 0) || (strcmp(read_typ_prezentovania, "UD") == 0) || (strcmp(read_typ_prezentovania, "PP") == 0) || (strcmp(read_typ_prezentovania, "UP") == 0))){
                // NIE JE Z MNOZINY 
                strcpy(read_typ_prezentovania, "NN");
            }

            // NACITANIE CAS
            fgets(read_cas_prezentovania, 6, subor);
            read_cas_prezentovania[strlen(read_cas_prezentovania)-1] = '\0'; // VYMAZE \n

            // NACITANIE DATUM
            fgets(read_datum, 10, subor);
            read_datum[strlen(read_datum)-1] = '\0'; // VYMAZE \n

            /////////////////////////////////
            //// VYTVORENIE LINKED LISTU ////
            /////////////////////////////////
            if(i == 0){         // PRVY PRVOK ZOZNAMU
                prva = (struct osoba *)malloc(sizeof(struct osoba));
                // KONTROLA CI SA ALOKOVALO MIESTO
                if(prva == NULL){
                    //printf("Unable to allocate memory.");
                    return prva;
                }
                // ZAPIS DAT PRE PRVY PRVOK ZOZNAMU
                // ID
                prva->id = read_int_id;                
                // NAZOV PRISPEVKU
                strncpy(prva->nazov_prispevku, read_nazov_prispevku, 151);
                // MENA AUTOROV //////////////////////////////////////////////////////////////////////////////////////////////////

                //// MENA AUTOROV ////

                // struct autor * autor_prvy = NULL;
                // struct autor * temp_autor, * pom_autor;
                // char * token = NULL; // PRE ROZDELENIE MIEN podla #
                autor_prvy = NULL;
                temp_autor = NULL;
                pom_autor = NULL;
                token = NULL;


                for(int x = 0; x < pocet_mien; x++){    // VYTVORENIE SPAJANEHO ZOZNAMU PODLA POCTU AUTOROV
                    if(x == 0){
                        autor_prvy = (struct autor *)malloc(sizeof(struct autor));

                        if(autor_prvy == NULL){
                            //printf("Unable to allocate memory.");
                            return NULL;
                        }

                        // UDAJE //
                        token = strtok(read_mena_autorov, "#");
                        //printf(">>> %s\n", token);
                        //sscanf(token, "%s %s", autor_prvy->meno, autor_prvy->priezvisko);
                        sscanf(token, "%s %[^\n]", autor_prvy->meno, autor_prvy->priezvisko);
                        // UDAJE //

                        // strcpy(autor_prvy->meno, "MENOOOO");
                        // strcpy(autor_prvy->priezvisko, "Priezvisko");
                        autor_prvy->next = NULL;
                        temp_autor = autor_prvy;
                    }
                    if(x > 0){
                        pom_autor = (struct autor *)malloc(sizeof(struct autor));

                        if(pom_autor == NULL){
                            //printf("Unable to allocate memory.");
                            return NULL;
                        }

                        // UDAJE //
                        token = strtok(NULL, "#");
                        //printf(">>> %s\n", token);
                        //sscanf(token, "%s %s", pom_autor->meno, pom_autor->priezvisko);
                        sscanf(token, "%s %[^\n]", pom_autor->meno, pom_autor->priezvisko);
                        // UDAJE //

                        // strcpy(pom_autor->meno, "druhe meno ou jeee");
                        // strcpy(pom_autor->priezvisko, "secondove priezvisko JESS !!");
                        
                        pom_autor->next = NULL; // Make sure new node points to NULL 

                        temp_autor->next = pom_autor;   // PREPOJIM PRVY S AKTUALNYM (a dalej)
                        temp_autor = temp_autor->next;  // POSUNIEM NA next
                    }
                    
                } // KONIEC FOR





                //// KONIEC MENA AUTOROV ////
                prva->mena_autorov = autor_prvy;    // PRVEHO AUTORA PRIPOJIM DO LINKED LISTU
                    // DORABAM AUTOROV //////////////////////////////////////////////////////////////////////////////////////////////////
                // TYP
                strncpy(prva->typ_prezentovania, read_typ_prezentovania, 3);
                // CAS
                strncpy(prva->cas_prezentovania, read_cas_prezentovania, 5);
                // DATUM
                strncpy(prva->datum, read_datum, 9);
                ///////////////////////////////////
                prva->next = NULL;  // NASTAVENIE ukazovatela next NA NULL
                temp = prva; // SKOPIROVANIE ADRESY
            }
            else if(i > 0){     // OSTATNE PRVKY ZOZNAMU
                pom = (struct osoba *)malloc(sizeof(struct osoba));
                // KONTROLA CI SA ALOKOVALO MIESTO
                if(pom == NULL){
                    //printf("Unable to allocate memory.");
                    return NULL;
                }
                // ZAPIS DAT PRE PRVY PRVOK ZOZNAMU
                // ID
                pom->id = read_int_id;                
                // NAZOV PRISPEVKU
                strncpy(pom->nazov_prispevku, read_nazov_prispevku, 151);

                // MENA AUTOROV //////////////////////////////////////////////////////////////////////////////////////////////////
                //// MENA AUTOROV ////

                autor_prvy = NULL;
                temp_autor = NULL;
                pom_autor = NULL;
                token = NULL;

                for(int x = 0; x < pocet_mien; x++){    // VYTVORENIE SPAJANEHO ZOZNAMU PODLA POCTU AUTOROV
                    if(x == 0){
                        autor_prvy = (struct autor *)malloc(sizeof(struct autor));

                        if(autor_prvy == NULL){
                            //printf("Unable to allocate memory.");
                            return NULL;
                        }

                        // UDAJE //
                        token = strtok(read_mena_autorov, "#");
                        //printf(">>> %s\n", token);
                        //sscanf(token, "%s %s", autor_prvy->meno, autor_prvy->priezvisko);
                        sscanf(token, "%s %[^\n]", autor_prvy->meno, autor_prvy->priezvisko);
                        // UDAJE //

                        // strcpy(autor_prvy->meno, "MENOOOO");
                        // strcpy(autor_prvy->priezvisko, "Priezvisko");
                        autor_prvy->next = NULL;
                        temp_autor = autor_prvy;
                    }
                    if(x > 0){
                        pom_autor = (struct autor *)malloc(sizeof(struct autor));

                        if(pom_autor == NULL){
                            //printf("Unable to allocate memory.");
                            return NULL;
                        }

                        // UDAJE //
                        token = strtok(NULL, "#");
                        //printf(">>> %s\n", token);
                        //sscanf(token, "%s %s", pom_autor->meno, pom_autor->priezvisko);
                        sscanf(token, "%s %[^\n]", pom_autor->meno, pom_autor->priezvisko);
                        // UDAJE //

                        // strcpy(pom_autor->meno, "druhe meno ou jeee");
                        // strcpy(pom_autor->priezvisko, "secondove priezvisko JESS !!");
                        
                        pom_autor->next = NULL; // Make sure new node points to NULL 

                        temp_autor->next = pom_autor;   // PREPOJIM PRVY S AKTUALNYM (a dalej)
                        temp_autor = temp_autor->next;  // POSUNIEM NA next
                    }
                    
                } // KONIEC FOR

                //// KONIEC MENA AUTOROV ////
                pom->mena_autorov = autor_prvy;
                    // DORABAM AUTOROV //////////////////////////////////////////////////////////////////////////////////////////////////
                // TYP
                strncpy(pom->typ_prezentovania, read_typ_prezentovania, 3);
                // CAS
                strncpy(pom->cas_prezentovania, read_cas_prezentovania, 5);
                // DATUM
                strncpy(pom->datum, read_datum, 9);
                ///////////////////////////////////

                pom->next = NULL;   // NASTAVENIE ukazovatela next NA NULL
                temp->next = pom;   // Link previous node with pom
                temp = temp->next;  // Make current node as previous node
            }
            ///// KONIEC VYTVORENIE LINKED LISTU /////

        }
        //////// NACITANIE UDAJOV ////////

        // ZATVORENIE SUBORU
        fclose(subor);
        subor = NULL;
    }
    printf("Nacitalo sa %d zaznamov\n", pocet_zaznamov);
    return prva;
}

void vypis(struct osoba *prva){
    struct osoba *temp;
    struct autor *temp_autor;

    // AK JE ZOZNAM PRAZDNY
    if(prva == NULL){
        printf("Prazdny zoznam zaznamov.\n");
        return;
    }
    
    temp = prva;
    

    int counter = 1;
    int counter_a = 1;
    while(temp != NULL)
    {
        temp_autor = temp->mena_autorov;
        counter_a = 1;

        printf("%d.\n", counter++);
        //counter++;
        printf("ID cislo : %d\n", temp->id); // Print data of current osoba
        printf("Nazov prispevku: %s\n", temp->nazov_prispevku);
        // MENA MI TREBA DOROBIT
            printf("Mena autorov:\n");
            while(temp_autor != NULL){
                printf("\t%d: %s %s\n", counter_a++, temp_autor->meno, temp_autor->priezvisko);
                temp_autor = temp_autor->next;
            }

        printf("Typ prezentovania: %s\n", temp->typ_prezentovania);
        printf("Cas prezentovania: %s\n", temp->cas_prezentovania);
        printf("Datum: %s\n", temp->datum);
        

        temp = temp->next;                 // Move to next osoba
    }
}

struct osoba* prikaz_a(struct osoba * prva){
    struct osoba *temp;
    temp = prva;

    int is_okay = 0;    //=0 WHILE ITERUJE // =1 KONCI

    int new_id = 0;
    char new_typ[3]={0};
    char old_typ[3]={0};

    
    while(is_okay == 0){
        scanf("%d %s", &new_id, new_typ);
        fflush(stdin);
        if(((new_id % 15) == 0) && ((strcmp(new_typ, "PD")==0) || (strcmp(new_typ, "UD")==0) || (strcmp(new_typ, "PP")==0) || (strcmp(new_typ, "UP")==0)) ){
            is_okay = 1;
        }
        else{
            printf("Zadane udaje niesu korektne, zadaj novy retazec: ");
        }
    }
    //printf("\nOKAY\n");
    //printf("%d -- %s\n", new_id, new_typ);

    // VYHLADAT KTORY PRVOK ZO SPAJANEHO ZOZNAMU MA ROVNAKE ID
    // SKOPIROVAT STARY TYP, PREPISAT NA NOVY 
    // UROBIT VYPIS " Prispevok s nazvom <nazov> sa bude prezentovar <NOVY TYP> [<stary typ>] "

    while(temp != NULL){
        if( temp->id == new_id ){
            strcpy(old_typ, temp->typ_prezentovania);
            strcpy(temp->typ_prezentovania, new_typ);
            printf("Prispevok s nazvom %s sa bude prezentovat %s [%s]\n", temp->nazov_prispevku, temp->typ_prezentovania, old_typ);
        }
        temp = temp->next;                 // Move to next osoba
    }


    return prva; // VRATIM UKAZOVATEL NA ZACIATOK AKTUALIZOVANEHO ZOZNAMU
}

void prizkaz_h(struct osoba * prva ){

    struct osoba *temp;
    struct autor *temp_autor;
    int counter = 1;
    int counter_a = 1;

    temp = prva;

    char search_typ[3] = {0};

    scanf("%2s", search_typ);
    fflush(stdin);

    if(prva == NULL){   // PRAZDNY SPAJANY ZOZNAM
        //printf("Prazdny zoznam zaznamov.\n");
        printf("Pre typ %s nie su ziadne zaznamy.\n", search_typ);
        return;
    }
    while(temp != NULL){
        if(strcmp(search_typ, temp->typ_prezentovania) == 0){
            //printf(">>>je zhodne\n");

            temp_autor = temp->mena_autorov;
            counter_a = 1;

            printf("%d.\n", counter++);
            //counter++;
            printf("ID cislo: %d\n", temp->id);
            printf("Nazov prispovku: %s\n", temp->nazov_prispevku);
                printf("Mena autorov:\n");
                while(temp_autor != NULL){
                    printf("\t%d: %s %s\n", counter_a++, temp_autor->meno, temp_autor->priezvisko);
                    temp_autor = temp_autor->next;
                }

            printf("Typ prezentovania: %s\n", temp->typ_prezentovania);
            printf("Cas prezentovania: %s\n", temp->cas_prezentovania);
            printf("Datum: %s\n", temp->datum);

        } // KONIEC IF-u

        temp = temp->next;
    } // KONIEC WHILE-u

    if(counter == 1){   // AK NIEJE ZIADNY ZAZNAM ZHODNY
        printf("Pre typ %s nie su ziadne zaznamy.\n", search_typ);
    }

}

int spocitajZoznam(struct osoba * prva){
    struct osoba * temp;
    temp = prva;

    int pocet_zaznamov = 0;
    while (temp != NULL){
        pocet_zaznamov++;
        temp = temp->next;
    }
    
    return pocet_zaznamov;
}

struct osoba * prikaz_p(struct osoba * prva){
    struct osoba * p_prva;
    p_prva = prva;
    // AK JE LINKED LIST PRAZDNY - tak vytvori linked list
    //printf("ZADAJ CISLO KAM CHCES NAPOJIT NOVY ZAZNAM\n");

    int kam = 0;

    scanf(" %d", &kam);
    fflush(stdin);

    // NACITANIE UDAJOV DO POMOCNYCH PREMENNYCH
    int input_id = 0;                 
    char input_nazov_prispevku[151]={0};  // MAX 150 ZNAKOV
    char input_mena_autorov[201]={0};     // MAX 200 ZNAKOV
    char input_typ_prezentovania[3]={0};  // MAX 2 ZNAKY { PD, UD, PP, UP }  // AK NIEJE Z MNOZINY TAK STRING JE "NN"
    char input_cas_prezentovania[5]={0};  // MAX 4 ZNAKY
    char input_datum[9]={0};    

    printf("ID: ");
    scanf(" %d", &input_id);    fflush(stdin);
    printf("NAZOV PRISPEVKU: ");
    scanf(" %150[^\n]s", input_nazov_prispevku);    fflush(stdin);
    printf("MENA AUTOROV: ");
    scanf(" %200[^\n]s", input_mena_autorov);   fflush(stdin);
    printf("TYP: ");
    scanf(" %2[^\n]s", input_typ_prezentovania);    fflush(stdin);
    printf("CAS: ");
    scanf(" %4[^\n]s", input_cas_prezentovania);    fflush(stdin);
    printf("DATUM: ");
    scanf(" %8[^\n]s", input_datum);    fflush(stdin);
    //

    //// KOLKO AUTOROV ////
    int pocet_mien = 0;
    pocet_mien = spocitaj_kolko_mien(input_mena_autorov, strlen(input_mena_autorov)+1);  // VRATIM SI POCET AUTOROV
    //// KOLKO AUTOROV ////


    // VYTVORENIE DOCASNEHO (noveho) ZAZNAMU //
    struct osoba *new = NULL;

    ////////////////////////////////////////////
        
        new = (struct osoba *)malloc(sizeof(struct osoba));
        // KONTROLA CI SA ALOKOVALO MIESTO
        if(new == NULL){
            //printf("Unable to allocate memory.");
            return NULL;
        }
        // ZAPIS DAT PRE PRVY PRVOK ZOZNAMU
        // ID
        new->id = input_id;                
        // NAZOV PRISPEVKU
        strncpy(new->nazov_prispevku, input_nazov_prispevku, 151);
        
        //// MENA AUTOROV ////
        struct autor * autor_prvy = NULL;
        struct autor * temp_autor, * pom_autor;
        char * token = NULL; // PRE ROZDELENIE MIEN podla #
        // autor_prvy = NULL;
        // temp_autor = NULL;
        // pom_autor = NULL;
        // token = NULL;

        for(int x = 0; x < pocet_mien; x++){    // VYTVORENIE SPAJANEHO ZOZNAMU PODLA POCTU AUTOROV
            if(x == 0){
                autor_prvy = (struct autor *)malloc(sizeof(struct autor));

                if(autor_prvy == NULL){
                    //printf("Unable to allocate memory.");
                    return NULL;
                }

                // UDAJE //
                token = strtok(input_mena_autorov, "#");
                //printf(">>> %s\n", token);
                //sscanf(token, "%s %s", autor_prvy->meno, autor_prvy->priezvisko);
                sscanf(token, "%s %[^\n]", autor_prvy->meno, autor_prvy->priezvisko);
                // UDAJE //

                // strcpy(autor_prvy->meno, "MENOOOO");
                // strcpy(autor_prvy->priezvisko, "Priezvisko");
                autor_prvy->next = NULL;
                temp_autor = autor_prvy;
            }
            if(x > 0){
                pom_autor = (struct autor *)malloc(sizeof(struct autor));

                if(pom_autor == NULL){
                    //printf("Unable to allocate memory.");
                    return NULL;
                }

                // UDAJE //
                token = strtok(NULL, "#");
                //printf(">>> %s\n", token);
                //sscanf(token, "%s %s", pom_autor->meno, pom_autor->priezvisko);
                sscanf(token, "%s %[^\n]", pom_autor->meno, pom_autor->priezvisko);
                // UDAJE //

                // strcpy(pom_autor->meno, "druhe meno ou jeee");
                // strcpy(pom_autor->priezvisko, "secondove priezvisko JESS !!");
                
                pom_autor->next = NULL;  

                temp_autor->next = pom_autor;   // PREPOJIM PRVY S AKTUALNYM (a dalej)
                temp_autor = temp_autor->next;  // POSUNIEM NA next
            }
            
        } // KONIEC FOR

        //// KONIEC MENA AUTOROV ////
        new->mena_autorov = autor_prvy;    // PRVEHO AUTORA PRIPOJIM DO LINKED LISTU

        // TYP
        strncpy(new->typ_prezentovania, input_typ_prezentovania, 3);
        // CAS
        strncpy(new->cas_prezentovania, input_cas_prezentovania, 5);
        // DATUM
        strncpy(new->datum, input_datum, 9);
        ///////////////////////////////////
        new->next = NULL;  // NASTAVENIE ukazovatela next NA NULL
        ////////////////////////////////////////////

    // VYTVORENIE DOCASNEHO ZAZNAMU //



    if(p_prva == NULL){   // SPAJANY ZOZNAM JE PRAZDNY (nie je vytvoreny)
        // PRVE MIESTO V ZOZNAME (zadane cislo neberiem do uvahy)
        // ZOZNAM JE PRAZDNY TAK VRATIM JEDINY (a prvy) vytvoreny prvok
        return new;
    }
    if(p_prva != NULL){   // ZOZNAM UŽ JE VYTVORENY (ma minimalne 1 polozku)
        // struct osoba * temp;
        // temp = p_prva;
        
        // AK kam == 1 --> NA ZACIATOK
        if(kam == 1){
            new->next = p_prva;
            return new;
        }
        int kolkoZaznamov = 0;
        kolkoZaznamov = spocitajZoznam(prva);
        //printf("V zozname je %d poloziek\n",kolkoZaznamov);

        // AK kam > pocet zaznamov v structe (tak na koniec)
        if(kam > kolkoZaznamov){ // PRIDANIE NA KONIEC ZOZNAMU
            // DOSTANEM SA NA POSLEDNY PRVOK 
            // A TAM PRIPOJIM MOJ new
            struct osoba * posun;
            posun = prva;  // posun = p_prva;
            while(posun != NULL){   // PRECHADZANIE CELYM ZOZNAMOM

                if(posun->next == NULL){    // NAJDENIE POSLEDNEHO PRVKU V ZOZNAME
                    posun->next = new;          // posledny prvok->next prepojim s vytvorenym
                    return prva;                // VRATIM UKAZOVATEL NA ZACIATOK ZOZNAMU
                }
                posun = posun->next;
            }
        }

        // AK SOM V INTERVALE (DNU V ZOZNAME) TAK PRIDAJ NAPOJ A POSUN
        if ( (kam > 1) && (kam <= kolkoZaznamov) ){

            // NAJDEM SI ZAZNAM V PORADI kam-1 >>> -> next nastavim na new
            // new->next NASTAVIM na zaznam kam
            struct osoba * posun;
            posun = prva;  // posun = p_prva;

            struct osoba *before, *picked;

            int kdeSom = 1;
            while(posun != NULL){
                if ( kdeSom == (kam - 1) ){     // AK SOM NA O 1 MENSOM AKO SOM SI VYBRAL
                    before = posun;
                }
                if ( kdeSom == kam ){
                    picked = posun;
                }
                kdeSom++;
                posun = posun->next;
            }
            before->next = new;         // DO kam-1 -> next ZAPISEM ADRESU NOVEHO PRVKU
            new->next = picked;         // new-> next ZAPISEM ADRESU PRVKU na mieste kam
            return prva;
        }

    }

}

struct osoba * vymaz_Zaznam(struct osoba * prva, int kdeSom){
    struct osoba *posun;
    //posun = prva;

    // AK CHCEM ZMAZAT PRVY ZAZNAM
        if(kdeSom == 1){
            posun = prva;
            // UVOLNI AUTOROV A STRUCT
            struct osoba * temp_del;
            struct autor * temp_del_autor;
            temp_del = prva;           // ULOZIM POLOZKU KTORU CHEM UVOLNIT

            while(temp_del->mena_autorov != NULL){  // UVOLNENIE AUTOROV
                temp_del_autor = temp_del->mena_autorov;                // DO POMOCNEHO UKAZOVATELA ULOZIM KTORY CHCEM UVOLNIT
                temp_del->mena_autorov = temp_del->mena_autorov->next;  // POSUNIEM DALEJ
                free(temp_del_autor);
                temp_del_autor = NULL;
            }
                
            prva = prva->next;  // POSUNIEM UKAZOVATEL NA DALSI PRVOK ZOZNAMU (lebo 1. prvok chcem zmazat)
            free(temp_del);     // DEALOKUJEM MOJ PRVY PRVOK
            temp_del = NULL;

            return prva;    // MAM dealokovany 1. prvok a prva ukazuje uz na 2.
        }

    // AK CHCEM ZMAZAT POSLEDNY ZAZNAM alebo DNU V ZOZNAME
        else{
            int kolkoZaznamov = spocitajZoznam(prva);
            //printf("V zozname je %d poloziek\n",kolkoZaznamov);

            if(kdeSom == kolkoZaznamov){                            // ZMAZANIE POSLEDNEHO PRVKU V ZOZNAME

                posun = prva;  // posun = p_prva;
                struct osoba * before = NULL;
                while(posun != NULL){   // PRECHADZANIE CELYM ZOZNAMOM
                
                    if(posun->next == NULL){    // NAJDENIE POSLEDNEHO PRVKU V ZOZNAME
                        before->next = NULL;    // SPRAVIM KONIEC (predposledny uz ukazuje na NULL)

                        // UVOLNI AUTOROV A STRUCT
                        struct osoba * temp_del;
                        struct autor * temp_del_autor;
                        temp_del = posun;           // ULOZIM POSLEDNU POLOZKU KTORU CHEM UVOLNIT

                        while(temp_del->mena_autorov != NULL){  // UVOLNENIE AUTOROV
                            temp_del_autor = temp_del->mena_autorov;                // DO POMOCNEHO UKAZOVATELA ULOZIM KTORY CHCEM UVOLNIT
                            temp_del->mena_autorov = temp_del->mena_autorov->next;  // POSUNIEM DALEJ
                            free(temp_del_autor);
                            temp_del_autor = NULL;
                        }
                        free(temp_del);     // DEALOKUJEM MOJ PRVY PRVOK
                        temp_del = NULL;
                        // UVOLNI AUTOROV A STRUCT

                        return prva;                // VRATIM UKAZOVATEL NA ZACIATOK ZOZNAMU
                    }
                    before = posun;
                    posun = posun->next;
                }
            }
            else if( (kdeSom > 1) && (kdeSom < kolkoZaznamov) ){    // ZMAZANIE PRVKU V ZOZNAME
                
                struct osoba * before = NULL;

                int cisloZaznamu_akutalne = 0;
                posun = prva;

                while( posun != NULL ){
                    cisloZaznamu_akutalne++;

                    if(cisloZaznamu_akutalne == kdeSom){        // SOM NA POLOZKE KTORU CHCEM ZMAZAT
                        before->next = posun->next;     // PREPOJENIE PREDCHADZAJUCEHO S DALSIM (vynecham aktualny)
                        // UVOLNI posun
                        struct osoba * temp_del;
                        struct autor * temp_del_autor;
                        temp_del = posun;           // ULOZIM POLOZKU KTORU CHEM UVOLNIT

                        while(temp_del->mena_autorov != NULL){  // UVOLNENIE AUTOROV
                            temp_del_autor = temp_del->mena_autorov;                // DO POMOCNEHO UKAZOVATELA ULOZIM KTORY CHCEM UVOLNIT
                            temp_del->mena_autorov = temp_del->mena_autorov->next;  // POSUNIEM DALEJ
                            free(temp_del_autor);
                            temp_del_autor = NULL;
                        }
                        free(temp_del);     // DEALOKUJEM MOJ PRVY PRVOK
                        temp_del = NULL;

                        return prva;
                    }

                    before = posun;
                    posun = posun->next;
                }

            }
        } // KONIEC ELSE
    

        

        

        // AK CHCEM ZMAZAT ZAZNAM VNUTRI

    
    
    return prva;
}

struct osoba * prikaz_z(struct osoba * prva){

    char input_autor[201] = {0};
    scanf(" %200[^\n]s", input_autor);
    fflush(stdin);

    for(int i = 0; i < strlen(input_autor); i++){ // KONVERZIA NA MALE ZNAKY
        input_autor[i] = tolower(input_autor[i]);
    }

    // PREJST ZAZNAM PO ZAZNAME A AUTORA PO AUTOROVI
    // SPOJIT MENO + PRIEZVISKO a TOLOWER
    // KONTROLOVAT CI JE ROVNAKE AKO input_autor
    
    
    // NAJDENIE ZHODY MENA //
    struct osoba * posun;
    struct autor * posun_autor;
    
    //char temp_priezvisko[201]={0};
    char temp_priezvisko[402]={0};  // V STRUCTE MAM DEFINOVANE 201 PRE MENO A 201 PRE PRIEZVISKO   (niektory compiler hlasil chybu)
    int kdeSom = 0;
    int hotovo = 0; // 0 >> JE HOTOVO   // 1 >> NIE JE HOTOVO   (ci je zhodne priezvisko so zadanym)

    // while(hotovo){  // ITERUJE AK SU ZHODNE PRIEZVISKA, AZ POKIAL == 0
    //     printf("ajaaaj");
    //     hotovo = 0; // NASTAVIM NA 0 KONCI ITERACIU
    // }

    ////////////////////////////////
    do{ // AK NAJDE ZHODU nastavi na 1

        if(hotovo == 1){
            //VYMAZAT
            prva = vymaz_Zaznam(prva, kdeSom);
        }

        hotovo = 0; // UKONCI CYKLUS AK SA NENASTAVI NA 1
        kdeSom = 0;
        

        posun = prva;

        // AK NAJDEM ZHODU >>> hotovo = 1
        while( posun != NULL ){                         // PRECHADZANIE PO ZAZNAMOCH
        posun_autor = posun->mena_autorov;
        kdeSom++;
        
            while( posun_autor != NULL){
                sprintf(temp_priezvisko, "%s %s", posun_autor->meno, posun_autor->priezvisko);  // SPOJENIE MENA A PRIEZVISKA DO temp_priezvisko
                //printf("%s\n", temp_priezvisko);
                for(int i = 0; i < strlen(temp_priezvisko); i++){   // KONVERZIA CELEHO MENA AUTORA na male
                    temp_priezvisko[i] = tolower(temp_priezvisko[i]);
                }
                //printf(">>>%s\n", temp_priezvisko);
                if( strcmp(input_autor, temp_priezvisko) == 0 ){    // KONTROLA CI SU ROVNAKE AKO ZADANE
                    //printf(">>%s<<je rovnake\n", temp_priezvisko);
                    printf("Prispevok s nazvom %s bol vymazany.\n", posun->nazov_prispevku);
                    hotovo = 1; // NASLA SA ZHODA
                    break;
                }

                memset(temp_priezvisko, 0, 201);
                posun_autor = posun_autor->next;        // POSUN NA DALSIEHO AUTORA
            }
            if(hotovo == 1){
                break;
            }
        
        posun = posun->next;
        }

    }while(hotovo);
    ////////////////////////////////

    //posun = prva;
    // while( posun != NULL ){                         // PRECHADZANIE PO ZAZNAMOCH
    //     posun_autor = posun->mena_autorov;
    //     kdeSom++;
        
    //     while( posun_autor != NULL){
    //         sprintf(temp_priezvisko, "%s %s", posun_autor->meno, posun_autor->priezvisko);  // SPOJENIE MENA A PRIEZVISKA DO temp_priezvisko
    //         //printf("%s\n", temp_priezvisko);
    //         for(int i = 0; i < strlen(temp_priezvisko); i++){   // KONVERZIA CELEHO MENA AUTORA na male
    //             temp_priezvisko[i] = tolower(temp_priezvisko[i]);
    //         }
    //         //printf(">>>%s\n", temp_priezvisko);
    //         if( strcmp(input_autor, temp_priezvisko) == 0 ){    // KONTROLA CI SU ROVNAKE AKO ZADANE
    //             printf(">>%s<<je rovnake\n", temp_priezvisko);
    //             prva = vymaz_Zaznam(prva, kdeSom);   // VYMAZE POLOZKU SO ZHODOU
    //             break;
    //         }

    //         memset(temp_priezvisko, 0, 201);
    //         posun_autor = posun_autor->next;        // POSUN NA DALSIEHO AUTORA
    //     }

    //     posun = posun->next;
    // }

    // NAJDENIE ZHODY MENA //
    // ODSTRANENIE ZAZNAMU //
    //posun = prva;

    //int kdeSom = 0;

    //prva = vymaz_Zaznam(prva, kdeSom);
    // prva = vymaz_Zaznam(prva, 1);
    // prva = vymaz_Zaznam(prva, 1);

    // ODSTRANENIE ZAZNAMU //


    return prva;
}

void skopiruj_zaznam(int pozicia, struct osoba * prva, struct osoba ** before, struct osoba ** actual, struct osoba ** next){
    
    // PRELEZIE ZAZNAM A SKOPIRUJE PODLA ZADANEHO CISLA

    struct osoba * temp = prva;
    
    //>>> int pocet_zaznamov = spocitajZoznam(temp);

    // AK HLADANA POZICIA EXISTUJE
    //>>> if(pozicia <= pocet_zaznamov){
        int i = 1;
        while (temp != NULL){

            if( (pozicia == 1) && (i == 1) ){                                   // PRVY ZAZNAM
                *before = NULL;
                *actual = temp;
                *next = temp->next;
                return; 
            }
            else{   // NIE PRVY ZAZNAM
                if(i == (pozicia-1)){       // SKOPIROVANIE BEFORE
                    *before = temp;
                }
                if(i == pozicia){
                    *actual = temp;
                    *next = temp ->next;
                    //return;
                }
            }

            i++;          // CISLO AKTUALNEHO ZAZNAMU (od 1)
            temp = temp->next;
        }
    //>>> }
}   // KONIEC FUNKCIE

/*
void skusam(struct osoba *prva){

    struct osoba * pom_c1_before = NULL;
    struct osoba * pom_c1 = NULL;
    struct osoba * pom_c1_next = NULL;

    // SKOPIRUJE MI ADRESY cislo POLOZKY Z prva
    skopiruj_zaznam(2, prva, &pom_c1_before, &pom_c1, &pom_c1_next);

    if(pom_c1_before != NULL)
        printf(">>BEFORE %d >>> %s\n", pom_c1_before->id, pom_c1_before->nazov_prispevku);
    if(pom_c1 != NULL)
        printf(">>ACTUAL %d >>> %s\n", pom_c1->id, pom_c1->nazov_prispevku);
    if(pom_c1_next != NULL)
        printf(">>NEXT %d >>> %s\n", pom_c1_next->id, pom_c1_next->nazov_prispevku);
}
*/

struct osoba * prikaz_r(struct osoba * prva){
    // FUNKCIA SA ZAVOLA IBA AK JE VYTVORENY LINKED LIST
    struct osoba * temp = prva;
    struct osoba * temp_zaciatok;

    int c1 = 0;
    int c2 = 1;

    scanf("%d %d", &c1, &c2);
    //printf(">>%d  %d\n", c1, c2);

    int kolko_zaznamov = spocitajZoznam(prva);
    //printf("%d ZAZNAMOV\n", kolko_zaznamov);

    if( (c1 > 0) && (c1 <= kolko_zaznamov) && (c2 > 0) && (c2 <= kolko_zaznamov) ){ // AK SU CISLA > 0 A ZAROVEN <= AKO POCET ZAZNAMOV
        //printf("ZADANE CISLA SU V INTERVALE !\n");

        // VYMENA C1 S C2

        // AK SU CISLA ROVNAKE
        if(c1 == c2){
            return prva;
        }
        struct osoba * first = prva;    // POMOCNA PREMENNA -> na zaciatok LINKED LISTU
        struct osoba * posun = prva;    // POMOCNA PREMENNA -> na zaciatok (budem ju posuvat)

        struct osoba * pom_c1_before = NULL;
        struct osoba * pom_c1 = NULL;
        struct osoba * pom_c1_next = NULL;

        struct osoba * pom_c2_before = NULL;
        struct osoba * pom_c2 = NULL;
        struct osoba * pom_c2_next = NULL;
    
        int kde_som = 1;
        while(posun != NULL){   // SKOPIROVANIE ADRIES DO POMOCNYCH PREMENNYCH (pre c1 a c2)
            if( kde_som == c1 ){
                skopiruj_zaznam(kde_som, first, &pom_c1_before, &pom_c1, &pom_c1_next);  // NAKOPIRUJE MI ADRESY PODLA kde_som
            }
            if( kde_som == c2 ){
                skopiruj_zaznam(kde_som, first, &pom_c2_before, &pom_c2, &pom_c2_next);
            }

            kde_som++; // AKTUALNE CISLO ZAZNAMU (od 1)
            posun = posun->next;    // POSUNUTIE NA DALSI ZAZNAM
        }

        // KONTROLNY VYPIS
        /*
        printf("\n>>> c1 = %d\n", c1);
        if(pom_c1_before != NULL)
            printf(">>BEFORE %d >>> %s\n", pom_c1_before->id, pom_c1_before->nazov_prispevku);
        if(pom_c1 != NULL)
            printf(">>ACTUAL %d >>> %s\n", pom_c1->id, pom_c1->nazov_prispevku);
        if(pom_c1_next != NULL)
            printf(">>NEXT %d >>> %s\n", pom_c1_next->id, pom_c1_next->nazov_prispevku);

        printf("\n>>> c2 = %d\n", c2);
        if(pom_c2_before != NULL)
            printf(">>BEFORE %d >>> %s\n", pom_c2_before->id, pom_c2_before->nazov_prispevku);
        if(pom_c2 != NULL)
            printf(">>ACTUAL %d >>> %s\n", pom_c2->id, pom_c2->nazov_prispevku);
        if(pom_c2_next != NULL)
            printf(">>NEXT %d >>> %s\n", pom_c2_next->id, pom_c2_next->nazov_prispevku);
        */
        // KONTROLNY VYPIS

        // PREHODENIE C1 A C2       /////////////// ASI MAM
        //struct osoba * temp = NULL;
        //temp = pom_c1;
        //pom_c1 = pom_c2;
        posun = prva;
        kde_som = 1;
        // struct osoba * temp = prva;
        // struct osoba * temp_zaciatok;

        // VYMENA PRVYCH DVOCH
        if((c1 == 1) && (c2 == 2)){
            temp_zaciatok = pom_c2;
            pom_c1->next = pom_c2_next;
            temp_zaciatok->next = pom_c1;

            return temp_zaciatok;
        }
        else if ((c1 == 2) && (c2 == 1)){
            temp_zaciatok = pom_c1;
            pom_c2->next = pom_c1_next;
            temp_zaciatok->next = pom_c2;

            return temp_zaciatok;
        }
        // VYMENA PRVYCH DVOCH

        while( posun != NULL ){                 // PRECHADZANIE ZOZNAMU A VYMENA PRVKOV
            if (kde_som == 1)
                temp_zaciatok = temp;   // KOPIA PRVEHO PRVKU, aby som nestratil zaciatok

            ////////////////// C1 //////////////////////
            if( (c1 == 1) && (kde_som == 1) ){   // AK MENIM PRVY PRVOK c1
                temp = pom_c2;
                temp->next = pom_c1_next;
                temp_zaciatok = pom_c2; // UKAZUJE NA ZACIATOK
            }
            else{
                if( kde_som == (c1-1) ){
                temp->next = pom_c2;
                }
                if( kde_som == c1 ){
                    temp->next = pom_c1_next;
                }
            }
            ////////////////// C2 //////////////////////
            if( (c2 == 1) && (kde_som == 1) ){   // AK MENIM PRVY PRVOK c2
                temp = pom_c1;
                temp->next = pom_c2_next;
                temp_zaciatok = pom_c1; // UKAZUJE NA ZACIATOK
            }
            else{
                if( kde_som == (c2-1) ){
                    temp->next = pom_c1;
                }
                if( kde_som == c2 ){
                    temp->next = pom_c2_next;
                }
            }

            kde_som++; // AKTUALNE CISLO ZAZNAMU (od 1)
            temp = temp->next;
            posun = posun->next;    // POSUNUTIE NA DALSI ZAZNAM
        }
        return temp_zaciatok;

        // pom_c1_before->next = pom_c2;
        // pom_c2->next = pom_c1_next;

        // pom_c2_before->next = pom_c1;
        // pom_c2_next = pom_c2_next;


        // PREHODENIE C1 A C2
    }

    return prva;
}

int main(){

    struct osoba * prva_osoba = NULL;  // iniciovanie prazdneho spajaneho zoznamu

    //PREMENNA check PRE KONTROLU ZADANEHO ZNAKU
    char check;
    // prikaz k - nastavi na 1 a while cyklus a program skonci
    int main_loop_end = 0;

    while (main_loop_end == 0){
        
        //printf("\nZadaj pismeno:");
		scanf(" %c", &check);

		//VYMAZE ZNAK NOVEHO RIADKU KTORE PRIDA scanf
		getchar();
        //VYCISTI BUFFER (scanf prebehne iba raz, pouzije sa prve pismeno)
        fflush(stdin);

        switch (check){

            //PRIKAZ N
            case 'n':
                //printf("PRIKAZ N\n\n");
                prva_osoba = prikaz_n(prva_osoba);
                break;

            //PRIKAZ V
            case 'v':
                //printf("PRIKAZ V\n\n");
                vypis(prva_osoba);
                break;

            //PRIKAZ P
            case 'p':
                //printf("PRIKAZ P\n\n");
                // PRIDA NA POZICIU (kontrolovat ci na prve miesto alebo niekde v zozname alebo na konci)
                // NACITANIE UDAJOV DO POLOZKY ZOZNAMU                
                // AK JE LINKED LIST PRAZDNY - tak vytvori linked list
                prva_osoba = prikaz_p(prva_osoba);
                break;

            //PRIKAZ Z
            case 'z':
                //printf("PRIKAZ Z\n\n");
                if(prva_osoba != NULL){
                    prva_osoba = prikaz_z(prva_osoba);
                }
                break;

            //PRIKAZ H
            case 'h':
                //printf("PRIKAZ H\n\n");
                prizkaz_h(prva_osoba);
                break;

            //PRIKAZ A
            case 'a':
                //printf("PRIKAZ A\n\n");
                prva_osoba = prikaz_a(prva_osoba);
                break;
            
            //PRIKAZ R
            case 'r':
                //printf("PRIKAZ R\n\n");
                if(prva_osoba != NULL){
                    prva_osoba = prikaz_r(prva_osoba);
                }
                break;    

            // KONIEC
            case 'k':
                //printf("PROGRAM KONCI !\n");
                main_loop_end = 1;

                // UVOLNENIE ZOZNAMU //
                struct osoba *temp_inside_del;    
                struct autor *temp_inside_del_autor;
                // while(prva_osoba != NULL)
                // {
                //     temp_inside_del = prva_osoba;            // DO temp_inside ULOZIM AKTUALNU POLOZKU
                //     prva_osoba = prva_osoba->next;           // prva UZ UKAZUJE NA DALSIU POLOZKU
                //     free(temp_inside_del);                   // UVOLNIM
                // }
    
                while(prva_osoba != NULL)
                {
                    temp_inside_del = prva_osoba;           // DO temp_inside ULOZIM AKTUALNU POLOZKU
                    while(temp_inside_del->mena_autorov != NULL){
                        temp_inside_del_autor = temp_inside_del->mena_autorov;
                        temp_inside_del->mena_autorov = temp_inside_del->mena_autorov->next;
                        free(temp_inside_del_autor);
                    }
                    prva_osoba = prva_osoba->next;      // prva UZ UKAZUJE NA DALSIU POLOZKU
                    free(temp_inside_del);                  // UVOLNIM
                }
                // UVOLNENIE ZOZNAMU //
                break;
        
            //AK NEPOZNA PRIKAZ
            default:
                printf("Zadane pismeno nepoznam\n");
                break;
        }

    }


    return 0;
}
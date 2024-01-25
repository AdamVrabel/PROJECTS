// Adam Vrabel - FIIT STU - ZPRPR2
// PROJEKT 1

//VSTUPY A VYSTUPY
#include <stdio.h>

//PRACA S RETAZCAMI
#include <string.h>

//PRACA S PAMATOU
#include <stdlib.h>

FILE * prikaz_v(FILE * subor, char *** ppp_nazov_prispevku, char *** ppp_mena_autorov, char *** ppp_typ_prezentovania, char *** ppp_cas_prezentovania, char *** ppp_datum, long *** ppp_datum_citatelne, int * p_pocet_zaznamov){

    //KONTROLA CI UZ BOL OTVORENY SUBOR
    // AK SUBER NEBOL OTVORENY --> OTVORI SUBOR
    if (subor == NULL){                                     
        //printf("SUBOR ESTE NEBOL OTVORENY\n");
        
        //OTVORENIE A KONTROLA OTVORENIA SUBORU
        if((subor = fopen("OrganizacnePodujatia.txt", "r")) == NULL){       
            printf("Neotvoreny subor\n\n");
            //VRATI ukazovatel na subor s hodnotou NULL
            return(subor);                 
        }
    }

    // AK UZ BOL SUBOR OTVORENY
    //printf("SUBOR UZ JE OTVORENY alebo SOM HO TERAZ OTVORIL\n\n");

    // + ZISTENIE CI SU VYTVORENE DYNAMICKE POLIA (ci bol stlaceny 'n')
    // + VYPIS DYNAMICKYCH POLI

    // AK BOL stlaceny 'n' == BOLI ALOKOVANE POLIA
    //    VYPIS Z POLI
    if( ((*ppp_nazov_prispevku) != NULL) && ((*ppp_mena_autorov) != NULL) && ((*ppp_typ_prezentovania) != NULL) && ((*ppp_cas_prezentovania) != NULL) && ((*ppp_datum) != NULL) && ((*ppp_datum_citatelne) != NULL) ){
        //printf("TERAZ VYPISUJEM Z POLI\n");

        // VYPIS Z DYNAMICKYCH POLI //
        for(int i = 0; i < *p_pocet_zaznamov; i++){
            printf("Nazov prispevku: %s\n", (*ppp_nazov_prispevku)[i]);
            printf("Mena autorov: %s\n", (*ppp_mena_autorov)[i]);
            printf("Typ prezentovania: %s\n", (*ppp_typ_prezentovania)[i]);
            printf("Cas prezentovania: %s\n", (*ppp_cas_prezentovania)[i]);
            //printf("Cas prezentovania: %c%c:%c%c\n", staticke_cas_prezentovania[i][0], staticke_cas_prezentovania[i][1], staticke_cas_prezentovania[i][2], staticke_cas_prezentovania[i][3]);
            //printf("Cas prezentovania: %c%c:%c%c\n", *((*ppp_cas_prezentovania)[i] + 0), *((*ppp_cas_prezentovania)[i] + 1), *((*ppp_cas_prezentovania)[i] + 2), *((*ppp_cas_prezentovania)[i] + 3));
            printf("Datum: %s\n", (*ppp_datum)[i]);
            // printf("Datum: %02ld.%02ld.%4ld\n", datumik[i][0], datumik[i][1], datumik[i][2]);
            // printf("Datum: %02ld.%02ld.%4ld\n", *((*ppp_datum_citatelne)[i] + 0), *((*ppp_datum_citatelne)[i] + 1), *((*ppp_datum_citatelne)[i] + 2));
            printf("\n");
        }
        // KONIEC - VYPIS ZO STATICKYCH POLI //
    }
    //    KONIEC VYPIS Z POLI

    // AK NEBOL stlaceny 'n' == NEBOLI ALOKOVANE POLIA
    //    VYPIS ZO SUBORU
    if( ((*ppp_nazov_prispevku) == NULL) && ((*ppp_mena_autorov) == NULL) && ((*ppp_typ_prezentovania) == NULL) && ((*ppp_cas_prezentovania) == NULL) && ((*ppp_datum) == NULL) && ((*ppp_datum_citatelne) == NULL) ){
        //printf("TERAZ VYPISUJEM NATVRDO ZO SUBORU\n");
        // TOTO JE ZLY VYPIS ALE NECHAM SI HO TU, PRE ISTOTU
        if(0){
            // VYPIS ZO SUBORU - NAHRUBO (NA HULVATA)
            char znak;
            char predchadzajuci_znak;
            // CISLO ZAZNAMU
            int cislo_zaznamu = 0;
            //INDEX RIADKU PRE JEDNOTLIVY ZAZNAM
            int index = 0;
            //AKTUALNY ZNAK V RIADKU
            int index_znak = 0;

            //////  STATICKE POLIA    /////
            //////  PRE MAX 200 ZAZNAMOV  /////
            char staticke_nazov_prispevku[200][151];     // MAX 150 ZNAKOVY RETAZEC + 1 pre '\0'
            char staticke_mena_autorov[200][101];        // MAX 100 ZNAKOVY RETAZEC + 1 pre '\0'
            char staticke_typ_prezentovania[200][3];     // 2 ZNAKY + 1 pre '\0'
            char staticke_cas_prezentovania[200][5];     // 4 ZNAKY + 1 pre '\0'
            char staticke_datum[200][9];                 // 8 ZNAKOV + 1 pre '\0'

            memset(staticke_nazov_prispevku, '\0', 200);
            memset(staticke_mena_autorov, '\0', 200);
            memset(staticke_typ_prezentovania, '\0', 200);
            memset(staticke_cas_prezentovania, '\0', 200);
            memset(staticke_datum, '\0', 200);
            /////// END - STATICKE POLIA ///////


            //////// VYPOCET ZAZNAMOV ////////
            int pocet_zaznamov = 1;
            char actual;
            char before;
            //SPOCITANIE ZAZNAMOV
            while (!feof(subor)){
                actual = fgetc(subor);
                if (actual == '\n' && before == '\n'){
                    pocet_zaznamov++;
                }
                before = actual;
            }
            rewind(subor); // POSUNIE UKAZOVATEL NA ZACIATOK SUBORU

            //printf("POCET ZAZNAMOV V SUBORE : %d\n", pocet_zaznamov);
            //////// VYPOCET ZAZNAMOV ////////


            while (!feof(subor)){       //feof() TESTUJE KONIEC SUBORU | TRUE ak je na konci
                // NACITANIE ZNAKU ZO SUBORU
                znak = fgetc(subor);
                //printf("%c", znak); 

                if (znak == '\n'){
                    if (predchadzajuci_znak == '\n'){
                        index = 0;
                        index_znak = 0;
                        cislo_zaznamu++;
                        continue;	//preskoci celu iteraciu cyklu (preskoci ;)
                    }
                    index++;
                    index_znak = 0;
                    predchadzajuci_znak = znak;
                    continue;
                }
                //NAZOV PRISPEVKU:
                if (index == 0){
                    if (index_znak < 150){      // ZAPISE 150 ZNAKOV
                        staticke_nazov_prispevku[cislo_zaznamu][index_znak] = znak;
                        index_znak++;
                    }
                }
                //MENA AUTOROV:
                if (index == 1){
                    if (index_znak < 100){      // ZAPISE 100 ZNAKOV
                        staticke_mena_autorov[cislo_zaznamu][index_znak] = znak;
                        index_znak++;
                    }
                }
                //TYP PREZENTOVANIA:
                if (index == 2){
                    if(index_znak == 0){         // ZAPISE 2 ZNAKY
                        // PRVY ZNAK - KONTROLA + ZAPIS
                        if (znak == 'P' || znak == 'U'){   // KONTROLA CI PRVY ZNAK V TYPE PREZENTOVANIA JE P / U
                            staticke_typ_prezentovania[cislo_zaznamu][0] = znak;
                            
                            index_znak++;
                            predchadzajuci_znak = znak;
                            continue;
                        }
                        else {
                            staticke_typ_prezentovania[cislo_zaznamu][0] = ' ';
                            
                            index_znak++;
                            predchadzajuci_znak = znak;
                            continue;
                        }
                    }
                    if (index_znak == 1){
                        // DRUHY ZNAK - KONTROLA + ZAPIS
                        if (znak == 'D' || znak == 'P'){   // KONTROLA CI DRUHY ZNAK V TYPE PREZENTOVANIA JE D / P
                            staticke_typ_prezentovania[cislo_zaznamu][1] = znak;
                            
                            index_znak++;
                            predchadzajuci_znak = znak;
                            continue;
                        }
                        else {
                            staticke_typ_prezentovania[cislo_zaznamu][1] = ' ';
                            
                            index_znak++;
                            predchadzajuci_znak = znak;
                            continue;
                        }

                    }
                    
                }
                //CAS PREZENTOVANIA:
                if (index == 3){
                    if (index_znak < 4){      // ZAPISE 4 ZNAKY
                        staticke_cas_prezentovania[cislo_zaznamu][index_znak] = znak;
                        index_znak++;
                    }

                }
                //DATUM: ...
                if (index == 4){
                    if (index_znak < 8){      // ZAPISE 8 ZNAKOV
                        staticke_datum[cislo_zaznamu][index_znak] = znak;
                        index_znak++;
                    }

                }

                predchadzajuci_znak = znak;  

            } //KONIEC WHILE

            // PREHODENIE DATUMU //
            long datumik[200][3];
            //datumik[][0] = DEN   //datumik[][1] = MESIAC  //datumik[][2] = ROK
            memset(datumik, 0, 3);

            for(int i = 0; i < pocet_zaznamov; i++){
                long temp = atol(staticke_datum[i]);

                datumik[i][2] = temp / 10000;    // ROK
                datumik[i][1] = (temp - (datumik[i][2] * 10000)) / 100;  // MESIAC
                datumik[i][0] = (temp -(datumik[i][2] * 10000) - (datumik[i][1] * 100));

                // printf("DEN: %ld\n", datumik[i][0]);
                // printf("MESIAC: %ld\n", datumik[i][1]);
                // printf("ROK: %ld\n\n", datumik[i][2]);

            }
            // END - PREHODENIE DATUMU //

            // VYPIS ZO STATICKYCH POLI //
            for(int i = 0; i < pocet_zaznamov; i++){
                printf("Nazov prispevku: %s\n", staticke_nazov_prispevku[i]);
                printf("Mena autorov: %s\n", staticke_mena_autorov[i]);
                printf("Typ prezentovania: %s\n", staticke_typ_prezentovania[i]);
                printf("Cas prezentovania: %c%c:%c%c\n", staticke_cas_prezentovania[i][0], staticke_cas_prezentovania[i][1], staticke_cas_prezentovania[i][2], staticke_cas_prezentovania[i][3]);
                printf("Datum: %s\n", staticke_datum[i]);
                //printf("Datum: %02ld.%02ld.%4ld\n", datumik[i][0], datumik[i][1], datumik[i][2]);
                printf("\n");
            }
            // KONIEC - VYPIS ZO STATICKYCH POLI //
            // KONIEC - VYPIS ZO SUBORU - NAHRUBO (NA HULVATA)
        }
        // KONIEC ZLEHO VYPISU
        // LEPSI VYPIS
        if(1){
            // VYPIS ZO SUBORU - NAHRUBO (NA HULVATA)
            char znak;
            char predchadzajuci_znak;
            // CISLO ZAZNAMU
            int cislo_zaznamu = 0;
            //INDEX RIADKU PRE JEDNOTLIVY ZAZNAM
            int index = 0;
            //AKTUALNY ZNAK V RIADKU
            int index_znak = 0;

            //////// VYPOCET ZAZNAMOV ////////
            int pocet_zaznamov = 1;
            char actual;
            char before;
            //SPOCITANIE ZAZNAMOV
            while (!feof(subor)){
                actual = fgetc(subor);
                if (actual == '\n' && before == '\n'){
                    pocet_zaznamov++;
                }
                before = actual;
            }
            rewind(subor); // POSUNIE UKAZOVATEL NA ZACIATOK SUBORU

            //printf("POCET ZAZNAMOV V SUBORE : %d\n", pocet_zaznamov);
            //////// VYPOCET ZAZNAMOV ////////


            while (!feof(subor)){       //feof() TESTUJE KONIEC SUBORU | TRUE ak je na konci
                // NACITANIE ZNAKU ZO SUBORU
                znak = fgetc(subor);
                //printf("%c", znak); 

                if (znak == '\n'){
                    if (predchadzajuci_znak == '\n'){
                        index = 0;
                        index_znak = 0;
                        cislo_zaznamu++;
                        printf("\n");
                        continue;	//preskoci celu iteraciu cyklu (preskoci ;)
                    }
                    index++;
                    index_znak = 0;
                    predchadzajuci_znak = znak;
                    continue;
                }
                //NAZOV PRISPEVKU:
                if (index == 0){
                    if (index_znak < 150){      // ZAPISE 150 ZNAKOV
                        if(index_znak == 0){
                            printf("\nNazov prispevku: %c", znak);
                            index_znak++;
                        }
                        else{
                            printf("%c", znak);
                            index_znak++;
                        }    
                    }
                }
                //MENA AUTOROV:
                if (index == 1){
                    if (index_znak < 100){      // ZAPISE 100 ZNAKOV
                        if(index_znak == 0){
                            printf("\nMena autorov: %c", znak);
                            index_znak++;
                        }
                        else{
                            printf("%c", znak);
                            index_znak++;
                        }
                    }
                }
                //TYP PREZENTOVANIA:
                if (index == 2){
                    if(index_znak == 0){         // ZAPISE 2 ZNAKY
                        // PRVY ZNAK - KONTROLA + ZAPIS
                        printf("\nTyp prezentovania: ");
                        if (znak == 'P' || znak == 'U'){   // KONTROLA CI PRVY ZNAK V TYPE PREZENTOVANIA JE P / U
                            printf("%c", znak);
                            
                            index_znak++;
                            predchadzajuci_znak = znak;
                            continue;
                        }
                        else { // AK PRVY ZNAK NIEJE P ALEBO U
                            printf(" ");
                            
                            index_znak++;
                            predchadzajuci_znak = znak;
                            continue;
                        }
                    }
                    if (index_znak == 1){
                        // DRUHY ZNAK - KONTROLA + ZAPIS
                        if (znak == 'D' || znak == 'P'){   // KONTROLA CI DRUHY ZNAK V TYPE PREZENTOVANIA JE D / P
                            printf("%c", znak);
                            
                            index_znak++;
                            predchadzajuci_znak = znak;
                            continue;
                        }
                        else { // AK PRVY ZNAK NIEJE D ALEBO P
                            printf(" ");
                            
                            index_znak++;
                            predchadzajuci_znak = znak;
                            continue;
                        }

                    }
                    
                }
                //CAS PREZENTOVANIA:
                if (index == 3){
                    if (index_znak < 4){      // ZAPISE 4 ZNAKY
                        if(index_znak == 0){
                            printf("\nCas prezentovania: %c", znak);
                            index_znak++;
                        }
                        else{
                            printf("%c", znak);
                            index_znak++;
                        }
                    }

                }
                //DATUM: ...
                if (index == 4){
                    if (index_znak < 8){      // ZAPISE 8 ZNAKOV
                        if(index_znak == 0){
                            printf("\nDatum: %c", znak);
                            index_znak++;
                        }
                        else{
                            printf("%c", znak);
                            index_znak++;
                        }
                    }
                }

                predchadzajuci_znak = znak;  
            } //KONIEC WHILE
            printf("\n\n");
        }
        // KONIEC LEPSIEHO VYPISU
    }
    //    KONIEC VYPIS ZO SUBORU  
    
    
    //fclose(subor) - je v case - k
    rewind(subor); // POSUNIE UKAZOVATEL NA ZACIATOK SUBORU
    return subor;
}

void prikaz_n(FILE * subor, char *** ppp_nazov_prispevku, char *** ppp_mena_autorov, char *** ppp_typ_prezentovania, char *** ppp_cas_prezentovania, char *** ppp_datum, long *** ppp_datum_citatelne, int * p_pocet_zaznamov, int ak_z_stlacene){

    // AK BOL UZ SUBOR OTVORENY
        // SPOCITA POCET ZAZNAMOV
        // AK BOLI POLIA VYTVORENE --> DEALOKUJE ( pomocou free() )
        // DYNAMICKY VYTVORI POLIA
    if(subor != NULL){

        if (ak_z_stlacene != 1){ // NEVYKONA SA IBA V PRIPADE ZE SA STLACILO Z
            ////////////// SPOCITANIE ZAZNAMOV //////////////
            *p_pocet_zaznamov = 1;
            char actual;
            char before;
            //SPOCITANIE ZAZNAMOV
            while (!feof(subor)){
                actual = fgetc(subor);
                if (actual == '\n' && before == '\n'){
                    *p_pocet_zaznamov = *p_pocet_zaznamov + 1;
                }
                before = actual;
            }
            rewind(subor); // POSUNIE UKAZOVATEL NA ZACIATOK SUBORU
                //printf("POCET ZAZNAMOV V SUBORE : %d\n", pocet_zaznamov);
            ////////////// END - SPOCITANIE ZAZNAMOV //////////////
        }

        // AK POLIA UZ SU VYTVORENE --> free --> alokovat nanovo
        if ( ((*ppp_nazov_prispevku) != NULL) && ((*ppp_mena_autorov) != NULL) && ((*ppp_typ_prezentovania) != NULL) && ((*ppp_cas_prezentovania) != NULL) && ((*ppp_datum) != NULL) && ((*ppp_datum_citatelne) != NULL) ){
            //printf("Polia uz boli vytvorene, teraz dealokujem...\n");

            // UVOLNENIE DYNAMICKYCH POLI
            //PPP_NAZOV_PRISPEVKU
            for (int i = 0; i < *p_pocet_zaznamov; i++){
                //free( *(nazov_prispevku + i) );
                free((*ppp_nazov_prispevku)[i]);
                //*(nazov_prispevku + i) = NULL;
                (*ppp_nazov_prispevku)[i] = NULL;
            }
            free(*ppp_nazov_prispevku);
            (*ppp_nazov_prispevku) = NULL;

            //PPP_MENA_AUTOROV
            for (int i = 0; i < *p_pocet_zaznamov; i++){
                free((*ppp_mena_autorov)[i]);
                (*ppp_mena_autorov)[i] = NULL;
            }
            free(*ppp_mena_autorov);
            (*ppp_mena_autorov) = NULL;

            //PPP_TYP_PREZENTOVANIA
            for (int i = 0; i < *p_pocet_zaznamov; i++){
                free((*ppp_typ_prezentovania)[i]);
                (*ppp_typ_prezentovania)[i] = NULL;
            }
            free(*ppp_typ_prezentovania);
            (*ppp_typ_prezentovania) = NULL;

            //PPP_CAS_PREZENTOVANIA
            for (int i = 0; i < *p_pocet_zaznamov; i++){
                free((*ppp_cas_prezentovania)[i]);
                (*ppp_cas_prezentovania)[i] = NULL;
            }
            free(*ppp_cas_prezentovania);
            (*ppp_cas_prezentovania) = NULL;

            //PPP_DATUM
            for (int i = 0; i < *p_pocet_zaznamov; i++){
                free((*ppp_datum)[i]);
                (*ppp_datum)[i] = NULL;
            }
            free(*ppp_datum);
            (*ppp_datum) = NULL;

            //PPP_DATUM_CITATELNE
            for (int i = 0; i < *p_pocet_zaznamov; i++){
                free((*ppp_datum_citatelne)[i]);
                (*ppp_datum_citatelne)[i] = NULL;
            }
            free(*ppp_datum_citatelne);
            (*ppp_datum_citatelne) = NULL;

        }
        // END - UVOLNENIE DYNAMICKYCH POLI

        // TERAZ MUSIM SPOCITAT ZAZNAMY ABY SOM VEDEL VYTVORIT POTREBNE MNOZSTVO POLI PODLA ZAZNAMOV
        ////////////// SPOCITANIE ZAZNAMOV //////////////
            *p_pocet_zaznamov = 1;
            char actual;
            char before;
            //SPOCITANIE ZAZNAMOV
            while (!feof(subor)){
                actual = fgetc(subor);
                if (actual == '\n' && before == '\n'){
                    *p_pocet_zaznamov = *p_pocet_zaznamov + 1;
                }
                before = actual;
            }
            rewind(subor); // POSUNIE UKAZOVATEL NA ZACIATOK SUBORU
            //printf("POCET ZAZNAMOV V SUBORE : %d\n", pocet_zaznamov);
        ////////////// END - SPOCITANIE ZAZNAMOV //////////////

        ////// DYNAMICKE VYTVORENIE POLI //////

        // NAZOV PRISPEVKU
        //*ppp_nazov_prispevku = (char **) malloc( (*p_pocet_zaznamov) * sizeof(char *));
        *ppp_nazov_prispevku = (char **) calloc( (*p_pocet_zaznamov),  sizeof(char *));
        // if (*ppp_nazov_prispevku == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }

        for (int i = 0; i < (*p_pocet_zaznamov); i++){
            //(*ppp_nazov_prispevku)[i] = malloc( sizeof(char) * 151);
            (*ppp_nazov_prispevku)[i] = calloc( 151, sizeof(char));
            // if ((*ppp_nazov_prispevku)[i] == NULL){
            //     printf("ERROR: out of memory\n");
            //     //return 1;
            // }
        }

        // MENA AUTOROV
        *ppp_mena_autorov = (char **) calloc( (*p_pocet_zaznamov),  sizeof(char *));
        // if (*ppp_mena_autorov == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }

        for (int i = 0; i < (*p_pocet_zaznamov); i++){
            //(*ppp_mena_autorov)[i] = malloc( sizeof(char) * 101);
            (*ppp_mena_autorov)[i] = calloc( 101, sizeof(char));
            // if ((*ppp_mena_autorov)[i] == NULL){
            //     printf("ERROR: out of memory\n");
            //     //return 1;
            // }
        }

        // TYP PREZENTOVANIA
        *ppp_typ_prezentovania = (char **) calloc( (*p_pocet_zaznamov),  sizeof(char *));
        // if (*ppp_typ_prezentovania == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }

        for (int i = 0; i < (*p_pocet_zaznamov); i++){
            //(*ppp_typ_prezentovania)[i] = malloc( sizeof(char) * 151);
            (*ppp_typ_prezentovania)[i] = calloc( 3, sizeof(char));
            // if ((*ppp_typ_prezentovania)[i] == NULL){
            //     printf("ERROR: out of memory\n");
            //     //return 1;
            // }
        }

        // CAS PREZENTOVANIA
        *ppp_cas_prezentovania = (char **) calloc( (*p_pocet_zaznamov),  sizeof(char *));
        // if (*ppp_cas_prezentovania == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }

        for (int i = 0; i < (*p_pocet_zaznamov); i++){
            //(*ppp_cas_prezentovania)[i] = malloc( sizeof(char) * 151);
            (*ppp_cas_prezentovania)[i] = calloc( 5, sizeof(char));
            // if ((*ppp_cas_prezentovania)[i] == NULL){
            //     printf("ERROR: out of memory\n");
            //     //return 1;
            // }
        }
        
        // DATUM
        *ppp_datum = (char **) calloc( (*p_pocet_zaznamov),  sizeof(char *));
        // if (*ppp_datum == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }

        for (int i = 0; i < (*p_pocet_zaznamov); i++){
            //(*ppp_datum)[i] = malloc( sizeof(char) * 151);
            (*ppp_datum)[i] = calloc( 9, sizeof(char));
            // if ((*ppp_datum)[i] == NULL){
            //     printf("ERROR: out of memory\n");
            //     //return 1;
            // }
        }

        // DATUM CITATELNE
        *ppp_datum_citatelne = (long **) calloc( (*p_pocet_zaznamov),  sizeof(long *));
        // if (*ppp_datum_citatelne == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }

        for (int i = 0; i < (*p_pocet_zaznamov); i++){
            //(*ppp_datum_citatelne)[i] = malloc( sizeof(long) * 8);
            (*ppp_datum_citatelne)[i] = calloc( 3, sizeof(long));
            // if ((*ppp_datum_citatelne)[i] == NULL){
            //     printf("ERROR: out of memory\n");
            //     //return 1;
            // }
        }

        //printf("Allocated!\n");

        // for(int i = 0; i < strlen("skuska"); i++) {
        //    *(*dp_nazov_prispevku + i) = "k";
        // }

        ////// END - DYNAMICKE VYTVORENIE POLI //////

        /////////////////////////////////////////////////////////////////
        ///////// NAPLNENIE DYNAM. POLI /////////
        char znak;
        char predchadzajuci_znak;
        // CISLO ZAZNAMU
        int cislo_zaznamu = 0;
        //INDEX RIADKU PRE JEDNOTLIVY ZAZNAM
        int index = 0;
        //AKTUALNY ZNAK V RIADKU
        int index_znak = 0;

        
        while (!feof(subor)){       //feof() TESTUJE KONIEC SUBORU | TRUE ak je na konci
            // NACITANIE ZNAKU ZO SUBORU
            znak = fgetc(subor);
            //printf("%c", znak); 

            if (znak == '\n'){
                if (predchadzajuci_znak == '\n'){
                    index = 0;
                    index_znak = 0;
                    cislo_zaznamu++;
                    continue;	//preskoci celu iteraciu cyklu (preskoci ;)
                }
                index++;
                index_znak = 0;
                predchadzajuci_znak = znak;
                continue;
            }
            //NAZOV PRISPEVKU:
            if (index == 0){
                if (index_znak < 150){      // ZAPISE 150 ZNAKOV
                    //*(*(*dp_nazov_prispevku + index_znak) + cislo_zaznamu) = znak;
                    *((*ppp_nazov_prispevku)[cislo_zaznamu] + index_znak) = znak;
                    //dp_nazov_prispevku[cislo_zaznamu][index_znak] = znak;
                    index_znak++;
                }
            }
            //MENA AUTOROV:
            if (index == 1){
                if (index_znak < 100){      // ZAPISE 100 ZNAKOV
                    *((*ppp_mena_autorov)[cislo_zaznamu] + index_znak) = znak;
                    index_znak++;
                }
            }
            //TYP PREZENTOVANIA:
            if (index == 2){
                if(index_znak == 0){         // ZAPISE 2 ZNAKY
                    // PRVY ZNAK - KONTROLA + ZAPIS
                    if (znak == 'P' || znak == 'U'){   // KONTROLA CI PRVY ZNAK V TYPE PREZENTOVANIA JE P / U
                        //staticke_typ_prezentovania[cislo_zaznamu][0] = znak;
                        *((*ppp_typ_prezentovania)[cislo_zaznamu] + 0) = znak;
                        
                        index_znak++;
                        predchadzajuci_znak = znak;
                        continue;
                    }
                    else {
                        //staticke_typ_prezentovania[cislo_zaznamu][0] = ' ';
                        *((*ppp_typ_prezentovania)[cislo_zaznamu] + index_znak) = ' ';
                        
                        index_znak++;
                        predchadzajuci_znak = znak;
                        continue;
                    }
                }
                if (index_znak == 1){
                    // DRUHY ZNAK - KONTROLA + ZAPIS
                    if (znak == 'D' || znak == 'P'){   // KONTROLA CI DRUHY ZNAK V TYPE PREZENTOVANIA JE D / P
                        //staticke_typ_prezentovania[cislo_zaznamu][1] = znak;
                        *((*ppp_typ_prezentovania)[cislo_zaznamu] + 1) = znak;
                        
                        index_znak++;
                        predchadzajuci_znak = znak;
                        continue;
                    }
                    else {
                        //staticke_typ_prezentovania[cislo_zaznamu][1] = ' ';
                        *((*ppp_typ_prezentovania)[cislo_zaznamu] + 1) = ' ';
                        
                        index_znak++;
                        predchadzajuci_znak = znak;
                        continue;
                    }

                }
                
            }
            //CAS PREZENTOVANIA:
            if (index == 3){
                if (index_znak < 4){      // ZAPISE 4 ZNAKY
                    //staticke_cas_prezentovania[cislo_zaznamu][index_znak] = znak;
                    *((*ppp_cas_prezentovania)[cislo_zaznamu] + index_znak) = znak;
                    index_znak++;
                }

            }
            //DATUM: ...
            if (index == 4){
                if (index_znak < 8){      // ZAPISE 8 ZNAKOV
                    //staticke_datum[cislo_zaznamu][index_znak] = znak;
                    *((*ppp_datum)[cislo_zaznamu] + index_znak) = znak;
                    index_znak++;
                }

            }

            predchadzajuci_znak = znak;  

        } //KONIEC WHILE
        rewind(subor);
        printf("Nacitane data\n\n");

        /////// PREHODENIE DATUMU ///////
        //long datumik[200][3];
        //ppp_datum_citatelne
        //datumik[][0] = DEN   //datumik[][1] = MESIAC  //datumik[][2] = ROK

        for(int i = 0; i < *p_pocet_zaznamov; i++){
            char temp_string[9] = {'\0'};               // DOCASNE POLE PRE KONVERZIU DATUMU DO DATUM_CITATELNE
            strcpy(temp_string, ((*ppp_datum)[i]));     // KOPIROVANIE STRINGU V DATUME DO DOCASNEHO STRINGU
            long temp = atol(temp_string);              // PREVOD STRING --> LONG
            

            *((*ppp_datum_citatelne)[i] + 2) = temp / 10000;    //ROK
            *((*ppp_datum_citatelne)[i] + 1) = (temp - ((*((*ppp_datum_citatelne)[i] + 2)) * 10000)) / 100;  // MESIAC
            *((*ppp_datum_citatelne)[i] + 0) = (temp -((*((*ppp_datum_citatelne)[i] + 2)) * 10000) - ((*((*ppp_datum_citatelne)[i] + 1)) * 100));

            // printf("DEN: %ld\n", *((*ppp_datum_citatelne)[i] + 0));
            // printf("MESIAC: %ld\n", *((*ppp_datum_citatelne)[i] + 1));
            // printf("ROK: %ld\n\n", *((*ppp_datum_citatelne)[i] + 2));
        }
        /////// END - PREHODENIE DATUMU ///////

        ///////// END - NAPLNENIE DYNAM. POLI /////////
///////////////////////////////////////////////////////////////////////////////////////////
        
    }
    else{
        printf("Neotvoreny subor.\n\n");
    }
}

void prikaz_s(FILE * subor, char *** ppp_nazov_prispevku, char *** ppp_mena_autorov, char *** ppp_typ_prezentovania, char *** ppp_cas_prezentovania, char *** ppp_datum, long *** ppp_datum_citatelne, int * p_pocet_zaznamov){
    
    // AK DYNAMICKE POLIA NIESU VYTVORENE
    if ( ((*ppp_nazov_prispevku) == NULL) && ((*ppp_mena_autorov) == NULL) && ((*ppp_typ_prezentovania) == NULL) && ((*ppp_cas_prezentovania) == NULL) && ((*ppp_datum) == NULL) && ((*ppp_datum_citatelne) == NULL) ){
        printf("Polia nie su vytvorene.\n\n");
    }

    // AK DYNAMICKE POLIA SU VYTVORENE
    if( ((*ppp_nazov_prispevku) != NULL) && ((*ppp_mena_autorov) != NULL) && ((*ppp_typ_prezentovania) != NULL) && ((*ppp_cas_prezentovania) != NULL) && ((*ppp_datum) != NULL) && ((*ppp_datum_citatelne) != NULL) ){
        
////////////// SPRAVIT KONTROLU SCANF aby som mohol zapisat iba 8 cisel do datumu a iba PD UD PP UP do typu

        int exist = 0; // AK EXISTUJE ZAZNAM, NASTAVI NA 1
        char input_datum[9];
        memset(input_datum, '\0', 9);

        char input_typ_prezentovania[3];
        memset(input_typ_prezentovania, '\0', 3);

        //printf("Zadaj datum: ");        
        scanf("%8s", input_datum);  //NACITA MI STRING O VELKOSTI 8 ZNAKOV

        //getchar();
        fflush(stdin);

        //printf("Zadaj typ prezentovania: ");
        scanf("%2s", input_typ_prezentovania);  //NACITA MI STRING O VELKOSTI 2 ZNAKOV
        //getchar();
        fflush(stdin);

        //printf("Zadany datum: %s\nZadaty typ prezentovania: %s\n\n", input_datum, input_typ_prezentovania);

        for(int i = 0; i < *p_pocet_zaznamov; i++){
            // AK SU ROVNAKE --> strcmp() VRATI 0
            if( (strcmp(input_datum, (*ppp_datum)[i]) == 0 ) && (strcmp(input_typ_prezentovania, (*ppp_typ_prezentovania)[i]) == 0 ) ){
                printf("%s\t", (*ppp_cas_prezentovania)[i]);
                printf("%s\t", (*ppp_mena_autorov)[i]);
                printf("%s\n", (*ppp_nazov_prispevku)[i]);
                exist = 1;
            }
        }
        if(exist != 0){
            printf("\n");
        }
        if(exist == 0){
            printf("Pre dany vstup neexistuju zaznamy.\n\n");
        }

    }


}

void prikaz_p(FILE * subor, char *** ppp_nazov_prispevku, char *** ppp_mena_autorov, char *** ppp_typ_prezentovania, char *** ppp_cas_prezentovania, char *** ppp_datum, long *** ppp_datum_citatelne, int * p_pocet_zaznamov){
    
    //POSTUPNE NACITAM ZAZNAMY --> PRIDAM NA KONIEC DYNAMICKYCH POLI

    // AK DYNAMICKE POLIA NIESU VYTVORENE
    if ( ((*ppp_nazov_prispevku) == NULL) && ((*ppp_mena_autorov) == NULL) && ((*ppp_typ_prezentovania) == NULL) && ((*ppp_cas_prezentovania) == NULL) && ((*ppp_datum) == NULL) && ((*ppp_datum_citatelne) == NULL) ){
        printf("Polia nie su vytvorene\n\n");
    }

    // AK DYNAMICKE POLIA SU VYTVORENE
    if( ((*ppp_nazov_prispevku) != NULL) && ((*ppp_mena_autorov) != NULL) && ((*ppp_typ_prezentovania) != NULL) && ((*ppp_cas_prezentovania) != NULL) && ((*ppp_datum) != NULL) && ((*ppp_datum_citatelne) != NULL) ){

        //NAZOV
        char input_nazov[151];
        memset(input_nazov, '\0', 151);
        //AUTOR
        char input_autor[101];
        memset(input_autor, '\0', 101);
        //TYP
        char input_typ_prezentovania[3];
        memset(input_typ_prezentovania, '\0', 3);
        //CAS
        char input_cas[5];
        memset(input_cas, '\0', 5);
        //DATUM
        char input_datum[9];
        memset(input_datum, '\0', 9);

        //printf("Zadaj nazov: ");        
        //scanf("%150s", input_nazov);
        scanf("%150[^\n]", input_nazov);  // [^\n] - povie kompilatoru nech cita znaky az po prvy vyskyt '\n'     // https://www.includehelp.com/c/c-program-to-read-string-with-spaces-using-scanf-function.aspx
        fflush(stdin);

        //printf("Zadaj autora: ");        
        scanf("%100[^\n]", input_autor);
        fflush(stdin);

        //printf("Zadaj typ prezentovania: ");
        scanf("%2s", input_typ_prezentovania);
        fflush(stdin);

        //printf("Zadaj cas: ");        
        scanf("%4s", input_cas);
        fflush(stdin);

        //printf("Zadaj datum: ");        
        scanf("%8s", input_datum);
        fflush(stdin);

        //printf("NAZOV: %s\nAUTOR: %s\nTYP: %s\nCAS: %s\nDATUM: %s\n", input_nazov, input_autor, input_typ_prezentovania, input_cas, input_datum);

        // REALOKOVANIE POLI
        // VYPOCET *p_pocet_zaznamov + 1 
        // A PRIDANIE ZAZNAMU NA KONIEC

        // ZVACSENIE POCTU ZAZNAMOV V DYNAM. POLY O 1 (PRE NOVY ZADANY ZAZNAM)
        *p_pocet_zaznamov = *p_pocet_zaznamov + 1;

        // realloc ( ukazovatel, velkost )

        // NAZOV PRISPEVKU
        *ppp_nazov_prispevku = (char **) realloc(*ppp_nazov_prispevku,  ((*p_pocet_zaznamov) * sizeof(char *)) );
        // if (*ppp_nazov_prispevku == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }
        (*ppp_nazov_prispevku)[(*p_pocet_zaznamov)-1] = calloc(151, sizeof(char));


        // MENA AUTOROV
        *ppp_mena_autorov = (char **) realloc( *ppp_mena_autorov, ((*p_pocet_zaznamov) * sizeof(char *)) );
        // if (*ppp_mena_autorov == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }
        (*ppp_mena_autorov)[(*p_pocet_zaznamov)-1] = calloc(151, sizeof(char));


        // TYP PREZENTOVANIA
        *ppp_typ_prezentovania = (char **) realloc( *ppp_typ_prezentovania, ((*p_pocet_zaznamov) * sizeof(char *)) );
        // if (*ppp_typ_prezentovania == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }
        (*ppp_typ_prezentovania)[(*p_pocet_zaznamov)-1] = calloc(151, sizeof(char));

        // CAS PREZENTOVANIA
        *ppp_cas_prezentovania = (char **) realloc( *ppp_cas_prezentovania, ((*p_pocet_zaznamov) * sizeof(char *)) );
        // if (*ppp_cas_prezentovania == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }
        (*ppp_cas_prezentovania)[(*p_pocet_zaznamov)-1] = calloc(151, sizeof(char));
        
        // DATUM
        *ppp_datum = (char **) realloc( *ppp_datum , ((*p_pocet_zaznamov) * sizeof(char *)) );
        // if (*ppp_datum == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }
        (*ppp_datum)[(*p_pocet_zaznamov)-1] = calloc(151, sizeof(char));

        // DATUM CITATELNE
        *ppp_datum_citatelne = (long **) realloc( *ppp_datum_citatelne , ((*p_pocet_zaznamov) * sizeof(long *)) );
        // if (*ppp_datum == NULL){
        //     printf("ERROR: out of memory\n");
        //     //return 1;
        // }
        (*ppp_datum_citatelne)[(*p_pocet_zaznamov)-1] = calloc(151, sizeof(long));

        // KOPIROVANIE ZADANYCH INPUTOV DO POSLEDNEHO INDEXU V POLIACH
        strcpy((*ppp_nazov_prispevku)[(*p_pocet_zaznamov)-1], input_nazov);
        strcpy((*ppp_mena_autorov)[(*p_pocet_zaznamov)-1], input_autor);
        strcpy((*ppp_typ_prezentovania)[(*p_pocet_zaznamov)-1], input_typ_prezentovania);
        strcpy((*ppp_cas_prezentovania)[(*p_pocet_zaznamov)-1], input_cas);
        strcpy((*ppp_datum)[(*p_pocet_zaznamov)-1], input_datum);

        ///////// KONVERZIA DATUM NA DATUM CITATELNE /////////
        char temp_string[9] = {'\0'};               // DOCASNE POLE PRE KONVERZIU DATUMU DO DATUM_CITATELNE
        strcpy(temp_string, ((*ppp_datum)[(*p_pocet_zaznamov)-1]));     // KOPIROVANIE STRINGU V DATUME DO DOCASNEHO STRINGU
        long temp = atol(temp_string);              // PREVOD STRING --> LONG

        *((*ppp_datum_citatelne)[(*p_pocet_zaznamov)-1] + 2) = temp / 10000;    //ROK
        *((*ppp_datum_citatelne)[(*p_pocet_zaznamov)-1] + 1) = (temp - ((*((*ppp_datum_citatelne)[(*p_pocet_zaznamov)-1] + 2)) * 10000)) / 100;  // MESIAC
        *((*ppp_datum_citatelne)[(*p_pocet_zaznamov)-1] + 0) = (temp -((*((*ppp_datum_citatelne)[(*p_pocet_zaznamov)-1] + 2)) * 10000) - ((*((*ppp_datum_citatelne)[(*p_pocet_zaznamov)-1] + 1)) * 100));
        ///////// KONVERZIA DATUM NA DATUM CITATELNE /////////


        printf("Zaznam sa podarilo pridat\n\n");
        //////////////////////////////////
    }
    

}

void prikaz_h(FILE * subor, char *** ppp_nazov_prispevku, char *** ppp_mena_autorov, char *** ppp_typ_prezentovania, char *** ppp_cas_prezentovania, char *** ppp_datum, long *** ppp_datum_citatelne, int * p_pocet_zaznamov){
    // AK DYNAMICKE POLIA NIESU VYTVORENE
    if ( ((*ppp_nazov_prispevku) == NULL) && ((*ppp_mena_autorov) == NULL) && ((*ppp_typ_prezentovania) == NULL) && ((*ppp_cas_prezentovania) == NULL) && ((*ppp_datum) == NULL) && ((*ppp_datum_citatelne) == NULL) ){
        printf("Polia nie su vytvorene\n\n");
    }

    if( ((*ppp_nazov_prispevku) != NULL) && ((*ppp_mena_autorov) != NULL) && ((*ppp_typ_prezentovania) != NULL) && ((*ppp_cas_prezentovania) != NULL) && ((*ppp_datum) != NULL) && ((*ppp_datum_citatelne) != NULL) ){

        //NAZOV
        // char input_nazov[151];
        // memset(input_nazov, '\0', 151);

        // printf("Zadaj nazov: ");        
        // scanf("%150[^\n]", input_nazov);  // [^\n] - povie kompilatoru nech cita znaky az po prvy vyskyt '\n'     // https://www.includehelp.com/c/c-program-to-read-string-with-spaces-using-scanf-function.aspx
        // fflush(stdin);

        //printf("%s\n", input_nazov);
        int UP[6] = {0};
        int UD[6] = {0};
        int PP[6] = {0};
        int PD[6] = {0};
        int temp_cas = 0;

        // [0]  ==> 8:00   -  9:59
        // [1]  ==> 10:00  -  11:59
        // [2]  ==> 12:00  -  13:59
        // [3]  ==> 14:00  -  15:59
        // [4]  ==> 16:00  -  17:59
        // [5]  ==> 18:00  -  19:59

        for(int i = 0; i < (*p_pocet_zaznamov); i++){
            // KONVERZIA CASU (string) NA (int)
            temp_cas = atoi((*ppp_cas_prezentovania)[i]);
            //printf("%d. toto je int = %d\n", i, temp_cas);

            // NAJDENIE VYSKYTOV A ZHODY
            if (800 <= temp_cas && temp_cas <= 959){    // OD 8:00 DO 9:59
                if(strcmp("UP", (*ppp_typ_prezentovania)[i]) == 0){
                    UP[0]++;
                }
                if(strcmp("UD", (*ppp_typ_prezentovania)[i]) == 0){
                    UD[0]++;
                }
                if(strcmp("PP", (*ppp_typ_prezentovania)[i]) == 0){
                    PP[0]++;
                }
                if(strcmp("PD", (*ppp_typ_prezentovania)[i]) == 0){
                    PD[0]++;
                }
            }
            if (1000 <= temp_cas && temp_cas <= 1159){    // OD 10:00 DO 11:59
                if(strcmp("UP", (*ppp_typ_prezentovania)[i]) == 0){
                    UP[1]++;
                }
                if(strcmp("UD", (*ppp_typ_prezentovania)[i]) == 0){
                    UD[1]++;
                }
                if(strcmp("PP", (*ppp_typ_prezentovania)[i]) == 0){
                    PP[1]++;
                }
                if(strcmp("PD", (*ppp_typ_prezentovania)[i]) == 0){
                    PD[1]++;
                }
            }
            if (1200 <= temp_cas && temp_cas <= 1359){    // OD 12:00 DO 13:59
                if(strcmp("UP", (*ppp_typ_prezentovania)[i]) == 0){
                    UP[2]++;
                }
                if(strcmp("UD", (*ppp_typ_prezentovania)[i]) == 0){
                    UD[2]++;
                }
                if(strcmp("PP", (*ppp_typ_prezentovania)[i]) == 0){
                    PP[2]++;
                }
                if(strcmp("PD", (*ppp_typ_prezentovania)[i]) == 0){
                    PD[2]++;
                }
            }
            if (1400 <= temp_cas && temp_cas <= 1559){    // OD 14:00 DO 15:59
                if(strcmp("UP", (*ppp_typ_prezentovania)[i]) == 0){
                    UP[3]++;
                }
                if(strcmp("UD", (*ppp_typ_prezentovania)[i]) == 0){
                    UD[3]++;
                }
                if(strcmp("PP", (*ppp_typ_prezentovania)[i]) == 0){
                    PP[3]++;
                }
                if(strcmp("PD", (*ppp_typ_prezentovania)[i]) == 0){
                    PD[3]++;
                }
            }
            if (1600 <= temp_cas && temp_cas <= 1759){    // OD 16:00 DO 17:59
                if(strcmp("UP", (*ppp_typ_prezentovania)[i]) == 0){
                    UP[4]++;
                }
                if(strcmp("UD", (*ppp_typ_prezentovania)[i]) == 0){
                    UD[4]++;
                }
                if(strcmp("PP", (*ppp_typ_prezentovania)[i]) == 0){
                    PP[4]++;
                }
                if(strcmp("PD", (*ppp_typ_prezentovania)[i]) == 0){
                    PD[4]++;
                }
            }
            if (1800 <= temp_cas && temp_cas <= 1959){    // OD 18:00 DO 19:59
                if(strcmp("UP", (*ppp_typ_prezentovania)[i]) == 0){
                    UP[5]++;
                }
                if(strcmp("UD", (*ppp_typ_prezentovania)[i]) == 0){
                    UD[5]++;
                }
                if(strcmp("PP", (*ppp_typ_prezentovania)[i]) == 0){
                    PP[5]++;
                }
                if(strcmp("PD", (*ppp_typ_prezentovania)[i]) == 0){
                    PD[5]++;
                }
            }
            // NAJDENIE VYSKYTOV A ZHODY
        }

        printf(" hodina\t\t\tUP\t\tUD\t\tPP\t\tPD\n");
        printf("08:00 - 09:59\t\t%d\t\t%d\t\t%d\t\t%d\n", UP[0], UD[0], PP[0], PD[0]);
        printf("10:00 - 11:59\t\t%d\t\t%d\t\t%d\t\t%d\n", UP[1], UD[1], PP[1], PD[1]);
        printf("12:00 - 13:59\t\t%d\t\t%d\t\t%d\t\t%d\n", UP[2], UD[2], PP[2], PD[2]);
        printf("14:00 - 15:59\t\t%d\t\t%d\t\t%d\t\t%d\n", UP[3], UD[3], PP[3], PD[3]);
        printf("16:00 - 17:59\t\t%d\t\t%d\t\t%d\t\t%d\n", UP[4], UD[4], PP[4], PD[4]);
        printf("18:00 - 19:59\t\t%d\t\t%d\t\t%d\t\t%d\n", UP[5], UD[5], PP[5], PD[5]);

        printf("\n");
    }
}

// K PRIKAZU O
void vymen(int *xp, int *yp){
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}
// K PRIKAZU O
void bubbleSort(int pole[], int n/*POCET ZAZNAMOV*/, char *** ppp_nazov_prispevku, char *** ppp_mena_autorov, char *** ppp_typ_prezentovania, char *** ppp_cas_prezentovania, char *** ppp_datum){
    // VYPISEM SI POLIA
    // printf("KONTROLNY VYPIS\n");
    // for(int i = 0; i < 3; i++){
    //     printf("%d --->%s\n", i, (*ppp_cas_prezentovania)[i]);
    // }

    int a, b;
    int help1 = 0;
    int help2 = 0;
    for (a = 0; a < n-1; a++){
 
        for (b = 0; b < n-a-1; b++){
            // AK ppp_cas_prezentovania[ pole[j] ] > ppp_cas_prezentovania [ pole[j+1] ]
            //     TAK vymen(&pole[j], &pole[j+1])
            help1 = atoi((*ppp_cas_prezentovania)[ pole[b] ]);
            help2 = atoi((*ppp_cas_prezentovania)[ pole[b+1] ]);
            
            if(help1 > help2){
                vymen(&pole[b], &pole[b+1]);
            }
        }
    } 
    /*
    int help = 0;
    printf("VYPIS PREORGANIZOVANEHO POLA VO FUNKCII bubbleSort\n");
    for(int i = 0; i < n; i++){
        //printf("UP,UD - ZHODNE VYSLEDKY index: %d\n", vyskyt_UP_UD[i]);
        
        help = pole[i];
        printf("%s\t%s\t%s\t%s\t\n", (*ppp_cas_prezentovania)[help], (*ppp_typ_prezentovania)[help], (*ppp_mena_autorov)[help], (*ppp_nazov_prispevku)[help]);
    }
    */  
}
void zmen_enter_za_koniec_retazca(char pole[], int velkost_pola){
    for(int i = 0; i < velkost_pola; i++){
        if( pole[i] == '\n' ){
            pole[i] = '\0';
        }
    }
}
void prikaz_o(FILE * subor, char *** ppp_nazov_prispevku, char *** ppp_mena_autorov, char *** ppp_typ_prezentovania, char *** ppp_cas_prezentovania, char *** ppp_datum, long *** ppp_datum_citatelne, int * p_pocet_zaznamov){

    // NACITANIE DATUMU
    char input_datum[9];
    memset(input_datum, '\0', 9);

    //printf("Zadaj datum: ");        
    scanf("%8s", input_datum);
    fflush(stdin);
    //printf("ZADANY DATUM: %s\n", input_datum);

    // AK JE SUBOR OTVORENY
    if(subor == NULL){
        printf("\n");
    }
    if(subor != NULL){
        //printf("Subor je otvoreny, som v O\n");

        // AK NIESU POLIA VYTVORENE, ale SUBOR JE OTVORENY --> VYPIS ZO SUBORU
        if( ((*ppp_nazov_prispevku) == NULL) && ((*ppp_mena_autorov) == NULL) && ((*ppp_typ_prezentovania) == NULL) && ((*ppp_cas_prezentovania) == NULL) && ((*ppp_datum) == NULL) && ((*ppp_datum_citatelne) == NULL)){
            //printf("Subor je otvoreny, polia niesu vytvorene, som v O\n");
            ////// VYPIS AK JE SUBOR OTVORENY, POLIA NEVYTVORENE
            rewind(subor);

            char nazov[152];
            memset(nazov, '\0', 152);
            char meno[102];
            memset(meno, '\0', 102);
            char typ[4];
            memset(typ, '\0', 4);
            char cas[6];
            memset(cas, '\0', 6);
            char datum[10];
            memset(datum, '\0', 10);
            char zmaz;
            int print_enter = 0;

            //////// VYPOCET ZAZNAMOV ////////
            int pocet_zaznamov = 1;
            char actual;
            char before;
            //SPOCITANIE ZAZNAMOV
            while (!feof(subor)){
                actual = fgetc(subor);
                if (actual == '\n' && before == '\n'){
                    pocet_zaznamov++;
                }
                before = actual;
            }
            rewind(subor);
            //////// VYPOCET ZAZNAMOV ////////

            // PRECITA CELY SUBOR, VYPISE IBA ZAZNAMY s UP a UD
            for(int i = 0; i < pocet_zaznamov; i++){
                fgets(nazov, 152, subor);
                fgets(meno, 102, subor);
                fgets(typ, 4, subor);
                fgets(cas, 6, subor);
                fgets(datum, 10, subor);
                zmaz = fgetc(subor);

                // NAHRADENIE '\n' za '\0'
                zmen_enter_za_koniec_retazca( nazov, (sizeof(nazov)/sizeof(char)) );
                zmen_enter_za_koniec_retazca( meno, (sizeof(meno)/sizeof(char)) );
                zmen_enter_za_koniec_retazca( typ, (sizeof(typ)/sizeof(char)) );
                zmen_enter_za_koniec_retazca( cas, (sizeof(cas)/sizeof(char)) );
                zmen_enter_za_koniec_retazca( datum, (sizeof(datum)/sizeof(char)) );

                if( strcmp(datum, input_datum) == 0 ){  // AK JE ZHODNY PRECITANY DATUM S INPUT_DATUM
                    if( ( strcmp(typ, "UP") == 0 ) || (strcmp(typ, "UD") == 0)){    // AK JE ZHODNY TYP S UP/UD
                    printf("%s\t", cas);
                    printf("%s\t", typ);
                    printf("%s\t", meno);
                    printf("%s\t\n", nazov);
                    }
                }
            }
            rewind(subor);
            // PRECITA CELY SUBOR, VYPISE IBA ZAZNAMY s PP a PD
            for(int i = 0; i < pocet_zaznamov; i++){
                fgets(nazov, 152, subor);
                fgets(meno, 102, subor);
                fgets(typ, 4, subor);
                fgets(cas, 6, subor);
                fgets(datum, 10, subor);
                zmaz = fgetc(subor);

                // NAHRADENIE '\n' za '\0'
                zmen_enter_za_koniec_retazca( nazov, (sizeof(nazov)/sizeof(char)) );
                zmen_enter_za_koniec_retazca( meno, (sizeof(meno)/sizeof(char)) );
                zmen_enter_za_koniec_retazca( typ, (sizeof(typ)/sizeof(char)) );
                zmen_enter_za_koniec_retazca( cas, (sizeof(cas)/sizeof(char)) );
                zmen_enter_za_koniec_retazca( datum, (sizeof(datum)/sizeof(char)) );

                if( strcmp(datum, input_datum) == 0 ){  // AK JE ZHODNY PRECITANY DATUM S INPUT_DATUM
                    if( ( strcmp(typ, "PP") == 0 ) || (strcmp(typ, "PD") == 0)){    // AK JE ZHODNY TYP S PP/PD
                        // VYPIS ENTRU AK SA NACHADZA ASPON JEDNO PP/PD
                        if(print_enter == 0){
                            printf("\n");
                        }
                        print_enter++;

                    printf("%s\t", cas);
                    printf("%s\t", typ);
                    printf("%s\t", meno);
                    printf("%s\t\n", nazov);
                    }
                }
            }
            rewind(subor);
            
            ////// VYPIS AK JE SUBOR OTVORENY, POLIA NEVYTVORENE
        }

        // AK SU POLIA VYTVORENE, subor OTVORENY    --> VYPIS Z POLI
        if( ((*ppp_nazov_prispevku) != NULL) && ((*ppp_mena_autorov) != NULL) && ((*ppp_typ_prezentovania) != NULL) && ((*ppp_cas_prezentovania) != NULL) && ((*ppp_datum) != NULL) && ((*ppp_datum_citatelne) != NULL)){
            //printf("Polia su vytvorene, som v O\n");

            int *vyskyt_UP_UD;
            int vyskyt_count_UP_UD = 0;
            vyskyt_UP_UD = (int *) malloc((*p_pocet_zaznamov) * sizeof(int));
            memset(vyskyt_UP_UD, 0, ((*p_pocet_zaznamov) * sizeof(int)) );

            int *vyskyt_PP_PD;
            int vyskyt_count_PP_PD = 0;
            vyskyt_PP_PD = (int *) malloc((*p_pocet_zaznamov) * sizeof(int));
            memset(vyskyt_PP_PD, 0, ((*p_pocet_zaznamov) * sizeof(int)) );

            int help = 0;
            //printf("POCET ZAZNAMOV: %d\n", (*p_pocet_zaznamov));

            ///////////////////////////////
            // ZHODA INPUT_DATUM s DATUM V ZAZNAME (UP,UD)
            for(int i = 0; i < (*p_pocet_zaznamov); i++){
                if((strcmp(input_datum, (*ppp_datum)[i]) == 0 )){   // AK INPUT_DATUM JE ZHODNY S DATUMOM V ZAZNAME
                    //printf("NAZOV: %s  DATUM: %s\n", (*ppp_nazov_prispevku)[i], (*ppp_datum)[i]);

                    // AK JE UP alebo UD
                    if( (strcmp("UP", (*ppp_typ_prezentovania)[i]) == 0 ) || (strcmp("UD", (*ppp_typ_prezentovania)[i]) == 0 ) ){
                        //printf("UPvetvaNAZOV: %s  TYP: %s\n", (*ppp_nazov_prispevku)[i], (*ppp_typ_prezentovania)[i]);
                        
                        // ZAPISEM INDEXY CO SU UP/UD a ZHODUJU SA S DATUMOM
                        vyskyt_UP_UD[vyskyt_count_UP_UD] = i;
                        vyskyt_count_UP_UD++;
                        
                    }
                }
            }
            // ZHODA INPUT_DATUM s DATUM V ZAZNAME (PP,PD)
            for(int i = 0; i < (*p_pocet_zaznamov); i++){
                if((strcmp(input_datum, (*ppp_datum)[i]) == 0 )){   // AK INPUT_DATUM JE ZHODNY S DATUMOM V ZAZNAME
                    // AK JE PP alebo PD
                    if( (strcmp("PP", (*ppp_typ_prezentovania)[i]) == 0 ) || (strcmp("PD", (*ppp_typ_prezentovania)[i]) == 0 ) ){
                        //printf("PPvetvaNAZOV: %s  TYP: %s\n", (*ppp_nazov_prispevku)[i], (*ppp_typ_prezentovania)[i]);

                        // ZAPISEM INDEXY CO SU PP/PD a ZHODUJU SA S DATUMOM
                        vyskyt_PP_PD[vyskyt_count_PP_PD] = i;
                        vyskyt_count_PP_PD++;
                    }
                }
            }
            if(vyskyt_count_UP_UD != 0){
                // ZORADENIE PODLA CASU
                bubbleSort(vyskyt_UP_UD/*POLE KDE SU AKO HODNOTY ZAPISANE ZHODNE s UP,UD*/, vyskyt_count_UP_UD/*CISLO KOLKO JE ZHODNYCH*/, ppp_nazov_prispevku, ppp_mena_autorov, ppp_typ_prezentovania, ppp_cas_prezentovania, ppp_datum);

                // VYPISANIE POLA VYSKYT_UP_UD  --> tu mam zapisane indexy zhodnych
                help = 0;
                for(int i = 0; i < vyskyt_count_UP_UD; i++){
                    //printf("UP,UD - ZHODNE VYSLEDKY index: %d\n", vyskyt_UP_UD[i]);
                    
                    help = (vyskyt_UP_UD)[i];
                    printf("%s\t%s\t%s\t%s\t\n", (*ppp_cas_prezentovania)[help], (*ppp_typ_prezentovania)[help], (*ppp_mena_autorov)[help], (*ppp_nazov_prispevku)[help]);
                }
                printf("\n");
            }
            if(vyskyt_count_PP_PD != 0){
                // ZORADENIE PODLA CASU
                bubbleSort(vyskyt_PP_PD/*POLE KDE SU AKO HODNOTY ZAPISANE ZHODNE s PP,PD*/, vyskyt_count_PP_PD/*CISLO KOLKO JE ZHODNYCH*/, ppp_nazov_prispevku, ppp_mena_autorov, ppp_typ_prezentovania, ppp_cas_prezentovania, ppp_datum);

                //printf("\n");
                // VYPISANIE POLA VYSKYT_PP_PD  --> tu mam zapisane indexy zhodnych
                help = 0;
                for(int i = 0; i < vyskyt_count_PP_PD; i++){
                    //printf("PP,PD - ZHODNE VYSLEDKY index: %d\n", vyskyt_PP_PD[i]);
                    help = (vyskyt_PP_PD)[i];
                    printf("%s\t%s\t%s\t%s\t\n", (*ppp_cas_prezentovania)[help], (*ppp_typ_prezentovania)[help], (*ppp_mena_autorov)[help], (*ppp_nazov_prispevku)[help]);
                }
                printf("\n");
            }

            ///////////////////////////////

            free(vyskyt_UP_UD);
            vyskyt_UP_UD = NULL;
            free(vyskyt_PP_PD);
            vyskyt_PP_PD = NULL;
        }

    }
}

void prikaz_z(FILE * subor, char *** ppp_nazov_prispevku, char *** ppp_mena_autorov, char *** ppp_typ_prezentovania, char *** ppp_cas_prezentovania, char *** ppp_datum, long *** ppp_datum_citatelne, int * p_pocet_zaznamov){
    // AK DYNAMICKE POLIA NIESU VYTVORENE
    if ( ((*ppp_nazov_prispevku) == NULL) && ((*ppp_mena_autorov) == NULL) && ((*ppp_typ_prezentovania) == NULL) && ((*ppp_cas_prezentovania) == NULL) && ((*ppp_datum) == NULL) && ((*ppp_datum_citatelne) == NULL) ){
        printf("Polia nie su vytvorene\n\n");
    }
    // AK DYNAMICKE POLIA SU VYTVORENE
    if( ((*ppp_nazov_prispevku) != NULL) && ((*ppp_mena_autorov) != NULL) && ((*ppp_typ_prezentovania) != NULL) && ((*ppp_cas_prezentovania) != NULL) && ((*ppp_datum) != NULL) && ((*ppp_datum_citatelne) != NULL) ){
        // NACITA NAZOV        
        char input_nazov[151];
        memset(input_nazov, '\0', 151);
        int count_nazov = 0;

        //printf("Zadaj nazov: ");        
        //scanf("%150s", input_nazov);
        scanf("%150[^\n]", input_nazov);  // [^\n] - povie kompilatoru nech cita znaky az po prvy vyskyt '\n'     // https://www.includehelp.com/c/c-program-to-read-string-with-spaces-using-scanf-function.aspx
        fflush(stdin);

        // SPOCITANIE ZHODNYCH ZAZNAMOV
        for(int i = 0; i < (*p_pocet_zaznamov); i++){
            if((strcmp(input_nazov, (*ppp_nazov_prispevku)[i]) == 0 )){   // AK JE INPUT_NAZOV ZHODNY S NAZVOM V ZAZNAME
                count_nazov++;
            }
        }

        // POMOCNE PREMENNE
        int tmp = 0;
        int index = 1;

        //printf("Vymazalo sa : %d zaznamov !\n", count_nazov);
        //printf("PRED: %s\n", (*ppp_nazov_prispevku)[0]);
        //strcpy((*ppp_nazov_prispevku)[0], "Prepisany zaznam :)");
        //printf("PO: %s\n", (*ppp_nazov_prispevku)[0]);

        for(int i = 0; i < (*p_pocet_zaznamov); i++){
            if((strcmp(input_nazov, (*ppp_nazov_prispevku)[i]) == 0 )){   // AK JE INPUT_NAZOV ZHODNY S NAZVOM V ZAZNAME
                
                // I-ty je zhodny s inputom, tak z posledneho vsetko dam sem

                //int tmp = (*p_pocet_zaznamov)-1; // TERAZ UKAZUJE NA POSLEDNY PRVOK
                tmp = (*p_pocet_zaznamov) - index;
                
                strcpy((*ppp_nazov_prispevku)[i], (*ppp_nazov_prispevku)[tmp]);
                strcpy((*ppp_mena_autorov)[i], (*ppp_mena_autorov)[tmp]);
                strcpy((*ppp_typ_prezentovania)[i], (*ppp_typ_prezentovania)[tmp]);
                strcpy((*ppp_cas_prezentovania)[i], (*ppp_cas_prezentovania)[tmp]);
                strcpy((*ppp_datum)[i], (*ppp_datum)[tmp]);

                index++; // UKAZUJE NA POSLEDNE MIESTO POLA
                
            }
        }

        // DEALOKOVANIE NEPOTREBNYCH 
        // AK SU 2 ZHODNE --> INDEX = 2
        index--;
        for(int i = 0; i < index; i++){ // PREBEHNE TOLKO KRAT KOLKO MAM ZHODNYCH PRISPEVKOV
            // NAZOV PRISPEVKU
            free( (*ppp_nazov_prispevku)[((*p_pocet_zaznamov) - 1 - i)] ); // NA POSLEDNOM MIESTE

            // MENA AUTOROV
            free( (*ppp_mena_autorov)[((*p_pocet_zaznamov) - 1 - i)] ); // NA POSLEDNOM MIESTE

            // TYP
            free( (*ppp_typ_prezentovania)[((*p_pocet_zaznamov) - 1 - i)] ); // NA POSLEDNOM MIESTE

            // CAS
            free( (*ppp_cas_prezentovania)[((*p_pocet_zaznamov) - 1 - i)] ); // NA POSLEDNOM MIESTE

            // DATUM
            free( (*ppp_datum)[((*p_pocet_zaznamov) - 1 - i)] ); // NA POSLEDNOM MIESTE

            // DATUM CITATELNE
            free( (*ppp_datum_citatelne)[((*p_pocet_zaznamov) - 1 - i)] ); // NA POSLEDNOM MIESTE

        }
        
        // ZMENSIT POCET ZAZNAMOV O --> count_nazov (zhodne)
        *p_pocet_zaznamov = (*p_pocet_zaznamov) - count_nazov;

        // REALOKOVANIE (vymazanie nepotrebnych na konci)
        *ppp_nazov_prispevku = (char **) realloc(*ppp_nazov_prispevku,  ((*p_pocet_zaznamov) * sizeof(char *)) );
        *ppp_mena_autorov = (char **) realloc(*ppp_mena_autorov,  ((*p_pocet_zaznamov) * sizeof(char *)) );
        *ppp_typ_prezentovania = (char **) realloc(*ppp_typ_prezentovania,  ((*p_pocet_zaznamov) * sizeof(char *)) );
        *ppp_cas_prezentovania = (char **) realloc(*ppp_cas_prezentovania,  ((*p_pocet_zaznamov) * sizeof(char *)) );
        *ppp_datum = (char **) realloc(*ppp_datum,  ((*p_pocet_zaznamov) * sizeof(char *)) );
        *ppp_datum_citatelne = (long **) realloc(*ppp_datum_citatelne,  ((*p_pocet_zaznamov) * sizeof(long *)) );
  

        printf("Vymazalo sa : %d zaznamov !\n\n", count_nazov);

    } // KONIEC IF-u
}

int main(){
    //PREMENNA check PRE KONTROLU ZADANEHO ZNAKU
    char check;
    // prikaz k - nastavi na 1 a while cyklus a program skonci
    int main_loop_end = 0;

    // UKAZOVATEL NA SUBOR v prikaz_v() pre priamy vypis zo subora
    FILE *subor = NULL;

    // DYNAMICKE POLIA
    char ** nazov_prispevku = NULL;
    char ** mena_autorov = NULL;
    char ** typ_prezentovania = NULL;
    char ** cas_prezentovania = NULL;
    char ** datum = NULL;
    long ** datum_citatelne = NULL;

    int pocet_zaznamov = 1;

    // POMOCNA PRE prikaz_z
    int ak_z_stacene = 0;

    while (main_loop_end == 0){
        
        //printf("\nZadaj pismeno:");
		scanf("%c", &check);

		//VYMAZE ZNAK NOVEHO RIADKU KTORE PRIDA scanf
		getchar();
        //VYCISTI BUFFER (scanf prebehne iba raz, pouzije sa prve pismeno)
        fflush(stdin);

        switch (check){
            //PRIKAZ V
            case 'v':
                //printf("PRIKAZ V\n\n");
                // OTVORI SUBOR
                // AK SU ALOKOVANE POLIA        --> VYPIS ZPOLI
                // AK NIE SU ALOKOVANIE POLIA   --> VYPIS ZO SUBORU
                subor = prikaz_v(subor, &nazov_prispevku, &mena_autorov, &typ_prezentovania, &cas_prezentovania, &datum, &datum_citatelne, &pocet_zaznamov);

                break;

            //PRIKAZ O         >>>>>>>>>>>>> POTREBUJEM ZORADIT CASOVO A PRIAMY VYPIS ZO SUBORU
            case 'o':
                //printf("PRIKAZ O\n\n");
                // AK SU DYNAM POLIA VYTVORENE      --> VYPIS Z POLI
                // AK NIESU DYNAM POLIA VYTVORENE   --> VYPIS ZO SUBORU

                // NACITAM DATUM ( RRRRMMDD )
                // VYPISE ZOZNAM S ROVNAKYM DATUMOM
                    // ZORADENE PODLA CASU
                    // ROZDELENE NA UP,UD  a  PP,PD
                
                prikaz_o(subor, &nazov_prispevku, &mena_autorov, &typ_prezentovania, &cas_prezentovania, &datum, &datum_citatelne, &pocet_zaznamov);                
                break;

            //PRIKAZ N
            case 'n':
                //printf("PRIKAZ N\n\n");

                // VYTVORI POLIA SO ZAZNAMAMI
                // AK BOLI UZ VYTVORENE, NAJPRV DEALOKUJE A AZ POTOM ALOKUJE
                prikaz_n(subor, &nazov_prispevku, &mena_autorov, &typ_prezentovania, &cas_prezentovania, &datum, &datum_citatelne, &pocet_zaznamov, ak_z_stacene);                

                break;

            //PRIKAZ S
            case 's':
                //printf("PRIKAZ S\n\n");
                // FUNKCIA NACITA DATUM (RRRRMMDD) a TYP PREZENTOVANIA (PD, UD, PP, UP)
                // VYPISE POLOZKY KTORE ZODPOVEDAJU INPUTU (CAS, TYP, NAZOV)

                prikaz_s(subor, &nazov_prispevku, &mena_autorov, &typ_prezentovania, &cas_prezentovania, &datum, &datum_citatelne, &pocet_zaznamov);

                break;

            //PRIKAZ H
            case 'h':
                //printf("PRIKAZ H\n\n");
                // VYPISE HISTOGRAM (TYP A HODINY v 2h intervaloch)
                prikaz_h(subor, &nazov_prispevku, &mena_autorov, &typ_prezentovania, &cas_prezentovania, &datum, &datum_citatelne, &pocet_zaznamov);
                break;

            //PRIKAZ Z
            case 'z':
                //printf("PRIKAZ Z\n\n");
                // NACITA NAZOV PRISPEVKU A ZMAZE VSETKY S ROVNAKYM NAZVOM Z DYNAM POLI
                prikaz_z(subor, &nazov_prispevku, &mena_autorov, &typ_prezentovania, &cas_prezentovania, &datum, &datum_citatelne, &pocet_zaznamov);
                ak_z_stacene = 1;
                break;

            //PRIKAZ P
            case 'p':
                //printf("PRIKAZ P\n\n");
                // PRIDANIE ZAZNAMU DO DYNAM POLI

                prikaz_p(subor, &nazov_prispevku, &mena_autorov, &typ_prezentovania, &cas_prezentovania, &datum, &datum_citatelne, &pocet_zaznamov);

                break;


            //KONIEC PROGRAMU
            case 'k':
                //DEALOKOVANIE POLI, ak boli vytvorene
                //UZAVRETIE SUBOR, ak bol vytvoreny
                //TATO VOLBA BEZ VYSTUPU
                
                //printf("UKONCUJEM PROGRAM !\n");
                main_loop_end = 1; 

                // ZATVORENIE SUBORU
                if (subor != NULL){
                    fclose(subor); // z case: 'v'
                    subor = NULL;
                }
                // UVOLNENIE DYNAMICKYCH POLI
                //NAZOV PRISPEVKU
                if (nazov_prispevku != NULL){
                    for (int i = 0; i < pocet_zaznamov; i++) {
                        //free( *(nazov_prispevku + i) );
                        free(nazov_prispevku[i]);
                        //*(nazov_prispevku + i) = NULL;
                        nazov_prispevku[i] = NULL;
                    }
                    free(nazov_prispevku);
                    nazov_prispevku = NULL;
                }
                //MENA AUTOROV
                if (mena_autorov != NULL){
                    for (int i = 0; i < pocet_zaznamov; i++) {
                        //free( *(mena_autorov + i) );
                        free(mena_autorov[i]);
                        //*(mena_autorov + i) = NULL;
                        mena_autorov[i] = NULL;
                    }
                    free(mena_autorov);
                    mena_autorov = NULL;
                }
                //TYP PREZENTOVANIA
                if (typ_prezentovania != NULL){
                    for (int i = 0; i < pocet_zaznamov; i++) {
                        //free( *(typ_prezentovania + i) );
                        free(typ_prezentovania[i]);
                        //*(typ_prezentovania + i) = NULL;
                        typ_prezentovania[i] = NULL;
                    }
                    free(typ_prezentovania);
                    typ_prezentovania = NULL;
                }
                //CAS PREZENTOVANIA
                if (cas_prezentovania != NULL){
                    for (int i = 0; i < pocet_zaznamov; i++) {
                        //free( *(cas_prezentovania + i) );
                        free(cas_prezentovania[i]);
                        //*(cas_prezentovania + i) = NULL;
                        cas_prezentovania[i] = NULL;
                    }
                    free(cas_prezentovania);
                    cas_prezentovania = NULL;
                }
                //DATUM
                if (datum != NULL){
                    for (int i = 0; i < pocet_zaznamov; i++) {
                        //free( *(datum + i) );
                        free(datum[i]);
                        //*(datum + i) = NULL;
                        datum[i] = NULL;
                    }
                    free(datum);
                    datum = NULL;
                }
                //DATUM CITATELNE
                if (datum_citatelne != NULL){
                    for (int i = 0; i < pocet_zaznamov; i++) {
                        //free( *(datum_citatelne + i) );
                        free(datum_citatelne[i]);
                        //*(datum_citatelne + i) = NULL;
                        datum_citatelne[i] = NULL;
                    }
                    free(datum_citatelne);
                    datum_citatelne = NULL;
                }
                // END - UVOLNENIE DYNAMICKYCH POLI
  
                break;
        
            //AK NEPOZNA PRIKAZ
            default:
                //printf("Zadane pismeno nepoznam\n");
                break;
        }

    }
    


    return 0;
}
#include <stdio.h>		//VSTUPY A VYSTUPY
#include <string.h>		//PRACA S RETAZCAMI
#include <stdlib.h>		//PRACA S PAMATOU


void nacitanie(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov) {
	char c;
	FILE *subor_citanie;

	int data = 0;

	int cislo_jazdca = 0;
	int aktualny_znak = 0;
	int vozidlo_aktualny_znak = 0;

	int casy_cislo = 0;

	int skip_reading = 0;

	memset(meno_priezvisko, '\0', 200);
	memset(priezvisko, '\0', 200);
	memset(pohlavie, '\0', 200);
	memset(rok, '\0', 200);
	memset(vozidlo, '\0', 200);
	memset(cas, '\0', 200);

	//OTVORENIE SUBORU + KONTROLA OTVORENIA SUBORU
	if ((subor_citanie = fopen("jazdci.csv", "r")) == NULL)
		printf("SUBOR SA NEPODARILO OVTORIT !\n");
	else{

		while (!feof(subor_citanie)){ 		//feof() TESTUJE KONIEC SUBORU
			if(skip_reading == 0) {
				c = getc(subor_citanie);
			}
			skip_reading = 0;
			if(c == '\n'){
				cislo_jazdca++;

				//RESET POMOCNYCH PREMENNYCH
				aktualny_znak = 0;
				data = 0;
				vozidlo_aktualny_znak = 0;
				casy_cislo = 0;

				continue;
			}
			if (c == ';'){
				data++;
				continue;	//preskoci celu iteraciu cyklu (preskoci ;)
			}
			//MENO A PRIEZVISKO
			if(data == 0){
				meno_priezvisko[cislo_jazdca][aktualny_znak] = c;
				aktualny_znak++;
			}
			//POHLAVIE
			if(data == 1){
				pohlavie[cislo_jazdca] = c;
			}
			//ROK
			if(data == 2){
				char temp[4];	// docasne pole pre konverziu roku v char na int
				int r = 0;
				while(r < 4){
					temp[r] = c;
					c = getc(subor_citanie);
					r++;
				}
				rok[cislo_jazdca] = atoi(temp);	//atoi() KONVERTUJE STRING na CISLO (int)
				skip_reading = 1;
				continue;

			}
			if(data == 3){
				vozidlo[cislo_jazdca][vozidlo_aktualny_znak] = c;
				vozidlo_aktualny_znak++;
			}
			if(data >= 4){
				char temp[6];	// docasne pole pre konverziu casu v char na float
				int r = 0;
				while(r < 6){
					temp[r] = c;
					c = getc(subor_citanie);
					r++;
				}
				cas[cislo_jazdca][casy_cislo] = atof(temp);		//atof() KONVERTUJE STRING na FLOAT
				casy_cislo++;
				
				skip_reading = 1;
				continue;
			}
		}
		//ZISTENIE KONECNEHO CISLA JAZDCOV +1 (pocitam od 0) 
		*celkovy_pocet_jazdcov = cislo_jazdca+1;

		//printf("CELKOVY JAZDCOV: %d\n", *celkovy_pocet_jazdcov);
	}
	
	//ZATVORENIE SUBORU + KONTROLA ZATVORENIA SUBORU
	if (fclose(subor_citanie) == EOF)printf("SUBOR SA NEPODARILO ZATVORIT !\n");
}

void rozdel_priezvisko(char meno_priezvisko[200][50], char priezvisko[200][50], int *celkovy_pocet_jazdcov){
	//printf("CELKOVY POCET JAZDCOV: %d\n", *celkovy_pocet_jazdcov);
	
	//PRIEZVISKO

	memset(priezvisko, '\0', 200);
	
	int i = 0;
	char temp[50];
	char temp_priezvisko[50];

	for (i = 0; i < *celkovy_pocet_jazdcov; i++){

		//PRI KAZDEJ ITERACII NASTAVI temp_priezvisko a temp na \0 - čize prázdna hodnota
		memset(temp_priezvisko, '\0', sizeof(temp_priezvisko));
		memset(temp, '\0', sizeof(temp));

		//SKOPIRUJE meno_priezvisko[i] do temp
		strcpy(temp, meno_priezvisko[i]);

		//CELY STRING POJDE OD ZADU
		strrev(temp);
		
		for (int j = 0; j < strlen(temp); j++){
			if(temp[j] == ' '){
				//V ELSE NACITAME PRIEZVISKO PO PRVU MEDZERU OD ZADU, potom BREAK
				temp_priezvisko[j] = '\0';
				break;
			}
			else{
				temp_priezvisko[j] = temp[j];
			}
		}
		strrev(temp_priezvisko); //PREHODIM SPAT PRIEZVISKO NACITANE ODZADU
		strcpy(priezvisko[i], temp_priezvisko);
		
	}
}

void sum(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){
	
	printf("\n");
	for (int i = 0; i < *celkovy_pocet_jazdcov; i++){
		printf("%s, ", meno_priezvisko[i]);				//VYPISE MENO A PRIEZVISKO

		printf("nar. %d, ", rok[i]);					//VYPISE ROK

		if (pohlavie[i] == 'm'){						//VYPISE MUZ
			printf("muz, ");							//
		}												//ALEBO
		else if(pohlavie[i] == 'f'){					//VYPISE ZENA
			printf("zena, ");
		}

		if( (strcmp(vozidlo[i], "bugatti") == 0) || (strcmp(vozidlo[i], "ferrari") == 0 ) || (strcmp(vozidlo[i], "porsche") == 0 ) || (strcmp(vozidlo[i], "honda") == 0 ) ){
			printf("Automobil: %s\n", vozidlo[i]);			//VYPISE AUTOMOBIL
		}
		else{
			printf("Automobil: %s\n", "neznamy");
		}

		printf("Casy okruhov: ");						//VYPISE CASY OKRUHOV
		for (int p = 0; p < 5; p++)	{
		printf("%.3f", cas[i][p]);
		if(p < 4) printf(";");							//NEVYPISE POSLEDU ; PRI CASOCH
		}
		printf("\n");
	}
}

void driver(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){

		int cislo_jazdca_driver = 0;
		int no_found_driver = 0;			//KONTROLA CI SA NACHADZA ZADANE PRIEZVISKO V .CSV SUBORE

		char driver_choose[200];
		memset(driver_choose, '\0', sizeof(driver_choose));

		printf("Zadajte meno jazdca: ");
		scanf("%s", &driver_choose);
		getchar();

		printf("\n");

		//DOSTANEM SA NA VYBRANEHO JAZDCA a zistim jeho INDEX (CISLO)
		int f = 0;
		for (f = 0; f < *celkovy_pocet_jazdcov; f++){

			//VYBERIE CISLO RIADKU(JAZDCA) pre dosadenie
			if(strcmp(priezvisko[f], driver_choose) == 0){
				cislo_jazdca_driver = f;
				break;
			}
			if(f == (*celkovy_pocet_jazdcov)-1){
				printf("Zadane priezvisko sa v subore nenaslo.\n");
				no_found_driver = 1;		//KONTROLA CI SA NACHADZA ZADANE PRIEZVISKO V .CSV SUBORE
			}
		}
		
		if (no_found_driver == 0){			//KONTROLA CI SA NACHADZA ZADANE PRIEZVISKO V .CSV SUBORE
			
			//printf("PRIEZVISKO: %s\n", priezvisko[cislo_jazdca_driver]);

			printf("%s\n", meno_priezvisko[cislo_jazdca_driver]);			//VYPISE MENO A PRIEZVISKO

			printf("nar. %d, ", rok[cislo_jazdca_driver]);					//VYPISE ROK

			if (pohlavie[cislo_jazdca_driver] == 'm'){						//VYPISE MUZ
				printf("muz\n");											//
			}																//ALEBO
			else if(pohlavie[cislo_jazdca_driver] == 'f'){					//VYPISE ZENA
				printf("zena\n");
			}

			printf("Automobil: %s\n\n", vozidlo[cislo_jazdca_driver]);		//VYPISE AUTOMOBIL

			printf("Casy okruhov: ");										//VYPISE CASY OKRUHOV
			for (int p = 0; p < 5; p++)	{
			printf("%.3f", cas[cislo_jazdca_driver][p]);
			if(p < 4) printf(";");											//NEVYPISE POSLEDU ; PRI CASOCH
			}
			printf("\n\n");

			//////////// VYPOCET A VYPIS NAJLEPSIEHO CASU ////////////
			float time_count[5];	//POLE PRE 5 CASOV VYBRANEHO JAZDCA
			memset(time_count, '\0', sizeof(time_count));

			float temp_best = 0;
			int counter_best = 0;

			for(counter_best = 0; counter_best < 5; counter_best++){ 	
				time_count[counter_best] = cas[cislo_jazdca_driver][counter_best]; 			//SKOPIRUJE CASY JAZDCA (cislo_jazdca_driver) DO POMOCNEHO POLA
				//printf("CASY: %.3f\n", time_count[counter_best]);
			}	
			
			if (time_count[0] < time_count[0+1]){
				temp_best = time_count[0];
				//printf("TEMP: %.3f\n", temp_best);
			}
			else if(time_count[0] > time_count[0+1]){
				temp_best = time_count[0+1];
				//printf("TEMP: %.3f\n", temp_best);
			}
			for (int a = 1; a < 5; a++){ 			//VYKONA SA 4 KRAT + VYSSIE SA VYKONA RAZ
				if (temp_best < time_count[a]){
					temp_best = temp_best;
					//printf("TEMP: %.3f\n", temp_best);
				}
				else if (temp_best > time_count[a]){
					temp_best = time_count[a];
					//printf("TEMP: %.3f\n", temp_best);
				}	
			}
			printf("Najlepsie kolo: %.3f\n", temp_best);

			//////////// VYPOCET A VYPIS NAJHORSIEHO CASU ////////////
			float temp_worst=0;

			if (time_count[0] > time_count[0+1]){			//VYKONA SA PRVY KRAT
				temp_worst = time_count[0];					// 
			}												//
			else if(time_count[0] < time_count[0+1]){		//VYKONA SA PRVY KRAT
				temp_worst = time_count[0+1];
			}
			for (int a = 1; a < 5; a++){ 					//VYKONA SA ZVYSNY 4 KRAT
				if (temp_worst > time_count[a]){
					temp_worst = temp_worst;
				}
				else if (temp_worst < time_count[a]){
					temp_worst = time_count[a];
				}	
			}
			printf("Najhorsie kolo: %.3f\n", temp_worst);

			//////////// VYPOCET A VYPIS PRIEMERNEHO KOLA ////////////
			float temp_average = (time_count[0] + time_count[1] + time_count[2] + time_count[3] + time_count[4]) / 5;
			printf("Priemerne kolo: %.3f\n", temp_average);

		}
}

void lap(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){

	float najlepsi_cas[200][50];
	memset(najlepsi_cas, '\0', 200);

	float temp_best = 0;
	
	int cislo_kola[200][1];		//cislo_kola - ulozim sem cislo (poradie od 0 - 4) najlepsieho kola pre kazdeho hraca
	memset(cislo_kola, '\0', 200);

	for (int c = 0; c < *celkovy_pocet_jazdcov; c++){ //VYPOCITA NAJLEPSI CAS PRE KAZDEHO JAZDCA

		float time_count[5];
		memset(time_count, '\0', sizeof(time_count));
		
		
		for(int counter_best = 0; counter_best < 5; counter_best++){ 	
			time_count[counter_best] = cas[c][counter_best]; 			//SKOPIRUJE CASY JAZDCA (cislo_jazdca_driver) DO POMOCNEHO POLA
		}	
		
		if (time_count[0] < time_count[0+1]){		//VYKONA SA PRVYKRAT
			temp_best = time_count[0];				//
		}											//
		else if(time_count[0] > time_count[0+1]){	//
			temp_best = time_count[0+1];			//
		}											//
		for (int a = 1; a < 5; a++){ 				//VYKONA SA ZVYSNYCH 4 KRAT
			if (temp_best < time_count[a]){			//
				temp_best = temp_best;				//
			}										//
			else if (temp_best > time_count[a]){	//
				temp_best = time_count[a];			//
			}										//
		}											//

		//printf("Najlepsie kolo pre jazdca %d: %.3f\n", c, temp_best);
		najlepsi_cas[c][0]= temp_best;

		//printf("TEMP BEST: %f", temp_best);
		//printf(" CASY: %f\n", cas[c][0]);	
	}
	//printf("NAJLEPSIE KOLO JAZDCA 0 je %.3f", najlepsi_cas[0][0]);

	// POROVNANIE KTORY CAS JAZDCA (a) SA ROVNA NAJVYSSIEMU CASU JAZDCA (a)
	for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
		for(int x = 0; x < 5; x++){
			if(cas[a][x] == najlepsi_cas[a][0]){
				cislo_kola[a][0] = x;
			}
		}
	}

	//CHCENY VYPIS
	printf("\n");
	for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
		printf("Najlepsie kolo: %.3f\n", najlepsi_cas[a][0]);
		printf("Jazdec: %s\n", meno_priezvisko[a]);
		printf("Cislo kola: %d", cislo_kola[a][0]+1); 	// +1 PRE TO ZE POCITA KOLA OD NULY
		printf("\n\n");
	}

}

void gender(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){

	//VYPOCET NAJLEPSIEHO CASU	
	float najlepsi_cas[200][50];
	memset(najlepsi_cas, '\0', 200);

	float temp_best = 0;

	//ULOZENIE NAJVYSSIEHO CASU PRE KAZDEHO JAZDCA
	int cislo_kola[200];
	memset(cislo_kola, '\0', 200);

	//////////VYPOCET NAJLEPSIEHO CASU PRE KAZDEHO JAZDCA//////////
	for (int c = 0; c < *celkovy_pocet_jazdcov; c++){ //VYPOCITA NAJLEPSI CAS PRE KAZDEHO JAZDCA

		float time_count[5];
		memset(time_count, '\0', sizeof(time_count));

		for(int counter_best = 0; counter_best < 5; counter_best++){ 	
			time_count[counter_best] = cas[c][counter_best]; 			//SKOPIRUJE CASY JAZDCA (cislo_jazdca_driver) DO POMOCNEHO POLA
			//printf("CASY: %.3f\n", time_count[counter_best]);
		}	
		
		if (time_count[0] < time_count[0+1]){
			temp_best = time_count[0];
			//printf("TEMP: %.3f\n", temp_best);
		}
		else if(time_count[0] > time_count[0+1]){
			temp_best = time_count[0+1];
			//printf("TEMP: %.3f\n", temp_best);
		}
		for (int a = 1; a < 5; a++){ 			//VYKONA SA 4 KRAT + VYSSIE SA VYKONA RAZ
			if (temp_best < time_count[a]){
				temp_best = temp_best;
				//printf("TEMP: %.3f\n", temp_best);
			}
			else if (temp_best > time_count[a]){
				temp_best = time_count[a];
				//printf("TEMP: %.3f\n", temp_best);
			}	
		}
		//printf("Najlepsie kolo pre jazdca %d: %.3f\n", c, temp_best);

		najlepsi_cas[c][0]= temp_best; //PRE KAZDEHO JAZDCA c DOSADI NAJLEPSI CAS		
	}
	//////////VYPOCET NAJLEPSIEHO CASU PRE KAZDEHO JAZDCA//////////

	/////////////////////////////VYPIS/////////////////////////////
	int help_gender_choose = 0;
	char gender_choose;
	do{
		printf("\nZadajte pohlavie: ");
		scanf("%s", &gender_choose);
		getchar();

		/////////ULOZENIE NAJVYSSIEHO CASU PRE KAZDEHO JAZDCA/////////
		memset(cislo_kola, '\0', 200);
		for(int t = 0; t < *celkovy_pocet_jazdcov; t++){
			for(int x = 0; x < 5; x++){
				if(cas[t][x] == najlepsi_cas[t][0]){
					cislo_kola[t] = x;
				}
			}
		}
		/////////ULOZENIE NAJVYSSIEHO CASU PRE KAZDEHO JAZDCA/////////
		if (gender_choose == 'm'){
			help_gender_choose = 1;
			/////////////MUZI/////////////
			for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
				if(pohlavie[a] == 'm'){
					printf("\nNajlepsie kolo: %.3f\n", najlepsi_cas[a][0]);
					printf("Jazdec: %s\n", meno_priezvisko[a]);
					printf("Cislo kola: %d", (cislo_kola[a])+1); 	// +1 PRE TO ZE POCITA KOLA OD NULY
					printf("\n");
				}
			}
			/////////////MUZI/////////////
		}
		else if(gender_choose == 'f'){
			help_gender_choose = 1;
			/////////////ZENY/////////////
			for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
				if(pohlavie[a] == 'f'){
					printf("\nNajlepsie kolo: %.3f\n", najlepsi_cas[a][0]);
					printf("Jazdec: %s\n", meno_priezvisko[a]);
					printf("Cislo kola: %d", (cislo_kola[a])+1); 	// +1 PRE TO ZE POCITA KOLA OD NULY
					printf("\n");
				}
			}
			/////////////ZENY/////////////
		}
		else{
			printf("\nZADANY ZNAK NIE JE VALIDNY, SKUSTE TO ZNOVA !\n");
		}

	}while(help_gender_choose == 0);
	/////////////////////////////VYPIS/////////////////////////////
}

void brand(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){

	float najlepsi_cas[200][50];
	memset(najlepsi_cas, '\0', 200);

	float temp_best = 0;
	
	int cislo_kola[200][1];		//cislo_kola - ulozim sem cislo (poradie od 0 - 4) najlepsieho kola pre kazdeho hraca
	memset(cislo_kola, '\0', 200);

	for (int c = 0; c < *celkovy_pocet_jazdcov; c++){ //VYPOCITA NAJLEPSI CAS PRE KAZDEHO JAZDCA

		float time_count[5];
		memset(time_count, '\0', sizeof(time_count));
		
		
		for(int counter_best = 0; counter_best < 5; counter_best++){ 	
			time_count[counter_best] = cas[c][counter_best]; 			//SKOPIRUJE CASY JAZDCA (cislo_jazdca_driver) DO POMOCNEHO POLA
			//printf("CASY: %.3f\n", time_count[counter_best]);
		}	
		
		if (time_count[0] < time_count[0+1]){				//VYKONA SA PRVY KRAT
			temp_best = time_count[0];						//
		}													//
		else if(time_count[0] > time_count[0+1]){			//
			temp_best = time_count[0+1];					//
		}													//
		for (int a = 1; a < 5; a++){ 						//VYKONA SA ZVYSNY 4 KRAT
			if (temp_best < time_count[a]){					//
				temp_best = temp_best;						//
			}												//
			else if (temp_best > time_count[a]){			//
				temp_best = time_count[a];					//
			}												//
		}													//
		
		//printf("Najlepsie kolo pre jazdca %d: %.3f\n", c, temp_best);
		najlepsi_cas[c][0]= temp_best;

		//printf("TEMP BEST: %f", temp_best);
		//printf(" CASY: %f\n", cas[c][0]);		
	}
	//printf("NAJLEPSIE KOLO JAZDCA 0 je %.3f", najlepsi_cas[0][0]);

	// POROVNANIE KTORY CAS JAZDCA (a) SA ROVNA NAJVYSSIEMU CASU JAZDCA (a)
	for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
		for(int x = 0; x < 5; x++){
			if(cas[a][x] == najlepsi_cas[a][0]){
				cislo_kola[a][0] = x;
			}
		}
	}
	
	//BUGATTI
	int prvy_krat_b = 0;
	float najlepsi_cas_b = 0;

	int najlepsi_cas_b_jazdec = 0;
	int najlepsi_cas_b_kolo = 0;

	//FERRARI
	int prvy_krat_f = 0;
	float najlepsi_cas_f = 0;

	int najlepsi_cas_f_jazdec = 0;
	int najlepsi_cas_f_kolo = 0;

	//PORSCHE
	int prvy_krat_p = 0;
	float najlepsi_cas_p = 0;

	int najlepsi_cas_p_jazdec = 0;
	int najlepsi_cas_p_kolo = 0;

	//HONDA
	int prvy_krat_h = 0;
	float najlepsi_cas_h = 0;

	int najlepsi_cas_h_jazdec = 0;
	int najlepsi_cas_h_kolo = 0;
	/////////////////////////////////

	// POROVNANIE KTORY CAS JAZDCA (a) SA ROVNA NAJVYSSIEMU CASU pre vozidlo
	for(int v = 0; v < *celkovy_pocet_jazdcov; v++){
		if(strcmp(vozidlo[v], "bugatti") == 0){
			//printf("%s - VOZIDLO JE BUGATTI\n", meno_priezvisko[v]);
			//////////////////////////////////////////////////////
			if (prvy_krat_b == 0){
				najlepsi_cas_b = najlepsi_cas[v][0];
				prvy_krat_b = 1;
			}
			else{
				if(najlepsi_cas_b < najlepsi_cas[v][0]){
					najlepsi_cas_b = najlepsi_cas_b; 
				}
				if(najlepsi_cas_b > najlepsi_cas[v][0]){
					najlepsi_cas_b = najlepsi_cas[v][0]; 
				}
			}
			//////////////////////////////////////////////////////
		}
		if(strcmp(vozidlo[v], "ferrari") == 0){
			//printf("%s - VOZIDLO JE FERRARI\n", meno_priezvisko[v]);
			//////////////////////////////////////////////////////
			if (prvy_krat_f == 0){
				najlepsi_cas_f = najlepsi_cas[v][0];
				prvy_krat_f = 1;
			}
			else{
				if(najlepsi_cas_f < najlepsi_cas[v][0]){
					najlepsi_cas_f = najlepsi_cas_f; 
				}
				if(najlepsi_cas_f > najlepsi_cas[v][0]){
					najlepsi_cas_f = najlepsi_cas[v][0]; 
				}
			}
			//////////////////////////////////////////////////////
		}
		if(strcmp(vozidlo[v], "porsche") == 0){
			//printf("%s - VOZIDLO JE PORSCHE\n", meno_priezvisko[v]);
			//////////////////////////////////////////////////////
			if (prvy_krat_p == 0){
				najlepsi_cas_p = najlepsi_cas[v][0];
				prvy_krat_p = 1;
			}
			else{
				if(najlepsi_cas_p < najlepsi_cas[v][0]){
					najlepsi_cas_p = najlepsi_cas_p; 
				}
				if(najlepsi_cas_p > najlepsi_cas[v][0]){
					najlepsi_cas_p = najlepsi_cas[v][0]; 
				}
			}
			//////////////////////////////////////////////////////

		}
		if(strcmp(vozidlo[v], "honda") == 0){
			//printf("%s - VOZIDLO JE HONDA\n", meno_priezvisko[v]);
			//////////////////////////////////////////////////////
			if (prvy_krat_h == 0){
				najlepsi_cas_h = najlepsi_cas[v][0];
				prvy_krat_h = 1;
			}
			else{
				if(najlepsi_cas_h < najlepsi_cas[v][0]){
					najlepsi_cas_h = najlepsi_cas_h; 
				}
				if(najlepsi_cas_h > najlepsi_cas[v][0]){
					najlepsi_cas_h = najlepsi_cas[v][0]; 
				}
			}
			//////////////////////////////////////////////////////
		}
	}

	////////////BUGATTI - dosadenie jazdca a cisla kola k casu
	for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
		for(int x = 0; x < 5; x++){
			if(cas[a][x] == najlepsi_cas_b){
				najlepsi_cas_b_jazdec = a;
				najlepsi_cas_b_kolo = x;
			}
		}
	}

	////////////FERRARI - dosadenie jazdca a cisla kola k casu
	for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
		for(int x = 0; x < 5; x++){
			if(cas[a][x] == najlepsi_cas_f){
				najlepsi_cas_f_jazdec = a;
				najlepsi_cas_f_kolo = x;
			}
		}
	}

	////////////PORSCHE - dosadenie jazdca a cisla kola k casu
	for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
		for(int x = 0; x < 5; x++){
			if(cas[a][x] == najlepsi_cas_p){
				najlepsi_cas_p_jazdec = a;
				najlepsi_cas_p_kolo = x;
			}
		}
	}

	////////////HONDA - dosadenie jazdca a cisla kola k casu
	for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
		for(int x = 0; x < 5; x++){
			if(cas[a][x] == najlepsi_cas_h){
				najlepsi_cas_h_jazdec = a;
				najlepsi_cas_h_kolo = x;
			}
		}
	}

	//////////VYPISY//////////
	//BUGATTI
	if(najlepsi_cas_b != 0){
		printf("Znacka: bugatti\n");
		printf("Najlepsie kolo: %.3f\n", najlepsi_cas_b);
		printf("Jazdec: %s\n", meno_priezvisko[najlepsi_cas_b_jazdec]);
		printf("Cislo kola: %d\n\n", najlepsi_cas_b_kolo+1);
	}
	else{
		printf("ZNACKA BUGATTI SA NENACHADZA V SUBORE\n\n");
	}
	//FERRARI
	if(najlepsi_cas_f != 0){
		printf("Znacka: ferrari\n");
		printf("Najlepsie kolo: %.3f\n", najlepsi_cas_f);
		printf("Jazdec: %s\n", meno_priezvisko[najlepsi_cas_f_jazdec]);
		printf("Cislo kola: %d\n\n", najlepsi_cas_f_kolo+1);
	}
	else{
		printf("ZNACKA FERRARI SA NENACHADZA V SUBORE\n\n");
	}
	//PORSCHE
	if(najlepsi_cas_p != 0){
		printf("Znacka: porsche\n");
		printf("Najlepsie kolo: %.3f\n", najlepsi_cas_p);
		printf("Jazdec: %s\n", meno_priezvisko[najlepsi_cas_p_jazdec]);
		printf("Cislo kola: %d\n\n", najlepsi_cas_p_kolo+1);
	}
	else{
		printf("ZNACKA PORSCHE SA NENACHADZA V SUBORE\n\n");
	}
	//HONDA
	if(najlepsi_cas_h != 0){
	printf("Znacka: honda\n");
	printf("Najlepsie kolo: %.3f\n", najlepsi_cas_h);
	printf("Jazdec: %s\n", meno_priezvisko[najlepsi_cas_h_jazdec]);
	printf("Cislo kola: %d\n\n", najlepsi_cas_h_kolo+1);
	}
	else{
		printf("ZNACKA HONDA SA NENACHADZA V SUBORE\n\n");
	}
	
	// printf("NAJLEPSI CAS PRE BUGATTI JE:%.3f\n", najlepsi_cas_b);
	// printf("NAJLEPSI CAS PRE FERRARI JE:%.3f\n", najlepsi_cas_f);
	// printf("NAJLEPSI CAS PRE PORSCHE JE:%.3f\n", najlepsi_cas_p);
	// printf("NAJLEPSI CAS PRE HONDU JE:%.3f\n", najlepsi_cas_h);

}

void year(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){
	
	float najlepsi_cas[200][50];
	memset(najlepsi_cas, '\0', 200);

	float temp_best = 0;
	
	int cislo_kola[200][1];		//cislo_kola - ulozim sem cislo (poradie od 0 - 4) najlepsieho kola pre kazdeho hraca
	memset(cislo_kola, '\0', 200);

	for (int c = 0; c < *celkovy_pocet_jazdcov; c++){ //VYPOCITA NAJLEPSI CAS PRE KAZDEHO JAZDCA

		float time_count[5];
		memset(time_count, '\0', sizeof(time_count));
		
		
		for(int counter_best = 0; counter_best < 5; counter_best++){ 	
			time_count[counter_best] = cas[c][counter_best]; 			//SKOPIRUJE CASY JAZDCA (cislo_jazdca_driver) DO POMOCNEHO POLA
		}	
		
		if (time_count[0] < time_count[0+1]){
			temp_best = time_count[0];
		}
		else if(time_count[0] > time_count[0+1]){
			temp_best = time_count[0+1];
		}
		for (int a = 1; a < 5; a++){ 			//VYKONA SA 4 KRAT + VYSSIE SA VYKONA RAZ
			if (temp_best < time_count[a]){
				temp_best = temp_best;
			}
			else if (temp_best > time_count[a]){
				temp_best = time_count[a];
			}	
		}
		//printf("Najlepsie kolo pre jazdca %d: %.3f\n", c, temp_best);
		najlepsi_cas[c][0]= temp_best;

		//printf("TEMP BEST: %f", temp_best);
		//printf(" CASY: %f\n", cas[c][0]);		
	}

	// POROVNANIE KTORY CAS JAZDCA (a) SA ROVNA NAJVYSSIEMU CASU JAZDCA (a)
	for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
		for(int x = 0; x < 5; x++){
			if(cas[a][x] == najlepsi_cas[a][0]){
				cislo_kola[a][0] = x;
			}
		}
	}

	int year_check = 1;
	int year_f = 0;
	/////////////////////////////////////////////////////////////////////
	do{
		printf("\nZadajte rok: ");				//ZADANIE ROKU NARODENIA
		scanf("%d", &year_f);
		getchar();

		printf("\n\n");

		if(year_f >= 1000 && year_f <= 2021){
			
			//////////VYPIS//////////
			for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
				if(year_f > rok[a]){
					printf("%s\n", meno_priezvisko[a]);
					printf("nar. %d\n", rok[a]);
					printf("Najlepsie kolo: %.3f\n", najlepsi_cas[a][0]);
					printf("Cislo kola: %d", cislo_kola[a][0]+1); 	// +1 PRE TO ZE POCITA KOLA OD NULY
					printf("\n\n");
				}
			}
			//////////VYPIS//////////
			year_check = 0;
			//break;
		}
		else{
			printf("ZADALI STE NESPRAVNY ROK, SKUSTE TO ZNOVA\n");
		}

	}while(year_check != 0);
	/////////////////////////////////////////////////////////////////////

}

void average(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){
	int counter_best = 0;
	float time_count[5];
	float porovnanie[200];
	memset(porovnanie, '\0', 200);

	float cas_porovnanie[200][50];		//VYPOCET PRIEMERNEHO CASU PRE KAZDEHO JAZDCA
	memset(cas_porovnanie, '\0', 200);

	

	for (int a = 0; a < *celkovy_pocet_jazdcov; a++){ //VYPOCITA A VYPISE PRIEMERNY CAS PRE KAZDEHO JAZDCA
	
		memset(time_count, '\0', sizeof(time_count));

		for(counter_best = 0; counter_best < 5; counter_best++){ 	
			time_count[counter_best] = cas[a][counter_best]; 			//SKOPIRUJE CASY JAZDCA (a) DO POMOCNEHO POLA
			//printf("CASY: %.3f\n", time_count[counter_best]);
		}	

		float temp_average = (time_count[0] + time_count[1] + time_count[2] + time_count[3] + time_count[4]) / 5;
			//printf("Priemerne kolo jazdca %d: %.3f\n", a, temp_average);
			printf("%s - %.3f\n", meno_priezvisko[a], temp_average);	//VYPIS PRIEMERNEHO CASU PRE KAZDEHO JAZDCA
			
			//porovnanie[a] = temp_average;
			cas_porovnanie[a][0]= temp_average; //cas_porovnanie [a] - cislo jazdca ,[0] - jeho priemerny cas
	}
	//printf("--------%.3f\n", porovnanie[8]);
	//printf("CISLO JAZDCA A PRIEMERNY CAS%.3f\n", cas_porovnanie[1][0]);

	// ZISTENIE NAJLEPSIEHO PRIEMERNEHO CASU
	float temp_best_of_all = 0;
	for (int a = 0; a < (*celkovy_pocet_jazdcov)-1; a++){

		if (cas_porovnanie[a][0] < cas_porovnanie[a+1][0]){
			temp_best_of_all = cas_porovnanie[a][0];
		}
		else if(cas_porovnanie[a][0] > cas_porovnanie[a+1][0]){
			temp_best_of_all = cas_porovnanie[a+1][0];
		}
		for (int e = 1; e < 5; e++){ 			//VYKONA SA 4 KRAT + VYSSIE SA VYKONA RAZ
			if (temp_best_of_all < cas_porovnanie[e][0]){
				temp_best_of_all = temp_best_of_all;
			}
			else if (temp_best_of_all > cas_porovnanie[e][0]){
				temp_best_of_all = cas_porovnanie[e][0];
			}	
		}

	}
	//printf("Najlepsie PRIEMERNE kolo ZO VSETKYCH: %.3f\n", temp_best_of_all);
	

	//POROVNA VSETKY PRIEMERNE CASY S temp_best_of_all a zistit tak MENO JAZDCA
	for(int a = 0; a < *celkovy_pocet_jazdcov; a++){
		if(cas_porovnanie[a][0] == temp_best_of_all){
			printf("\nNajlepsie:\n%s - %.3f\n", meno_priezvisko[a], temp_best_of_all);
		}
		
	}
}

void under(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){

	int ciarka = 0;
	int pocet = 0;
	float cas_kola = 0;
	char under_meno_priezvisko[200][50];
	memset(under_meno_priezvisko, '\0', 200);

	float under_cas[200][5];
	memset(under_cas, '\0', 200);

	int ziadny_hrac = 1;


	printf("Zadajte cas kola: ");
	scanf("%f", &cas_kola);
	getchar();

	for(int i = 0; i < *celkovy_pocet_jazdcov; i++){
		for(int j = 0; j < 5; j++){
			if(cas[i][j] <= cas_kola){			//POROVNANIE CASOV A ZADANEHO CASU
				under_cas[i][j] = cas[i][j];
				pocet++;
			}
		}
		if(pocet > 0){
			ziadny_hrac = 0;
			//VYPIS MENA PRIEZVISKA A POCTU KOL
			if (pocet == 1){
				printf("\n%s - %d kolo, ", meno_priezvisko[i], pocet);
			}
			if (pocet >= 2 && pocet < 5){
				printf("\n%s - %d kola, ", meno_priezvisko[i], pocet);
			}
			if (pocet == 5){
				printf("\n%s - %d kol, ", meno_priezvisko[i], pocet);
			}

			ciarka = 0;
			for(int a = 0; a < 5; a++){
				if(under_cas[i][a] != '\0'){	//ak cas splna podmienku zadanu na vstupe tak ho vypise
					if(ciarka == 1){
						printf(", ");
					}
					printf("%d (%.3f)", a+1, under_cas[i][a]);
					ciarka = 1;
				}
			}
		}

		pocet = 0;
	}
	if(ziadny_hrac == 1){
		printf("\nZiadny jazdec nezajazdil kolo za mensi alebo rovnaky cas ako je zadany.\n");
	}

	printf("\n");

}

void change(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){
	
	char volba_priezviska_change[50];
	int volba_poradoveho_kola_change = 0;
	char volba_novy_cas_change[50];
	int counter = 0;

	memset(volba_priezviska_change, '\0', 50);
	memset(volba_novy_cas_change, '\0', 50);

	FILE *subor_zmena_casu;
	char temp_zmena_casu[200];
	char temp_zmena_casu_subor[10000];

	int pocet_bodkociarok = 0;

	int i = 0;
	char c;
	int poloha = 0;
	int zapis_cas = 0;

	int cislo_jazdca_zmena = 0; // ???
	
	//NACITANIE PRIEZVISKA
	printf("Zadajte priezvisko jazdca na zmenu: ");
	scanf("%s", &volba_priezviska_change);
	getchar();

	printf("Zadajte poradove cislo kola: ");
	scanf("%d", &volba_poradoveho_kola_change);
	getchar();

	printf("Zadajte novy cas kola: ");
	scanf("%s", &volba_novy_cas_change);
	getchar();
	

	//DOSTANEM SA NA VYBRANEHO JAZDCA a zistim jeho INDEX (CISLO)
	for (int i = 0; i < *celkovy_pocet_jazdcov; i++){

		//VYBERIE CISLO RIADKU(JAZDCA) pre zmenu
		if(strcmp(priezvisko[i], volba_priezviska_change) == 0){
			cislo_jazdca_zmena = i;
			break;
		}
	}

	int kontrola = *celkovy_pocet_jazdcov;
	kontrola--;
	//////////////////////////////////////////////////////////////////////////////
	//AK SA NEROVNA POSLEDNEMU RIADKU
	if(cislo_jazdca_zmena != kontrola){
			//OTVORENIE SUBORU NA CITANIE + KONTROLA OTVORENIA SUBORU
		if ((subor_zmena_casu = fopen("jazdci.csv", "r")) == NULL)
			printf("SUBOR SA NEPODARILO OVTORIT !\n");
		else{
			
			//PRECITAM RIADKY PO VYBRANE CISLO JAZDCA A ULOZIM DO temp_file
			for (int i = 0; i < cislo_jazdca_zmena; i++){
				while ((c = getc(subor_zmena_casu)) != '\n'){
					temp_zmena_casu_subor[poloha] = c;
					poloha++;
				}
				//NACIANIE DO temp_file aj znak \n
				temp_zmena_casu_subor[poloha] = c;
				poloha++;
			}

			//VYBRANY JAZDEC - NACITAVAM HO OD ZACIATKU AZ PO KONIEC RIADKA
			while ((c = getc(subor_zmena_casu)) != '\n'){
				
				if(c == ';'){
					pocet_bodkociarok++;	//KAZDY UDAJ V RIADKU pocet_bodkociarok++
				}
				if(pocet_bodkociarok - 3 == volba_poradoveho_kola_change){ 	//VYBER UDAJU PRE ZMENU CASU
					temp_zmena_casu_subor[poloha++] = c;
					while (counter < strlen(volba_novy_cas_change)){		//CYKLUS PREJDE OD 0 PO DLZKU ZADANEHO CISLA
						temp_zmena_casu_subor[poloha++] = volba_novy_cas_change[counter++]; //VYMENI STARY CAS ZA NOVY
					}
					
					while(1){ //NEKONECNY CYKLUS
						c=getc(subor_zmena_casu);
						if(c == ';' || c =='\n' || c == EOF) break;
					}
					if(c == ';'){
						temp_zmena_casu_subor[poloha++] = ';';
						pocet_bodkociarok++;
					}
					else {
						break;
					}
				}
				else if(pocet_bodkociarok - 3 < volba_poradoveho_kola_change || pocet_bodkociarok - 3 > volba_poradoveho_kola_change){
					temp_zmena_casu_subor[poloha++] = c;
				}
			} 

			temp_zmena_casu_subor[poloha++] = c;

			//CITANIE ZNAKOV OD JAZCA AZ PO KONIEC SUBORU
			//if(c != EOF){
				while((c = getc(subor_zmena_casu)) != EOF){
				temp_zmena_casu_subor[poloha] = c;
				poloha++;
				}
			//}
			//ZATVORENIE SUBORU + KONTROLA ZATVORENIA SUBORU
			if (fclose(subor_zmena_casu) == EOF)printf("SUBOR SA NEPODARILO ZATVORIT !\n");
		}

		
		//OTVORENIE SUBORU NA ZAPIS A ZAPISANIE NACITANYCH UDAJOV BEZ CELEHO RIADKU S VYBRANYM PRIEZVISKOM
		if ((subor_zmena_casu = fopen("jazdci.csv", "w")) == NULL)
			printf("SUBOR SA NEPODARILO OVTORIT !\n");
		else{
			//fputs("", subor_zmazanie);
			fputs(temp_zmena_casu_subor, subor_zmena_casu);
		}
		//ZATVORENIE SUBORU + KONTROLA ZATVORENIA SUBORU
		if (fclose(subor_zmena_casu) == EOF)
			printf("SUBOR SA NEPODARILO ZATVORIT !\n");
		
	}
	//////////////////////////////////////////////////////////////////////////////
	//AK SA ROVNA POSLEDNEMU RIADKU
	else{
		//printf("\nVYBRAL SI POSLEDNY RIADOK\n");

			//OTVORENIE SUBORU NA CITANIE + KONTROLA OTVORENIA SUBORU
		if ((subor_zmena_casu = fopen("jazdci.csv", "r")) == NULL)
			printf("SUBOR SA NEPODARILO OVTORIT !\n");
		else{
			
			//PRECITAM RIADKY PO VYBRANE CISLO JAZDCA A ULOZIM DO temp_file
			for (int i = 0; i < cislo_jazdca_zmena; i++){
				while ((c = getc(subor_zmena_casu)) != '\n'){
					temp_zmena_casu_subor[poloha] = c;
					poloha++;
				}
				//NACIANIE DO temp_file aj znak \n
				temp_zmena_casu_subor[poloha] = c;
				poloha++;
			}

			//VYBRANY JAZDEC - NACITAVAM HO OD ZACIATKU RIADKU AZ PO KONIEC SUBORU (lebo sme na poslednom jazdcovi)
			while ((c = getc(subor_zmena_casu)) != EOF){
				
				if(c == ';'){
					pocet_bodkociarok++;	//KAZDY UDAJ V RIADKU pocet_bodkociarok++
				}
				if(pocet_bodkociarok - 3 == volba_poradoveho_kola_change){ 	//VYBER UDAJU PRE ZMENU CASU
					temp_zmena_casu_subor[poloha++] = c;
					while (counter < strlen(volba_novy_cas_change)){		//CYKLUS PREJDE OD 0 PO DLZKU ZADANEHO CISLA
						temp_zmena_casu_subor[poloha++] = volba_novy_cas_change[counter++]; //VYMENI STARY CAS ZA NOVY
					}
					
					while(1){ //NEKONECNY CYKLUS
						c=getc(subor_zmena_casu);
						if(c == ';' || c =='\n' || c == EOF) break;
					}
					if(c == ';'){
						temp_zmena_casu_subor[poloha++] = ';';
						pocet_bodkociarok++;
					}
					else {
						break;
					}
				}
				else if(pocet_bodkociarok - 3 < volba_poradoveho_kola_change || pocet_bodkociarok - 3 > volba_poradoveho_kola_change){
					temp_zmena_casu_subor[poloha++] = c;
				}
			} 

			//CITANIE ZNAKOV OD JAZCA AZ PO KONIEC SUBORU
			if(c != EOF){
				while((c = getc(subor_zmena_casu)) != EOF){
				temp_zmena_casu_subor[poloha] = c;
				poloha++;
				}
			}
			//ZATVORENIE SUBORU + KONTROLA ZATVORENIA SUBORU
			if (fclose(subor_zmena_casu) == EOF)printf("SUBOR SA NEPODARILO ZATVORIT !\n");
		}


		//OTVORENIE SUBORU NA ZAPIS A ZAPISANIE NACITANYCH UDAJOV BEZ CELEHO RIADKU S VYBRANYM PRIEZVISKOM
		if ((subor_zmena_casu = fopen("jazdci.csv", "w")) == NULL)
			printf("SUBOR SA NEPODARILO OVTORIT !\n");
		else{
			//fputs("", subor_zmazanie);
			fputs(temp_zmena_casu_subor, subor_zmena_casu);
		}
		//ZATVORENIE SUBORU + KONTROLA ZATVORENIA SUBORU
		if (fclose(subor_zmena_casu) == EOF)
			printf("SUBOR SA NEPODARILO ZATVORIT !\n");
			
	}
	
}

void newdriver(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){

	FILE *subor_zapis;
	char zapis_meno[200];
	char zapis_priezvisko[200];
	int zapis_rok=0;
	char zapis_pohlavie;
	char zapis_auto[200];
	float zapis_casy[5];

	//KONTROLA SPRAVNEHO ZAPISU
	int zapis_rok_chcek = 1;
	int zapis_pohlavie_check = 1;
	int zapis_auto_check = 1;


	//OTVORENIE SUBORU + KONTROLA OTVORENIA SUBORU
	if ((subor_zapis = fopen("jazdci.csv", "a")) == NULL)  //OTVORENIE SUBORU PRE PRIPOJENIE NA KONIEC
		printf("SUBOR SA NEPODARILO OVTORIT !\n");
	else{
		fprintf(subor_zapis, "%s", "\n");

		printf("Zadajte Meno a priezvisko jazdca: ");		//ZADAJ MENO A PRIEZVISKO
		scanf("%s %s", &zapis_meno, &zapis_priezvisko);
		getchar();
		fprintf(subor_zapis, "%s %s;", zapis_meno, zapis_priezvisko);	//ZAPIS MENO A PRIEZVISKO DO SUBORU

		do{
			printf("\nZadajte rok narodenia jazdca: ");			//ZADAJ ROK NARODENIA
			scanf("%d", &zapis_rok);
			getchar();

			if(zapis_rok >= 1000 && zapis_rok <= 2021){

				zapis_rok_chcek = 0;
				break;
			}
			else{
				printf("ZADALI STE NESPRAVNY ROK, SKUSTE TO ZNOVA\n");
			}

		}while(zapis_rok_chcek != 0);

		do{
			printf("\nZadajte pohlavie (m alebo f): ");			//ZADAJ POHLAVIE
			scanf("%c", &zapis_pohlavie);
			getchar();

			if(zapis_pohlavie == 'm' || zapis_pohlavie == 'f'){	//ZAPIS POHLAVIE DO SUBORU
				fprintf(subor_zapis, "%c;", zapis_pohlavie);
				zapis_pohlavie_check = 0;
			}
			else{
				printf("Zadanu pismeno nie je validne.");
			}
		}while(zapis_pohlavie_check !=0);


		if(zapis_rok_chcek == 0){
			fprintf(subor_zapis, "%d;", zapis_rok);				//ZAPIS ROK DO SUBORU
		}


		// strcmp() porovna stringy ak su rovnaké vrati 0	//ZADAJ ZNACKA AUTA
		do{
			printf("\nZadajte znacku auta: ");			
			scanf("%s", &zapis_auto);
			getchar();

			if( (strcmp(zapis_auto, "bugatti") == 0) || (strcmp(zapis_auto, "ferrari") == 0 ) || (strcmp(zapis_auto, "porsche") == 0 ) || (strcmp(zapis_auto, "honda") == 0 ) ){
				fprintf(subor_zapis, "%s;", zapis_auto);	//ZAPIS ZNACKA AUTA DO SUBORU
				zapis_auto_check = 0;
			}
			else {
				printf("Zadanu znacku nie je mozne zapisat");
			}
		}while(zapis_auto_check !=0);


		for(int casy=0; casy < 5; casy++){
			printf("\nZadajte casy: ");					//CASY
			scanf("%f", &zapis_casy[casy]);
			getchar();
			if(casy == 4){
				fprintf(subor_zapis, "%.3f", zapis_casy[casy]);
			}
			else{
			fprintf(subor_zapis, "%.3f;", zapis_casy[casy]);
			}
		}
		
	}

	//ZATVORENIE SUBORU + KONTROLA ZATVORENIA SUBORU
	if (fclose(subor_zapis) == EOF)printf("SUBOR SA NEPODARILO ZATVORIT !\n");

}

void rmdriver(char meno_priezvisko[200][50], char priezvisko[200][50], char pohlavie[200], int rok[200], char vozidlo[200][50], float cas[200][50], int *celkovy_pocet_jazdcov){

	FILE *subor_zmazanie;

	char volba_priezviska[50];
	memset(volba_priezviska, '\0', 50);
	int cislo_jazdca_zmazanie = 201;
	char temp_zmazanie[200];
	memset(temp_zmazanie, '\0', 200);

	char temp_file[10000];
	memset(temp_file, '\0', 10000);

	int i = 0;
	char c;
	int poloha = 0;
	int err = 0;

	//NACITANIE PRIEZVISKA
	printf("Zadajte priezvisko na zmazanie: ");
	scanf("%s", &volba_priezviska);
	getchar();
	
	//DOSTANEM SA NA VYBRANEHO JAZDCA a zistim jeho INDEX (CISLO)
	for (int i = 0; i < *celkovy_pocet_jazdcov; i++){

		//VYBERIE CISLO RIADKU(JAZDCA) pre zmazanie
		if(strcmp(priezvisko[i], volba_priezviska) == 0){
			cislo_jazdca_zmazanie = i;
			break;
		}
		else{
			//printf("NENAJDENY JAZDEC\n");
			err = 1;
		}
	}
	
	int kontrola = *celkovy_pocet_jazdcov;
	kontrola--;
	//////////////////////////////////////////////////////////////////////////////
	//AK SA VYBER NA ZMAZANIE NEROVNA POSLEDNEMU RIADKU
	if (cislo_jazdca_zmazanie != kontrola && cislo_jazdca_zmazanie != 201){

		//OTVORENIE SUBORU NA CITANIE + KONTROLA OTVORENIA SUBORU
	if ((subor_zmazanie = fopen("jazdci.csv", "r")) == NULL)
		printf("SUBOR SA NEPODARILO OVTORIT !\n");
	else{
		
		//PRECITAM RIADKY PO VYBRANE CISLO JAZDCA A ULOZIM DO temp_file
		for (int i = 0; i < cislo_jazdca_zmazanie; i++){
			while ((c = getc(subor_zmazanie)) != '\n'){
				temp_file[poloha] = c;
				poloha++;
			}
			//NACIANIE DO temp_file aj znak \n
			temp_file[poloha] = c;
			poloha++;
		}

		//VYBRANY JAZDEC - NACITAM HO AZ PO KONIEC RIADKA
		i = 0;
		while ((c = getc(subor_zmazanie)) != '\n'){
			temp_zmazanie[i] = c;
			i++;
		} 
		//VYPISE RIADOK KTORY CHCEM ZMAZAT
		//printf("ZMAZANIE: %s\n", temp_zmazanie);

		//CITANIE ZNAKOV OD JAZCA AZ PO KONIEC SUBORU
		while((c = getc(subor_zmazanie)) != EOF){
			temp_file[poloha] = c;
			poloha++;
		}
		//ZATVORENIE SUBORU + KONTROLA ZATVORENIA SUBORU
		if (fclose(subor_zmazanie) == EOF)printf("SUBOR SA NEPODARILO ZATVORIT !\n");
		
		}
		if ((subor_zmazanie = fopen("jazdci.csv", "w")) == NULL)
		printf("SUBOR SA NEPODARILO OVTORIT !\n");
		else{
			//fputs("", subor_zmazanie);
			fputs(temp_file, subor_zmazanie);
		}
		//ZATVORENIE SUBORU + KONTROLA ZATVORENIA SUBORU
		if (fclose(subor_zmazanie) == EOF)printf("SUBOR SA NEPODARILO ZATVORIT !\n");
	}

	//////////////////////////////////////////////////////////////////////////////
	//AK SA VYBER NA ZMAZANIE ROVNA POSLEDNEMU RIADKU
	else if(cislo_jazdca_zmazanie == kontrola && cislo_jazdca_zmazanie != 201){
		//OTVORENIE SUBORU NA CITANIE + KONTROLA OTVORENIA SUBORU
		if ((subor_zmazanie = fopen("jazdci.csv", "r")) == NULL)
			printf("SUBOR SA NEPODARILO OVTORIT !\n");
		else{
			
			//PRECITAM RIADKY PO VYBRANE CISLO JAZDCA A ULOZIM DO temp_file
			for (int i = 0; i < cislo_jazdca_zmazanie; i++){
				while ((c = getc(subor_zmazanie)) != '\n'){
					temp_file[poloha] = c;
					poloha++;
				}
				//NACIANIE DO temp_file aj znak \n
				temp_file[poloha] = c;
				poloha++;
			}

			//VYBRANY JAZDEC - NACITAM HO AZ PO KONIEC RIADKA
			i = 0;
			while ((c = getc(subor_zmazanie)) != EOF){
				temp_zmazanie[i] = c;
				i++;
			} 
			//NAHRADI mi znak \n NA KONCI VYPISU SUBORU
			temp_file[strlen(temp_file)-1] = '\0';

			//VYPISE RIADOK KTORY CHCEM ZMAZAT
			//printf("ZMAZANIE: %s\n", temp_zmazanie);

			if (fclose(subor_zmazanie) == EOF)printf("SUBOR SA NEPODARILO ZATVORIT !\n");
			
			}
		//OTVORENIE SUBORU NA ZAPIS A ZAPISANIE NACITANYCH UDAJOV BEZ CELEHO RIADKU S VYBRANYM PRIEZVISKOM
		if ((subor_zmazanie = fopen("jazdci.csv", "w")) == NULL)
		printf("SUBOR SA NEPODARILO OVTORIT !\n");
		else{
			//fputs("", subor_zmazanie);
			fputs(temp_file, subor_zmazanie);
		}
		//ZATVORENIE SUBORU + KONTROLA ZATVORENIA SUBORU
		if (fclose(subor_zmazanie) == EOF)printf("SUBOR SA NEPODARILO ZATVORIT !\n");
	}

	if(err == 1){
		printf("NENAJDENY JAZDEC\n");
	}
	if(cislo_jazdca_zmazanie != 201){
		printf("Jazdec s menom \"%s\" bol vymazany.", meno_priezvisko[cislo_jazdca_zmazanie]);
	}
}


int main() {
	char check;
	int main_counter = 0;

	/* SKUSKA POINTROV
	int d = 25;
	printf("%d\n", d);
	int *p = &d;
	printf("%d, %d\n", p, *p);
	*/

	//maximalne hodnoty pre polia
	char meno_priezvisko[200][50];
	memset(meno_priezvisko, '\0', sizeof(meno_priezvisko));

	char priezvisko[200][50];
	memset(priezvisko, '\0', sizeof(priezvisko));

	char pohlavie[200];
	memset(pohlavie, '\0', sizeof(pohlavie));

	int rok[200];
	memset(rok, '\0', sizeof(rok));

	char vozidlo[200][50];
	memset(vozidlo, '\0', sizeof(vozidlo));

	float cas[200][50];
	memset(cas, '\0', sizeof(cas));

	int celkovy_pocet_jazdcov = 0;

	printf("---------------------------------------------------------------\n");
	printf("PRIKAZ s - funkcia sum()\n");										//HOTOVO
	printf("PRIKAZ d - funkcia driver()\n");									//HOTOVO
	printf("PRIKAZ l - funkcia lap()\n");										//HOTOVO
	printf("PRIKAZ g - funkcia gender()\n");									//HOTOVO
	printf("PRIKAZ b - funkcia brand()\n");										//HOTOVO
	printf("PRIKAZ y - funkcia year()\n");										//HOTOVO
	printf("PRIKAZ a - funkcia average()\n");									//HOTOVO
	printf("PRIKAZ u - funkcia under()\n");										//HOTOVO
	printf("PRIKAZ c - funkcia change()\n");									//HOTOVO
	printf("PRIKAZ n - funkcia newdriver()\n");									//HOTOVO
	printf("PRIKAZ r - funkcia rmdriver()\n");									//HOTOVO
	printf("PRIKAZ t - OBNOVI UDAJE V POLIACH\n");
	printf("PRIKAZ x - UKONCI PROGRAM\n");
	printf("---------------------------------------------------------------\n");

	//NACITA UDAJE DO POLI
	// nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
	// rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);

	// for (int i = 0; i < celkovy_pocet_jazdcov; i++)
	// {
	// 	printf("PRIEZVISKO: %s\n", priezvisko[i]);
	// }
	
	do {
		if (main_counter!=0) printf("\n");
		main_counter++;

		printf("ZADAJ pismeno:");
		scanf("%c", &check);

		//VYMAZE ZNAK NOVEHO RIADKU KTORE PRIDA scanf
		getchar();


		if (check == 's' || check == 'd' || check == 'x' || check == 't' || check == 'l' || check == 'g' || check == 'b' || check == 'y' || check == 'a' || check == 'u' || check == 'c' || check == 'n' || check == 'r') {

			switch (check) {
				// bud: case (ASCII CISLO)    alebo: case'(znak)'
			case 'x':
			printf("\nPROGRAM UKONCENY !\n");
			break;

			case 't':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				break;

			case 's':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);

				sum(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				break;

			case 'd':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				
				driver(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				break;

			case 'l':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				
				lap(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);

				break;

			case 'g':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				
				gender(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);

				break;

			case 'b':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				
				brand(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);

				break;

			case 'y':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				
				year(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);

				break;

			case 'a':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				
				average(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				break;

			case 'u':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				
				under(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				break;

			case 'c':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				
				change(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);

				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				sum(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				break;

			case 'n':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);

				newdriver(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
	
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);

				sum(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				break;

			case 'r':
				//NACITA UDAJE DO POLI
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);

				rmdriver(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				
				nacitanie(meno_priezvisko, priezvisko, pohlavie, rok, vozidlo, cas, &celkovy_pocet_jazdcov);
				rozdel_priezvisko(meno_priezvisko, priezvisko, &celkovy_pocet_jazdcov);
				break;
			}

		}
		else {
			printf("\nZADANE PISMENO NEPOZNAM\nSKUS TO ESTE RAZ !\n");
		}
	}while (check != 'x');



	//printf("UPLNY KONIEC MAINU");
	return 0;
}
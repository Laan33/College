//Assignment16_CathalLawlor_21325456


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

void replaceBoxerData();
void countriesDataGather();
void newCountries();


void main() {


	char fileDogs[] = "C:\\Users\\catha\\Downloads\\dogs.txt"; 
	FILE* fptr1;

	fopen_s(&fptr1, fileDogs, "r");
	if (fptr1 == NULL) { //error message for file failure case
		printf("Error Opening File \n Exiting ........");
		return;
	}
	else { //finding and priting file size
		fseek(fptr1, 0, SEEK_END); //going to end of file
		int len = ftell(fptr1); // len being the length of file
		printf("Part 1\n");
		printf("Size of dogs.txt: %d bytes.\n", len);
		fclose(fptr1);
	}

	replaceBoxerData();
	countriesDataGather();
	newCountries();
}


void replaceBoxerData() { //replacing pug with boxer
	char fileDogs[] = "C:\\Users\\catha\\Downloads\\dogs.txt";
	FILE* fptr1;

	fopen_s(&fptr1, fileDogs, "r+");
	if (fptr1 == NULL) { //error message for file failure case
		printf("Error Opening File \n Exiting ........");
		return;
	}
	else { //moving in 3 units from the end and fputs boxer into the file instead.
		fseek(fptr1, -3, SEEK_END);
		int len = ftell(fptr1);
		printf("Part 2\n");
		fputs("Boxer", fptr1);
		fclose(fptr1);
	}	
}


void countriesDataGather() { //part 3, reading and printing seperated countries and populations

	/* Note - there is an extra enter / \n in the countries.txt, which prints netherlands twice with this, 
	I have removed the extra enter and netherlands prints only once.*/

	char fileCountries[] = "C:\\Users\\catha\\Downloads\\countries.txt";
	FILE* fptr2;
	fopen_s(&fptr2, fileCountries, "r+");

	char line[200]; 
	char country[30];
	long population;

	if (fptr2 == NULL) { //error message for file failure case
		printf("Error Opening File \n Exiting ........");
		return;
	}
	else {
		int i = 0, j = 0; 
		while (!feof(fptr2)) { //while loop for length of txt file
			fgets(line, 200, fptr2); //take out the current selected line
			while (line[i] != '\t' ) { //seperating out countries from population using tab as a delimiter
				country[j] = line[i]; //setting the country
				i++;
				j++;
			}
			country[j] = '\0';
			i++;
		

			population = atoi(&line[i]); //grabbing out population from line[i]
			printf("Country: %s, \t Population: %d\n", country, population);
			i = 0, j = 0;
		}
		fclose(fptr2);
	}
}

void newCountries() { //entering in a country with L as a second name
	char fileCountries[] = "C:\\Users\\catha\\Downloads\\countries.txt";
	FILE* fptr2;
	fopen_s(&fptr2, fileCountries, "a");

	
	if (fptr2 == NULL) { //error message for file failure case
		printf("Error Opening File \n Exiting ........");
		return;
	}
	else {
		fseek(fptr2, 0, SEEK_END);
		fputs("\nLibya \t 6777000", fptr2);
	}
	fclose(fptr2);
}



#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

//void readCompanies(char fileName[]);
void addCompanies(char fileName[], char compName[], float rev);

void main() {
	printf("Week 16 tutorial.\n");

	FILE* fptr;
	fopen_s(&fptr, "C:\\Users\\catha\\Desktop\\companies.txt", "r");

	if (fptr != NULL) {
		printf("Success! \n");
		float rev[20];
		char line[200];
		char companies[20][200];
		int c = 0;

		while (!feof(fptr)) {
			int i = 0;
			fgets(line, 200, fptr);
			printf("%s", line);
			while (line[i] != '\t') {
				companies[c][i] = line[i];
				i++;
			}
			companies[c][i] = '\0';
			i++;
			rev[c] = atof(&line[i]);
			c++;
		}

		for (int i = 0; i < c; i++) {
			printf("Company: %s,\t Revenue: %0.1f billions", companies[c], rev[i]);
		}

		fclose(fptr);
	}

	fopen_s(&fptr, "C:\\Users\\catha\\Desktop\\companies.txt", "a");

	if (fptr != NULL) {
		printf("Success(2) ! \n");
		fprintf(fptr, "\nCisco\t49.8");
		fclose;
	}
	addCompanies("test1", "test2", 1.1);
	//readCompanies("C:\\Users\\catha\\Desktop\\companies.txt");
}

void addCompanies(char fileName[], char compName[], float rev) {
	printf("%s\t%s\t%0.1f.\n", fileName, compName, rev);
}



/*void reapCompanies(char fileName[]) {
	FILE* fptr;
	fopen_s(&fptr, fileName, "r");

	if (fptr != NULL) {
		printf("Success! \n");
		fclose(fptr);
	}
}
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct { //struct to be used for printing elements 
	int month, day, year;
	char region[50], name[50], item[50];
	int units;
	float unitCost, total;
} runAway;

void main() {

	char fileName[] = "C:\\Users\\catha\\Downloads\\SampleData.txt"; //setting file to be a string as so it can be used later
	FILE* fp;

	fopen_s(&fp, fileName, "r");
	if (fp == NULL) { //error message for file failure case
		printf("Error Opening File \n Exiting ........");
		return;
	}
	else { //priting file data
		char data = fgetc(fp);
		while (data != EOF) {
			printf("%c", data);
			data = fgetc(fp);
		}
	}
	fclose(fp); //closing file and opening again to be read into while loop
	fopen_s(&fp, fileName, "r");
	if (fp == NULL) {
		printf("Error Opening File;\nExiting..........");
		return;
	}

	runAway s;

	float allTotalled = 0;

	int i = 0;

	char line[100];

	fgets(line, 100, fp);

	printf("\n  \n------------Part 2------------\n  \n");

	while (!feof(fp)) {
		fscanf_s(fp, "%d%*[-/]%d%*[-/]%d", &s.month, &s.day, &s.year); //scans in values and prints them out again with struct
		fscanf_s(fp, "%s", s.region, 50);
		fscanf_s(fp, "%s", s.name, 50);
		fscanf_s(fp, "%s", s.item, 50);
		fscanf_s(fp, "%d", &s.units);
		fscanf_s(fp, "%f", &s.unitCost);
		fscanf_s(fp, "%f", &s.total);


		printf("\nMonth: %d\t", s.month);
		printf("Day: %d\t", s.day);
		printf("Year: %d\t", s.year);
		printf("Region: %s\t", s.region);
		printf("Name: %s\t", s.name);
		printf("Item type: %s\t", s.item);
		printf("No. of units: %d\t", s.units);
		printf("Unit cost: %0.3f\t", s.unitCost);
		printf("Total cost: %0.3f\n", s.total);
		allTotalled += s.total;
		i++; //incrementing the while loop for next line
	}


	float avg = allTotalled / i; //avg being calculated

	runAway d = { 1,25,22,"Galway","Lawlor","Pen",56,19.99,1119.44 }; // adding my info to the file
	printf("Total income from all orders: %f\n", allTotalled);
	printf("Average order value: %f\n", avg);
	fclose(fp);
	fopen_s(&fp, fileName, "a");
	fprintf(fp, "%d/%d/%d %s\t%s\t%s\t%d\t%.2f\t%.2f\n", d.month, d.day, d.year, d.region, d.name, d.item, d.units, d.unitCost, d.total);
	fclose(fp);

	printf("\n \n --------------Part 3-------------- \n");
	printf(" File Updated \n \n ");
}
//Name: Cathal Lawlor
//Student ID: 21325456
//Date: 22/02/2022

//libraries used
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

typedef struct {
    char make[20];
    char model[20];
    int year;
}car;

car garage[10];

void readCars(char myfilePath[], int numCars); //prototyping functions
void displayGarage(int numCars);
int checkYear(int numCars, int year);

void main() {
    //Part-1
    printf("------ Part 1 ------\n");

    //file path assigned to char array
    char myfilePath[] = "C:\\Users\\catha\\downloads\\carsYear.csv";

    readCars(myfilePath, 10);

    displayGarage(10);

    int ans = checkYear(10, 2016);

    printf("%d cars from %d in the garage.\n", ans, 2016);
}

void readCars(char myfilePath[], int numCars) {
    car c;
    //file pointer set
    FILE* fptr;

    char line[200];

    fopen_s(&fptr, myfilePath, "r"); //opening file
    if (fptr == NULL) {
        printf("Error Opening File \n Exiting ........");
        return;
    }
    else {
        int i = 0;
        char commaDelims[] = ","; //delimiters for seperating model and year then from next set
        char spaceDelims[] = " ";
        for (int i = 0; i < numCars; i++) {
            fgets(line, 200, fptr);

            char* next = NULL; //used for clearing the variables

            char* data = strtok_s(line, spaceDelims, &next); //ripping data using strtok
            strcpy_s(c.make, 20, data);

            data = strtok_s(NULL, commaDelims, &next);
            strcpy_s(c.model, 20, data);

            data = strtok_s(NULL, commaDelims, &next);
            c.year = atoi(data); //converting string to integer

            garage[i] = c; //assigning the info in c to the i'th element in the garage array

        }

        fclose(fptr);
    }
}
void displayGarage(int numCars) {
    for (int i = 0; i < numCars; i++) {
        car c = garage[i]; //setting the current car to the i'th car in the garage
        printf("\nCar %d\n", i); 
        printf("Car make = %s,\nCar model %s,\nCar Year =  %d\n", c.make, c.model, c.year); //printing car info
    }
}

int checkYear(int numCars, int year) {
    int counter = 0;
    for (int i = 0; i < numCars; i++) {
        car c = garage[i];
        if (c.year == year) { //checking if car year is what we desire
            counter++; //increment counter if so
        }
    }
    return counter; //returning to be printed
}
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

typedef enum {closed, operational, overcapacity} wStatus;
char statusString[3][15] = { "closed", "operational", "overcapacity"};


typedef struct {
	char city [20] ;
	float capacity;
	wStatus status;
}warehouse;


void displayWarehouse();

warehouse allWarehouses[5];
int numW = 0;


void main() {
	srand(time(NULL));
	strcpy_s(allWarehouses[0].city, 20, "Dublin");
	allWarehouses[0].capacity = 52;
	allWarehouses[0].status = operational;

	strcpy_s(allWarehouses[1].city, 20, "Limerick");
	allWarehouses[1].capacity = 83;
	allWarehouses[1].status = overcapacity;

	strcpy_s(allWarehouses[2].city, 20, "Cork");
	allWarehouses[2].capacity = 0;
	allWarehouses[2].status = closed;
	numW = 3;

	for (int i = 0; i < 365; i++) {
		for (int j = 0; j < numW; j++) {
			float cap = rand() % 100;
			allWarehouses[j].capacity = cap;

			if (cap > 80) {
				allWarehouses[j].status = overcapacity;
			}

			else if (cap < 20) {
				allWarehouses[j].status = closed;
			}

			else {
				allWarehouses[j].status = operational;
			}
		}
		printf("\n--------------- day %d -----------\n", i + 1);
		displayWarehouse();


	}



}

void displayWarehouse() {
	printf("%15s %15s %15s.\n", "City", "Capacity", "Status");

	for (int i = 0; i < numW; i++) {
		printf("%15s %15.1f%% %15s.\n", allWarehouses[i].city, allWarehouses[i].capacity, statusString[allWarehouses[i].status]);
	}

}


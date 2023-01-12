// 

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

void printDoubleArray(double* dp, int len);
void swapIntegerValues(int* i1, int* i2);
void squareIntArray(int* i1, int len);
void printIntegerArrayBackwards(int* arr, int len);
void randomIntArray(int* arr, int len, int max);

void main() {
	srand(time(NULL));

	int int1 = 4;
	int int2 = 12;

	double array1[] = { 1.50, 2.30, 4.70, 8.90 };
	int array2[] = { 1,2,3,4,5};


	printf("Hello world\n\n"); //Testing :)

	printDoubleArray(array1, 4);
	swapIntegerValues(int1, int2);
	squareIntArray(array2, 5);
	printIntegerArrayBackwards(array2, 5); 
	randomIntArray(array2, 5, 100);

	for (int i = 0; i < 5; i++) { //loop to print out the randomised array
		printf("%d\t", array2[i]);
	}

}


void printDoubleArray(double* dp, int len) { 
	printf("Q1: Double Array\n");
	for (int i = 0; i < len; i++) { //printing out the array that was pointed to
		printf("%0.2lf\t", *(dp+i));
	}
}

void swapIntegerValues(int* i1, int* i2) {
	printf("\nQ2: Integer Swap\n");
	int tempSwap = 0; //Temporary variable for carrying over integer
	printf("x = %d, y = %d\n", i1, i2);
	tempSwap = i1;
	i1 = i2;
	i2 = tempSwap;
	printf("x = %d, y = %d\n", i1, i2);
	
}

void squareIntArray(int* i1, int len) {
	printf("Q3: Square Array\n");
	for (int i = 0; i < len; i++) { 
		*i1 = (*i1) * (*i1); //multiplying value by itself and printing it out 
		printf("%d\t", *i1);
		i1++;
	}
}

void printIntegerArrayBackwards(int* arr, int len) {
	printf("\nQ4: Array Backwards\n");
	arr += (len - 1); 
	for (int i = len - 1; i >= 0; i--) { //running for loop backwards and printing the array
		printf("%d\t", *arr);
		arr--;
	}
}

void randomIntArray(int* arr, int len, int max) {
	printf("\nQ5: Random integers\n"); 
	for (int i = 0; i < len; i++) {
		*(arr + i) = (rand()) % (max + 1); //the resultant of modulus 100 on a random number
	}
}


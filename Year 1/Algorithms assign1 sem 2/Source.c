// CT102 Algorithms
// Selection Sort and Insertion Sort functions
// 01/02/2022

//#include <stdafx.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

void insertionSort(int[], int);
void selectionSort(int[], int);
void bubbleSort(int arrA[], int size);





//testing functions in main()
void main() {


	int n = 5000;
	int arrA[5000];


	FILE* infp;

	fopen_s(&infp, "C:\\Users\\catha\\Desktop\\5000Ints.txt", "r");
	if (infp == NULL) {
		printf("Error reading file :/");
		return;
	}

	while (!feof(infp)) {
		printf("File working?");
		for (int i = 0; i < n; i++) {
			fscanf_s(infp, "%dzn", &arrA[i]);
		}
	}



	//selectionSort(arrA, n);
	insertionSort(arrA, n);
	//bubbleSort(arrA, n);

	printf("\n Printing out Sorted array:: ");
	for (int i = 0; i < n; i++)

		printf("\n %d", arrA[i]);
}



void bubbleSort(int arrA[], int size) {
	int i, k, temp;
	int numSwaps = 0;
	int numCompare = 0;
	double timeReq;


	clock_t time;
	time = clock();


	for (k = 0; k < size; k++) {
		numCompare++;
		for (i = 0; i < size - 1 - k; i++) {
			numCompare++;
			if (arrA[i] > arrA[i + 1]) {
				numSwaps++;
				//out of order so swap
				temp = arrA[i];
				arrA[i] = arrA[i + 1];
				arrA[i + 1] = temp;
			}
		} //end inner i for 
	} //end outer k for 

	time = clock() - time;
	timeReq = ((double)time) / CLOCKS_PER_SEC;
	printf("\n\nBubble Sort: \nTime Taken=%f seconds;\n", timeReq);
	printf("Number of swaps = %d;\n", numSwaps);
	printf("Number of comparisons = %d;\n", numCompare);
}

// Selection Sort: integer array arrA [] of size
void selectionSort(int arrA[], int size) {

	int i, j, min, temp;
	int numSwaps = 0;
	int numCompare = 0;
	double timeReq;


	clock_t time;
	time = clock();

	for (i = 0; i < size - 1; i++) {
		min = i;
		//find next smallest
		for (j = min + 1; j < size; j++) {
			if (arrA[min] > arrA[j]) {
				min = j;
			}
			numCompare++;
		}

		//swap values at locations i and min, if i != min
		if (i != min) {
			numSwaps++;
			temp = arrA[i];
			arrA[i] = arrA[min];
			arrA[min] = temp;
		}
	} //end outer while

	time = clock() - time;
	timeReq = ((double)time) / CLOCKS_PER_SEC;
	printf("\n\nSelection Sort: \nTime Taken=%f seconds;\n", timeReq);
	printf("Number of swaps = %d;\n", numSwaps);
	printf("Number of comparisons = %d;\n", numCompare);
}


// Insertion Sort: integer array arrA [] of size
void insertionSort(int arrA[], int size)
{
	int i, j, curr;
	int numSwaps = 0;
	int numCompare = 0;
	double timeReq;

	clock_t time;
	time = clock();

	for (i = 1; i < size; i++) {
		curr = arrA[i];

		for (j = i - 1; j >= 0 && curr < arrA[j]; j--) {   //compare
			numCompare++;
			//make room ...
			arrA[j + 1] = arrA[j];
			numSwaps++;
		}
		numCompare++;

		if (i != j + 1) // if not at the correct position already
			arrA[j + 1] = curr;
		numSwaps++;

	} // end outer i for

	time = clock() - time;
	timeReq = ((double)time) / CLOCKS_PER_SEC;
	printf("\n\nInsertion Sort: \nTime Taken=%f seconds;", timeReq);
	printf("Number of swaps = %d;\n", numSwaps);
	printf("Number of comparisons = %d;\n", numCompare);

} //return


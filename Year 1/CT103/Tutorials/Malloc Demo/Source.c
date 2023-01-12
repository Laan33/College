#include <stdio.h>
#include <stdlib.h>

void main() {
	srand(time(NULL));
	int x = 0;
	//float array1[]; 

	printf("Hello, enter a number please!\n");
	scanf_s("%d", &x);
	printf("\nYou entered %d, creating array with that size now", x);

	float* dynamic_array;
	dynamic_array = (float*)malloc(sizeof(float)*x);
	dynamic_array[0] = 1.0;

	printf("You entered %f woo hoo ! ", dynamic_array[0]);

	//array1 = ma


}
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

int visitors[7];

void recordVisit(int n);
int factorialNoRec(int n);
int factorialRec(int n);

void main() {
	int num = 2;
	int fact;

	recordVisit(7);


	fact = factorialNoRec(num);
	printf("Gan aon recursion in a usaid %d factorial = %d.\n", num, fact);

	fact = factorialRec(num);
	printf("Le recursion a usaid %d factorial = %d.\n", num, fact);

}

void recordVisit(int n) {
	int visit;

	if (n>0) {
		printf("Enter the number of visitors:");
		scanf_s("%d", &visit );
		visitors[7-n] = visit;
		recordVisit(n - 1);
	}
	else {
		printf("Daily visitors:\n");
		for (int i = 0; i < 7; i++) {
			printf("Day %d visitors = %d. \n", (i+1), visitors[i]);
		}
	}
}





int factorialNoRec(int n) {
	int ans = 1;
	for (int i = 1; i <= n; i++) {
		ans = ans * i;
	}
	return ans;
}

int factorialRec(int n) {
	if (n == 1) {
		return 1;
	}
	return n*factorialRec(n - 1);
}

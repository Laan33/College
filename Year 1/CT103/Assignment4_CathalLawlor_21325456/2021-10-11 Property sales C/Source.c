#include <stdio.h>
void main() {
	float SLES1 = 0.0;
	float SLES2 = 0.0;
	float SLES3 = 0.0;
	float SLES4 = 0.0;


	printf("Enter total daily sales on day 1 (Euro):");
	scanf_s("%f", &SLES1);

	printf("Enter total daily sales on day 2 (Euro):");
	scanf_s("%f", &SLES2);

	printf("Enter total daily sales on day 3 (Euro):");
	scanf_s("%f", &SLES3);

	printf("Enter total daily sales on day 4 (Euro):");
	scanf_s("%f", &SLES4);
	
	float TotalSales = (SLES1 + SLES2 + SLES3 + SLES4);
	float AvSales = (TotalSales / 4.0);

	printf("Total sales: %0.1f \n", TotalSales);
	printf("Average sales: %0.1f \n", AvSales);

	if (AvSales <= 10000) {
		printf("Sales are low\n");
	}
	else if (AvSales <= 15000) {
		printf("Sales are normal\n");
	}
	else {
		printf("Sales are high\n");
	}

	float AvCust = 500.0;
	float Avspend = (AvSales / AvCust);

	printf("Average spend per customer: %0.2f euros", Avspend);
}
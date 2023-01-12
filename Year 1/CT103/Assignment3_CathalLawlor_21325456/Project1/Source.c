#include <stdio.h>
void main() {
	int numStaff;
	int staffId;
	int year;
	int numNewStaff = 0;
	int firstYear = 100000;

	printf("How many staff?: ");
	scanf_s("%d", &numStaff);

	for (int i = 0; i < numStaff;i++) {
		printf("Enter staff ID: %d", i);
		scanf_s("%d", &staffId);

		if (staffId<0) {
			staffId = abs(staffId);
			printf("Negative numbers are not accepted,\n we have made your staff ID positive.\n");
		}

		if (staffId>=100000 || staffId<=9999) {
			printf("Invalid staff ID number, ID must be a five digit number. \n");
			printf("Please try again\n");
			i--;
		}

		else {

			year = staffId % 100;
			printf("Year hired = %d \n\n", year);

			if (year <= 21) {
				printf("Hired after 2000\n");
				numNewStaff++;

			}
			else {
				printf("Hired before 2000\n");

				if (year < firstYear) {
					firstYear = year;

				}
			}
		}


	} //curly

	printf("There are %d new staff \n", numNewStaff);
	printf("Earliest year employee hired pre 2000 = 19%d\n", firstYear);
}
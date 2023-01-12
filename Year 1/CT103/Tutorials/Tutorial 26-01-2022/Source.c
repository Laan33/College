#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

void main() {
	FILE* fptr1;
	FILE* fptr2;
	FILE* fptr3;
	char line[100];


	fopen_s(&fptr1, "C:\\Users\\catha\\Desktop\\rainfallMay.txt", "r");
	fopen_s(&fptr2, "C:\\Users\\catha\\Desktop\\rainfallJune.txt", "r");
	fopen_s(&fptr3, "C:\\Users\\catha\\Desktop\\rainfallSummary.txt", "w");

	if (fptr1 == NULL || fptr2 == NULL || fptr3 == NULL) {
		printf("Error with one of the selected files.\n");
		return;
	}

	else {
		printf("All good, all good, more staff next week\n");

		for (int i = 0; i < 2; i++) { //i @ 0 is may, 1 is june
			FILE* fptr;

			if (i == 0) {
				fptr = fptr1;
				printf("Month is May.\n");
				fprintf(fptr3, "Month is May.\n");
			}

			else	{
				fptr = fptr2;
				printf("Month is June.\n");
				fprintf(fptr3, "Month is June.\n");
			}


			fget(line, 100, fptr1);
			printf("%s.\n", line);

			int d, m, y;
			float rain;

			float maxRain = -10, minRain = 10000, avgRain, totRain = 0;;
			int dayCount = 0;


			while (!feof(fptr)) {
				fscanf_s(fptr, "%d/", &d);
				fscanf_s(fptr, "%d/", &m);
				fscanf_s(fptr, "%d\t", &y);
				fscanf_s(fptr, "%f\n", &rain);
				printf("%d/%d/%d\t0.2%f\n", d, m, y, rain);

				if (rain > maxRain) {
					maxRain = rain;
				}
				if (rain > minRain) {
					minRain = rain;
				}

				totRain += rain;
				dayCount += 1;

			}

			avgRain = totRain / dayCount;
			printf("Max rain = %0.2f\n", maxRain);
			printf("Min rain = %0.2f\n", minRain);
			printf("Total rain = %0.2f\n", totRain);
			printf("Avg rain = %0.2f\n", avgRain);

			fprintf(fptr3, "Max rain = %0.2f\n", maxRain);
			fprintf(fptr3, "Min rain = %0.2f\n", minRain);
			fprintf(fptr3, "Total rain = %0.2f\n", totRain);
			fprintf(fptr3, "Avg rain = %0.2f\n", avgRain);

			
		}
	}





	fclose(fptr1);
}
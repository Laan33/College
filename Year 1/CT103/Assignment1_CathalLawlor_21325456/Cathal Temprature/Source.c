#include "stdio.h"
void main()
{
	int degC = 0;
	int degF = 0;

	printf("enter temperature in degrees Celcius:  ");

	scanf_s("%d", &degC);

	degF = 32 + (degC * 9) / 5;

	printf("Degrees Fahrenheit = %d \n", degF);


	if (degC >= 25)
	{
		printf("TOO HOT!");
	}
}
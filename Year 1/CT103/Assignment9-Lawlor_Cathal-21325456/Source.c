//Cathal Lawlor - 21325456 - 23/11/2021
#include <stdio.h>
#include <string.h>
#include <math.h>

//function prototypes
float time();
float roundup(float time);
float electricity(float roundup);
void end(float roundup, float electricity);

//global variables.
float dayrate = 23.9;
float nightrate = 12.6;
float ft;
float rt;
float r;

//The main function that runs all other functions & contains vars used in such functions
void main() {
	time();
	roundup(ft);
	electricity(rt);
	end(rt, r);
}

//calculating time.
float time() {
	float hour;
	float min;

	printf("Enter hour of time: ");
	scanf_s("%f", &hour);
	if (hour < 0) {
		hour = 00;
	}
	else if (hour >= 24) {
		hour = 23;
	}

	printf("Enter minute of time: ");
	scanf_s("%f", &min);

	if (min < 0) {
		min = 01;
	}
	else if (min >= 60) {
		min = 00;
	}
	min = min / 60;
	ft = hour + min;
	printf("Final time is: %0.2f \n", ft);
	return ft;
}
//round time up.
float roundup(float ft) {
	rt = ceil(ft);
	printf("Rounded time is: %0.2f \n", rt);
	return rt;
}
//calculating electricity rate.
float electricity(float rt) {

	r = (rt >= 8.00 && rt < 11.59) ? dayrate : nightrate;
	return r;
}
//printing electricity rate.
void end(float rt, float r) {
	printf("Electricity price at %0.2f is %0.2f. \n", rt, r);
}

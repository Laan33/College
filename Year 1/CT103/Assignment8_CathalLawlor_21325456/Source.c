//Cathal Lawlor - 21325456 - 16/11/2021
#include <stdio.h>
#include <string.h>
#include <ctype.h>

//setting the prototypes
float volume(float l, float w, float h);
void mass(float volume);
float barrels(float volume);
void display(float volume, float barrels);

//main function that will run the other functions. Contains varibles for calulating the volume.
void main() {
    int id = 21325456;
    float l = 4;
    float w = 5;
    float h = 6;
    volume(l, w, h);
    float totalvolume = volume(l, w, h);
    barrels(totalvolume);
    float totalbarrels = barrels(totalvolume);
    display(totalvolume, totalbarrels);
    mass(totalvolume);
}
//function the calculate the volume.
float volume(float l, float w, float h) {
    float v = l * w * h;
    return v;
}
//function to calculate and print the mass.
void mass(float v) {
    float mass = v * 1000;
    printf("Mass of water: %0.2f kg \n", mass);
}
//function to calculate the barrels filled.
float barrels(float v) {
    float b = 0.48 / v;
    return b;
}
//function to print the ID, Volume and barrels.
void display(float v, float b) {
    printf("ID number: 21415252 \n");
    printf("Volume of the water: %0.2f m^3 \n", v);
    printf("Barrels: %0.3f \n", b);

}
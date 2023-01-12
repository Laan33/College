//cathal Lawlor – assignment 10 

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int maxpass;
int pass;
int count;
int rdmpasscode;
int srchpass;
int count2;
int digits;


int setpasscode(int maxpass); //prototyping the functions
int orderedpasscodesearch(int ran, int count2);
int finalcode(int ran);
void randompasscodesearch(int ran);

void main()
{
    for (int i = 0; i < 4; i++) {
        setpasscode(maxpass);
        randompasscodesearch(pass);
        orderedpasscodesearch(pass, srchpass);
    }
    finalcode(pass);
    randompasscodesearch(pass);
    orderedpasscodesearch(pass, srchpass);
}



int setpasscode(int maxpass) { //inputting the passcodes
    printf("Enter max password size required : ");
    scanf_s("%d", &maxpass);
    long lt = time(NULL);
    srand(lt);
    pass = rand() % maxpass + 1; 
    printf("Password is %d\n", pass);


    return pass;
}

void randompasscodesearch(int ran) { //trying to find the passcode randomly

    long l = time(NULL);
    srand(l * 2);
    do {
        rdmpasscode = rand();
        count++;
    } while (rdmpasscode != ran);
    printf("Password found with %d attempts.\n", count);
}


int orderedpasscodesearch(int ran) { //ordered search of passcode
    while (ran != count2) {
        count2++;
    }
    printf("Password found with %d attemps. \n", count2 + 1);
    return count2;
}


int finalcode(int ran) { //testing using my student id num 
    pass = 456;
    printf("Final password is: %d", pass);
    return pass;
}
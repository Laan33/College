#include <stdio.h>

int main(int arg, char *argc[]) {
    printf("Hello assignment1.\n");

    /*
     (A) Write code that creates variables of the following types and prints out their size using
  sizeof(): [2.5 marks]
  ● int
  ● int*
  ● long
  ● double *
  ● char **
     */

    int integer_var;
    int *int_pointer;
    long long_var;
    double *double_pointer;
    char **char_double_pointer;

    printf("Size of int: %zu bytes\n", sizeof(integer_var));
    printf("Size of int*: %zu bytes\n", sizeof(int_pointer));
    printf("Size of long: %zu bytes\n", sizeof(long_var));
    printf("Size of double*: %zu bytes\n", sizeof(double_pointer));
    printf("Size of char**: %zu bytes\n", sizeof(char_double_pointer));

    return 0;
}


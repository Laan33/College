#include <stdio.h> 
#include <string.h>
#include <stdlib.h>
#include <time.h>

#define MAXSTRING 100

char dictionary[100000][MAXSTRING];
int numWords = 0;

void readDictionary();
int rnd(int lower, int upper);

int main() {
    srand(time(0));
    readDictionary();
    if (numWords > 0) {
        // pick a random word to use
        int wordIdx = rnd(0, numWords - 1);
        char* theWord = dictionary[wordIdx];
       
        //printf("Target word: %s\n", theWord);

        // initialise the gradually-revealed word as a string of dashes
        
        // gameplay loop
        char userWord [20]; 

      
            
        }
    }

void readDictionary() {
    // fopen requests a file to be opened obtains a FILE pointer to access it
    FILE* file_ptr;
    file_ptr = fopen("dictionary.txt", "r"); // open for reading

    if (file_ptr == NULL) {
        printf("Could not open dictionary.txt");
    }
    else {
        /*while (fgets(dictionary[numWords], MAXSTRING - 1, file_ptr) != NULL) {
            int len = strlen(dictionary[numWords]);
            dictionary[numWords][len - 1] = '\0'; // remove the \n which has also been read into the string!
            len--;
            
        } */



        printf("Loaded %d suitable words from the dictionary.\n", numWords);
        fclose(file_ptr); // don't forget to close the file
    }
}

int rnd(int lower, int upper) {
    int range = (upper - lower) + 1;
    return (rand() % range) + lower;
}
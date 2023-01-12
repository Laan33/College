//Cathal Lawlor Assignment 20 2/3/2022
//21325456

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int readWords(char filePath[]);
void guessing();

//creating struct needed for words
typedef struct {
	char dictWord[20];
}dictionary;

//creating 100,000 dictionary's for each word
dictionary word[100000];
 

void main() {
	char myFilePath[] = "C:\\Users\\catha\\downloads\\dictionary.txt";//filepath

	int numWords = readWords(myFilePath);//readWords returning i and placing each word into the word array
	printf("Loaded %d words within criteria from the dictionary.\n\n", numWords);

	guessing();//calling guessing function 
}


int readWords(char filePath[]) {
	dictionary newWord; //new dictionary word
	FILE* fptr; //file pointer

	
	char line[200];
	char delims[] = "\n";
	int i = 0;

	fopen_s(&fptr, filePath, "r");//opening file

	if (fptr == NULL) {//checking file is working / exists
		printf("Error, file not found, Exiting....");
	}
	else {
		while (!feof(fptr) && i < 100000) {//going through the 100,000 words with a while loop
			fgets(line, 200, fptr);//getting each line

			char* next = NULL;
			char* first = strtok_s(line, delims, &next);//using strtok to read in each line

			if (strlen(first) >= 4 && strlen(first) <= 7) {
				strcpy_s(newWord.dictWord, 20, first);
				word[i] = newWord;
				i++;//reading line and saving to word array if word is within condtions ( >= 4 and <= 7) 
			}
		}
		fclose(fptr);//closing the file
	}

	return i;//returning the number of words in the array
}



void guessing() {
	srand(time(NULL));

	char chosenWord[20];//a new word
	char guessedLetter;//character variable for guessed letter
	char emptyString[20];//blank string that will be changed
	int found = 0;//checking if word is found or not
	int i = 0;//for loop counter
	int count = 0;//number of tries counter

	int randNumber = rand() % 100000;//creating a random number for finding word 
	strcpy_s(chosenWord, 15, word[randNumber].dictWord); //using random number to create word and saving it to chosenWord

	if (chosenWord == NULL) {//if chosenWord is NULL run this again
		int randNumber = rand() % 100000;
		strcpy_s(chosenWord, 20, word[randNumber].dictWord);
	}

	for (int i = 0; i < strlen(chosenWord); i++) {
		emptyString[i] = '-';//initialises blank string to -'s same number as word to show user position of letters and unknowns
	}
	emptyString[strlen(chosenWord)] = '\0';//putting end of string for emptyString

	while (found == 0) {//seeing if word is found
		printf("Guess %d.\n", i + 1);//Guess number for user

		printf("%s", emptyString);//printing out emptyString and letters found if any

		printf("\nGuess a letter > ");//lets user know to guess a letter
		scanf_s(" %c", &guessedLetter);//saves this character to guessedLetter


		for (int j = 0; j < strlen(chosenWord); j++) {//for loop to check if character is part of word
			if (chosenWord[j] == guessedLetter) {
				emptyString[j] = guessedLetter;//if so, the letter is placed in appropriate places
			}
		}

		int value = strcmp(chosenWord, emptyString);//value to see if strings are identical
		if (value == 0) {//if so
			printf("\n");//print new line
			printf("Well done, that took you %d guesses to find %s!\n\n", i + 1, chosenWord);//print number of tries it took
			break;//exit the function
		}

		i++;//i incrementer
	}

}




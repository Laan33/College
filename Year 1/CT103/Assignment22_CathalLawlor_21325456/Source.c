/*Name: Cathal Lawlor
  Student ID: 21325456
  Date: 22nd March 2022 */
 
#include <stdio.h> 
#include <string.h>
#include <ctype.h>
#include <math.h>

//function prototypes
void fileReader(char filePath[]);
void locationMover();

//struct for user actions
typedef struct {
	char action[20];
}actions;

//initialising the struct
actions s[20];

//struct to read in 2d array of integers and their descriptions
typedef struct {
	int res[9][7];
	char description[100];
} idValues;

//initialising the struct
idValues vals[20];

//main function
void main() {
	char myFilePath[] = "C:\\Users\\catha\\Desktop\\adventure_locations.txt";//filepath

	fileReader(myFilePath);//calling read file function
	locationMover();//calling location mover function
}

void fileReader(char filePath[]) {
	idValues iValue;//initialise one struct iValue for 2d array and descriptions
	FILE* fptr;//file pointer
	char delims[] = "\t";//delimeter needed for strtok_s

	char inputString[20];//string to convert to lowercase

	//counters
	int i = 0;
	int k = 0;

	char line[200];//to get each line

	fopen_s(&fptr, filePath, "r");//opening file

	if (fptr == NULL) {
		printf("Error opening file, exiting ....");//printing error message for file failure
	}
	else {
		fgets(line, 200, fptr);//getting first line

		//setting up next and first for string token function
		char* next = NULL;
		char* first = strtok_s(line, delims, &next);

		while (first != NULL) {
			strcpy_s(inputString, 20, first);//copy first to input String

			//Convert each character to lowercase
			while (inputString[k] != '\0') {
				inputString[k] = tolower(inputString[k]);
				k++;
			}
			k = 0;//reset k counter to 0
			strcpy_s(s[i].action, 20, inputString);//copy changed string to structure s, under action

			first = strtok_s(NULL, delims, &next);//resetting first

			i++;//incrementing i
		}

		i = 0;//resetting i to 0 for next part

		fgets(line, 200, fptr);//getting second line, but isn't really needed


		for (int i = 0; i < 9; i++) {//done 9 times
			fgets(line, 200, fptr);//getting third line

			next = NULL;//setting next

			//for each number we save it inside a 2d array and reset the value of first
			first = strtok_s(line, delims, &next);
			iValue.res[i][0] = atoi(first);

			first = strtok_s(NULL, delims, &next);
			iValue.res[i][1] = atoi(first);

			first = strtok_s(NULL, delims, &next);
			iValue.res[i][2] = atoi(first);

			first = strtok_s(NULL, delims, &next);
			iValue.res[i][3] = atoi(first);

			first = strtok_s(NULL, delims, &next);
			iValue.res[i][4] = atoi(first);

			first = strtok_s(NULL, delims, &next);
			iValue.res[i][5] = atoi(first);

			first = strtok_s(NULL, delims, &next);
			iValue.res[i][6] = atoi(first);

			//finally we copy the description string in 
			first = strtok_s(NULL, delims, &next);
			strcpy_s(iValue.description, 100, first);

			//Letting vals[i] equal to all values gotten in iValue
			vals[i] = iValue;
		}
	}
}

void locationMover() {
	printf("Welcome to Galway Adventure. Type 'help' for help.");//Intro message
	printf("\n\n");

	printf("%s\n", vals[0].description);//print description of id 1

	char chosenAction[20];//string to save chosen action by user

	//three strings to compare
	char help[] = "help";
	char quit[] = "quit";
	char look[] = "look";

	//quitInt to exit programme if user types quit
	int quitInt = 0;

	//id's changed upon user input
	int currId = 1;
	int prevId = 0;

	//counter needed to change user input to lowercase
	int j = 0;

	while (quitInt == 0) {
		//scan user's action and save to chosenAction
		printf("> ");
		scanf_s("%s", chosenAction, 20);

		chosenAction[strlen(chosenAction)] = '\0';//set end of string

		//converting letters to lowercase
		while (chosenAction[j] != '\0') {
			chosenAction[j] = tolower(chosenAction[j]);
			j++;
		}
		j = 0;//reset j

		if (strcmp(chosenAction, help) == 0) {//if user types help
			printf("\n");
			printf("I know these commands:\n");//Give these commands
			printf("n, s, e, w, in, out, look, help, quit.\n\n");
		}

		if (strcmp(chosenAction, quit) == 0) {//if user types quit
			quitInt = 1;//change this to 1, this quits our while loop ending the program
			printf(" Bye!");//bye message
		}

		for (int i = 1; i < 10; i++) {//for loop to loop through all possible actions
			if (strcmp(chosenAction, look) == 0) {//if user types look
				printf("%s\n", vals[currId - 1].description);//print out the description of the Id they are on
				break;
			}
			if (strcmp(chosenAction, s[i].action) == 0) {//when user input is same as action
				//let current id be equal to the value that the action points to
				currId = vals[currId - 1].res[currId - 1][i];//CurrID - 1 here as my array is 0-indexed

				if (currId == 0) {//if current id = 0
					printf("You can't go that way\n");//error message
					currId = prevId;//current id set back to previous Id value
					break;//break
				}
				else {
					//otherwise print out the description of current location
					printf("%s\n", vals[currId - 1].description);
				}

				//set previous Id equal to current Id
				prevId = currId;
			}
		}
	}
	printf("\n");
}





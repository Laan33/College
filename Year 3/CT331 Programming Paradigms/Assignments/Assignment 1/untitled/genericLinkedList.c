//
// Created by catha on 19/10/2023.
//

//The linked list currently only accepts char* data. Create the files genericLinkedList.h and
//genericLinkedList.c files, extend the linked list to accept any data type ( Hint: Use
//void* to create a pointer to any data type.)
//[10 marks total]
//● Ensure all functions from Question 2 work accordingly.
//● Traverse() will not be able to format the data (%d, %s, %ld etc...) so…
//○ Update the struct to store a function pointer. The function should print
//out a specific data type ( like printStr() will print a string, and printInt() will
//print an integer) Eg:
//void printChar(void* data){ printf("%c\n",
//*(char*)data);
//}
//○ When traverse is called, use the function pointer with the element’s data to
//print it out.
//Eg:
//element->printFunction(element->data);


#include <stdio.h>
#include <malloc.h>
#include "genericLinkedList.h"





//Returns a pointer to the element
listElement* createElGeneric(void* data, void (*printfunction)(void* data)) {
    listElement* e = malloc(sizeof(listElement));
    if (e == NULL) {
        // Handle memory allocation error
        return NULL;
    }

    e->data = data;
    e->printfunction = printfunction;
    e->next = NULL;
    return e;
}


//Prints out each element in the list
void traverseGeneric(listElement *start) {
    listElement *current = start;
    while (current != NULL) {
        current->printfunction(current->data);
        current = current->next;
    }
}

void printChar(void *data) {
    printf("%c\n",
           *(char *) data);
}

void printInt(void *data) {
    printf("%d\n",
           *(char *) data);
}

void printStr(void *data) {
    printf("%s\n", (char*) data);
}

void printFloat(void *data) {
    printf("%.3f\n", *(float *) data);
}

//Inserts a new element after the given el
//Returns the pointer to the new element
listElement *insertAfterGeneric(listElement *after, void *data, void (*printfunction)(void *)) {
    listElement *newEl = createElGeneric(data, printfunction);
    if (newEl == NULL || after == NULL) {
        // Handle memory allocation failure OR if given no list
        return NULL;
    }

    listElement *next = after->next;
    newEl->next = next;
    after->next = newEl;
    return newEl;
}


//Delete the element after the given el
void deleteAfterGeneric(listElement *after) {
    if (after == NULL || after->next == NULL) {
        return;
    }

    listElement *delete = after->next;
    after->next = delete->next;
    delete->next = NULL;
    free(delete->data);
    free(delete);
}



//Returns the number of elements in a linked list.
int lengthGeneric(listElement *list) {
    int count = 0;
    listElement *current = list;

    while (current != NULL) {
        count++;
        current = current->next;
    }
    return count;

}

//Push a new element onto
//the head of a list. ○ Update the list reference using side effects.
void pushGeneric(listElement **list, void *data, void (*printfunction)(void *)) {
    listElement *newEl = createElGeneric(data, printfunction);

    // Check if memory allocation was successful
    if (newEl != NULL) {
        newEl->next = *list;  // Point the new element to the current head
        *list = newEl;        // Update the list reference to the new element
    }
}

//Pop an element from the head of a list.
//Update the list reference using side effects.
listElement *popGeneric(listElement **list) {
    //Declare and assign the popped element
    listElement *poppedElement = *list;

    //Checking if it's not null first
    if(*list != NULL) {
        *list = (*list)->next; // Update the list to point to the next element
    }
    return poppedElement;
}

//Enqueue a new element onto the head of the list.
//Update the list reference using side effects.
void enqueueGeneric(listElement **list, void *data, void (*printfunction)(void *)) {
    listElement *newEl = createElGeneric(data, printfunction);

    // Check if memory allocation was successful
    if (newEl == NULL) {
        // Handle the error, such as returning or exiting the function
        return;
    }

    newEl->next = *list;

    *list = newEl;
}

//Dequeue an element from the tail of the list.
listElement *dequeueGeneric(listElement *list) {
    listElement *current = list;
    listElement *prevCurr = NULL;

    // Check if the list is empty
    if (current == NULL) {
        return NULL;
    }

    while (current->next != NULL) {
        prevCurr = current;
        current = current->next;
    }

    // If the previous element exists, remove the last element
    if (prevCurr != NULL) {
        prevCurr->next = NULL;
    } else {
        // If there's no previous element, the list only had one element
        list = NULL; // Cast NULL to the correct pointer type
    }

    return current;
}








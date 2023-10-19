//
// Created by catha on 19/10/2023.
//

#include "linkedList.h"
#include "linkedList-1.h"

#include <stddef.h>



//Returns the number of elements in a linked list.
int length(listElement *list) {
    int count = 0;
    listElement *current = list;

    while (current != NULL) {
        count++;
        current = current->next;
    }
    return count;

}


//Push a new element onto the head of a list.
//Update the list reference using side effects. - See: swap() from lecture 3
void push(listElement **list, char *data, size_t size) {
    listElement *newEl = createEl(data, size);

    // Check if memory allocation was successful
    if (newEl != NULL) {
        newEl->next = *list;  // Point the new element to the current head
        *list = newEl;        // Update the list reference to the new element
    }
}

//Pop an element from the head of a list.
//Update the list reference using side effects.
listElement *pop(listElement **list) {
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
void enqueue(listElement** list, char* data, size_t size) {
    listElement *newEl = createEl(data, size);

    // Check if memory allocation was successful
    if (newEl == NULL) {
        // Handle the error, such as returning or exiting the function
        return;
    }

    newEl->next = *list;

    *list = newEl;
}

//Dequeue an element from the tail of the list.
listElement* dequeue(listElement* list) {
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
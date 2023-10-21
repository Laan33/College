//
// Created by catha on 19/10/2023.
//

#ifndef UNTITLED_GENERICLINKEDLIST_H
#define UNTITLED_GENERICLINKEDLIST_H

typedef struct listElementStruct {
    void *data;

    void (*printfunction)(void *);

    struct listElementStruct *next;
} listElement;

//Returns a pointer to the element
listElement* createElGeneric(void* data, void (*printfunction)(void* data));

//Prints out each element in the list
void traverseGeneric(listElement *start);

void printChar(void *data);

void printInt(void *data);

void printStr(void *data);

void printFloat(void *data);

//Inserts a new element after the given el
//Returns the pointer to the new element
listElement *insertAfterGeneric(listElement *after, void *data, void (*printfunction)(void *)) ;

//Delete the element after the given el
void deleteAfterGeneric(listElement *after);


//Returns the number of elements in a linked list.
int lengthGeneric(listElement *list);

//Push a new element onto
//the head of a list. ○ Update the list reference using side effects.
void pushGeneric(listElement **list, void *data, void (*printfunction)(void *));

//Pop an element from the head of a list.
//Update the list reference using side effects.
listElement *popGeneric(listElement **list);

//Enqueue a new element onto the head of the list.
//Update the list reference using side effects.
void enqueueGeneric(listElement **list, void *data, void (*printfunction)(void *));

//Dequeue an element from the tail of the list.
listElement *dequeueGeneric(listElement *list);



#endif //UNTITLED_GENERICLINKEDLIST_H


//extend the linked list to accept any data type ( Hint: Use
//void* to create a pointer to any data type.)
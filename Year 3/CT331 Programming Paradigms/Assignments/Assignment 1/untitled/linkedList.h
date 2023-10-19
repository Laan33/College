//
// Created by catha on 19/10/2023.
//

#ifndef UNTITLED_LINKEDLIST_H
#define UNTITLED_LINKEDLIST_H


#include <stddef.h>
#include "linkedList-1.h"

typedef struct listElementStruct {
    char *data;
    size_t size;
    struct listElementStruct *next;
} listElement;

//Returns the number of elements in a linked list.
int length(listElement *list);

//Push a new element onto
//the head of a list. ○ Update the list reference using side effects.
void push(listElement **list, char *data, size_t size);

//Pop an element from the head of a list.
//Update the list reference using side effects.
listElement *pop(listElement **list);

#endif //UNTITLED_LINKEDLIST_H

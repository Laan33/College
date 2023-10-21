//
// Created by catha on 20/10/2023.
//

#include <stdio.h>
#include <malloc.h>
#include "Question3.h"
#include "genericLinkedList.h"
void question3() {
    printf("Question 3.\n\n");

    // Create the initial list with a string
    listElement *l = createElGeneric("Test String (1).", printStr);

    // Test createElGeneric and traverseGeneric
    printf("Initial list:\n");
    traverseGeneric(l);
    printf("\n");

    // Test insertAfterGeneric with an integer
    int intData = 42;
    listElement *l2 = insertAfterGeneric(l, &intData, printInt);
    printf("After inserting an integer:\n");
    traverseGeneric(l);
    printf("\n");

    // Test insertAfterGeneric with a character
    char charData = 'A';
    listElement *l3 = insertAfterGeneric(l, &charData, printChar);
    printf("After inserting a character:\n");
    traverseGeneric(l);
    printf("\n");

    // Test lengthGeneric
    int listLength = lengthGeneric(l);
    printf("Length of the list: %d\n\n", listLength);

    // Test pushGeneric with a string
    char *strData = "Pushed String (5)";
    pushGeneric(&l, strData, printStr);
    printf("After pushing a string:\n");
    traverseGeneric(l);
    printf("\n");

    // Test popGeneric
    listElement *poppedElement = popGeneric(&l);
    printf("Popped element data: %s\n", (char *) poppedElement->data);
    printf("After popping the head element:\n");
    traverseGeneric(l);
    printf("\n");

    // Test enqueueGeneric with a float
    float floatData = 8.2f;
    enqueueGeneric(&l, &floatData, printFloat);
    printf("After enqueuing a float:\n");
    traverseGeneric(l);
    printf("\n");

    // Test dequeueGeneric
    listElement *dequeuedElement = dequeueGeneric(l);
    printf("Dequeued element data: %d\n", *(int *) dequeuedElement->data);
    printf("After dequeuing the tail element:\n");
    traverseGeneric(l);
    printf("\n");

}
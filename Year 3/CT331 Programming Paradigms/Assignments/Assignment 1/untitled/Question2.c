//
// Created by catha on 19/10/2023.
//

#include <stdio.h>
#include "linkedList.h"
#include "linkedList-1.h"


void question2() {


    printf("Question 2.\n");

// Create the initial list
    listElement *l = createEl("Test String (1).", 30);

    // Test create and traverse
    printf("Initial list:\n");
    traverse(l);
    printf("\n");

    // Test insert after
    listElement *l2 = insertAfter(l, "another string (2)", 30);
    insertAfter(l2, "a final string (3)", 30);
    printf("After insertions:\n");
    traverse(l);
    printf("\n");

    // Test push
    push(&l, "new head string (0)", 30);
    printf("After push:\n");
    traverse(l);
    printf("\n");

    // Test pop
    listElement *popped = pop(&l);
    printf("Popped element: %s\n", popped->data);
    printf("After pop:\n");
    traverse(l);
    printf("\n");

    // Testing length
    printf("Length of the list: %d\n", length(l));

    printf("Tests complete.\n");
}


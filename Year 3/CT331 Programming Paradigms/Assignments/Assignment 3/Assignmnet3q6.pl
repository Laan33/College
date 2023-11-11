/*
Write a set of prolog facts and rules called insertInOrder that inserts an element into its
correct position in a given list. The given element must be the first argument, the given
list must be the second and the final list with the inserted element must be the third
argument. You can assume that the 2nd argument is sorted in ascending order,
Eg: insertInOrder(El, List, NewList) …
Your code should produce the following results:
?- insertInOrder(7,[1,2,3], X).
X = [1,2,3,7].
?- insertInOrder(2, [3], X).
X = [2, 3].
?- insertInOrder(1, [], X).
X = [1]. (5 marks)
*/


/*Recursively go through the list, pushing the element at the start, comparing, 
and then going and doing the tail of the first comparison to the rest of the list, 



*/

/*Base case - list is empty, the element is placed in the return list */
insertInOrder(X, [], [X]). 

/*if the element is less than the head, it is placed at the start of the list */
insertInOrder(X, [H|T], [X,H|T]) :- 
    X =< H. 

/*if the element is greater than the head, we recurse through the rest of list - return a list having the head, and the return of the recursive call as the tail */
insertInOrder(X, [H|T], [H|Rest]) :- 
    X > H, 
    insertInOrder(X, T, Rest). /*recursively go through the remainder of the list, and return the list with the element inserted in the correct position */
    


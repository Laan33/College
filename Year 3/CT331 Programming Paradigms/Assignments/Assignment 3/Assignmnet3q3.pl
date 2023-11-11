/*
Write a set of prolog facts and rules called isNotElementInList that determine if a
given element is not in a given list. The element must be the first argument and the
list must be the second.
Eg: isNotElementInList(El, List) …
Your code should produce the following results:
*/

/*
?- isNotElementInList(1, []).
True.
*/

/*
?- isNotElementInList(1, [1]).
False.
*/

/*
?- isNotElementInList(1, [2]).
True.
*/

/*
?- isNotElementInList(2, [1, 2, 3]).
False.
*/

/*
?- isNotElementInList(7, [1, 2, 9, 4, 5]).
True. (5 marks)
*/


isNotElementInList(_ , []). %Base case, no element is in a list if the list is empty

isNotElementInList(El, [H | T]) :- %Recursive case, if the element is not in the list    
    El \= H, %Checking if el is not equals to the head of the list
    isNotElementInList(El, T). %Recurse through the tail of the list



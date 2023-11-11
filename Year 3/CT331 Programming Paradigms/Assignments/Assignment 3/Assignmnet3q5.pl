
/*
Write a set of prolog facts and rules called reverseList that reverses a given list. The
given list must be the first argument and the reversed list must be the thrird
Eg: reverseList(List, ReversedList) …
Your code should produce the following results:
?- reverseList([1,2,3], X).
X = [3,2,1].
?- reverseList([1], X).
X = [1].
?- reverseList([], X).
X = []. (5 marks)

*/

%Base case - reversing an empty list results in an empty list.
/*
reverseList([], []).

reverseList( , , Accumulator)


reverseList(List, ReversedList) :-
    reverseList([Head|Tail], [Tail|Head]). */

% This is the helper function that will be called by the user. 
reverseList(List, Reversed) :- reverseList(List, [], Reversed).

% Base case - reversing an empty list results in an empty list.
reverseList([], Reversed, Reversed).

% Recursive case - add the head of the list to the accumulator and call reverseList on the tail.
reverseList([H|T], Acc, Reversed) :- reverseList(T, [H|Acc], Reversed).
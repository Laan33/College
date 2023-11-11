

/*
Write a set of prolog facts and rules called mergeLists that merges (concatenates / appends) three lists. The given lists must be the first three arguments, and the merged
list must be the fourth argument. Do not use the built in append rule.
Eg: mergeLists(List1, List2, List3, Merged) …
Your code should produce the following results:
*/

/*
?- mergeLists([7],[1,2,3],[6,7,8], X).
X = [7,1,2,3,6,7,8].
*/

/*
?- mergeLists([2], [1], [0], X).
X = [2, 1, 0].
*/

/*
?- mergeLists([1], [], [], X).
X = [1]. (5 marks)
*/

/*Go and recurse list 2 and 3 first and then do list 1 and 23 */

% Helper predicate: mergeLists/3 
%Merges two lists by merging the second and third lists and then merging the first list with the result.
mergeLists(List1, List2, List3, Merged) :-
    mergeLists(List2, List3, List23), % Merge the second and third lists.
    mergeLists(List1, List23, Merged). % Merge the first list with the result.

% Base case: Merging an empty list with another list results in the same list.
mergeLists([], List, List).

% Recursive rule: Merge two non-empty lists.
mergeLists([Head|Tail1], List2, [Head|ResultTail]) :- 
    % Take the head of the first list and then recursively merge the rest of the first list with the second list.
    mergeLists(Tail1, List2, ResultTail).



/*

In this example:

    The base case states that merging an empty list with another list results in the second list.
    The recursive rule says that to merge two non-empty lists, take the head of the first list and then recursively merge the rest of the first list with the second list.

You can use this merge_lists/3 predicate by providing it with two lists and obtaining the merged result.

*/

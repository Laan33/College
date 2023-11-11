


/*
1. Using the “=” sign and the prolog list syntax to explicitly unify variables with
parts of a list, write a prolog query that displays the head and tail of the list [1,2,3]. */
%?- [H|T] = [1,2,3].




/*
2. Similarly, use a nested list to display the head of the list, the head of the tail of the list
and the tail of the tail of the list [1,2,3,4,5]. */ 
%?- [H|[TH|TT]] = [1,2,3,4,5].


/*
3. Write a prolog rule 'contains1' that returns true if a given element is the first
element of a given list. */

% Sample list as a fact to test with: [1,2,3,4,5].

contains1(X, [X|_]).

/*
4. Write a prolog rule 'contains2' that returns true if a given list is the same as the tail of
another given list. */
contains2(Y, [_|T]) :- Y = T.



/*
5. Write a prolog query using 'contains1' to display the first element of a given list. */
%contains1(FirstEl, [1,2,3]).



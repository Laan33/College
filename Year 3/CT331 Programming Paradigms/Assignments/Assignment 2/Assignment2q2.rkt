#lang racket


;;
;;Write a Scheme function named ins_beg which, when passed an element and a list,
;;inserts the element at the beginning of the list. You can assume only valid
;;elements and lists.
;;e.g., if the function is called ins_beg:
;;a. (ins_beg ‘a ‘(b c d)) returns the list (a b c d)
;;b. (ins_beg ‘(a b) ‘(b c d)) returns the list ((a b) b c d)


(provide ins_beg)
(provide ins_end)


(define (ins_beg el lst)
  (cons el lst))


;;B. Write a Scheme function named ins_end which, when passed an element and a
;;list, inserts the element at the end of the list. You can assume only valid elements
;;and lists.
;;e.g., if the function is called ins_end:
;;a. (ins_end ‘a ‘(b c d)) returns the list (b c d a)
;;b. (ins_end ‘(a b) ‘(b c d)) returns the list (b c d (a b))



(define (ins_end el lst)
  (cons lst el))

;;C. Write a Scheme function named cout_top_level that counts the number of top-
;;levels
;;items in a list, i.e., you don’t need to include items in a sub list.
;;D. Write a non-tail recursive function called count_instances that counts the number
;;of times an item occurs in a list of items (you may assume all items are atomic).
;;E. Write a tail-recursive version of your solution to part D called count_instances_tr
;;F. Write a Scheme function named count_instances_deep that counts the number of
;;times an item occurs in a list of items; note that the list may contain sub-lists and you
;;should also count occurrences in those lists.





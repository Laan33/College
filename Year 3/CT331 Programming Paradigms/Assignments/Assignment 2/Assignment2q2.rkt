#lang racket
(provide ins_beg)
(provide ins_end)
(provide cout_top_level)
(provide count_instances)
(provide count_instances_tr)
(provide count_instances_tr_helper)
(provide count_instances_deep)

;; ins_beg: Scheme function that takes an element and a list and returns a new list with the element added to the beginning of the original list.
(define (ins_beg el lst)
  (cons el lst)) ;;creating new list containing the new element at the start using cons

;; ins_end: Scheme function that takes an element and a list and returns a new list with the element added to the end of the original list.
(define (ins_end el lst)
  (append lst (cons el empty))) ;;Assuming lst is a list, and appending a new list (created using cons) with el to the end of lst

;; cout_top_level: Scheme function that takes a list and returns the number of top-level items in the list (i.e., items that are not in a sublist).
(define (cout_top_level lst)
  (if (empty? lst) ;; empty? is a built-in function to 0 when true - if the list is empty.
      0
      (+ 1 (cout_top_level (cdr lst))) ;;if not empty, return 1 + counting the rest of the list - recursively
  )
)

;;D. Write a non-tail recursive function called count_instances that counts the number
;;of times an item occurs in a list of items (you may assume all items are atomic).
(define (count_instances item lst)
  (cond
    [(null? lst) 0] ;; Base case - list is empty / finished counting
    [(equal? (car lst) item) (+ 1 (count_instances item (cdr lst)))] ;;If the first element in list matches return incremented by 1 + recursively count the remainder of the list 
    [else (count_instances(cdr lst))] ;;Otherwise, continue with the rest of the list.
  )
)


;; E. Write a tail-recursive version of part D solution called count_instances_tr
(define (count_instances_tr item lst cnt)
  (cond
    [(null? lst) cnt] ;; Base case - list is empty, finished counting, return count

    ;; if first element is equals to the item
    [(eq? (car lst) item)
      ;; increment 1 + recurse remainder of the list
      (count_instances_tr item (cdr lst) (+ cnt 1))
    ]

  ;; else - first element isn't equal to the item
  [else 
    ;; recursively count the remainder of the list - count is the same
    (count_instances_tr item (cdr lst) cnt)
  ]
  )
)

;; Tail recursive function helper for count of a given item in a list - assumption all items are atomic
(define (count_instances_tr_helper item lst)
  ;;Calling the function with starter count - 0 
  (count_instances_tr item lst 0)
)
  

;; F. Write a Scheme function named count_instances_deep that counts the number of
;; times an item occurs in a list of items; note that the list may contain sub-lists and you
;; should also count occurrences in those lists.
(define (count_instances_deep item lst)
  (cond
    [(null? lst)  ;; Base case - list is empty / finished counting
      0
    ]  
    
    ;; If the first element in the list is a list, recurse through the list, and then recurse through the remainder of the main list
    ;; returning the sum of the two results 
    [(pair? (car lst))
      (+ (count_instances_deep item (car lst)) (count_instances_deep item (cdr lst)))
    ]
    
    ;; If the first element in list matches return incremented by 1 + recursively count the remainder of the list 
    [(equal? (car lst) item)
      (+ 1 (count_instances_deep item (cdr lst)))
    ] 
    
    [else (count_instances_deep item (cdr lst))] ;;Otherwise, continue with the rest of the list.
  )
)



#lang racket


(provide display_in_order)
(provide tree_search)
(provide tree_insert)
(provide tree_insert_list)
(provide tree_sort)
(provide tree_sort_ho)
(provide tree_sort_ho_list)
(provide sort-ascending)
(provide sort-descending)
(provide sort-ascending-last-digit)




;; A. Display in sorted order the contents of a binary search tree

;; Once the binary search tree has been created, its elements can be retrieved in-order by: 
  ;; ● Recursively traversing the left subtree of the root node.
  ;; ● Accessing the node itself
  ;; ● Then recursively traversing the right subtree of the node

(define (display_in_order bst)
  (cond
  ;; If current tree is null, print out a string that's empty (nothing)
  [(null? bst) (display "")]

  ;; If the binary tree has nodes - continue traversing
  [else
    ;; Traverse and display the contents of the left sub-tree of current node
    (display_in_order (cadr bst))

    ;; display current node
    (display (car bst))
    (newline) ;; new line for formatting

    ;; Traverse and display the contents of the right sub-tree of current node
    (display_in_order (caddr bst)) ;; AKA (cadr (cdr bst))) - access the right sub-tree which is the third element in the list
  ])
)

;; B. Return #t or #f if a given item is present or absent in a tree or not. The function
;; should take the item and a list representing a tree.
(define (tree_search item bst)
  (cond
    ;; If current tree is null, return false
    [(null? bst) #f]
  
    ;; If the item is found, return true
    [(equal? item (car bst)) #t]

    [else 
      (or 
        (tree_search item (cadr bst)) ;; search left sub-tree
        (tree_search item (caddr bst)) ;; search right sub-tree
        ;; Could have checked if item is less than or greater than the current node for more efficiency
        ;; but the improved efficiency is not worth the extra code / complexity         
      )
    ]
  )
)


;; C. Insert an item correctly into a list representing a binary search tree. Your function
;; should take an item and a tree as inputs.
(define (tree_insert item bst)
  (cond    
    ;; If the tree is empty, set current node to 5, return
    [(null? bst)
      (list item '() '())
    ]

    ;; If item is less than the current node, insert into left sub-tree
    [(< item (car bst))
      ;; create a new tree, keeping the same root node, not changing the right sub-tree, 
      ;; but a left sub-tree that has the item inserted
      (list (car bst) (tree_insert item (cadr bst)) (caddr bst))
    ]

    ;; If item is greater than the current node, insert into right sub-tree
    [(> item (car bst))
      ;; create a new tree, keeping the same root node, not changing the left sub-tree, 
      ;; but a right sub-tree that has the item inserted
      (list (car bst) (cadr bst) (tree_insert item (caddr bst)))
    ]
  
    ;; Else, the item is equal to the current node, return the tree (doing nothing)
    [else
      bst
    ]
  )  
)


;; D. Take a list of items and insert them into a binary search tree.
(define (tree_insert_list lst bst)
  (if (null? lst) ;; if the list is empty, return the tree as is
      bst 
    
      ;; otherwise, recurse through with the remainder of the list & binary tree created from the first element into bst
      (tree_insert_list (cdr lst) (tree_insert (car lst) bst))
  )
)

;; E. Implement a tree-sort algorithm. Your function should take a list of items and display them in sorted order.
(define (tree_sort lst)
  ;; insert the list into a binary search tree structure to sort it and then displaying the contents in order
  (display_in_order (tree_insert_list lst '()))
)

;; F. Implement a higher order version of the tree-sort function that takes a list and a
;; function that determines the sorted order. For example, write a version that sorts
;; the list in ascending, descending and ascending based on last digit.
(define (tree_sort_ho item bst compare-func)
  (cond 
    ;; if there are no elements in the list, create an empty tree
    [(null? bst)
      (list item '() '())
    ]  
  
    ;; if the item is to go before the current node, insett it to the left hand side of the bst
    [(compare-func item (car bst))
      ;; create new bst with same root node, same right-hand side, but a left hand side that has the item inserted
      (list (car bst) (tree_sort_ho item (cadr bst) compare-func) (caddr bst))
    ]

    ;; if the item is to go after the current node, insert it to the right hand side of the bst
    [(compare-func (car bst) item)
      ;; create new bst with same root node, same left hand side, but a right hand side that has the item inserted
      (list (car bst) (cadr bst) (tree_sort_ho item (caddr bst) compare-func))
    ]

    ;; otherwise, the item is equal to the current node, return the tree as is
    [else
      bst
    ]  
  )
)

;; Function to sort a list using a higher order function
(define (tree_sort_ho_list lst bst compare-func)
  (if (null? lst) ;; if the list is empty, return the tree as is
    bst
    
    ;; else, recurse through with the remainder of the list & binary tree created from the first element into bst
    (tree_sort_ho_list (cdr lst) (tree_sort_ho (car lst) bst compare-func) compare-func)
    
  )
)

;; sorts the list in ascending order - states if items were supplied in correct order
(define (sort-ascending item1 item2)
  (if (< item1 item2)
    #t
    #f
  )
)

;; sorts the list in descending order - states if items were supplied in correct order
(define (sort-descending item1 item2)
  (if (> item1 item2)
    #t
    #f
  )
)

;; sorts the list in ascending order based on the last digit - e.g. if item1 = item2, then return false
(define (sort-ascending-last-digit item1 item2)
  (if (< (modulo item1 10) (modulo item2 10))
    #t
    #f
  )
)



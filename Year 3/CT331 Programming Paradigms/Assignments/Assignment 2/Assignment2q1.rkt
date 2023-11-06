#lang racket

(display "\nAssigment 2\nQuestion 1\n\n")

;;A cons pair of two numbers
(cons 1 2)

;; A list of 3 numbers, using only the cons function.
(cons 2 (cons 4 (cons 7 empty)))

;;A list containing a string, a number and a nested list of three numbers, using only the
;;cons function.
(cons "This is a string!"
      (cons 5
            (cons (cons 1 (cons 2 (cons 3 empty))) empty)
      )
)


;;A list containing a string, a number and a nested list of three numbers, using only the
;;list function
(list "This is a string!" 5 (list 1 2 3))

;;A list containing a string, a number and a nested list of three numbers, using only the
;;append function.
(append '("This is a string!") '(5) '((1 2 3)))



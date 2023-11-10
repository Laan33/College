#lang racket

(display "\nAssigment 2\nQuestion 1\n")

;;A cons pair of two numbers
(display "\nA cons pair of two numbers.\n")
(cons 1 2)

;;A list of 3 numbers, using only the cons function.
(display "\nA list of 3 numbers, using only the cons function.\n")
(cons 2 (cons 4 (cons 7 empty)))

;;A list containing a string, a number and a nested list of three numbers, using only the
;;cons function.
(display "\n;A list containing a string, a number and a nested list of three numbers.\n")
(cons "This is a string!"
      (cons 5
            (cons (cons 1 (cons 2 (cons 3 empty))) empty)
      )
)


;;A list containing a string, a number and a nested list of three numbers, using only the
;;list function
(display "\nA list containing a string, a number and a nested list of three numbers - list \n")
(list "This is a string!" 5 (list 1 2 3))

;;A list containing a string, a number and a nested list of three numbers, using only the
;;append function.
(display "\nA list containing a string, a number and a nested list of three numbers - append. \n")
(append '("This is a string!") '(5) '((1 2 3)))



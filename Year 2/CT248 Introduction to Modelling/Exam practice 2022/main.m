A = [6 9 7; 3 1 9; 5 2 2];
B = diag([2 2 2]);

disp(A);
disp(B);

beans = A*B;
disp(beans);

beans10 = 10*A*B;
disp(beans10);


beansPlus = A + B;
disp(beansPlus);

beansUp = A .^ B;
disp(beansUp);





prs = create_sales(10,5,-1,20,100);
prs


prs_c = clean_products(prs);
prs_c


[sales_c, sales_p] = analyse_sales(prs_c);

sales_c
sales_p
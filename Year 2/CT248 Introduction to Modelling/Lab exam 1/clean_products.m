function [clean_Sales] = clean_products(prod_Sales)

    clean_Sales = prod_Sales;

    clean_Sales(clean_Sales < 0) = 0;

end


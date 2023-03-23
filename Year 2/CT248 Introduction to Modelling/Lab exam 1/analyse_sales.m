

function [sales_c, sales_p] = analyse_sales(prs_c)
       % Get the size of the sales data matrix
    [nCustomers, nProducts] = size(prs_c);
    
    % Initialize arrays to store the total sales for each customer and product
    sales_c = zeros(1, nCustomers);
    sales_p = zeros(1, nProducts);
    
    % Loop through each row (customer) of the sales data matrix
    for i = 1:nCustomers
        % Sum up the sales for the i-th customer and store in sales_c
        sales_c(i) = sum(prs_c(i,:));
    end
    
    % Loop through each column (product) of the sales data matrix
    for j = 1:nProducts
        % Sum up the sales for the j-th product and store in sales_p
        sales_p(j) = sum(prs_c(:,j));
    end
end

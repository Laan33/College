
function [sales] = create_sales(customers, products, lower, upper, seed)
    % Seeding the rng with given input
    rng(seed)

    % Returning a matrix of sales, in the defined ranges
    % dimension of customer x customer
    sales = randi([lower, upper], [customers, products]);
end



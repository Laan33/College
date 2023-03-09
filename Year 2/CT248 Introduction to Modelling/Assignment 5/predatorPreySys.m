function [] = predatorPreySys()
    % Define the ODE system
    global k;
    global population0;

    %pop - population of sharks (pop(1)) and tuna population (pop(2))
    odefun = @(t, pop) [k(1)*pop(1)*pop(2) - k(2)*pop(1); k(3)*pop(2) - k(4)*pop(1)*pop(2)];
    
    %dS/dt = (k1)(S)(T) - (k2)(S)
    %dT/dt = (k3)(T) - (k4)(S)(T)

    % Setting initial conditions and the time span
    pop0 = [population0; population0];
    tspan = [0 50];
    
    % Solving the ODE system
    [t, pop] = ode45(odefun, tspan, pop0);
    
    % Plotting results
    plot(t, pop(:, 1), 'b', t, pop(:, 2), 'r');
    xlabel('Time');
    ylabel('Population');
    legend('Sharks', 'Tuna');
end


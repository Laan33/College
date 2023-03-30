%P - population
%R - annual growth rate
%K - maximum capacity

%dP/dt = rP ( 1 - P/K)

% Anonymous groth function
growthPop = @(t,P,r,K) r.*P.*(1 - P./K);

% Set parameters for ode45()
tspan = [0 100]; % time interval
P0 = 750; % initial population

% Results array
results = cell(1,50);

% index loop 1 - 50
for i = 1:50
    %K is then linspace between 1x10^3 to 1x10^6, with every 50th index
    K = linspace(1000, 1000000, 50);
    tempK = K(i);
    
     %K = 1000*2^(i-1); - I tried to do some exponential - too finicky   
   
    r = 0.1; % growth rate
    [t,P] = ode45(@(t,P) growthPop(t,P,r,tempK), tspan, P0); %  ODE
    results{i} = P; % results in cell array

    
end

% Ploting the results
figure;
hold on;
for i = 1:50
    plot(t, results{i}, 'LineWidth', 1.5);
end
hold off;
xlim([0 100]);
ylim([0 1000000]);
xlabel('Time');
ylabel('Population');

%Can't get it to grow / plateau as fast as exam sample graph


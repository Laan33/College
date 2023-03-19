% Parameters
contacts = linspace(3, 8, 20); % 20 contact values between 3 and 8
infectivity = 0.125;
alpha = 0.25;
beta = 0.02;
gamma = 0.10;

% Time & init conditions
time_vec = 0:.25:100;
init_vec = [9999 1 0 0 0];

% Init output arrays
out_infected = zeros(length(time_vec), length(contacts));
out_in_hospital = zeros(length(time_vec), length(contacts));

% For each contact, we do 20 runs, averaging it
for i = 1:length(contacts)
    for j = 1:20
        % SIR model ran with current parameters
        [t, y] = SIR(time_vec, init_vec, contacts(i), infectivity, alpha, beta, gamma);
        
        % Update infected and hospitalised counts
        out_infected(:,i) = out_infected(:,i) + y(:,2);
        out_in_hospital(:,i) = out_in_hospital(:,i) + y(:,4);
    end
end

% Taking the average from our 20 runs
out_infected = out_infected./20; 
out_in_hospital = out_in_hospital./20;


% Plotting using subgraph
figure;
subplot(3,1,1);
plot(time_vec, out_infected);
xlabel('Time (days)');
ylabel('Infected count');
title('Infected counts over time, various differing daily contacts (averaged out)');

subplot(3,1,2);
plot(time_vec, out_in_hospital);
xlabel('Time (days)');
ylabel('Hospitalised count');
title('Number of people in hospital, various differing daily contacts (averaged out)');

subplot(3,1,3);
max_hospitalised = max(out_in_hospital);
plot(contacts, max_hospitalised, 'o-');
xlabel('Average daily contacts per person');
ylabel('Peak hospitalised count');
title('Peak number of people in hospital against average daily contacts');



function [t,y] = SIR(time_vec, init_vec, contacts, infectivity, alpha, beta, gamma)
 N = sum(init_vec); % sum of all the population
% Differential equations
SIR_eqs = @(t, y) [-contacts*y(1)*y(2) / N*infectivity; %1
                   contacts*y(1)*y(2) / N*infectivity - alpha*y(2); %2
                   alpha*y(2) - beta*y(3); %3
                   beta*y(3) - gamma*y(4); %4
                   gamma*y(4)];%5

% Solving using ode45
[t, y] = ode45(SIR_eqs, time_vec, init_vec);

end

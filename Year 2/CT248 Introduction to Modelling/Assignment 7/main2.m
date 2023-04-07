%PART 1

% Read in csv file
flights = readtable('Flights.csv');

% Counting num records
num_records = size(flights, 1);

% Output the number of records
fprintf('The number of records in the dataset is %d\n', num_records);



%PART 2

% Convert "origin" and "dest" to strings
flights.origin = string(flights.origin);
flights.dest = string(flights.dest);



%PART 3


% Create a new vector with departure delay values
dep_delay = flights.dep_delay;

% Compute summary statistics
summary_stats = table(min(dep_delay), median(dep_delay), max(dep_delay), sum(ismissing(dep_delay)), 'VariableNames', {'Min', 'Median', 'Max', 'NumMissing'});
disp(summary_stats);


%PART 4
% Filter out missing values from dep_delay
idx = ~isnan(flights.dep_delay);
flights_filtered = flights(idx, :);

% Check the number of records
fprintf("Flights filtered size: %d\n", size(flights_filtered));


%PART 5
fprintf('Difference in flights vs flights filtered: %d\n', (height(flights) - height(flights_filtered)));



%PART 6

flights_final = flights(flights.dep_delay <= 120, :);
fprintf('Flights final size: %d\n', (height(flights_final)));


%PART 7

% Group the data by month and calculate the average departure delay
res1 = grpstats(flights_final, 'Month', {'mean'}, 'DataVars', 'dep_delay');

% Plot the average delay per month
subplot(2,2,1);
plot(res1.Month, res1.mean_dep_delay, 'bo-');
xlabel('Month');
ylabel('Average Delay (minutes)');
title('Average Delay per Month');
grid on; % Add a grid to the plot
axis tight; % Zoom to show only the data


%PART 8

% Group the data by hour and calculate the average departure delay
res2 = grpstats(flights_final, 'hour', {'mean'}, 'DataVars', 'dep_delay');

% Plot the average delay per hour
subplot(2,2,2);
plot(res2.hour, res2.mean_dep_delay, 'bo-');
xlim([4.5 23.5]);
xlabel('Hour');
ylabel('Average Delay (minutes)');
title('Average Delay per Hour');
grid on;


%PART 9


% Compute average delay by month and origin
avrDelayMonthOrigin = grpstats(flights_final,{'Month','origin'},'mean','DataVars','dep_delay');

% Extract necessary columns and rename them
res3 = avrDelayMonthOrigin(:,{'Month','origin','GroupCount','mean_dep_delay'});
res3.Properties.VariableNames = {'Month','origin','Count','AvrDelayMonthOrigin'};

% Set the color palette
colors = ["r", "g", "b"];

locations = ["JFK" "LGA" "EWR"];
% Create a figure with subplots
figure;
for i = 1:3
    % Filter the data by origin
    data = res3(res3.origin == locations(i),:);
     
    % Create the line graph on a new subplot
    subplot(2,2,i+2);
    plot(data.Month, data.AvrDelayMonthOrigin, '-o', 'Color', colors(i), 'LineWidth', 2, 'MarkerFaceColor', colors(i), 'MarkerSize', 6);
    
    % Set the title and axis labels
    title(sprintf('Average Delay by Month - %s', locations{i}));
    xlabel('Month');
    ylabel('Average Delay (minutes)');
    
    % Set the axis limits
    xlim([1 12]);
    ylim([0 15]);
    
    % Set the tick marks
    xticks(1:12);
    yticks(0:5:15);
    
    % Add a legend
    legend(locations{i}, 'Location', 'northeast');
end

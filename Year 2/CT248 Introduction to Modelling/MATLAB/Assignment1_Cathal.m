clear;

d = roll_2_dice(10000, 100);

[freq, prop] = tabulate_2_dice(d);
disp(freq);
disp(prop);


function [outputVector] = roll_2_dice(amountOfTrials, seed) 
    %sets the seed inputted
    rng(seed)
    %creates an array of zeros for the amount of trials for faster code
    outputVector = zeros(1,amountOfTrials);

    %for loop where there is two dice 'randomly thrown and summed to the
    %vector - outputVector
    for c = 1 : amountOfTrials
        dice1 = randi(6);
        dice2 = randi(6);

        outputVector(c) = dice1 + dice2;
    end
end %return 


function [freq, prop] = tabulate_2_dice(trialedVector)
    %vector of all trials taken as input

    %iterate up by one, in the vector freq, the value between 1-13 for all
    %of trials in vector
    freq=histcounts(trialedVector(:),1:13);

    %setting vector prop to vector freq divided by the total sum
    prop = freq() / sum(freq);
end % return

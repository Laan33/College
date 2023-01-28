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

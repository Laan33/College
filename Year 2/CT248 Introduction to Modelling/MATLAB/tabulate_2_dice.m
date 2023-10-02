function [freq, prop] = tabulate_2_dice(trialedVector)
    %vector of all trials taken as input

    %iterate up by one, in the vector freq, the value between 1-13 for all
    %of trials in vector
    freq=histcounts(trialedVector(:),1:13);

    %setting vector prop to vector freq divided by the total sum
    prop = freq() / sum(freq);
end % return


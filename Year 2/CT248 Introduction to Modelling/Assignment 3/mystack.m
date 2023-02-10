function [push, pop, peek] = mystack()
    push = @mystack_push;
    pop = @mystack_pop;
    peek = @mystack_peek;
end

function outputstack = mystack_push(instack, value)
    outputstack = [value, instack];
end

function outputstack = mystack_pop(instack)
    if length(instack) < 2
        outputstack = [];
    else
        outputstack = instack(2:length(instack));
    end
end

function value = mystack_peek(instack)
    if isempty(instack)
        value = "Stack empty";
    else
        value = instack(1);
    end
end

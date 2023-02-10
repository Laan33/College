function [transformed] = transform_pic(pic)

transformed = 255 - pic;
end


%converts a 255 colour code to 0, 254 to 1, etc,
% and 0 to 255. 
% The relationship can be expressed as the equation of a line.

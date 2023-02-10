function [binary_img] = transform_threshold(pic,threshold)

binary_img = pic > threshold;
end

%converts the picture to binary format where any value above
% the threshold is white (1), and all values equal to or below
% are black(0).
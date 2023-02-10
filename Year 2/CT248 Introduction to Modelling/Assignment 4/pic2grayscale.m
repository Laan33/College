function [pic_gray] = pic2grayscale(pic)
%PIC2GRAYSCALE Summary of this function goes here
%   Detailed explanation goes here
pic_gray = 0.2989 * pic(:,:,1) + 0.5870 * pic(:,:,2) + 0.1140 * pic(:,:,3);

end

%uses the NTSC Standard transformation
% to convert RGB to grayscale.
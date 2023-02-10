clear;

eng1 = imread("Engineering-Building.jpg");
subplot(2,3,1); imshow(eng1); axis image;title('Image');


eng1_gs = pic2grayscale(eng1);
subplot(2,3,2); imshow(eng1_gs);title('Grey Scale');

eng1_gs_inv = transform_pic(eng1_gs);
subplot(2,3,3); imshow(eng1_gs_inv);title('Transform 254');

eng1_gs_bin_50 = transform_threshold(eng1_gs,50);
subplot(2,3,4); imshow(eng1_gs_bin_50);title('Threshold 50%');

eng1_gs_bin_75 = transform_threshold(eng1_gs,75);
subplot(2,3,5); imshow(eng1_gs_bin_75);title('Threshold 75%');

eng1_gs_bin_100 = transform_threshold(eng1_gs,100);
subplot(2,3,6); imshow(eng1_gs_bin_100);title('Threshold 100%');

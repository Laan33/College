%Shark - S(t) Product ST - average probability it makes a meal of the tuna at rate
%k1ST - average prob of encounter k1 is average prob per unit time the
%encounter results in a meal
% represents the rate shark population grows 

%disappearance of sharks is k2S

%tuna growth (birth - death) is proportional to net population
%k3S - they disappear in proportion to the product of encounters with
%sharks at k4ST, not necessarily equal to growth rate of sharks

global k;
global population0;
k = [0.015; 0.7; 0.5; 0.01];
population0 = 100;

predatorPreySys();


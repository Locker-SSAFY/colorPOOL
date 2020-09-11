% convert RGB to CIE1976L*a*b* and save as csv file
dataTable = table(data1(:,:,1),data1(:,:,2),data1(:,:,3));
dataArray = table2array(dataTable);
n = length(dataArray);
m = 15;
r1 = double.empty;
r2 = double.empty;
r3 = double.empty;
r4 = double.empty;
r5 = double.empty;
g1 = double.empty;
g2 = double.empty;
g3 = double.empty;
g4 = double.empty;
g5 = double.empty;
b1 = double.empty;
b2 = double.empty;
b3 = double.empty;
b4 = double.empty;
b5 = double.empty;


for i=1:n
    r1(i) = dataArray(i,1);
    r2(i) = dataArray(i,2);
    r3(i) = dataArray(i,3);
    r4(i) = dataArray(i,4);
    r5(i) = dataArray(i,5);
    
    g1(i) = dataArray(i,6);
    g2(i) = dataArray(i,7);
    g3(i) = dataArray(i,8);
    g4(i) = dataArray(i,9);
    g5(i) = dataArray(i,10);
    
    b1(i) = dataArray(i,11);
    b2(i) = dataArray(i,12);
    b3(i) = dataArray(i,13);
    b4(i) = dataArray(i,14);
    b5(i) = dataArray(i,15);
end

ll1 = double.empty;
ll2 = double.empty;
ll3 = double.empty;
ll4 = double.empty;
ll5 = double.empty;
aa1 = double.empty;
aa2 = double.empty;
aa3 = double.empty;
aa4 = double.empty;
aa5 = double.empty;
bb1 = double.empty;
bb2 = double.empty;
bb3 = double.empty;
bb4 = double.empty;
bb5 = double.empty;

for i=1:n
    lab1=rgb2lab([r1(i),g1(i),b1(i)]);
    lab2=rgb2lab([r2(i),g2(i),b2(i)]);
    lab3=rgb2lab([r3(i),g3(i),b3(i)]);
    lab4=rgb2lab([r4(i),g4(i),b4(i)]);
    lab5=rgb2lab([r5(i),g5(i),b5(i)]);
    
    ll1 = [ll1;lab1(1)];
    ll2 = [ll2;lab2(1)];
    ll3 = [ll3;lab3(1)];
    ll4 = [ll4;lab4(1)];
    ll5 = [ll5;lab5(1)];
    
    
    aa1 = [aa1;lab1(2)];
    aa2 = [aa2;lab2(2)];
    aa3 = [aa3;lab3(2)];
    aa4 = [aa4;lab4(2)];
    aa5 = [aa5;lab5(2)];

    bb1 = [bb1;lab1(3)];
    bb2 = [bb2;lab2(3)];
    bb3 = [bb3;lab3(3)];
    bb4 = [bb4;lab4(3)];
    bb5 = [bb5;lab5(3)];
end

dataCIEtable = table(ids1,names1,targets1,ll1,ll2,ll3,ll4,ll5,aa1,aa2,aa3,aa4,aa5,bb1,bb2,bb3,bb4,bb5);
dataCIEtable.Properties.VariableNames = {'Id' 'Name' 'Target' 'L1' 'L2' 'L3' 'L4' 'L5' 'A1' 'A2' 'A3' 'A4' 'A5' 'B1' 'B2' 'B3' 'B4' 'B5'};
writetable(dataCIEtable,'kulerCIEdata.csv');
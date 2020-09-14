dataCIE = load('kulerCIEdata.mat');
dataCIEtable = dataCIE.dataCIEtable;

n = length(dataCIEtable.Id);

id = dataCIEtable.Id;
name = dataCIEtable.Name;
target = dataCIEtable.Target;


L1 = dataCIEtable.L1;
L2 = dataCIEtable.L2;
L3 = dataCIEtable.L3;
L4 = dataCIEtable.L4;
L5 = dataCIEtable.L5;

A1 = dataCIEtable.A1;
A2 = dataCIEtable.A2;
A3 = dataCIEtable.A3;
A4 = dataCIEtable.A4;
A5 = dataCIEtable.A5;

B1 = dataCIEtable.B1;
B2 = dataCIEtable.B2;
B3 = dataCIEtable.B3;
B4 = dataCIEtable.B4;
B5 = dataCIEtable.B5;

qL1 = int8.empty;
qL2 = int8.empty;
qL3 = int8.empty;
qL4 = int8.empty;
qL5 = int8.empty;

qA1 = int8.empty;
qA2 = int8.empty;
qA3 = int8.empty;
qA4 = int8.empty;
qA5 = int8.empty;

qB1 = int8.empty;
qB2 = int8.empty;
qB3 = int8.empty;
qB4 = int8.empty;
qB5 = int8.empty;

%quantize
for i=1:n
    qL1 = [qL1;Quantizer(L1(i))];
    qL2 = [qL2;Quantizer(L2(i))];
    qL3 = [qL3;Quantizer(L3(i))];
    qL4 = [qL4;Quantizer(L4(i))];
    qL5 = [qL5;Quantizer(L5(i))];
    
    qA1 = [qA1;Quantizer(A1(i))];
    qA2 = [qA2;Quantizer(A2(i))];
    qA3 = [qA3;Quantizer(A3(i))];
    qA4 = [qA4;Quantizer(A4(i))];
    qA5 = [qA5;Quantizer(A5(i))];
    
    qB1 = [qB1;Quantizer(B1(i))];
    qB2 = [qB2;Quantizer(B2(i))];
    qB3 = [qB3;Quantizer(B3(i))];
    qB4 = [qB4;Quantizer(B4(i))];
    qB5 = [qB5;Quantizer(B5(i))];
end

qdataCIEtable = table(id,name,target,qL1,qL2,qL3,qL4,qL5,qA1,qA2,qA3,qA4,qA5,qB1,qB2,qB3,qB4,qB5);
qdataCIEtable.Properties.VariableNames = {'Id' 'Name' 'Target' 'L1' 'L2' 'L3' 'L4' 'L5' 'A1' 'A2' 'A3' 'A4' 'A5' 'B1' 'B2' 'B3' 'B4' 'B5'};
writetable(qdataCIEtable,'kulerQuantCIEdata.csv');

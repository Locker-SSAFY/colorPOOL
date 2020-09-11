%filtering all fields of kulerData.mat where target<4 
kulerData = load('kulerData.mat');
targets = kulerData.targets;
names = kulerData.names;
ids = kulerData.ids;
data = kulerData.data;

n = length(targets);

for i = 1:n
    if targets(i) < 4
        targets(i) = 0;
    end
end

a = find(targets);

targets1 = targets(a);
names1 = names(a);
ids1 = ids(a);
data1 = data(a,:,:);

% convert mat file to csv
KulerDataTable = table(ids1,names1,targets1,data1(:,:,1),data1(:,:,2),data1(:,:,3));
KulerDataTable.Properties.VariableNames = {'Id' 'Name' 'Target' 'R' 'G' 'B' };
writetable(KulerDataTable,'kulerData.csv');
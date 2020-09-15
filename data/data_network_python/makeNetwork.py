import pandas as pd
import scipy.io as si
import numpy as np
import matplotlib.cm as cm
import matplotlib.pyplot as plt
import networkx as nx #네트워크 구성을 위해 필요
import community as community_louvain #커뮤니티(클러스터) 구성을 위해 필요


# 전처리(양자화)된 csv파일 로드
mat = si.loadmat("../preprocessing_matlab/QuantkulerCIEdata.mat")
name = 'qdataCIEtable2'

matdata = mat[name]
matdata = matdata[0][0]
row = matdata[0].__len__()

#graph 선언(from networkx)
graph = nx.Graph()

# 각 테마(행)에서 컬러 추출 (L1, a1, b1), ... ,(L5, a5, b5)
for i in range(0, row):
    themeColors = [0 for a in range(5)];
    for j in range(0, 5):
        L = int(matdata[j+3][i][0])
        A = int(matdata[j+8][i][0])햣 ㄴㅅㅁ
        B = int(matdata[j+13][i][0])
        themeColors[j] = (L, A, B)
        graph.add_node(themeColors[j]) # node 추가
    for j in range(0, 5):
        for k in range(j+1,5):
            graph.add_edge(themeColors[j],themeColors[k]) # edge 추가

#louvain algorithm 적용 -> community(cluster) 적용
partition = community_louvain.best_partition(graph);
print(max(partition.values())) # 이슈: community의 개수가 매번 다르다...




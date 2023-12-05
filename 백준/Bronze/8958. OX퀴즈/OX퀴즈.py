import sys

input_ = sys.stdin.readline

N = int(input())

OX = []

for i in range (N):
    OX.append(input_().strip())

for i in range (N):
    sum = 0; val = 1;
    str_ox = list(OX[i])
    for j in range(len(str_ox)):
        if(str_ox[j] == 'X'):
            val = 1
        else:
            sum += val
            val += 1
    print(sum)
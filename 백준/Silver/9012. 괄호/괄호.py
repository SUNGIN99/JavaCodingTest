def VPS(s1):
    ps = [];
    j=0;
    for i in range(0, len(s1), 1):
        
        if( (i != 0) and (j != 0) and (s1[i] == ')') and (ps[j-1] == '(') ):
            ps.pop()
            j = j - 1
        else:
            ps.append(s1[i])
            j = j + 1
        #print(i, j, ps)

    if(len(ps) == 0):
        return 'YES';
    else :
        return 'NO';

s = [];

T = int(input())

#입력부
for i in range(0, T, 1):
    s.append(input())


#VPS 판단
for i in range(0, T, 1):
    print(VPS(s[i]));

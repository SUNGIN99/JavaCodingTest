N, C = map(int, input().split())

square = N * N
A = B = N
for i in range(C):
    X, Y = map(int, input().split())

    if( X >= A or Y >= B):
        continue

    else:
        square_row = X * B
        square = square_high = A * Y

        if(square_row >= square_high):
            square = square_row
            A = X
        else:
            B = Y

        #print(i+1, ') ', square)
print(square)
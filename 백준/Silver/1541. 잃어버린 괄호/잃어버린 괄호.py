from collections import deque
from typing import List

def returnSum(function : List[str]):
    sumV = 0
    for x in function:
        nums = list(map(int, x.split('+')))
        sumV += sum(nums)

    return sumV;

waits = {'+' : [],
         '-' : []}

operate = input()

values = operate.split('-')

for op in values:
    key_index = operate.find(op)
    if(key_index == 0):
        waits['+'].append(op)
    else:
        if (operate[key_index - 1] == '+'):
            waits['+'].append(op)
        elif (operate[key_index - 1] == '-'):
            waits['-'].append(op)

    operate = operate.replace(op, 'X', 1)

result = 0
for op, function in waits.items():
    temp = returnSum(function)
    if op == '+':
        result += temp
    elif op == '-':
        result -= temp
 
print(result)
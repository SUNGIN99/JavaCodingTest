boom = input()
target = input()
stack = []

def boomSTR(boom, target):
    check_len = len(target)
    for char in boom:
        stack.append(char)
        #print(stack[-check_len:])
        if len(stack) >= check_len and ''.join(stack[-check_len:]) == target:
            for _ in range(check_len):
                stack.pop()

    if len(stack) == 0:
        return "FRULA"

    return ''.join(stack)

result = boomSTR(boom, target)
print(result)
def fibonacci(n):
    if n==1:
        return (0,1)
    else:
        before = fibonacci(n-1)
        return (before[1], before[0]+before[1])

num = input('Input the number: ')

answer = fibonacci(num)[0]

print (num, end=" ")
if num == 1:
    print ('st', end=" ")
elif num == 2:
    print ('nd', end=" ")
else:
    print ('th', end=" ")

print (' fibonacci number is: ', end=" ")
print (answer)


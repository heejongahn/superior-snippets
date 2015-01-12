def fibonacci(n):
    if n==1:
        return (0,1)
    else:
        before = fibonacci(n-1)
        return (before[1], before[0]+before[1])

num = input('Input the number: ')

answer = fibonacci(num)[0]

print num,
if num == 1:
    print 'st',
elif num == 2:
    print 'nd',
else:
    print 'th',

print ' fibonacci number is: ',
print answer


# Checking in which order the argument of functions are evaluated.

def first(a):
    print("First!")
    return 1

def second(a):
    print("Second")
    return 2

def third(a):
    print ("Third")
    return 3

def orders(a, b, c):
    print (a+b+c)

orders(first(1), second(1), third(1))

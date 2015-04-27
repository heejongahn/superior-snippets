# Difference between shallow copy and deep copy
import copy

# 0. Assignment

l_1 = [1, 2, [3, 4]]
l_2 = l_1

print (id(l_1) == id(l_2)) # True
print (id(l_1[2]) == id(l_2[2])) # Also True

# 1. Shallow copy

a = [1, 2, [3, 4]]
b = a[:]

print (id(a) == id(b)) # False
print (id(a[2]) == id(b[2])) # True


# 2. Deep copy

x = [1, 2, [3, 4]]
y = copy.deepcopy(x)

print (id(x) == id(y)) # False
print (id(x[2]) == id(y[2])) # False

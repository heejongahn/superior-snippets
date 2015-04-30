l = [1, 4, 9, 16, 25]

for i in l:
    print (i)
else:
    print ("This will be printed after the iteration.")

for i in l:
    print (i)
    if i>10:
        break
else:
    print ("This will not be printed.")



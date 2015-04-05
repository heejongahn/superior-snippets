def hanoi(n, source, spare, dest):
    if n==1:
        print ("Moves from %d to %d" % (source, dest))

    else:
        hanoi(n-1, source, dest, spare)
        hanoi(1, source, spare, dest)
        hanoi(n-1, spare, source, dest)

def hanoi_2nd(n, source, spare, dest, l):
    if n==1:
        l[source] = l[source] - 1
        l[dest] = l[dest] + 1
        print (l)
        return l

    else:
        l = hanoi_2nd(n-1, source, dest, spare, l)
        l = hanoi_2nd(1, source, spare, dest, l)
        l = hanoi_2nd(n-1, spare, source, dest, l)
        return l

n = int(input("How many plates? "))

print ("First Implementation...")
hanoi(n, 1, 2, 3)
print ("- - - - - - - - - - - - - - - -")

print ("Second Implementation...")
l = [n, 0, 0]
hanoi_2nd(n, 0, 1, 2, l)

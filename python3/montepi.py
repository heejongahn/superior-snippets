import random

def montepi(n):
    inside = 0
    for i in range(n):
        x, y = random.random(), random.random()

        if pow(x, 2) + pow(y, 2) <= 1:
            inside += 1

    return 4 * inside / n

while (1):
    n = input("How many times do you want to iterate? Insert 0 to quit : ")
    if n == "0":
        break
    try:
        print (montepi(int(n)))
    except:
        continue

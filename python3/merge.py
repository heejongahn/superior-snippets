def mergeSort(l):
    length = len(l)
    if length==1:
        return l
    if length==2:
        return [int(min(l)), int(max(l))]

    left_l = l[:length//2]
    right_l = l[length//2:]

    left_l = mergeSort(left_l)
    right_l = mergeSort(right_l)

    return merge(left_l, right_l)

def merge(left_l, right_l):
    newlist = []
    while (len(left_l) > 0 and len(right_l) > 0):
        if (left_l[0] < right_l[0]):
            newlist.append(left_l.pop(0))
        else:
            newlist.append(right_l.pop(0))

    if (len(left_l) == 0):
        return newlist + right_l
    else:
        return newlist + left_l

inputList = input().split(" ")
for i in range(len(inputList)):
    inputList[i] = int(inputList[i])

print(mergeSort(inputList))

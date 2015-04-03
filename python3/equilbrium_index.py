def ei(arr):
    len_arr = len(arr)

    answers = []
    sum = 0
    sumarr = [0] * len_arr
    for i in range(len_arr):
        sum += arr[i]
        sumarr[i] = sum

    for i in range(1, len_arr):
        if (sumarr[i] - arr[i]) * 2 == sum - arr[i] :
            answers.append(i)

    return answers


arr = [-7, 1, 5, 2, -4, 3, 0]
print (ei(arr))

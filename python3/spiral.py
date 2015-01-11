# << Spiral >>
# Takes two integers(row, col) from a user.
# Then print out a matrix spiral matrix.
# Used a simple recursive call to solve a problem.

# Example input)
# row = 2, col = 2

# Example result)
# 0 1
# 3 2


# Take an input using python standard I/O
row_and_col = raw_input("Insert row and column number... Separated by spacebar (' ') ")
row_and_col_list = row_and_col.split(' ')

row = int(row_and_col_list[0])
col = int(row_and_col_list[1])


# Manage a answer matrix as a nested list
ans = []
for i in range(row):
    row_list = [0] * col
    ans.append(row_list)

def fringe(start, count, row, col):
    start_row = count
    start_col = count

    if (row > 0):
        # Upper side
        for i in range(col):
            ans[start_row][start_col+i] = start + i

        if (row > 1):
            # Bottom side
            for i in range(col):
                ans[start_row + (row-1)][start_col+i] = start + (col-1) + (row-1) + (col-i-1)


    if (col > 0):
        # Right side
        for i in range(row):
            ans[start_row + i][start_col + (col-1)] = start + (col-1) + i

        if (col > 1):
            # Left side
            for i in range(row):
                ans[start_row + i][start_col] = start + (col-1) + (row-1) + (col-1) + (row-1-i)

    # Start Point
    if not (col == 0 and row == 0):
        ans[start_row][start_col] = start

    # Recursive call to inner square
    newStart = start + 2 * (col + row) - 4
    if row > 1 and col > 1:
        fringe(newStart, count + 1, row - 2 , col - 2)


# First call to fringe - the most outer rim
fringe (0, 0, row, col)

# Print out the answer matrix
for ans_row in ans:
    for ans_element in ans_row:
        print ans_element,
    print ''

import glob

filename_list = glob.glob("gt_*.txt")

for filename in filename_list:
    with open(filename, 'r') as f:
        with open(filename[3:], 'w') as newfile:
            l = []
            lines = f.readlines()
            prev = ((lines[0].split(", "))[1])

            for line in lines:
                _, h, _, _, word = line.strip('\n').split(", ")
                word = word.strip('"')
                if int(h)-int(prev) <= 10:
                    l.append(word)
                else:
                    newfile.write(" ".join(l))
                    newfile.write('\n')
                    l = [word]
                prev = h

            newfile.write(" ".join(l))

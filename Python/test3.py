def initialCoordinates(n, m):
    #print(x)
    #print(y)
    x = n+2
    y = m+2
    outer_list = []
    for i in range(x):
        inner_list = []
        for j in range(y):
            tuple = (i,j)
            inner_list.append(tuple)
            #print(tuple)
        #print(inner_list)
        outer_list.append(inner_list)
    #print(outer_list)
    return outer_list

def initialCoordinates2(n, m, h, v):
    #print(x)
    #print(y)
    x = n+2
    y = m+2
    outer_list = []
    for i in range(x):
        inner_list = []
        for j in range(y):
            tuple = (i,j)
            if i not in h and j not in v:
                inner_list.append(tuple)
            #print(tuple)
        if inner_list:
            #print(inner_list)
            outer_list.append(inner_list)
    #print(outer_list)
    return outer_list

def removeHorizontalCoordinates(m, h_list):
    y = m+2
    outer_list = []
    for i in range(len(h_list)):
        inner_list = []
        for j in range(y):
            tuple = (h_list[i],j)
            inner_list.append(tuple)
        outer_list.append(inner_list)
    return outer_list

def removeVerticalCoordinates(n, v_list):
    x = n+2
    outer_list = []
    for i in range(len(v_list)):
        inner_list = []
        for j in range(x):
            tuple = (j,v_list[i])
            inner_list.append(tuple)
        outer_list.append(inner_list)
    return outer_list

def convertCoordinatesToColumns(coords_rows):
    outer_list = []
    num_new_rows = len(coords_rows[0])
    num_new_columns = len(coords_rows)
    #print(num_new_rows)
    #print(num_new_columns)
    #print(num_new_columns)
    for i in range(num_new_rows):
        inner_list = []
        for j in range(num_new_columns):
            #print(coords_rows[j][i])
            inner_list.append(coords_rows[j][i])
        outer_list.append(inner_list)
    return outer_list

def calculateLongestX(coords_rows):    
    outer_list = []
    for i in range(len(coords_rows)):
        longest_length=0
        inner_list=[]
        diff=0
        for j in range(len(coords_rows[i])-1):
            diff=coords_rows[i][j+1][1]-coords_rows[i][j][1]
            if diff>longest_length:
                longest_length=diff
                inner_list=[coords_rows[i][j],coords_rows[i][j+1]]
        #print(inner_list)
        outer_list.append(inner_list)
    return outer_list

def calculateLongestY(coords_columns):
    outer_list = []
    for i in range(len(coords_columns)):
        longest_length=0
        inner_list=[]
        diff=0
        for j in range(len(coords_columns[i])-1):
            diff=coords_columns[i][j+1][0]-coords_columns[i][j][0]
            if diff>longest_length:
                longest_length=diff
                inner_list=[coords_columns[i][j],coords_columns[i][j+1]]
        #print(inner_list)
        outer_list.append(inner_list)
    return outer_list

def findArea(coords_rows,coords_columns):
    outer_list = []
    for i in range(len(coords_rows)):
        #print(coords_rows[i][0])
        for j in range(len(coords_columns)):
            #print(coords_columns[j][0])
            if coords_rows[i][0]==coords_columns[j][0]:
                #print(coords_rows[i])
                #print(coords_columns[j])
                outer_list.append(coords_rows[i])
                outer_list.append(coords_columns[j])
    #print(outer_list)
    
    length=outer_list[0][1][1]-outer_list[0][0][1]
    breadth=outer_list[1][1][0]-outer_list[1][0][0]
    area=length*breadth
    return area


def sewers(n, m, h, v):
    # Write your code here
    coords_rows=[]
    #coords_rows=initialCoordinates(n, m)
    coords_rows=initialCoordinates2(n, m, h, v)
    #removeHorizontalCoords=removeHorizontalCoordinates(m, h)
    #removeVerticalCoords=removeVerticalCoordinates(n, v)
    
    #to remove horizontal rows
    #for i in range(len(removeHorizontalCoords)):
    #    coords_rows.remove(removeHorizontalCoords[i])
    #print(coords_rows)
    #print(removeVerticalCoords)
        
    #to remove vertical columns
    #for i in range(len(removeVerticalCoords)):
        #print(removeVerticalCoords[i])
    #    for j in range(len(removeVerticalCoords[i])):
            #print(removeVerticalCoords[i][j])
    #        for k in range(len(coords_rows)):
    #            try:
    #                coords_rows[k].remove(removeVerticalCoords[i][j])
    #            except ValueError:
    #                pass  # do nothing!
                #print(coords_rows[k])
    #print(coords_rows)
    
    #convert coordinates to columns
    coords_columns=convertCoordinatesToColumns(coords_rows)
    #print(coords_columns)
    
    #calculate longest x
    coords_rows=calculateLongestX(coords_rows)
    #print(coords_rows)
    
    #calculate longest y
    coords_columns=calculateLongestY(coords_columns)
    #print(coords_columns)
    
    #to find the largest area
    area=findArea(coords_rows,coords_columns)
    #print(area)
    return area
    
def testcase1():
    n=2
    m=2
    h_size=1
    h = [1]
    v_size=1
    v = [2]
    return [n, m, h, v]
    
def testcase2():
    n=3
    m=2
    h_size=1
    h = [2]
    v_size=1
    v = [2]
    return [n, m, h, v]

def testcase3():
    n=3
    m=2
    h_size=3
    h = [1,2,3]
    v_size=2
    v = [1,2]
    return [n, m, h, v]

def testcase4():
    n=4
    m=4
    h_size=1
    h = [4]
    v_size=2
    v = [3,4]
    return [n, m, h, v]

def testcase5():
    n=1000
    m=1000
    #h_size=1
    h = [522, 575, 426, 445, 772, 81, 447, 629, 497, 202, 775, 325, 982, 784, 417, 156, 932, 902, 728, 537, 857, 739, 918, 211, 679, 506, 340, 568, 868, 16, 940, 263, 593, 449, 991, 310, 355, 68, 431, 580, 757, 218, 934, 328, 676, 221, 80, 922, 545, 511, 67, 467, 674, 691, 504, 835, 34, 965, 980, 895, 501, 152, 731, 302, 153, 896, 22, 489, 399, 607, 466, 432, 502, 968, 333, 475, 792, 84, 10, 694, 354, 712, 409, 480, 643, 121, 951, 492, 420, 197, 925, 167, 717, 438, 200, 986, 104, 483, 620, 806, 881, 858, 559, 553, 554, 962, 435, 279, 464, 327, 549, 832, 595, 720, 658, 639, 992, 130, 989, 958, 581, 600, 473, 929, 770, 455, 718, 628, 807, 335, 898, 552, 530, 811, 569, 148, 384, 954, 913, 114, 315, 686, 334, 382, 392, 326, 8, 957, 850, 231, 61, 185, 588, 305, 564, 890, 52, 747, 943, 740, 469, 370, 516, 443, 7, 525, 299, 412, 163, 699, 571, 976, 217, 590, 343, 427, 220, 342, 584, 239, 496, 875, 601, 258, 377, 40, 428, 238, 517, 653, 433, 768, 307, 456, 878, 977, 368, 999, 882, 541, 826, 764, 269, 401, 98, 698, 763, 515, 413, 495, 523, 206, 357, 904, 410, 844, 611, 484, 262, 394, 949, 107, 546, 737, 987, 979, 306, 685, 291, 542, 134, 94, 751, 89, 729, 212, 964, 297, 823, 280, 917, 338, 176, 183, 555, 3, 316, 256, 13, 974, 931, 24, 609, 304, 151, 199, 876, 825, 893, 939, 374, 323, 846, 819, 154, 157, 814, 886, 100, 856, 709, 879, 479, 461, 14, 123, 744, 400, 20, 963, 829, 984, 930, 322, 665, 646, 385, 191, 353, 605, 110, 453, 356, 243, 854, 75, 831, 837, 695, 845, 673, 865, 524, 952, 329, 331, 692, 27, 63, 358, 266, 801, 95, 51, 478, 809, 308, 499, 650, 706, 822, 15, 381, 267, 282, 237, 119, 111, 926, 444, 788, 944, 702, 888, 655, 521, 946, 131, 616, 654, 434, 683, 226, 741, 486, 101, 661, 532, 961, 903, 276, 758, 766, 259, 224, 836, 54, 18, 395, 56, 378, 711, 83, 184, 790, 945, 345, 268, 785, 675, 312, 321, 920, 696, 149, 948, 535, 591, 284, 82, 841, 471, 103, 366, 732, 776, 488, 64, 736, 773, 70, 122, 274, 632, 560, 748, 880, 889, 320, 733]
    #v_size=2
    v = [345, 90, 640, 212, 717, 135, 211, 37, 483, 144, 668, 778, 256, 127, 398, 808, 328, 758, 566, 591, 371, 76, 805, 964, 279, 370, 309, 441, 329, 125, 429, 661, 241, 166, 870, 394, 314, 513, 901, 687, 748, 614, 965, 222, 122, 234, 311, 997, 193, 838, 172, 6, 803, 711, 547, 136, 442, 343, 431, 420, 923, 843, 239, 903, 622, 535, 777, 275, 321, 177, 404, 261, 972, 93, 747, 562, 237, 698, 271, 836, 504, 101, 743, 433, 897, 277, 110, 645, 487, 324, 246, 889, 524, 243, 258, 961, 389, 581, 789, 619, 688, 477, 899, 25, 913, 134, 920, 179, 65, 697, 727, 51, 706, 763, 500, 471, 68, 652, 266, 850, 52, 1000, 319, 411, 456, 626, 115, 764, 380, 517, 757, 679, 801, 526, 990, 967, 604, 475, 416, 249, 406, 199, 557, 954, 355, 991, 539, 505, 637, 268, 254, 66, 736, 428, 107, 932, 332, 786, 766, 868, 159, 418, 768, 968, 710, 905, 269, 460, 802, 638, 516, 806, 2, 423, 326, 906, 419, 721, 875, 615, 755, 674, 458, 111, 149, 788, 378, 485, 930, 577, 583, 228, 657, 671, 685, 151, 195, 338, 546, 359, 986, 147, 119, 927, 528, 310, 323, 294, 305, 798, 399, 654, 649, 20, 714, 88, 735, 607, 464, 417, 565, 781, 216, 885, 131, 437, 337, 376, 454, 143, 580, 618, 49, 322, 413, 56, 16, 636, 114, 828, 741, 767, 164, 966, 669, 270, 646, 542, 368, 925, 235, 274, 350, 361, 473, 106, 782, 952, 142, 35, 59, 709, 995, 847, 974, 381, 226, 224, 809, 943, 527, 610, 495, 298, 655, 567, 790, 908, 300, 633, 650, 312, 582, 853, 97, 881, 620, 427, 240, 70, 50, 178, 133, 739, 533, 388, 299, 558, 720, 861, 922, 601, 377, 882, 46, 104, 9, 696, 327, 585, 555, 859, 963, 139, 970, 860, 834, 911, 876, 145, 677, 214, 545, 609, 879, 937, 940, 553, 531, 817, 994, 865, 590, 886, 593, 493, 221, 132, 60, 623, 982, 181, 64, 365, 894, 175, 956, 816, 126, 883, 364, 352, 715, 140, 335, 180, 362, 874, 612, 45, 520, 807, 703, 613, 552, 263, 351, 291, 725, 774, 643, 342, 719, 130, 629, 953, 157, 1, 40, 773, 931, 678, 919, 656, 434, 951, 318, 430, 391, 993, 823, 503, 346, 854, 31, 989, 851, 412, 499, 819, 436, 672, 571, 795, 549, 554, 215, 289, 19, 141, 969, 15, 281, 200, 835, 518, 616, 783, 490, 82, 285, 307, 976, 933, 47, 95, 812, 978, 422, 797, 536, 466, 936, 26, 182, 589, 232, 794, 333, 62, 770, 397, 862, 617, 611, 973, 498, 873, 407, 302, 296, 980, 544, 864, 325, 660, 550, 184, 664, 443, 112, 373, 448, 793, 878, 822, 960, 700, 926, 54, 884, 220, 830, 349, 447, 396, 856, 353, 713, 592, 537, 374, 264, 18, 293, 334, 173, 375, 210, 635, 762, 574, 387, 780, 673, 356, 569, 405, 386, 472, 979, 120, 841, 670, 800, 699, 457, 639, 751, 723, 408, 138, 785, 124, 360, 694, 915, 840, 257, 121, 154, 599, 863, 348, 707, 39, 597, 600, 938, 749, 118, 959, 486, 152, 659, 286, 837, 680, 737, 4, 247, 344, 742, 728, 403, 998, 935, 53, 686, 548, 651, 917, 410, 977, 787, 958, 689, 849, 772, 871, 331, 731, 496, 895, 7, 250, 769, 445, 449, 461, 702, 532, 13, 236, 259, 85, 918, 161, 663, 627, 113, 438, 867, 23, 944, 10, 647, 89, 921, 892, 666, 759, 887, 996, 372, 543, 306, 888, 301, 579]
    return [n, m, h, v]
    
 

list = testcase5()
print(sewers(list[0], list[1], list[2], list[3]))

#answer
def sewers(n,m,h,v):
    #check the maximum continuous numbers in h
    #check the maximum continuous numbers in v
    longest_h = 0
    longest_v = 0

    for num in h:
        if num - 1 not in h:
            current_num = num
            current_streak = 1

            while current_num + 1 in h:
                current_num += 1
                current_streak += 1

            longest_h = max(longest_h, current_streak)

    for num in v:
        if num - 1 not in v:
            current_num = num
            current_streak = 1

            while current_num + 1 in v:
                current_num += 1
                current_streak += 1

            longest_v = max(longest_v, current_streak)

    area = (longest_h+1)*(longest_v+1)
    return area



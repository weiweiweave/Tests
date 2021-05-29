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
    coords_rows=initialCoordinates(n, m)
    removeHorizontalCoords=removeHorizontalCoordinates(m, h)
    removeVerticalCoords=removeVerticalCoordinates(n, v)
    
    #to remove horizontal rows
    for i in range(len(removeHorizontalCoords)):
        coords_rows.remove(removeHorizontalCoords[i])
    #print(coords_rows)
    #print(removeVerticalCoords)
        
    #to remove vertical columns
    for i in range(len(removeVerticalCoords)):
        #print(removeVerticalCoords[i])
        for j in range(len(removeVerticalCoords[i])):
            #print(removeVerticalCoords[i][j])
            for k in range(len(coords_rows)):
                try:
                    coords_rows[k].remove(removeVerticalCoords[i][j])
                except ValueError:
                    pass  # do nothing!
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



list = testcase4()
print(sewers(list[0], list[1], list[2], list[3]))

    
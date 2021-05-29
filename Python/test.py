def betterComprression(s):
    list_s = list(s)
    #print(list_s)
    
    characters_list = []
    frequency_list = []
    temp_list = [i for i in list_s]
    
    
    while len(temp_list)>0:
        
        if ord(temp_list[0])>96:
            characters_list.append(temp_list[0])
        temp_list.pop(0)
    #print(characters_list)
    
    temp_list = [i for i in list_s]
    temp_list.pop(0)
    temp_list = ["," if ord(i)>96 else i for i in temp_list]
    
    temp_list_str = ''.join(map(str, temp_list))
    #print(temp_list_str)
    temp_list = temp_list_str.split(",")
    #print(temp_list)
    frequency_list = [int(i) for i in temp_list]
    
    #print(frequency_list)
    #print(list_s)
    
    ascii_characters_list = [ord(i)-97 for i in characters_list]
    #print(ascii_characters_list)
    
    total_frequency_list = [0 for i in range(26)]
    
    for i in range(len(ascii_characters_list)):
        total_frequency_list[ascii_characters_list[i]] += frequency_list[i]
    
    #print(total_frequency_list)
    result_list = []
    for i in range(len(total_frequency_list)):
        if total_frequency_list[i]>0:
            #print(total_frequency_list[i])
            result_list.append(chr(i+97))
            result_list.append(total_frequency_list[i])
            result_str = ''.join(map(str, result_list))
        
    return result_str



mystring = "a3c9b2c1"
mystring2 = "a12b56c1"

print(betterComprression(mystring))

# File: mystats.py
import numpy as np
# define the mean function here

# define the stddev function here

# define the median function here

# define the mode function here

# part (a)
print('The current module is:', __name__)
#The output is "The current module is: __main__"

# part (b)
#def mean(*args):
#    if len(args) == 0:
#        raise ValueError("At least one argument is required to calculate the mean.")
#    
#    total = sum(args)
#    mean_value = total / len(args)
#    return mean_value

def is_iter(v):
    v_is_iter = True
    try:
        iter(v)
    except TypeError:
        v_is_iter = False
    return v_is_iter

def mean(*args):
    if len(args) == 0:
        raise ValueError("At least one argument is required to calculate the mean.")
    
    total = 0
    count = 0
    
    for arg in args:
        if is_iter(arg):
            total += sum(arg)
            count += len(arg)
        else:
            total += arg
            count += 1
    
    if count == 0:
        raise ValueError("No numeric values found to calculate the mean.")
    
    mean_value = total / count
    return mean_value


# Example usage:
result = mean(10, 20, 30, 40, 50)
print(f"The mean is: {result}")


print('mean(1) should be 1.0, and is:', mean(1))
print('mean(1,2,3,4) should be 2.5, and is:',
                                    mean(1,2,3,4))
print('mean(2.4,3.1) should be 2.75, and is:',
                                    mean(2.4,3.1))
#print('mean() should FAIL:', mean())

# part (c)

def is_iter(v):
    v_is_iter = True
    try:
        iter(v)
    except TypeError:
        v_is_iter = False
    return v_is_iter

def mean(*args):
    if len(args) == 0:
        raise ValueError("At least one argument is required to calculate the mean.")
    
    total = 0
    count = 0
    
    for arg in args:
        if is_iter(arg):
            total += sum(arg)
            count += len(arg)
        else:
            total += arg
            count += 1
    
    if count == 0:
        raise ValueError("No numeric values found to calculate the mean.")
    
    mean_value = total / count
    return mean_value

v1 = {1, 2, 3} # a set is iterable
print(v1, "is iterable:", is_iter(v1))
v2 = 123
print(v2, "is iterable:", is_iter(v2))
print('mean([1,1,1,2]) should be 1.25, and is:',
                               mean([1,1,1,2]))
print('mean((1,), 2, 3, [4,6]) should be 3.2,' +
       'and is:', mean((1,), 2, 3, [4,6]))

# part (d)
# your code here
for i in range(10):
    print("Draw", i, "from Norm(0,1):",
                        np.random.randn())
ls50 = [np.random.randn() for x in range(50)]
print("Mean of", len(ls50), "values from Norm(0,1):",
    mean(ls50))

ls10000 = [np.random.randn() for x in range(10000)]
print("Mean of", len(ls10000), "values from " +
        "Norm(0,1):", mean(ls10000))

# part (e)
# your code here
np.random.seed(0)
a1 = np.random.randn(10)
print("a1:", a1) # should display an ndarray of 10 values
print("the mean of a1 is:", mean(a1))


# part (f)
# your code here
def stddev(*args):
    if len(args) == 0:
        raise ValueError("At least one argument is required to calculate the mean.")
    
    u = mean(*args)
    print(u)
    total = 0
    count = 0
    
    for arg in args:
        if is_iter(arg):
            for i in arg:
                total += (i-u)**2
            count += len(arg)
        else:
            total += (arg-u)**2
            count += 1
    
    if count == 0:
        raise ValueError("No numeric values found to calculate the mean.")
    
    stddev_value = (total / count)**0.5
    return stddev_value


print("the stddev of a1 is:", stddev(a1))
print(np.std(a1)) #you can see that I have the correcrt value

# part (g)
# your code here
def median(*args):
        
   
        
        sorted_numbers = sorted(args)
        for arg in args:
            if is_iter(arg):
                sorted_numbers = np.sort(arg)
        length = len(sorted_numbers)
        if length % 2 == 1:
            return sorted_numbers[length // 2]
        else:
            mid1 = length // 2
            mid2 = mid1 - 1
            return (sorted_numbers[mid1] + sorted_numbers[mid2]) / 2

print("the median of a1 is:", median(a1))
print("median(3, 1, 5, 9, 2):", median(3, 1, 5, 9, 2))
# part (h)
# your code here
def mode(*args):
    d = {}
    max_count = 0
    for arg in args:
        if is_iter(arg):
            for i in arg:
                if i in d:
                    d[i] += 1
                else:
                    d[i] = 1
                max_count = max(max_count, d[i])
        else:
            if arg in d:
                    d[arg] += 1
            else:
                    d[arg] = 1
            max_count = max(max_count, d[arg])
    mode_val = []
    for key in d:
        if d[key] == max_count:
            mode_val.append(key)
    x = tuple(mode_val)
    return x

print("mode(1, 2, (1, 3), 3, [1, 3, 4]) is:",
    mode(1, 2, (1, 3), 3, [1, 3, 4]))

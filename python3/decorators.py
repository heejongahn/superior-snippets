# Basic decorator usage
def verbose(func):
    def decorated():
        print ("Begins %s" % func.__name__)
        func()
        print ("Ends %s" % func.__name__)
    return decorated

@verbose
def fact():
    print ("I'm the king of the world!")
    return

fact()


# Decorate a funcion with arguments

def logger(func):
    def inner(*args, **kwargs): #1
        print ("Arguments were: %s, %s" % (args, kwargs))
        return func(*args, **kwargs) #2
    return inner

@logger
def add(x, y):
    print (x + y)

add(1, y=2)
add(3, 4)
add(y=5, x=6)

class MyStack:
    def __init__(self):
        self.elements = []

    def empty(self):
        return len(self.elements) == 0

    def pop(self):
        try:
            return self.elements.pop()
        except:
            print ("Empty Stack.")
            return None

    def push(self, e):
        self.elements.append(e)

    def top(self):
        if self.empty():
            print ("Empty Stack.")
            return None
        return self.elements[-1]

class MyQueue:
    def __init__(self):
        self.elements = []

    def empty(self):
        return len(self.elements) == 0

    def enqueue(self, e):
        self.elements.insert(0, e)

    def dequeue(self):
        if self.empty():
            print ("Empty queue.")
            return None

        return self.elements.pop()

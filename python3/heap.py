class MyMaxHeap():
    # We'll assume that all elements are integer

    def __init__(self):
        self.elements=[]

    def heapify_to_top(self, child):
        # If root, there's nothing left to do
        if child == 1:
            return

        parent = child//2

        # If the law is violated, swap child/parent elements and recursive call
        if self.elements[parent-1] < self.elements[child-1]:
            (self.elements[parent-1], self.elements[child-1]) = (self.elements[child-1], self.elements[parent-1])
            self.heapify_to_top(parent)

    def heapify_to_bottom(self, parent):
        size = len(self.elements)

        # If leaf, there's nothing left to do
        if parent*2 > size:
            return

        child = parent * 2

        if parent*2 + 1 <= size and self.elements[child-1] < self.elements[parent*2] :
            child += 1

        if self.elements[parent-1] < self.elements[child-1]:
            (self.elements[parent-1], self.elements[child-1]) = (self.elements[child-1], self.elements[parent-1])
            self.heapify_to_bottom(child)

    def insert(self, item):
        self.elements.append(item)
        self.heapify_to_top(len(self.elements))


    def delete(self):
        self.elements[0] = self.elements[-1]
        self.elements = self.elements[:-1]
        self.heapify_to_bottom(1)

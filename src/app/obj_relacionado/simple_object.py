

class SimpleObject:
    def __init__(self, valor_a):
        self.a = valor_a

    @property
    def a(self):
        return self._a

    @a.setter
    def a(self, value):
        if value > 0:
            raise ValueError("value must be less than zero")
        self._a = value


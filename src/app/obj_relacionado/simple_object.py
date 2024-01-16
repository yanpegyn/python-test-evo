

class SimpleObject:
    def __init__(self, valor_a):
        self.a = valor_a
        self._b = "Private"

    @property
    def a(self):
        return self._a

    @a.setter
    def a(self, value):
        if value > 0:
            raise ValueError("value must be less than zero")
        self._a = value

    def __b(self):
        return self._b

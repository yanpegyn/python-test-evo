from random import random

from src.app.obj_relacionado.simple_object import SimpleObject


class AcoplatedObject:
    def __init__(self, simple_value):
        self._name = f"AcoplatedObject{random()}"
        self.simple_objects = SimpleObject(simple_value)

    @property
    def name(self):
        return self._name

    @name.setter
    def name(self, value):
        self._name = value

    @property
    def simple_objects(self):
        return self._simple_objects

    @simple_objects.setter
    def simple_objects(self, simple_object):
        self._simple_objects = [simple_object, simple_object, simple_object]

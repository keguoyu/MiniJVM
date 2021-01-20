#!/usr/bin/env python
# encoding: utf-8

import struct

from vm.DataWrapper import DataWrapper


class LocalVariables(list):
    def __init__(self, max_locals):
        super().__init__([DataWrapper() for _ in range(max_locals)])

    def set_numeric(self, index, val):
        self[index].num = val

    def get_numeric(self, index):
        return self[index].num

    def get_double(self, index):
        val = self.get_numeric(index)
        return struct.unpack('>d', struct.pack('>q', val))[0]

    def set_double(self, index, val):
        val = struct.unpack('>q', struct.pack('>d', val))[0]
        self.set_numeric(index, val)

    def get_float(self, index):
        val = self.get_numeric(index)
        return struct.unpack('>f', struct.pack('>l', val))[0]

    def set_float(self, index, val):
        val = struct.unpack('>l', struct.pack('>f', val))[0]
        self.set_numeric(index, val)

    def set_ref(self, index, ref):
        if ref == 0:
            ref = None
        self[index].ref = ref

    def get_ref(self, index):
        return self[index].ref

    def set_slot(self, index, slot: DataWrapper):
        # todo:
        # self[index] = copy.deepcopy(slot)
        self[index] = slot

    def get_this(self):
        return self.get_ref(0)

    def __str__(self):
        return "slots:{0}".format([str(t) for t in self])

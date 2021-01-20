#!/usr/bin/env python
# encoding: utf-8

import struct


class DataWrapper:
    def __init__(self):
        # 存放整数
        self.num = 0
        # 存放引用
        self.ref = None

    def __str__(self):
        return "num:{0} ref:{1}".format(self.num, self.ref)


class DataWrapperArray(list):
    def __init__(self, slot_count=1):
        if slot_count > 0:
            super().__init__([DataWrapper() for _ in range(slot_count)])
        else:
            super().__init__()

    def set_numeric(self, index, val):
        self[index].num = val

    def get_numeric(self, index):
        return self[index].num

    def set_double(self, index, val):
        val = struct.unpack('>q', struct.pack('>d', val))[0]
        self.set_numeric(index, val)

    def get_double(self, index):
        val = self.get_numeric(index)
        return struct.unpack('>d', struct.pack('>q', val))[0]

    def set_float(self, index, val):
        val = struct.unpack('>l', struct.pack('>f', val))[0]
        self.set_numeric(index, val)

    def get_float(self, index):
        val = self.get_numeric(index)
        return struct.unpack('>f', struct.pack('>l', val))[0]

    def set_ref(self, index, ref):
        if ref == 0:
            ref = None
        self[index].ref = ref

    def get_ref(self, index):
        return self[index].ref


# slot拷贝，不能使用深拷贝copy.deepcopy函数，由于ref复制的是引用，需要将num和ref都进行拷贝。
def copy_slot(slot):
    new_slot = DataWrapper()
    new_slot.num = slot.num
    new_slot.ref = slot.ref
    return new_slot

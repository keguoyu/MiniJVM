#!/usr/bin/env python
# encoding: utf-8

import ctypes

from ops.base.Instruction import NoOperandsInstruction
from ops.loads.Aload import ALOAD
from ops.loads.Dload import DLOAD
from ops.loads.Fload import FLOAD
from ops.loads.Iload import ILOAD
from ops.loads.Lload import LLOAD
from ops.math.Iinc import IINC
from ops.stores.Astore import ASTORE
from ops.stores.Dstore import DSTORE
from ops.stores.Fstore import FSTORE
from ops.stores.Istore import ISTORE
from ops.stores.Lstore import LSTORE


class WIDE(NoOperandsInstruction):
    def __init__(self):
        self.modified_instruction = None

    def fetch_operands(self, reader):
        opcode = reader.read_uint8()

        if opcode == 0x15:
            inst = ILOAD()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x16:
            inst = LLOAD()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x17:
            inst = FLOAD()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x18:
            inst = DLOAD()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x19:
            inst = ALOAD()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x36:
            inst = ISTORE()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x37:
            inst = LSTORE()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x38:
            inst = FSTORE()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x39:
            inst = DSTORE()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x3a:
            inst = ASTORE()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            self.modified_instruction = inst
        elif opcode == 0x84:
            inst = IINC()
            inst.index = ctypes.c_uint(reader.read_uint16()).value
            inst.const = ctypes.c_int32(reader.read_int16()).value
            self.modified_instruction = inst
        elif opcode == 0xa9:
            raise RuntimeError("Unsupported opcode: 0xa9!")

    def execute(self, frame):
        self.modified_instruction.execute(frame)

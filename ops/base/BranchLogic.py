#!/usr/bin/env python
# encoding: utf-8

from runtime import Frame


def branch(frame: Frame, offset):
    pc = frame.thread.pc
    next_pc = pc + offset
    frame.next_pc = next_pc

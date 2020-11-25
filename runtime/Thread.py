#!/usr/bin/env python
# encoding: utf-8

from runtime.heap.Method import Method


class Thread:
    def __init__(self):
        from runtime.Stack import Stack
        self.pc = 0
        self.stack = Stack(1024)

    def push_frame(self, frame):
        self.stack.push(frame)

    def pop_frame(self):
        return self.stack.pop()

    @property
    def current_frame(self):
        return self.stack.top()

    def new_frame(self, method: Method):
        from runtime.Frame import Frame
        return Frame(self, method)

    @property
    def top_frame(self):
        return self.stack.top()

    def is_stack_empty(self):
        return self.stack.is_empty()

    def clear_stack(self):
        self.stack.clear()

    def get_frames(self):
        return self.stack.get_frames()

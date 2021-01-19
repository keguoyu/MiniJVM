#!/usr/bin/env python
# encoding: utf-8

class MethodDescriptor:
    def __init__(self):
        self.parameter_types = []
        self.return_type = ""

    def add_parameter_type(self, t):
        self.parameter_types.append(t)

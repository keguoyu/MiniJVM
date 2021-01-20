from lang.classloader.JvmClassLoader import JvmClassLoader
from lang.classloader.Entry import Entry
import os


class UserClassLoader(JvmClassLoader):

    def parse_entry(self, path):
        if not path:
            # 未传递path 则为当前路径
            path = os.path.abspath(os.path.dirname(__file__))
        return Entry.new_entry(path)

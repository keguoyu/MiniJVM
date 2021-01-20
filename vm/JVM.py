
from lang.classloader.ClassLoader import ClassLoader
from lang.classloader import Loaders
from vm import ByteCodeInterpreter


class JavaVirtualMachine:
    
    def __init__(self, cmd) -> None:
        super().__init__()
        self.cmd = cmd
        cl = Loaders.new_parent_delegate_class_loader(cmd.jreOption, cmd.class_path)
        # 方法区 存放类加载的信息
        self.method_area = {}
        # 类加载器
        self.class_loader = ClassLoader(self.method_area, cl, cmd.debug)

    
    def start(self):
        if self.cmd.class_name:
            class_name = self.cmd.class_name.replace(".", "/")
            main_class = self.class_loader.load_class(class_name)
            main_method = main_class.get_main_method()

            if main_method:
                ByteCodeInterpreter.interpret(main_method, self.cmd.debug, self.cmd.args)
            else:
                print("Main method not found in class {0}".format(self.cmd.class_name))
        else:
            raise RuntimeError('class_name is not valid')
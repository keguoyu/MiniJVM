class Arguments:
    right_fmt = False
    version_flag = False
    help_flag = False
    class_path = ""
    class_name = ""

    def __init__(self, args_list):
        self.parse_args(args_list)

    def parse_args(self, args_list):
        if len(args_list) == 2:
            self.right_fmt = True
            if args_list[1] == "-version":
                self.version_flag = True
            elif args_list[1] == "-help":
                self.help_flag = True
            else:
                self.class_name = args_list[0]
        elif len(args_list) == 4:
            if args_list[1] == "-classpath":
                self.class_path = args_list[1]
                self.class_name = args_list[2]
                self.right_fmt = True
        else:
            self.right_fmt = False

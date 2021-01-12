class MethodArea:
    _cls_cache = {}

    def put_class(self, class_name, jvm_class):
        self._cls_cache[class_name] = jvm_class

    def get_class(self, class_name):
        return self._cls_cache[class_name]
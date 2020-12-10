package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.utils.Debugger;
import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * java -cp /Users/keguoyu/dev/jvm/MiniJVM/test Hello.class
 */
public class UserPathJvmClassLoader extends JvmClassLoader {
    public UserPathJvmClassLoader(JvmClassLoader parent) {
        super(parent);
    }

    @Override
    JvmClass<?> findClass(String classPath, String className) {
        ClassFile classFile = null;
        String newClassName = tryAppendClassSub(className);
        if (classPath.endsWith(".zip") || classPath.endsWith(".jar")) {
            classFile = tryGetFromZip(classPath, newClassName);
        } else {
            classFile = tryGetFromFolder(classPath, newClassName);
        }
        if (classFile != null) {
            System.out.printf("Find class %1s in userPath success\n", className);
            return defineClass(classFile);
        }
        return null;
    }

    private ClassFile tryGetFromZip(String path, String className) {
        try {
            ZipFile zipFile = new ZipFile(path);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class") && entry.getName().contains(className.replace(".", "/"))) {
                    InputStream stream = zipFile.getInputStream(entry);
                    return ClassFile.read(stream);
                }
            }
        } catch (IOException | ConstantPoolException e) {
            Debugger.printlnError(e);
        }
        return null;
    }

    private ClassFile tryGetFromFolder(String classPath, String className) {
        String fullName = classPath + File.separator + className;
        File file = new File(fullName);
        ClassFile classFile = null;
        try {
            classFile = ClassFile.read(file);
        } catch (IOException | ConstantPoolException e) {
            Debugger.printlnError(e);
        }
        return classFile;
    }

}

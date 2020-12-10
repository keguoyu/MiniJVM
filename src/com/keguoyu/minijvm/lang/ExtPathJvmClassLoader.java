package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.ClassFile;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ExtPathJvmClassLoader extends JvmClassLoader {
    private static final String extDirs = System.getProperty("java.ext.dirs");
    private static File[] allFiles = null;

    static {
        allFiles = getExtDirs();
    }

    private static File[] getExtDirs() {
        String var0 = extDirs;
        File[] var1;
        if (var0 != null) {
            StringTokenizer var2 = new StringTokenizer(var0, File.pathSeparator);
            int var3 = var2.countTokens();
            var1 = new File[var3];

            for(int var4 = 0; var4 < var3; ++var4) {
                var1[var4] = new File(var2.nextToken());
            }
        } else {
            var1 = new File[0];
        }

        return var1;
    }

    public ExtPathJvmClassLoader(JvmClassLoader parent) {
        super(parent);
    }

    @Override
    JvmClass<?> findClass(String classPath, String className) {
        ClassFile classFile = null;
        String newClassName = tryAppendClassSub(className);
        for (File file : allFiles) {
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith(".jar") || absolutePath.endsWith(".zip")) {
                try {
                    ZipFile zipFile = new ZipFile(absolutePath);
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();
                        if (entry.getName().equals(newClassName)) {
                            InputStream stream = zipFile.getInputStream(entry);
                            classFile = ClassFile.read(stream);
                            System.out.printf("Find class %1s in extPath success\n", className);
                            break;
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return classFile != null ? defineClass(classFile) : null;
    }
}

package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.ClassFile;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 启动类加载器
 * java -cp bootpath java.lang.Object.class
 */
public class BootPathJvmClassLoader extends JvmClassLoader {
    private static final String bootClassPath = System.getProperty("sun.boot.class.path");
    private static File[] allFiles = null;

    static {
        allFiles = getAllFiles();
    }

    private static File[] getAllFiles() {
        File[] var1;
        if (BootPathJvmClassLoader.bootClassPath != null) {
            int var2 = 0;
            int var3 = 1;
            boolean var4 = false;
            int var5;
            int var7;
            for (var5 = 0; (var7 = BootPathJvmClassLoader.bootClassPath.indexOf(File.pathSeparator, var5)) != -1; var5 = var7 + 1) {
                ++var3;
            }

            var1 = new File[var3];
            var4 = false;

            for (var5 = 0; (var7 = BootPathJvmClassLoader.bootClassPath.indexOf(File.pathSeparator, var5)) != -1; var5 = var7 + 1) {
                if (var7 - var5 > 0) {
                    var1[var2++] = new File(BootPathJvmClassLoader.bootClassPath.substring(var5, var7));
                } else {
                    var1[var2++] = new File(".");
                }
            }

            if (var5 < BootPathJvmClassLoader.bootClassPath.length()) {
                var1[var2++] = new File(BootPathJvmClassLoader.bootClassPath.substring(var5));
            } else {
                var1[var2++] = new File(".");
            }

            if (var2 != var3) {
                File[] var6 = new File[var2];
                System.arraycopy(var1, 0, var6, 0, var2);
                var1 = var6;
            }
        } else {
            var1 = new File[0];
        }
        return var1;
    }

    public BootPathJvmClassLoader() {
        super(null);
    }

    @Override
    JvmClass<?> findClass(String classPath, String className) {
        ClassFile classFile = null;
        for (File file : allFiles) {
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith(".jar") || absolutePath.endsWith(".zip")) {
                try {
                    ZipFile zipFile = new ZipFile(absolutePath);
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();
                        if (entry.getName().equals(className)) {
                            InputStream stream = zipFile.getInputStream(entry);
                            classFile = ClassFile.read(stream);
                            System.out.printf("Find class %1s in bootPath success\n", className);
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

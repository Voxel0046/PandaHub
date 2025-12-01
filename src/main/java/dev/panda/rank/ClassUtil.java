package dev.panda.rank;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import lombok.experimental.UtilityClass;
import org.bukkit.plugin.java.JavaPlugin;

@UtilityClass
public final class ClassUtil {

    public static Collection<Class<?>> getClassesInPackage(JavaPlugin plugin, String pack) {
        List<Class<?>> toReturn = new ArrayList<>();
        CodeSource codeSource = plugin.getClass().getProtectionDomain().getCodeSource();
        URL url = codeSource.getLocation();
        String toFind = pack.replace('.', '/');
        String path = url.getPath().replace("%20", " ");
        String jar = path.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        JarFile jarFile;
        try {
            jarFile = new JarFile(jar);
        }
        catch (IOException e) {
            throw new IllegalStateException("Unexpected IOException reading JAR File '" + jar + "'", e);
        }
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            String className = null;
            if (name.endsWith(".class") && name.startsWith(toFind) && name.length() > toFind.length() + "/".length()) {
                className = name.replace('/', '.').replace('\\', '.').replace(".class", "");
            }
            if (className == null) continue;
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (clazz == null) continue;
            toReturn.add(clazz);
        }
        try {
            jarFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}

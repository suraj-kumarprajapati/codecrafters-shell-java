package environment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class SystemEnvironment implements Environment{

    private Path currentDirectory;
    private final Map<String, String> env;

    public SystemEnvironment() {
        this.currentDirectory = Paths.get(System.getProperty("user.dir"));
        this.env = System.getenv();
    }

    @Override
    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    @Override
    public void changeDirectory(Path path) {
        Path newPath = resolveDirectory(path).normalize();

        if(!Files.exists(newPath) || !Files.isDirectory(newPath)) {
            System.out.println(newPath + ": No such file or directory");
            return;
        }

        currentDirectory = newPath.toAbsolutePath();
    }

    @Override
    public String getEnvironmentVariable(String name) {
        return env.get(name);
    }

    private Path resolveDirectory(Path path) {
        String pathStr = path.toString();

        if(pathStr.startsWith("~")) {
            String homeDir = env.get("HOME");
            return Paths.get(homeDir, pathStr.substring(1));
        }

        return path.isAbsolute() ? path : currentDirectory.resolve(path);
    }
}

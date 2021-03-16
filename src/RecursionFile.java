import java.io.File;

//рекурсивный обход и вывод имени файлов в каталогах и подкаталогах (корневой каталог- ваш проект)

public class RecursionFile {
    public static void main(String[] args) {
        File dir = new File("./src");
        recursionDirectory(dir);
    }

    public static void recursionDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile())
                    System.out.println("File: " + file.getName());
                if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName());
                    recursionDirectory(file);
                }
            }
        }
    }
}

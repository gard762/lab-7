import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileSystemTask {
    public static void main(String[] args) throws IOException {
        Path surnameDir = Paths.get("Voytovich");
        Path nameFile = surnameDir.resolve("Mark.txt");

        try {
            Files.createDirectories(surnameDir);
            if(!Files.exists(nameFile))
                Files.createFile(nameFile);
            System.out.println("Шаги 1-2 выполнены: создана папка и файл.");

            Path nestedDirs = surnameDir.resolve("dir1/dir2/dir3");
            Files.createDirectories(nestedDirs);

            Path copiedFile = nestedDirs.resolve(nameFile.getFileName());
            Files.copy(nameFile, copiedFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Шаг 3 выполнен: созданы вложенные папки и скопирован файл.");

            Path dir1 = surnameDir.resolve("dir1");
            Files.createFile(dir1.resolve("file1.txt"));
            System.out.println("Шаг 4 выполнен: создан file1.");

            Path dir2 = surnameDir.resolve("dir1/dir2");
            Files.createFile(dir2.resolve("file2.txt"));
            System.out.println("Шаг 5 выполнен: создан file2.");

            System.out.println("\n--- Шаг 6: Рекурсивный обход ---");
            try (Stream<Path> paths = Files.walk(surnameDir)){
                paths.forEach(path -> {
                    if(Files.isDirectory(path)){
                        System.out.println("D: " + path.getFileName());
                    } else {
                        System.out.println("F: " + path.getFileName());
                    }
                });
            }

            try(Stream<Path> paths = Files.walk(dir1)){
                paths
                        .sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try{
                                Files.delete(path);
                            } catch (IOException e){
                                System.out.println("Не удалось удалить: " + path);
                            }
                        });

            }
            System.out.println("Директория dir1 и ее содержимое удалены.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
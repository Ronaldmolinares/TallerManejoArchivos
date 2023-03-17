import java.io.*;
import java.util.Scanner;

public class FileCopier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first file path: ");
        String filePath1 = scanner.nextLine();

        System.out.println("Enter the second file path: ");
        String filePath2 = scanner.nextLine();

        System.out.println("Enter the source path: ");
        String sourcePath = scanner.nextLine();

        System.out.println("Enter the file extension (TEXT or BINARY): ");
        String extension = scanner.nextLine();

        // Determine the new file name
        String[] path1Parts = filePath1.split("\\\\");
        String[] path2Parts = filePath2.split("\\\\");
        String fileName1 = path1Parts[path1Parts.length - 1];
        String fileName2 = path2Parts[path2Parts.length - 1];
        String[] fileName1Parts = fileName1.split("\\.");
        String[] fileName2Parts = fileName2.split("\\.");
        String newFileName = fileName1Parts[0] + "-" + fileName2Parts[0] + "." + extension.toLowerCase();
        String newFilePath = sourcePath + "\\" + newFileName;

        // Determine the file types
        boolean isFile1Binary = true;
        boolean isFile2Binary = false;
        if (extension.equalsIgnoreCase("binary")) {
            isFile2Binary = true;
        }

        // Check if the new file already exists
        File newFile = new File(newFilePath);
        if (newFile.exists()) {
            System.out.println("The file " + newFileName + " already exists. Do you want to clear it? (yes/no)");
            String clearFile = scanner.nextLine();
            if (clearFile.equalsIgnoreCase("yes")) {
                newFile.delete();
            }
        }

        // Copy the files' contents into the new file
        try {
            FileInputStream fileInputStream1 = new FileInputStream(filePath1);
            FileOutputStream fileOutputStream = new FileOutputStream(newFilePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream1.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            fileInputStream1.close();

            if (isFile2Binary) {
                FileInputStream fileInputStream2 = new FileInputStream(filePath2);
                while ((length = fileInputStream2.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }
                fileInputStream2.close();
            } else {
                FileReader fileReader2 = new FileReader(filePath2);
                BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
                String line;
                while ((line = bufferedReader2.readLine()) != null) {
                    fileOutputStream.write(line.getBytes());
                    fileOutputStream.write("\r\n".getBytes());
                }
                bufferedReader2.close();
            }

            fileOutputStream.close();
            System.out.println("The files have been copied to " + newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

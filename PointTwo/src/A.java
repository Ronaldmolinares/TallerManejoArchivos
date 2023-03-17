import java.io.*;

public class A {
	public static void main(String[] args) throws IOException {
		String firstFilePath = readFilePathFromKeyboard("Enter the path of the first file: ");
		String secondFilePath = readFilePathFromKeyboard("Enter the path of the second file: ");
		String sourcePath = readFilePathFromKeyboard("Enter the destination path: ");
		String extension = readExtensionFromKeyboard();

		String newFileName = generateNewFileName(firstFilePath, secondFilePath, extension, "-");

		boolean clearFile = askUserToClearFile();

		copyFilesToDestination(firstFilePath, secondFilePath, sourcePath, newFileName, extension, clearFile);

		System.out.println("Files copied successfully!");
	}

	private static String readFilePathFromKeyboard(String prompt) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(prompt);
		return reader.readLine();
	}

	private static String readExtensionFromKeyboard() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String extension = null;
		do {
			System.out.print("Enter the file extension (TEXT or BINARY): ");
			extension = reader.readLine();
		} while (!extension.equalsIgnoreCase("text") && !extension.equalsIgnoreCase("binary"));
		return extension.toLowerCase();
	}

	private static String generateNewFileName(String firstFilePath, String secondFilePath, String extension, String separator) {
		String firstFileName = getFileNameFromPath(firstFilePath);
		String secondFileName = getFileNameFromPath(secondFilePath);
		String newFileName = firstFileName + separator + secondFileName + "." + extension;
		return newFileName;
	}

	private static String getFileNameFromPath(String filePath) {
		File file = new File(filePath);
		String fileName = file.getName();
		int extensionIndex = fileName.lastIndexOf(".");
		if (extensionIndex != -1) {
			fileName = fileName.substring(0, extensionIndex);
		}
		return fileName;
	}

	private static boolean askUserToClearFile() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String answer = null;
		do {
			System.out.print("Do you want to clear the file if it exists? (yes or no): ");
			answer = reader.readLine();
		} while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));
		return answer.equalsIgnoreCase("yes");
	}

	private static void copyFilesToDestination(String firstFilePath, String secondFilePath, String sourcePath, String newFileName, String extension, boolean clearFile) throws IOException {
		String fullFilePath = sourcePath + File.separator + newFileName;
		File outputFile = new File(fullFilePath);

		if (outputFile.exists() && clearFile) {
			PrintWriter writer = new PrintWriter(outputFile);
			writer.print("");
			writer.close();
		}

		OutputStream outputStream = new FileOutputStream(outputFile, true);

		if (extension.equals("text")) {
			copyTextFileToOutputStream(firstFilePath, outputStream);
			copyTextFileToOutputStream(secondFilePath, outputStream);
		} else if (extension.equals("binary")) {
			copyBinaryFileToOutputStream(firstFilePath, outputStream);
			copyBinaryFileToOutputStream(secondFilePath, outputStream);
		}

		outputStream.close();
	}

	private static void copyBinaryFileToOutputStream(String filePath, OutputStream outputStream) throws IOException {
		InputStream inputStream = new FileInputStream(filePath);
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
	}



private static void copyTextFileToOutputStream(String filePath, OutputStream outputStream) throws IOException {
	BufferedReader reader = new BufferedReader(new FileReader(filePath));
	String line = reader.readLine();
	while (line != null) {
		outputStream.write(line.getBytes());
		outputStream.write(System.getProperty("line.separator").getBytes());

	}
}
}
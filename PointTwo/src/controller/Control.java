package controller;
import view.IoManager;
import java.io.*;

public class Control{
	private static IoManager io;


	public Control() {
		io = new IoManager();
	}

	public void init() throws FileNotFoundException {
		
		String filePath1 =io.readGraphicMessagePath("Enter the first  file path .bin");
		String filePath2 =io.readGraphicMessagePath("Enter the second file path .txt");
		String sourcePath=io.readGraphicMessageOutputPath("Enter the source of you new File");
		String newFilePath="";
		int opcionTipeFile=0;
		String extension=".";
		opcionTipeFile=io.readMenu();

		switch (opcionTipeFile) {
		case 1: 
			extension+="bin";
			newFilePath=newFilePath(filePath1, filePath2, sourcePath, extension);
			copyFilesBin(filePath1, filePath2, newFilePath);
			break;
		case 2:
			extension+="txt";
			newFilePath=newFilePath(filePath1, filePath2, sourcePath, extension);
			copyFiles(filePath1, filePath2, newFilePath);
			break;
		case 3:
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcionTipeFile);
		}
		io.showGraphicMessage("¡SEE YOU LATER!");
	}

	public static String newFilePath(String filePath1, String filePath2,String sourcePath, String extension) {
		String[] path1Parts= filePath1.split("\\\\");
		String[] path2Parts = filePath2.split("\\\\");
		String fileName1 = path1Parts[path1Parts.length - 1];//Last Position 
		String fileName2 = path2Parts[path2Parts.length - 1];
		String[] fileName1Parts=fileName1.split("\\.");
		String[] fileName2Parts=fileName2.split("\\.");
		String newFileName= fileName1Parts[0]+"-"+fileName2Parts[0]+extension;
		String newFilePath=sourcePath+"\\"+newFileName;
		return newFilePath;
	}

	private static void copyFilesBin(String filePath1,String filePath2,String newFilePath) {
		try {
			String textData;
			Boolean clean = false;
			File outputFile=new File(newFilePath);
			if (outputFile.exists()) {
				String clearFile = io.readGraphicString("The file already exists"+"\n"+"Do you want to clear it? (yes/no)");
				if (clearFile.equalsIgnoreCase("yes")) {
					outputFile.delete();
					clean=false;
				}else {
					clean=true;
				}
			}
			FileInputStream binaryFileInputStream=new FileInputStream(filePath1);
			BufferedReader textFileReader= new BufferedReader(new FileReader(filePath2));
			FileOutputStream binaryFileOutputStream =new FileOutputStream(newFilePath,clean);
			String salida="\n---------------------------\n";
			byte[] binaryData =binaryFileInputStream.readAllBytes();
			binaryFileOutputStream.write(binaryData);
			binaryFileOutputStream.write(salida.getBytes());
			while((textData= textFileReader.readLine()) !=null) {
				binaryFileOutputStream.write(textData.getBytes());
			}
			binaryFileOutputStream.write(salida.getBytes());
			binaryFileInputStream.close();
			textFileReader.close();
			binaryFileOutputStream.close();
			io.showGraphicMessage("The files have been copied succesfuly in " +newFilePath);
		}catch(IOException e) {
			e.printStackTrace();
			io.showGraphicErrorMessage("One exception has been recorded "+e);
		}
	}

	public static void copyFiles(String filePath1,String filePath2,String newFilePath) {
		try {
			// Comprobar si el archivo de salida ya existe
			File outputFile = new File(newFilePath);
			Boolean clean = true;
			if (outputFile.exists()) {
				String clearFile = io.readGraphicString("The file already exists"+"\n"+"Do you want to clear it? (yes/no)");
				if (clearFile.equalsIgnoreCase("yes")) {
					outputFile.delete();
					clean=false;
				}else {
					clean=true;
				}
			}
			BufferedReader textFileReader = new BufferedReader(new FileReader(filePath2));
			FileInputStream binaryFileInputStream = new FileInputStream(filePath1);
			FileWriter fileWriter = new FileWriter(newFilePath, clean);
			String textData;
			String salida="\n---------------------------\n";
			byte[] binaryData = binaryFileInputStream.readAllBytes();
			fileWriter.write(new String(binaryData));
			fileWriter.write(salida);
			while((textData= textFileReader.readLine())!=null) {
				fileWriter.write(textData+"\n");
			}
			fileWriter.write(salida);
			fileWriter.flush();
			fileWriter.close();
			textFileReader.close();
			binaryFileInputStream.close();
			io.showGraphicMessage("The files have been copied to succesfuly in" + newFilePath);
		} catch (IOException e) {
			io.showGraphicErrorMessage("One exception has been recorded "+e);
		}
	}
}


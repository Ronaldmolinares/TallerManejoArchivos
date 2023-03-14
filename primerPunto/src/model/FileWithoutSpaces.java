package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileWithoutSpaces {

	public String readFile(String path) {

		File file = new File(path);
		StringBuilder builder = new StringBuilder();

		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while ((line = br.readLine()) != null) {

				builder.append(line).append("\n");

			}

			br.close();

		} catch(IOException e){
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public void writeFile(String path, String newContent) {
		
		try (FileWriter write = new FileWriter(path)){
			
			write.write(newContent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

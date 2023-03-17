package view;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class IoManager {

	//atributtes
	private Scanner scanner;

	public IoManager() {
		scanner= new Scanner(System.in);
	}

	public char readChar(String menssage) {
		this.showMenssage(menssage);
		return scanner.next().charAt(0);
	}

	public String readString(String menssage) {
		this.showMenssage(menssage);
		scanner.nextLine();
		return scanner.nextLine();
	}

	public short readShort(String menssage) {
		this.showMenssage(menssage);
		return scanner.nextShort();
	}

	public int readInt(String menssage) {
		this.showMenssage(menssage);
		return scanner.nextInt();
	}

	public long readLong(String menssage) {
		this.showMenssage(menssage);
		return scanner.nextLong();
	}

	public double readDouble(String menssage) {
		this.showMenssage(menssage);
		return scanner.nextDouble();
	}
	public float readFloat(String menssage) {
		this.showMenssage(menssage);
		return scanner.nextFloat();
	}


	public void showMenssage (String message) {
		System.out.println(message);
	}
	public void showMenssageInLine (String message) {
		System.out.print(message);
	}

	public void showArrayaString(String[] strings) {
		for(int contador=0;contador<=strings.length-1;contador++) {
			System.out.println(strings[contador]);
		}
	}

	//----NUMERICAL DATA


	public char readGraphicChar(String menssage) {
		this.showMenssage(menssage);
		return JOptionPane.showInputDialog(null, menssage).charAt(0);
	}

	public String readGraphicString(String menssage) {
		this.showMenssage(menssage);
		return JOptionPane.showInputDialog(null, menssage);
	}

	public short readGraphicShort(String message) {
		this.showMenssage(message);
		return Short.parseShort(JOptionPane.showInputDialog(null, message));
	}

	public int readGraphicInt(String message) {
		this.showMenssage(message);
		return Integer.parseInt(JOptionPane.showInputDialog(null, message));
	}

	public long readGraphicLong(String message) {
		this.showMenssage(message);
		return Long.parseLong(JOptionPane.showInputDialog(null, message));
	}

	public double readGraphicDouble(String  message) {
		this.showMenssage(message);
		return Double.parseDouble(JOptionPane.showInputDialog(null, message));
	}
	public float readGraphicFloat(String message) {
		this.showMenssage(message);
		return Float.parseFloat(JOptionPane.showInputDialog(null, message));
	}
	public String readGraphicMessagePath(String message) {
		this.showMenssage(message);
		return (String) JOptionPane.showInputDialog(null, message, message, 3, null, null, "src\\resources\\input\\file");
	}
	public String readGraphicMessageOutputPath(String message) {
		this.showMenssage(message);
		return (String) JOptionPane.showInputDialog(null, message, null, 3, null, null, "src\\resources\\output");
	}

	public void showGraphicMessage (String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void showGraphicArrayString(String[] message) {
		String show="";
		for (int i=0; i<message.length;i++)
			show+=message[i]+"\n";
		JOptionPane.showMessageDialog(null, show);
	}
	public void showGraphicArrayStringInLine(String[] message) {
		String show="";
		for (int i=0; i<message.length;i++)
			show+=message[i]+"/";
		JOptionPane.showMessageDialog(null, show);
	}
	public void showGraphicErrorMessage(IOException error ) {
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	} 

	
	public int readMenu(){
		String menuText = 
						("Select the type of file you want to work with.\n"+
						"1. Binary (.bin)\n"+
						"2. Text   (.txt)\n"+ 
						"3. Exit"
			  			);
		return readGraphicInt(menuText);
	}
	public int readMenuModify(){
		String menuText = 
						("Do you want to clear it?\n"+
						"1. YES \n"+
						"2. NO\n"+ 
						"3. SALIDA\n"
			  			);
		return readGraphicInt(menuText);
	}

	public void showGraphicErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	
	

}

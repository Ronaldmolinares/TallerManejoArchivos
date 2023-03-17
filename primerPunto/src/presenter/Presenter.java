package presenter;
import model.FileWithoutSpaces;
import view.View;

public class Presenter {

	public void run() {
		View view = new View();
		FileWithoutSpaces archive = new FileWithoutSpaces();	
		boolean salir = false;
		int opcion;

		do {
			view.showMessage("******** Bienvenido ********");
			view.showMessage("1. Mostrar archivo sin espacios");
			view.showMessage("2. Salir");

			opcion = view.readInt("Seleccione una de las opciones ");

			switch (opcion) {
			case 1:
				String info = archive.readFile("src\\resources\\input\\test.txt");
				//Muestra en pantalla el texto del documento sin espacios
				// Elimina espacios, tabuladores, retornos
				System.out.println(info.replaceAll(" ", ""));
				//String readInfo = info.replaceAll(" ", "");
				//Guarda es un nuevo archivo el texto sin espacios
				archive.writeFile("src\\resources\\output\\new-test.txt", info.replace(" ", ""));
				break;

			case 2:
				salir = true;
				System.exit(0);
				break;

			default:
				view.showMessage("Solo números entre 1 y 2");
			}

		} while (!salir);
	}


	public static void main(String[] args) {	
		new Presenter().run();	
	}

}

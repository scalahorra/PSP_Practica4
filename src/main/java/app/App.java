package app;

import java.io.*;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class App {

	static final int PORT = 14147;
	static final String HOST = "localhost";
	static final String USER = "scalahorra";
	static final String PASSWORD = "admin";
	
	public static void main(String[] args) throws SocketException, IOException {
		
		int opcion;
		
		FTPClient ftpClient = new FTPClient();
			
		boolean login;
		
		do {
			
			//Menu
			System.out.println("1. Iniciar sesión");
			System.out.println("2. Cerrar sesión");
			System.out.println("3. Listar fichero y directorios");
			System.out.println("4. Subir fichero");
			System.out.println("5. Descargar fichero del servidor");
			System.out.println("6. Eliminar fichero");
			System.out.println("7. Eliminar directorio");
			System.out.println("8. Crear directorio");
			System.out.println("9. Cambiar directorio actual");
			System.out.println("0. Salir");
			
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			
			switch (opcion) {
			
			
			case 1:
				
				ftpClient.connect(HOST);
				
				String borrar = scanner.nextLine();
				System.out.println("Introduzca su usuario");
				String usuario = scanner.nextLine();
				
				System.out.println("Intruzca su contraseña");
				String contrasena = scanner.nextLine();
				
				System.out.println(usuario + "." + contrasena + ".");
				
				if (usuario == USER && contrasena == PASSWORD) {
										
					login = ftpClient.login(USER,PASSWORD);
					
					System.out.println("Inicio de sesión correcto");
				}
				else {
					
					System.out.println("Usuario y/o contraseña incorrectos");
					
				}
				
				break;
				
				
			case 2:
				
				ftpClient.logout();
				ftpClient.disconnect();
				System.out.println("Desconectado");
				
				break;
				
				
			case 3:
				
					
				
				break;
				
				
			case 4: 

				//Comprobamos que el usuario este logeado
				if (login = true) {
					
					//Seleccionamos las caracteristicas del archivo
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
		            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
		            ftpClient.enterLocalPassiveMode();
		 
		            //Le decimos el nombre del archivo
		            String filename = "C://Users//Sergio//Documents//eclipse-workspace//miarchivo.txt";
		 
		            //Metemos el archivo
		            FileInputStream fis = new FileInputStream(filename);
		 
		            //Guardamos el archivo
		            ftpClient.storeFile(filename, fis);
		            
		            fis.close();
				}
				
				break;
				
				
			case 5:
				
				if (login = true) {
				
					String archivo = "/archivo.doc";
					System.out.println("Escriba el nombre del fichero que quiere descargar");
					archivo = scanner.nextLine();
					
					
					FileOutputStream fos = new FileOutputStream("archivoLocal");

					ftpClient.retrieveFile(archivo, fos);
					fos.close(); 
				}
				
			}
			
		} while (opcion != 0);
		

	}

}

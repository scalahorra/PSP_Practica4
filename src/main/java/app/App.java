package app;

import java.io.*;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class App {

	static final int PORT = 14147;
	static final String HOST = "localhost";
	static final String USER = "scalahorra";
	static final String PASSWORD = "";
	
	public static void main(String[] args) throws SocketException, IOException {
		
		int opcion = 0;
		
		FTPClient ftpClient = new FTPClient();
		
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
			
			opcion = Leer.pedirEnteroValidar();
			
			switch (opcion) {
			
			
			case 1:
				//Iniciar sesión
				
				ftpClient.connect(HOST);
				
				System.out.println("Introduzca su usuario");
				String usuario = Leer.pedirCadena();
				
				System.out.println("Intruzca su contraseña");
				String contrasena = Leer.pedirCadena();
		
				
				boolean login;
				
				if (usuario == USER && contrasena == PASSWORD) {
										
					login = ftpClient.login(USER, PASSWORD);
					
				}
				else {
					
					login = ftpClient.login(usuario,  contrasena);
					
				}
				
				if(login) {
					System.out.println("Login correcto");
				} else {
					System.out.println("Login incorrecto");
				}
				
				break;
				
				
			case 2:
				//Desconexion
				
				ftpClient.logout();
				ftpClient.disconnect();
				System.out.println("Desconectado");
				
				break;
				
				
			case 3:
				//Lista los elementos 
				
				FTPFile[] files = ftpClient.listFiles();
				
				System.out.println("Actualmente hay " + files.length + " ficheros en este directorio");
				
				for (int i=0; i<files.length; i++) {
					
					System.out.println(files[i]);
				}
				
				break;
				
				
			case 4: 
				//Subida de fichero

				System.out.println("Escriba el nombre del fichero que quiera subir");
				
				String nombreFichero = Leer.pedirCadena();
				
				System.out.println("Escriba la ruta de dónde lo quire subir");
				String ruta = Leer.pedirCadena();
				
				System.out.println(ruta);
				
				FileInputStream fis = new FileInputStream(ruta + nombreFichero);
				
				boolean subirArchivo = ftpClient.storeFile(nombreFichero, fis);
				
				if(subirArchivo == true) {
					
					System.out.println("Subida realizada con éxito");
				} else {
					
					System.out.println("Ocurrió un problema al subir el archivo");
				}
				
				break;
				
				
			case 5:
				//Descarga de ficheros
				
				System.out.println("Escriba el nombre del fichero que se quiere descargar");
				String nombreFicheroDescargar = Leer.pedirCadena();
				
				File archivoDescarga = new File("C:\\Users\\Desktop\\compartir");
				
				OutputStream os = new BufferedOutputStream(new FileOutputStream(archivoDescarga + "\\" + nombreFicheroDescargar));
				
				boolean descargarArchivo = ftpClient.retrieveFile(nombreFicheroDescargar, os);
				
				os.close();
				
				if(descargarArchivo) {
					
					System.out.println("Archivo descargado correctamente");
				} else {
					
					System.out.println("Ocurrió un problema al descargar el archivo");
				}
				
				break;
				
			case 6:
				//Eliminacion de ficheros
				
				System.out.println("Escriba el nombre del fichero que desea eliminar");
				String ficheroEliminar = Leer.pedirCadena();
				
				boolean eliminacion = ftpClient.deleteFile("\\" + ficheroEliminar);
				
				if(eliminacion) {
					
					System.out.println("Fichero eliminado correctamente");
				}else {
					
					System.out.println("Ocurrió un problema al eliminar el archivo");
				}
				
				break;
				
			case 7:
				//Eliminacion de directorios
				
				System.out.println("Escriba el nombre del directorio que desea eliminar");
				String directorioEliminar = Leer.pedirCadena();
				
				boolean eliminacionDir = ftpClient.removeDirectory(directorioEliminar);
				
				if(eliminacionDir) {
					
					System.out.println("Directorio eliminado correctamente");
				}else {
					
					System.out.println("Ocurrió un problema al eliminar el directorio");
				}
				
				break;
				
			case 8:
				//Creacion de directorios
				
				System.out.println("Escriba el nombre del directorio que desea crear");
				String directorioCrear = Leer.pedirCadena();
				
				boolean creacion = ftpClient.makeDirectory(directorioCrear);
				
				if(creacion) {
					
					System.out.println("Directorio creado correctamente");
				}else {
					
					System.out.println("Ocurrió un problema al crear el directorio");
				}
				
				break;
				
			}
			
		} while (opcion != 0);
		

	}

}

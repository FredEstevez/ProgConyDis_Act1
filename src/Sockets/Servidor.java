package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {
	
	 private Socket clientSocket;
	 
	 public Servidor (Socket clientSocket) {
		 this.clientSocket = clientSocket;
		 
	 }
	
	 public static void main(String[] args) {
		 
		 try {
		
			 ServerSocket serverSocket = new ServerSocket(5001);
			 System.out.println("Servidor de Chat iniciado.");
				
			 int i = 0;
			 
			 while (true) {
				 
				 Socket clientSocket2 = serverSocket.accept();
				 i++;
				 System.out.println("Cliente conectado desde : " + clientSocket2.getInetAddress() + " , Cliente No." + i);
				 new Thread(new Servidor(clientSocket2),"Cliente " + i).start();
				 
			 }			
		 } catch (IOException e){
			 e.printStackTrace();
		 }
	 }

	public void run() {
		String inputLine;
		
		try {
		 PrintWriter out = new PrintWriter( clientSocket.getOutputStream(), true);
		 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		 out.println("Bienvenido a la encuesta de Cine ");
		 out.println("Por favor, responde la encuesta, selecionando la opcion que tipo de cine te gusta : ");
		 out.println("1.- Cine de Comedia");
		 out.println("2.- Cine de Accion");
		 out.println("3.- Cine de Terror");
		 out.println("4.- Cine de Romance");
		 out.println("5.- Cine Documental");
		 
		 
		 
		 while((inputLine = in.readLine()) != null) {
			 System.out.println("El cliente ha seleccionado la Opción: " + inputLine);
			 
			
			 
			 if (inputLine.equals("q")) {
				 clientSocket.close();
				 break;
			 }
		 }
		 } catch (IOException e){
			 e.printStackTrace();
			 
	}
}
}

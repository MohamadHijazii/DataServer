package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Model.ClientHandler;
import Model.ImageGenerator;

public class Driver {
	
	
	private static ServerSocket socket;
	public static ImageGenerator image ;
	
	public static void connect() {
		try {
			socket = new ServerSocket(6875);
			System.out.println("Connected to port 6875");
				
		} catch (IOException e) {
			System.out.println("Connection error");
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
		//generating the image
		image = new ImageGenerator();
		image.GenerateRandomImage();
		image.test();
		int sx = 3,sy = 1,ex = 5,ey = 4;
		char [][] im = Driver.image.getPartImage(sx, sy, ex, ey);
		
		
		for(int i=0;i<ex-sx+1;i++) {
			for(int j=0;j<ey-sy+1;j++) {
				System.out.print(im[i][j] +" ");
				
			}
			System.out.println("");
		}
		//connecting to server socket
		connect();
		
		//waiting for sensors to connect
		
		Socket link;
		ArrayList<ClientHandler> clients = new ArrayList<>();
		while(true) {
			try {
				link = socket.accept();
				//a new sensor is connected
				ClientHandler client = new ClientHandler(link);
				clients.add(client);
				client.start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
		
		

	}

}

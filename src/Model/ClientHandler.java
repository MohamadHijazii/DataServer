package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import application.Driver;

public class ClientHandler extends Thread{
	
	public int id;
	private Socket link;
	private DataInputStream in;
	private DataOutputStream out;
	public int sx,sy,ex,ey;
	
	
	public ClientHandler(Socket link) {
		this.link = link;
		try {
			in = new DataInputStream(link.getInputStream());

			out = new DataOutputStream(link.getOutputStream());

			System.err.println("input and output streams opened successfully");

		} catch (IOException e) {

			System.out.println("Error while opening input and output streams");
		}

	}
	
	public void run() {
		
		while(true) {
			char c = 0;
			try {
				c = (char) in.readByte();
			} catch (IOException e) {
				
			}
			if(c == 'N') {
				
			}
			else
			if(c == 'D') {
				ReceiveCoordination();
				SendImage();
			}
			else {
				//System.out.println("Packet Error");
			}
			
		}
	}
	
	public void ReceiveCoordination() {
		//1B: type of request, already read
		//sx,sy,ex,ey kel we7de 3a 4 bytes
		try {
			sx = in.readInt();
			sy = in.readInt();
			ex = in.readInt();
			ey = in.readInt();
			System.out.println("Data server read the coordination ..");
			
		}catch(IOException ex) {
			
		}
	}
	
	public void SendImage() {
		//1Byte type of request 'I'
		try {
			out.writeByte('I');
			int n = Driver.image.CalculateDimensions(sx, sy, ex, ey);
			char [][] image = Driver.image.getPartImage(sx, sy, ex, ey);
			//4B size of the part of the image 
			out.writeInt(ex-sx+1);
			out.writeInt(ey-sy+1);
			//The image on N bytes
			for(int i=0;i<ex-sx+1;i++) {
				for(int j=0;j<ey-sy+1;j++) {
					out.write(image[i][j]);
				}
			}
			System.out.println("Data server send the image ..");
			
		}catch(IOException ex) {
			
		}
	}

}

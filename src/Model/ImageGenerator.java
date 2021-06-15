package Model;

import java.util.Random;

public class ImageGenerator {
	
	public int size;
	public char [][]image;
	
	public ImageGenerator(int s) {
		size = s;
		image = new char[s][s];
	}
	
	public ImageGenerator() {
		size = 6;
		image = new char[size][size];
	}
	
	public void GenerateRandomImage() {
		Random r = new Random();
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				char c = (char) (r.nextInt(26) + 'A');
				image[i][j] = c;
			}
		}
	}
	
	
	public char[][] getImage(){
		return image;
	}
	
	public  char[][] getPartImage(int sx,int sy,int ex,int ey){
		int x = ex-sx+1;
		int y = ey-sy+1;
		char [][] im = new char[x][y];
		for(int i=sx,kx=0;i<=ex;i++,kx++) {
			for(int j=sy,ky = 0;j<=ey;j++,ky++) {
				im[kx][ky] = image[i][j];
			}
		}
		return im;
	}
	
	public int CalculateDimensions(int sx,int sy,int ex,int ey) {
		int x = ex-sx+1;
		int y = ey-ex+1;
		return x*y;
	}
	
	
	public void test() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				System.out.print(image[i][j]+"\t");
			}
			System.out.println("");
		}
	}
}

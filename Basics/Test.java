import java.io.*;
import java.util.*;

class Test {

	public static void main(String[] args) {
		int numObj = 5;
		int[][] symMat = new int[numObj][numObj];

		for(int i = 0; i < numObj; i++) {
			for(int j = 0; j < numObj; j++) 
				System.out.print(symMat[i][j] + " ");
			System.out.println("");
		}

		for(int i = 0; i < numObj; i++) 
			for(int j = 0; j < numObj; j++) 
				symMat[i][j]++;


		for(int i = 0; i < numObj; i++) {
			for(int j = 0; j < numObj; j++) 
				System.out.print(symMat[i][j] + " ");
			System.out.println("");
		}
		
	}
}

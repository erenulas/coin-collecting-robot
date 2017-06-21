import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CoinCollectingRobot {

	public static void main(String[] args) {
		
		//	Used for reading the input file
		FileReader fr = null;
		BufferedReader br = null;
		
		//	Used for writing into the output file
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		//	keeps the input matrix
		String[][] a = null;
		
		//	n keeps # of rows, and m keeps # of columns.
		int n=0,m=0;
		
		//	temporary string array for keeping the row 
		String[] temp;
		
		//	keeps number of lines
		int isFirstLine=-1;
		
		//	 keeps the dynamic programming algorithm results
		int[][] b = null;
		
		
		try {
			
			//	gets the filename as a command line argument
			fr = new FileReader(args[0]);
			
			//	keeps the line that is read
			String line;
			
			br = new BufferedReader(fr);
			
			//	reads until all the lines are read
			while((line = br.readLine()) != null) {
				//	splits the line according the tab spaces
				temp = line.split("\t");
				//	if this is the line that contains the dimensions of the board, enters here
				if(isFirstLine == -1){
					//	puts # of rows into n, and # of columns into m
					n = Integer.parseInt(temp[0]);
					m = Integer.parseInt(temp[1]);
					//	initializes the input matrix according to these dimensions
					a = new String[n][m];
				} else {
					//	puts the values from temp to input matrix
					for(int i=0;i<m;i++){
						a[isFirstLine][i] = temp[i];
					}
				}
				isFirstLine++;
			}
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//	prints the input matrix
		/*for(int i=0;i<n;i++){							
			for(int j=0;j<m;j++){
				System.out.print(a[i][j] + "\t");
			}
			System.out.println("\n");
		}*/
		
		// initializes the result matrix, and makes all of its elements -6
		b = new int[n][m];
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				b[i][j]=-6;
			}
		}
		
		// finds the maximum # of coins
		int result = findmax(a,b,n,m);
		
		//	prints max # of coins, and the matrix that contains dynamic programming algo. results
		/*System.out.println(result);
		
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				System.out.print(b[i][j] + "\t");
			}
			System.out.println("\n");
		}*/
		
		
		//System.out.println();
		//System.out.println();
		
		//	replaces the values of cells in the path with "P"
		showPath(a,b,n,m);
		
		//	prints the updated matrix a
		/*for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				System.out.print(a[i][j] + "\t");
			}
			System.out.println("\n");
		}*/
		
		
		try {
			//	writes output to output.dat file
			fw = new FileWriter("output.dat");
			bw = new BufferedWriter(fw);
			//	writes the # of coins to the first line of the output file
			bw.write(""+result);
			bw.newLine();
			
			// writes updated matrix a into the output file
			// each cell is separated by tab spaces
			for(int i=0;i<n;i++){
				for(int j=0;j<m;j++){
					bw.write(a[i][j]);
					bw.write("\t");
				}
				bw.newLine();
			}
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
	
	//	replaces the values of the cells that is on the path with "P"
	public static void showPath(String a[][], int b[][], int n, int m){
		
		//	up keeps the value of the upper cell, left keeps the value of the cell on the left, 
		//	and max_of keeps the maximum value of these two
		int max_of, up, left;
		//	starts from the bottom of the matrix b(result matrix)
		int i=n-1;
		int j=m-1;
		//	keeps moving until it reaches the starting point
		while(i>0 || j>0){
				//	makes the last element of the matrix "P" since it is the destination
				if(i == n-1 && j == m-1){
					a[i][j] = "P";
				}
				
				//	checks the upper cell, if it is not in the array, then takes up value as -6,
				//	otherwise takes the value in the cell from matrix b
				if(i-1 < 0)
					up = -6;
				else
					up = b[i-1][j];
				
				//	does the same thing that is done for the upper cell
				if(j-1 < 0)
					left = -6;
				else
					left = b[i][j-1];
				
				//	gets the maximum of upper cell and cell on the left
				max_of = Math.max(up, left);
				//	if the maximum value is the value of upper one, then moves there, and makes the upper cell value "P" (in matrix a)
				//	otherwise it goes to the cell on the left, and makes its value "P"
				if(max_of == up){
					i = i-1;
					a[i][j] = "P";
					continue;
				} else {
					j = j-1;
					a[i][j] = "P";
					continue;
				}
		}
		//	replaces the value of the first cell with "P"
		a[0][0] = "P";
	}
	
	
	//	finds the maximum # of coins collected that is brought to cell(n,m)
	public static int findmax(String a[][], int b[][],int n, int m){
		
		//	up keeps the value of the upper cell, left keeps the value of the cell on the left
		//	max_of keeps the maximum of these values, and currentCoing keeps the number of coins that the current cell has
		int up,left,max_of,currentCoin;
		
		
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				
				//	if the upper cell is not in the board, then upper cell value is taken as 0
				//	otherwise, it is taken from the result matrix (matrix b)
				if(i-1 < 0 || i-1 >= n){
					up = 0;
				} else {
					up = b[i-1][j];
				}
				
				//	if the cell on the left is not in the board, then value of the cell is taken as 0
				//	otherwise, it is taken from the result matrix (matrix b)
				if(j-1 < 0 || j-1 >= m){
					left = 0;
				} else {
					left = b[i][j-1];
				}
				
				//	gets the maximum of upper cell and the cell on the left
				max_of = Math.max(up, left);
				
				//	if the cell has "X" character or 
				//	if the cell is on the first row and the cell on its left is unreachable, or
				//	if the upper cell, and the cell on its left is unreachable, 
				//	its value in the result matrix is not changed and is left as -6 in the result matrix
				//	since this is lower than the values each cell can take, that cell is not chosen to go to
				//	otherwise, the value in the current cell is put into currentCoin
				if(a[i][j].charAt(0) == 'X' || (left == -6 && i == 0) || (left == -6 && up == -6)){
					continue;
				} else {
					currentCoin = Integer.parseInt(a[i][j]);
				}
				
				//	if the value of the currentCoin is less than zero and,
				//	if we cannot afford it, then the value of that cell is left as -6 in the result matrix since it is unreachable
				//
				if(currentCoin < 0 && max_of < Math.abs(currentCoin)){
					continue;
				}
				//	adds the maximum of upper cell and the cell on left to currentCoin value, 
				//	and puts it to the corresponding cell
				//
				b[i][j] = max_of + currentCoin;
				
			}
		}
		//	returns the max # of coins that can be brought to cell(n,m)
		return b[n-1][m-1];
	}

}

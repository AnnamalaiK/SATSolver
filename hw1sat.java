import java.io.*;
import java.util.*;

public class hw1sat {
	
public static Hashtable<String,Integer> hash=new Hashtable<String,Integer>();

	public static int eval(int [][] expression, int[] b, int nbclauses,int nbvar) { //function to evaluate the expression
				
				int check=1; //flag for checking each clause (true or false)
				int i;
				
				for (i=0;i<nbclauses;i++)
				{	
					check=0;
					for(int j=0;j<nbvar;j++)
					{
						if(expression[i][j] == 0) continue;
						if(expression[i][j]<0) //flip if value is negative
						{
							check |= (1^b[Math.abs(expression[i][j])-1]);
						}
						else
						{
							check |= b[(expression[i][j])-1];
						}
					}
					if(check == 0) { //returns the clause where the current random assignment fails
						return i;
					}
						
				} // end of i
						
				return i;
		
	}
	
	//-------------------------------------------------------------------------------
	
	private static void initclause(int number_of_variables, int[] currvalues) //Generates a random binary number of size nbvar
    { 
       // System.out.println("Now in Clause initialization");
		        
        for(int i=0; i<number_of_variables; i++)
        {
            if(Math.random() < 0.50)
                currvalues[i]=1;
            else
                currvalues[i]=0;
        }
               
  
    }
	//---------------------------------------------------------
	public static void main(String args[]) {
		int i;
		
		int nbvar = 0,nbclauses;
		
		try {
			FileReader file=new FileReader(args[0]);	//file reading 
			BufferedReader br=new BufferedReader(file);
			
			String str1;
			String line;
			String[] str4;
			str1=br.readLine();
			while(str1.startsWith("c")) {
				str1=br.readLine();
			}
			
			String[] str3=str1.split(" "); // split function to delete spaces in between characters in the file
			nbvar=Integer.parseInt(str3[2]);
			nbclauses=Integer.parseInt(str3[3]);
			
			int[][] arr=new int[nbclauses][nbvar];//creating an array based on the file
			
			i=0; 
			while((line=br.readLine())!=null){ //store values in file into a matrix
				str4=line.split(" ");
				
				for(int j=0;j<((str4.length)-1);j++) {
					arr[i][Math.abs(Integer.parseInt(str4[j]))-1]=Integer.parseInt(str4[j]);
					
				}
				i++;
				
				}
			
			int[] randIp = new int[nbvar]; //Generate random number
			initclause(nbvar, randIp);
			hash.put(Arrays.toString(randIp), 1); //store value in hashtable so that there are no collisions
			int count=0;
			while(true) {
				count=0;
			Integer x=1;
			if(count==6) {
				//System.out.println("------");
				initclause(nbvar,randIp);
				count=0;
			}
			//System.out.println("+++++");
				
			int isFail = eval(arr,randIp,nbclauses,nbvar); 
			if(isFail == nbclauses) { //if number of values is equal to nbclauses solution is found
				//print solution for the expression - SATISIFIABLE
				System.out.println("c Solution for previous problem");
				System.out.println("s SATISFIABLE");
				System.out.print("v ");
				for(int l=1;l<nbvar;l++) {
					if(randIp[l]==0) {
					System.out.print("-"+l);
					System.out.print(" ");
					}
					else {
						System.out.print(l);
					System.out.print(" ");
					}
				}
				//exit
				return;
			}
			//flip logic
			while(x!=null) {
//			isFail=eval(arr,randIp,nbclauses,nbvar);
			hash.put(Arrays.toString(randIp), 1);
			int a[]=new int[nbvar]; 
			int g=0;
			Random r=new Random(); //generate random number by flipping one bit that is flip
			for(int z=0;z<nbvar;z++) {
			if(arr[isFail][z]!=0) { //store set values in an array
				a[g]=z;
				g++;
			}
		}
		
		int rnd=r.nextInt(g);
		randIp[a[rnd]]=1^randIp[a[rnd]]; //flip variable
		x=(hash.get(Arrays.toString(randIp))); //check if value is in hash 
		
		}count++;		
			}
	}
		catch(IOException e) {
			e.printStackTrace();

		}
		
	}
}



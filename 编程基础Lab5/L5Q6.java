import java.util.Scanner;
public class L5Q6 {
   public static void main(String[] args)
   {
Scanner sc=new Scanner(System.in);
System.out.printf("please enter the size of the matrix:");
int n=sc.nextInt();
int [][] matrix=new int [n][n];
for (int i=0;i<n;i++)
{if (i==0)//make the first row and the first column be 1
{matrix[i][0]=1;
for (int j=1;j<=n;j++)//make first column to 1 
matrix[j-1][i]=1;}}
for (int i=1;i<=n-1;i++)//to creare a pascal triangle
{
for (int j=1;j<=n-1;j++)

matrix[i][j]=matrix[i-1][j]+matrix[i-1][j-1];

}
for (int i=0;i<n;i++)
{
for (int j=0;j<n;j++)
System.out.printf("%-3d",matrix[i][j]);
System.out.printf("\n");


}



sc.close();




   } 
}

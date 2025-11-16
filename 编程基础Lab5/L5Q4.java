import java.util.Scanner;
public class L5Q4 {
    public static void main(String[]args)
    {
    Scanner sc =new Scanner(System.in);
int [][] matrix = new int [3][3];
int [][] m2 =new int [3][3];
System.out.printf("please enter 9 numbers to full the matrix :\n");
for (int i=0;i<3;i++)
{for (int j=0;j<3;j++)
    {matrix[i][j]=sc.nextInt();
    System.out.printf("please enter another number:\n");}
    System.out.printf("please enter another row:\n");



}
for (int i =0;i<3;i++)
{
for (int t=0;t<3;t++)
m2[t][2-i]=matrix[i][t];

}
for (int i=0;i<3;i++)
{for (int j=0;j<3;j++)
System.out.printf("%d",m2[i][j]);
System.out.printf("\n");




}
sc.close();






    }
}

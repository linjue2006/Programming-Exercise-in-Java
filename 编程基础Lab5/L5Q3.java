import java.util.Scanner;
import java.util.Random;
public class L5Q3 {
   public static void main(String[]args)
   {
Random ran=new Random();
Scanner sc=new Scanner(System.in);
int [] array=new int [7];
System.out.printf("please enter the number of employees:\n");
int employee=sc.nextInt();

for (int i=0;i<employee;i++)
{
for (int j=0;j<7;j++)
{array[j]=ran.nextInt(9);
    System.out.printf("the work hours is %d according to the %d employee\n",array[j],i+1);
    
}




}
sc.close();






   } 
}

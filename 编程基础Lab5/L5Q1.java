import java.util.Scanner;
public class L5Q1{
public static void main(String[]args)
{
    int [] array = new int [100];
    Scanner sc =new Scanner(System.in);
    System.out.printf("enter 100 numbers: \n");
    int max=0;
    int min=0;
    int sum=0;
    for (int i=0;i<100;i++)
    {
array[i]=sc.nextInt();
System.out.printf("please enter another number:\n");
if(i==0)
{array[0]=max;
min=array[0];
}
if(i>0)
{
    if(array[i]>max)
    max=array[i];
    if(array[i]<min)
    min=array[i];
}
sum+=array[i];
    }
for(int j =0;j<100;j++)
System.out.printf("the scores are : %d\n",array[j]);
System.out.printf("the max is :%d\n",max);
System.out.printf("the min is :%d\n",min);
System.out.printf("the average is : %.2f\n",(double)sum/100);
sc.close();













}











}

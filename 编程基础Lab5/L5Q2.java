import java.util.Random;
public class L5Q2{
public static void main(String[]args)
{
int [] array =new int [10];
Random ram =new Random();
array[0]=ram.nextInt(20)+1;
for (int i=1;i<10;i++)
{
array[i]=ram.nextInt(20)+1;
for(int j=0;j<i;j++)
{
if (array[i]==array[j])
array[i]=ram.nextInt(20)+1;

}

}

for (int t=0;t<10;t++)
System.out.printf("the non-duplicate numbers are :%d\n",array[t]);






}










}
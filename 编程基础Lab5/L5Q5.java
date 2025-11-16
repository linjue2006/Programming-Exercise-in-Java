import java.util.Random;
import java.util.Scanner;


public class L5Q5 {
    public static void main(String[]args)
    {
int [] ar1=new int [20];
int temp=-1;
Random ram =new Random();
for (int i=0;i<20;i++)
{ar1[i]=ram.nextInt(101);
System.out.printf("%d ",ar1[i]);}
int lim=20;
for (int j=0;j<19;j++)
{lim--;
    for(int t=1;t<lim;t++)
    {if (ar1[t-1]<ar1[t])
        {
temp=ar1[t-1];
ar1[t-1]=ar1[t];
ar1[t]=temp;
        }
for (int i=0;i<20;i++)
System.out.printf("%d ",ar1[i]);
System.out.printf("\n");
Scanner sc =new Scanner(System.in);
System.out.printf("please enter a number to search:");
int num=sc.nextInt();
Boolean notfound =true;
int max=ar1[0];int min= ar1 [19]; double mid =(max+min)/2;
int count =0;
while (notfound)//still has a question that how to discern a number not in the  array
{
if (num>mid)
{mid=(max+mid)/2;count++;}

else if (num<mid)
{mid=(mid+min)/2;count++;}
else if(num==mid)
{notfound=false;
count++;}
else 
{notfound=false;count++;
System.out.printf("recycle times:%d\n",count);}}



sc.close();

}

    
    
    
    
    
    
    }






}








    }


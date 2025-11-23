import java.util.*;

public class L8Q1 {
   class Number{
  private int [] arr ;
  private int sum=0;
  private int max=0;
  
  Random rand =new Random();
  public Number()//constructor
  {
arr=new int[10];
    for (int i=0;i<10;i++)
    { this.arr[i]=rand.nextInt(101);
sum+=this.arr[i];
if(this.arr[i]>max)
    max=this.arr[i];
    }
  }
public Number(int n)//overloaded constructor
{
arr=new int[n];
for (int i=0;i<n;i++)
    {this.arr [i]=rand.nextInt(101);
    
    sum+=this.arr[i];
if(this.arr[i]>max)
    max=this.arr[i];
    }}
public void display()
{
for (int i=0;i<arr.length;i++)
    System.out.printf("%d ",arr[i]);
System.out.println();
System.out.printf("Sum: %d\n",sum);
System.out.printf("Max: %d\n",max);
System.out.printf("Average:%.2f\n",(double)sum/arr.length);

}





   } 
 public static void main(String[] args)
 {
Number num1=new L8Q1().new Number();
num1.display();



 }  
}

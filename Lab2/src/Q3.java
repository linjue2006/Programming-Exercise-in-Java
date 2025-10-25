import java.util.Random;
public class Q3 {
public static void main(String [] args){int[] numbers =new int[10];
    int sum=0;
   
Random n =new Random();
for (int i=0;i<numbers.length;i++){
   
    numbers[i]=n.nextInt(40)+10;
    System.out.printf("numbers[%d]=%d\n",i,numbers[i]);
    sum+=numbers[i];
}
System.out.printf("the sum and the average is :%d,%.2f\n",sum,(float)sum/numbers.length);




}
   


    
}

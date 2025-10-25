import java.util.Scanner;
public class Q4 {
    public static void main(String[] args){
Scanner second =new Scanner(System.in);
System.out.printf("please enter seconds you want to convert:\n");
int x,hours,minutes,seconds;
x=second.nextInt();
hours=x/3600;
minutes=(x%3600)/60;
seconds=x%60;
System.out.printf("%d seconds is %d hours,%d minutes,and %d seconds\n",x,hours,minutes,seconds);
second.close();


    }
    
}

import java.util.Scanner;
public class Q6 {public static void main(String[] args){
    Scanner input=new Scanner(System.in);
System.out.printf("l=please enter the weight ,the initial temperature and the final tempertureof the water:\n");
    int m =input.nextInt();//the weight of the water

    int i=input.nextInt();//the initial temperature

    int f=input.nextInt();//the final temperature

    float q=m*(f-i)*4184;
    System.out.printf("the energy needed is:%E",q);
    input.close();



}
    
}

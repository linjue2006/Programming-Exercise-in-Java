import java. util.Random;
public class L3Q4 {
    public static void main(String[]args)
    {
int arr[][]=new int[2][3];
Random rand =new Random();

for (int i=0;i<2;i++){
arr[i][0]=rand.nextInt(6)+1;
arr[i][1]=rand.nextInt(6)+1;
arr[i][2]=arr[i][0]+arr[i][1];





}
if (arr[0][2]>arr[1][2])
System.out.println("Player1 win the game");
else
System.out.println("Player2 win the game");





    }
    
}

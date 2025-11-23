import java.util.Random;

public class L6Q5 {
   public static void main(String[]args)
   {
    
Game p1=new Game();
Game p2=new Game();
int s1=p1.n;
int s2=p2.n;
while(true)
{
   s1+=p1.move();
   s2+=p2.move(); 
   if (s1>=100)
   {
System.out.printf("player 1 wins the game");
break;

   }
 if (s2>=100)
    {
System.out.printf("player 2 wins the game");
break;


    }
 
 


   } 
}}
class Game
{
public int n;
Random r;
public Game()
{
    r = new Random();
    this .n=r.nextInt(5)+1;
}
public int move ()
{
    this.n=r.nextInt();
    return this.n;

}



}
import java.util.*;
public class L8Q4
{
public static void main(String args[])
{Game2 p1=new Game2();
Game2 p2=new Game2();
p1.play(p1,p2);//先玩第二个游戏，测试结果

}
}
class Game1
{
int score=0;
int cur1=0;
int cur2=0;
Random ran = new Random();
public void play(Game1 p1,Game1 p2)
{
while(p1.score<=100&&p2.score<=100)
{
p1.cur1=ran.nextInt(6)+1;
p1.cur2=ran.nextInt(6)+1;
p1.score+=(p1.cur1+p1.cur2);
while(p1.cur1==p1.cur2)
{
p1.cur1=ran.nextInt(6)+1;
p1.cur2=ran.nextInt(6)+1;
p1.score+=(p1.cur1+p1.cur2);
if(p1.score>100)
  System.out.println(p1 + "wins");
return;  

}
p2.cur1=ran.nextInt(6)+1;//p1扔完p2再开始
p2.cur2=ran.nextInt(6)+1;
p2.score+=(p2.cur1+p2.cur2);
while(p2.cur1==p2.cur2)
{
p2.cur1=ran.nextInt(6)+1;
p2.cur2=ran.nextInt(6)+1;
p2.score+=(p2.cur1+p2.cur2);
if(p2.score>100)
  System.out.println(p2 + "wins");
return;  

}
}

}}
class Game2
{
int score =0;
int curr=0;
Boolean flag=false;//标记是否已经超过100一次，为游戏规则做准备,如果第一次超过100，则上一次投掷的分数无效
Random rand =new Random();
public void play(Game2 p1,Game2 p2)
{
while(true)//似乎直接可以改成while(true)就行
{
p1.curr=rand.nextInt(6)+1;
    p1.score+=p1.curr;
if(p1.curr==6)
{p1.curr=rand .nextInt(6)+1;
if(p1.curr!=6)
p1.score+=p1.curr;
}
if(p1.score>100&&!p1.flag)//第一次超过100则减去上一次的分数，使其不超过100
{
    p1.score-=p1.curr;
    p1.flag=true;
}
if(p1.score>100&&p1.flag)//第二次超过100直接获胜
   {System.out.println(p1+"wins");
return;}

p2.curr=rand.nextInt(6)+1;
    p2.score+=p2.curr;
if(p2.curr==6)
{p2.curr=rand .nextInt(6)+1;
if(p2.curr!=6)
p2.score+=rand.nextInt(6)+1;
}
if(p2.score>100&&!p2.flag)
{
    p2.score-=p2.curr;
    p2.flag=true;
}
if(p2.score>100&&p2.flag)
 { System.out.println(p2+"wins");
return;}







}





}











}
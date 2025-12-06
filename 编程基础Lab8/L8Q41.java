import java.util.*;
public class L8Q41
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
int turnScore=0;
    while(true)//似乎直接可以改成while(true)就行
{
p1.curr=rand.nextInt(6)+1;
   turnScore=p1.curr;

if(p1.curr==6)//如果等于6则在掷一次
{p1.curr=rand .nextInt(6)+1;
if(p1.curr!=6)
turnScore+=p1.curr;
else
turnScore=0;//如果第二次也是6则本轮分数为0

}
p1.score+=turnScore;//将本轮分数加入总分
if(p1.score>100)//如果超过100，则减去本轮分数

    p1.score-=turnScore;
 else if(p1.score==100)
    {System.out.println("第一个玩家赢了");
return;
    }

p2.curr=rand.nextInt(6)+1;
   turnScore=p2.curr;

if(p2.curr==6)//如果等于6则在掷一次
{p2.curr=rand .nextInt(6)+1;
if(p2.curr!=6)
turnScore+=p2.curr;
else
turnScore=0;//如果第二次也是6则本轮分数为0

}
p2.score+=turnScore;//将本轮分数加入总分
if(p2.score>100)//如果超过100，则减去本轮分数

    p2.score-=turnScore;
 else if(p2.score==100)
    {System.out.println("第二个玩家赢了");
return;
    }






}





}











}
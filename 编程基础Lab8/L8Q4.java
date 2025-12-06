import java.util.*;
public class L8Q4 {
    public static void main(String args[]) {
        Game2 p1 = new Game2("Player 1");
        Game2 p2 = new Game2("Player 2");
        p1.play(p1, p2); // 先玩第二个游戏，测试结果
    }
}

class Game1 {
    int score = 0;
    int cur1 = 0;
    int cur2 = 0;
    Random ran = new Random();

    public void play(Game1 p1, Game1 p2) {
        while (p1.score <= 100 && p2.score <= 100) {
            p1.cur1 = ran.nextInt(6) + 1;
            p1.cur2 = ran.nextInt(6) + 1;
            p1.score += (p1.cur1 + p1.cur2);
            while (p1.cur1 == p1.cur2) {
                p1.cur1 = ran.nextInt(6) + 1;
                p1.cur2 = ran.nextInt(6) + 1;
                p1.score += (p1.cur1 + p1.cur2);
                if (p1.score > 100) {
                    System.out.println("p1 wins");
                    return;
                }
            }

            p2.cur1 = ran.nextInt(6) + 1; // p1扔完p2再开始
            p2.cur2 = ran.nextInt(6) + 1;
            p2.score += (p2.cur1 + p2.cur2);
            while (p2.cur1 == p2.cur2) {
                p2.cur1 = ran.nextInt(6) + 1;
                p2.cur2 = ran.nextInt(6) + 1;
                p2.score += (p2.cur1 + p2.cur2);
                if (p2.score > 100) {
                    System.out.println("p2 wins");
                    return;
                }
            }
        }
    }
}
class Game2 {
    int score = 0;
    boolean flag = false; // 标记是否已经超过100一次
    Random rand = new Random();
    String name;

    public Game2() {
        this.name = "Player";
    }

    public Game2(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void play(Game2 p1, Game2 p2) {
        while (true) {
            // Player 1 turn
            if (takeTurn(p1)) return;

            // Player 2 turn
            if (takeTurn(p2)) return;
        }
    }

    private boolean takeTurn(Game2 p) {
        int oldScore = p.score; // 记录这一轮之前的分数
        int roll = rand.nextInt(6) + 1;
        p.score += roll;

        // 如果第一次扔了6，这可以在扔一次
        if (roll == 6) {
            int roll2 = rand.nextInt(6) + 1;
            p.score += roll2;
        }

        // 检查是否超过100
        if (p.score > 100) {
            if (!p.flag) {
                // 如果第一次超过100，这次分数就不算 (revert to oldScore)
                p.score = oldScore;
                p.flag = true;
                System.out.println(p + " 第一次超过100，分数无效，回到: " + p.score);
            } else {
                // 第二次超过100直接获胜
                System.out.println(p + " wins");
                return true;
            }
        }
        return false;
    }
}
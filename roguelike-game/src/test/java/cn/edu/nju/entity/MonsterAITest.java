package cn.edu.nju.entity;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import cn.edu.nju.GameLogic.GameControl;

public class MonsterAITest {
    private MonsterAI ai;
    private class task implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }

    @Before 
    public void setUp(){
        GameControl.initGame();
        GameControl.gameState = true;
        ai = new MonsterAI(GameControl.getMonsters().get(0));
    }

    @Test
    public void test(){
        new Thread(ai).start();
        new Thread(new task()).start();
    }
}

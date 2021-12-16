package cn.edu.nju.entity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import cn.edu.nju.GameLogic.GameControl;
import cn.edu.nju.utils.Direction;

public class MonsterAI implements Runnable{
    private Monster monster;
    private Random rand;
    private boolean active;



    public MonsterAI(Monster monster){
        this.monster = monster;
        rand = new Random();
        this.active = true;
    }


    @Override
    public void run() {
        while(this.active){
            if(!GameControl.gameState)break;
            if(GameControl.suspend)continue;
            if(!monster.isAlive())break;
            int dir = rand.nextInt(4);
            switch(dir){
                case 0:
                    monster.setDirection(Direction.LEFT);
                    break;
                case 1:
                    monster.setDirection(Direction.RIGHT);
                    break;
                case 2:
                    monster.setDirection(Direction.UP);
                    break;
                case 3:
                    monster.setDirection(Direction.DOWN);
                    break;
            }
            monster.move();
            if(rand.nextInt(2) == 1)monster.fire(monster.dir);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("[MonsterAI]:Exceptions in MonsterAI!!!");
            }
        }
        }
    }

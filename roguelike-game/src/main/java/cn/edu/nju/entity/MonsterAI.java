package cn.edu.nju.entity;

import java.util.Random;

import cn.edu.nju.utils.Direction;

public class MonsterAI implements Runnable{
    private Monster monster;
    private Random rand;
    private boolean active;
    private Long internalCounter;


    public MonsterAI(Monster monster){
        this.monster = monster;
        rand = new Random();
        this.active = true;
        internalCounter = 0L;
    }

    

    @Override
    public void run() {
        while(this.active){
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
           if(internalCounter == 30000000){
            monster.move();
            if(rand.nextInt(2) == 1)monster.fire(monster.dir);
            internalCounter = 0L;
           }
           internalCounter++;
        }
        }
    }

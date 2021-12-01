package cn.edu.nju;

import cn.edu.nju.GameLogic.GameControl;
import cn.edu.nju.gui.KeyBoard;
import cn.edu.nju.gui.Window;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        try {
			
			System.out.println("[Main]: Starting...");
			Window.create();
			GameControl.initGame();
			Window.setVisible();
			Window.screen.setFocusable(true);
			Window.screen.requestFocus();
			KeyBoard kb = new KeyBoard();
			Window.screen.addKeyListener(kb);
			while(!kb.enter_be_pressed){
				
			}
			TimeUnit.MILLISECONDS.sleep(2000);
			GameControl.startGame();
			System.out.println("[Main]: Started!");
			
		} catch(Exception e) {
			System.err.println("\n[Main]: Uncaught exception in initialization!\n");
			e.printStackTrace();
			System.exit(-1);
		}
	}
    }

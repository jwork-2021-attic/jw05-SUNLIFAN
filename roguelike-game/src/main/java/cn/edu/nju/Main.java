package cn.edu.nju;

import cn.edu.nju.GameLogic.GameControl;
import cn.edu.nju.gui.Window;

public class Main {
    public static void main(String[] args) {
        try {
			
			System.out.println("[Main]: Starting...");
			
			Window.create();
			GameControl.startGame();
			Window.screen.setFocusable(true);
			Window.screen.requestFocus();
			Window.setVisible();
			
			System.out.println("[Main]: Started!");
			
		} catch(Exception e) {
			System.err.println("\n[Main]: Uncaught exception in initialization!\n");
			e.printStackTrace();
			System.exit(-1);
		}
	}
    }

package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import persistence.JsonReader;
import persistence.JsonWriter;

import model.*;

public class SurvivorGameGUI extends JFrame {

    private static final int INTERVAL = 10;
    private SurvivorGame survivorGame;
    private GamePanel gamePanel;


    public SurvivorGameGUI() {
        survivorGame = new SurvivorGame();

        setTitle("Survivor Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(false);
        //addKeyListener(new KeyHandler());
        //gamePanel = new GamePanel(survivorGame);
        //add(gamePanel, BorderLayout.CENTER);

        pack();
        //centreOnScreen();
        setVisible(true);

        //addTimer();
    }



}


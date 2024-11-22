package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import persistence.JsonReader;
import persistence.JsonWriter;

import model.*;

public class SurvivorGameGUI extends JFrame {
    private static final String JSON_STORE = "./data/SurvivorGame.json";
    private static final int INTERVAL = 10;
    private SurvivorGame survivorGame;
    private GamePanel gamePanel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public SurvivorGameGUI() {
        survivorGame = new SurvivorGame();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Survivor Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(false);
        addKeyListener(new KeyHandler());
        gamePanel = new GamePanel(survivorGame);
        add(gamePanel, BorderLayout.CENTER);
        addSaveAndLoadButtons();

        pack();
        centreOnScreen();
        setVisible(true);

        addTimer();
    }

    // Set up timer
	// modifies: none
	// effects:  initializes a timer that updates game each
	//           INTERVAL milliseconds
	private void addTimer() {
		Timer t = new Timer(INTERVAL, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				//survivorGame.update();
				gamePanel.repaint(); 
			}
		});
		
		t.start();
	}

    
    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            survivorGame.keyPressed(e.getKeyCode());
        }
    }

    // Centres frame on desktop
	// modifies: this
	// effects:  location of frame is set so frame is centred on desktop
	private void centreOnScreen() {
		Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
	}

    // Add save and load buttons
    // modifies: none
    // effects: add save and load buttons to the frame
    private void addSaveAndLoadButtons() {
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save Game");
        JButton loadButton = new JButton("Load Game");
        saveButton.addActionListener(e -> saveGame());
        loadButton.addActionListener(e -> loadGame());
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        saveButton.setFocusable(false);
        loadButton.setFocusable(false);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    //EFFECTS: Saves the game to JSON_STORE
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(survivorGame);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Game saved!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error: Unable to save the game.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the game from JSON_STORE
    private void loadGame() {
        try {
            survivorGame = jsonReader.read();
            gamePanel.updateGame(survivorGame);
            JOptionPane.showMessageDialog(this, "Game loaded");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: Unable to load the game.");
        }
    }
}


package SpaceExplorer.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import SpaceExplorer.Game;

import java.awt.Font;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements a GUI application frame used as the start screen
 * for the game
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 */
public class StartScreen {

	private JFrame frmStartScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frmStartScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStartScreen = new JFrame();
		frmStartScreen.setTitle("Space Explorer");
		frmStartScreen.setName("Start Screen");
		frmStartScreen.setResizable(false);
		frmStartScreen.setBounds(100, 100, 640, 480);
		frmStartScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStartScreen.getContentPane().setLayout(null);
		
		JLabel lblSpaceExplorer = new JLabel("Space Explorer");
		lblSpaceExplorer.setFont(new Font("Tahoma", Font.PLAIN, 48));
		lblSpaceExplorer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpaceExplorer.setBounds(12, 12, 610, 194);
		frmStartScreen.getContentPane().add(lblSpaceExplorer);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = Game.getCurrentGame();
				SetupScreen setup = new SetupScreen(frmStartScreen);
				setup.setVisible(true);
				if (setup.getStatusCode() == 0) {					
					game.setupGame(setup.getDays(), setup.getCrew());
					MainGameScreen mainGame = new MainGameScreen(frmStartScreen);
					mainGame.setVisible(true);
					GameOverScreen gameOverScreen = new GameOverScreen(frmStartScreen);
					gameOverScreen.setVisible(true);
				}
			}
		});
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnStartGame.setBounds(12, 219, 610, 100);
		frmStartScreen.getContentPane().add(btnStartGame);
		
		JButton btnQuitGame = new JButton("Quit Game");
		btnQuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game game = Game.getCurrentGame();
				game.quitGame();
			}
		});
		btnQuitGame.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnQuitGame.setBounds(12, 332, 610, 100);
		frmStartScreen.getContentPane().add(btnQuitGame);
		
		try {			
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/resources/Metropolis.wav")));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frmStartScreen, "An error occurred while playing the music.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}

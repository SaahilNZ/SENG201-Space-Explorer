package SpaceExplorer.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import SpaceExplorer.Game;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
				game.startGame();
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
	}
}

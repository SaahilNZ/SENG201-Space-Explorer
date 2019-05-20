package SpaceExplorer.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SpaceExplorer.Game;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOverScreen extends JDialog {

	private final JPanel pnlContent = new JPanel();
	
	/**
	 * Create the dialog.
	 */
	public GameOverScreen(JFrame parent) {
		super(parent, true);
		Game game = Game.getCurrentGame();
		setTitle(game.getWinStatus() ? "Victory!" : "Game Over");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblGameOver = new JLabel(game.getWinStatus() ? "Victory!" : "Game Over");
			lblGameOver.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
			pnlContent.add(lblGameOver, BorderLayout.NORTH);
		}
		{
			JPanel pnlVerticalContent = new JPanel();
			pnlContent.add(pnlVerticalContent, BorderLayout.CENTER);
			pnlVerticalContent.setLayout(new BorderLayout(0, 0));
			{
				JPanel pnlResult = new JPanel();
				pnlVerticalContent.add(pnlResult, BorderLayout.NORTH);
				pnlResult.setLayout(new BoxLayout(pnlResult, BoxLayout.Y_AXIS));
				{
					Component verticalStrut = Box.createVerticalStrut(60);
					pnlResult.add(verticalStrut);
				}
				{
					JPanel pnlShipName = new JPanel();
					pnlResult.add(pnlShipName);
					{
						JLabel lblShipName = new JLabel("Ship Name: " + game.getCrew().getShip().getName());
						pnlShipName.add(lblShipName);
					}
				}
				{
					JPanel pnlDaysTaken = new JPanel();
					pnlResult.add(pnlDaysTaken);
					{
						int daysTaken = game.getCurrentDay() > game.getDesiredDays()
								? game.getDesiredDays() : game.getCurrentDay();
						JLabel lblDaysTaken = new JLabel("Days Taken: " + daysTaken);
						pnlDaysTaken.add(lblDaysTaken);
					}
				}
				{
					JPanel pnlGameResult = new JPanel();
					pnlResult.add(pnlGameResult);
					{
						String gameResult;
						if (game.getWinStatus()) {
							gameResult = "You successfully found all the spaceship parts!";
						} else {
							gameResult = "You were unable to find all the spaceship parts.";
						}
						JLabel lblGameResult = new JLabel(gameResult);
						pnlGameResult.add(lblGameResult);
					}
				}
				{
					JPanel pnlScore = new JPanel();
					pnlResult.add(pnlScore);
					{
						JLabel lblScore = new JLabel("Score: " + game.calculateScore());
						pnlScore.add(lblScore);
					}
				}
			}
		}
		{
			JPanel pnlDialogButtons = new JPanel();
			pnlDialogButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnlDialogButtons, BorderLayout.SOUTH);
			{
				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				btnClose.setActionCommand("OK");
				pnlDialogButtons.add(btnClose);
				getRootPane().setDefaultButton(btnClose);
			}
		}
	}

}

package SpaceExplorer.GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JTextPane;

import SpaceExplorer.Game;
import SpaceExplorer.CrewMembers.CrewMember;
import SpaceExplorer.GUI.Actions.SelectCrewMemberDialog;
import SpaceExplorer.GUI.Actions.ViewCrewDialog;
import SpaceExplorer.GUI.Actions.VisitOutpostDialog;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGameScreen extends JDialog {
	
	private JLabel lblPlanetName;
	private JTextPane txtpnPlanetDescription;
	private JLabel lblShipName;
	private JTextPane txtpnShipStats;
	private JLabel lblCurrentDay;
	
	private Game game;
	
	/**
	 * Create the dialog.
	 */
	public MainGameScreen(JFrame parent) {
		super(parent, true);
		setResizable(false);
		
		game = Game.getCurrentGame();
		
		setTitle("Space Explorer");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabsMainContainer = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabsMainContainer);
		
		JPanel pnlGameInfo = new JPanel();
		tabsMainContainer.addTab("Game Information", null, pnlGameInfo, null);
		pnlGameInfo.setLayout(new BorderLayout(0, 0));
		
		JSplitPane spGameInfo = new JSplitPane();
		spGameInfo.setResizeWeight(0.5);
		pnlGameInfo.add(spGameInfo);
		
		JPanel pnlPlanetInfo = new JPanel();
		spGameInfo.setLeftComponent(pnlPlanetInfo);
		pnlPlanetInfo.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlPlanet = new JPanel();
		pnlPlanetInfo.add(pnlPlanet, BorderLayout.NORTH);
		pnlPlanet.setLayout(new BoxLayout(pnlPlanet, BoxLayout.Y_AXIS));
		
		lblPlanetName = new JLabel(game.getCurrentPlanet().toString());
		pnlPlanet.add(lblPlanetName);
		lblPlanetName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		pnlPlanet.add(verticalStrut);
		
		txtpnPlanetDescription = new JTextPane();
		txtpnPlanetDescription.setEditable(false);
		txtpnPlanetDescription.setBackground(SystemColor.control);
		txtpnPlanetDescription.setText(game.getCurrentPlanet().getDescription());
		pnlPlanet.add(txtpnPlanetDescription);
		
		JPanel pnlPlanetButtons = new JPanel();
		pnlPlanetInfo.add(pnlPlanetButtons, BorderLayout.SOUTH);
		pnlPlanetButtons.setLayout(new BorderLayout(0, 0));
		
		JButton btnSearchPlanet = new JButton("Search Planet");
		btnSearchPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectCrewMemberDialog dialog = new SelectCrewMemberDialog(parent,
						game.getCrew().getCrewMembers());
				dialog.setVisible(true);
				if (dialog.getStatusCode() == 0) {
					CrewMember crewMember = dialog.getSelectedCrewMember();
					String message = crewMember.searchPlanet(game.getCrew(), game.getCurrentPlanet());
					JOptionPane.showMessageDialog(parent, message, "Search Results", JOptionPane.INFORMATION_MESSAGE);
				}
				dialog.dispose();
				if (game.getCrew().getShipPieces() >= game.getTotalShipParts()) {
					game.setWinStatus(true);
					JOptionPane.showMessageDialog(parent, "You've found all the ship parts!",
							"Congratulations", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				}
			}
		});
		pnlPlanetButtons.add(btnSearchPlanet, BorderLayout.NORTH);
		
		JButton btnVisitOutpost = new JButton("Visit Outpost");
		btnVisitOutpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitOutpostDialog dialog = new VisitOutpostDialog(parent,
						game.getCurrentPlanet().getOutpost(), game.getCrew());
				dialog.setVisible(true);
			}
		});
		pnlPlanetButtons.add(btnVisitOutpost, BorderLayout.SOUTH);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		pnlPlanetButtons.add(verticalStrut_3, BorderLayout.CENTER);
		
		JPanel pnlShipInfo = new JPanel();
		spGameInfo.setRightComponent(pnlShipInfo);
		pnlShipInfo.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlShip = new JPanel();
		pnlShipInfo.add(pnlShip, BorderLayout.NORTH);
		pnlShip.setLayout(new BoxLayout(pnlShip, BoxLayout.Y_AXIS));
		
		lblShipName = new JLabel(game.getCrew().getShip().toString());
		lblShipName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlShip.add(lblShipName);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlShip.add(verticalStrut_1);
		
		String shipStats = game.getCrew().getShip().getStatus();
		shipStats += "\nShip parts found: " + game.getCrew().getShipPieces() + "/" + game.getTotalShipParts();
		txtpnShipStats = new JTextPane();
		txtpnShipStats.setEditable(false);
		txtpnShipStats.setBackground(SystemColor.control);
		txtpnShipStats.setText(shipStats);
		pnlShip.add(txtpnShipStats);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlShip.add(verticalStrut_2);
		
		lblCurrentDay = new JLabel("Current Day: " + game.getCurrentDay());
		pnlShip.add(lblCurrentDay);
		
		JButton btnNextDay = new JButton("Next Day");
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean gameStatus = game.newDay();
				if (gameStatus) {					
					refreshDialog();
				} else {
					JOptionPane.showMessageDialog(parent, "You have either run out of days, or all your crew members are dead.\nBetter luck next time!",
							"Game Over", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
				}
			}
		});
		pnlShipInfo.add(btnNextDay, BorderLayout.SOUTH);
		
		JPanel pnlCrewControls = new JPanel();
		tabsMainContainer.addTab("Crew Controls", null, pnlCrewControls, null);
		pnlCrewControls.setLayout(null);
		
		JButton btnViewCrew = new JButton("<html><center>View Crew</center></html>");
		btnViewCrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCrewDialog dialog = new ViewCrewDialog(parent, game.getCrew());
				dialog.setVisible(true);
			}
		});
		btnViewCrew.setBounds(10, 36, 100, 100);
		pnlCrewControls.add(btnViewCrew);
		
		JLabel lblActions = new JLabel("Regular Actions:");
		lblActions.setBounds(10, 11, 210, 14);
		pnlCrewControls.add(lblActions);
		
		JButton btnPilotShip = new JButton("<html><center>Pilot Ship</center></html>");
		btnPilotShip.setBounds(120, 36, 100, 100);
		pnlCrewControls.add(btnPilotShip);
		
		JButton btnUseItem = new JButton("<html><center>Use Item</center></html>");
		btnUseItem.setBounds(10, 147, 100, 100);
		pnlCrewControls.add(btnUseItem);
		
		JButton btnSleep = new JButton("<html><center>Sleep</center></html>");
		btnSleep.setBounds(120, 147, 100, 100);
		pnlCrewControls.add(btnSleep);
		
		JButton btnCookFood = new JButton("<html><center>Cook Food</center></html>");
		btnCookFood.setBounds(304, 36, 100, 100);
		pnlCrewControls.add(btnCookFood);
		
		JButton btnPerformMusic = new JButton("<html><center>Perform Music</center></html>");
		btnPerformMusic.setBounds(304, 147, 100, 100);
		pnlCrewControls.add(btnPerformMusic);
		
		JLabel lblClassActions = new JLabel("Crew Actions:");
		lblClassActions.setBounds(304, 11, 210, 14);
		pnlCrewControls.add(lblClassActions);
		
		JButton btnRepairShip = new JButton("<html><center>Repair Ship</center></html>");
		btnRepairShip.setBounds(10, 258, 100, 100);
		pnlCrewControls.add(btnRepairShip);
		
		JButton btnHealCrewMember = new JButton("<html><center>Heal Crew</center></html>");
		btnHealCrewMember.setBounds(414, 36, 100, 94);
		pnlCrewControls.add(btnHealCrewMember);
		
		JButton btnCurePlague = new JButton("<html><center>Cure Plague</center></html>");
		btnCurePlague.setBounds(414, 147, 100, 100);
		pnlCrewControls.add(btnCurePlague);
		
		JTextPane txtpnNoteCrewActions = new JTextPane();
		txtpnNoteCrewActions.setEditable(false);
		txtpnNoteCrewActions.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtpnNoteCrewActions.setBackground(SystemColor.control);
		txtpnNoteCrewActions.setText("NOTE: Crew Actions can only be taken by certain types of crew members");
		txtpnNoteCrewActions.setBounds(304, 258, 210, 100);
		pnlCrewControls.add(txtpnNoteCrewActions);
	}
	
	private void refreshDialog() {
		lblPlanetName.setText(game.getCurrentPlanet().toString());
		txtpnPlanetDescription.setText(game.getCurrentPlanet().getDescription());
		txtpnShipStats.setText(game.getCrew().getShip().getStatus());
		lblCurrentDay.setText("Current Day: " + game.getCurrentDay());
		String shipStats = game.getCrew().getShip().getStatus();
		shipStats += "\nShip parts found: " + game.getCrew().getShipPieces() + "/" + game.getTotalShipParts();
		txtpnShipStats.setText(shipStats);
		
		invalidate();
		validate();
		repaint();
	}
}

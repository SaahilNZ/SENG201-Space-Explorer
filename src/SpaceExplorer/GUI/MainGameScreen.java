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

import SpaceExplorer.FoodItem;
import SpaceExplorer.Game;
import SpaceExplorer.Item;
import SpaceExplorer.Planet;
import SpaceExplorer.CrewMembers.Chef;
import SpaceExplorer.CrewMembers.CrewMember;
import SpaceExplorer.CrewMembers.SpaceBard;
import SpaceExplorer.CrewMembers.CrewMember.ActionResult;
import SpaceExplorer.GUI.Actions.SelectListItemDialog;
import SpaceExplorer.GUI.Actions.ViewCrewDialog;
import SpaceExplorer.GUI.Actions.VisitOutpostDialog;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
				SelectListItemDialog<CrewMember> dialog = new SelectListItemDialog<CrewMember>(parent,
						game.getCrew().getCrewMembers(), "Select Crew Member");
				dialog.setVisible(true);
				if (dialog.getStatusCode() == 0) {
					CrewMember crewMember = dialog.getSelectedItem();
					ActionResult result = crewMember.searchPlanet(game.getCrew(), game.getCurrentPlanet());
					JOptionPane.showMessageDialog(parent, result.getMessage(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
					refreshDialog();
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
		btnPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectListItemDialog<CrewMember> crewDialog1 = new SelectListItemDialog<CrewMember>(parent,
						game.getCrew().getCrewMembers(), "Select First Crew Member");
				crewDialog1.setVisible(true);
				if (crewDialog1.getStatusCode() == 0) {
					SelectListItemDialog<CrewMember> crewDialog2 = new SelectListItemDialog<CrewMember>(parent,
							game.getCrew().getCrewMembers(), "Select Second Crew Member");
					crewDialog2.setVisible(true);
					if (crewDialog2.getStatusCode() == 0) {
						SelectListItemDialog<Planet> planetDialog = new SelectListItemDialog<Planet>(parent,
								game.getPlanets(), "Select Destination");
						planetDialog.setVisible(true);
						if (planetDialog.getStatusCode() == 0) {
							String message = "";
							CrewMember cm1 = crewDialog1.getSelectedItem();
							CrewMember cm2 = crewDialog2.getSelectedItem();
							Planet planet = planetDialog.getSelectedItem();
							
							if (cm1 == cm2) {
								message = "You cannot pick the same crew member twice.";
							} else {
								if (game.getCurrentPlanet() == planet) {
									message = "You are already on " + planet.getName();
								} else {
									if (cm1.canPilotShip() && cm2.canPilotShip()) {
										cm1.pilotShip();
										cm2.pilotShip();
										game.setCurrentPlanet(planet);
										message = "You have travelled to " + planet.getName();
									} else {
										message = "One or both crew members do not have enough actions left to pilot the ship.";
									}
								}
							}
							
							refreshDialog();
							JOptionPane.showMessageDialog(parent, message, "Pilot Ship", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		btnPilotShip.setBounds(120, 36, 100, 100);
		pnlCrewControls.add(btnPilotShip);
		
		JButton btnUseItem = new JButton("<html><center>Use Item</center></html>");
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectListItemDialog<CrewMember> crewDialog = new SelectListItemDialog<CrewMember>(parent,
						game.getCrew().getCrewMembers(), "Select Crew Member");
				crewDialog.setVisible(true);
				if (crewDialog.getStatusCode() == 0) {
					SelectListItemDialog<Item> itemDialog = new SelectListItemDialog<Item>(parent,
							game.getCrew().getItems(), "Select Item");
					itemDialog.setVisible(true);
					if (itemDialog.getStatusCode() == 0) {
						CrewMember crewMember = crewDialog.getSelectedItem();
						Item item = itemDialog.getSelectedItem();
						ActionResult result = crewMember.useItem(item);
						String message = result.getMessage();
						if (result.getSuccess()) {
							if (!game.getCrew().removeItem(item)) {
								message = "Item not found";
							}
						}
						JOptionPane.showMessageDialog(parent, message, "Use Item", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnUseItem.setBounds(10, 147, 100, 100);
		pnlCrewControls.add(btnUseItem);
		
		JButton btnSleep = new JButton("<html><center>Sleep</center></html>");
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectListItemDialog<CrewMember> dialog = new SelectListItemDialog<CrewMember>(parent,
						game.getCrew().getCrewMembers(), "Select Crew Member");
				dialog.setVisible(true);
				if (dialog.getStatusCode() == 0) {
					JOptionPane.showMessageDialog(parent, dialog.getSelectedItem().sleep(),
							"Sleep", JOptionPane.INFORMATION_MESSAGE);
					refreshDialog();
				}
				dialog.dispose();
			}
		});
		btnSleep.setBounds(120, 147, 100, 100);
		pnlCrewControls.add(btnSleep);
		
		JButton btnCookFood = new JButton("<html><center>Cook Food</center></html>");
		btnCookFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: cook food
				ArrayList<Chef> chefs = new ArrayList<Chef>();
				for (CrewMember crewMember : game.getCrew().getCrewMembers()) {
					if (crewMember instanceof Chef) chefs.add((Chef)crewMember);
				}
				SelectListItemDialog<Chef> crewDialog = new SelectListItemDialog<Chef>(parent, chefs, "Select Chef");
				crewDialog.setVisible(true);
				if (crewDialog.getStatusCode() == 0) {
					ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
					for (Item item : game.getCrew().getItems()) {
						if (item instanceof FoodItem) foodItems.add((FoodItem)item);
					}
					
					SelectListItemDialog<FoodItem> itemDialog = new SelectListItemDialog<FoodItem>(parent,
							foodItems, "Select Food Item");
					itemDialog.setVisible(true);
					if (itemDialog.getStatusCode() == 0) {
						Chef chef = crewDialog.getSelectedItem();
						FoodItem foodItem = itemDialog.getSelectedItem();
						ActionResult result = chef.cook(foodItem);
						String message = result.getMessage();
						JOptionPane.showMessageDialog(parent, message, "Cook Food", JOptionPane.INFORMATION_MESSAGE);
						refreshDialog();
					}
				}
				crewDialog.dispose();
			}
		});
		btnCookFood.setBounds(304, 36, 100, 100);
		pnlCrewControls.add(btnCookFood);
		
		JButton btnPerformMusic = new JButton("<html><center>Perform Music</center></html>");
		btnPerformMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<SpaceBard> bards = new ArrayList<SpaceBard>();
				for (CrewMember crewMember : game.getCrew().getCrewMembers()) {
					if (crewMember instanceof SpaceBard) bards.add((SpaceBard)crewMember);
				}
				SelectListItemDialog<SpaceBard> dialog = new SelectListItemDialog<SpaceBard>(parent, bards, "Select Space Bard");
				dialog.setVisible(true);
				if (dialog.getStatusCode() == 0) {
					JOptionPane.showMessageDialog(parent,
							((SpaceBard)dialog.getSelectedItem()).performMusic(game.getCrew().getCrewMembers()),
							"Perform Music", JOptionPane.INFORMATION_MESSAGE);
					refreshDialog();
				}
				dialog.dispose();
			}
		});
		btnPerformMusic.setBounds(304, 147, 100, 100);
		pnlCrewControls.add(btnPerformMusic);
		
		JLabel lblClassActions = new JLabel("Crew Actions:");
		lblClassActions.setBounds(304, 11, 210, 14);
		pnlCrewControls.add(lblClassActions);
		
		JButton btnRepairShip = new JButton("<html><center>Repair Ship</center></html>");
		btnRepairShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectListItemDialog<CrewMember> dialog = new SelectListItemDialog<CrewMember>(parent,
						game.getCrew().getCrewMembers(), "Select Crew Member");
				dialog.setVisible(true);
				if (dialog.getStatusCode() == 0) {
					JOptionPane.showMessageDialog(parent, dialog.getSelectedItem().repairShip(game.getCrew().getShip()),
							"Repair Ship", JOptionPane.INFORMATION_MESSAGE);
					refreshDialog();
				}
				dialog.dispose();
			}
		});
		btnRepairShip.setBounds(10, 258, 100, 100);
		pnlCrewControls.add(btnRepairShip);
		
		JButton btnHealCrewMember = new JButton("<html><center>Heal Crew</center></html>");
		btnHealCrewMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: heal crew
			}
		});
		btnHealCrewMember.setBounds(414, 36, 100, 94);
		pnlCrewControls.add(btnHealCrewMember);
		
		JButton btnCurePlague = new JButton("<html><center>Cure Plague</center></html>");
		btnCurePlague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: cure plague
			}
		});
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

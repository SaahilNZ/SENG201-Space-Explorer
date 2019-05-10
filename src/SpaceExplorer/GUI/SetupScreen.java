package SpaceExplorer.GUI;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;

import SpaceExplorer.Crew;
import SpaceExplorer.Game;
import SpaceExplorer.Ship;
import SpaceExplorer.CrewMembers.*;
import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

import java.awt.FlowLayout;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.Box;

public class SetupScreen extends JDialog {

	private JSlider sldDays;
	private JTextField txtCrewName;
	private JList<CrewMember> lstCrewList;
	private DefaultListModel<CrewMember> crewListModel;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnConfirm;
	
	private Crew crew;
	private JTextField txtShipName;
	
	/**
	 * Create the dialog.
	 */
	public SetupScreen(JFrame parent) {
		super(parent, true);
		setTitle("Game Setup");
		setResizable(false);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlHeader = new JPanel();
			getContentPane().add(pnlHeader, BorderLayout.NORTH);
			{
				JLabel lblGameSetup = new JLabel("Game Setup");
				lblGameSetup.setFont(new Font("Tahoma", Font.PLAIN, 32));
				pnlHeader.add(lblGameSetup);
			}
			
			JPanel pnlContent = new JPanel();
			getContentPane().add(pnlContent, BorderLayout.CENTER);
			pnlContent.setLayout(new BorderLayout(0, 0));
			{
				JPanel pnlVerticalContent = new JPanel();
				pnlContent.add(pnlVerticalContent, BorderLayout.NORTH);
				pnlVerticalContent.setLayout(new BoxLayout(pnlVerticalContent, BoxLayout.Y_AXIS));
				{
					JPanel pnlDays = new JPanel();
					pnlVerticalContent.add(pnlDays);
					FlowLayout flDays = (FlowLayout) pnlDays.getLayout();
					flDays.setAlignment(FlowLayout.LEFT);
					{
						JLabel lblDays = new JLabel("Number of Days:");
						lblDays.setHorizontalAlignment(SwingConstants.LEFT);
						sldDays = new JSlider();
						sldDays.setMajorTickSpacing(1);
						sldDays.setSnapToTicks(true);
						sldDays.setPaintTicks(true);
						sldDays.setPaintLabels(true);
						sldDays.setValue(3);
						sldDays.setMinimum(3);
						sldDays.setMaximum(10);

						pnlDays.add(lblDays);
						pnlDays.add(sldDays);
					}
					
					JPanel pnlCrewName = new JPanel();
					FlowLayout flCrewName = (FlowLayout) pnlCrewName.getLayout();
					flCrewName.setAlignment(FlowLayout.LEFT);
					pnlVerticalContent.add(pnlCrewName);
					{
						JLabel lblCrewName = new JLabel("Crew Name:");
						txtCrewName = new JTextField();
						txtCrewName.setColumns(36);
						
						pnlCrewName.add(lblCrewName);
						pnlCrewName.add(txtCrewName);
					}
				}
				
				JPanel pnlShipName = new JPanel();
				FlowLayout flowLayout_1 = (FlowLayout) pnlShipName.getLayout();
				flowLayout_1.setAlignment(FlowLayout.LEFT);
				pnlVerticalContent.add(pnlShipName);
				
				JLabel lblShipName = new JLabel("Ship Name:");
				pnlShipName.add(lblShipName);
				
				txtShipName = new JTextField();
				pnlShipName.add(txtShipName);
				txtShipName.setColumns(36);
				
				JPanel pnlCrewMemberHeader = new JPanel();
				pnlVerticalContent.add(pnlCrewMemberHeader);
				FlowLayout flowLayout = (FlowLayout) pnlCrewMemberHeader.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				
				JLabel lblCrewMembers = new JLabel("Crew Members:");
				pnlCrewMemberHeader.add(lblCrewMembers);
			}
			
			JPanel pnlCrewMembers = new JPanel();
			pnlContent.add(pnlCrewMembers, BorderLayout.CENTER);
			pnlCrewMembers.setLayout(new BorderLayout(0, 0));
			
			JPanel pnlCrewList = new JPanel();
			pnlCrewMembers.add(pnlCrewList, BorderLayout.CENTER);
			pnlCrewList.setLayout(new BorderLayout(0, 0));
			
			crewListModel = new DefaultListModel<CrewMember>();
			lstCrewList = new JList<>(crewListModel);
			lstCrewList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					btnRemove.setEnabled(lstCrewList.getSelectedIndex() != -1);
				}
			});
			lstCrewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			pnlCrewList.add(lstCrewList);
			
			JPanel pnlListControls = new JPanel();
			pnlCrewMembers.add(pnlListControls, BorderLayout.EAST);
			pnlListControls.setLayout(new BoxLayout(pnlListControls, BoxLayout.X_AXIS));
			
			JPanel pnlListButtons = new JPanel();
			pnlListControls.add(pnlListButtons);
			pnlListButtons.setLayout(new BoxLayout(pnlListButtons, BoxLayout.Y_AXIS));
			
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CreateCrewMemberDialog dialog = new CreateCrewMemberDialog(parent);
					dialog.setVisible(true);
					CrewMember crewMember = null;
					switch (dialog.getCrewMemberType()) {
					case "Space Marine":
						crewMember = new SpaceMarine(dialog.getCrewMemberName());
						break;
					case "Scout":
						crewMember = new Scout(dialog.getCrewMemberName());
						break;
					case "Mechanic":
						crewMember = new Mechanic(dialog.getCrewMemberName());
						break;
					case "Doctor":
						crewMember = new Doctor(dialog.getCrewMemberName());
						break;
					case "Chef":
						crewMember = new Chef(dialog.getCrewMemberName());
						break;
					case "Space Bard":
						crewMember = new SpaceBard(dialog.getCrewMemberName());
						break;
					}
					if (crewMember != null) {						
						crewListModel.addElement(crewMember);
						int crewCount = crewListModel.size();
						btnConfirm.setEnabled(crewCount >= 2);
						if (crewCount >= 4) {
							btnAdd.setEnabled(false);
						}
					}
				}
			});
			btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
			pnlListButtons.add(btnAdd);
			
			btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					crewListModel.remove(lstCrewList.getSelectedIndex());
					lstCrewList.clearSelection();
					if (crewListModel.size() < 4) {
						btnAdd.setEnabled(true);
					}
				}
			});
			
			Component verticalStrut = Box.createVerticalStrut(20);
			pnlListButtons.add(verticalStrut);
			btnRemove.setEnabled(false);
			btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
			pnlListButtons.add(btnRemove);
			
			JPanel pnlFooter = new JPanel();
			getContentPane().add(pnlFooter, BorderLayout.SOUTH);
			{				
				btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtCrewName.getText().length() > 0) {
							if (txtShipName.getText().length() > 0) {
								Ship ship = new Ship(txtShipName.getText(), 200, 200);
								ArrayList<CrewMember> crewMembers = new ArrayList<CrewMember>();
								for (Object crewMember : crewListModel.toArray()) {
									crewMembers.add((CrewMember)crewMember);
								}
								crew = new Crew(crewMembers, txtCrewName.getText(), ship, Game.STARTING_MONEY, null);
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(parent, "Please name your ship.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(parent, "Please name your crew.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnConfirm.setEnabled(false);
				pnlFooter.add(btnConfirm);
			}
		}
	}
	
	public Crew getCrew() {
		return this.crew;
	}
	
	public int getDays() {
		return sldDays.getValue();
	}
}

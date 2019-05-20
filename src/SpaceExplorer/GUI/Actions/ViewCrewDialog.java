package SpaceExplorer.GUI.Actions;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SpaceExplorer.Crew;
import SpaceExplorer.Item;
import SpaceExplorer.CrewMembers.CrewMember;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import javax.swing.event.ListSelectionEvent;

/**
 * This class implements a GUI dialog used to view the crew's details
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 */
public class ViewCrewDialog extends JDialog {

	private final JPanel pnlContent = new JPanel();
	private JList<Item> lstInventory;
	private JButton btnItemDetails;
	private JList<CrewMember> lstCrewMembers;
	private JButton btnCrewMemberDetails;

	/**
	 * Create the dialog.
	 */
	public ViewCrewDialog(JFrame parent, Crew crew) {
		super(parent, true);
		setResizable(false);
		setTitle("Crew");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout());
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlVerticalContent = new JPanel();
			pnlContent.add(pnlVerticalContent, BorderLayout.NORTH);
			pnlVerticalContent.setLayout(new BoxLayout(pnlVerticalContent, BoxLayout.Y_AXIS));
			{
				JPanel pnlCrewName = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlCrewName.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnlVerticalContent.add(pnlCrewName);
				{
					JLabel lblCrewName = new JLabel(crew.getCrewName());
					lblCrewName.setFont(new Font("Tahoma", Font.PLAIN, 20));
					pnlCrewName.add(lblCrewName);
				}
			}
			{
				JPanel pnlMoney = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlMoney.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnlVerticalContent.add(pnlMoney);
				{
					JLabel lblMoney = new JLabel("Money: $" + crew.currentMoney());
					pnlMoney.add(lblMoney);
				}
			}
		}
		{
			JSplitPane spMainContent = new JSplitPane();
			pnlContent.add(spMainContent, BorderLayout.CENTER);
			spMainContent.setResizeWeight(0.5);
			{
				JPanel pnlInventory = new JPanel();
				spMainContent.setLeftComponent(pnlInventory);
				pnlInventory.setLayout(new BorderLayout(0, 0));
				{
					DefaultListModel<Item> inventoryItemModel = new DefaultListModel<Item>();
					for (Item item : crew.getItems()) {
						inventoryItemModel.addElement(item);
					}
					lstInventory = new JList<>();
					lstInventory.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent arg0) {
							btnItemDetails.setEnabled(lstInventory.getSelectedIndex() != -1);
						}
					});
					lstInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					lstInventory.setModel(inventoryItemModel);
					pnlInventory.add(lstInventory);
				}
				{
					btnItemDetails = new JButton("Details");
					btnItemDetails.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							ItemDetails details = new ItemDetails(parent, lstInventory.getSelectedValue());
							details.setVisible(true);
						}
					});
					btnItemDetails.setEnabled(false);
					pnlInventory.add(btnItemDetails, BorderLayout.SOUTH);
				}
				{
					JLabel lblInventory = new JLabel("Inventory:");
					pnlInventory.add(lblInventory, BorderLayout.NORTH);
				}
			}
			{
				JPanel pnlCrewMembers = new JPanel();
				spMainContent.setRightComponent(pnlCrewMembers);
				pnlCrewMembers.setLayout(new BorderLayout(0, 0));
				{
					DefaultListModel<CrewMember> crewMemberModel = new DefaultListModel<CrewMember>();
					for (CrewMember crewMember: crew.getCrewMembers()) {
						crewMemberModel.addElement(crewMember);
					}
					lstCrewMembers = new JList<>();
					lstCrewMembers.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e) {
							btnCrewMemberDetails.setEnabled(lstCrewMembers.getSelectedIndex() != -1);
						}
					});
					lstCrewMembers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					lstCrewMembers.setModel(crewMemberModel);
					pnlCrewMembers.add(lstCrewMembers, BorderLayout.CENTER);
				}
				{
					btnCrewMemberDetails = new JButton("Details");
					btnCrewMemberDetails.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							CrewMemberDetails details = new CrewMemberDetails(parent, lstCrewMembers.getSelectedValue());
							details.setVisible(true);
						}
					});
					btnCrewMemberDetails.setEnabled(false);
					pnlCrewMembers.add(btnCrewMemberDetails, BorderLayout.SOUTH);
				}
				{
					JLabel lblCrewMembers = new JLabel("Crew Members:");
					pnlCrewMembers.add(lblCrewMembers, BorderLayout.NORTH);
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
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				btnClose.setActionCommand("Cancel");
				pnlDialogButtons.add(btnClose);
			}
		}
	}

}

package SpaceExplorer.GUI.Actions;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.event.ListSelectionListener;

import SpaceExplorer.Crew;
import SpaceExplorer.FoodItem;
import SpaceExplorer.Item;
import SpaceExplorer.MedicalItem;
import SpaceExplorer.Outpost;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class VisitOutpostDialog extends JDialog {

	private final JPanel pnlContent = new JPanel();
	private JButton btnBuy;
	private JList<Item> lstOutpostItems;
	private DefaultListModel<Item> outpostItemsModel;
	private JList<Item> lstCrewItems;
	private DefaultListModel<Item> crewItemsModel;
	private JTextPane txtpnItemInfo;
	private JTextPane txtpnCrewItemInfo;
	private JLabel lblItemName;
	private JLabel lblCrewItemName;
	private JLabel lblMoney;
	
	private Crew crew;
	
	/**
	 * Create the dialog.
	 */
	public VisitOutpostDialog(JFrame parent, Outpost outpost, Crew crew) {
		super(parent, true);
		this.crew = crew;
		setTitle("Outpost");
		setResizable(false);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout());
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));
		

		outpostItemsModel = new DefaultListModel<Item>();
		for (Item item : outpost.getInventory()) {
			outpostItemsModel.addElement(item);
		}
		
		crewItemsModel = new DefaultListModel<Item>();
		for (Item item : crew.getItems()) {
			crewItemsModel.addElement(item);
		}
		
		{
			JPanel pnlHeaderContent = new JPanel();
			pnlContent.add(pnlHeaderContent, BorderLayout.NORTH);
			pnlHeaderContent.setLayout(new BoxLayout(pnlHeaderContent, BoxLayout.Y_AXIS));
			{
				JPanel pnlOutpostName = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlOutpostName.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnlHeaderContent.add(pnlOutpostName);
				{
					JLabel lblOutpostName = new JLabel(outpost.getName());
					pnlOutpostName.add(lblOutpostName);
					lblOutpostName.setFont(new Font("Tahoma", Font.PLAIN, 20));
				}
			}
			{
				JPanel pnlMoney = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlMoney.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				pnlHeaderContent.add(pnlMoney);
				{
					lblMoney = new JLabel("Money: $" + crew.currentMoney());
					pnlMoney.add(lblMoney);
				}
			}
		}
		{
			JTabbedPane tabsInventories = new JTabbedPane(JTabbedPane.TOP);
			pnlContent.add(tabsInventories, BorderLayout.CENTER);
			JSplitPane spOutpostItems = new JSplitPane();
			tabsInventories.addTab("Outpost Inventory", null, spOutpostItems, null);
			spOutpostItems.setResizeWeight(0.5);
			{
				JPanel pnlItemInformation = new JPanel();
				spOutpostItems.setRightComponent(pnlItemInformation);
				pnlItemInformation.setLayout(new BorderLayout(0, 0));
				{
					JPanel pnlItemName = new JPanel();
					pnlItemInformation.add(pnlItemName, BorderLayout.NORTH);
					FlowLayout flowLayout = (FlowLayout) pnlItemName.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					{
						lblItemName = new JLabel("");
						pnlItemName.add(lblItemName);
					}
				}
				{
					txtpnItemInfo = new JTextPane();
					txtpnItemInfo.setText("");
					txtpnItemInfo.setBackground(SystemColor.control);
					pnlItemInformation.add(txtpnItemInfo, BorderLayout.CENTER);
				}
				{
					btnBuy = new JButton("Buy");
					btnBuy.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Item item = lstOutpostItems.getSelectedValue();
							if (crew.currentMoney() >= item.getPrice()) {
								crew.deductMoney(item.getPrice());
								Item newItem = item.createCopy();
								crew.addItem(newItem);
								outpost.removeItem(item);
								outpostItemsModel.removeElement(item);
								crewItemsModel.addElement(item);
								lstOutpostItems.clearSelection();
								refreshInfo();
							} else {
								JOptionPane.showMessageDialog(parent, "You cannot afford this item.",
										"Not enough money", JOptionPane.ERROR_MESSAGE);
							}
						}
					});
					btnBuy.setEnabled(false);
					pnlItemInformation.add(btnBuy, BorderLayout.SOUTH);
				}
			}
			lstOutpostItems = new JList<Item>();
			lstOutpostItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lstOutpostItems.setModel(outpostItemsModel);
			lstOutpostItems.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					btnBuy.setEnabled(lstOutpostItems.getSelectedIndex() != -1);
					if (lstOutpostItems.getSelectedIndex() == -1) {
						lblItemName.setText("");
						txtpnItemInfo.setText("");
					} else {
						Item item = lstOutpostItems.getSelectedValue();
						lblItemName.setText(item.getName());
						String itemInfo = "";
						if (item instanceof MedicalItem) {
							itemInfo += "Medical Item\n";
							itemInfo += "Restores " + ((MedicalItem)item).getRestoreAmount() + " points of health.\n";
							itemInfo += ((MedicalItem)item).curesPlague() ? "This item cures Space Plague.\n"
									: "This item does not cure Space Plague.\n";
						} else if (item instanceof FoodItem) {
							itemInfo += "Food Item\n";
							itemInfo += "Restores " + ((FoodItem)item).getHungerAmount() + " points of hunger.\n";
							itemInfo += "Restores " + ((FoodItem)item).getTiredAmount() + " points of exhaustion.\n";
						}
						itemInfo += "Price: $" + item.getPrice();
						txtpnItemInfo.setText(itemInfo);
					}
				}
			});
			spOutpostItems.setLeftComponent(lstOutpostItems);
			{
				JSplitPane spCrewInventory = new JSplitPane();
				spCrewInventory.setResizeWeight(0.5);
				tabsInventories.addTab("Crew Inventory", null, spCrewInventory, null);
				{
					lstCrewItems = new JList<Item>();
					lstCrewItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					lstCrewItems.setModel(crewItemsModel);
					lstCrewItems.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e) {
							if (lstCrewItems.getSelectedIndex() == -1) {
								lblCrewItemName.setText("");
								txtpnCrewItemInfo.setText("");
							} else {
								Item item = lstCrewItems.getSelectedValue();
								lblCrewItemName.setText(item.getName());
								String itemInfo = "";
								if (item instanceof MedicalItem) {
									itemInfo += "Medical Item\n";
									itemInfo += "Restores " + ((MedicalItem)item).getRestoreAmount() + " points of health.\n";
									itemInfo += ((MedicalItem)item).curesPlague() ? "This item cures Space Plague.\n"
											: "This item does not cure Space Plague.\n";
								} else if (item instanceof FoodItem) {
									itemInfo += "Food Item\n";
									itemInfo += "Restores " + ((FoodItem)item).getHungerAmount() + " points of hunger.\n";
									itemInfo += "Restores " + ((FoodItem)item).getTiredAmount() + " points of exhaustion.\n";
								}
								itemInfo += "Price: $" + item.getPrice();
								txtpnCrewItemInfo.setText(itemInfo);
							}
							refreshInfo();
						}
					});
					spCrewInventory.setLeftComponent(lstCrewItems);
				}
				{
					JPanel pnlItemInformation = new JPanel();
					spCrewInventory.setRightComponent(pnlItemInformation);
					pnlItemInformation.setLayout(new BorderLayout(0, 0));
					{
						JPanel pnlItemName = new JPanel();
						FlowLayout flowLayout = (FlowLayout) pnlItemName.getLayout();
						flowLayout.setAlignment(FlowLayout.LEFT);
						pnlItemInformation.add(pnlItemName, BorderLayout.NORTH);
						{
							lblCrewItemName = new JLabel("");
							pnlItemName.add(lblCrewItemName);
						}
					}
					{
						txtpnCrewItemInfo = new JTextPane();
						txtpnCrewItemInfo.setText("");
						txtpnCrewItemInfo.setBackground(SystemColor.menu);
						pnlItemInformation.add(txtpnCrewItemInfo, BorderLayout.CENTER);
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
				btnClose.setActionCommand("Cancel");
				pnlDialogButtons.add(btnClose);
			}
		}
	}
	
	private void refreshInfo() {
		lblMoney.setText("Money: $" + crew.currentMoney());
		pnlContent.invalidate();
		pnlContent.validate();
		pnlContent.repaint();
	}
}

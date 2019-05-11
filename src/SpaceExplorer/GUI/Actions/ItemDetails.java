package SpaceExplorer.GUI.Actions;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SpaceExplorer.FoodItem;
import SpaceExplorer.Item;
import SpaceExplorer.MedicalItem;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemDetails extends JDialog {

	private final JPanel pnlContent = new JPanel();


	/**
	 * Create the dialog.
	 */
	public ItemDetails(JFrame parent, Item item) {
		super(parent, true);
		setResizable(false);
		setBounds(100, 100, 480, 240);
		getContentPane().setLayout(new BorderLayout());
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlVerticalContent = new JPanel();
			pnlContent.add(pnlVerticalContent, BorderLayout.NORTH);
			pnlVerticalContent.setLayout(new BoxLayout(pnlVerticalContent, BoxLayout.Y_AXIS));
			{
				JPanel pnlItemName = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlItemName.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnlVerticalContent.add(pnlItemName);
				{
					JLabel lblItemName = new JLabel(item.getName());
					lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 20));
					pnlItemName.add(lblItemName);
				}
			}
			{
				JPanel pnlItemType = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlItemType.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnlVerticalContent.add(pnlItemType);
				{
					String itemType = "";
					if (item instanceof MedicalItem) { 
						itemType = "Item Type: Medical Item";
					} else if (item instanceof FoodItem) {
						itemType = "Item Type: Food Item";
					}
					JLabel lblItemType = new JLabel(itemType);
					pnlItemType.add(lblItemType);
				}
			}
			{
				JPanel pnlRestoreAmount = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlRestoreAmount.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnlVerticalContent.add(pnlRestoreAmount);
				{
					String restoreAmount = "";
					if (item instanceof MedicalItem) { 
						restoreAmount = "Restores " + ((MedicalItem)item).getRestoreAmount() + " points of health.";
					} else if (item instanceof FoodItem) {
						restoreAmount = "Restores " + ((FoodItem)item).getHungerAmount() + " points of hunger.";
					}
					JLabel lblRestoreAmount = new JLabel(restoreAmount);
					pnlRestoreAmount.add(lblRestoreAmount);
				}
			}
			{
				JPanel pnlSpacePlague = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlSpacePlague.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnlVerticalContent.add(pnlSpacePlague);
				{
					if (item instanceof MedicalItem) {
						JLabel lblThisItemCures = new JLabel(((MedicalItem)item).curesPlague() ?
								"This item cures Space Plague." : "This item does not cure Space Plague.");
						pnlSpacePlague.add(lblThisItemCures);
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

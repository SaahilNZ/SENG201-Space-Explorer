package SpaceExplorer.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SpaceExplorer.CrewMembers.CrewMember;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;

public class CrewMemberDetails extends JDialog {

	private final JPanel pnlContent = new JPanel();
	private JLabel lblCrewMemberName;
	private JLabel lblHealthText;
	private JLabel lblHungerText;
	private JLabel lblTirednessText;
	
	// To refresh the dialog, use:
	// frame.invalidate();
	// frame.validate();
	// frame.repaint();

	/**
	 * Create the dialog.
	 */
	public CrewMemberDetails(CrewMember crewMember) {
		setTitle(crewMember.getName());
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel pnlHeader = new JPanel();
			getContentPane().add(pnlHeader, BorderLayout.NORTH);
			{
				lblCrewMemberName = new JLabel(crewMember.getName());
				pnlHeader.add(lblCrewMemberName);
				lblCrewMemberName.setFont(new Font("Tahoma", Font.PLAIN, 22));
			}
		}
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		{
			pnlContent.setLayout(new BorderLayout(0, 0));
		}
		{
			JPanel pnlVerticalContent = new JPanel();
			pnlContent.add(pnlVerticalContent, BorderLayout.NORTH);
			pnlVerticalContent.setLayout(new BoxLayout(pnlVerticalContent, BoxLayout.Y_AXIS));
			{
				{					
					JPanel pnlHealth = new JPanel();
					pnlVerticalContent.add(pnlHealth);
					pnlHealth.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					JLabel lblHealth = new JLabel("Health:");
					lblHealthText = new JLabel(crewMember.getHealth() + "/" + crewMember.getMaxHealth());
					{					
						pnlHealth.add(lblHealth);
						pnlHealth.add(lblHealthText);
					}
				}
				{
					JPanel pnlHunger = new JPanel();
					pnlVerticalContent.add(pnlHunger);
					pnlHunger.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					JLabel lblHunger = new JLabel("Hunger:");
					lblHungerText = new JLabel(crewMember.getHunger() + "/" + crewMember.getMaxHunger());
					{
						pnlHunger.add(lblHunger);
						pnlHunger.add(lblHungerText);
					}
				}
				{
					JPanel pnlTiredness = new JPanel();
					pnlVerticalContent.add(pnlTiredness);
					pnlTiredness.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					JLabel lblTiredness = new JLabel("Tiredness:");
					lblTirednessText = new JLabel(crewMember.getTiredness() + "/" + crewMember.getMaxTiredness());
					{
						pnlTiredness.add(lblTiredness);
						pnlTiredness.add(lblTirednessText);
					}
				}
			}
		}
		{
			JPanel pnlDialogButtons = new JPanel();
			pnlDialogButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnlDialogButtons, BorderLayout.SOUTH);
			{
				JButton btnOkay = new JButton("OK");
				btnOkay.setActionCommand("OK");
				pnlDialogButtons.add(btnOkay);
				getRootPane().setDefaultButton(btnOkay);
			}
		}
	}

}

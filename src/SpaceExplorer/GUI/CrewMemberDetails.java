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
				lblCrewMemberName.setFont(new Font("Tahoma", Font.PLAIN, 32));
			}
		}
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		{
			pnlContent.setLayout(new BorderLayout(0, 0));
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
						lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 24));
						lblHealthText = new JLabel(crewMember.getHealth() + "/" + crewMember.getMaxHealth());
						lblHealthText.setFont(new Font("Tahoma", Font.PLAIN, 24));
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
						lblHunger.setFont(new Font("Tahoma", Font.PLAIN, 24));
						lblHungerText = new JLabel(crewMember.getHunger() + "/" + crewMember.getMaxHunger());
						lblHungerText.setFont(new Font("Tahoma", Font.PLAIN, 24));
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
						lblTiredness.setFont(new Font("Tahoma", Font.PLAIN, 24));
						lblTirednessText = new JLabel(crewMember.getTiredness() + "/" + crewMember.getMaxTiredness());
						lblTirednessText.setFont(new Font("Tahoma", Font.PLAIN, 24));
						{
							pnlTiredness.add(lblTiredness);
							pnlTiredness.add(lblTirednessText);
						}
					}
					{
						JPanel pnlPlague = new JPanel();
						pnlVerticalContent.add(pnlPlague);
						pnlPlague.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
						JLabel lblPlague = new JLabel(crewMember.hasPlague() ? "Infected with the space plague." : "");
						lblPlague.setFont(new Font("Tahoma", Font.PLAIN, 24));
						{
							pnlPlague.add(lblPlague);
						}
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

package SpaceExplorer.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;

public class CreateCrewMemberDialog extends JDialog {

	private final JPanel pnlContent = new JPanel();
	private JTextField txtCrewMemberName;

	/**
	 * Create the dialog.
	 */
	public CreateCrewMemberDialog() {
		setTitle("Create Crew Member");
		setResizable(false);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout());
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlCrewMemberName = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlCrewMemberName.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlContent.add(pnlCrewMemberName, BorderLayout.NORTH);
			{
				JLabel lblCrewMemberName = new JLabel("Crew Member Name:");
				pnlCrewMemberName.add(lblCrewMemberName);
			}
			{
				txtCrewMemberName = new JTextField();
				pnlCrewMemberName.add(txtCrewMemberName);
				txtCrewMemberName.setColumns(30);
			}
		}
		{
			JSplitPane spCrewTypes = new JSplitPane();
			pnlContent.add(spCrewTypes, BorderLayout.CENTER);
			{
				JPanel pnlSplitLeft = new JPanel();
				spCrewTypes.setLeftComponent(pnlSplitLeft);
				pnlSplitLeft.setLayout(new BoxLayout(pnlSplitLeft, BoxLayout.Y_AXIS));
				{
					JRadioButton rbSpaceMarine = new JRadioButton("Space Marine");
					rbSpaceMarine.setSelected(true);
					pnlSplitLeft.add(rbSpaceMarine);
				}
				{
					JRadioButton rbScout = new JRadioButton("Scout");
					pnlSplitLeft.add(rbScout);
				}
				{
					JRadioButton rbMechanic = new JRadioButton("Mechanic");
					pnlSplitLeft.add(rbMechanic);
				}
				{
					JRadioButton rbDoctor = new JRadioButton("Doctor");
					pnlSplitLeft.add(rbDoctor);
				}
				{
					JRadioButton rbChef = new JRadioButton("Chef");
					pnlSplitLeft.add(rbChef);
				}
				{
					JRadioButton rbSpaceBard = new JRadioButton("Space Bard");
					pnlSplitLeft.add(rbSpaceBard);
				}
			}
			{
				JPanel pnlSplitRight = new JPanel();
				spCrewTypes.setRightComponent(pnlSplitRight);
				pnlSplitRight.setLayout(new BorderLayout(0, 0));
				{
					JPanel pnlTypeInformation = new JPanel();
					pnlSplitRight.add(pnlTypeInformation, BorderLayout.NORTH);
					pnlTypeInformation.setLayout(new BoxLayout(pnlTypeInformation, BoxLayout.Y_AXIS));
					{
						JTextPane txtpnCrewTypeDescription = new JTextPane();
						txtpnCrewTypeDescription.setBackground(SystemColor.control);
						txtpnCrewTypeDescription.setText("Crew Type\r\n\r\nDescription\r\n\r\nStats:\r\nHealth: 100\r\nHunger: 100\r\nTiredness: 100\r\n\r\nSpecial ability:\r\nSpecial ability details");
						txtpnCrewTypeDescription.setEditable(false);
						pnlTypeInformation.add(txtpnCrewTypeDescription);
					}
				}
			}
		}
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				pnlButtons.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				pnlButtons.add(cancelButton);
			}
		}
	}

}

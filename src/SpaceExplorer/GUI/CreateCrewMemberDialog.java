package SpaceExplorer.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * This class implements a GUI dialog used to create crew members
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 */
public class CreateCrewMemberDialog extends JDialog implements ActionListener {
	private static final HashMap<String, String> CREW_INFORMATION = new HashMap<String, String>() {
		{
			String spaceMarine = "";
			spaceMarine += "Space Marine\n";
			spaceMarine += "A battle-hardened soldier.\n\n";
			spaceMarine += "Stats:\n";
			spaceMarine += "Max Health: 140\n";
			spaceMarine += "Max Hunger: 140\n";
			spaceMarine += "Max Tiredness: 140\n";
			spaceMarine += "\n";
			spaceMarine += "Special ability:\n";
			spaceMarine += "Lowers the chance that a pirate attack will occur.\n";
			put("Space Marine", spaceMarine);

			String scout = "";
			scout += "Scout:\n";
			scout += "An individual trained in reconnaissance.\n\n";
			scout += "Stats:\n";
			scout += "Max Health: 110\n";
			scout += "Max Hunger: 100\n";
			scout += "Max Tiredness: 120\n";
			scout += "\n";
			scout += "Special ability:\n";
			scout += "Searching planets can give up to two items instead of one.\n";
			put("Scout", scout);

			String mechanic = "";
			mechanic += "Mechanic:\n";
			mechanic += "Skilled at making repairs.\n\n";
			mechanic += "Stats:\n";
			mechanic += "Max Health: 100\n";
			mechanic += "Max Hunger: 100\n";
			mechanic += "Max Tiredness: 100\n";
			mechanic += "\n";
			mechanic += "Special ability:\n";
			mechanic += "Repairing the ship restores twice as much health.\n";
			put("Mechanic", mechanic);

			String doctor = "";
			doctor += "Doctor:\n";
			doctor += "Saves lives.\n\n";
			doctor += "Stats:\n";
			doctor += "Max Health: 80\n";
			doctor += "Max Hunger: 100\n";
			doctor += "Max Tiredness: 100\n";
			doctor += "\n";
			doctor += "Special ability:\n";
			doctor += "Can heal allies and cure plague without the use of items.\n";
			put("Doctor", doctor);

			String chef = "";
			chef += "Chef:\n";
			chef += "Proficient in turning ingredients into meals.\n\n";
			chef += "Stats:\n";
			chef += "Max Health: 100\n";
			chef += "Max Hunger: 120\n";
			chef += "Max Tiredness: 100\n";
			chef += "\n";
			chef += "Special ability:\n";
			chef += "Can cook food to make it more effective.\n";
			put("Chef", chef);

			String spaceBard = "";
			spaceBard += "Space Bard:\n";
			spaceBard += "The best musician on this side of the galaxy.\n\n";
			spaceBard += "Stats:\n";
			spaceBard += "Max Health: 90\n";
			spaceBard += "Max Hunger: 100\n";
			spaceBard += "Max Tiredness: 90\n";
			spaceBard += "\n";
			spaceBard += "Special ability:\n";
			spaceBard += "Can perform music to lower the tiredness of other crew members.\n";
			put("Space Bard", spaceBard);
		}
	};

	private JFrame parent;
	private final JPanel pnlContent = new JPanel();
	private JTextField txtCrewMemberName;
	private JTextPane txtpnCrewTypeDescription;
	private JButton okButton;
	private String selectedCrewType = "Space Marine";
	private int statusCode = -1;

	/**
	 * Create the dialog.
	 */
	public CreateCrewMemberDialog(JFrame parent) {
		super(parent, true);
		this.parent = parent;
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
				ButtonGroup crewTypes = new ButtonGroup();
				pnlSplitLeft.setLayout(new BoxLayout(pnlSplitLeft, BoxLayout.Y_AXIS));
				{
					JRadioButton rbSpaceMarine = new JRadioButton("Space Marine");
					rbSpaceMarine.setActionCommand("Space Marine");
					rbSpaceMarine.addActionListener(this);
					crewTypes.add(rbSpaceMarine);
					rbSpaceMarine.setSelected(true);
					pnlSplitLeft.add(rbSpaceMarine);
				}
				{
					JRadioButton rbScout = new JRadioButton("Scout");
					rbScout.setActionCommand("Scout");
					rbScout.addActionListener(this);
					crewTypes.add(rbScout);
					pnlSplitLeft.add(rbScout);
				}
				{
					JRadioButton rbMechanic = new JRadioButton("Mechanic");
					rbMechanic.setActionCommand("Mechanic");
					rbMechanic.addActionListener(this);
					crewTypes.add(rbMechanic);
					pnlSplitLeft.add(rbMechanic);
				}
				{
					JRadioButton rbDoctor = new JRadioButton("Doctor");
					rbDoctor.setActionCommand("Doctor");
					rbDoctor.addActionListener(this);
					crewTypes.add(rbDoctor);
					pnlSplitLeft.add(rbDoctor);
				}
				{
					JRadioButton rbChef = new JRadioButton("Chef");
					rbChef.setActionCommand("Chef");
					rbChef.addActionListener(this);
					crewTypes.add(rbChef);
					pnlSplitLeft.add(rbChef);
				}
				{
					JRadioButton rbSpaceBard = new JRadioButton("Space Bard");
					rbSpaceBard.setActionCommand("Space Bard");
					rbSpaceBard.addActionListener(this);
					crewTypes.add(rbSpaceBard);
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
						txtpnCrewTypeDescription = new JTextPane();
						txtpnCrewTypeDescription.setBackground(SystemColor.control);
						txtpnCrewTypeDescription.setText(CREW_INFORMATION.get(selectedCrewType));
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
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				pnlButtons.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				pnlButtons.add(cancelButton);
			}
		}
	}

	public String getCrewMemberName() {
		return txtCrewMemberName.getText();
	}
	
	public String getCrewMemberType() {
		return selectedCrewType;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("OK")) {
			if (txtCrewMemberName.getText().length() > 0) {
				statusCode = 0;
				setVisible(false);	
			} else {
				JOptionPane.showMessageDialog(parent, "Please name your new crew member.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (actionCommand.equals("Cancel")) {
			statusCode = -1;
			setVisible(false);
		} else {			
			selectedCrewType = actionCommand;
			refreshData();
		}
	}
	
	private void refreshData() {
		txtpnCrewTypeDescription.setText(CREW_INFORMATION.get(selectedCrewType));
		pnlContent.invalidate();
		pnlContent.validate();
		pnlContent.repaint();
	}
}

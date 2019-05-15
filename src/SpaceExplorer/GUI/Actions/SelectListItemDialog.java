package SpaceExplorer.GUI.Actions;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class SelectListItemDialog<T> extends JDialog {

	private final JPanel pnlContent = new JPanel();
	private DefaultListModel<T> listModel;
	private JList<T> lstCrewMembers;
	private JButton btnOk;
	
	private T selectedItem;
	private int statusCode = -1;

	/**
	 * Create the dialog.
	 */
	public SelectListItemDialog(JFrame parent, Collection<T> listItems, String title) {
		super(parent, true);
		setTitle(title);
		setResizable(false);
		setBounds(100, 100, 340, 420);
		getContentPane().setLayout(new BorderLayout());
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblSelectCrewMember = new JLabel("Select Crew Member:");
			pnlContent.add(lblSelectCrewMember, BorderLayout.NORTH);
		}
		{
			listModel = new DefaultListModel<T>();
			for (T listItem : listItems) {
				listModel.addElement(listItem);
			}
			lstCrewMembers = new JList<>();
			lstCrewMembers.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					btnOk.setEnabled(lstCrewMembers.getSelectedIndex() != -1);
				}
			});
			lstCrewMembers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lstCrewMembers.setModel(listModel);
			pnlContent.add(lstCrewMembers, BorderLayout.CENTER);
		}
		{
			JPanel pnlDialogButtons = new JPanel();
			pnlDialogButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnlDialogButtons, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						statusCode = 0;
						selectedItem = lstCrewMembers.getSelectedValue();
						setVisible(false);
					}
				});
				btnOk.setEnabled(false);
				btnOk.setActionCommand("OK");
				pnlDialogButtons.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						statusCode = -1;
						setVisible(false);
					}
				});
				btnCancel.setActionCommand("Cancel");
				pnlDialogButtons.add(btnCancel);
			}
		}
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public T getSelectedItem() {
		return selectedItem;
	}
}

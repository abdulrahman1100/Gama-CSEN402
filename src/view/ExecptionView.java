package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class ExecptionView extends JFrame implements ActionListener {
	private JPanel  ds;
	private Label txtMessage;
	

public ExecptionView(String s) {
this.setTitle("Window");
this.setDefaultCloseOperation(HIDE_ON_CLOSE);
this.setBackground(Color.white);
setBounds(350, 370, 500, 200);
this.setResizable(false);
this.setLayout(new GridLayout(0,1));
txtMessage=new Label();
txtMessage.setText(s);
( txtMessage).setAlignment(Label.CENTER);
txtMessage.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
txtMessage.setForeground(Color.RED);
txtMessage.setBackground(Color.WHITE);
this.add(txtMessage);
ds=new JPanel(new FlowLayout());
ds.setBackground(Color.white);
JButton button1 = new JButton("OK");
button1.setBackground(Color.LIGHT_GRAY);
button1.setActionCommand("OK");
button1.addActionListener(this);
ds.add(button1);
this.add(ds);
this.setVisible(true);
}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
             if(btn.getActionCommand()=="OK")
            	 this.setVisible(false);
	}



}

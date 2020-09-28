package Network;

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

import model.game.Player;

@SuppressWarnings("serial")
public class GameOverWindow extends JFrame implements ActionListener {
	private JPanel  ds;
	private Label txtMessage;
	private UDPServer gameview;

public GameOverWindow(Player p,UDPServer gameview,String s) {
	this.gameview=gameview;
this.setTitle("Window");
this.setDefaultCloseOperation(HIDE_ON_CLOSE);
this.setBackground(Color.white);
setBounds(350, 370, 300, 200);
this.setLayout(new GridLayout(0,1));
txtMessage=new Label();
txtMessage.setText(s+p.getName());

	
txtMessage.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));

txtMessage.setForeground(Color.RED);
txtMessage.setBackground(Color.WHITE);
txtMessage.setAlignment(Label.CENTER);

this.add(txtMessage);
ds=new JPanel(new FlowLayout());
ds.setBackground(Color.white);
JButton button2 = new JButton("NewGame");
button2.setBackground(Color.LIGHT_GRAY);
button2.setActionCommand("NewGame");
button2.addActionListener(this);
ds.add(button2);

JButton button1 = new JButton("OK");
button1.setBackground(Color.LIGHT_GRAY);
button1.setActionCommand("OK");
button1.addActionListener(this);
ds.add(button1);

JButton button3 = new JButton("Restart");
button3.setBackground(Color.LIGHT_GRAY);
button3.setActionCommand("Restart");
button3.addActionListener(this);
ds.add(button3);
this.add(ds);
this.setVisible(true);
}

	public GameOverWindow(Player player2, UDPClient udpClient,String s) {
		this.setTitle("Window");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setBackground(Color.white);
		setBounds(350, 370, 300, 200);
		this.setLayout(new GridLayout(0,1));
		txtMessage=new Label();
		txtMessage.setText(s+player2.getName());

			
		txtMessage.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));

		txtMessage.setForeground(Color.RED);
		txtMessage.setBackground(Color.WHITE);
		txtMessage.setAlignment(Label.CENTER);

		this.add(txtMessage);
		ds=new JPanel(new FlowLayout());
		ds.setBackground(Color.white);
		JButton button2 = new JButton("NewGame");
		button2.setBackground(Color.LIGHT_GRAY);
		button2.setActionCommand("NewGame");
		button2.addActionListener(this);
		ds.add(button2);

		JButton button1 = new JButton("OK");
		button1.setBackground(Color.LIGHT_GRAY);
		button1.setActionCommand("OK");
		button1.addActionListener(this);
		ds.add(button1);

		JButton button3 = new JButton("Restart");
		button3.setBackground(Color.LIGHT_GRAY);
		button3.setActionCommand("Restart");
		button3.addActionListener(this);
		ds.add(button3);
		this.add(ds);
		this.setVisible(true);
	// TODO Auto-generated constructor stub
}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
             if(btn.getActionCommand()=="OK")
            	 this.setVisible(false);
             if(btn.getActionCommand()=="NewGame") {
            	 gameview.setVisible(false);
            	
             }
             if(btn.getActionCommand()=="Restart")
          
        	 this.setVisible(false);


	}



}
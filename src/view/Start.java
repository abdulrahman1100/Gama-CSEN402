package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class Start extends JFrame implements ActionListener ,Runnable{
	private JTextField a;
    private JTextField a1;

    GameView gameview;
	public static void main(String[]args) {
		new Start();
	}
	public synchronized void start() {

	}
	public Start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(550, 350, 300, 300);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		JLabel p=new JLabel();
		p.setLayout(new FlowLayout());
		//ImageIcon image= new ImageIcon("Super.jpg");
		//this.setIconImages((List<? extends Image>) new ImageIcon("Super.jpg"));
	     p.setIcon(new ImageIcon("StartBackGround.jpg"));
	    // this.setExtendedState(ERROR);
		Label l=new Label();
		l.setAlignment(Label.CENTER);
			l.setText("Player1's Name:");
			p.add(l);
		 a=new JTextField(20);
		a.setEditable(true);
		p.add(a);
		Label l1=new Label();
		l1.setText("Player2's Name:");
		l1.setAlignment(Label.CENTER);
		p.add(l1);
	   a1=new JTextField(20);
	   a1.setEditable(true);
		p.add(a1);
		p.setBackground(Color.WHITE);
		JButton start=new JButton("Start");
	      start.setActionCommand("Start");
	     start.addActionListener(this);
	      p.add(start);

				
			

	      this.add(p,BorderLayout.CENTER);
	     this.setVisible(true);
	     
	}
	@Override
	public void actionPerformed(ActionEvent e) {
               		if(e.getActionCommand()=="Start") {
               			if(!a.getText().isEmpty()&&!a1.getText().isEmpty()) {
               			
                   		 gameview=	new GameView(a.getText(),a1.getText());
start();

               			}else {
               				new ExecptionView("Enter The players name");
               			}
               			
               		}

               		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

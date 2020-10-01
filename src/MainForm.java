
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javafx.scene.text.Font;

public class MainForm extends JFrame implements ActionListener{
	JTextField field;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	TextField t1,t2,t3,t4,t5;
	JButton take, importfile, performk1, performk2, performk3;
	MainForm(){
		
		
		setLayout(new GridLayout(12,1));
		l1=new JLabel("ANALYSIS ON YOUTUBE VIDEO STATISTICS",SwingConstants.CENTER);
                l1.setFont(new java.awt.Font("Segoe Script", 1, 20)); 
                l1.setForeground(new java.awt.Color(51, 0, 102));
                l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		add(l1);
		l2= new JLabel(" ");
		add(l2);
		l3= new JLabel(" ");
		add(l3);
		importfile = new JButton("Import Dataset");
                importfile.setBackground(new java.awt.Color(255, 255, 255));
                importfile.setFont(new java.awt.Font("Segoe Script", 0, 18)); 
                importfile.setForeground(new java.awt.Color(0, 102, 0));
		add(importfile);
		importfile.addActionListener(this);
		l4= new JLabel(" ");
		add(l4);
		
		
		
		l5= new JLabel("SELECT FROM FOLLOWING ANALYSIS",SwingConstants.CENTER);
                l5.setFont(new java.awt.Font("Segoe Script", 1, 20)); // NOI18N
                l5.setForeground(new java.awt.Color(51, 0, 102));
                l5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		add(l5);
		
		performk1 = new JButton("Based on number of likes");
                performk1.setBackground(new java.awt.Color(255, 255, 255));
                performk1.setFont(new java.awt.Font("Segoe Script", 0, 18)); 
                performk1.setForeground(new java.awt.Color(0, 102, 0));
		add(performk1);
		performk1.addActionListener(this);
		l6= new JLabel(" ");
		add(l6);
		performk2 = new JButton("Based on number of views");
                performk2.setBackground(new java.awt.Color(255, 255, 255));
                performk2.setFont(new java.awt.Font("Segoe Script", 0, 18)); 
                performk2.setForeground(new java.awt.Color(0, 102, 0));
		add(performk2);
		performk2.addActionListener(this);
		l7= new JLabel(" ");
		add(l7);
		performk3 = new JButton("Based on number of comment count");
                performk3.setBackground(new java.awt.Color(255, 255, 255));
                performk3.setFont(new java.awt.Font("Segoe Script", 0, 18)); 
                performk3.setForeground(new java.awt.Color(0, 102, 0));
		add(performk3);
		performk3.addActionListener(this);
		l8= new JLabel(" ");
		add(l8);
		setSize(700,700);
		setVisible(true);
		getContentPane().setBackground(new java.awt.Color(204,255,255));
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainForm m = new MainForm();

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try
		  {
			  
		      if(arg0.getSource()== importfile)
		      {  
		    	   ImportCSV Frame = new ImportCSV();
		    	   Frame.setVisible(true);
		    	  
		       }else if(arg0.getSource()== performk1)
		      {  
		    	   Analysis1 a1 = new Analysis1();
		    	   
		    	  
		       }else if(arg0.getSource()== performk2)
			      {  
		    	   Analysis2 a2 = new Analysis2();
			    	  
			       }else if(arg0.getSource()== performk3)
				      {  
			    	   
			    	   Analysis3 a3 = new Analysis3();
				       }
		  }
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
	}

}

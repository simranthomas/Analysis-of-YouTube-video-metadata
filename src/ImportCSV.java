
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import javax.swing.JLabel;

public class ImportCSV extends JFrame implements ActionListener{
	JButton choose;
	private JTable table;
	String sid;
	
	ImportCSV(){
		setLayout(new GridLayout(10,1));
                JLabel l9= new JLabel(" ");
		add(l9);
		JLabel l10= new JLabel(" ");
		add(l10);
                JLabel l5= new JLabel("CHOOSE CSV FILE");
                l5.setFont(new java.awt.Font("Segoe Script", 1, 20)); 
                l5.setForeground(new java.awt.Color(51, 0, 102));
                l5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		add(l5);
		choose =new JButton("Upload File");
                choose.setBackground(new java.awt.Color(255, 255, 255));
                choose.setFont(new java.awt.Font("Segoe Script", 0, 18)); 
                choose.setForeground(new java.awt.Color(0, 102, 0));
		add(choose);
		choose.addActionListener(this);
		setSize(700,700);
                getContentPane().setBackground(new java.awt.Color(204,255,255));
		
	}
	
	
	public void SaveData(){
		System.out.println("in save data");
		System.out.println("processing file");
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		Session session = cluster.connect("miniproject");
		int i=2;
                for(i = 1; i<table.getRowCount();i++)
			{
			  
			  String video_id = table.getValueAt(i, 0).toString();
			  
	    	  String category_id =table.getValueAt(i, 1).toString();
                  String views = table.getValueAt(i, 2).toString();
	    	  String likes = table.getValueAt(i, 3).toString();
	    	  String dislikes = table.getValueAt(i, 4).toString();
	    	  String comment_count = table.getValueAt(i, 5).toString();
	    	  String comments_disabled = table.getValueAt(i, 6).toString();
	    	  String ratings_disabled = table.getValueAt(i, 7).toString();
	    	  String video_error_or_removed = table.getValueAt(i, 8).toString();
            //      System.out.println(video_id);
            //      System.out.println(views);
            //      System.out.println(likes);
            //      System.out.println(dislikes);
            //      System.out.println(comment_count);
            //      System.out.println(comments_disabled);
            //      System.out.println(ratings_disabled);
            //      System.out.println(video_error_or_removed);
	    	  try
			  {
	    	  String query = "INSERT INTO youtube(video_id, category_id, views,likes,dislikes, comment_count,comments_disabled,ratings_disabled,video_error_or_removed)" +
						"VALUES('"+video_id.toString()+"','"+category_id.toString()+"','"+views.toString()+"','"+likes.toString()+"','"+dislikes.toString()+"','"+comment_count.toString()+"','"+comments_disabled.toString()+"','"+ratings_disabled+"','"+video_error_or_removed+"');";
   	  
	    	  
	    	 

				
				session.execute(query);
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  
			}
		session.close();
  	  	System.out.println("CSV file loaded successfully!");
  	  	JLabel l1= new JLabel(" ");
		add(l1);
		JLabel l2= new JLabel(" ");
		add(l2);
                JLabel l3= new JLabel(" ");
		add(l3);
		JLabel l4= new JLabel("DATSET IMPORTED SUCCESSFULLY!!");
                 l4.setFont(new java.awt.Font("Segoe Script", 1, 20)); 
                l4.setForeground(new java.awt.Color(51, 0, 102));
                l4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		add(l4);
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try
		  {
			  
		      if(arg0.getSource()== choose)
		      {  
		    	  table = new JTable();
		    	  System.out.println("Button clicked");
		    	  final DefaultTableModel model = (DefaultTableModel)table.getModel();
		    	  model.addColumn("video_id");
		    	  model.addColumn("category_id");
		    	  model.addColumn("views");
		    	  model.addColumn("likes");
		    	  model.addColumn("dislikes");
		    	  model.addColumn("comment_count");
                  model.addColumn("comments_disabled");
                  model.addColumn("ratings_disabled");
                  model.addColumn("video_error_or_removed");
		    	  System.out.println("model created");
		    	  
		    	  JFileChooser fileopen = new JFileChooser();
		    	  
		    	  FileFilter filter = new FileNameExtensionFilter("Text/CSV file", "txt", "csv");
		    	  System.out.println("above filter");
		    	  fileopen.addChoosableFileFilter(filter);
		    	  int ret = fileopen.showDialog(null, "Choose file");

		    	  if (ret == JFileChooser.APPROVE_OPTION) {

		    	  File file = fileopen.getSelectedFile();
		    	  System.out.println(file.getName());
		    	  try {
		    		  System.out.println("fileselected");
		    		  BufferedReader br = new BufferedReader(new FileReader(file));
		    		  String line;
		    		  int row = 0;
		    		  while ((line = br.readLine()) != null) {
		    		  String[] arr = line.split(",");
		    		  model.addRow(new Object[0]);
		    		 
		    		  model.setValueAt(arr[0], row, 0);
		    		  model.setValueAt(arr[1], row, 1);
		    		  model.setValueAt(arr[2], row, 2);
		    		  model.setValueAt(arr[3], row, 3);
		    		  model.setValueAt(arr[4], row, 4);
		    		  model.setValueAt(arr[5], row, 5);
		    		  model.setValueAt(arr[6], row, 6);
		    		  model.setValueAt(arr[7], row, 7);
		    		  model.setValueAt(arr[8], row, 8);
		    		
		    		  row++;
		    		  }
		    		  br.close();
		    		  
		    		  } catch (IOException ex) {
		    		 
		    		  // TODO Auto-generated catch block
		    		 
		    		  ex.printStackTrace();
		    		 
		    		  }	    	  
		    	  }
		    	  
		    	  SaveData();
		    	  setVisible(false);
		       }
		  }
    catch(Exception e)
    {
    	System.out.println(e);
    }
	}
}

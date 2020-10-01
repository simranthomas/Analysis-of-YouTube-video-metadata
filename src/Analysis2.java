import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.jfree.ui.RefineryUtilities;

public class Analysis2 implements ActionListener{
	JFrame f;
	JLabel l1;
	static JLabel l2;
	static JLabel l3;
	static JLabel l4;
	static JLabel l5;
	static JLabel l6, l7,l12;
	static JLabel l8;
	JButton kmn,back;
	static int val[]=new int[3];
	Analysis2(){
		f=new JFrame();
		kmn = new JButton("Analyze");
                kmn.setBackground(new java.awt.Color(255, 255, 255));
                kmn.setFont(new java.awt.Font("Segoe Script", 0, 18)); 
                kmn.setForeground(new java.awt.Color(0, 102, 0));
		kmn.addActionListener(this);
		l1 = new JLabel("BASED ON VIEWS");
                l1.setFont(new java.awt.Font("Segoe Script", 1, 20)); 
                l1.setForeground(new java.awt.Color(51, 0, 102));
                l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		l2 = new JLabel();
		l3 = new JLabel("Least Trending :");
                l3.setFont(new java.awt.Font("Segoe Script", 1, 20)); 
                l3.setForeground(new java.awt.Color(51, 0, 102));
		l4 = new JLabel("Moderately Trending :");
                l4.setFont(new java.awt.Font("Segoe Script", 1, 20)); 
                l4.setForeground(new java.awt.Color(51, 0, 102));
		l5 = new JLabel("Most Trending :");
                l5.setFont(new java.awt.Font("Segoe Script", 1, 20)); 
                l5.setForeground(new java.awt.Color(51, 0, 102));
		l7 = new JLabel();
                 l12=new JLabel("");
		back = new JButton("BACK");
                back.setBackground(new java.awt.Color(255, 255, 255));
                back.setFont(new java.awt.Font("Segoe Script", 0, 18)); 
                back.setForeground(new java.awt.Color(0, 102, 0));
		back.addActionListener(this);
		f.add(l1);
		f.add(l12);
		f.add(l3);
		f.add(l4);
		f.add(l5);
		f.add(l7);
                f.add(kmn);
		f.add(l2);
                f.add(back);
		f.pack();
		f.setVisible(true);
		f.setSize(800,800);
		f.setLayout(new GridLayout(10,2,4,2));
		f.setSize(700,700);
                f.getContentPane().setBackground(new java.awt.Color(204,255,255));
		
	}
	
public static int[][] getCentroid(int data[],int noofclusters,int centroid[][]){
		
		int distance[][]=new int[noofclusters][data.length];
		String names[] = {"Least Viewed","Moderately Viewed","Most Viewed"};
		int cluster[]=new int[data.length];
		int clusternodecount[]=new int[noofclusters];
		
		centroid[0]=centroid[1];
		centroid[1]=new int[]{0,0,0};
		
		for(int i=0;i<noofclusters;i++){
			for(int j=0;j<data.length;j++){
				distance[i][j]=Math.abs(data[j]-centroid[0][i]);
			}
			
		}
		
		for(int j=0;j<data.length;j++){
			int smallerDistance=0;
			if(distance[0][j]<distance[1][j] && distance[0][j]<distance[2][j])
				smallerDistance=0;
			if(distance[1][j]<distance[0][j] && distance[1][j]<distance[2][j])
				smallerDistance=1;
			if(distance[2][j]<distance[0][j] && distance[2][j]<distance[1][j])
				smallerDistance=2;//
			
			centroid[1][smallerDistance]=centroid[1][smallerDistance]+data[j];
			clusternodecount[smallerDistance]=clusternodecount[smallerDistance]+1;
			cluster[j]=smallerDistance;
			
		}
		
		
		for(int j=0;j<noofclusters;j++){
			centroid[1][j]=centroid[1][j]/clusternodecount[j];
			
		}
		System.out.println();
	
		boolean isAchived=true;
		for(int j=0;j<noofclusters;j++){
			if(isAchived && centroid[0][j] == centroid[1][j]){
				isAchived=true;
				continue;
			}
			isAchived=false;
		}
		
		if(!isAchived){
                    
			getCentroid(data,noofclusters,centroid);
		}
		
		if(isAchived){
			int cnt1=0;
			System.out.println("======================================== ");
			System.out.println(" Final Cluster is ");
			for(int i=0;i<noofclusters;i++){	
                              System.out.print("C"+(i+1)+":");
                              cnt1=0;
				for(int j=0;j<data.length;j++){
					if(cluster[j]==i){
						if(data[j]!=0){
							cnt1++;
						}
						
						 }
					
				}
				if(i==0){
					val[0]=cnt1;
					l3.setText("Least Trending :" + Integer.toString(cnt1));
				}else if(i==1){
					val[1]=cnt1;
					l4.setText("Moderately Trending :" + Integer.toString(cnt1));
				}else if(i==2){
					val[2]=cnt1;
					l5.setText("Most Trending :" + Integer.toString(cnt1));
				}
				System.out.println(cnt1);
				System.out.println();
			}
			l2.setText("DONE!");
                        l2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        l2.setFont(new java.awt.Font("Segoe Script", 1, 20)); 
                        l2.setForeground(new java.awt.Color(51, 0, 102));
			final PieChart3D demo = new PieChart3D("Pie Chart",val,names);
                        demo.pack();
                        RefineryUtilities.centerFrameOnScreen(demo);
                        demo.setVisible(true);
                        
                        final BarChart3D demo1 = new BarChart3D("Bar Chart",val,names);
                        demo1.pack();
                        RefineryUtilities.centerFrameOnScreen(demo);
                        demo1.setVisible(true);
		}
		
		return centroid;
		
	}


	public void show(){
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	    
	     
	      Session session = cluster.connect();
	      session.execute("USE miniproject");
	      
	     
	     String cql = "SELECT video_id,views FROM youtube";
	     
	     //Getting the ResultSet
	     ResultSet result = session.execute(cql);
	          int data[] = new int[24000];
	     int i=0; 
	     int NA =0;
	     String tmp;
	        for(Row r : result)
	        {
	        	tmp = r.getString("views");
	        	
	        	data[i] = Integer.parseInt(r.getString("views"));
	        	i++;
	        	  
	     }
	       
	        
	        int noofclusters=3;
			int centroid[][]=new int[][]{
				{0,0,0},
				{10000,50000,100000}
			};
			getCentroid(data,noofclusters,centroid);	
	
	    }

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == kmn){
			
			show();
			
		}
                else if(arg0.getSource()==back)
                {
                    f.setVisible(false);
                }
		
	}
	public static void main(String[] args) {  
	    new Analysis2();  
	}  
	
}
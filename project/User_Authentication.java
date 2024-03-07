// User authentication.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;

class User_Authentication extends JFrame implements ActionListener
{
	JLabel l1,l2,l3;
	JTextField t1;
	JPasswordField t2;
	JButton b1,b2,b3;
	ImageIcon i;
	LineBorder lb;
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Font f;

	User_Authentication()
	{
		super("User Authentication");
		l1 = new JLabel("Enter Username : ");
		l2 = new JLabel("Enter Password : ");
		i = new ImageIcon("images//l2.jpg");
		lb = new LineBorder(Color.red,4);

		
			
		f=new Font("Sanserif",Font.BOLD,20);
		l1.setFont(f);
		l2.setFont(f);

		l3 = new JLabel(i);
		l3.setBorder(lb);
		
		t1 = new JTextField(10);
		t2 = new JPasswordField(10);
		t1.setFont(f);
		t2.setFont(f);
	
		b1=new JButton("Submit");
		b3=new JButton("New User");
		b2 = new JButton("Exit");
		
		add(b1);
		add(b2);
		add(b3);
		add(l1);
		add(l2);
		add(t1);
		add(t2);
		add(l3);

		setLayout(null);
		
		l1.setBounds(35,306,220,20);
		t1.setBounds(340,305,330,30);

		l2.setBounds(35,418,220,20);
		t2.setBounds(340,417,330,30);

		b1.setBounds(350,500,80,25);
		b2.setBounds(450,500,80,25);
		b3.setBounds(570,500,80,25);	

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		b1.setBackground(Color.black);
		b2.setBackground(Color.black);
		b3.setBackground(Color.black);
	
		b1.setForeground(Color.white);
		b2.setForeground(Color.white);
		b3.setForeground(Color.white);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
		System.out.println("Connection Established.");
		}
		catch(Exception e)
		{
		}

		setUndecorated(false);
		
		l3.setBounds(0,0,i.getIconWidth(),i.getIconHeight());
		//t1.setBackground(Color.yellow);
		//t2.setBackground(Color.yellow);

		t1.setForeground(Color.blue);
		t2.setForeground(Color.blue);
		setSize(i.getIconWidth(),i.getIconHeight());
		
		setLocation(
		(Toolkit.getDefaultToolkit().getScreenSize().width-getWidth())/2,
		(Toolkit.getDefaultToolkit().getScreenSize().height-getHeight())/2
		);		
		setVisible(true);	
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object o = ae.getSource();
		if(o==b1)
		{
			String u=t1.getText();
			String p=t2.getText();
		
			try
			{
			ps = con.prepareStatement("select * from login where uname=? and pass=?");
			ps.setString(1,u);
			ps.setString(2,p);

			rs=ps.executeQuery();

			if(rs.next())
			{
				System.out.println("Valid User.");
				JOptionPane.showMessageDialog(this,"Valid User","Done",JOptionPane.INFORMATION_MESSAGE);
				new home_page();
				this.dispose();

			}
			else
			{
				System.out.println("Invalid User.");
				JOptionPane.showMessageDialog(this,"Invalid User","Sorry!",JOptionPane.ERROR_MESSAGE);
			}

			}//try
			catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Database query error", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		
		}
		if(o==b2){	
			int result = JOptionPane.showConfirmDialog(this,"Are you sure to exit?","Exit?",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE);
				if(result==JOptionPane.YES_OPTION){
				System.exit(0);
				}
		}
		if(o==b3)
			new Registration();
		}
	public static void main(String args[])
	{
		new User_Authentication();
	}
}
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MatchesAllocation extends JFrame implements ActionListener {

	private JButton allocatebutton,Clearbutton;/* GUI JButton */
	private JTextArea  InfoReferee;/*GUI JTextFields */
	private JComboBox AreaCombo;
	private JCheckBox JuniorCheckBox, SeniorCheckBox;
	private int currentValue;
	private JTextArea GraphicsDisplay, AllocationsDisplay, CurrentDisplay;/* Display of the GUI */
	private String allocs,GetHomeLocs,GetLocTotravel,GetQuals,GetName,GetSurname,Refereename1,Refereename2,AssignDetailsOut;
	private int GetAllocations,minAllocation,CentralAreas,NorthAreas,SouthAreas;
	private  String [] getdisplay,GetHomeLocations,GetLocationTotravel;
	private String[] AlltheLinesAdded,thelines,GetNorthLocToTravel,GetCentralLocToTravel,GetSouthLocToTravel; 
	private int [] GetAllocsSorted,GetQualifications,GetCentralAllocations,GetSouthAllocations,GetNorthAllocations;
	private int [] GetNorthQualifications,GetCentralQualifications,GetSouthQualifications;
	private String [] GetAllLinesNorth,GetAllLinesCentral,GetAllLinesSouth;
	
	public MatchesAllocation(String [] AlltheLinesAdded) // Constructor for the information of the GUI
	{	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Allocate a Referee");
		setSize(600, 380);
		GraphicsDisplay = new JTextArea();
		AllocationsDisplay = new JTextArea(5,5);
		JScrollPane scrollPane = new JScrollPane( AllocationsDisplay );
		GraphicsDisplay.setFont(new Font("Courier", Font.PLAIN, 14));
		add( GraphicsDisplay, BorderLayout.CENTER);
		add(scrollPane, BorderLayout.SOUTH);
		layoutTop();
		this.AlltheLinesAdded =  AlltheLinesAdded;
	}
	
	public void layoutTop() {
		JPanel top = new JPanel();
		JLabel weeklabel = new JLabel("Number of Week:");
		top.add(weeklabel);
		
		JPanel spinnerpanel = new JPanel();
		Integer value = new Integer(52); 
		Integer min = new Integer(1);
		Integer max = new Integer(52); 
		Integer step = new Integer(1); 
		final SpinnerNumberModel model = new SpinnerNumberModel(value,min,max,step);   
		JSpinner spinner = new JSpinner(model);
		getContentPane().add(spinner, BorderLayout.NORTH);  
		spinnerpanel.add(spinner);
		ChangeListener listener = new ChangeListener(){
		@Override 
		public void stateChanged( ChangeEvent e) // method to take the changes of the spinner
		{
			currentValue = (Integer) model.getValue();// get the current value of the spinner
		}
		};
		spinner.addChangeListener(listener);
		top.add(spinner);
		
		JPanel combopanel = new JPanel();
		JLabel combolabel = new JLabel("Match Area:");
		combopanel.add(combolabel);
		AreaCombo = new JComboBox();
		AreaCombo.addItem("North");
		AreaCombo.addItem("Central");
		AreaCombo.addItem("South");
		AreaCombo.addActionListener(this);
		combopanel.add(AreaCombo);
		top.add(combopanel);
		
		JPanel emptypanel = new JPanel();
		top.add(emptypanel);
		
		JPanel checkpanel = new JPanel();
		JLabel checklabel = new JLabel("Level:");
		checkpanel.add(checklabel);
		JuniorCheckBox = new JCheckBox("Junior");
		JuniorCheckBox.addActionListener(this);
		SeniorCheckBox = new JCheckBox("Senior");
		SeniorCheckBox.addActionListener(this);
		ButtonGroup group = new ButtonGroup();
		group.add(JuniorCheckBox);
		group.add(SeniorCheckBox);
		checkpanel.add(JuniorCheckBox);
		checkpanel.add(SeniorCheckBox);
		top.add(checkpanel);
		
		JPanel refpanel= new JPanel();
		JLabel reflabel= new JLabel("Information:");
		refpanel.add(reflabel);
		InfoReferee= new JTextArea(7,40);
		JScrollPane scrollPane2 = new JScrollPane(InfoReferee);
		refpanel.add(scrollPane2);
		String plaintext="Here you can check for suitability of Referees. Select the week of the match from\n"
						  +"the spinner, the area of the match from the Combo box and the level of the match\n"
						  +"from the CheckBox.\n"
						  +"The program will automatically display the two most suitable referees in the\n"
						  +"following order: First are seleceted the referees who live in the area of the\n"
						  +"stadium and have the least number of allocations compared to other suitable\n"
						  +"referees in this area. Secondly, referees are considered who live in an adjacent\n"
						  +"area to the stadium, are prepared to travel there and have the least number of\n"
						  +"allocations compared to other who come into this category. Finally, referees are\n"
						  +"considered who live in a non-adjacent area to the stadium but who are prepared\n"
						  +"to travel there and have the least number of allocations compared to other \n"
						  +"referees in this category.\n"
						  +"After displaying the referees you can clear the area with the button.";
		InfoReferee.setText(plaintext);	
		top.add(refpanel);
		
		JPanel allocpanel = new JPanel();
		allocatebutton = new JButton("Check Suitability");
		allocatebutton.addActionListener(this);
		allocpanel.add(allocatebutton);
		Clearbutton = new JButton("Clear Area");
		Clearbutton.addActionListener(this);
		allocpanel.add(Clearbutton);
		
		top.add(allocpanel);
		top.setBorder(
	            BorderFactory.createTitledBorder( "Allocate a Referee"));
		//you can add this in the above line to add style on the border. BorderFactory.createEtchedBorder(Color.GRAY, Color.GRAY),
		add(top, BorderLayout.CENTER);	
		}
	
	public void ClerTextArea ()// a method to clear the contents of the JTextArea that displays the Referees.
	{
		AllocationsDisplay.setText(null);	
		allocs="";
		Refereename1=null;
		Refereename2=null;
	}
	
	public void StoreTheInfoNeeded () 
	{
		getdisplay = AlltheLinesAdded;//Store the array from GUI class to getdisplay array
		CurrentDisplay = new JTextArea();//create a new JTextArea
		int len = AlltheLinesAdded.length;
		
		for(int i = 0; i < len; i++) 
			{
			CurrentDisplay.append(AlltheLinesAdded[i]+"\n");//add in the JTextArea the array
			}
		
		Scanner in = new Scanner(CurrentDisplay.getText());//iterate through the lines
		while (in.hasNextLine()) //read each line from the Text area
		{ 
		GetAllocsSorted = new int [CurrentDisplay.getLineCount()-1];//a new array with the lines 
		GetHomeLocations = new String [CurrentDisplay.getLineCount()-1];
		GetLocationTotravel= new String [CurrentDisplay.getLineCount()-1];
		GetQualifications = new int [CurrentDisplay.getLineCount()-1];
		
			for (int i=0; i<CurrentDisplay.getLineCount()-1; i++) 
			{
				String line = in.nextLine();
			   String t []= line.split("\\s");//split for spaces from each line
			   for (int x=0; x<t.length; x++) 
			   { 
				   GetAllocations = Integer.parseInt(t[4]);//the 5th element of each line is the allocations number
				   GetHomeLocs = t[5];//the 6th element is the Home Location of the referee
				   GetLocTotravel = t[6];
				   GetQuals = t[3];
			   }
				   GetAllocsSorted[i] = GetAllocations;//store allocations number in the array
				   GetHomeLocations[i] = GetHomeLocs;//store Home Locations in the array
				   GetLocationTotravel[i]= GetLocTotravel;
				   GetQualifications[i] = Integer.parseInt(GetQuals.substring(3,4) );
				   boolean Central =GetHomeLocations[i].equals("Central");
				   CentralAreas += Central ?1:0 ;
				   boolean North =GetHomeLocations[i].equals("North");
				   NorthAreas += North ?1:0 ;
				   boolean South =GetHomeLocations[i].equals("South");
				   SouthAreas += South ?1:0 ;
			}
		}//end of while loop
	}
	
	public void ResetAreas ()//resets to 0 the integers. Otherwise it stores null values when the user requests for second time allocation. 
	{
		CentralAreas=0;
		NorthAreas=0;
		SouthAreas=0;
	}
	
	public void TaketheNorthAllocations()
	{
		Scanner in = new Scanner(CurrentDisplay.getText());//iterate through the lines
		GetNorthAllocations= new int [NorthAreas];
		GetNorthQualifications = new int [NorthAreas];
		GetNorthLocToTravel = new String [NorthAreas];
		GetAllLinesNorth = new String [NorthAreas];
		int count=0;
		
		for (int j = 0; j < GetHomeLocations.length; j++){
			String line = in.nextLine();
		    if (GetHomeLocations[j].equals("North"))
		    {
		    	System.arraycopy(GetAllocsSorted, j, GetNorthAllocations, count, 1);
		    	System.arraycopy(GetQualifications, j, GetNorthQualifications, count, 1);
		    	System.arraycopy(GetLocationTotravel, j, GetNorthLocToTravel, count, 1);
		    	GetAllLinesNorth[count]=line;
		    	count++;
		    }
		}
		minAllocation = GetNorthAllocations[0];
		int index=0;
		for (int k = 0; k < GetNorthAllocations.length; k++){
			 if (GetNorthAllocations[k] < minAllocation)
			{
				minAllocation = GetNorthAllocations[k];
		        index = k;
		    }
		}
	}
	
	public void TaketheCentralAllocations()
	{
		Scanner in = new Scanner(CurrentDisplay.getText());//iterate through the lines
		GetCentralAllocations = new int [CentralAreas];
		GetCentralQualifications = new int [CentralAreas];
		GetCentralLocToTravel = new String [CentralAreas];
		GetAllLinesCentral = new String [CentralAreas];
			int count=0;
			for (int j = 0; j < GetHomeLocations.length; j++){
				String line = in.nextLine();
			    if (GetHomeLocations[j].equals("Central"))
			    {
			    	System.arraycopy(GetAllocsSorted, j, GetCentralAllocations, count, 1);
			    	System.arraycopy(GetQualifications, j, GetCentralQualifications, count, 1);
			    	System.arraycopy(GetLocationTotravel, j, GetCentralLocToTravel, count, 1);
			    	GetAllLinesCentral[count]=line;
			    	count++;
			    }
			}
			minAllocation = GetCentralAllocations[0];
			int index=0;
			for (int k = 0; k < GetCentralAllocations.length; k++){
				 if (GetCentralAllocations[k] < minAllocation)
				{
					minAllocation = GetCentralAllocations[k];
			        index = k;
			    }
			}
	}
	
	public void TaketheSouthAllocations()
	{
		Scanner in = new Scanner(CurrentDisplay.getText());//iterate through the lines
		GetSouthAllocations = new int [SouthAreas];
		GetSouthQualifications = new int [SouthAreas];
		GetSouthLocToTravel = new String [SouthAreas];
		GetAllLinesSouth = new String [SouthAreas];
			int count=0;
			for (int j = 0; j < GetHomeLocations.length; j++){
				String line = in.nextLine();
			    if (GetHomeLocations[j].equals("South"))
			    {
			    	System.arraycopy(GetAllocsSorted, j, GetSouthAllocations, count, 1);
			    	System.arraycopy(GetQualifications, j, GetSouthQualifications, count, 1);
			    	System.arraycopy(GetLocationTotravel, j, GetSouthLocToTravel, count, 1);
			    	GetAllLinesSouth[count]=line;
			    	count++;
			    }
			}
			minAllocation = GetSouthAllocations[0];
			int index=0;
			for (int k = 0; k < GetSouthAllocations.length; k++){
				 if (GetSouthAllocations[k] < minAllocation)
				{
					minAllocation = GetSouthAllocations[k];
			        index = k;
			    }
			}
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==allocatebutton)
		{
			allocs="";
			String checkbox="";
			String combo = (String)AreaCombo.getSelectedItem();
			if (!JuniorCheckBox.isSelected() && !SeniorCheckBox.isSelected())//to validate the check boxes
			{
			JOptionPane.showMessageDialog(null, "Please select match level", "Result Summary", JOptionPane.ERROR_MESSAGE);
			}
			
			else if (combo.equals("North") && SeniorCheckBox.isSelected() ){
				
				StoreTheInfoNeeded();
				TaketheNorthAllocations();
			
			for (int i=0; i< GetNorthAllocations.length; i++) {
				if ( (GetNorthAllocations[i]==minAllocation)&& GetNorthQualifications[i] >= 2 ) 
				{
					allocs = GetAllLinesNorth[i]+ "\n";
					GetNorthAllocations[i]++;
				}
					}
				TaketheCentralAllocations();
				for (int i=0; i< GetCentralAllocations.length; i++) {
				 if ( GetCentralLocToTravel[i].substring(0, 1).equals("Y")&&(GetCentralAllocations[i]==minAllocation)&& GetCentralQualifications[i] >= 2)//need to give the second assignment here
				{
					allocs+= GetAllLinesCentral[i]+"\n";
					GetCentralAllocations[i]++;
				}
					}
				TaketheSouthAllocations();
				for (int i=0; i< GetSouthAllocations.length; i++) {
				if ( GetSouthLocToTravel[i].substring(0, 1).equals("Y")&&(GetSouthAllocations[i]==minAllocation)&& GetSouthQualifications[i] >= 2)//need to give the second assignment here
				{
					allocs+= GetAllLinesSouth[i]+"\n";
					GetSouthAllocations[i]++;
				}
					}
				ResetAreas();
			}// end of if combo == North 
			
			else 	if (combo.equals("Central") && SeniorCheckBox.isSelected() ){
				
				StoreTheInfoNeeded();
				TaketheCentralAllocations();
				
				for (int i=0; i< GetCentralAllocations.length; i++) {
				if ( (GetCentralAllocations[i]==minAllocation)&& GetCentralQualifications[i] >= 2) 
				{
					allocs = GetAllLinesCentral[i]+ "\n";
					GetCentralAllocations[i]++;
				}
					}
				TaketheNorthAllocations();
				for (int i=0; i< GetNorthAllocations.length; i++) {
				if ( GetNorthLocToTravel[i].substring(1,2).equals("Y")&&(GetNorthAllocations[i]==minAllocation)&& GetNorthQualifications[i] >= 2)//need to give the second assignment here
					{
						allocs+= GetAllLinesNorth[i]+"\n";
						GetNorthAllocations[i]++;
					}
						}
				TaketheSouthAllocations();
				for (int i=0; i< GetSouthAllocations.length; i++) {
				if ( GetSouthLocToTravel[i].substring(1,2).equals("Y")&&(GetSouthAllocations[i]==minAllocation)&& GetSouthQualifications[i] >= 2)//need to give the second assignment here
				{
					allocs+= GetAllLinesSouth[i]+"\n";
					GetSouthAllocations[i]++;
				}
					}
				ResetAreas();
			}//end of if (combo==Central
			else if (combo.equals("South") && SeniorCheckBox.isSelected() ){
					
					StoreTheInfoNeeded();
					TaketheSouthAllocations();
					
				for (int i=0; i< GetSouthAllocations.length; i++) {
				if ( (GetSouthAllocations[i]==minAllocation)&& GetSouthQualifications[i] >= 2) 
				{
					allocs = GetAllLinesSouth[i]+ "\n";
					GetSouthAllocations[i]++;
				}
						}
				TaketheCentralAllocations();
				for (int i=0; i< GetCentralAllocations.length; i++) {
				 if ( GetCentralLocToTravel[i].substring(2,3).equals("Y")&&(GetCentralAllocations[i]==minAllocation)&&GetCentralQualifications[i] >= 2)
				{
					allocs+= GetAllLinesCentral[i]+"\n";
					GetCentralAllocations[i]++;
				}
						}
				TaketheNorthAllocations();
				for (int i=0; i< GetNorthAllocations.length; i++) {
				 if (GetNorthLocToTravel[i].substring(2,3).equals("Y")&&(GetNorthAllocations[i]==minAllocation)&& GetNorthQualifications[i] >= 2)
				{
					allocs+= GetAllLinesNorth[i]+"\n";
					GetNorthAllocations[i]++;
				}
					}
				ResetAreas();
			}// end of if combo==South 
			
			else if (combo.equals("North") && JuniorCheckBox.isSelected() )
			{
				StoreTheInfoNeeded();
				TaketheNorthAllocations();
				
			for (int i=0; i< GetNorthAllocations.length; i++) {
				if ( (GetNorthAllocations[i]==minAllocation)&& GetNorthQualifications[i]>=1) 
				{
				allocs = GetAllLinesNorth[i]+ "\n";
				GetNorthAllocations[i]++;
				}
					}	
			TaketheCentralAllocations();
				for (int i=0; i<GetCentralAllocations.length; i++) {
				if ( GetCentralLocToTravel[i].substring(0,1).equals("Y")&&(GetCentralAllocations[i]==minAllocation)&& GetCentralQualifications[i]>=1)//need to give the second assignment here
				{
					allocs+= GetAllLinesCentral[i]+"\n";
					GetCentralAllocations[i]++;
				}
					}
				TaketheSouthAllocations();
				for (int i=0; i<GetSouthAllocations.length; i++) {
				 if (GetSouthLocToTravel[i].substring(0,1).equals("Y")&&(GetSouthAllocations[i]==minAllocation)&& GetSouthQualifications[i]>=1)//need to give the second assignment here
				{
					allocs+= GetAllLinesSouth[i]+"\n";
					GetSouthAllocations[i]++;
				}
					}
				ResetAreas();
			}// end of if combo == North 
			
			else if (combo.equals("Central") && JuniorCheckBox.isSelected() ){
	
				StoreTheInfoNeeded();
				TaketheCentralAllocations();
				
			for (int i=0; i< GetCentralAllocations.length; i++) {
				if ( (GetCentralAllocations[i]==minAllocation)&& GetCentralQualifications[i]>=1) 
				{
				allocs = GetAllLinesCentral[i]+ "\n";
				GetCentralAllocations[i]++;
				}
					}
			TaketheNorthAllocations();
			for (int i=0; i< GetNorthAllocations.length; i++) {
				 if ( GetNorthLocToTravel[i].substring(1,2).equals("Y")&&(GetNorthAllocations[i]==minAllocation)&& GetNorthQualifications[i]>=1)//need to give the second assignment here
				 {
					 allocs+= GetAllLinesNorth[i]+"\n";
					 GetNorthAllocations[i]++;
				 }
					}
			TaketheSouthAllocations();
			for (int i=0; i< GetSouthAllocations.length; i++) {
				 if (GetSouthLocToTravel[i].substring(1,2).equals("Y")&&(GetSouthAllocations[i]==minAllocation)&& GetSouthQualifications[i]>=1)//need to give the second assignment here		
				 {
					 allocs+= GetAllLinesSouth[i]+"\n";
					 GetSouthAllocations[i]++;
				 }
					}
			ResetAreas();
			}// end of if combo == Central 
			else if (combo.equals("South") && JuniorCheckBox.isSelected() ){
				
				StoreTheInfoNeeded();	
				TaketheSouthAllocations();
			
			for (int i=0; i< GetSouthAllocations.length; i++) {
				if ( (GetSouthAllocations[i]==minAllocation)&& GetSouthQualifications[i]>=1) 
				{	
					allocs = GetAllLinesSouth[i]+ "\n";
					GetSouthAllocations[i]++;
				}
					}
			TaketheCentralAllocations();
			for (int i=0; i< GetCentralAllocations.length; i++) {
				 if ( GetCentralLocToTravel[i].substring(2, 3).equals("Y")&&(GetCentralAllocations[i]==minAllocation)&& GetCentralQualifications[i]>=1)//need to give the second assignment here
				{
					allocs+= GetAllLinesCentral[i]+"\n";
					GetCentralAllocations[i]++;
				}
			}
			TaketheNorthAllocations();
			for (int i=0; i< GetNorthAllocations.length; i++) {
				 if (GetNorthLocToTravel[i].substring(2, 3).equals("Y")&&(GetNorthAllocations[i]==minAllocation)&& GetNorthQualifications[i]>=1)//need to give the second assignment here
				{
					allocs+= GetAllLinesNorth[i]+"\n";
					GetNorthAllocations[i]++;
				}
					}
			ResetAreas();
			}// end of if combo == South 
		 
			if (JuniorCheckBox.isSelected())
				{
				checkbox= "Junior";
				}
			if (SeniorCheckBox.isSelected())
				{
				checkbox = "Senior";
				}
	
			AllocationsDisplay.setFont(new Font("Courier", Font.PLAIN, 14));
			AllocationsDisplay.setText(allocs);
		
			TakeTheNamesFromAllocs();
			 if (!allocs.equals("") && Refereename2==null)//give the message to the user that one referee is automatically selected  
			{
				JOptionPane.showMessageDialog(null, "One Suitable Referee is selected :" +Refereename1,"Result Summary", JOptionPane.INFORMATION_MESSAGE);
			}  
			 else if (!allocs.equals("") && Refereename2!=null)//give the message to the user that two referees are automatically selected  
			{
				JOptionPane.showMessageDialog(null, "Two Suitable Referees are selected :" +Refereename1+" "+"and"+" "+Refereename2,"Result Summary", JOptionPane.INFORMATION_MESSAGE);
			}		
			else if (allocs.equals(""))//give the message that did not find  suitable referees
			 {
				 JOptionPane.showMessageDialog(null, "There have not been found suitable Referees", "Result Summary", JOptionPane.INFORMATION_MESSAGE);
			 }
	
			  AssignDetailsOut = (currentValue +" "+checkbox+" "+combo+" "+Refereename1+" "+Refereename2+"\n");
			  WriterOut();
		}	//end of if (e.getSource()==allocatebutton)	
	
		if (e.getSource()==Clearbutton)
		{
			ClerTextArea();
		}
	}//end of actionEvent method
	
	public void TakeTheNamesFromAllocs() 
	{
		thelines = new String [AllocationsDisplay.getLineCount()-1];
		if (allocs!=null) {
		Scanner in = new Scanner (allocs);
		while (in.hasNextLine())
		{
			for (int i=0; i<thelines.length; i++)
			{
				String line = in.nextLine();
				   String s []= line.split("\\s");//split for spaces from each line
				   for (int x=0; x<s.length; x++) 
				   { 
					   GetName= s[1];//the second element is the first name
					   GetSurname=s[2];//the third element is the last name
				   }
				thelines[i]=GetName+ " "+GetSurname;	
				Refereename1 = thelines[0];//assign the array values into strings
				if (AllocationsDisplay.getLineCount()-1==2)		{
				Refereename2 = thelines[1];//assign the array values into strings
																}	
				}
			}
		}// end of if
	}
	
		public  void WriterOut()  
		{
			try
			{	
				FileWriter writer = new FileWriter("MatchAllocs.txt");
				PrintWriter out = new PrintWriter(writer);
				String currentDisplay = AssignDetailsOut;//get the display at that moment
				if (Refereename2==null)//if the program do not find second referee we do not want the 
				{						// word null to be written in the output txt file.
					 AssignDetailsOut=currentDisplay.substring(0,(currentDisplay.length()-5));
				}
				Scanner in = new Scanner(AssignDetailsOut);//pass it here
				while (in.hasNextLine())
				{
				String Allocationlines = in.nextLine();
				out.println("Assigned Referees:");//print the header
				out.println(Allocationlines);// print all the lines
				}
				out.close();
			
			}
			 catch (IOException e)
			 	{
				 System.out.println("Error processing file:" + e);
			 	}
		}
}
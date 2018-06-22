import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.util.Scanner;
import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;

public class ProgProjGUI  extends JFrame implements ActionListener {

	/* GUI JButtons */
	private JButton closeButton,show;
	private JButton addButton, deleteButton,searchButton,BarChartButton,AllocateButton,GuideButton,UpdateButton;//all the buttons of the main GUI
	private JComboBox facenameCombo,travelCombo,qualCombo, HomeAreaCombo,AreaTotravelCombo;
	private JTextField txt,txt1,Nametext,Qualtext;
	private JCheckBox NorthCheckBox,CentralCheckBox, SouthCheckBox;
	/*GUI JTextFields */
	private JTextField name,allocations;//all the text fields of the main GUI 

	/* Display of referees timetable */
	private JTextArea Refereedisplay;
	public String RefereeID,Firstname,Lastname,qualification,homeloc,loctovisit,GetAllocations;
	private String Qual,initials,Loc,aloc,lname,GetID,GetAllocs;
	private String lines,NewLine,NameEntered,init,AllLines,IDinitials,GetFirstName,Getlastname;
	private int matchesallocated,result,result2,result3,result4,result5,result6,result7,result8,result9;
	private int result10,result11,result12,result13,result14,result15,result16,result17,accumulatedlineslength;
	private static int IDnumberRest=1;
	private static int count2ndif=1;
	private String [] alltheIDs,getlines,GettheIDS,GettheAllocs,SortIDs,ArrayofFirstAndLastname;
	String [] array;
	private double []  AlltheAllocations;
	private int [] lineslength;
	public String [] AlltheLinesAdded,list;
 	 ArrayList <String> IDs= new ArrayList <String>();
	  ArrayList <Double> Allocations= new ArrayList<Double>();
	/*
	 * Constructor for the main GUI containing title, size, font, display the text area and layout
	 */
	public ProgProjGUI (String ID, String fname, String lname, String Qualify, int matches, String home, String location) {
		RefereeID=ID;
		Firstname=fname;
		Lastname=lname;
		qualification=Qualify;
		matchesallocated=matches;
		homeloc=home;
		loctovisit=location;	
	}
	
	public ProgProjGUI() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Hibernia JavaBall Games");
		setSize(650, 400);
		Refereedisplay = new JTextArea();
		Refereedisplay.setText(lines);
		RefereesDay();
		Refereedisplay.setFont(new Font("Courier", Font.PLAIN, 14));
		add( Refereedisplay, BorderLayout.CENTER);
		layoutTop();
		layoutBottom();	
	}
	
	public void layoutTop() // the method for the layout of the two upper buttons, save&exit and Details
	{
		JPanel top = new JPanel();
		closeButton = new JButton("Save and Exit");
		closeButton.addActionListener(this);
		top.add(closeButton);
		BarChartButton = new JButton("Bar Chart");
		BarChartButton.addActionListener(this);
		top.add(BarChartButton);
		AllocateButton = new JButton("Allocate");
		AllocateButton.addActionListener(this);
		GuideButton = new JButton("GUIDE");
		GuideButton.addActionListener(this);
		top.add(AllocateButton);
		top.add(GuideButton);
		add(top, BorderLayout.NORTH);
	}

	public void layoutBottom()// the method for the layout of the labels and buttons
	{
		// instantiate panel for bottom of display
		JPanel bottom = new JPanel(new GridLayout(5,4 ));
		// add name label, text field and add button
		JLabel idLabel = new JLabel("Referee Name");
		bottom.add(idLabel);
		name = new JTextField();
		bottom.add(name);
		JPanel panel1 = new JPanel();
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		panel1.add(addButton);
		bottom.add(panel1);

		// add qualification label, combo box and delete button
		JLabel nmeLabel = new JLabel("Qualification");
		bottom.add(nmeLabel);
		
		//add the combo box for the qualification
		qualCombo = new JComboBox();
		qualCombo.addItem("NJB1");
		qualCombo.addItem("NJB2");
		qualCombo.addItem("NJB3");
		qualCombo.addItem("NJB4");
		qualCombo.addItem("IJB1");
		qualCombo.addItem("IJB2");
		qualCombo.addItem("IJB3");
		qualCombo.addItem("IJB4");
		qualCombo.addActionListener(this);
		bottom.add(qualCombo);
		JPanel panel2 = new JPanel();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		panel2.add(deleteButton);
		bottom.add(panel2);

		// add the allocations label
		JLabel nmeLabel2 = new JLabel("Allocations");
		bottom.add(nmeLabel2);
		allocations = new JTextField();
		bottom.add(allocations);

		// add the search button
		JPanel panel3 = new JPanel();
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		panel3.add(searchButton);
		bottom.add(panel3);

		// add home area 
		JLabel homeLabel = new JLabel("Home Area");
		bottom.add(homeLabel);

		// add the panel which contains the 3 checkboxes
		JPanel panel4 = new JPanel();
		NorthCheckBox = new JCheckBox("North");
		NorthCheckBox.addActionListener(this);
		CentralCheckBox = new JCheckBox("Central");
		CentralCheckBox.addActionListener(this);
		SouthCheckBox = new JCheckBox("South");
		SouthCheckBox.addActionListener(this);
		ButtonGroup group = new ButtonGroup();//group the checkBoxes so that when one is selected
		group.add(NorthCheckBox);// the other two are unselected.
		group.add(CentralCheckBox);
		group.add(SouthCheckBox);
		panel4.add(NorthCheckBox);
		panel4.add(CentralCheckBox);
		panel4.add(SouthCheckBox);
		bottom.add(panel4);
		add(bottom,BorderLayout.SOUTH);

		//add an empty panel in order the location to travel label to be aligned
		JPanel emptypanel = new JPanel();
		bottom.add(emptypanel);
		//add the label locations to visit
		JLabel loctotravelLabel = new JLabel("Locations to travel");
		bottom.add(loctotravelLabel);

		//add the combo box for the possible locations to travel a referee
		travelCombo = new JComboBox();
		travelCombo.addItem("NNN");
		travelCombo.addItem("YYY");
		travelCombo.addItem("NYY");
		travelCombo.addItem("NNY");
		travelCombo.addItem("NYN");
		travelCombo.addItem("YNN");
		travelCombo.addItem("YYN");
		travelCombo.addItem("YNY");
		travelCombo.addActionListener(this);
		bottom.add(travelCombo);
	}
	
	public void RefereesDay()//this method reads from the file RefereesIn. It is the method we need to read from this file
	{
		 lines="";
		try {
			FileReader reader = new FileReader("RefereesIn.txt");
			Scanner in = new Scanner(reader);
			while (in.hasNextLine()) { 
				String line = in.nextLine();
				String [] token = line.split(" ");
				RefereeID = token[0];
				Firstname = token[1];
				Lastname = token[2];
				qualification = token[3];
				String temp = token[4];			
				matchesallocated = Integer.parseInt(temp);
				homeloc = token[5];
				loctovisit =token[6];
				lines+=(RefereeID+" "+ Firstname+" "+ Lastname+" "+ qualification+" "+ matchesallocated+" "+ homeloc+" "+loctovisit+" "+"\n");		
			}
			Refereedisplay.setText(lines);
			System.out.println(lines);
			reader.close();
		}
		catch (IOException e) {
			System.out.println("File not found");
		}			
	}
	
	public String  getRefereeID()//accessor method for RefereeID
	{
		return RefereeID;
	}

	public void displayChart(){
	
		JFrame jfr= new JFrame();
		double [] val= new double[7];
		val[0] = 1.5;
		String [] val2= new String[5];
		val2[0]="ssd";
		
		BarChartClass cls= new BarChartClass(val,val2,"");			
		jfr.add(cls);
			
				String currentDisplay = Refereedisplay.getText();
				Scanner in = new Scanner(currentDisplay);//pass it here
				while (in.hasNextLine())//read each line from the Text area
				{
					int lines = Refereedisplay.getLineCount()-1;
					GettheIDS = new String [lines];// create an array
					String array [] = new String [lines];
					
					for (int i=0; i<lines; i++) 
					{	
						String line = in.nextLine();
						if (line.equals("")) {
							 array =(String[])  ArrayUtils.removeElement(GettheIDS, GettheIDS[i]);
							 GettheIDS = Arrays.copyOf(array, array.length);
							lines--;
							i--;
						}
						else {		
						AllLines = line.substring(0,3);//extract the first 3 characters from each line who are the IDs
						GettheIDS[i] = AllLines;//store them into the array
						}
						}
					}		
					String currentDisplay2 = Refereedisplay.getText();
					Scanner in2 = new Scanner(currentDisplay2);
				
					while (in2.hasNextLine()) //read each line from the Text area
					{
						int lines2 = Refereedisplay.getLineCount()-1;
						GettheAllocs = new String [lines2];// create an array
						String array2 [] = new String [lines2];
						
						for (int i=0; i<lines2; i++) 
						{
							String line = in2.nextLine();
						   String t []= line.split("\\s");//split for spaces from each line
						 if (line.equals("")) {
							 array2 =(String[])  ArrayUtils.removeElement(GettheAllocs, GettheAllocs[i]);
							 GettheAllocs = Arrays.copyOf(array2, array2.length);
							lines2--;
							i--;
						 }
						 else {
						   for (int x=0; x<t.length; x++) {
							  GetAllocs = t[4];	// the 5th element of each line is the allocations number
						  } 
						 GettheAllocs[i] = GetAllocs;//store them into the array
						 }
						}//end of for loop		
					}// end of while loop	
					double[] value= new double[GettheAllocs.length];
					String[] Referees = new String[GettheIDS.length];
					jfr.getContentPane().add(new BarChartClass(value, Referees,
							"BarChart of Referees"));;
					 for (int i=0; i<GettheIDS.length; i++) {
						 value[i] = Double.parseDouble(GettheAllocs[i]); 
							Referees[i] = GettheIDS[i];
					 }
		jfr.setSize(950, 500);
		jfr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jfr.setVisible(true);	
	}
	
	public void RefereesOut()//this method reads from the file RefereesIn. It is the method we need to read from this file
	{
			// prompt the user for the name of the input file 
			System.out.print("RefereesIn.txt");
			String inputFileName = ("RefereesIn.txt");

			// prompt the user for the name of the output file 
			System.out.print("RefereesOut.txt");
			String outputFileName = ("RefereesOut.txt");

			FileReader reader = null;
			PrintWriter out = null;
			
			try 
			{
				try {
					// try to open input file
					reader = new FileReader(inputFileName);
					String CurrentDisplay= Refereedisplay.getText();//get the display at the moment that the user presses the save&exit button
					// create a scanner object from reader
					Scanner in = new Scanner(CurrentDisplay);//pass it here
					// open output file
					out = new PrintWriter(outputFileName);
					int lineNumber = 1;
					// loop as long as there are more lines in the input file
					while (in.hasNextLine())
					{
						// read next line from input file
						String line = in.nextLine();
						// number the line (using a comment) and output this data
						out.print("/* " + lineNumber + " */ ");
						// output the line display from the textarea
						out.println(line);
						lineNumber++;	
					}
				}
				finally {
					// close the input file assuming it was successfully opened
					if (reader != null) reader.close();
					// close the output file assuming it was successfully opened
					if (out != null) out.close();
				}
			}
			catch (IOException exception)
			{
				// error processing either input or output file
				System.out.println("Error processing file: " + exception);
			}
		}
	
	public void processAdding()//this is the method to process the add of a referee 
	{
		RefereesClass fclass= new RefereesClass();
		fclass.addReferee(Firstname, Lastname);
	}
	public void processDeletion()// this is the method to delete a referee
	{
		RefereesClass fclass= new RefereesClass();
		fclass.deleteReferee(RefereeID, Firstname, Lastname);
	}	

	public void actionPerformed(ActionEvent e)// the action perform method for the 3 buttons 
	{
		if (e.getSource()==addButton)
		{
			RefereesClass Refclass= new RefereesClass();
			 int numreferees= Refclass.getnumreferees();
			 NameEntered=name.getText();
			 
			  if (numreferees<=11) {
			processAdding();
			
			Qual = (String)qualCombo.getSelectedItem();//get the value from qualification combo box
			Loc =(String)travelCombo.getSelectedItem();//get the value from location to travel combo box
			
			if (NorthCheckBox.isSelected())
			{ homeloc= "North";}
			if (CentralCheckBox.isSelected())
			{homeloc = "Central";}
			if (SouthCheckBox.isSelected())
			{homeloc = "South";}
			
			if (NameEntered.equals(null)|| NameEntered.equals(""))//validate the fields
			 {
				JOptionPane.showMessageDialog(null, "Please enter a Referee name to add.","Result Summary", JOptionPane.ERROR_MESSAGE);
			 }
			else if (allocations.getText().equals(null)|| allocations.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please enter an allocation number.","Result Summary", JOptionPane.ERROR_MESSAGE);
			}
			else if (!NorthCheckBox.isSelected()&& !CentralCheckBox.isSelected()&& !SouthCheckBox.isSelected())
			{
				JOptionPane.showMessageDialog(null, "Please select a Home Area.","Result Summary", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
			 init = name.getText().substring(0, 1);// prints the first letter
			  String printlastname =name.getText().substring(name.getText().lastIndexOf(" "));
			  String firstsecondletter = (init + printlastname.substring(1, 2));//the first two letters of ID
			  
			  initials = init+lname;
			
				 int  IDnumberZero=0;
				 int IDnumberKL = 2;
				  list =new String [Refereedisplay.getLineCount()-1];
				  String getAllTheIDs [] = Refereedisplay.getText().split("\\n");
				  for (int i=0; i<Refereedisplay.getLineCount()-1; i++)
				  {
					  list[i]= getAllTheIDs[i].substring(0, 2);//to gather all the First two characters of the IDs.
				  }
				  
				  if (firstsecondletter.equals("KL")){
					  IDnumberKL++;
					  lname = (printlastname.substring(1,2)+""+IDnumberKL);
				  }
				  else if (Arrays.asList(list).contains(firstsecondletter )&& IDnumberRest==1)//check if the ID of the name that user adds matches with the IDs of those who are already in the display.
				  {																							
					  IDnumberRest++;
					  lname = (printlastname.substring(1,2)+""+IDnumberRest);
					  count2ndif++;
				  }
				  else if (Arrays.asList(list).contains(firstsecondletter ) && count2ndif==1)//check if the ID of the name that user adds matches with the IDs of those who are already in the display.
				  {																							
					  bruteforce(list);
					  if (bruteforce(list)){
						  lname = (printlastname.substring(1,2)+""+2);
					  }
					  count2ndif++;
				  }
				  else if (Arrays.asList(list).contains(firstsecondletter ) && count2ndif==2)//check if the ID of the name that user adds matches with the IDs of those who are already in the display.
				  {																							
					  bruteforce(list);
					  if (bruteforce(list)){
						  lname = (printlastname.substring(1,2)+""+3);
					  }
					  count2ndif++;
				  }
				  else if (Arrays.asList(list).contains(firstsecondletter ) && IDnumberRest==2&& count2ndif==3)//check if the ID of the name that user adds matches with the IDs of those who are already in the display.
				  {																							
					  bruteforce(list);
					  if (bruteforce(list)){
						  lname = (printlastname.substring(1,2)+""+3);
					  }
				  }
				  else
				  {
					  IDnumberZero++;
					  lname = (printlastname.substring(1,2)+""+(IDnumberZero));
				  }
			  aloc = allocations.getText();
			  NewLine = (init +lname +" "+NameEntered+ " "+Qual+" "+aloc+" " +homeloc+" "+Loc+"\n");
			  GetID = init+lname;
			  GetAllocations=aloc;
			
			  lineslength = new int[12];
			  SortIDs =new String[Refereedisplay.getLineCount()-1];
			  
			  Scanner kb = new Scanner(Refereedisplay.getText());
			int numberofcurrentlines = Refereedisplay.getLineCount();
			 
			  while (kb.hasNextLine()){
				  for (int k=0; k<numberofcurrentlines-1; k++){
				  String line = kb.nextLine();
				 
				IDinitials = line.substring(0, 3);//get the IDs line by line
				SortIDs[k] = IDinitials; 
			
				  int  tokens = line.length();	 
				  lineslength[k] =tokens;  
			  }
				  }
				Arrays.sort(SortIDs);  	
				   
		  }//end of else
			
			  if (SortIDs.length==0 )//the case when the user has deleted all the referees
			  {
				  try {
						Refereedisplay.getDocument().insertString(0, NewLine, null);//place in the top of the area.
					} catch (BadLocationException e1) {	
						e1.printStackTrace();
					} 
			  }
			  else if (SortIDs.length>0) {
					 int accumulatedlineslength=0;
					boolean found = false;
				  for (int i=0; i< SortIDs.length; i++){
					  
						 result = NewLine.substring(0,3).compareTo(SortIDs[i]);
						 accumulatedlineslength+= lineslength[i];//count the lines that is reading until it finds the line to add it
					 
						 if (result<0 && !found )// place above a referee
					  {
						 //insert in the difference of all the lines added and the one that will be added above plus one or more spaces 
						 //determined by the times the loop counted
						try {
						Refereedisplay.getDocument().insertString(accumulatedlineslength-lineslength[i]+i, NewLine, null);
						} catch (BadLocationException e1) {	
							e1.printStackTrace();
						}
						
						found = true;
					 }

					  else if (result>0 && SortIDs.length== i+1)//place under a referee
					  {
						  Refereedisplay.append(NewLine);
					  }			  
				  }//end of for loop
			  }//end of   else if (SortIDs.length>0)
			  
			  }//end of  if (numreferees<=11)
			  			 
			  alltheIDs  = new String [12];
			  alltheIDs[0]= "DG1";
			  alltheIDs[1]= "DM1";
			  alltheIDs[2]= "JL1";
			  alltheIDs[3]= "KL1";
			  alltheIDs[4]= "KL2";
			  alltheIDs[5]= "TT1";	 
				
			if (numreferees== 6 )
				 {  
				  IDs.add( alltheIDs[0]);
				  IDs.add( alltheIDs[1]);
				  IDs.add( alltheIDs[2]);
				  IDs.add( alltheIDs[3]);
				  IDs.add( alltheIDs[4]);
				  IDs.add( alltheIDs[5]);

				alltheIDs[6]= GetID;
				IDs.add (alltheIDs[6]);
				
				 }
			 if (numreferees ==7) 
				 {
				alltheIDs [7] = GetID;
				IDs.add(alltheIDs[7]);
				alltheIDs[6] = IDs.get(6);
				 }
			 
			 if (numreferees ==8) 
			 {
				 alltheIDs [7]= IDs.get(7);
			alltheIDs [8] = GetID;
			IDs.add(alltheIDs[8]);
			alltheIDs[6] = IDs.get(6);
			 }
			 
			 if (numreferees ==9) 
			 {
			alltheIDs [9] = GetID;
			IDs.add(alltheIDs[9]);
			alltheIDs [8]= IDs.get(8);
			alltheIDs[7] = IDs.get(7);
			alltheIDs[6] = IDs.get(6);
			 }
			 
			 if (numreferees ==10) 
			 {
			alltheIDs [10] = GetID;
			IDs.add(alltheIDs[10]);
			alltheIDs [9]= IDs.get(9);
			alltheIDs [8]= IDs.get(8);
			alltheIDs[7] = IDs.get(7);
			alltheIDs[6] = IDs.get(6);
			 }
			 
			 if (numreferees ==11) 
			 {
			alltheIDs [11] = GetID;
			IDs.add(alltheIDs[11]);
			alltheIDs [10]= IDs.get(10);
			alltheIDs [9]= IDs.get(9);
			alltheIDs [8]= IDs.get(8);
			alltheIDs[7] = IDs.get(7);
			alltheIDs[6] = IDs.get(6);
			 }
			   
			  AlltheAllocations = new double [12];
			  AlltheAllocations[0]= 3;
			  AlltheAllocations[1]= 3;
			  AlltheAllocations[2]= 2;
			  AlltheAllocations[3]= 6;
			  AlltheAllocations[4]= 12;
			  AlltheAllocations[5]= 2;
			 
			  if (numreferees== 6 )
				 {  
				 Allocations.add( AlltheAllocations[0]);
				 Allocations.add( AlltheAllocations[1]);
				 Allocations.add( AlltheAllocations[2]);
				 Allocations.add( AlltheAllocations[3]);
				 Allocations.add( AlltheAllocations[4]);
				 Allocations.add( AlltheAllocations[5]);
				
				  AlltheAllocations[6] =Double.parseDouble(aloc);
				  Allocations.add (AlltheAllocations[6]);
				 }
			  
			 if (numreferees ==7) 
				 {
				 AlltheAllocations [7] =Double.parseDouble(aloc);
				 Allocations.add(AlltheAllocations[7]);
				 AlltheAllocations[6] = Allocations.get(6);
				 }
			 
			 if (numreferees ==8) 
			 {
				 AlltheAllocations [7]= Allocations.get(7);
				 AlltheAllocations [8] = Double.parseDouble(aloc);
				 Allocations.add(AlltheAllocations[8]);
				 AlltheAllocations[6] = Allocations.get(6);
			 }
			 
			 if (numreferees ==9) 
			 {
				 AlltheAllocations [9] = Double.parseDouble(aloc);
				 Allocations.add(AlltheAllocations[9]);
				 AlltheAllocations [8]= Allocations.get(8);
				 AlltheAllocations[7] = Allocations.get(7);
				 AlltheAllocations[6] = Allocations.get(6);
			 }
			 
			 if (numreferees ==10) 
			 {
				 AlltheAllocations [10] = Double.parseDouble(aloc);
				 Allocations.add(AlltheAllocations[10]);
				 AlltheAllocations [9]= Allocations.get(9);
				 AlltheAllocations [8]= Allocations.get(8);
				 AlltheAllocations[7] = Allocations.get(7);
				 AlltheAllocations[6] = Allocations.get(6);
			 }
			 
			 if (numreferees ==11) 
			 {
				 AlltheAllocations [11] = Double.parseDouble(aloc);
				 Allocations.add(AlltheAllocations[11]);
				 AlltheAllocations [10]= Allocations.get(10);
				 AlltheAllocations [9]= Allocations.get(9);
				 AlltheAllocations [8]= Allocations.get(8);
				 AlltheAllocations[7] = Allocations.get(7);
				 AlltheAllocations[6] = Allocations.get(6);
			 }  
			  else 
				  if (numreferees==12) 
				 {
				JOptionPane.showMessageDialog(null, "Referees full. No new Referees can be added","Result Summary", JOptionPane.ERROR_MESSAGE);
					 System.out.println("Referees full. No new Referees can be added");
				 }
			//System.out.print("You pressed the add button");	
		}
		
		if (e.getSource()==deleteButton)	
		{ 
			NameEntered = name.getText();
			String ArrayofFirstAndLastname[] = new String [Refereedisplay.getLineCount()-1];
			Scanner in = new Scanner(Refereedisplay.getText());
			while (in.hasNextLine())
			{	
				for (int i=0; i<Refereedisplay.getLineCount()-1; i++)
				{
					String line = in.nextLine();
					if (line.equals("")) {
						 GetFirstName="";
						 Getlastname="";
					}
					else {
					   String s []= line.split("\\s");//split for spaces from each line
					   for (int x=0; x<s.length; x++) 
					   { 
						   GetFirstName= s[1];//the second element is the first name
						   Getlastname=s[2];//the third element is the last name
					   }
					ArrayofFirstAndLastname[i] = GetFirstName +" " +Getlastname;
					}
					}
			}
			if (NameEntered.equals("")|| NameEntered.equals(" "))
			{
			JOptionPane.showMessageDialog(null, "Name field is blank. Please enter a Referee name to delete!","Result Summary", JOptionPane.ERROR_MESSAGE);
			}
			else if (NameEntered.matches("[0-9]+"))
			{
			JOptionPane.showMessageDialog(null, "You entered numbers. Please enter a Referee name to delete!","Result Summary", JOptionPane.ERROR_MESSAGE);	
			}
			else if (!Arrays.asList(ArrayofFirstAndLastname).contains(NameEntered))
			{
			JOptionPane.showMessageDialog(null, "You entered symbols or the Referee name you typed does not exist. Please enter another Referee name to delete!","Result Summary", JOptionPane.ERROR_MESSAGE);	
			}
			
			else {
			processDeletion();	
				getlines =new String [Refereedisplay.getLineCount()-1];
				getlines = Refereedisplay.getText().split("\\n");
					
				 for (int i=0; i<getlines.length; i++){
						if( getlines[i].contains(NameEntered)) 
								{							//it deletes the referee typed by the user and deletes the empty line left.
				    			 Refereedisplay.setText( Refereedisplay.getText().replaceAll(getlines[i], "").replaceAll("(?m)^[ \t]*\r?\n", ""));
				    			}
					}
			}
		}//end of if (e.getSource()==deleteButton)
				
		if (e.getSource()==searchButton){	
			
			RefereesClass frame = new RefereesClass();//this is to display the new window 
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(650, 300);
			frame.setVisible(true);

			txt1 = new JTextField(15);
			JPanel panel = new JPanel();
			facenameCombo = new JComboBox();
			facenameCombo.addItem("RefereeID");
			facenameCombo.addItem("Qualification");
			facenameCombo.addItem("Allocations");
			facenameCombo.addItem("Home Area");
			facenameCombo.addItem("Locations to travel");
			facenameCombo.addActionListener(this);
			HomeAreaCombo = new JComboBox();
			HomeAreaCombo.addItem("North");
			HomeAreaCombo.addItem("Central");
			HomeAreaCombo.addItem("South");
			AreaTotravelCombo = new JComboBox();
			AreaTotravelCombo.addItem("NNN");
			AreaTotravelCombo.addItem("YYY");
			AreaTotravelCombo.addItem("NNY");
			AreaTotravelCombo.addItem("NYY");
			AreaTotravelCombo.addItem("YYN");
			AreaTotravelCombo.addItem("YNN");
			AreaTotravelCombo.addItem("NYN");
			AreaTotravelCombo.addItem("YNY");
			show = new JButton("Show");
			UpdateButton = new JButton("Update");
			UpdateButton.addActionListener(this);
			
			JLabel NameLabel = new JLabel("Enter Referee:");
			JPanel Updatepanel = new JPanel();
			JLabel NameLabel2 = new JLabel("Enter Referee name to update:");
			txt = new JTextField(10);
			Nametext = new JTextField(10);
			JLabel QualLabel = new JLabel("Qualification:");
			JLabel HomeLabel = new JLabel("Home:");
			JLabel AreatoTravelLabel = new JLabel("Locations to travel:");
			Qualtext = new JTextField(5);
			panel.add(NameLabel);
			panel.add(txt1);
			panel.add(facenameCombo);
			panel.add(show);
			panel.add(txt);
			 panel.setPreferredSize(new Dimension(100, 110));
			 panel.setBorder(
			            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.GRAY), "Search a Referee"));
			frame.add(panel, BorderLayout.NORTH);//add the panel of search to top of the frame
			
			Updatepanel.add(NameLabel2);//the JComponents to update a referee
			Updatepanel.add(Nametext);
			Updatepanel.add(QualLabel);
			Updatepanel.add(Qualtext);
			Updatepanel.add(HomeLabel);
			Updatepanel.add(HomeAreaCombo);
			Updatepanel.add(AreatoTravelLabel);
			Updatepanel.add(AreaTotravelCombo);
			Updatepanel.add(UpdateButton);
			Updatepanel.setBorder(
			            BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.GRAY), "Update a Referee"));
			frame.add(Updatepanel, BorderLayout.CENTER);//add the panel of updates to center
			facenameCombo.getSelectedItem();

			show.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{		
					String s = (String) facenameCombo.getSelectedItem().toString();
					txt.setText(s);
					
					//to validate the name text area
					 if (txt1.getText().equals("") || (txt1.getText().equals(" ")))    
					{
						JOptionPane.showMessageDialog(null, "Please Enter a Name!", "Result Summary", JOptionPane.ERROR_MESSAGE);
					}
					else if (txt1.getText().matches("[0-9]+"))
					{
						JOptionPane.showMessageDialog(null, "Please Enter a Name, not Numbers!", "Result Summary", JOptionPane.ERROR_MESSAGE);
					}
					
					else if (txt1.getText().matches("([a-zA-Z]+\\s+)*[a-zA-Z]+"))// validate for only alphabets and spaces
					{		
					Scanner sc = new Scanner (Refereedisplay.getText());
					String ArrayRefereeIDs[] = new String [Refereedisplay.getLineCount()-1];
					String NameAndLastName[] = new String [Refereedisplay.getLineCount()-1];
					String Qualifications []= new String [Refereedisplay.getLineCount()-1];
					int MatchesAllocated [] =  new int [Refereedisplay.getLineCount()-1];
					String HomeLocations [] =  new String [Refereedisplay.getLineCount()-1];
					String Locations [] =  new String [Refereedisplay.getLineCount()-1];
					while (sc.hasNextLine())
					{	
						for (int i=0; i<Refereedisplay.getLineCount()-1; i++)
						{
							String line = sc.nextLine();
							   String t []= line.split("\\s");//split for spaces from each line
							   for (int x=0; x<t.length; x++) 
							   { 
								   RefereeID = t[0];
								   GetFirstName= t[1];//the second element is the first name
								   Getlastname=t[2];//the third element is the last name
								   Qual = t[3];//get the Qualification
								   matchesallocated =Integer.parseInt(t[4]);
								   homeloc = t[5];//get the home location
								   Loc= t[6];//get the areas to travel
							   }
							   ArrayRefereeIDs [i] = RefereeID;
							   NameAndLastName[i] = GetFirstName +" " +Getlastname;
							   Qualifications[i] = Qual;
							   MatchesAllocated[i]=  matchesallocated;
							   HomeLocations[i] = homeloc;
							   Locations[i] = Loc;				   
							
						    if (txt1.getText().equals(NameAndLastName[i])&& (s.equals("RefereeID")))
						 		{
								txt.setText( ArrayRefereeIDs [i]);
								}
						 else if (txt1.getText().equals(NameAndLastName[i])&& (s.equals("Qualification")))
						 		{
									txt.setText( Qualifications[i]);
								}
						else if (txt1.getText().equals(NameAndLastName[i])&& (s.equals("Allocations")))
								{
									txt.setText(Integer.toString(MatchesAllocated[i]));
								}
			    		else if (txt1.getText().equals(NameAndLastName[i])&& (s.equals("Home Area")))
			    				{
									txt.setText(HomeLocations[i]);
								}
						else if (txt1.getText().equals(NameAndLastName[i])&& (s.equals("Locations to travel")))
								{
									txt.setText( Locations[i]);
								}
						
						}//end of first for loop		
					}//end of while loop
					}// end of third if
					else 
					{
					JOptionPane.showMessageDialog(null, "No such name exists!", "Result Summary", JOptionPane.ERROR_MESSAGE);	
					}
				}		
				
			});
		}
		if (e.getSource()==UpdateButton){
			String NameToUpdate = Nametext.getText();
			String newQualification= Qualtext.getText();
			String newhome = (String) HomeAreaCombo.getSelectedItem().toString();
			String newlocToTravel = (String) AreaTotravelCombo.getSelectedItem().toString();
			Scanner in = new Scanner (Refereedisplay.getText());
			String Arraynames[] = new String [Refereedisplay.getLineCount()-1];//the array that holds all the referee names
			String DetailsOfRef [] = new String [Refereedisplay.getLineCount()-1];//the array that holds all the details of referees
			String Updatedlines="";
			if (NameToUpdate.equals("")|| NameToUpdate.equals(null)|| NameToUpdate.equals(" ")){
			JOptionPane.showMessageDialog(null, "Please Enter a Name!", "Result Summary", JOptionPane.ERROR_MESSAGE);
			}
			if (newQualification.equals("")|| newQualification.equals(null)|| newQualification.equals(" ")){
			JOptionPane.showMessageDialog(null, "Please Enter a Qualification!", "Result Summary", JOptionPane.ERROR_MESSAGE);
			}
			if (newQualification.length()!=4){
				JOptionPane.showMessageDialog(null, "Please Enter a proper Qualification i.e NJB2, IJB3!", "Result Summary", JOptionPane.ERROR_MESSAGE);
			}
			else if (newQualification.length()==4){
			while (in.hasNextLine())
			{	
				for (int i=0; i<Refereedisplay.getLineCount()-1; i++)
				{
					String line = in.nextLine();
					   String s []= line.split("\\s");//split for spaces from each line
					   for (int x=0; x<s.length; x++) 
					   { 
						   GetFirstName= s[1];//the second element is the first name
						   Getlastname=s[2];//the third element is the last name
						   Qual = s[3];//get the Qualification
						   homeloc = s[5];//get the home location
						   Loc= s[6];//get the areas to travel
					   }
					Arraynames[i] = GetFirstName +" " +Getlastname;
					DetailsOfRef[i] = Qual+","+homeloc+","+Loc;
					if (NameToUpdate.matches(Arraynames[i]))//the name entered by the user is contained in the array
					{					
					DetailsOfRef[i]= newQualification+","+newhome+","+newlocToTravel;//change the details
					String [] token = line.split(" ");
					RefereeID = token[0];
					Firstname = token[1];
					Lastname = token[2];
					qualification = newQualification;//pass them to the JtextArea
					String temp = token[4];			
					matchesallocated = Integer.parseInt(temp);
					homeloc =newhome;//pass them to the JtextArea
					loctovisit =newlocToTravel;//pass them to the JtextArea
					Updatedlines+=(RefereeID+" "+ Firstname+" "+ Lastname+" "+ qualification+" "+ matchesallocated+" "+ homeloc+" "+loctovisit+" "+"\n");
					}
					else {
						String [] token = line.split(" ");
						RefereeID = token[0];
						Firstname = token[1];
						Lastname = token[2];
						qualification = token[3];//do not do the same for all the referees
						String temp = token[4];			
						matchesallocated = Integer.parseInt(temp);
						homeloc = token[5];//but only for the one/or more that the user has typed
						loctovisit =token[6];//in the NameToUpdate field
						Updatedlines+=(RefereeID+" "+ Firstname+" "+ Lastname+" "+ qualification+" "+ matchesallocated+" "+ homeloc+" "+loctovisit+" "+"\n");
					}
				}//end of for loop		
			}//end of while
			Refereedisplay.setText(Updatedlines);//Update the JTextArea. 
		}//end of if newQualification==4
	}	
		
		if (e.getSource()== BarChartButton)
		{
			displayChart();
		}
		
		if (e.getSource()==AllocateButton)
		{
			 AlltheLinesAdded = new String [Refereedisplay.getLineCount()-1]; 
			  Scanner in = new Scanner (Refereedisplay.getText());
			  while (in.hasNextLine()){
				  for (int i=0; i<Refereedisplay.getLineCount()-1; i++) {
			String line = in.nextLine();
				  AlltheLinesAdded[i] = line ;
		  }
		}	  
			MatchesAllocation malloc=new MatchesAllocation(AlltheLinesAdded);
			malloc.setVisible(true);			
		}
		
		if (e.getSource()==GuideButton)
		{
			try {
		       // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    } catch (Exception evt) {}
		  
		    JFrame f = new JFrame("Instructions");
		    // Create the StyleContext, the document and the pane
		    StyleContext sc = new StyleContext();
		    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		    JTextPane pane = new JTextPane(doc);
		    SimpleAttributeSet set = new SimpleAttributeSet();
		    set = new SimpleAttributeSet();
		    StyleConstants.setFontSize(set, 12);
          
		    // Create and add the style
		    final javax.swing.text.Style heading2Style = sc.addStyle("Heading2", null);
		    heading2Style.addAttribute(StyleConstants.FontSize, new Integer(16));
		    heading2Style.addAttribute(StyleConstants.FontFamily, "arial");
		    heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));
		    heading2Style.addAttribute(StyleConstants.Underline, new Boolean(true));
		    try {
		          try {
		            // Add the text to the document
		            doc.insertString(0, text, set);
		            
		            // Finally, apply the style to the heading
		            doc.setParagraphAttributes(0, 1, heading2Style, false);
		      
		          } catch (BadLocationException l) {
		          }
		    } catch (Exception q) {
		      System.out.println("Exception when constructing document: " + q);
		      System.exit(1);
		    }
		   f.getContentPane().add(new JScrollPane(pane));
		    f.setSize(500, 500);
		    f.setVisible(true);
		  }
			
		if (e.getSource()==closeButton)
		{
			RefereesOut();
			System.out.print("Close");
			System.exit(0);	
		}
	}
	
	public JTextArea getRefereedisplay() {
		return Refereedisplay;
	}
	
	public static boolean bruteforce(String[] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i].equals(list[j]) && i != j) {
                    return true;
                }
            }
        }
        return false;
    }
	
	 public static final String text = "Welcome to Referees Allocation program\n"
			  + "\n"
		      + "Here you can:\n"
		      + "1) Add a new referee along with his/her qualification, their allocations\n" 
		      + "to a match, their home location and the location that he/she is willing to travel. The 1 in the referee qualification means that a referee can attend only Junior matches while the 2 means that can attend both Junior and Senior matches.\n"
		      + "The NNN, NYY, NNY etc represents the referee willingness to travel in an area match. So if a referee has NNN means that he/she is not willing to travel North, Central,South whereas NNY means that he/she is willing to travel to South but not in North and Central.\n"
		      + "YYY means that he/she is willing to travel to North,Central and South, NYY means that he/she is willing to travel to Central and South but not to North.\n"
		      + "2) Delete a referee either from those who are loaded or from those you add\n"
		      + "3) Search for a referee and their details either from those who are loaded\n"
		      + "or from those you add.\n" 
		      + "4) Allocate one or two referees to a match choosing a level of match, area match and week \n"
		      + "5) The BarChart displays all the referees, loaded and added in corelation with their allocations to a match.\n"
		      + "6) The program loads from a file six referees, you can add another six and allocate\n"
		      + "them to a match with qualification, home location and location to travel. The program\n"
		      + "selects automatically two suitable referees according to their home location, the least match allocations and if they are willing to travel to the match area. The suitable referees are saved in a file called matchAllocs.txt\n"
		      + "7) The Save and Exit button saves the current referees added in a file called RefereesOut.txt.\n"
		      + "\n"
		      + "Developed by George Kosmadakis "   ;
}







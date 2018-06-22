import javax.swing.JFrame;

public class RefereesClass extends JFrame {
	String arr;
	public  static int numreferees=6;//variable for the number of referees
	//private RefereesClass [] fReferees;// an array for the number of referees
	private String RefereeID;
	private String Firstname;
	private String Lastname;
	private String Qualification;
	private int matchesallocated;
	private String homeloc;
	private String loctovisit;//all the variables for the details of each referee
	/*the second GUI*/
	private static final int FRAME_WIDTH = 500;//width of the GUI Find a referee
	private static final int FRAME_HEIGHT = 200;//height of the GUI Find a referee
	
	/*the constructor of this class.it takes all the variables of each referee*/
	public RefereesClass (String ID, String fname, String lname, String Qualify, int matches, String home, String location ) 
	{
		RefereeID=ID;
		Firstname=fname;
		Lastname=lname;
		Qualification=Qualify;
		matchesallocated=matches;
		homeloc=home;
		loctovisit=location;
	}
		
	public RefereesClass()//this is the default constructor where i have put some information for GUI Find a referee
	{
		setSize(FRAME_WIDTH, FRAME_HEIGHT);//this is the size 
		setTitle ("Find a Referee");//the title
	}
	
	public String getRefereeID()// method to return RefereeID
	{
		return RefereeID;	
	}
	
	public String getFirstname()// method to return Firstname
	{
		return Firstname;	
	}
	
	public String getLastname()// method to return Lastname
	{
	
		return Lastname;
	}
	
	public int getnumreferees() 
	{
		return numreferees;
	}
	
	public RefereesClass findReferee (String Firstname, String Lastname)
	{  
		RefereesClass fReferees[] = new RefereesClass[12];
		for (int i=0; i<numreferees; i++)
		{ 
			fReferees[i] = new RefereesClass (RefereeID, Firstname, Lastname,Qualification,matchesallocated,homeloc,loctovisit);
			RefereesClass Referee = fReferees[i];
			
			if(Referee.getFirstname() == Firstname)
				return Referee;
		}	
		return findReferee ( Firstname,  Lastname);
	}
	
	public  RefereesClass addReferee (String Firstname, String Lastname)
	{
		 RefereesClass fReferees[] = new RefereesClass[12];
		 
		 RefereesClass Referee = null;
		  if (numreferees<12)
		 { 
			 Referee = new RefereesClass(RefereeID, Firstname,  Lastname, Qualification, matchesallocated, homeloc, loctovisit);
		   if (Referee!=null)
		   	{ 
			fReferees[numreferees] = Referee;
			numreferees++; 
			}
		 }
		 return Referee;
	}
	
	 public  void  deleteReferee(String RefereeID, String Firstname, String Lastname)//method to delete a referee
	 {
		 RefereesClass fReferees[] = new RefereesClass[12];
		 boolean found = false;
		 int index = 0;
		 while (index<numreferees && !found)
		 {
		
			 fReferees[index] = new RefereesClass (RefereeID, Firstname, Lastname,Qualification,matchesallocated,homeloc,loctovisit);
	 		if((fReferees[0].getFirstname()==Firstname)) 	
	 		{
	 			fReferees[index] = fReferees[numreferees-1];
	 			numreferees--;
	 			found= true;				
	 		}
	 		else
	 			index++;
	 	}
	 	if (!found)
	 	{
	 		System.out.println("No Referee with Name " + Firstname +" "+ Lastname + " exists!");
	 	}
	 }

}

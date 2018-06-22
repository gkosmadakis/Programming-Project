public class RefereesTeam implements Comparable <RefereesTeam>
{
	
	private static final int RefereeID = 0;//initialize the ID
	public static final RefereesTeam[] RefereesTeam = null;
	RefereesTeam team = new RefereesTeam();// team is the object of this class
	public int length;	

	public int compareTo(RefereesTeam other)/*this is the method that compares Id referees in order to 
										be appeared in alphabetical order in the main GUI*/
	{		
		  int RefereeID = this.getRefereeID();
		  int otherRefereeID = other.getRefereeID();
		  if (RefereeID < otherRefereeID)
			   return -1;
		  else if (RefereeID == otherRefereeID)
			   return 0;
		  else
			   return 1;
	}

	public int  getRefereeID()//accessor method for RefereeID
	{
		return RefereeID;
	}
	
}
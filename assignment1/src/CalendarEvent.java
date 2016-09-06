/**
 * 
 * CalendarEvent implements the Comparable interface and composes
 * a CalendarDay. CalendarEvents are created by Calendar's populate method.
 * Attributes include all event info.
 * 
 * @author walter
 *
 */
class CalendarEvent implements Comparable<Object> {
	private int StartTime;
	private int EndTime;
	private String EventName;
	private String Location;
	private String Description;
	//------------------------------------
	/**
	 * 
	 * CalendarEvent's constructor
	 * 
	 * @param StartTime - e.g. 1100 or 1530
	 * @param EndTime - same as above
	 * @param EventName
	 * @param Location
	 * @param Description
	 */
	public CalendarEvent(int StartTime, int EndTime, String EventName, String Location, String Description) {
		super();
		this.StartTime = StartTime;
		this.EndTime = EndTime;
		this.EventName = EventName;
		this.Location = Location;
		this.Description = Description;
	}
	//------------------------------------
	/**
	 * compareTo(Object) method checks to see if entered event time
	 * overlaps with an existing event and returns 0 if it overlaps.
	 * If it doesn't overlap, then the method places the event in the
	 * correct order in the CalendarDay's list of events.
	 */
	public int compareTo(Object item) {
		//if events overlap, return 0
		//if not, return -1 if this event is earlier or 1 if this event is later
		int thisStart = StartTime;
		int thisEnd = EndTime;
		int thatStart = ((CalendarEvent)item).getStartTime();
		int thatEnd = ((CalendarEvent)item).getEndTime(); 
		if((thisStart == thatStart) || (thisEnd == thatEnd) || 
			((thisStart >= thatStart) && (thisStart < thatEnd)) || 
			((thisEnd > thatStart) && (thisEnd <= thatEnd))) {
			return 0;
		}
		else if(thisStart < thatStart) {
			return -1;
		}
		else
			return 1;
	}
	//------------------------------------
	/**
	 * Prints important event info, used in CalendarDay's toString() method
	 */
	public String toString() {
		return String.format("----------------------------------------------------------\n" +
				"Event:\t\t" + EventName + "\n" + 
				"Time:\t\t" + StartTime + " - " + EndTime + "\n" +
				"Location: \t" + Location + "\n" +
				"Description: \t" + Description + "\n"); 
	}
	//------------------------------------
	public int getStartTime() {
		return StartTime;
	}
	public void setStartTime(int n) {
		StartTime = n;
	}
	//------------------------------------
	public int getEndTime() {
		return EndTime;
	}
	public void setEndTime(int n) {
		EndTime = n;
	}
	//------------------------------------
	public String getEventName() {
		return EventName;
	}
	public void setEventName(String s) {
		EventName = s;
	}
	//------------------------------------
	public String getLocation() {
		return Location;
	}
	public void setLocation(String s) {
		Location = s;
	}
	//------------------------------------
	public String getDescription() {
		return Description;
	}
	public void setDescription(String s) {
		Description = s;
	}
}
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Calendar extends the LinkedList class and is composed of CalendarDay items that
 * are inserted into theCalendar LinkedList. Calendar also has FirstName and LastName
 * attributes that belong to the owner. Calendar has a number of methods described below.
 * 
 * @author walter
 *
 */

class Calendar extends LinkedList {
	private String FirstName;
	private String LastName;
	private LinkedList theCalendar = new LinkedList(); // populate method to initialize
	//------------------------------------
	/**
	 * printIntro() prints the Calendar's introduction.
	 */
	private void printIntro() {
		System.out.println("Congratulations " + FirstName +
				" " + LastName + " for creating a super awesome" +
				" Calendar!!");
	}
	//------------------------------------
	/**
	 * Calendar's constructor takes FirstName and LastName arguments.
	 * 
	 * @param FirstName - First name of owner
	 * @param LastName - Last name of owner
	 */
	public Calendar(String FirstName, String LastName) {
		super(); // do I need FirstName/lastName or does super() do that?
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.printIntro();
	}
	//------------------------------------
	/**
	 * populate(Scanner kb) takes a scanner argument and reads the console arguments or 
	 * file lines one at a time to enter new calendar events (and days if necessary) into
	 * Calendar
	 * 
	 * @param kb - keyboard java.util.Scanner
	 */
	public void populate(Scanner kb) { // am i making good use of the LinkedList insert method?
		System.out.println("===========================================================");
		System.out.println("Enter a new Calendar Event in the following example format:\n");
		System.out.println("1100 1300 6 28 2016 Lunch with the family");
		System.out.println("Cici Pizza");
		System.out.println("Meeting my wife and kids for lunch");
		System.out.println("===========================================================");
		System.out.println("ENTER -1 TO COMPLETE EVENT ENTRY\n");
		kb.nextLine(); // need this because nextInt() grabbed the 1 and need to move to the next line.
		String nextLine = kb.nextLine();
		while(!nextLine.equals("-1")) {
			String delims = "[ ]+";
			String[] tokens = nextLine.split(delims);
			int start = Integer.parseInt(tokens[0]);
			int end = Integer.parseInt(tokens[1]);
			int month = Integer.parseInt(tokens[2]);
			int day = Integer.parseInt(tokens[3]);
			int year = Integer.parseInt(tokens[4]);
			String eventName = kb.nextLine();
			String location = kb.nextLine();
			String description = kb.nextLine();
			//validate day
			CalendarDay entryDay = new CalendarDay(month, day, year);
			if(!validate(entryDay)) {
				System.out.println("Invalid date: Events can only be " +
					"scheduled today or in the future");
			}
			else { //validation passed
				//replace entryDay with existing day if already exists
				CalendarDay foundDay = this.find(entryDay); //DOES FIND WORK?
				CalendarEvent newEvent = new CalendarEvent(start, end, eventName, location, description);
				// if new day, no need to check if event overlaps so just add.
				if(foundDay == null) { 
					theCalendar.insert(entryDay); //days are NOT automatically ordered by LinkedList insert method
					entryDay.getEvents().insert(newEvent); //events are automatically ordered by LinkedList insert method
				}
				// if day already exists, need to check if event overlaps
				else {
					entryDay = foundDay; // reassign our entry day to the one that already exists
					//check if event already overlaps with existing event
					LinkedListNode currentEvent = entryDay.getEvents().head;
					//Traverse while a match is not found
					while(currentEvent.getNext() != null && currentEvent.getData().compareTo(newEvent) != 0) {
						currentEvent = currentEvent.getNext();
					}
					if(currentEvent.getData().compareTo(newEvent) != 0) {
						entryDay.getEvents().insert(newEvent);
					}
				}
			}
			nextLine = kb.nextLine();
		}
	}
	//------------------------------------
	/**
	 * validate checks that the date of an entered event is today or in the future
	 * 
	 * @param day - checks the CalendarDay that is passed as argument
	 * @return - returns true if the day is today or in the future or false otherwise
	 */
	private boolean validate(CalendarDay day) {
		DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date); 
		String delims = "[ ]+";
		String[] tokens = currentDate.split(delims);
		int currentDay = Integer.parseInt(tokens[0]);
		int currentMonth = Integer.parseInt(tokens[1]);
		int currentYear = Integer.parseInt(tokens[2]);
		if(day.getYear() < currentYear)
			return false;
		else if(day.getYear() > currentYear)
			return true;
		else {
			if(day.getMonth() < currentMonth)
				return false;
			else if(day.getMonth() > currentMonth)
				return true;
			else {
				if(day.getDay() < currentDay)
					return false;
				else
					return true;
			}
		}
	}
	//------------------------------------
	/**
	 * 
	 * remove deletes the event it is passed as well as the day if the day is then empty.
	 * void return as specified in UML.
	 * 
	 * @param startTime - start time of event
	 * @param month - month of event
	 * @param day - day of event
	 * @param year - year of event
	 */
	public void remove(int startTime, int month, int day, int year) {
		CalendarDay findDay = new CalendarDay(month, day, year);
		CalendarDay foundDay = find(findDay);
		LinkedListNode currentEvent = foundDay.getEvents().head;

		//Start traversal looking for event
		if(currentEvent != null) {
			
			//if event has more than just one event, no need to worry about removing day so do this one
			if(currentEvent.getNext() != null) {
				//While there is a next, check and keep going
				while(currentEvent.getNext() != null) {
					if(((CalendarEvent)currentEvent.getNext().getData()).getStartTime() == startTime) { 				
						LinkedListNode temp = currentEvent.getNext(); 
						currentEvent.setNext(temp.getNext());
						return;
						//UML says void method - return temp.getData();
					}
					currentEvent = currentEvent.getNext(); 
				}
				//If there is no next (I'm at the end, check this one
				if(((CalendarEvent)currentEvent.getData()).getStartTime() == startTime) { 				
					currentEvent.setNext(null);
					return;
				}
			}
			
			//if event only has one event, do this one so I can be ready to delete the day
			else {
				//remove matching event;
				if(((CalendarEvent)currentEvent.getData()).getStartTime() == startTime) { 				
					foundDay.getEvents().head = null;
	
					//now traverse Calendar list to remove empty day
					LinkedListNode currentDay = theCalendar.head;
					
					//traverse list
					while(currentDay.getNext() != null) {
						int thatMonth = (((CalendarDay)(currentDay.getNext().getData())).getMonth());
						int thatDay = (((CalendarDay)(currentDay.getNext().getData())).getDay());
						int thatYear = (((CalendarDay)(currentDay.getNext().getData())).getYear());
						if(foundDay.getMonth() == thatMonth && foundDay.getDay() == thatDay && foundDay.getYear() == thatYear) {
							if(currentDay.getNext().getNext() == null) {
								currentDay.setNext(null);
								return;
							}
							else {
								currentDay.setNext(currentDay.getNext().getNext());
								return;
							}
						}
						currentDay = currentDay.getNext();
					}
				}
			}
		}
	}
	//------------------------------------
	/**
	 * 
	 * find searches the Calendar for the CalendarDay it is passed and returns it or null
	 * 
	 * @param item - CalendarDay search query
	 * @return - returns existing Calendar day or null if doesn't exist
	 */
	public CalendarDay find(CalendarDay item) {
		//if no days
		if(theCalendar.head == null) 
			return null;
		//else calendar has days so start traverse
		else {
			LinkedListNode current = theCalendar.head;
			int thisDay = (((CalendarDay)(current.getData())).getDay());
			int thisMonth = (((CalendarDay)(current.getData())).getMonth());
			int thisYear = (((CalendarDay)(current.getData())).getYear());
			int thatDay = (item).getDay();
			int thatMonth = (item).getMonth();
			int thatYear = (item).getYear();
			//traverse entire list and exit loop when at last item
			while(current.getNext() != null) { //traverse the entire list
				if(thisDay == thatDay && thisMonth == thatMonth && thisYear == thatYear) {
					return (CalendarDay)current.getData();
				}
				current = current.getNext();
				thisDay = (((CalendarDay)(current.getData())).getDay());
				thisMonth = (((CalendarDay)(current.getData())).getMonth());
				thisYear = (((CalendarDay)(current.getData())).getYear());
			}
			//now at last item, need to test it!
			if(thisDay == thatDay && thisMonth == thatMonth && thisYear == thatYear) {
				return (CalendarDay)current.getData();
			}
			else {
				return null;
			}
		}
	}

	//------------------------------------
	/**
	 * 
	 * Returns a string of all the events scheduled for the CalendarDay.
	 * Uses CalendarDay's toString() method.
	 * 
	 * @param month - month of CalendarDay
	 * @param day - day of CalendarDay
	 * @param year - year of CalendarDay
	 * @return - returns String composed of event strings or "Nothing..." if no events
	 */
	public String DayToString(int month, int day, int year) {
		CalendarDay findDay = new CalendarDay(month, day, year);
		try {
			CalendarDay today = find(findDay);
			return today.toString();
		}
		catch(Exception e) {
			return new String("\n==========================================================\n" + 
						"Nothing to do on this day!\n" + 
							"==========================================================\n");
		}
	}
	//------------------------------------
	/**
	 * Returns a string of the entire calendar which is composed
	 * of all CalendarDay strings.
	 */
	public String toString() {
		String s = new String("===========================================================\n" + 
					this.getFirstName() + " " + this.getLastName() + "'s Calendar:\n");
		LinkedListNode current = theCalendar.head;
		s += current.getData();
		while(current.getNext() != null) {
			s += current.getNext().getData();
			current = current.getNext(); 
		}
		s += "================================================";
		return s;
	}
	//------------------------------------
	/**
	 * 
	 * @return - returns owner's first name
	 */
	public String getFirstName() {
		return FirstName;
	}
	//------------------------------------
	/**
	 * 
	 * @return - returns owner's last name
	 */
	public String getLastName() {
		return LastName;
	}
	//------------------------------------
	/**
	 * 
	 * @param FirstName - sets new owner first name
	 */
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	//------------------------------------
	/**
	 * 
	 * @param LastName - sets new owner last name
	 */
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
}
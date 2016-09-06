/**
 * 
 * CalendarDay is created when Calendar is populated with an event
 * on a day that does not yet exist. Its attributes are day, month, year,
 * and the LinkedList events that is composed of CalendarEvents.
 * CalendarDay class implements the Comparable interface and uses the
 * compareTo() method to order days in the Calendar.
 * 
 * @author walter
 *
 */
class CalendarDay implements Comparable<Object> {
	private int day;
	private int month;
	private int year;
	private LinkedList events = new LinkedList();
	//------------------------------------
	/**
	 * 
	 * The constructor for CalendarDay
	 * 
	 * @param month
	 * @param day
	 * @param year
	 */
	public CalendarDay(int month, int day, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	//------------------------------------
	/**
	 * Checks to see if the day already exists in the Calendar
	 * and then finds correct order of day in Calendar
	 * 
	 */
	public int compareTo(Object item) {
		int thatDay = ((CalendarDay)item).getDay();
		int thatMonth = ((CalendarDay)item).getMonth();
		int thatYear = ((CalendarDay)item).getYear();
		if(this.day == thatDay && this.month == thatMonth && this.year == thatYear) {
			return 0;
		}
		else {
			if(this.year < thatYear)
				return -1;
			else if(this.year > thatYear)
				return 1;
			else {
				if(this.month < thatMonth)
					return -1;
				else if(this.month > thatMonth)
					return 1;
				else {
					if(this.day < thatDay)
						return -1;
					else
						return 1;
				}
			}
		}
	}
	//------------------------------------
	/**
	 * Returns a string of the day and its events
	 * used by Calendar's DayToString() method.
	 */
	public String toString() { 
		String m = Integer.toString(month);
		String d = Integer.toString(day);
		if(m.length() == 1) 
			m = "0" + m;
		if(d.length() == 1)
			d = "0" + d;
		String out = "\n========================" + m + "/" + 
					d + "/" + year + "========================\n";
		out += events.toString();
		out += "----------------------------------------------------------\n";
		out += "==========================================================\n";
		return out;
	}
	//------------------------------------
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public LinkedList getEvents() {
		return events;
	}
	public void setEvents(LinkedList events) {
		this.events = events;
	}
}
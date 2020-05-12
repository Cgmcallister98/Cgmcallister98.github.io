import java.util.Calendar;

public class DateInfo {
	private int year;
	private int month;
	private int dayOfMonth;

	Calendar calendar = Calendar.getInstance();
	Calendar today = Calendar.getInstance();

	public DateInfo() {//no arg constructor
		 year = calendar.get(Calendar.YEAR);
		 month = calendar.get(Calendar.MONTH)+1;
		 dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	 }
	//parameters constructor
	 public DateInfo(int month, int dayOfMonth, int year) {
		 this.year = year; 
		 this.month = month;
		 this.dayOfMonth = dayOfMonth;
	 }
	 
	 public DateInfo(DateInfo date) {//copy constructor
		 year = date.year;
		 month = date.month;
		 dayOfMonth = date.dayOfMonth;
	 }
	
	public int compareDate(DateInfo maturityDate) {
		Calendar maturity = Calendar.getInstance();
		maturity.set(Calendar.MONTH, maturityDate.getMonth());
		maturity.set(Calendar.DAY_OF_MONTH, maturityDate.getDayOfMonth());
		maturity.set(Calendar.YEAR, maturityDate.getYear());
		if(today.equals(maturity) || today.after(maturity))
			return 1;
		else return 0;
	}
	
	public int clearCheckDate(DateInfo checkClearDate) {
		Calendar clearDate = Calendar.getInstance();
		clearDate.set(Calendar.MONTH, checkClearDate.getMonth()-1);
		clearDate.set(Calendar.DAY_OF_MONTH, checkClearDate.getDayOfMonth());
		clearDate.set(Calendar.YEAR, checkClearDate.getYear());
		clearDate.add(Calendar.MONTH, 6);
		if (today.before(clearDate)) {
			return 1;
		} else return 0;
	}
	
	//Setters
	public void setYear(int year) {
		calendar.set(Calendar.YEAR, year);
		this.year = calendar.get(Calendar.YEAR);
	}
	
	public void setMonth(int month) {
		calendar.set(Calendar.MONTH, month);
		this.month = calendar.get(Calendar.MONTH);
	}
	
	public void setDayOfMonth(int dayOfMonth) {
		calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	//Getters
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	
	public DateInfo getDate() {
		DateInfo myDate = new DateInfo(month, dayOfMonth, year);
		return myDate;
	}
	
	public String toString() {
		String str = String.format("%d/%d/%d", month, dayOfMonth, year);
		return str;
	}
}
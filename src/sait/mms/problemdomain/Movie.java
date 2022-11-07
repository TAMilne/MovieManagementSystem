package sait.mms.problemdomain;
/**
 * Movie Class for creating and describing Movie Objects
 * @author TravisM
 * @version April 20, 2022
 *
 */
public class Movie {
	private int duration;
	private String title;
	private int year;
	
	/**
	 * Builds Movie Object with empty values
	 */
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Builds Movie Object with assigned values
	 * @param duration Duration
	 * @param title Title
	 * @param year Year
	 * 
	 */
	public Movie(int duration, String title, int year) {
		super();
		this.duration = duration;
		this.title = title;
		this.year = year;
	}

	/**
	 * Returns Duration
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets Duration
	 * @param duration Duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Returns Title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets Movie Title
	 * @param title Title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns Year
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets Year
	 * @param year Year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Custom To String
	 * 
	 */
	@Override
	public String toString() {
		return String.format("%-16d %-8d %-25s", duration, year, title);
	}
	
	
}

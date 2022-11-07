package sait.mms.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import sait.mms.contracts.DatabaseDriver;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * Movie Management Class for Creating and Controlling Movie Management System
 * 
 * @author Travis Milne
 *
 */
public class MovieManagementSystem {
	private DatabaseDriver db;
	private Scanner input;

	/**
	 * Default Constructor for MMS
	 * 
	 * @throws SQLException
	 */
	public MovieManagementSystem() throws SQLException {
		this.db = new MariaDBDriver();
		this.db.connect();
		this.input = new Scanner(System.in);
	}

	/**
	 * Builds and Displays Main Menu for User
	 * 
	 * @throws SQLException
	 */
	public void displayMenu() throws SQLException {
		while (true) {
			System.out.print("\nJim's Movie Manager\n" + "1\tAdd New Movie\n" + "2\tPrint movies released in year\n"
					+ "3\tPrint random list of movies\n" + "4\tDelete a movie\n" + "5\tExit\n" + "\nEnter option: ");

			String userSelection = "";
			while (userSelection.equals("")) {
				userSelection = input.next();

				System.out.println();
				switch (userSelection) {
				case "1":
					addMovie();
					break;
				case "2":
					printMoviesInYear();
					break;
				case "3":
					printRandomMovies();
					break;
				case "4":
					deleteMovie();
					break;
				case "5":
					db.disconnect();
					System.out.println("Thank you, Have a GREAT day!");
					System.exit(0);
					break;
				default:
					System.out.println("That is not a valid option. Please try again.");
				}
			}
		}
	}

	/**
	 * Adds a Movie to the Database
	 */
	public void addMovie() {
		String title = "";
		int duration;
		int year;
		try {
			System.out.print("Enter movie title: ");
			title = input.nextLine();
			input.nextLine();

			System.out.print("Enter duration: ");
			duration = input.nextInt();

			do {
				System.out.print("Enter in year: ");
				year = input.nextInt();
			} while (year < 1930 || year > 2022);

			String query = "insert into movies (duration, title, year) values(" + String.format("%d", duration) + ", '"
					+ title + "', " + String.format("%d", year) + ");";
			int rows = db.update(query);

			System.out.println("\n" + rows + " movie(s) has been successfully added to the database!");
		} catch (SQLException e) {
			System.out.println("Unable able to add " + title + " to Movie List.");
		} catch (Exception e) {
			System.out.println("\nPlease enter a valid movie title, duration, and year.");
			addMovie();
		}
	}

	/**
	 * Prints a list of Movies Based upon year released
	 */
	public void printMoviesInYear() {
		int duration = 0;
		int year = 0;
		try {
			do {
				System.out.print("Enter in year: ");
				year = input.nextInt();
			} while (year < 1930 || year > 2022);

			String query = "Select * from movies";

			ResultSet rs = db.get(query);

			System.out.println();
			System.out.println("Movie List");
			System.out.printf("%-16s %-8s %-40s %n", "Duration", "Year", "Title");
			while (rs.next()) {
				if (rs.getInt(4) == year) {
					Movie movie = new Movie(rs.getInt(2), rs.getString(3), rs.getInt(4));
					System.out.println(movie);
					duration += rs.getInt(2);
				}
			}
			System.out.println("\nTotal Duration: " + duration + " minutes");
		} catch (Exception e) {
			System.out.print("\nInvalid year entry!\n");
			input.next();
		}
	}

	/**
	 * Prints an assigned numbered list of Random movies
	 */
	public void printRandomMovies() {
		int duration = 0;
		int numberOfMovies = 0;
		try {
			System.out.print("Enter number of movies: ");
			numberOfMovies = input.nextInt();

			System.out.println();

			String query = "SELECT * FROM movies ORDER BY RAND() LIMIT " + numberOfMovies + ";";

			ResultSet rs = db.get(query);
			System.out.printf("%-16s %-8s %-40s %n", "Duration", "Year", "Title");
			while (rs.next()) {
				Movie movie = new Movie(rs.getInt(2), rs.getString(3), rs.getInt(4));
				System.out.println(movie);
				duration += rs.getInt(2);
			}
			System.out.println("\nTotal Duration: " + duration + " minutes");
		} catch (Exception e) {
			System.out.println("\nInvalid entry!");
			input.next();
		}
	}

	/**
	 * Deletes Movie From Database based on ID
	 */
	public void deleteMovie() {
		int id = 0;
		try {
			do {
				System.out.print("Enter the movie ID that you want to delete: ");
				id = input.nextInt();
			} while (id == 0);
			String query2 = "DELETE FROM movies WHERE id =" + id + ";";
			int rows = db.update(query2);

			System.out.println("\nMovie " + id + " has been removed");
		} catch (SQLException e) {
			System.out.println("\nMovie Removal Failed.");
			input.next();
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid Movie Id to be removed from the list.");
			input.next();
		}
	}
}

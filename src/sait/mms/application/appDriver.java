package sait.mms.application;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;

import sait.mms.managers.MovieManagementSystem;
/**
 *  Main Driver of Movie Management System
 * @author Travis Milne
 * @version April 20, 2022
 */
public class appDriver {
	
	/**
	 * Creates Movie Management System Object and initiates Menu Display
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException  {
		MovieManagementSystem mms = new MovieManagementSystem();
		mms.displayMenu();
	}
}

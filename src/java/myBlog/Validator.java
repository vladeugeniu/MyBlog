
package myBlog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A validator class for password and uername
 */

public class Validator{

	  private Pattern pattern_username;
          private Pattern pattern_password;
	  private Matcher matcher;
          
          /**
           * Regex from Stack
           */
	  private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
          private static final String PASSWORD_PATTERN = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

	  public Validator(){
		  pattern_username = Pattern.compile(USERNAME_PATTERN);
                  pattern_password = Pattern.compile(PASSWORD_PATTERN);
	  }
      
          /**
           * 
           * @param username the string to be checked
           * @return if the given string is valid to be an username
           */
	 
	  public boolean validateUsername(final String username){

		  matcher = pattern_username.matcher(username);
		  return matcher.matches();

	  }
          
          /**
           * @param password the string to be checked
           * @return if the given string is valid to be a password
           */
          public boolean valiidatePassword(final String password){
              matcher = pattern_password.matcher(password);
              return matcher.matches();
          }
}
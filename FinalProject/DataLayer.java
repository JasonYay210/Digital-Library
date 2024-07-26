// Lily Chau, Nick Criss, Renee Bogdany, Chloe Calderon, Natnail Tolossa, Jason Chen
// ISTE 330 - Group 1

import java.util.*;
import java.sql.*;
import javax.swing.*;

import java.security.MessageDigest;
import java.math.BigInteger;


public class DataLayer {	
	private Connection conn;
	private ResultSet rs;
	private Statement stmt;
	private String sql;
	final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
   
  /* connect method - connect to database */
  public boolean connect(String databaseName, String userName, String password){
	      conn = null;
	
	      String url = "jdbc:mysql://localhost/" + databaseName;
	
	      url = url + "?serverTimezone=UTC"; //  Mac Users
	
	
	   try{
	         Class.forName(DEFAULT_DRIVER);
	         conn = DriverManager.getConnection(url, userName, password);
	         System.out.println("\nCreated Connection to the Faculty table in test3 Database!\n");
	         return true;
	      }
	      catch(ClassNotFoundException cnfe){
			 System.out.println("ERROR, CAN NOT CONNECT!!");
	         System.out.println("Class");
	         System.out.println("ERROR MESSAGE-> "+cnfe);
	         System.exit(0);
	      }
	      catch(SQLException sqle){
			 System.out.println("ERROR SQLException in connect()");
			 System.out.println("ERROR MESSAGE -> "+sqle);
	         sqle.printStackTrace();
	         System.exit(0);
	      }//end of catch
	
	      return (false);
	   } // End of connect method

   /* read faculty password - to get the faculty password */
	public String readFacultyPassword(String factID) {   // Username came fron the first GUI Input Box  = FacultyID
	       int result = 0;
	       String DBpassword = new String();
	       try {
	           PreparedStatement stmt2;
	           stmt2 = conn.prepareStatement("SELECT password FROM faculty WHERE facultyID = ?");
		       stmt2.setString(1,factID);
	           rs = stmt2.executeQuery(); 
	           while(rs.next()) {
			      DBpassword = rs.getString(1);
			     }//end of while loop
	         }// endn of try
	        catch(Exception e)
	         {
				 System.out.println("Error while getting password from database");
				 System.out.println("Error message is --> "+e);
			}//end of catch		
			return DBpassword;
    }// end of method
    

/* CODE FOR FACULTY PORTION */


    // function to add a faculty member into the database
    public boolean insertFacultyRecord(String fname, String lname, String username, String email, String password, int buildingNum, int officeNum, String workNum, String cellNum, String officeHrs) {
        boolean result = true;
        int x = 0;
        try {
           PreparedStatement stmt3;
           stmt3 = conn.prepareStatement("INSERT INTO faculty (firstName, lastName, username, password, email, buildingNumber, officeNumber, work_num, cell_num, office_hours) VALUES(?,?,?,?,?,?,?,?,?,?)");
            stmt3.setString(1,fname);
            stmt3.setString(2,lname);
            stmt3.setString(3,username);
          stmt3.setString(4,email);   
            stmt3.setString(5,password);
          stmt3.setInt(6,buildingNum);
          stmt3.setInt(7,officeNum);
          stmt3.setString(8,workNum);
          stmt3.setString(9,cellNum);
          stmt3.setString(10,officeHrs);
            x = stmt3.executeUpdate();     // Performs the update command
            if (x > 0) {
               result = true;
              System.out.println("\nSuccessfully added faculty member.");
            } else {
               result = false;
           }
        }// end of try
        catch(Exception e) {
              System.out.println("Error while trying to insert a record or close.");
              System.out.println("Error message is --> "+e);
              result = false;
        }//end of catch
         
        return result;
    }// end of method

    // method to update faculty
      public void updateFaculty(String faculty_ID, String firstName, String lastName, int buildingNumber, String password) {
           try {
             String sql = ("UPDATE faculty SET firstName=?, lastName=?, buildingNumber=?, password=? WHERE faculty_ID=?");
              PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,firstName);
                ps.setString(2,lastName);
                ps.setInt(3,buildingNumber);   
                ps.setString(4,password);
                ps.setString(5,faculty_ID);
                
               int x = ps.executeUpdate();     // Performs the update command
	            if (x > 0) {
                 System.out.println("\nSuccessfully updated faculty member.");
	            } else {
               }
                

           }// end of try
           catch(Exception e) {
                 System.out.println("Error while trying to update a record.");
                 System.out.println("Error message is --> "+e);
            }//end of catch
            
       //return result;
    }// end of method    

    // method to add a faculty abstract
    public void addAbstract(int faculty_ID, String abstractTitle, String abstractContent) {
        
        int abstract_ID = 0;
        
        try {
        
           stmt = conn.createStatement();
           
           String sql = "INSERT INTO abstract (abstractTitle, abstractContent) VALUES (?,?)";
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, abstractTitle);
           ps.setString(2, abstractContent);
           
           int result = ps.executeUpdate();
           System.out.println("Faculty abstract was inserted.");
           
            //check if it has been successfully added
         if(result == 1) {
            //System.out.println("Specialty successfully added.");
            sql = "SELECT abstract_ID FROM abstract where abstractTitle = " + '"' + abstractTitle + '"' + ";";
            rs = stmt.executeQuery(sql);
            // get the candidate_ID and return it
            while(rs.next()) {
               abstract_ID = rs.getInt(1);
            }
            System.out.println("Abstract ID: " + abstract_ID);
            
            // insert into student_specialty table
            String insertQuery2 = "INSERT INTO faculty_abstract (faculty_ID, abstract_ID) VALUES (?,?)";
            PreparedStatement ps2 = conn.prepareStatement(insertQuery2);
            ps2.setInt(1, faculty_ID);
            ps2.setInt(2, abstract_ID);
            
            int result2 = ps2.executeUpdate();
            
            }

           
        } catch(SQLException sqle) { 
           System.out.println("Unable to insert abstract.");
           System.out.println("Error: " + sqle);
           sqle.printStackTrace();
        } // end of try catch
    } //end of addAbstract

    // method for faculty to add a specialty
    public void addFacultySpecialty(int faculty_ID, String specialties) {
    
    String[] specialtyIds = specialties.split(",");
      int specialty_ID = 0;
      
      
      // loop through the specialties & add to table
      for (String specialty: specialtyIds) {
         try {
            
            stmt = conn.createStatement();
         
            String insertQuery = "INSERT INTO specialty (specialty) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            ps.setString(1, specialty);
            
            int result = ps.executeUpdate();
            //check if it has been successfully added
         if(result == 1) {
            System.out.println("Specialty successfully added.");
            sql = "SELECT specialty_ID FROM specialty where specialty = " + '"' + specialty + '"' + ";";
            rs = stmt.executeQuery(sql);
            // get the candidate_ID and return it
            while(rs.next()) {
               specialty_ID = rs.getInt(1);
            }
            System.out.println("Specialty ID: " + specialty_ID);
            
            // insert into student_specialty table
            String insertQuery2 = "INSERT INTO faculty_specialty (faculty_ID, specialty_ID) VALUES (?,?)";
            PreparedStatement ps2 = conn.prepareStatement(insertQuery2);
            ps2.setInt(1, faculty_ID);
            ps2.setInt(2, specialty_ID);
            
            int result2 = ps2.executeUpdate();     
            
         } else {
            // print error
            System.out.println("Candidate could not be added.");
            //return -1;
         
         } //end of if else 
            
         } catch(SQLException sqle) { 
            System.out.println("Unable to insert specialty.");
            System.out.println("Error: " + sqle);
            sqle.printStackTrace();
         } // end of try catch
      } // end of for
    } // end of addFacultyspecialty
   
   // method to insert a faculty abstract
    public void insertFacultyAbstract(int faculty_ID , int abstract_ID) {
        String insertQuery = "INSERT INTO faculty_abstract (faculty_ID, abstract_ID) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setInt(1, faculty_ID);
                stmt.setInt(2, abstract_ID);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }


/* CODE FOR STUDENT PORTION */


    // function to add a student into the database
    public void addStudent(String lastName, String firstName, String username, String password, String email, String portfolio_link) {
    
      int student_ID = 0;
      
      int result = 0;
      try {
         
         // adding a student
         String insertQuery = "INSERT INTO student (lastName, firstName, userName, password, email, portfolio_link) VALUES (?,?,?,?,?,?)";
         //prepared statement
         PreparedStatement ps = conn.prepareStatement(insertQuery);
         ps.setString(1, lastName);
         ps.setString(2, firstName);
         ps.setString(3, username);
         ps.setString(4, password);
         ps.setString(5, email);
         ps.setString(6, portfolio_link);
         
         result = ps.executeUpdate();
         
         if(result > 0) {
            System.out.println("\nSuccessfully added student.");
         }
         
      } catch(SQLException sqle) { 
         System.out.println("Unable to add student.");
         System.out.println("Error: " + sqle);
         sqle.printStackTrace();
         //return -1; 
      } //end of try catch
      
    }

/* method to add one or multiple specialities to a student returns void */
    public void addStudentSpecialty(int student_ID, String specialties) {
        
      String[] specialtyIds = specialties.split(",");
      int specialty_ID = 0;
      
      
      // loop through the specialties & add to table
      for (String specialty: specialtyIds) {
         try {
            
            stmt = conn.createStatement();
         
            String insertQuery = "INSERT INTO specialty (specialty) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            ps.setString(1, specialty);
            
            int result = ps.executeUpdate();
            //check if it has been successfully added
         if(result == 1) {
            System.out.println("Specialty successfully added.");
            sql = "SELECT specialty_ID FROM specialty where specialty = " + '"' + specialty + '"' + ";";
            rs = stmt.executeQuery(sql);
            // get the candidate_ID and return it
            while(rs.next()) {
               specialty_ID = rs.getInt(1);
            }
            System.out.println("Specialty ID: " + specialty_ID);
            
            // insert into student_specialty table
            String insertQuery2 = "INSERT INTO student_specialty (student_ID, specialty_ID) VALUES (?,?)";
            PreparedStatement ps2 = conn.prepareStatement(insertQuery2);
            ps2.setInt(1, student_ID);
            ps2.setInt(2, specialty_ID);
            
            int result2 = ps2.executeUpdate();
            
            
         } else {
            // print error
            System.out.println("Candidate could not be added.");
            //return -1;
         
         } //end of if else 

            
         } catch(SQLException sqle) { 
            System.out.println("Unable to insert specialty.");
            System.out.println("Error: " + sqle);
            sqle.printStackTrace();
         } // end of try catch
      } 
    } // end of addStudentspecialty()

    
/* OUTSIDE ORGANIZATIONS*/ 


    /* add organization method - to add an organization to the DB */
    public void addOrganization(String organization, String keyword) {
        try {
            // Assuming conn is already established in the connect method
            String insertQuery = "INSERT INTO organization (organization, keyword) VALUES (?, ?)";

            // Using PreparedStatement to safely execute the query
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

            // Setting values for the placeholders in the query
            preparedStatement.setString(1, organization);
            preparedStatement.setString(2, keyword);

            // Executing the query
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully!");

            // Closing the PreparedStatement
            preparedStatement.close();
        } catch (SQLException sqle) {
            System.out.println("Error in getOrganizationInfo()");
            System.out.println("ERROR MESSAGE -> " + sqle);
            sqle.printStackTrace();
        }
    }
    
    /* to get organization keyword */
    public String getOrganizationKeyword(int id) {
      try {
            // Assuming conn is already established in the connect method
            String insertQuery = "SELECT keyword FROM organization WHERE organization_ID=?";

            // Using PreparedStatement to safely execute the query
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

            // Setting values for the placeholders in the query
            preparedStatement.setInt(1, id);

            // Executing the query=
            
             rs = preparedStatement.executeQuery();
             
             String keyword = "";

            while (rs.next()) {
               keyword = rs.getString("keyword");
            }
            preparedStatement.close();
            return keyword;
            
        } catch (SQLException sqle) {
            System.out.println("Error in getOrganizationKeyword()");
            System.out.println("ERROR MESSAGE -> " + sqle);
            sqle.printStackTrace();
            return "";
        }    
    
    }

/* SPECIALTY, KEYWORD, AND ABSTRACT SEARCHES */

   // for student to search for faculty based on a keyword in the facultys abstract
      public String searchKeyWords(String keyWord){
         StringBuilder result = new StringBuilder();

         try{
            sql = "SELECT * FROM faculty JOIN faculty_abstract USING(faculty_ID) JOIN abstract USING(abstract_ID) WHERE abstractContent REGEXP ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, keyWord);
            
            rs = ps.executeQuery();

         // checking if we found any matching specialties
         boolean hasMatches = false;
         while (rs.next()) {
               hasMatches = true;
               String firstName = rs.getString("firstName");
               String lastName = rs.getString("lastName");
               int buildingNumber = rs.getInt("buildingNumber");
               int officeNumber = rs.getInt("officeNumber");
               String email = rs.getString("email");

               result.append("Faculty found: ").append(firstName).append(" ").append(lastName)
                     .append(",\nBuilding Number: ").append(buildingNumber).append(", Office Number: ")
                     .append(officeNumber).append("\nEmail: ").append(email).append("\n");
         }

         if (!hasMatches) {
               result.append("No matches found for ").append(keyWord);
         }
            ps.close();
         } catch(SQLException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
         }
         return result.toString();
      }

   // for students to match with a faculty based on specialty
      public String checkSpecialtyPairing(String specialty) {
        StringBuilder result = new StringBuilder();
        // get the specialty ID from the specialty table 
        try { 
        String getSecialtyIDQuery = "SELECT specialty_ID FROM specialty WHERE specialty = ?";
        PreparedStatement ps = conn.prepareStatement(getSecialtyIDQuery);
        ps.setString(1, specialty);
        
        ResultSet rs = ps.executeQuery();

        // checking if the specialty exists
        if (!rs.next()) {
            return "Specialty not found: " + specialty;
        }

        int specialtyID = rs.getInt("specialty_ID");
        // checking for matches between faculty specialty and student
        String checkQuery = "SELECT f.firstName, f.lastName, f.buildingNumber, f.officeNumber, f.email FROM faculty f " +
                            "JOIN faculty_specialty fs ON f.faculty_ID = fs.faculty_ID " +
                            "WHERE fs.specialty_ID = ? AND fs.specialty_ID IN (SELECT student_ID FROM student_specialty)";
        
        ps = conn.prepareStatement(checkQuery);
        ps.setInt(1, specialtyID);

        rs = ps.executeQuery();

        // checking if we found any matching specialties
        boolean hasMatches = false;
        while (rs.next()) {
            hasMatches = true;
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int buildingNumber = rs.getInt("buildingNumber");
            int officeNumber = rs.getInt("officeNumber");
            String email = rs.getString("email");
            
            result.append("Faculty found: ").append(firstName).append(" ").append(lastName)
                  .append(",\nBuilding Number: ").append(buildingNumber).append(", Office Number: ")
                  .append(officeNumber).append("\nEmail: ").append(email).append("\n");
        }

        if (!hasMatches) {
            result.append("No matches found for ").append(specialty);
        }

        } catch(SQLException sqle) { 
            System.out.println("Unable to check specialty.");
            System.out.println("Error: " + sqle);
            sqle.printStackTrace();
        } // end of try catch
        return result.toString();

   } //end of get faculty pairing info


   // for faculty to search for a student by their interest
      public String searchByInterest(String facultyInterest) {
         StringBuilder result = new StringBuilder();

        try {
            String sql = "SELECT s.firstName, s.lastName, s.email, s.portfolio_link " +
                        "FROM student s " +
                        "JOIN student_specialty ss ON s.student_ID = ss.student_ID " +
                        "JOIN specialty sp ON ss.specialty_ID = sp.specialty_ID " +
                        "WHERE sp.specialty = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, facultyInterest);

            ResultSet resultSet = statement.executeQuery();
            
            boolean hasMatches = false;
            while (resultSet.next()) {
               hasMatches = true;
               String firstName = resultSet.getString("firstName");
               String lastName = resultSet.getString("lastName");
               String email = resultSet.getString("email");
               String portfolioLink = resultSet.getString("portfolio_link");
               
               result.append("Student Name: ").append(firstName).append(" ").append(lastName).append(", Email: ").append(email).append(", Portfolio Link: ").append(portfolioLink).append("\n");
            }

            if (!hasMatches) {
                  result.append("No matches for ").append(facultyInterest);
            }
         } catch (SQLException e) {
            e.printStackTrace(); 
         }
         return result.toString();

   }
    
    /* FOR LOGGING IN */
    
    public boolean checkUsername(String username, String role) {
      String sql = "";
      try {

    
      switch(role) {
         case "Faculty":
            sql = "SELECT username FROM faculty";
            break;
         case "Student":
            sql = "SELECT userName FROM student";
            break;
         default:
            break;      
      }
      
           
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                if(username.equals(resultSet.getString("username")))
                  return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real application
            return false;
        }

    
    }
    
    // checks to see if the password and username match the corresponding user
    public boolean checkPassword(String username, String password, String role) {
      String sql = "";
      boolean check = false;
      
      switch(role) {
         case "Faculty":
            sql = "SELECT password FROM faculty WHERE username = ?";
            break;
         case "Student":
            sql = "SELECT password FROM student WHERE userName = ?";
            break;
         default:
            break;      
      }
    
     try {
      PreparedStatement statement = conn.prepareStatement(sql);  
      statement.setString(1, username); 
           
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
               String pass = resultSet.getString("password");
               //PresentationLayer2 pl = new PresentationLayer2();
               String encryptedPassword = encrypt(password);
               System.out.println(pass);
               System.out.println(encryptedPassword);
               
               if(pass.equals(encryptedPassword)) {
                  check = true;
               } else {
                  check = false;
               }
               
            }
         
        } catch(SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real application
            return false;
        }

      return check;
    
    }
    
    // Password Encryption
    public static String encrypt(String secret) {
        String shal = "";
        String value = new String(secret);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            shal = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shal;
    }

   /* 
    * close method
    * closes connection to the database
    * returns true or false
    */
   public boolean close() {
      try {
      // check if connection was open
         if(conn != null) { 
            conn.close();
         }
      } catch(SQLException sqle) {
         System.out.println("Error: " + sqle);
         return false;
      }     
      return true;      
   } //close
}//end of class
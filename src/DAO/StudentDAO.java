package DAO;

import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionManager;
import Model.Student;


public class StudentDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;

	static String studentEmail, studentPassword, studentName, studentAddress, studentPhone;
	static boolean valid;
	
	 //login
    public static Student login(Student bean) throws NoSuchAlgorithmException {
    	
    	studentEmail = bean.getStudentEmail();
    	studentPassword = bean.getStudentPassword();

        //convert the password to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(studentPassword.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String pass = sb.toString();
        String searchQuery = "select * from students where studentEmail='" + studentEmail + "' AND studentPassword='" + pass + "'";

        System.out.println("Your email is " + studentEmail);
        System.out.println("Your password is " + studentPassword);
        System.out.println("Query: " + searchQuery);

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String studentEmail = rs.getString("studentEmail");
           
                bean.setStudentEmail(studentEmail);
                bean.setStudentID(rs.getInt("id"));
                bean.setStudentName(rs.getString("studentName"));
                bean.setValid(true);
           	}
           
            // if user does not exist set the isValid variable to false
            else if (!more) {
            	System.out.println("Sorry, you are not a registered user! Please sign up first");
            	bean.setValid(false);
            }
           
        }

        catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }

        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }

        return bean;
    }
    
	//register student
	public static void add(Student bean) throws NoSuchAlgorithmException {
		
		studentEmail = bean.getStudentEmail();
		studentPassword = bean.getStudentPassword();
		studentName = bean.getStudentName();
		studentAddress = bean.getStudentAddress();
		studentPhone = bean.getStudentPhone();

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(studentPassword.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		String pass = sb.toString();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement(
					"insert into students (studentEmail, studentPassword, studentName, studentAddress, studentPhone)" + " values(?,?,?,?,?)");
			ps.setString(1, studentEmail);
			ps.setString(2, pass);
			ps.setString(3, studentName);
			ps.setString(4, studentAddress);
			ps.setString(5, studentPhone);
			ps.executeUpdate();

		}

		catch (Exception ex) {
			System.out.println("failed: An Exception has occured!" + ex);
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					ps = null;
				}
				if (currentCon != null) {
					try {
						currentCon.close();
					} catch (Exception e_) {
						currentCon = null;
					}
				}
			}
		}
	}
	
	 //get user by email
    public static Student getStudentByEmail(String studentEmail) {
    	Student student = new Student();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from students where studentEmail=?");
            
            ps.setString(1, studentEmail);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
            	student.setStudentEmail(rs.getString("studentEmail"));
            	student.setStudentPassword(rs.getString("studentPassword"));
            	student.setStudentName(rs.getString("studentName"));
            	student.setStudentAddress(rs.getString("studentAddress"));
            	student.setStudentPhone(rs.getString("studentPhone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
    
 //getallaccount
    
    public static Student getStudent() {
    	Student student = new Student();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from students");
          
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	student.setStudentEmail(rs.getString("studentEmail"));
            	student.setStudentPassword(rs.getString("studentPassword"));
               // teacher.setAddress(rs.getString("address"));
               // teacher.setPhone(rs.getString("phone"));
                //teacher.setPassword(rs.getString("password"));
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
    
    	//update account
        public static void updateAccount(Student bean) throws NoSuchAlgorithmException {

        	studentEmail = bean.getStudentEmail();
    		studentPassword = bean.getStudentPassword();
    		studentName = bean.getStudentName();
    		studentAddress = bean.getStudentAddress();
    		studentPhone = bean.getStudentPhone();
        	String searchQuery = "";
        	        	
        	searchQuery = "UPDATE students SET studentName ='"+ studentName +"', studentAddress='" + studentAddress + "', studentPhone='" + studentPhone + "'";
        	
        	try {

                currentCon = ConnectionManager.getConnection();
                stmt = currentCon.createStatement();
                stmt.executeUpdate(searchQuery);
                System.out.println(searchQuery);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
			public static Student getStudent(Student bean)  {
			        	
						studentEmail = bean.getStudentEmail();
			
			            String searchQuery = "select * from students where email='" + studentEmail + "'";
			
			            try {
			                currentCon = ConnectionManager.getConnection();
			                stmt = currentCon.createStatement();
			                rs = stmt.executeQuery(searchQuery);
			                boolean more = rs.next();
			
			                // if user exists set the isValid variable to true
			                if (more) {
			                	String studentEmail = rs.getString("studentEmail");
			               
			                    bean.setStudentEmail(studentEmail);
			                    bean.setValid(true);
			               	}
			               
			                else if (!more) {
			                	System.out.println("Sorry");
			                	bean.setValid(false);
			                }
			               
			            }
			
			            catch (Exception ex) {
			                System.out.println("Log In failed: An Exception has occurred! " + ex);
			            }
			
			            finally {
			                if (rs != null) {
			                    try {
			                        rs.close();
			                    } catch (Exception e) {
			                    }
			                    rs = null;
			                }
			
			                if (stmt != null) {
			                    try {
			                        stmt.close();
			                    } catch (Exception e) {
			                    }
			                    stmt = null;
			                }
			
			                if (currentCon != null) {
			                    try {
			                        currentCon.close();
			                    } catch (Exception e) {
			                    }
			
			                    currentCon = null;
			                }
			            }
			
			            return bean;
			        }
			
			//allstudent
	    	 public static List<Student> getAllStudent() {
	   		  List<Student> students = new ArrayList<Student>();
	   		  
	   		  try {
	   		  	currentCon = ConnectionManager.getConnection();
	   		  	stmt = currentCon.createStatement();
	   		  
	   		  	  String q = "select * from students";
	   		      ResultSet rs = stmt.executeQuery(q);
	   		      
	   		      while (rs.next()) {
	   		    	Student student = new Student();
	   		          	   		         
	   		    	student.setStudentEmail(rs.getString("studentEmail"));
	            	student.setStudentPassword(rs.getString("studentPassword"));
	            	student.setStudentName(rs.getString("studentName"));
	            	student.setStudentAddress(rs.getString("studentAddress"));
	            	student.setStudentPhone(rs.getString("studentPhone"));
	            		students.add(student);
	   		      }
	   		  } catch (SQLException e) {
	   		      e.printStackTrace();
	   		  }

	   		  return students;
	   	}
			
			//update pass
    	    public static void updatePass(Student bean) throws NoSuchAlgorithmException {

    	    	
    	    	studentEmail = bean.getStudentEmail();
    	    	studentPassword = bean.getStudentPassword();
    	    	
    	    	
    	    	//convert the password to MD5
    	        MessageDigest md = MessageDigest.getInstance("MD5");
    	        md.update(studentPassword.getBytes());
    	 
    	        byte byteData[] = md.digest();
    	 
    	        //convert the byte to hex format
    	        StringBuffer sb = new StringBuffer();
    	        for (int i = 0; i < byteData.length; i++) {
    	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    	        }
    	        
    	        String pass = sb.toString();
    	       
    	        String searchQuery = "UPDATE students SET  password='" + studentPassword +"' WHERE email = '" + studentEmail + "'";
    	       
    	    	try {

    	            currentCon = ConnectionManager.getConnection();
    	            stmt = currentCon.createStatement();
    	            stmt.executeUpdate(searchQuery);
    	            
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }
}

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
import Model.Teacher;


public class TeacherDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;

	static String teacherEmail, teacherPassword, teacherName, teacherAddress, teacherPhone, teacherRole;
	static boolean valid;
	
	 //login
    public static Teacher login(Teacher bean) throws NoSuchAlgorithmException {
    	
    	teacherEmail = bean.getTeacherEmail();
    	teacherPassword = bean.getTeacherPassword();

        //convert the password to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(teacherPassword.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String pass = sb.toString();
        String searchQuery = "select * from teachers where teacherEmail='" + teacherEmail + "' AND teacherPassword='" + pass + "'";

        System.out.println("Your email is " + teacherEmail);
        System.out.println("Your password is " + teacherPassword);
        System.out.println("Query: " + searchQuery);

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String teacherEmail = rs.getString("teacherEmail");
            	String teacherRole = rs.getString("teacherRole");
           
                bean.setTeacherEmail(teacherEmail);
                bean.setTeacherRole(teacherRole);
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
    
	//register teacher
	public static void add(Teacher bean) throws NoSuchAlgorithmException {
		
		teacherEmail = bean.getTeacherEmail();
		teacherPassword = bean.getTeacherPassword();
		teacherName = bean.getTeacherName();
		teacherAddress = bean.getTeacherAddress();
		teacherPhone = bean.getTeacherPhone();
		teacherRole = bean.getTeacherRole();

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(teacherPassword.getBytes());

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
					"insert into teachers (teacherEmail, teacherPassword, teacherName, teacherAddress, teacherPhone, teacherRole)" + " values(?,?,?,?,?,?)");
			ps.setString(1, teacherEmail);
			ps.setString(2, pass);
			ps.setString(3, teacherName);
			ps.setString(4, teacherAddress);
			ps.setString(5, teacherPhone);
			ps.setString(6, teacherRole);
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
    public static Teacher getTeacherByEmail(String teacherEmail) {
        Teacher teacher = new Teacher();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from teachers where teacherEmail=?");
            
            ps.setString(1, teacherEmail);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                teacher.setTeacherEmail(rs.getString("teacherEmail"));
                teacher.setTeacherPassword(rs.getString("teacherPassword"));
                teacher.setTeacherName(rs.getString("teacherName"));
                teacher.setTeacherAddress(rs.getString("teacherAddress"));
                teacher.setTeacherPhone(rs.getString("teacherPhone"));
                teacher.setTeacherRole(rs.getString("teacherRole"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }
    
 //getallaccount
    
    public static Teacher getTeacher() {
        Teacher teacher = new Teacher();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from teachers");
          
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	teacher.setTeacherEmail(rs.getString("teacherEmail"));
                teacher.setTeacherPassword(rs.getString("teacherPassword"));
               // teacher.setAddress(rs.getString("address"));
               // teacher.setPhone(rs.getString("phone"));
                //teacher.setPassword(rs.getString("password"));
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }
    
    	//update account
        public static void updateAccount(Teacher bean) throws NoSuchAlgorithmException {

        	teacherEmail = bean.getTeacherEmail();
        	teacherName = bean.getTeacherName();
        	teacherAddress = bean.getTeacherAddress();
        	teacherPhone = bean.getTeacherPhone();
        	String searchQuery = "";
        	
            searchQuery = "UPDATE teachers SET teacherName ='"+ teacherName +"', teacherAddress='" + teacherAddress + "', teacherPhone='" + teacherPhone + "' WHERE teacherEmail= '" + teacherEmail + "'";
       	   
        	
        	try {

                currentCon = ConnectionManager.getConnection();
                stmt = currentCon.createStatement();
                stmt.executeUpdate(searchQuery);
                System.out.println(searchQuery);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
			public static Teacher getTeacher(Teacher bean)  {
			        	
			    		teacherEmail = bean.getTeacherEmail();
			
			            String searchQuery = "select * from teachers where email='" + teacherEmail + "'";
			
			            try {
			                currentCon = ConnectionManager.getConnection();
			                stmt = currentCon.createStatement();
			                rs = stmt.executeQuery(searchQuery);
			                boolean more = rs.next();
			
			                // if user exists set the isValid variable to true
			                if (more) {
			                	String teacherEmail = rs.getString("teacherEmail");
			               
			                    bean.setTeacherEmail(teacherEmail);
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
			
			//allteacher
	    	 public static List<Teacher> getAllTeacher() {
	   		  List<Teacher> teachers = new ArrayList<Teacher>();
	   		  
	   		  try {
	   		  	currentCon = ConnectionManager.getConnection();
	   		  	stmt = currentCon.createStatement();
	   		  
	   		  	  String q = "select * from teachers";
	   		      ResultSet rs = stmt.executeQuery(q);
	   		      
	   		      while (rs.next()) {
	   		          Teacher teacher = new Teacher();
	   		          	   		         
	   		        teacher.setTeacherEmail(rs.getString("teacherEmail"));
	                teacher.setTeacherPassword(rs.getString("teacherPassword"));
	                teacher.setTeacherName(rs.getString("teacherName"));
	                teacher.setTeacherAddress(rs.getString("teacherAddress"));
	                teacher.setTeacherPhone(rs.getString("teacherPhone"));
	                teacher.setTeacherRole(rs.getString("teacherRole"));
	   		          teachers.add(teacher);
	   		      }
	   		  } catch (SQLException e) {
	   		      e.printStackTrace();
	   		  }

	   		  return teachers;
	   	}
			
			//update pass
    	    public static void updatePass(Teacher teacher) throws NoSuchAlgorithmException {

    	    	
    	      	teacherEmail = teacher.getTeacherEmail();
    	    	teacherPassword = teacher.getTeacherPassword();
    	    	
    	    	
    	    	//convert the password to MD5
    	        MessageDigest md = MessageDigest.getInstance("MD5");
    	        md.update(teacherPassword.getBytes());
    	 
    	        byte byteData[] = md.digest();
    	 
    	        //convert the byte to hex format
    	        StringBuffer sb = new StringBuffer();
    	        for (int i = 0; i < byteData.length; i++) {
    	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    	        }
    	        
    	        String pass = sb.toString();
    	       
    	        String searchQuery = "UPDATE teachers SET  password='" + teacherPassword +"' WHERE email = '" + teacherEmail + "'";
    	       
    	    	try {

    	            currentCon = ConnectionManager.getConnection();
    	            stmt = currentCon.createStatement();
    	            stmt.executeUpdate(searchQuery);
    	            
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }
}

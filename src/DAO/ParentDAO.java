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
import Model.Parent;


public class ParentDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;

	static String parentEmail, parentPassword, parentName, parentAddress, parentPhone;
	static boolean valid;
	
	 //login
    public static Parent login(Parent bean) throws NoSuchAlgorithmException {
    	
    	parentEmail = bean.getParentEmail();
    	parentPassword = bean.getParentPassword();

        //convert the password to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(parentPassword.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String pass = sb.toString();
        String searchQuery = "select * from parents where parentEmail='" + parentEmail + "' AND parentPassword='" + pass + "'";

        System.out.println("Your email is " + parentEmail);
        System.out.println("Your password is " + parentPassword);
        System.out.println("Query: " + searchQuery);

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String parentEmail = rs.getString("parentEmail");
           
                bean.setParentEmail(parentEmail);
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
    
	//register parent
	public static void add(Parent bean) throws NoSuchAlgorithmException {
		
		parentEmail = bean.getParentEmail();
		parentPassword = bean.getParentPassword();
		parentName = bean.getParentName();
		parentAddress = bean.getParentAddress();
		parentPhone = bean.getParentPhone();

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(parentPassword.getBytes());

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
					"insert into parents (parentEmail, parentPassword, parentName, parentAddress, parentPhone)" + " values(?,?,?,?,?)");
			ps.setString(1, parentEmail);
			ps.setString(2, pass);
			ps.setString(3, parentName);
			ps.setString(4, parentAddress);
			ps.setString(5, parentPhone);
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
    public static Parent getParentByEmail(String parentEmail) {
    	Parent parent = new Parent();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from parents where parentEmail=?");
            
            ps.setString(1, parentEmail);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
            	parent.setParentEmail(rs.getString("parentEmail"));
            	parent.setParentPassword(rs.getString("parentPassword"));
            	parent.setParentName(rs.getString("parentName"));
            	parent.setParentAddress(rs.getString("parentAddress"));
            	parent.setParentPhone(rs.getString("parentPhone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parent;
    }
    
 //getallaccount
    
    public static Parent getParent() {
    	Parent parent = new Parent();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from parents");
          
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	parent.setParentEmail(rs.getString("parentEmail"));
            	parent.setParentPassword(rs.getString("studentPassword"));
               // parent.setParentAddress(rs.getString("parentAddress"));
               // parent.setParentPhone(rs.getString("parentPhone"));
                //parent.setParentPassword(rs.getString("parentPassword"));
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parent;
    }
    
    	//update account
        public static void updateAccount(Parent bean) throws NoSuchAlgorithmException {

        	parentEmail = bean.getParentEmail();
        	parentPassword = bean.getParentPassword();
        	parentName = bean.getParentName();
        	parentAddress = bean.getParentAddress();
        	parentPhone = bean.getParentPhone();
        	String searchQuery = "";
        	
        	searchQuery = "UPDATE parents SET parentName ='"+ parentName +"', parentAddress='" + parentAddress + "', parentPhone='" + parentPhone + "'";
        	
        	try {

                currentCon = ConnectionManager.getConnection();
                stmt = currentCon.createStatement();
                stmt.executeUpdate(searchQuery);
                System.out.println(searchQuery);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
			public static Parent getParent(Parent bean)  {
			        	
						parentEmail = bean.getParentEmail();
			
			            String searchQuery = "select * from parents where email='" + parentEmail + "'";
			
			            try {
			                currentCon = ConnectionManager.getConnection();
			                stmt = currentCon.createStatement();
			                rs = stmt.executeQuery(searchQuery);
			                boolean more = rs.next();
			
			                // if user exists set the isValid variable to true
			                if (more) {
			                	String parentEmail = rs.getString("parentEmail");
			               
			                    bean.setParentEmail(parentEmail);
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
	    	 public static List<Parent> getAllParent() {
	   		  List<Parent> parents = new ArrayList<Parent>();
	   		  
	   		  try {
	   		  	currentCon = ConnectionManager.getConnection();
	   		  	stmt = currentCon.createStatement();
	   		  
	   		  	  String q = "select * from parents";
	   		      ResultSet rs = stmt.executeQuery(q);
	   		      
	   		      while (rs.next()) {
	   		    	Parent parent = new Parent();
	   		          	   		         
	   		    	parent.setParentEmail(rs.getString("parentEmail"));
	   		    	parent.setParentPassword(rs.getString("parentPassword"));
	   		    	parent.setParentName(rs.getString("parentName"));
	   		    	parent.setParentAddress(rs.getString("parentAddress"));
	   		    	parent.setParentPhone(rs.getString("parentPhone"));
	   		    		parents.add(parent);
	   		      }
	   		  } catch (SQLException e) {
	   		      e.printStackTrace();
	   		  }

	   		  return parents;
	   	}
			
			//update pass
    	    public static void updatePass(Parent bean) throws NoSuchAlgorithmException {

    	    	
    	    	parentEmail = bean.getParentEmail();
    	    	parentPassword = bean.getParentPassword();
    	    	
    	    	
    	    	//convert the password to MD5
    	        MessageDigest md = MessageDigest.getInstance("MD5");
    	        md.update(parentPassword.getBytes());
    	 
    	        byte byteData[] = md.digest();
    	 
    	        //convert the byte to hex format
    	        StringBuffer sb = new StringBuffer();
    	        for (int i = 0; i < byteData.length; i++) {
    	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    	        }
    	        
    	        String pass = sb.toString();
    	       
    	        String searchQuery = "UPDATE parents SET  password='" + parentPassword +"' WHERE email = '" + parentEmail + "'";
    	       
    	    	try {

    	            currentCon = ConnectionManager.getConnection();
    	            stmt = currentCon.createStatement();
    	            stmt.executeUpdate(searchQuery);
    	            
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }
}

package javaconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JavaConnection {

        static String CREATE_TABLE_SQL="CREATE TABLE employee(EMP_ID int(11) NOT NULL,NAME VARCHAR(45) NOT NULL,EMAIL VARCHAR(45) NOT NULL,PRIMARY KEY(EMP_ID))";
	public static void main(String[] args)throws Exception {
		//createTable();
                //insertRecord();
                //updateRecord();
                //deleteRecord();
                selectRecord();
     
	}
	public static void createTable() throws Exception {
		try{ 
                   Connection con=getConnection();
                    PreparedStatement create= con.prepareStatement(CREATE_TABLE_SQL);
                    System.out.println("Table created succesfully");
                    create.executeUpdate();
		}
		catch(Exception e) {
                    System.out.println(e);
		}
		
		
	}

    public static Connection getConnection() throws Exception{
	try
	{
		String url="jdbc:mysql://localhost:3306/test";
        String uname="root";
        String psswrd="";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,psswrd);
        System.out.println("Connected");
        return con;
	}
		catch(Exception e) 
	{System.out.println(e);
	}
	return null;
    }
    
    public static void insertRecord() throws Exception{
        
        Connection con=getConnection();
	PreparedStatement stmt=con.prepareStatement("insert into employee(emp_id,name,email) values (2,'Emily','emily@gmail.com')");
        System.out.println("Inserted record successfully");
        stmt.executeUpdate();
	con.close();
				
    }
    
     public static void updateRecord(){
        
        String updateSql = "update employee set email=? where emp_id=1";
        Connection con=null;
        try{
        con=getConnection();
	PreparedStatement stmt=con.prepareStatement(updateSql);
	stmt.setString(1, "joe@gmail.com");
        System.out.println("Record Updated successfully");
        stmt.executeUpdate();
	con.close();		
        }catch(Exception e){
            e.printStackTrace();
        }
       	
    }
     
     public static void deleteRecord() throws Exception{
        
        Connection con=getConnection();
	PreparedStatement stmt=con.prepareStatement("delete from employee where emp_id=1");
        System.out.println("Deleted record successfully");
        stmt.executeUpdate();
	con.close();
				
    }
     
     public static void selectRecord()throws Exception{
        
        String sql = "select * from employee where EMP_ID=?";
        Connection con=getConnection();
	PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setInt(1, 2);
         System.out.println("Inserted Values are");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
          System.out.println("EMP_ID=" + rs.getInt(1));
          System.out.println("NAME=" + rs.getString(2));
          System.out.println("EMAIL=" + rs.getString(3));
        }
	con.close();			
    }
}

 

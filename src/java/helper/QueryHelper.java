package helper;

import global.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import callback.QueryCallback;



public class QueryHelper {  
    
    public static void executeQuery(String sql,Object[] parameters,QueryCallback call){  
        Connection conn=null;  
        PreparedStatement pstmt=null;  
        ResultSet rs=null;  
        try{  
            conn=DataAccess.getConnection();  
            pstmt=conn.prepareStatement(sql); 
            if(parameters!=null)  
            {  
                int i=1;  
                for(Object param:parameters){  
                    pstmt.setObject(i, param);  
                    i++;  
                }  
            }  
               
              
            rs=pstmt.executeQuery();         
            call.call(rs);   
              
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }  
  
}  

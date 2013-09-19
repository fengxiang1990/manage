package helper;

import global.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;

import callback.ExecuteCallback;

public class ExecuteHelper {
	public static void executeUpdate(String sql,Object[] parameters,ExecuteCallback call){  
        Connection conn=null;  
        PreparedStatement pstmt=null;  
        int   update_num = 0;
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
               
              
            update_num=pstmt.executeUpdate();      
            call.execute(update_num);
              
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }  
}

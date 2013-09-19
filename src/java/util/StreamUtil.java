package util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StreamUtil {
	/** 
     * 将byte数组转换成InputStream 
     * @param in 
     * @return 
     * @throws Exception 
     */  
    public static InputStream byteTOInputStream(byte[] in) throws Exception{  
          
        ByteArrayInputStream is = new ByteArrayInputStream(in);  
        return is;  
    }  
}

package service;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;


import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import dao.ImageDao;
import entity.ImageReturn;
import entity.ImageReturnURL;
import entity.Response;
import global.Config;

public class ImageService  implements IImageSer {
	
   
   /* (non-Javadoc)
 * @see service.IImageSer#uploadFile(java.io.File, int, java.lang.String)
 */
@Override
public String uploadFile(File file,int type,String username){
	   Response res = new Response();
	   boolean flag  = new ImageDao().storeImage(file,type,username);
	   if(flag){
		   res.setResponse_code(0);
		   res.setResponse_msg("");
	   }
	return JSONObject.fromObject(res).toString();
   }
   /* (non-Javadoc)
 * @see service.IImageSer#uploadByByte(java.lang.String, int, java.lang.String)
 */
@Override
public String uploadByByte(String content,int type,String username){
	   try{
	 //  FileOutputStream fos = new FileOutputStream("E:\\upload\\"+"dark"+".jpg");
	   Response res = new Response();
	   byte[] by = new BASE64Decoder().decodeBuffer(content);
	  // fos.write(by);
	   System.out.println(content);
	   boolean flag  = new ImageDao().storeImage(by,type,username);
	   if(flag){
		   res.setResponse_code(0);
		   res.setResponse_msg("upload success");
	   }
	  //  fos.close();
		return JSONObject.fromObject(res).toString();
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	return null;
   }
   /* (non-Javadoc)
 * @see service.IImageSer#getImagePgNum(int, int)
 */
@Override
public byte[][] getImagePgNum(int pg,int size){	   
	   byte[][] byarray = null;
	   try{
	   ImageReturn ir  =  new ImageDao().getImageByPgNum(pg, size);
	   byarray =  new byte[ir.getCount()][Config.socket_buff];
	   InputStream[] inss = ir.getInss();
		 //  BufferedImage image=ImageIO.read(ins); 
	   for(int i=0;i<inss.length;i++){
		   InputStream ins =  inss[i];
		 ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        byte[] data = new byte[Config.socket_buff];  
	        int count = -1;  
	        while((count = ins.read(data,0,Config.socket_buff)) != -1)  
	            outStream.write(data, 0, count);  	          
	        data = null;  
	        byte[] bt = new byte[Config.socket_buff];
	        bt =  outStream.toByteArray();
	        byarray[i] = bt;
	   }
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	        return byarray;
   }
   /* (non-Javadoc)
 * @see service.IImageSer#getBy(int)
 */
@Override
public byte[] getBy(int id) throws IOException {
		InputStream ins = new ImageDao().getImageById(id);
		 //  BufferedImage image=ImageIO.read(ins); 
		 ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        byte[] data = new byte[Config.socket_buff];  
	        int count = -1;  
	        while((count = ins.read(data,0,Config.socket_buff)) != -1)  
	            outStream.write(data, 0, count);  	          
	        data = null;  
	        return outStream.toByteArray(); 
	}
    	
   /* (non-Javadoc)
 * @see service.IImageSer#getImagePg(int, int)
 */
@Override
public String[] getImagePg(int pg,int size){	   
	   String[] datas = null;
	   try{
	   ImageReturnURL ir  =  new ImageDao().getImageByPg(pg, size);
	   datas  = new String[ir.getUrls().size()];
	   for(int i = 0; i< ir.getUrls().size();i++){
		   datas[i]  = ir.getUrls().get(i);
	   }
	   return datas;
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	return null;
   }
}

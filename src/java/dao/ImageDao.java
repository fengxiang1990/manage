package dao;

import entity.ImageReturn;
import entity.ImageReturnURL;
import entity.Type;
import global.Config;
import global.DataAccess;
import global.DatabaseManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ImageUtil;
import util.UUIDTool;

public class ImageDao {
	 private DatabaseManager databaseManager=new DatabaseManager(); 
	private static final String dir_path =Config.resdir;
	
	  public static void main(String[] args){
		     ImageDao upload = new ImageDao();
		try{
		    for(String path:ImageUtil.readfile(dir_path)){
		    	File file = new File(path);
	            upload.storeImage(file);
		    }
	        }catch(Exception e){
	             e.printStackTrace();
	        }
	    }
	    protected Connection dbConnection=null;
	    protected String driverName = Config.dbdriver;
	    protected String dbURL = Config.dburl;
	    protected String userID = Config.dbuser;
	    protected String passwd = Config.dbpass;  
	    protected PreparedStatement pstm =null;
	    protected ResultSet rs  =null;
	    public List<String> getAllFileName(){
	    	List<String> list = new ArrayList<String>();
	    	String sql = "select * from picture where 1=?";
	    	try{
	    	dbConnection = DataAccess.getConnection();
	    	pstm = dbConnection.prepareStatement(sql);
	    	pstm.setInt(1,1);
	    	rs = pstm.executeQuery();
	    	while(rs.next()){
	    		list.add(rs.getString("pic_name"));
	    	}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return list;
	    }
	    public boolean storeImage(File file,int type,String username){
	        try{
	            FileInputStream fin = new FileInputStream(file);
	            ByteBuffer nbf = ByteBuffer.allocate((int)file.length());
	            byte[] array = new byte[1024];
	            int length=0;
	            while((length=fin.read(array))>0){
	                if(length!=1024)
	                     nbf.put(array,0,length);
	                else
	                     nbf.put(array);
	            }
	            fin.close();
	            byte[] content = nbf.array();
	            return setImage(content,type,username);
	            
	        }catch(FileNotFoundException e){
	             e.printStackTrace();
	            }catch (IOException e){
	                 e.printStackTrace();
	                }
	            return false;
	    
	    }
	    
	    public boolean storeImage(File file){
	        try{
	            FileInputStream fin = new FileInputStream(file);
	            ByteBuffer nbf = ByteBuffer.allocate((int)file.length());
	            byte[] array = new byte[1024];
	            int length=0;
	            while((length=fin.read(array))>0){
	                if(length!=1024)
	                     nbf.put(array,0,length);
	                else
	                     nbf.put(array);
	            }
	            fin.close();
	            byte[] content = nbf.array();
	            return setImage(content);
	            
	        }catch(FileNotFoundException e){
	             e.printStackTrace();
	            }catch (IOException e){
	                 e.printStackTrace();
	                }
	            return false;
	    
	    }
	    
	    public boolean storeImage(byte[] by,int type,String username){
	            byte[] content = by;
	            return setImage(content,type,username);
	    
	    }
	    
	    private boolean setImage(byte[] in,int type,String username){
	        boolean flag = false;
	        try{
	             Class.forName(driverName);
	             dbConnection = DriverManager.getConnection(dbURL,userID,passwd);
	             String name  = UUIDTool.generate();
	             String sql  = "insert into picture(pic_name,content,type,createTime,whose) values('"+name+"',?,?,?,?)";
	             pstm  = dbConnection.prepareStatement(sql);
	             pstm.setBytes(1,in);
	             pstm.setInt(2,type);
	             SimpleDateFormat f  = new SimpleDateFormat("yyyy/MM/dd");
	             Date d  = new Date();
	             pstm.setString(3,f.format(d));
	             pstm.setString(4,username);
	             int i  = pstm.executeUpdate();
	             if(i>0){
	            	 flag=true;
	             }
	            }catch(Exception e){
	            try {
					dbConnection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	             e.printStackTrace();
	        }finally{
	        	try {
					dbConnection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        return flag;
	        
	    }   
	    
	    private boolean setImage(byte[] in){
	        boolean flag = false;
	        try{
	             Class.forName(driverName);
	             dbConnection = DriverManager.getConnection(dbURL,userID,passwd);
	             String name  = UUIDTool.generate();
	             String sql  = "insert into picture(pic_name,content,type,createTime) values('"+name+"',?,?,?)";
	             pstm  = dbConnection.prepareStatement(sql);
	             pstm.setBytes(1,in);
	             pstm.setInt(2,Type.type_1);
	             SimpleDateFormat f  = new SimpleDateFormat("yyyy/MM/dd");
	             Date d  = new Date();
	             pstm.setString(3,f.format(d));
	             int i  = pstm.executeUpdate();
	             if(i>0){
	            	 flag=true;
	             }
	                     }catch(Exception e){
	             e.printStackTrace();
	        }
	        return flag;
	        
	    }    
	    public InputStream getImageById(int id){
	         InputStream is =null;
	    	 try{
	             Class.forName(driverName);
	             dbConnection = DriverManager.getConnection(dbURL,userID,passwd);
	             String sql  = "select * from  picture where id=?";
	             pstm  = dbConnection.prepareStatement(sql);
	             pstm.setInt(1,id);
	             rs  = pstm.executeQuery();
	             while(rs.next()){
	            	 is = rs.getBinaryStream("content");
	             }
	          }catch(Exception e){
	             e.printStackTrace();
	        }
			return is;
	    	
	    }
	    
	    public ImageReturn getImageByPgNum(int pgnum,int size){
	         InputStream[] iss =new InputStream[size];
	         ImageReturn ir  = null;
	    	 try{
	    		 ir  = new ImageReturn();
	             Class.forName(driverName);
	             dbConnection = DriverManager.getConnection(dbURL,userID,passwd);
	             StringBuilder sql  = new StringBuilder() ;
	             sql.append(" select pic.* from picture pic ");
	             sql.append(" where pic.createTime in ( ");
	             sql.append(" select * from ( ");
	             sql.append(" select p1.createTime    from  ");
	             sql.append("    (select createTime ct from picture p ");
	             sql.append("      order by createTime desc limit 1,1 ");
	             sql.append("    ) t1,picture p1 ");
	             sql.append("  where to_days(t1.ct) - to_days(p1.createtime) < 5 ");
	             sql.append("  ) t2) ");
	             sql.append(" order by date_format(pic.createTime,'%y/%m/%d') desc  limit ?,? ");
	             System.out.println(sql +" " +pgnum+" "+size);
	             pstm  = dbConnection.prepareStatement(sql.toString());
	             pstm.setInt(1,(pgnum-1)*size);
	             pstm.setInt(2,size);
	             rs  = pstm.executeQuery();
	             int i = 0;
	             while(rs.next()){
	            	 InputStream is = rs.getBinaryStream("content");
	            	 iss[i++] = is;
	             }
	             ir.setCount(i);
	             ir.setInss(iss);
	          }catch(Exception e){
	             e.printStackTrace();
	        }
			return ir;
	    	
	    }

	  
	    public ImageReturnURL getImageByPg(int pgnum,int size){
	    	ImageReturnURL ir  = null;
	    	List<String>  datas;
	    	 try{
	    		 ir  = new ImageReturnURL();
	    		 datas = new ArrayList<String>();
	             Class.forName(driverName);
	             dbConnection = DataAccess.getConnection();
	             StringBuilder sql  = new StringBuilder() ;
	            /* sql.append(" select pic.* from picture pic ");
	             sql.append(" where pic.createTime in ( ");
	             sql.append(" select * from ( ");
	             sql.append(" select p1.createTime    from  ");
	             sql.append("    (select createTime ct from picture p ");
	             sql.append("      order by createTime desc limit 1,1 ");
	             sql.append("    ) t1,picture p1 ");
	             sql.append("  where to_days(t1.ct) - to_days(p1.createtime) < 5 ");
	             sql.append("  ) t2) ");
	             sql.append(" order by date_format(pic.createTime,'%y/%m/%d') desc  limit ?,? ");
                     * */
                     //all pictures
                     sql.append(" select pic.* from picture pic ");
                     sql.append(" order by date_format(pic.createTime,'%y/%m/%d') desc  limit ?,? ");
	             System.out.println(sql +" " +pgnum+" "+size);
	             pstm  = dbConnection.prepareStatement(sql.toString());
	             pstm.setInt(1,(pgnum-1)*size);
	             pstm.setInt(2,size);
	             rs  = pstm.executeQuery();
	             int i = 0;
	             while(rs.next()){
	            	 datas.add(rs.getString("image_url"));	            	 
	             }
	             ir.setCount(i);
	             ir.setUrls(datas);
	          }catch(Exception e){
	             e.printStackTrace();
	        }
			return ir;
	    	
	    }
	    
	    public boolean addImageInfo(String name,int type,String username){
	        boolean flag = false;
	        try{
	             Class.forName(driverName);
	             dbConnection = DriverManager.getConnection(dbURL,userID,passwd);
	             String sql  = "insert into picture(pic_name,type,createTime,whose,image_url) values('"+name+"',?,?,?,?)";
	             pstm  = dbConnection.prepareStatement(sql);
	             pstm.setInt(1,type);
	             SimpleDateFormat f  = new SimpleDateFormat("yyyy/MM/dd");
	             Date d  = new Date();
	             pstm.setString(2,f.format(d));
	             pstm.setString(3,username);
	             pstm.setString(4,"http://imageservice-imgservice.stor.sinaapp.com/"+name);
	             int i  = pstm.executeUpdate();
	             if(i>0){
	            	 flag=true;
	             }
	            }catch(Exception e){
	            try {
					dbConnection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	             e.printStackTrace();
	        }finally{
	        	try {
					dbConnection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        return flag;
	        
	    }   
}

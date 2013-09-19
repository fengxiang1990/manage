package dao;

import entity.User;
import global.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mapper.ObjectMapper;
import mapper.RowMapper;

public class UserDao {
	 private int UPDATE_NUM=0;
	 private DatabaseManager databaseManager=new DatabaseManager();  
     
	  
	 //ʹ�ñ����˺ŵ�½
	    public List<User> findUserByNameAndPwd(String name,String pwd) {  
	      
	        List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer  selectSql=new StringBuffer();    
	        //1.��װsql���,����Ϊ��̬�󶨵�  
	        selectSql.append("select username,");
	        selectSql.append("       password,");
	        selectSql.append("       openid,");
	        selectSql.append("       accessToken ");
	        selectSql.append("from   user u ");
	        selectSql.append("where  u.username=? and u.password=?");
	        //2.����ѯ�����е�ֵ���뵽parameterList��  
	        parameterList.add(name);
	        parameterList.add(pwd);
	        //3.sql���Ҳ���ˣ���ѯʱ���������ֵҲ���ˣ����������Խ��в�ѯ�����������װ��  
	        Object[] parameters=parameterList.toArray();  
	        System.out.println(selectSql.toString()+" "+name+" "+pwd);
	        List<User> userList=new ArrayList<User>();   
	        databaseManager.executeQuery(new UserMapper(userList), selectSql.toString(), parameters);  	          
	        UserMapper us  = new UserMapper(userList);
	        return us.getUserList();  
	    }  
	  
	  //ʹ��openID ��½ ��QQ��½��������Openid��Ψһ�ģ�
	  //ʹ����Ѷ�˺ŵ�OPENID��½
	    public List<User> findUserByOpenId(String openid) {  
	      
	        List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer  selectSql=new StringBuffer();    
	        //1.��װsql���,����Ϊ��̬�󶨵�  
	        selectSql.append("select username,");
	        selectSql.append("       password,");
	        selectSql.append("       openid,");
	        selectSql.append("       accessToken ");
	        selectSql.append("from   user u ");
	        selectSql.append("where  u.openid=?");
	        //2.����ѯ�����е�ֵ���뵽parameterList��  
	        parameterList.add(openid);
	        //3.sql���Ҳ���ˣ���ѯʱ���������ֵҲ���ˣ����������Խ��в�ѯ�����������װ��  
	        Object[] parameters=parameterList.toArray();  
	        List<User> userList=new ArrayList<User>();   
	        databaseManager.executeQuery(new UserMapper(userList), selectSql.toString(), parameters);  	          
	        UserMapper us  = new UserMapper(userList);
	        return us.getUserList();  
	    } 	    
	    //ʹ����Ѷ�˺ŵ�½��ǿ��ע���˺� ������Ѷ�˺�
	    public boolean regist(String name,String password,String openid){
	    	List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer sql =new StringBuffer();    
	        //1.��װsql���,����Ϊ��̬�󶨵�  
	        sql.append("insert into user(username,password,openid) values(?,?,?)");
	        //2.����ѯ�����е�ֵ���뵽parameterList��  
	        parameterList.add(name);
	        parameterList.add(password);
	        parameterList.add(openid);
	        //3.sql���Ҳ���ˣ���ѯʱ���������ֵҲ���ˣ����������Խ��в�ѯ�����������װ��  
	        Object[] parameters=parameterList.toArray();  
	        int size = 0;
	        databaseManager.executeUpdate(new ObjectMap(size), sql.toString(), parameters);
	        ObjectMap om = new ObjectMap(size);
	        System.out.println("size:"+om.getSize());
	        if(om.getSize()>0){
	        	return true;
	        }
	        return false;
	    }
	    //ע�᱾���˺�
	    public boolean regist(String name,String password){
	    	List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer sql =new StringBuffer();    
	        //1.��װsql���,����Ϊ��̬�󶨵�  
	        sql.append("insert into user(username,password) values(?,?)");
	        //2.����ѯ�����е�ֵ���뵽parameterList��  
	        parameterList.add(name);
	        parameterList.add(password);
	        //3.sql���Ҳ���ˣ���ѯʱ���������ֵҲ���ˣ����������Խ��в�ѯ�����������װ��  
	        Object[] parameters=parameterList.toArray();  
	        databaseManager.executeUpdate(new ObjectMap(), sql.toString(), parameters);
	        if(UPDATE_NUM>0){
	        	return true;
	        }
	        return false;
	    }
	    
	    //�����˺�  ����Ѷ�˺�
	    public boolean bind(String name,String pwd,String openid){
	    	List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer sql =new StringBuffer();    
	        //1.��װsql���,����Ϊ��̬�󶨵�  
	        sql.append("update user set openid=? where username=? and password=?");
	        //2.����ѯ�����е�ֵ���뵽parameterList��  
	        parameterList.add(openid);
	        parameterList.add(name);
	        parameterList.add(pwd);	        
	        //3.sql���Ҳ���ˣ���ѯʱ���������ֵҲ���ˣ����������Խ��в�ѯ�����������װ��  
	        Object[] parameters=parameterList.toArray();  
	
	        databaseManager.executeUpdate(new ObjectMap(), sql.toString(), parameters);
	        //ObjectMap om = new ObjectMap();
	        System.out.println(sql.toString()+" "+name+" "+pwd+" "+openid);
	       // System.out.println("size:"+om.getSize());
	        if(UPDATE_NUM>0){
	        	return true;
	        }
	        return false;
	    }
	    
	    /*
	     * test the main
	     */
	    public static void main(String[] args){
	    	List<User> list  = new UserDao().findUserByNameAndPwd("admin","admin");
	    	//List<User> list  = new UserDao().findUserByOpenId("sdsadad");
	        for(User u:list){
	        	System.out.println(u.getUsername());
	        	System.out.println(u.getPwd());
	        	System.out.println(u.getOpenid());
	        	System.out.println(u.getAccessToken());
	        }
	    }
	    
	    class UserMapper implements RowMapper{  
		    private List<User> userList=new ArrayList<User>();  
		  
		    public UserMapper(List<User> userList) {  
		      
		        this.userList = userList;  
		    }  
		  
		    public List<User> getUserList() {  
		        return userList;  
		    }  
		  
		    public void setUserList(List<User> userList) {  
		        this.userList = userList;  
		    }  
		  
		   
			public void buildRow(ResultSet rs) throws SQLException {  
		        User user=new User();  
		          /*
		           * set values
		           */
		        user.setUsername(rs.getString("username"));
		        user.setPwd(rs.getString("password"));
		        user.setOpenid(rs.getString("openid"));
		        user.setAccessToken(rs.getString("accessToken"));
		        userList.add(user);  
		    }     
	}
	    
	    class ObjectMap implements ObjectMapper{
	    	private int size;
	    	public ObjectMap(){
	    	}
	    	public ObjectMap(int size){
	    		this.size = size;
	    		System.out.println("size:"+size);
	    	}
			
			public void buildObject(Object obj) throws SQLException {
				// TODO Auto-generated method stub
				this.size = (Integer)obj;
				UPDATE_NUM = this.size;
			}
			public int getSize() {
				return size;
			}
			public void setSize(int size) {
				this.size = size;
			}  

	    }
	}  
	 
	
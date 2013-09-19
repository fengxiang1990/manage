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
     
	  
	 //使用本地账号登陆
	    public List<User> findUserByNameAndPwd(String name,String pwd) {  
	      
	        List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer  selectSql=new StringBuffer();    
	        //1.组装sql语句,参数为动态绑定的  
	        selectSql.append("select username,");
	        selectSql.append("       password,");
	        selectSql.append("       openid,");
	        selectSql.append("       accessToken ");
	        selectSql.append("from   user u ");
	        selectSql.append("where  u.username=? and u.password=?");
	        //2.将查询条件中的值加入到parameterList中  
	        parameterList.add(name);
	        parameterList.add(pwd);
	        //3.sql语句也有了，查询时所需参数的值也有了，接下来可以进行查询，并将结果组装了  
	        Object[] parameters=parameterList.toArray();  
	        System.out.println(selectSql.toString()+" "+name+" "+pwd);
	        List<User> userList=new ArrayList<User>();   
	        databaseManager.executeQuery(new UserMapper(userList), selectSql.toString(), parameters);  	          
	        UserMapper us  = new UserMapper(userList);
	        return us.getUserList();  
	    }  
	  
	  //使用openID 登陆 （QQ登陆所产生的Openid是唯一的）
	  //使用腾讯账号的OPENID登陆
	    public List<User> findUserByOpenId(String openid) {  
	      
	        List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer  selectSql=new StringBuffer();    
	        //1.组装sql语句,参数为动态绑定的  
	        selectSql.append("select username,");
	        selectSql.append("       password,");
	        selectSql.append("       openid,");
	        selectSql.append("       accessToken ");
	        selectSql.append("from   user u ");
	        selectSql.append("where  u.openid=?");
	        //2.将查询条件中的值加入到parameterList中  
	        parameterList.add(openid);
	        //3.sql语句也有了，查询时所需参数的值也有了，接下来可以进行查询，并将结果组装了  
	        Object[] parameters=parameterList.toArray();  
	        List<User> userList=new ArrayList<User>();   
	        databaseManager.executeQuery(new UserMapper(userList), selectSql.toString(), parameters);  	          
	        UserMapper us  = new UserMapper(userList);
	        return us.getUserList();  
	    } 	    
	    //使用腾讯账号登陆后强制注册账号 并绑定腾讯账号
	    public boolean regist(String name,String password,String openid){
	    	List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer sql =new StringBuffer();    
	        //1.组装sql语句,参数为动态绑定的  
	        sql.append("insert into user(username,password,openid) values(?,?,?)");
	        //2.将查询条件中的值加入到parameterList中  
	        parameterList.add(name);
	        parameterList.add(password);
	        parameterList.add(openid);
	        //3.sql语句也有了，查询时所需参数的值也有了，接下来可以进行查询，并将结果组装了  
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
	    //注册本地账号
	    public boolean regist(String name,String password){
	    	List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer sql =new StringBuffer();    
	        //1.组装sql语句,参数为动态绑定的  
	        sql.append("insert into user(username,password) values(?,?)");
	        //2.将查询条件中的值加入到parameterList中  
	        parameterList.add(name);
	        parameterList.add(password);
	        //3.sql语句也有了，查询时所需参数的值也有了，接下来可以进行查询，并将结果组装了  
	        Object[] parameters=parameterList.toArray();  
	        databaseManager.executeUpdate(new ObjectMap(), sql.toString(), parameters);
	        if(UPDATE_NUM>0){
	        	return true;
	        }
	        return false;
	    }
	    
	    //已有账号  绑定腾讯账号
	    public boolean bind(String name,String pwd,String openid){
	    	List<Object> parameterList=new ArrayList<Object>();  	          
	        StringBuffer sql =new StringBuffer();    
	        //1.组装sql语句,参数为动态绑定的  
	        sql.append("update user set openid=? where username=? and password=?");
	        //2.将查询条件中的值加入到parameterList中  
	        parameterList.add(openid);
	        parameterList.add(name);
	        parameterList.add(pwd);	        
	        //3.sql语句也有了，查询时所需参数的值也有了，接下来可以进行查询，并将结果组装了  
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
	 
	
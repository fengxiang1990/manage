/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Role;
import entity.Smenu;
import global.DataAccess;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author admin
 */
@Repository("menuDao")
public class MenuDao extends BaseDao{
   public void test() throws SQLException{
       this.dbConnection = DataAccess.getConnection();
       System.out.println(dbConnection.isClosed());
   }
   
   public List<Smenu> findSmenu(String...args){
       List<Smenu> data = new ArrayList<Smenu>();
       try{
       dbConnection = DataAccess.getConnection();
       pstm  = dbConnection.prepareStatement(args[0]);
       pstm.setString(1,args[1]);
       rs = pstm.executeQuery();
       while(rs.next()){
           System.out.println("rs:"+rs.getInt("id"));
           Smenu m = new Smenu();
           m.setId(rs.getInt("id"));
           m.setName(rs.getString("name"));
           m.setPid(rs.getInt("pid"));
           m.setLevel(rs.getInt("level"));
           m.setCreateDate(rs.getString("create_date"));
           m.setCreatePerson(rs.getString("create_person"));
           m.setSortorder(rs.getInt("sortorder"));
           m.setAction(rs.getString("action"));
           data.add(m);
       }
       return data;
       }catch(Exception e){
           try {
               this.dbConnection.close();
           } catch (SQLException ex) {
               Logger.getLogger(MenuDao.class.getName()).log(Level.SEVERE, null, ex);
           }
           e.printStackTrace();
           return null;
       }       
   }

    public boolean hasChildren(String sql, int id) {
       try{
           this.dbConnection = DataAccess.getConnection();
       this.pstm  = dbConnection.prepareStatement(sql);
       this.pstm.setInt(1,id);
       this.rs = this.pstm.executeQuery();
       int i= 0;
       while(this.rs.next()){
           i++;
       }
       if(i>1){
           return true;
       }else{
           return false;
       }
       }catch(Exception e){
           e.printStackTrace();
       }
       return false;
    }
    
    public List<Smenu> getChildren(String sql, int id){
        List<Smenu> data = new ArrayList<Smenu>();
       try{
           this.dbConnection = DataAccess.getConnection();
       this.pstm  = dbConnection.prepareStatement(sql);
       this.pstm.setInt(1,id);
       this.rs = this.pstm.executeQuery();
       while(this.rs.next()){
           Smenu m = new Smenu();
           m.setId(rs.getInt("id"));
           m.setName(rs.getString("name"));
           m.setPid(rs.getInt("pid"));
           m.setLevel(rs.getInt("level"));
           m.setCreateDate(rs.getString("create_date"));
           m.setCreatePerson(rs.getString("create_person"));
           m.setSortorder(rs.getInt("sortorder"));
            m.setAction(rs.getString("action"));
           data.add(m);
       }
       return data;
       }catch(Exception e){
           e.printStackTrace();
       }
       return null;
    }

    public List<Smenu> findSmenuByLv(String toString, int i) {
         List<Smenu> data = new ArrayList<Smenu>();
       try{
       this.dbConnection = DataAccess.getConnection();
       this.pstm  = dbConnection.prepareStatement(toString);
       this.pstm.setInt(1,i);
       this.rs = this.pstm.executeQuery();
       while(rs.next()){
           Smenu m = new Smenu();
           m.setId(rs.getInt("id"));
           m.setName(rs.getString("name"));
           m.setPid(rs.getInt("pid"));
           m.setLevel(rs.getInt("level"));
           m.setCreateDate(rs.getString("create_date"));
           m.setCreatePerson(rs.getString("create_person"));
           m.setSortorder(rs.getInt("sortorder"));
            m.setAction(rs.getString("action"));
           data.add(m);
       }
       return data;
       }catch(Exception e){
            try {
               this.dbConnection.close();
           } catch (SQLException ex) {
               Logger.getLogger(MenuDao.class.getName()).log(Level.SEVERE, null, ex);
           }
           e.printStackTrace();
           return null;
       }       
    }

    public List<Role> findAllRoles(String sql) {
        List<Role> data = new ArrayList<Role>();
       try{
       this.dbConnection = DataAccess.getConnection();
       this.pstm  = dbConnection.prepareStatement(sql);
       this.rs = this.pstm.executeQuery();
       while(rs.next()){
           Role m = new Role();
           m.setId(rs.getInt("id"));
           m.setName(rs.getString("name"));
           data.add(m);
       }
       return data;
       }catch(Exception e){
            try {
               this.dbConnection.close();
           } catch (SQLException ex) {
               Logger.getLogger(MenuDao.class.getName()).log(Level.SEVERE, null, ex);
           }
           e.printStackTrace();
           return null;
       }       
    }

    public String getAction(String sql, int id) {
         List<Smenu> data = new ArrayList<Smenu>();
       try{
       this.dbConnection = DataAccess.getConnection();
       this.pstm  = dbConnection.prepareStatement(sql);
       this.pstm.setInt(1,id);
       this.rs = this.pstm.executeQuery();
       rs.last();
       return rs.getString("action");
       }catch(Exception e){
            try {
               this.dbConnection.close();
           } catch (SQLException ex) {
               Logger.getLogger(MenuDao.class.getName()).log(Level.SEVERE, null, ex);
           }
           e.printStackTrace();
           return null;
       }       
    }

    public boolean addRole(String sql, String name) {
          try{
       this.dbConnection = DataAccess.getConnection();
       this.pstm  = dbConnection.prepareStatement(sql);
       this.pstm.setString(1,name);
       int i= this.pstm.executeUpdate();
       if(i>0){
           return true;
       }else{
           return false;
       }
       }catch(Exception e){
            try {
               this.dbConnection.close();
           } catch (SQLException ex) {
               Logger.getLogger(MenuDao.class.getName()).log(Level.SEVERE, null, ex);
           }
           return false;
       }       
    }

    public List<String> getSids(String sql, String rid) {
        List<String> data = null;
       try{
        data = new ArrayList<String>();
       this.dbConnection = DataAccess.getConnection();
       this.pstm  = dbConnection.prepareStatement(sql);
       this.pstm.setInt(1,Integer.parseInt(rid));
       this.rs =  this.pstm.executeQuery();
       while(rs.next()){
           String sid = rs.getString("sid");
           data.add(sid);
       }
       }catch(Exception e){
            try {
               this.dbConnection.close();
           } catch (SQLException ex) {
               Logger.getLogger(MenuDao.class.getName()).log(Level.SEVERE, null, ex);
           }
       }       
       return data;
    }
}

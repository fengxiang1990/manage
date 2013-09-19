/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MenuDao;
import entity.Role;
import entity.Smenu;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service("menuService")
public class MenuService {
    @Autowired
    private MenuDao menuDao;

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    public  void test() throws SQLException{
        menuDao.test();
    }
    
    public List<Smenu> findSmenuByUserName(String username){
        StringBuilder sb = new StringBuilder();
        sb.append(" select sm.* ");
        sb.append(" from   suser s, ");
        sb.append(" rs    rs, ");
        sb.append("  role  r, ");
        sb.append("  smenu sm ");
        sb.append(" where  s.rid = r.id ");
        sb.append(" and    r.id =  rs.rid ");
        sb.append(" and    rs.sid = sm.id ");
        sb.append(" and    s.username=? ");
        return menuDao.findSmenu(sb.toString(),username);
    }
    
    public boolean hasChildren(int id){
        String sql  ="select sm1.* from smenu sm,smenu sm1 where sm.id=? and sm.id= sm1.pid;";
        return menuDao.hasChildren(sql,id);
    }
    
     public List<Smenu> getChildren(int id){
        String sql  ="select sm1.* from smenu sm,smenu sm1 where sm.id=? and sm.id= sm1.pid;";
        return menuDao.getChildren(sql,id);
    }

    public List<Smenu> findSmenuByLv(int i) {
         StringBuilder sb = new StringBuilder();
        sb.append(" select sm.* ");
        sb.append(" from   suser s, ");
        sb.append(" rs    rs, ");
        sb.append("  role  r, ");
        sb.append("  smenu sm ");
        sb.append(" where  s.rid = r.id ");
        sb.append(" and    r.id =  rs.rid ");
        sb.append(" and    rs.sid = sm.id ");
        sb.append(" and    sm.level=? ");
        return menuDao.findSmenuByLv(sb.toString(),i);
    }

    public List<Role> findAllRoles() {
        String sql = "select * from role";
        return menuDao.findAllRoles(sql);
    }

    public String getActionById(int id) {
        String sql="select action from smenu where id=?";
        return menuDao.getAction(sql,id);
    }

    public boolean addRole(String name) {
       String sql  = "insert into role(name) values(?)";
       return this.menuDao.addRole(sql,name);
    }
}

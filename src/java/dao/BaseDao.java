/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import global.DataAccess;
import global.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class BaseDao {
    private DatabaseManager databaseManager=new DatabaseManager();
    protected Connection dbConnection=null;
    protected PreparedStatement pstm =null;
    protected ResultSet rs  =null;
}

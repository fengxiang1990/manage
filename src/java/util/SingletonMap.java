/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;

/**
 *
 * @author admin
 */
public class SingletonMap extends HashMap{
    private SingletonMap(){}
    private  static final SingletonMap instance = new SingletonMap();
    public static synchronized SingletonMap getInstance(){
        return instance;
    }
}

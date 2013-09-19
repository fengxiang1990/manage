package service;

import java.util.List;

import net.sf.json.JSONObject;
import dao.UserDao;
import entity.Response;
import entity.User;
import global.Config;

public class UserService  implements IUserService{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserDao dao = new UserDao();
	String json;
	public static void main(String[] args){
		new UserService().login("admin","admin");
	}
	
	public String login(String account, String pwd) {
		// TODO Auto-generated method stub
		Response response = new Response();
		try{
		List<User> list = dao.findUserByNameAndPwd(account, pwd);
		User u = list.get(0);
		response.setResponse_code(Config.res_suc);
		JSONObject  ja = JSONObject.fromObject(u);
		json  = ja.toString();
		System.out.println(json);
		response.setResponse_msg(json);
		return JSONObject.fromObject(response).toString();
		}catch(Exception e){
			response.setResponse_code(Config.res_fal);
			response.setResponse_msg("");
			return JSONObject.fromObject(response).toString();
		}
	}

	
	public String regist(String account, String pwd) {
		// TODO Auto-generated method stub
		Response response = new Response();
		boolean flag  = dao.regist(account, pwd);
		if(flag){			
		response.setResponse_code(Config.res_suc);
		response.setResponse_msg("");
		return JSONObject.fromObject(response).toString();
		}else{
			response.setResponse_code(Config.res_fal);
			response.setResponse_msg("");
			return JSONObject.fromObject(response).toString();
		}
	}
	
	public String loginByOpenId(String openid) {
		// TODO Auto-generated method stub
		Response response = new Response();
		try{
		List<User> list = dao.findUserByOpenId(openid);
		User u = list.get(0);
		response.setResponse_code(Config.res_suc);
		JSONObject  ja = JSONObject.fromObject(u);
		json  = ja.toString();
		response.setResponse_msg(json);
		return JSONObject.fromObject(response).toString();
		}catch(Exception e){
			response.setResponse_code(Config.res_fal);
			response.setResponse_msg("");
			return JSONObject.fromObject(response).toString();
		}
	}
	
	public String bindOpenIdToUser(String account, String pwd, String openid) {
		// TODO Auto-generated method stub		
		Response response = new Response();
		try{
		boolean flag  = dao.bind(account,pwd,openid);
		if(flag){
		response.setResponse_code(Config.res_suc);
		}else{
			response.setResponse_code(Config.res_fal);
		}
		JSONObject  ja = JSONObject.fromObject(flag);
		json  = ja.toString();
		System.out.println(json);
		response.setResponse_msg("bind success");
		return JSONObject.fromObject(response).toString();
		}catch(Exception e){
			response.setResponse_code(Config.res_fal);
			response.setResponse_msg("");
			return JSONObject.fromObject(response).toString();
		}
	}
    //判断openid 是否已经绑定过账号 
	
	public String isOpenidBind(String openid) {
		// TODO Auto-generated method stub
		Response response = new Response();
		try{
		List<User> list = dao.findUserByOpenId(openid);
		User u = list.get(0);
		response.setResponse_code(Config.res_suc);
		JSONObject  ja = JSONObject.fromObject(u);
		json  = ja.toString();
		response.setResponse_msg("is bind");
		return JSONObject.fromObject(response).toString();
		}catch(Exception e){
			response.setResponse_code(Config.res_fal);
			response.setResponse_msg("");
			return JSONObject.fromObject(response).toString();
		}
	}
	
}

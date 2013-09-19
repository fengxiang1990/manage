package service;


public interface IUserService {
    public String login(String account,String pwd);
    public String regist(String account,String pwd);
    public String loginByOpenId(String openid);
    public String bindOpenIdToUser(String account,String pwd,String openid);
    public String isOpenidBind(String openid);
}

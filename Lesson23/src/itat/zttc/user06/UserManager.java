package itat.zttc.user06;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserManager implements Serializable{
	private static final long serialVersionUID = -5444712120516445386L;
	private List<String> forbitUsers = Arrays.asList("曾轶可","周杰伦","李刚","李刚儿子");
	//Key存储username,value存储这个username的用户对象
	Map<String,User> users;
	
	public UserManager() {
		users = new HashMap<String,User>();
	}
	
	
	public void add(User user) {
		if(user.getPassword().length()<=6) {
			throw new UserException("密码必须大于6位");
		}
		if(forbitUsers.contains(user.getNickname())) {
			throw new UserException(user.getNickname()+"不能注册");
		}
		if(users.containsKey(user.getUsername())) {
			throw new UserException("用户"+user.getUsername()+"已经存在！不能添加");
		}
		users.put(user.getUsername(), user);
		System.out.println(user.getNickname()+"成功添加!");
	}
	
	public void delete(String username) {
		users.remove(username);
	}
	
	public void update(User user) {
		if(!users.containsKey(user.getUsername())) {
			throw new UserException("更新用户不存在");
		}
		users.put(user.getUsername(), user);
	}
	
	public User load(String username) {
		return users.get(username);
	}
	
	public List<User> list() {
		List<User> lu = new ArrayList<User>();
		Set<String> keys = users.keySet();
		for(String username:keys) {
			lu.add(users.get(username));
		}
		return lu;
	}
	
	public User login(String username,String password) {
		User u = this.load(username);
		if(u==null) {
			throw new UserException("用户名不正确!");
		}
		if(!password.equals(u.getPassword())) {
			throw new UserException("用户密码不正确");
		}
		return u;
	}
}

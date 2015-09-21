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
	private List<String> forbitUsers = Arrays.asList("�����","�ܽ���","���","��ն���");
	//Key�洢username,value�洢���username���û�����
	Map<String,User> users;
	
	public UserManager() {
		users = new HashMap<String,User>();
	}
	
	
	public void add(User user) {
		if(user.getPassword().length()<=6) {
			throw new UserException("����������6λ");
		}
		if(forbitUsers.contains(user.getNickname())) {
			throw new UserException(user.getNickname()+"����ע��");
		}
		if(users.containsKey(user.getUsername())) {
			throw new UserException("�û�"+user.getUsername()+"�Ѿ����ڣ��������");
		}
		users.put(user.getUsername(), user);
		System.out.println(user.getNickname()+"�ɹ����!");
	}
	
	public void delete(String username) {
		users.remove(username);
	}
	
	public void update(User user) {
		if(!users.containsKey(user.getUsername())) {
			throw new UserException("�����û�������");
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
			throw new UserException("�û�������ȷ!");
		}
		if(!password.equals(u.getPassword())) {
			throw new UserException("�û����벻��ȷ");
		}
		return u;
	}
}

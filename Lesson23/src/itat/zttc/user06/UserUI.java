package itat.zttc.user06;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class UserUI {
	private UserManager um;
	private final static String SAVE_PATH = "e:/kh/user.save";
	public UserUI() {
		//如果要考虑存储的问题，可以把UserManager存储
		//所以在启动的时候不应该new而应该从硬盘上面读取存储的数据
		//读取数据时可能发生如下问题:
		//1、如果是第一次打开该系统，存储文件不存在，此时该new一个新的UserManager并且创建相应的存储文件
		try {
			File f = new File(SAVE_PATH);
			if(!f.exists()) {
				f.createNewFile();
				um = new UserManager();
				System.out.println("你是第一次使用我们的系统，初始化完成");
			} else {
				//如果文件存在表示该对象已经存在，可以直接加载
				um = load();
			}
			//2、如果不是第一次打开系统，就直接读取相应的UserManager
		} catch (IOException e) {
			System.out.println("发生系统错误:"+e.getMessage());
			System.exit(-1);
		}
	}
	private UserManager load() {
		//通过对象流加载对象
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(SAVE_PATH));
			UserManager um = (UserManager)ois.readObject();
			return um;
		} catch(EOFException e){
			//如果文件存在，但是没有加载到对象，直接返回一个新对象
			System.out.println("你是第一次使用我们的系统，初始化完成");
			return new UserManager();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ois!=null) ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	private void save() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH));
			oos.writeObject(um);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos!=null) oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void start() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String str = null;
			showInfo();
			while((str=br.readLine())!=null) {
				if(str.equalsIgnoreCase("a")||str.equalsIgnoreCase("1")) {
					add(br);
				} else if(str.equalsIgnoreCase("d")||str.equalsIgnoreCase("2")) {
					delete(br);
				} else if(str.equalsIgnoreCase("U")||str.equalsIgnoreCase("3")) {
					update(br);
				} else if(str.equalsIgnoreCase("R")||str.equalsIgnoreCase("4")) {
					load(br);
				} else if(str.equalsIgnoreCase("L")||str.equalsIgnoreCase("5")) {
					list();
				} else if(str.equalsIgnoreCase("login")||str.equalsIgnoreCase("6")) {
					login(br);
				} else if(str.equalsIgnoreCase("e")||str.equalsIgnoreCase("7")) {
					System.out.println("希望再次使用我们的系统!");
					break;
				} else {
					System.err.println("请输入正确的操作");
				}
				showInfo();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void login(BufferedReader br) throws IOException {
		try {
			System.out.println("请输入用户名");
			String username = br.readLine();
			System.out.println("请输入用户密码");
			String password = br.readLine();
			User u = um.login(username, password);
			System.out.println("热烈欢迎:"+u.getNickname()+"同志使用我们的系统！");
		} catch (UserException e) {
			System.err.println(e.getMessage());
		}
	}
	private void update(BufferedReader br) throws IOException {
		try {
			System.out.println("请输入要修改的用户名");
			String username = br.readLine();
			System.out.println("请输入要修改的密码");
			String password = br.readLine();
			System.out.println("请输入要修改的昵称");
			String nickname = br.readLine();
			System.out.println("请输入要修改的年龄");
			int age = Integer.parseInt(br.readLine());
			User u = new User(username, password, nickname, age);
			um.update(u);
			save();
		} catch (NumberFormatException e) {
			System.err.println("叮咚！你输入的年龄格式不正确!");
		} catch(UserException e) {
			System.err.println(e.getMessage());
		}
	}
	private void delete(BufferedReader br) throws IOException {
		System.out.println("请输入要删除的用户");
		String username = br.readLine();
		um.delete(username);
		save();
	}
	private void load(BufferedReader br) throws IOException {
		System.out.println("请输入要查询用户名");
		String username = br.readLine();
		User u = um.load(username);
		if(u==null) {
			System.err.println("你要查找的用户不存在！");
		} else{
			System.out.println(u);
		}
	}
	private void list() {
		List<User> list = um.list();
		for(User u:list) {
			System.out.println(u);
		}
	}
	private void add(BufferedReader br) throws IOException {
		try {
			System.out.println("请输入用户名:");
			String username = br.readLine();
			System.out.println("请输入密码：");
			String pwd = br.readLine();
			System.out.println("请输入用户昵称");
			String nickname = br.readLine();
			System.out.println("请输入年龄");
			int age = Integer.parseInt(br.readLine());
			User user = new User(username,pwd,nickname,age);
			um.add(user);
			System.out.println(user.getNickname()+"成功添加!!");
			save();
		} catch (NumberFormatException e) {
			System.err.println("叮咚！！请输入正确的年龄！");
		} catch(UserException e) {
			System.err.println(e);
		}
	}
	
	public void showInfo() {
		System.out.println("请选择相应的操作:");
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("添加[A或1]---删除[D或2]---修改[U或3]--查询[R或4]--列表[L或5]--登录[login或者6]--退出系统[E或7]");
		System.out.println("--------------------------------------------------------------------------------------------");
	}
}

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
		//���Ҫ���Ǵ洢�����⣬���԰�UserManager�洢
		//������������ʱ��Ӧ��new��Ӧ�ô�Ӳ�������ȡ�洢������
		//��ȡ����ʱ���ܷ�����������:
		//1������ǵ�һ�δ򿪸�ϵͳ���洢�ļ������ڣ���ʱ��newһ���µ�UserManager���Ҵ�����Ӧ�Ĵ洢�ļ�
		try {
			File f = new File(SAVE_PATH);
			if(!f.exists()) {
				f.createNewFile();
				um = new UserManager();
				System.out.println("���ǵ�һ��ʹ�����ǵ�ϵͳ����ʼ�����");
			} else {
				//����ļ����ڱ�ʾ�ö����Ѿ����ڣ�����ֱ�Ӽ���
				um = load();
			}
			//2��������ǵ�һ�δ�ϵͳ����ֱ�Ӷ�ȡ��Ӧ��UserManager
		} catch (IOException e) {
			System.out.println("����ϵͳ����:"+e.getMessage());
			System.exit(-1);
		}
	}
	private UserManager load() {
		//ͨ�����������ض���
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(SAVE_PATH));
			UserManager um = (UserManager)ois.readObject();
			return um;
		} catch(EOFException e){
			//����ļ����ڣ�����û�м��ص�����ֱ�ӷ���һ���¶���
			System.out.println("���ǵ�һ��ʹ�����ǵ�ϵͳ����ʼ�����");
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
					System.out.println("ϣ���ٴ�ʹ�����ǵ�ϵͳ!");
					break;
				} else {
					System.err.println("��������ȷ�Ĳ���");
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
			System.out.println("�������û���");
			String username = br.readLine();
			System.out.println("�������û�����");
			String password = br.readLine();
			User u = um.login(username, password);
			System.out.println("���һ�ӭ:"+u.getNickname()+"ͬ־ʹ�����ǵ�ϵͳ��");
		} catch (UserException e) {
			System.err.println(e.getMessage());
		}
	}
	private void update(BufferedReader br) throws IOException {
		try {
			System.out.println("������Ҫ�޸ĵ��û���");
			String username = br.readLine();
			System.out.println("������Ҫ�޸ĵ�����");
			String password = br.readLine();
			System.out.println("������Ҫ�޸ĵ��ǳ�");
			String nickname = br.readLine();
			System.out.println("������Ҫ�޸ĵ�����");
			int age = Integer.parseInt(br.readLine());
			User u = new User(username, password, nickname, age);
			um.update(u);
			save();
		} catch (NumberFormatException e) {
			System.err.println("���ˣ�������������ʽ����ȷ!");
		} catch(UserException e) {
			System.err.println(e.getMessage());
		}
	}
	private void delete(BufferedReader br) throws IOException {
		System.out.println("������Ҫɾ�����û�");
		String username = br.readLine();
		um.delete(username);
		save();
	}
	private void load(BufferedReader br) throws IOException {
		System.out.println("������Ҫ��ѯ�û���");
		String username = br.readLine();
		User u = um.load(username);
		if(u==null) {
			System.err.println("��Ҫ���ҵ��û������ڣ�");
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
			System.out.println("�������û���:");
			String username = br.readLine();
			System.out.println("���������룺");
			String pwd = br.readLine();
			System.out.println("�������û��ǳ�");
			String nickname = br.readLine();
			System.out.println("����������");
			int age = Integer.parseInt(br.readLine());
			User user = new User(username,pwd,nickname,age);
			um.add(user);
			System.out.println(user.getNickname()+"�ɹ����!!");
			save();
		} catch (NumberFormatException e) {
			System.err.println("���ˣ�����������ȷ�����䣡");
		} catch(UserException e) {
			System.err.println(e);
		}
	}
	
	public void showInfo() {
		System.out.println("��ѡ����Ӧ�Ĳ���:");
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("���[A��1]---ɾ��[D��2]---�޸�[U��3]--��ѯ[R��4]--�б�[L��5]--��¼[login����6]--�˳�ϵͳ[E��7]");
		System.out.println("--------------------------------------------------------------------------------------------");
	}
}

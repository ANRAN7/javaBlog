package itat.zttc.talk;

public class Answer implements Runnable{
	private String name;
	private Asker asker;
	private String[] answers;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Asker getAsker() {
		return asker;
	}

	public void setAsker(Asker asker) {
		this.asker = asker;
	}
	
	public Answer(String name, Asker asker) {
		super();
		this.name = name;
		this.asker = asker;
		answers = new String[]{"����˯��","���ڿ��ʼ�","�й���������",
				"�ϵ�Ц��","�����ϲ���","�����ˣ�������","�����·���","��������","��������",
				"����","Ӯ��","out��","��ȥ�����","��ȥ����Ӱ","��ȥ�����","��������̹��",
				"��ȥ������"};
	}

	@Override
	public void run() {
		while(true) {
			answer();
		}
	}
	
	public void answer() {
		synchronized (asker) {
			try {
				if(asker.isAsk()) {
					String q = asker.getQuestion();
					if(q.indexOf("���˵�")>0) {
						System.out.println("��Ѿ�������˵���");
					} else {
						int index = Asker.ran.nextInt(answers.length);
						String an = answers[index];
						System.out.println(name+":`"+an+"`");
					}
					asker.setAsk(false);
					Thread.sleep(2000);
					asker.notify();
					asker.wait();
				} else {
					asker.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

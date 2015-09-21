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
		answers = new String[]{"我在睡觉","我在看笔记","中国队总是输",
				"上帝笑了","我在上厕所","打雷了！下雨啦","我收衣服啦","恨死你了","爱死你了",
				"输了","赢了","out了","我去买面包","我去看电影","我去买军火","我买了辆坦克",
				"我去找拉登"};
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
					if(q.indexOf("王八蛋")>0) {
						System.out.println("你丫才是王八蛋！");
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

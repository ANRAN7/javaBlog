package itat.zttc.talk;

import java.util.Random;

public class Asker implements Runnable{
	private String name;
	private String[] questions;
	private String question;
	private boolean isAsk;
	public static Random ran = new Random();
	
	public Asker(String name) {
		this.name = name;
		questions = new String[]{"��������","��ѧϰ����","��out����","������?","�������˵���?",
				"���ں�˭˵��?","���ں����˵�˵����","�������","�㰮������!","��޲����ң�",
				"���쿼����","�й�������","�����������Ӯ����","�й������Ӯ����"};
		isAsk = false;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getQuestions() {
		return questions;
	}

	public void setQuestions(String[] questions) {
		this.questions = questions;
	}


	public boolean isAsk() {
		return isAsk;
	}


	public void setAsk(boolean isAsk) {
		this.isAsk = isAsk;
	}


	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			ask();
		}
	}
	
	public void ask() {
		synchronized (this) {
			try {
				if(!isAsk) {
					int index = ran.nextInt(questions.length);
					question = questions[index];
					System.out.println(name+":'"+question+"'");
					Thread.sleep(2000);
					isAsk = true;
					this.notify();
					this.wait();
				} else {
					this.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

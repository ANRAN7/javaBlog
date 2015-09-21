package itat.zttc.talk;

public class Test {
	public static void main(String[] args) {
		Asker asker = new Asker("ÀîÓî´º");
		Answer ans = new Answer("Íôº­", asker);
		Thread ast = new Thread(asker);
		Thread ant = new Thread(ans);
		ant.setDaemon(true);
		ast.start();
		ant.start();
	}
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class TestMoveBall02 extends JFrame{
	public static void main(String[] args) {
		new TestMoveBall02();
	}
	
	private MyPanel mp;
	private JButton jb1,jb2;
	private Thread mt;
	public TestMoveBall02() {
		this.setSize(600, 500);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jb1 = new JButton("ÒÆ¶¯Ð¡Çò");
		jb1.addActionListener(new BtnClickEvent());
		this.add(jb1,BorderLayout.NORTH);
		
		jb2 = new JButton("Í£Ö¹ÒÆ¶¯");
		jb2.addActionListener(new BtnClickEvent());
		this.add(jb2,BorderLayout.SOUTH);
		
		mp = new MyPanel(40, 40);
		this.add(mp);
		mt = new Thread(mp);
		
		this.setVisible(true);
	}
	
	class BtnClickEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jb1) {
				mt.start();
			} else if(e.getSource()==jb2) {
				mp.stop();
			}
		}
	}
	
	
	class MyPanel extends JPanel implements Runnable {
		int x,y;
		private boolean flag = true;
		
		@Override
		public void run() {
			try {
				for(int i=0;i<200;i++) {
					if(!flag) break;
					mp.x+=3;
					mp.repaint();
					Thread.sleep(50);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		public MyPanel(int x,int y) {
			this.x = x;
			this.y = y;
		}
		
		public void stop() {
			flag = false;
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.RED);
			g.fillOval(x, y, 50, 50);
		}
	}
}

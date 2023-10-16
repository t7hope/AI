package controller;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class App extends JPanel implements Runnable, KeyListener {
	private static final int WIDTH = 500;
	private static final int HEIGHT = 700;

	private static final int WARRIOR_WIDTH = 30;
	private static final int WARRIOR_HEIGHT = 70;
	private static final int WARRIOR_SPEED = 7;

	private static final double UPS = 60.0D;
	private static final double FPS = 120.0D;

	private static final boolean[] KEYS = new boolean[9999];

	private final Rectangle northJet;
	private final Rectangle southJet;
	private List<Bullet> bulletNorth = new ArrayList<>();
	private List<Bullet> bulletSouth = new ArrayList<>();
//	private Bullet bulletNorth;
//	private Bullet bulletSouth;

	private boolean start1, start2;

	public App() {
		super(true);
		this.setPreferredSize(new Dimension(App.WIDTH, App.HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
		setVisible(true);
		this.northJet = new Rectangle(App.WIDTH / 2 - App.WARRIOR_WIDTH / 2, 0, App.WARRIOR_WIDTH, App.WARRIOR_HEIGHT);
		this.southJet = new Rectangle(App.WIDTH / 2 - App.WARRIOR_WIDTH / 2, App.HEIGHT - App.WARRIOR_HEIGHT,
				App.WARRIOR_WIDTH, App.WARRIOR_HEIGHT);

		this.start1 = false;
		this.bulletNorth = new ArrayList<>();

		this.start2 = false;
		this.bulletSouth = new ArrayList<>();

	}

	private void addMouseListener(App app) {
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getButton() == MouseEvent.BUTTON1) {

						if (e.getButton() == MouseEvent.BUTTON1) {
							if (start1) {
								start1 = false;
							} else {
								start1 = true;

								bulletNorth.add(new Bullet(
										new Point((int) northJet.getX() + 10, (int) northJet.getY() + WARRIOR_HEIGHT),
										1));
								Timer timer = new Timer(1, new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										for (int i = 0; i < bulletNorth.size(); i++) {
											bulletNorth.get(i).setPointY();
										}
										; // Di chuyển viên đạn
										repaint(); // Vẽ lại giao diện
									}
								});
								timer.start();

							}
						}

					}

				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (e.getButton() == MouseEvent.BUTTON3) {

						if (e.getButton() == MouseEvent.BUTTON3) {
							if (start2) {
								start2 = false;
							} else {
								start2 = true;

								bulletSouth.add(new Bullet(
										new Point((int) southJet.getX() + 10, (int) southJet.getY() + WARRIOR_HEIGHT),
										2));
								Timer timer = new Timer(1, new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										for (int i = 0; i < bulletSouth.size(); i++) {
											bulletSouth.get(i).setPointY();
										}
										; // Di chuyển viên đạn
										repaint(); // Vẽ lại giao diện
									}
								});
								timer.start();

							}
						}

					}

				}

			}

		});

	}

	@Override
	public void run() {
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / App.UPS;
		final double timeF = 1000000000 / App.FPS;
		double deltaU = 0, deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				this.update();
				deltaU--;
			}

			if (deltaF >= 1) {
				this.repaint(0, 0, App.WIDTH, App.HEIGHT);
				deltaF--;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		App.KEYS[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (App.KEYS[KeyEvent.VK_Q]) {

		} else {
			App.KEYS[e.getKeyCode()] = false;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, App.WIDTH, App.HEIGHT);
		g2.setColor(Color.CYAN);
		g2.fillRect(this.northJet.x, this.northJet.y, App.WARRIOR_WIDTH, App.WARRIOR_HEIGHT);
		g2.fillRect(this.southJet.x, this.southJet.y, App.WARRIOR_WIDTH, App.WARRIOR_HEIGHT);
		g2.setColor(Color.CYAN);
		g2.setColor(Color.red);
		for (Bullet bullet : bulletNorth) {
			bullet.draw(g2);
		}
		
		for (Bullet bullet : bulletSouth) {
			bullet.draw(g2);
		}
		g2.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);

	}

	public void jump() {
		while (this.northJet.y > App.HEIGHT / 2) {
			this.northJet.y--;
		}
	}

	private void update() {

		if (App.KEYS[KeyEvent.VK_Q]) {
			jump();
			App.KEYS[KeyEvent.VK_Q] = false;
		}
		
		// north jet
		if (App.KEYS[KeyEvent.VK_A]) {
			if (this.northJet.x > 0) {
				this.northJet.x -= App.WARRIOR_SPEED;
			}
		}

		if (App.KEYS[KeyEvent.VK_D]) {
			if (this.northJet.x + App.WARRIOR_WIDTH < App.WIDTH) {
				this.northJet.x += App.WARRIOR_SPEED;
			}

		}
		if (App.KEYS[KeyEvent.VK_W]) {
			if (this.northJet.y == 0) {
				this.northJet.y += App.WARRIOR_SPEED;
			} else if (this.northJet.y > 0 && this.northJet.y < (350 - App.WARRIOR_HEIGHT)) {
				this.northJet.y += App.WARRIOR_SPEED;
			}
		}
		if (App.KEYS[KeyEvent.VK_S]) {
			if (this.northJet.y > 0) {
				this.northJet.y -= App.WARRIOR_SPEED;
			} else if (this.northJet.y == 0) {
				this.northJet.y -= 0;
			}

		}

		// south jet
		if (App.KEYS[KeyEvent.VK_LEFT]) {
			if (this.southJet.x > 0) {
				this.southJet.x -= App.WARRIOR_SPEED;
			}
		}

		if (App.KEYS[KeyEvent.VK_RIGHT]) {
			if (this.southJet.x + App.WARRIOR_WIDTH < App.WIDTH) {
				this.southJet.x += App.WARRIOR_SPEED;
			}
		}
		if (App.KEYS[KeyEvent.VK_UP]) {
			if (this.southJet.y > (350 - App.WARRIOR_HEIGHT / 100)) {
				this.southJet.y -= App.WARRIOR_SPEED;
			}
		}
		if (App.KEYS[KeyEvent.VK_DOWN]) {
			if (this.southJet.y < (700 - App.WARRIOR_HEIGHT)) {
				this.southJet.y += App.WARRIOR_SPEED;
			}
		}
	}

	private void start() {
		new Thread(this).start();
	}

	public static void main(String[] args) {
		App jet = new App();

		JFrame frame = new JFrame("Warrior Fight");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(jet);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		jet.start();
	}
}

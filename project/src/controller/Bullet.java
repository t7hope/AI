package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class Bullet {
	private Point point;
	private int num; // Tọa độ x, y của viên đạn

	public Bullet(Point point, int num) {
		super();
		this.point = point;
		this.num = num;
	}

	public Bullet() {
	}

	public void move(int num) {
		if (num == 1) {
			point.y += 1;
		} else
			point.y -= 1;
	}

   

	public void draw(Graphics g2) {
		g2.setColor(Color.RED);
		g2.fillOval((int) point.getX(), (int) point.getY(), 10, 10);

	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setPointY() {
		if (this.num == 1) {
			this.point.y += num;
		}else this.point.y -= num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
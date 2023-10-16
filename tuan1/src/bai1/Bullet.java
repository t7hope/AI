package bai1;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Bullet extends JPanel {
    private int x, y; // Tọa độ x, y của viên đạn

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        // Cập nhật tọa độ x, y để di chuyển viên đạn
        y += 1; // Ví dụ: di chuyển sang phải với tốc độ 1 pixel/frame
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10); // Vẽ viên đạn dưới dạng hình tròn
    }
}
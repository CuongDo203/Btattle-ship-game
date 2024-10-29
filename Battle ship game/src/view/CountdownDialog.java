package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class CountdownDialog extends JDialog{
    private static int countdown = 10; // Thời gian đếm ngược ban đầu (5 giây)
    private JLabel messageLabel;
    private JLabel countdownLabel;
    JButton okButton;

    public CountdownDialog(Frame owner, String title, String content) {
        super(owner, title);
        setModal(true);
        messageLabel = new JLabel(content, SwingConstants.CENTER);
        countdownLabel = new JLabel("Trở về sau: " + countdown + " giây", SwingConstants.CENTER);
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng cửa sổ khi nhấn OK
            }
        });

        // Sắp xếp các thành phần trong dialog
        setLayout(new BorderLayout());
        add(messageLabel, BorderLayout.NORTH);
        add(countdownLabel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);

        setSize(400, 150); // Kích thước của cửa sổ
        setLocationRelativeTo(owner); // Hiển thị ở giữa màn hình
//        Point centerPoint = new Point(owner.getX() + owner.getWidth()/2 - this.getWidth()/2,owner.getY() + owner.getHeight()/2-this.getHeight()/2);
//        setLocation(centerPoint);
        // Tạo một Timer để đếm ngược mỗi giây
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown--; // Giảm thời gian đếm ngược
                countdownLabel.setText("Đóng sau: " + countdown + " giây");

                // Nếu hết thời gian (countdown == 0), đóng cửa sổ
                if (countdown == 0) {
                    dispose();
                    ((Timer) e.getSource()).stop(); // Dừng Timer
                }
            }
        });
        
        // Bắt đầu đếm ngược
        timer.start();
    }
    
    public void showWindow() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}

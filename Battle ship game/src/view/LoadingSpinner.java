package view;

import controller.ClientControl;
import dto.ClientMessage;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingSpinner extends JDialog {

    private ClientControl clientCtr;
    
    public LoadingSpinner(Window parent, ClientControl clientCtr, String content) {
        super(parent, "Loading!", ModalityType.APPLICATION_MODAL);
        this.clientCtr = clientCtr;
        // Tạo header label
        JLabel header = new JLabel(content);
        header.setFont(new Font("Serif", Font.BOLD, 30));
        header.setAlignmentX(CENTER_ALIGNMENT); // Đặt căn giữa theo trục X
        
        // Tạo ImageIcon và resize ảnh
        ImageIcon loadingIcon = new ImageIcon(getClass().getResource("/assets/icons/loading.gif"));
        Image scaledImage = loadingIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setAlignmentX(CENTER_ALIGNMENT); // Đặt căn giữa theo trục X
        
        // Tạo nút hủy
        JButton cancelButton = new JButton("Hủy");
        cancelButton.setAlignmentX(CENTER_ALIGNMENT); // Căn giữa nút
        cancelButton.addActionListener(e -> {
            System.out.println("Bạn đã hủy.");
            ClientMessage clientMess = new ClientMessage();
            clientMess.setCommand(ClientMessage.CANCEL_FIND_RANDOM_PLAYER);
            clientCtr.sendMessage(clientMess);
            dispose(); // Đóng cửa sổ khi nhấn nút hủy
        });
        
        // Tạo JPanel và sử dụng BoxLayout để sắp xếp các thành phần theo chiều dọc
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Sắp xếp theo chiều dọc
        panel.add(header);
        panel.add(label);
        panel.add(cancelButton); // Thêm nút hủy vào dưới cùng
        
        // Thêm panel vào JDialog
        getContentPane().add(panel);

        // Thiết lập kích thước
        

        // Đặt JDialog ở giữa cửa sổ cha
        setLocationRelativeTo(parent); 
    }

    public void showWindow() {
        setSize(400, 300); // Điều chỉnh kích thước cho phù hợp
        setResizable(false);
        setVisible(true); // Hiển thị dialog và làm cho nó modal
    }
}

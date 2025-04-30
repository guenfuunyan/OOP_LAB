package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingAccumulator extends JFrame {
    private JButton[] btnNumbers = new JButton[10];
    private JButton btnDelete, btnReset;
    private JTextField tfDisplay;

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            String text = tfDisplay.getText();

            switch (cmd) {
                case "DEL":
                    if (!text.isEmpty()) {
                        tfDisplay.setText(text.substring(0, text.length() - 1));
                    }
                    break;
                case "C":
                    tfDisplay.setText("");
                    break;
                default: // các nút số
                    tfDisplay.setText(text + cmd);
                    break;
            }
        }
    }

    public SwingAccumulator() {
        // Khởi tạo JTextField hiển thị
        tfDisplay = new JTextField();
        tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tfDisplay.setEditable(false);

        // Panel chứa các nút số và chức năng
        JPanel panelButtons = new JPanel(new GridLayout(4, 3));

        // Thêm nút 1–9
        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton(String.valueOf(i));
            panelButtons.add(btnNumbers[i]);
            btnNumbers[i].addActionListener(e -> {
                String cmd = e.getActionCommand();
                tfDisplay.setText(tfDisplay.getText() + cmd);
            });
        }

        // Nút DEL
        btnDelete = new JButton("DEL");
        panelButtons.add(btnDelete);
        btnDelete.addActionListener(e -> {
            String text = tfDisplay.getText();
            if (!text.isEmpty()) {
                tfDisplay.setText(text.substring(0, text.length() - 1));
            }
        });

        // Nút 0
        btnNumbers[0] = new JButton("0");
        panelButtons.add(btnNumbers[0]);
        btnNumbers[0].addActionListener(e -> {
            tfDisplay.setText(tfDisplay.getText() + "0");
        });

        // Nút C (reset)
        btnReset = new JButton("C");
        panelButtons.add(btnReset);
        btnReset.addActionListener(e -> tfDisplay.setText(""));

        // Đưa mọi thứ vào JFrame
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(tfDisplay, BorderLayout.NORTH);
        cp.add(panelButtons, BorderLayout.CENTER);

        // Cài đặt chung cho cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Swing Accumulator");
        setSize(250, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Chạy giao diện trên Event Dispatch Thread
        SwingUtilities.invokeLater(SwingAccumulator::new);
    }
    private void addButtons(JPanel panelButtons) {
        ActionListener btnListener = new ButtonListener();
        
        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton(String.valueOf(i));
            panelButtons.add(btnNumbers[i]);
            btnNumbers[i].addActionListener(btnListener);
        }

        btnDelete = new JButton("DEL");
        panelButtons.add(btnDelete);
        btnDelete.addActionListener(btnListener);

        btnNumbers[0] = new JButton("0");
        panelButtons.add(btnNumbers[0]);
        btnNumbers[0].addActionListener(btnListener);

        btnReset = new JButton("C");
        panelButtons.add(btnReset);
        btnReset.addActionListener(btnListener);
    }

}

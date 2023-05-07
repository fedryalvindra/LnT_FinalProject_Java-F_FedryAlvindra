package data.project;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.config.Connectiondb;
import data.utility.Pudding;

public class Menu extends JFrame implements ActionListener {

    private Vector<Pudding> puddsData;
    private Connectiondb connectionDb;

    private JPanel topPanel, botPanel;
    private JLabel welcomeLbl;
    private JButton enterBtn;

    void menuFrame() {
        setTitle("PT Pudding");
        setVisible(true);
        setSize(400, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void showMenu() {

        topPanel = new JPanel();
        welcomeLbl = new JLabel("Welcome to PT Pudding");

        welcomeLbl.setFont(new Font("", Font.BOLD, 20));
        topPanel.add(welcomeLbl);

        botPanel = new JPanel();
        enterBtn = new JButton("Enter");
        enterBtn.addActionListener(this);
        botPanel.add(enterBtn);

        add(topPanel, BorderLayout.NORTH);
        add(botPanel, BorderLayout.CENTER);
    }

    public Menu(Vector<Pudding> puddsData, Connectiondb connectionDb) {
        this.puddsData = puddsData;
        this.connectionDb = connectionDb;
        fillDataVector();

        showMenu();
        menuFrame();
    }

    private void fillDataVector() {
        try {
            connectionDb.rs = connectionDb.getPudsData();
            while (connectionDb.rs.next()) {
                String id = String.valueOf(String.valueOf(connectionDb.rs.getObject(1)));
                String puddName = String.valueOf(connectionDb.rs.getObject(2));
                int price = Integer.valueOf(String.valueOf(connectionDb.rs.getObject(3)));
                int stock = Integer.valueOf(String.valueOf(connectionDb.rs.getObject(4)));

                puddsData.add(new Pudding(id, puddName, price, stock));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterBtn) {
            setVisible(false);
            new ViewPudding(puddsData, connectionDb);
        }
    }
}

package data.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import data.config.Connectiondb;
import data.utility.Pudding;

public class ViewPudding extends JFrame implements ActionListener, MouseInputListener {

    private Vector<Pudding> puddsData;
    private Connectiondb connectionDb;

    private JPanel topPanel, midPanel, contentPanel, formPanel, tablePanel, botPanel, botTPanel, botBPanel,
            idLblPanel, nameLblPanel, priceLblPanel, stockLblPanel,
            idTextFieldPanel, nameTextFieldPanel, priceTextFieldPanel, stockTextFieldPanel;
    private JLabel titleLbl, idLbl, nameLbl, priceLbl, stockLbl;
    private JTextField idTextField, nameTextField, priceTextField, stockTextField;
    private JTable puddingTable;
    private JScrollPane scrollPane;
    private JButton addBtn, updateBtn, deleteBtn, clearBtn, backBtn;

    private Vector<Object> column, row;
    private Vector<Vector<Object>> data;

    private int price, stock;
    private String id, puddName, priceStr, stockStr;

    void viewFrame() {
        setTitle("PT Pudding");
        setVisible(true);
        setSize(1000, 670);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void showView() {

        // Top Panel
        topPanel = new JPanel();
        titleLbl = new JLabel("CRUD Pudding");
        titleLbl.setFont(new Font("", Font.BOLD, 20));
        topPanel.add(titleLbl);

        // Mid Panel
        midPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(1, 2));

        // Left Panel
        formPanel = new JPanel(new GridLayout(5, 2));

        // Pudding ID
        idLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        idTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        idLbl = new JLabel("Pudding ID");
        idLblPanel.add(idLbl);
        idTextField = new JTextField();
        idTextField.setEditable(false);
        idTextField.setPreferredSize(new Dimension(150, 25));
        idTextFieldPanel.add(idTextField);

        // Pudding Name
        nameLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        nameTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        nameLbl = new JLabel("Pudding Name");
        nameLblPanel.add(nameLbl);
        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(150, 25));
        nameTextFieldPanel.add(nameTextField);

        // Pudding Price
        priceLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        priceTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        priceLbl = new JLabel("Pudding Price");
        priceLblPanel.add(priceLbl);
        priceTextField = new JTextField();
        priceTextField.setPreferredSize(new Dimension(150, 25));
        priceTextFieldPanel.add(priceTextField);

        // Pudding Stock
        stockLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        stockTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        stockLbl = new JLabel("Pudding Stock");
        stockLblPanel.add(stockLbl);
        stockTextField = new JTextField();
        stockTextField.setPreferredSize(new Dimension(150, 25));
        stockTextFieldPanel.add(stockTextField);

        formPanel.add(idLblPanel);
        formPanel.add(idTextFieldPanel);
        formPanel.add(nameLblPanel);
        formPanel.add(nameTextFieldPanel);
        formPanel.add(priceLblPanel);
        formPanel.add(priceTextFieldPanel);
        formPanel.add(stockLblPanel);
        formPanel.add(stockTextFieldPanel);

        // Right Panel
        column = new Vector<Object>();
        column.add("ID");
        column.add("PuddingName");
        column.add("PuddingPrice");
        column.add("PuddingStock");

        data = new Vector<Vector<Object>>();
        for (Pudding admin : puddsData) {
            row = new Vector<Object>();
            row.add(admin.getId());
            row.add(admin.getPuddName());
            row.add(admin.getPrice());
            row.add(admin.getStock());
            data.add(row);
        }

        puddingTable = new JTable(data, column);
        puddingTable.addMouseListener(this);
        scrollPane = new JScrollPane(puddingTable);
        tablePanel = new JPanel();
        tablePanel.add(scrollPane);

        contentPanel.add(formPanel);
        contentPanel.add(tablePanel);

        midPanel.add(contentPanel);

        // Bot Panel
        botPanel = new JPanel(new GridLayout(2, 1));

        // Bottom Top
        botTPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        updateBtn = new JButton("Update");
        updateBtn.addActionListener(this);
        deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(this);
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(this);

        botTPanel.add(addBtn);
        botTPanel.add(updateBtn);
        botTPanel.add(deleteBtn);
        botTPanel.add(clearBtn);

        // Bottom Bottom
        botBPanel = new JPanel();

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        botBPanel.add(backBtn);

        botPanel.add(botTPanel);
        botPanel.add(botBPanel);


        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(botPanel, BorderLayout.SOUTH);
    }

    public ViewPudding(Vector<Pudding> puddsData, Connectiondb connectionDb) {
        this.puddsData = puddsData;
        this.connectionDb = connectionDb;

        showView();
        viewFrame();
    }

    private void clearTextField() {
        idTextField.setText("");
        nameTextField.setText("");
        priceTextField.setText("");
        stockTextField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            price = Integer.valueOf(priceStr);
            stock = Integer.valueOf(stockStr);
            // Add Data to Vector
            Pudding addNewPudding = new Pudding(id, puddName, price, stock);
            puddsData.add(addNewPudding);

            // Add Data to Database
            connectionDb.insertPudsData(id, puddName, price, stock);

            puddsData.clear();
            setVisible(false);
            new Menu(puddsData, connectionDb);

            clearTextField();
        } else if (e.getSource() == updateBtn) {

            id = idTextField.getText();
            puddName = nameTextField.getText();
            priceStr = priceTextField.getText();
            stockStr = stockTextField.getText();

            if (id.isEmpty() || puddName.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
                return;
            }

            price = Integer.valueOf(priceStr);
            stock = Integer.valueOf(stockStr);

            int selectedIndex = puddingTable.getSelectedRow();
            if (selectedIndex >= 0) {
                Pudding selectedPudding = puddsData.get(selectedIndex);
                selectedPudding.setId(id);
                selectedPudding.setPuddName(puddName);
                selectedPudding.setPrice(price);
                selectedPudding.setStock(stock);
            } else {

            }

            connectionDb.updateData(id, puddName, price, stock);

            puddingTable.repaint();

            clearTextField();
            puddingTable.clearSelection();

            } else if (e.getSource() == deleteBtn) {

            String deleteId = idTextField.getText();
            if (deleteId.isEmpty()) {
                return;
            }

            int selectedIndex = puddingTable.getSelectedRow();

            if (selectedIndex >= 0) {
                puddsData.remove(selectedIndex);
            } else {

            }

            connectionDb.deleteData(deleteId);
            puddingTable.repaint();
            clearTextField();
            puddingTable.clearSelection();

            } else if (e.getSource() == clearBtn) {
                clearTextField();
            } else if (e.getSource() == backBtn) {
                puddsData.clear();
                setVisible(false);
                new Menu(puddsData, connectionDb);
            }
        }

        @Override
        public void mouseClicked (MouseEvent e){
            if (e.getSource() == puddingTable) {
                int row = puddingTable.getSelectedRow();
                idTextField.setText(puddingTable.getValueAt(row, 0).toString());
                nameTextField.setText(puddingTable.getValueAt(row, 1).toString());
                priceTextField.setText(puddingTable.getValueAt(row, 2).toString());
                stockTextField.setText(puddingTable.getValueAt(row, 3).toString());
            }
        }

        @Override
        public void mousePressed (MouseEvent e){
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased (MouseEvent e){
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered (MouseEvent e){
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited (MouseEvent e){
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseDragged (MouseEvent e){
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseMoved (MouseEvent e){
            // TODO Auto-generated method stub

        }


    }




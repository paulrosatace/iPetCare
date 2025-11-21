/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import frames.ComboBoxMultiSelection;
import java.awt.Dimension;
import javax.swing.JComboBox;
import frames.ComboBoxMultiSelection;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.CardLayout;
import java.util.List;
import java.sql.DriverManager;
import JDBC.jdbcConnection;
import java.sql.ResultSetMetaData;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector; 
import javax.swing.table.DefaultTableModel; 
import javax.swing.event.ListSelectionEvent; 
import javax.swing.event.ListSelectionListener; 
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List; 
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ADMIN
 */
public class homeFrame extends javax.swing.JFrame {

    private String currentUserRole;
    private ComboBoxMultiSelection<String> servicesComboBox;
    private int selectedAppointmentId = -1; 

    Color defaultcolor = new Color(0, 0, 0);
    Color clickedcolor = new Color(102, 102, 102);
    Color white = new Color(255, 255, 255);

    DefaultTableModel table = new DefaultTableModel(new Object[]{"Client Name", "Address", "Email", "Contact", "Pet Name", "Species", "Breed", "Service/s", "Schedule", "Assistant", "Total Bill"}, 0);
    int editingRow = -1;

    public homeFrame() {


        initComponents();
        this.currentUserRole = "Guest";
        System.out.println("Home frame initialized without a specific role. Defaulting to: " + this.currentUserRole);
        customizeUIBasedOnRole();
        fetchAppointments(null);
        
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if (!e.getValueIsAdjusting()) {
                    jTable1MouseClicked();
                }
            }
        });

        jTable1.setModel(table);
        recordsTable.setModel(table);
    }

    public homeFrame(String userRole) {
        initComponents();
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                
                fetchAppointments(jTextField1.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               
                fetchAppointments(jTextField1.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
                fetchAppointments(jTextField1.getText().trim());
            }
        });

        this.currentUserRole = userRole;
        System.out.println("User logged in with role: " + this.currentUserRole);
        customizeUIBasedOnRole();
        fetchAppointments(null); 
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    jTable1MouseClicked();
                }
            }
        });
    }

    public void addSelectedServices(String services) {
        
        jTextArea1.setText(services);
        
        JOptionPane.showMessageDialog(this, "Selected Services: " + services, "Services Added", JOptionPane.INFORMATION_MESSAGE);
    }

    private void customizeUIBasedOnRole() {
        if (this.currentUserRole != null) {
            if ("SuperAdmin".equals(this.currentUserRole) || "Admin".equals(this.currentUserRole)) {
                System.out.println("Enabling Admin/SuperAdmin features.");
            } else if ("Staff".equals(this.currentUserRole)) {
                System.out.println("Enabling Staff features.");
            } else {
                System.out.println("Unknown role: " + this.currentUserRole);
            }
        } else {
            System.out.println("No role set for UI customization.");
        }
    }


    @SuppressWarnings("unchecked")

   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        appointmentsButton = new javax.swing.JButton();
        recordsButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        appointmentsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        petNameTextfield = new javax.swing.JTextField();
        speciesTextField = new javax.swing.JTextField();
        breedTextField = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        assistantTextfield = new javax.swing.JTextField();
        servicebutton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        totalBillTextfield = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        addAppointmentButton = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        classNameTextfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        addressTextfield = new javax.swing.JTextField();
        emailTextfield = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        contactTextfield = new javax.swing.JTextField();
        servicesPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        groomingButton = new javax.swing.JButton();
        boardingButton = new javax.swing.JButton();
        petwalkingButton = new javax.swing.JButton();
        daycareButton = new javax.swing.JButton();
        miscellaneousButton = new javax.swing.JButton();
        trainingButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        petWalkingServicesPanel = new javax.swing.JPanel();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        dayCareServicesPanel = new javax.swing.JPanel();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        trainingServicesPanel = new javax.swing.JPanel();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        miscellaneousServicesPanel = new javax.swing.JPanel();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        boardingServicesPanel = new javax.swing.JPanel();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        groomingServicesPanel = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        recordsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        recordsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        settingsPanel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        aboutPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        aboutUsPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("jMenu1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(2000, 1000));

        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 700));
        jPanel1.setPreferredSize(new java.awt.Dimension(2000, 1000));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Green Modern Veterinary Clinic Logo.png"))); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(260, 53));
        jPanel2.add(jLabel11);

        appointmentsButton.setBackground(new java.awt.Color(0, 0, 0));
        appointmentsButton.setForeground(new java.awt.Color(255, 255, 255));
        appointmentsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/appointments.png"))); // NOI18N
        appointmentsButton.setText("Appointments");
        appointmentsButton.setBorder(null);
        appointmentsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                appointmentsButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                appointmentsButtonMouseReleased(evt);
            }
        });
        appointmentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentsButtonActionPerformed(evt);
            }
        });
        jPanel2.add(appointmentsButton);

        recordsButton.setBackground(new java.awt.Color(0, 0, 0));
        recordsButton.setForeground(new java.awt.Color(255, 255, 255));
        recordsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/records.png"))); // NOI18N
        recordsButton.setText("Records");
        recordsButton.setBorder(null);
        recordsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                recordsButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                recordsButtonMouseReleased(evt);
            }
        });
        recordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordsButtonActionPerformed(evt);
            }
        });
        jPanel2.add(recordsButton);

        settingsButton.setBackground(new java.awt.Color(0, 0, 0));
        settingsButton.setForeground(new java.awt.Color(255, 255, 255));
        settingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N
        settingsButton.setText("Settings");
        settingsButton.setBorder(null);
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        jPanel2.add(settingsButton);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel10.setPreferredSize(new java.awt.Dimension(2000, 1000));
        jPanel10.setLayout(new java.awt.CardLayout());

        appointmentsPanel.setBackground(new java.awt.Color(51, 51, 51));
        appointmentsPanel.setPreferredSize(new java.awt.Dimension(2000, 615));
        appointmentsPanel.setRequestFocusEnabled(false);
        appointmentsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Client Name", "Address", "Email", "Contact", "Pet Name", "Species", "Breed", "Service/s", "Schedule", "Assistant", "Total Bill", "ID" // Added Address and ID
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        // Hide the ID column from view (optional, but good for internal IDs)
        jTable1.getColumnModel().getColumn(11).setMinWidth(0);
        jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(11).setWidth(0);
        jTable1.setOpaque(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        appointmentsPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 1490, 280));

        jButton3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        appointmentsPanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 690, 111, -1));

        jButton4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        appointmentsPanel.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 690, 110, -1));

        jButton5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton5.setText("Edit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        appointmentsPanel.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 690, 110, -1));

        jButton6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton6.setText("Print Receipt");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        appointmentsPanel.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 690, -1, -1));

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(230, 230, 230));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PET INFORMATION");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 250, 70));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pet Name:");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, 20));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Species:");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 70, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Breed:");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 60, -1));

        petNameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petNameTextfieldActionPerformed(evt);
            }
        });
        jPanel7.add(petNameTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 140, 30));

        speciesTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speciesTextFieldActionPerformed(evt);
            }
        });
        jPanel7.add(speciesTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 140, 30));
        jPanel7.add(breedTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 149, 140, 30));
        jPanel7.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 149, 140, 30));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Schedule:");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, 20));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Assign Assistant:");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        assistantTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assistantTextfieldActionPerformed(evt);
            }
        });
        jPanel7.add(assistantTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 140, 30));

        servicebutton.setText("Select Service");
        servicebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicebuttonActionPerformed(evt);
            }
        });
        jPanel7.add(servicebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 140, 30));

        jPanel13.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 660, 350));

        jScrollPane3.setViewportView(jPanel13);

        appointmentsPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 680, 370));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("TOTAL BILL:");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 110, 30));

        totalBillTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalBillTextfieldActionPerformed(evt);
            }
        });
        jPanel6.add(totalBillTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 140, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("₱");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 30, 30));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 450, 240));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("SELECTED SERVICES");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 240, -1));

        addAppointmentButton.setBackground(new java.awt.Color(47, 110, 138));
        addAppointmentButton.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        addAppointmentButton.setText("Add Appointment");
        addAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAppointmentButtonActionPerformed(evt);
            }
        });
        jPanel6.add(addAppointmentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 140, 30));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 350));

        appointmentsPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 490, 370));

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(230, 230, 230));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CLIENT INFORMATION");
        jPanel16.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Client Name:");
        jPanel16.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 30));

        classNameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classNameTextfieldActionPerformed(evt);
            }
        });
        jPanel16.add(classNameTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 140, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Address:");
        jPanel16.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 50, 30));
        jPanel16.add(addressTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 140, 30));

        emailTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextfieldActionPerformed(evt);
            }
        });
        jPanel16.add(emailTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 140, 30));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Email:");
        jPanel16.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 40, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Contact:");
        jPanel16.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 50, -1));
        jPanel16.add(contactTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 140, 30));

        jPanel9.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 350));

        appointmentsPanel.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 300, 370));

        jPanel10.add(appointmentsPanel, "card3");

        servicesPanel.setBackground(new java.awt.Color(255, 255, 204));
        servicesPanel.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel8.setEnabled(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(700, 450));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(102, 102, 102));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Select Service Types");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1526, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel14.setLayout(new java.awt.GridLayout(2, 0, 5, 0));

        jPanel21.setBackground(new java.awt.Color(102, 102, 102));
        jPanel21.setPreferredSize(new java.awt.Dimension(672, 75));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        groomingButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        groomingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grooming.png"))); // NOI18N
        groomingButton.setText("Grooming Services");
        groomingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        groomingButton.setPreferredSize(new java.awt.Dimension(180, 75));
        groomingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groomingButtonActionPerformed(evt);
            }
        });
        jPanel21.add(groomingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

        boardingButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        boardingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boarding.png"))); // NOI18N
        boardingButton.setText("Boarding Services");
        boardingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        boardingButton.setPreferredSize(new java.awt.Dimension(180, 75));
        boardingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boardingButtonActionPerformed(evt);
            }
        });
        jPanel21.add(boardingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        petwalkingButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        petwalkingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/petWalking.png"))); // NOI18N
        petwalkingButton.setText(" Pet Walking Services");
        petwalkingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        petwalkingButton.setPreferredSize(new java.awt.Dimension(180, 75));
        petwalkingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petwalkingButtonActionPerformed(evt);
            }
        });
        jPanel21.add(petwalkingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, -1, -1));

        daycareButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        daycareButton.setText(" Daycare Services");
        daycareButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        daycareButton.setPreferredSize(new java.awt.Dimension(180, 75));
        daycareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daycareButtonActionPerformed(evt);
            }
        });
        jPanel21.add(daycareButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, -1, -1));

        miscellaneousButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        miscellaneousButton.setText("Miscellaneous Services");
        miscellaneousButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        miscellaneousButton.setPreferredSize(new java.awt.Dimension(180, 75));
        miscellaneousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miscellaneousButtonActionPerformed(evt);
            }
        });
        jPanel21.add(miscellaneousButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, -1, -1));

        trainingButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        trainingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/training.png"))); // NOI18N
        trainingButton.setText("Training Services");
        trainingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        trainingButton.setPreferredSize(new java.awt.Dimension(180, 75));
        trainingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainingButtonActionPerformed(evt);
            }
        });
        jPanel21.add(trainingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, -1, -1));

        addButton.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        addButton.setText("Add");
        addButton.setPreferredSize(new java.awt.Dimension(100, 25));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel21.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, -1, -1));

        jPanel14.add(jPanel21);

        jPanel15.setBackground(new java.awt.Color(102, 102, 102));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel19.setBackground(new java.awt.Color(102, 102, 102));
        jPanel19.setLayout(new java.awt.GridLayout(2, 0));

        jPanel23.setBackground(new java.awt.Color(102, 102, 102));

        jPanel18.setLayout(new java.awt.CardLayout());

        petWalkingServicesPanel.setBackground(new java.awt.Color(102, 102, 102));
        petWalkingServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        petWalkingServicesPanel.setFocusable(false);
        petWalkingServicesPanel.setPreferredSize(new java.awt.Dimension(560, 170));
        petWalkingServicesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 60));

        jCheckBox8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox8.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox8.setText("30-Minute Walk");
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });
        petWalkingServicesPanel.add(jCheckBox8);

        jCheckBox9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox9.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox9.setText("1-Hour Walk");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });
        petWalkingServicesPanel.add(jCheckBox9);

        jCheckBox10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox10.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox10.setText("Daily Walk Package");
        petWalkingServicesPanel.add(jCheckBox10);

        jPanel18.add(petWalkingServicesPanel, "card3");

        dayCareServicesPanel.setBackground(new java.awt.Color(102, 102, 102));
        dayCareServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dayCareServicesPanel.setFocusable(false);
        dayCareServicesPanel.setPreferredSize(new java.awt.Dimension(560, 170));
        dayCareServicesPanel.setLayout(new java.awt.GridBagLayout());

        jCheckBox11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox11.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox11.setText("Pet Sitting (In-home care)  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 40, 0, 0);
        dayCareServicesPanel.add(jCheckBox11, gridBagConstraints);

        jCheckBox12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox12.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox12.setText("Feeding & Medication Management");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 60);
        dayCareServicesPanel.add(jCheckBox12, gridBagConstraints);

        jPanel18.add(dayCareServicesPanel, "card4");

        trainingServicesPanel.setBackground(new java.awt.Color(102, 102, 102));
        trainingServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        trainingServicesPanel.setPreferredSize(new java.awt.Dimension(560, 170));
        trainingServicesPanel.setLayout(new java.awt.GridBagLayout());

        jCheckBox13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox13.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox13.setText("Basic Obedience Training");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        trainingServicesPanel.add(jCheckBox13, gridBagConstraints);

        jCheckBox14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox14.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox14.setText("Potty Training");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 59;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        trainingServicesPanel.add(jCheckBox14, gridBagConstraints);

        jCheckBox15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox15.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox15.setText("Behavioral Correction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        trainingServicesPanel.add(jCheckBox15, gridBagConstraints);

        jCheckBox16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox16.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox16.setText("Puppy Socialization");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        trainingServicesPanel.add(jCheckBox16, gridBagConstraints);

        jCheckBox17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox17.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox17.setText("Trick Training");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 40);
        trainingServicesPanel.add(jCheckBox17, gridBagConstraints);

        jPanel18.add(trainingServicesPanel, "card5");

        miscellaneousServicesPanel.setBackground(new java.awt.Color(102, 102, 102));
        miscellaneousServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        miscellaneousServicesPanel.setFocusable(false);
        miscellaneousServicesPanel.setPreferredSize(new java.awt.Dimension(560, 170));
        miscellaneousServicesPanel.setLayout(new java.awt.GridBagLayout());

        jCheckBox18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox18.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox18.setText("Pet Photography Session");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        miscellaneousServicesPanel.add(jCheckBox18, gridBagConstraints);

        jCheckBox19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox19.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox19.setText("Pet Birthday Celebration");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        miscellaneousServicesPanel.add(jCheckBox19, gridBagConstraints);

        jCheckBox20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox20.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox20.setText("Pet Massage or Spa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        miscellaneousServicesPanel.add(jCheckBox20, gridBagConstraints);

        jCheckBox21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox21.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox21.setText("Pet Taxi (Pickup & Drop-off service)");
        jCheckBox21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox21ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        miscellaneousServicesPanel.add(jCheckBox21, gridBagConstraints);

        jCheckBox22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox22.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox22.setText("Pet Nutrition Consultation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 22);
        miscellaneousServicesPanel.add(jCheckBox22, gridBagConstraints);

        jPanel18.add(miscellaneousServicesPanel, "card6");

        boardingServicesPanel.setBackground(new java.awt.Color(102, 102, 102));
        boardingServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        boardingServicesPanel.setFocusable(false);
        boardingServicesPanel.setPreferredSize(new java.awt.Dimension(560, 170));
        boardingServicesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 60));

        jCheckBox23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox23.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox23.setText("Full-Day Boarding");
        boardingServicesPanel.add(jCheckBox23);

        jCheckBox24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox24.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox24.setText("Half-Day Boarding");
        boardingServicesPanel.add(jCheckBox24);

        jCheckBox25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox25.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox25.setText("Overnight Stay");
        boardingServicesPanel.add(jCheckBox25);

        jPanel18.add(boardingServicesPanel, "card7");

        groomingServicesPanel.setBackground(new java.awt.Color(102, 102, 102));
        groomingServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        groomingServicesPanel.setPreferredSize(new java.awt.Dimension(560, 170));
        groomingServicesPanel.setLayout(new java.awt.GridBagLayout());

        jCheckBox1.setBackground(new java.awt.Color(102, 102, 102));
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Full Grooming");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        groomingServicesPanel.add(jCheckBox1, gridBagConstraints);

        jCheckBox2.setBackground(new java.awt.Color(102, 102, 102));
        jCheckBox2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("Bath Only");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 30, 0, 0);
        groomingServicesPanel.add(jCheckBox2, gridBagConstraints);

        jCheckBox3.setBackground(new java.awt.Color(102, 102, 102));
        jCheckBox3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setText("Nail Clipping");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        groomingServicesPanel.add(jCheckBox3, gridBagConstraints);

        jCheckBox4.setBackground(new java.awt.Color(102, 102, 102));
        jCheckBox4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox4.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox4.setText("Ear Cleaning");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 0, 0, 0);
        groomingServicesPanel.add(jCheckBox4, gridBagConstraints);

        jCheckBox5.setBackground(new java.awt.Color(102, 102, 102));
        jCheckBox5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox5.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox5.setText("Teeth Brushing  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 39, 0, 0);
        groomingServicesPanel.add(jCheckBox5, gridBagConstraints);

        jCheckBox6.setBackground(new java.awt.Color(102, 102, 102));
        jCheckBox6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox6.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox6.setText("De-shedding Treatment");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 39, 0, 0);
        groomingServicesPanel.add(jCheckBox6, gridBagConstraints);

        jCheckBox7.setBackground(new java.awt.Color(102, 102, 102));
        jCheckBox7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox7.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox7.setText("Flea/Tick Treatment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        groomingServicesPanel.add(jCheckBox7, gridBagConstraints);

        jPanel18.add(groomingServicesPanel, "card2");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1526, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(0, 463, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 464, Short.MAX_VALUE)))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(0, 8, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 8, Short.MAX_VALUE)))
        );

        jPanel19.add(jPanel23);

        jPanel15.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel15);

        jPanel8.add(jPanel14, java.awt.BorderLayout.CENTER);

        servicesPanel.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel10.add(servicesPanel, "card5");

        recordsPanel.setBackground(new java.awt.Color(241, 239, 236));
        recordsPanel.setLayout(new java.awt.GridBagLayout());

        recordsTable.setAutoCreateRowSorter(true);
        recordsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Owner", "Address", "Email", "Number", "Pet Name", "Species", "Breed", "Services", "Date", "Assistant", "Total Bill"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(recordsTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1126;
        gridBagConstraints.ipady = 530;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 33, 31, 31);
        recordsPanel.add(jScrollPane2, gridBagConstraints);

        jLabel1.setText("Search:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 20, 0, 0);
        recordsPanel.add(jLabel1, gridBagConstraints);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 177;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 6, 0, 0);
        recordsPanel.add(jTextField1, gridBagConstraints);

        jPanel10.add(recordsPanel, "card4");

        settingsPanel.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setPreferredSize(new java.awt.Dimension(130, 642));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 30));

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("About Us");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jButton7.setPreferredSize(new java.awt.Dimension(130, 100));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton7);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Log out");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jButton1.setPreferredSize(new java.awt.Dimension(130, 100));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton1);

        settingsPanel.add(jPanel11, java.awt.BorderLayout.CENTER);

        aboutPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jPanel24.setLayout(new java.awt.CardLayout());

        aboutUsPanel.setBackground(new java.awt.Color(204, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Mission");

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea2.setRows(5);
        jTextArea2.setText("Our vision is to be the most reliable and innovative digital pet care solution,\nempowering pet owners and professionals through smart tools, \nseamless experiences, and a shared love for animals.\n\n");
        jScrollPane5.setViewportView(jTextArea2);

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Vision");

        jTextArea3.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTextArea3.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea3.setRows(5);
        jTextArea3.setText("At iPETCARE, our mission is to deliver compassionate, \nconvenient, and high-quality pet services that ensure the health,\n happiness, and well-being of every pet in our care. \nWe strive to create a trusted platform that supports pet owners\n with everything from scheduling appointments to tracking medical records.");
        jScrollPane6.setViewportView(jTextArea3);

        javax.swing.GroupLayout aboutUsPanelLayout = new javax.swing.GroupLayout(aboutUsPanel);
        aboutUsPanel.setLayout(aboutUsPanelLayout);
        aboutUsPanelLayout.setHorizontalGroup(
            aboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutUsPanelLayout.createSequentialGroup()
                .addGroup(aboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aboutUsPanelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(aboutUsPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(aboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(172, Short.MAX_VALUE))
            .addGroup(aboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(aboutUsPanelLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(196, Short.MAX_VALUE)))
        );
        aboutUsPanelLayout.setVerticalGroup(
            aboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutUsPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(aboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(aboutUsPanelLayout.createSequentialGroup()
                    .addGap(104, 104, 104)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(393, Short.MAX_VALUE)))
        );

        jPanel24.add(aboutUsPanel, "card2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1114, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        aboutPanel.add(jPanel3, java.awt.BorderLayout.CENTER);

        settingsPanel.add(aboutPanel, java.awt.BorderLayout.EAST);

        jPanel10.add(settingsPanel, "card6");

        jPanel1.add(jPanel10, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void appointmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentsButtonActionPerformed
        appointmentsPanel.setVisible(true);
        recordsPanel.setVisible(false);
        servicesPanel.setVisible(false);
        settingsPanel.setVisible(false);


    }//GEN-LAST:event_appointmentsButtonActionPerformed
    
    private void recordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordsButtonActionPerformed
        appointmentsPanel.setVisible(false);
        recordsPanel.setVisible(true);
        servicesPanel.setVisible(false);
        settingsPanel.setVisible(false);

    }//GEN-LAST:event_recordsButtonActionPerformed

    private void appointmentsButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentsButtonMousePressed
        appointmentsButton.setBackground(clickedcolor);
        recordsButton.setBackground(defaultcolor);
        appointmentsButton.setForeground(white);
    }//GEN-LAST:event_appointmentsButtonMousePressed

    private void recordsButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recordsButtonMousePressed
        appointmentsButton.setBackground(defaultcolor);
        recordsButton.setBackground(clickedcolor);
        recordsButton.setForeground(white);
    }//GEN-LAST:event_recordsButtonMousePressed

    private void appointmentsButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentsButtonMouseReleased
        appointmentsButton.setBackground(defaultcolor);
    }//GEN-LAST:event_appointmentsButtonMouseReleased

    private void recordsButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recordsButtonMouseReleased
        recordsButton.setBackground(defaultcolor);
    }//GEN-LAST:event_recordsButtonMouseReleased

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        appointmentsPanel.setVisible(false);
        recordsPanel.setVisible(false);
        servicesPanel.setVisible(false);
        settingsPanel.setVisible(true);

    }//GEN-LAST:event_settingsButtonActionPerformed

    private void petNameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petNameTextfieldActionPerformed

    }//GEN-LAST:event_petNameTextfieldActionPerformed
    
    private void appendIfSelected(StringBuilder builder, JCheckBox checkBox) {
        if (checkBox.isSelected()) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(checkBox.getText());
        }
    }
    
    private void addAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAppointmentButtonActionPerformed
       
    String clientName = classNameTextfield.getText();
    String address = addressTextfield.getText();
    String email = emailTextfield.getText();
    String contact = contactTextfield.getText();
    String petName = petNameTextfield.getText();
    String species = speciesTextField.getText();
    String breed = breedTextField.getText();

    String selectedServices = jTextArea1.getText();
List<String> selectedServicesList = new ArrayList<>();

if (selectedServices != null && !selectedServices.trim().isEmpty()) {
    selectedServicesList = Arrays.asList(selectedServices.split("\\n+"));  
}


    Date schedule = jDateChooser1.getDate();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String sched = sdf.format(schedule);

    String assistant = assistantTextfield.getText();
    
    
    double totalBill = 0.0;
    String totalBillInput = totalBillTextfield.getText();
    String totalBillDisplay = ""; 

    try {
        
        String cleanInput = totalBillInput.trim().replaceAll("[^\\d\\.]", "");
        
        
        if (cleanInput.isEmpty()) {
             JOptionPane.showMessageDialog(this, "Please enter a valid number for Total Bill.", "Input Error", JOptionPane.WARNING_MESSAGE);
             return;
        }
        
       
        totalBill = Double.parseDouble(cleanInput);

        
        totalBillDisplay = String.format("$%.2f", totalBill);
        
    } catch (NumberFormatException ex) {
        // This will now only catch cases where the cleaned string is an invalid number format (e.g., "1.2.3")
        JOptionPane.showMessageDialog(this, "Please enter a valid number for Total Bill.", "Input Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (clientName.isEmpty() || petName.isEmpty() || sched == null) {
        JOptionPane.showMessageDialog(this, "Client Name, Pet Name, Services, and Schedule are required.", "Input Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // ... (Existing code for database connection and preparation)

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = jdbcConnection.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO appointments (client_name, client_address, client_email, client_contact, "
                + "pet_name, pet_species, pet_breed, selected_services, schedule, assigned_assistant, total_bill) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, clientName);
        pstmt.setString(2, address);
        pstmt.setString(3, email);
        pstmt.setString(4, contact);
        pstmt.setString(5, petName);
        pstmt.setString(6, species);
        pstmt.setString(7, breed);
        pstmt.setString(8, selectedServices);
        pstmt.setString(9, sched);
        pstmt.setString(10, assistant);
        
        
        pstmt.setDouble(11, totalBill); 

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Appointment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Add new row to JTable
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            
            // --- USE THE FORMATTED STRING (totalBillDisplay) FOR THE JTABLE ---
            model.addRow(new Object[]{
                clientName, address, email, contact,
                petName, species, breed,
                selectedServices, // Use the service string
                sched, // Use the formatted date string
                assistant, 
                totalBillDisplay // ADD THE CURRENCY FORMATTED STRING HERE
            });
            // ... (Rest of the success code)
            clearFormFields();
            fetchAppointments(null);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add appointment.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        // ... (Database error handling)
    } finally {
        // ... (Database closing)
    }
        
        /*String clientName = classNameTextfield.getText();
        String address = addressTextfield.getText();
        String email = emailTextfield.getText();
        String contact = contactTextfield.getText();
        String petName = petNameTextfield.getText();
        String species = speciesTextField.getText();
        String breed = breedTextField.getText();
        
        
        String selectedServices = jTextArea1.getText();
        List<String> selectedServicesList = new ArrayList<>();
        if (selectedServices != null && !selectedServices.trim().isEmpty()) {
            selectedServicesList = Arrays.asList(selectedServices.split(",\\s*"));
        }

        
        Date schedule = jDateChooser1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String sched = sdf.format(schedule);
        
        String assistant = assistantTextfield.getText();
        double totalBill = 0.0;
        
        try {
            totalBill = Double.parseDouble(totalBillTextfield.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for Total Bill.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (clientName.isEmpty() || petName.isEmpty() || sched == null) {
            JOptionPane.showMessageDialog(this, "Client Name, Pet Name, Services, and Schedule are required.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = jdbcConnection.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "INSERT INTO appointments (client_name, client_address, client_email, client_contact, "
                    + "pet_name, pet_species, pet_breed, selected_services, schedule, assigned_assistant, total_bill) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, clientName);
            pstmt.setString(2, address);
            pstmt.setString(3, email);
            pstmt.setString(4, contact);
            pstmt.setString(5, petName);
            pstmt.setString(6, species);
            pstmt.setString(7, breed);
            pstmt.setString(8, selectedServices);
            pstmt.setString(9, sched);
            pstmt.setString(10, assistant);
            pstmt.setDouble(11, totalBill);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Appointment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Add new row to JTable
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{
                    clientName, address, email, contact,
                    petName, species, breed,
                    schedule, assistant, totalBill, 
                });
                clearFormFields(); 
                fetchAppointments(null); 
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            System.err.println("Error adding appointment: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error adding appointment: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            jdbcConnection.closeConnection(conn, pstmt, null);
        }*/
    }//GEN-LAST:event_addAppointmentButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (selectedAppointmentId == -1) {
            JOptionPane.showMessageDialog(this, "Please select an appointment from the table to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this appointment?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = jdbcConnection.getConnection();
                if (conn == null) {
                    return;
                }

                String sql = "DELETE FROM appointments WHERE id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, selectedAppointmentId);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Appointment deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFormFields(); // Clear the form after successful deletion
                    fetchAppointments(null); // Refresh the table
                    selectedAppointmentId = -1; // Reset selected ID
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete appointment. No record found with ID: " + selectedAppointmentId, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                System.err.println("Error deleting appointment: " + e.getMessage());
                JOptionPane.showMessageDialog(this,
                        "Error deleting appointment: " + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                jdbcConnection.closeConnection(conn, pstmt, null);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        jTable1MouseClicked();
        if (selectedAppointmentId == -1) {
            JOptionPane.showMessageDialog(this, "Please select an appointment from the table to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // 1. Validate if a row is selected
    if (selectedAppointmentId == -1) {
        JOptionPane.showMessageDialog(this, "Please select an appointment from the table to print.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 2. Collect all necessary data from homeFrame's text fields/components
    //    These fields should already be populated from jTable1MouseClicked()
    String clientName = classNameTextfield.getText();
    String address = addressTextfield.getText();
    String contact = contactTextfield.getText();
    String petName = petNameTextfield.getText();
    String species = speciesTextField.getText();
    String breed = breedTextField.getText();
    String selectedServices = jTextArea1.getText(); // Services from the JTextArea

    Date scheduleDate = jDateChooser1.getDate(); // Get the Date object from JDateChooser
    String formattedSchedule = "";
    if (scheduleDate != null) {
        // Format the date for display in printFrame (e.g., "MM/dd/yyyy" or "yyyy-MM-dd")
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); // Choose your desired display format
        formattedSchedule = sdf.format(scheduleDate);
    }

    String assistant = assistantTextfield.getText();
    double totalBill = 0.0;
    try {
        totalBill = Double.parseDouble(totalBillTextfield.getText());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Total Bill is not a valid number. Printing will proceed with 0.0.", "Input Error", JOptionPane.WARNING_MESSAGE);
        // Decide how to handle this - either return or use 0.0 or a default value
    }

    // 3. Create an instance of printFrame and pass the collected data via its constructor
    //    You'll need to create this constructor in your printFrame.java (see below)
    printFrame printer = new printFrame(
        clientName, address, contact,
        petName, species, breed, selectedServices,
        formattedSchedule, assistant, totalBill
    );

    // 4. Make the printFrame visible
    printer.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed
    
    private void searchClientsByName() {
    String input = jTextField1.getText().trim();

    try {
        // Connect to your MySQL database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petcareservices?allowPublicKeyRetrieval=true&useSSL=false", "root", "allen556");

        // Query to search by client name (partial match)
        String sql = "SELECT clients_name FROM appointments WHERE client_name=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + input + "%");

        ResultSet rs = stmt.executeQuery();

        // Update the JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear old data

        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = rs.getObject(i + 1);
            }
            model.addRow(row);
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading client data:\n" + ex.getMessage());
    }
}
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (selectedAppointmentId == -1) {
            JOptionPane.showMessageDialog(this, "Please select an appointment from the table to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Collect updated data from UI fields
        String clientName = classNameTextfield.getText();
        String address = addressTextfield.getText();
        String email = emailTextfield.getText();
        String contact = contactTextfield.getText();
        String petName = petNameTextfield.getText();
        String species = speciesTextField.getText();
        String breed = breedTextField.getText();
        /**String selected = (String) servicesComboBox.getSelectedItem();
        List<String> selectedServicesList = Arrays.asList(selected.split(",\\s*"));
        String selectedServices = String.join(", ", selectedServicesList);**/
        
        String selectedServices = jTextArea1.getText();
        List<String> selectedServicesList = new ArrayList<>();
        if (selectedServices != null && !selectedServices.trim().isEmpty()) {
            selectedServicesList = Arrays.asList(selectedServices.split(",\\s*"));
        }
        
        Date schedule = jDateChooser1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String sched = sdf.format(schedule);
        String assistant = assistantTextfield.getText();
        double totalBill = 0.0;
        try {
            totalBill = Double.parseDouble(totalBillTextfield.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for Total Bill.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Basic validation
        if (clientName.isEmpty() || petName.isEmpty() || sched == null) {
            JOptionPane.showMessageDialog(this, "Client Name, Pet Name, Services, and Schedule are required.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = jdbcConnection.getConnection();
            if (conn == null) {
                return;
            }

            String sql = "UPDATE appointments SET client_name=?, client_address=?, client_email=?, client_contact=?, "
                    + "pet_name=?, pet_species=?, pet_breed=?, selected_services=?, schedule=?, assigned_assistant=?, total_bill=? "
                    + "WHERE id=?";
            pstmt = conn.prepareStatement(sql);

            // Set parameters for the prepared statement (order matters!)
            pstmt.setString(1, clientName);
            pstmt.setString(2, address);
            pstmt.setString(3, email);
            pstmt.setString(4, contact);
            pstmt.setString(5, petName);
            pstmt.setString(6, species);
            pstmt.setString(7, breed);
            pstmt.setString(8, selectedServices);
            pstmt.setString(9, sched);
            pstmt.setString(10, assistant);
            pstmt.setDouble(11, totalBill);
            pstmt.setInt(12, selectedAppointmentId); // Set the ID for the WHERE clause

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Appointment updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFormFields(); // Clear the form after successful update
                fetchAppointments(null); // Refresh the table
                selectedAppointmentId = -1; // Reset selected ID
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update appointment. No record found with ID: " + selectedAppointmentId, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            System.err.println("Error updating appointment: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error updating appointment: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            jdbcConnection.closeConnection(conn, pstmt, null);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void speciesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speciesTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_speciesTextFieldActionPerformed

    private void groomingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groomingButtonActionPerformed
        //        cardLayout.show(jPanel5, "groomingCard");
        groomingServicesPanel.setVisible(true);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(false);
        //        groomingServicesPanel.getParent().setComponentZOrder(groomingServicesPanel, 1);
        groomingServicesPanel.revalidate();
        groomingServicesPanel.repaint();
    }//GEN-LAST:event_groomingButtonActionPerformed

    private void boardingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boardingButtonActionPerformed
        //          cardLayout.show(jPanel5, "boardingCard");
        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(true);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(false);
        //        boardingServicesPanel.getParent().setComponentZOrder(boardingServicesPanel, 2);
        boardingServicesPanel.revalidate();
        boardingServicesPanel.repaint();
    }//GEN-LAST:event_boardingButtonActionPerformed

    private void petwalkingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petwalkingButtonActionPerformed
        //        cardLayout.show(jPanel5, "petWalkingCard");
        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(true);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(false);
        //        petWalkingServicesPanel.getParent().setComponentZOrder(petWalkingServicesPanel, 3);
        petWalkingServicesPanel.revalidate();
        petWalkingServicesPanel.repaint();
    }//GEN-LAST:event_petwalkingButtonActionPerformed

    private void trainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainingButtonActionPerformed
        //          cardLayout.show(jPanel5, "trainingCard");
        //        trainingServicesPanel.getParent().setComponentZOrder(trainingServicesPanel, 4);
        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(true);
        miscellaneousServicesPanel.setVisible(false);
        trainingServicesPanel.repaint();
        trainingServicesPanel.revalidate();
    }//GEN-LAST:event_trainingButtonActionPerformed

    private void daycareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daycareButtonActionPerformed
        //          cardLayout.show(jPanel5, "dayCareCard");
        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(true);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(false);
        //        dayCareServicesPanel.getParent().setComponentZOrder(dayCareServicesPanel, 5);
        dayCareServicesPanel.revalidate();
        dayCareServicesPanel.repaint();
    }//GEN-LAST:event_daycareButtonActionPerformed

    private void miscellaneousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miscellaneousButtonActionPerformed
        //        cardLayout.show(jPanel5, "miscellaneousCard");
        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(true);
        //        miscellaneousServicesPanel.getParent().setComponentZOrder(miscellaneousServicesPanel, 6);
        miscellaneousServicesPanel.revalidate();
        miscellaneousServicesPanel.repaint();
    }//GEN-LAST:event_miscellaneousButtonActionPerformed
// Example pricing map to be defined as a class member
private final Map<String, Double> servicePrices = createServicePricesMap();

private Map<String, Double> createServicePricesMap() {
    Map<String, Double> prices = new HashMap<>();
    
    
    prices.put("Full Grooming", 65.00); 
    prices.put("Nail Clipping", 15.00); 
    prices.put("Teeth Brushing", 10.00); 
    prices.put("Flea/Tick Treatment", 25.00);
    prices.put("Bath Only", 35.00);
    prices.put("Ear Cleaning", 12.00);
    prices.put("De-shedding Treatment", 40.00);

    
    prices.put("30 min Walk", 20.00); 
    prices.put("60 min Walk", 35.00); 
    prices.put("Daily Park Visit", 45.00); 

    
    
    return prices;
}
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
      List<String> selectedServices = new ArrayList<>();
    double totalBill = 0.0; // Initialize total bill

    // --- Collect selections (Your existing collection logic) ---

    // Collect selections from Grooming
    if (jCheckBox1.isSelected()) selectedServices.add(jCheckBox1.getText());
    if (jCheckBox2.isSelected()) selectedServices.add(jCheckBox2.getText());
    if (jCheckBox3.isSelected()) selectedServices.add(jCheckBox3.getText());
    if (jCheckBox4.isSelected()) selectedServices.add(jCheckBox4.getText());
    if (jCheckBox5.isSelected()) selectedServices.add(jCheckBox5.getText());
    if (jCheckBox6.isSelected()) selectedServices.add(jCheckBox6.getText());
    if (jCheckBox7.isSelected()) selectedServices.add(jCheckBox7.getText());

    // Collect selections from Pet Walking
    if (jCheckBox8.isSelected()) selectedServices.add(jCheckBox8.getText());
    if (jCheckBox9.isSelected()) selectedServices.add(jCheckBox9.getText());
    if (jCheckBox10.isSelected()) selectedServices.add(jCheckBox10.getText());

    // Collect selections from Day Care
    if (jCheckBox11.isSelected()) selectedServices.add(jCheckBox11.getText());
    if (jCheckBox12.isSelected()) selectedServices.add(jCheckBox12.getText());

    // Collect selections from Training
    if (jCheckBox13.isSelected()) selectedServices.add(jCheckBox13.getText());
    if (jCheckBox14.isSelected()) selectedServices.add(jCheckBox14.getText());
    if (jCheckBox15.isSelected()) selectedServices.add(jCheckBox15.getText());
    if (jCheckBox16.isSelected()) selectedServices.add(jCheckBox16.getText());
    if (jCheckBox17.isSelected()) selectedServices.add(jCheckBox17.getText());

    // Collect selections from Miscellaneous
    if (jCheckBox18.isSelected()) selectedServices.add(jCheckBox18.getText());
    if (jCheckBox19.isSelected()) selectedServices.add(jCheckBox19.getText());
    if (jCheckBox20.isSelected()) selectedServices.add(jCheckBox20.getText());
    if (jCheckBox21.isSelected()) selectedServices.add(jCheckBox21.getText());
    if (jCheckBox22.isSelected()) selectedServices.add(jCheckBox22.getText());

    // Collect selections from Boarding
    if (jCheckBox23.isSelected()) selectedServices.add(jCheckBox23.getText());
    if (jCheckBox24.isSelected()) selectedServices.add(jCheckBox24.getText());
    if (jCheckBox25.isSelected()) selectedServices.add(jCheckBox25.getText());

    // --- NEW: Calculate Total Bill ---
    for (String serviceName : selectedServices) {
        // Look up the price for the service name in the map
        Double price = servicePrices.get(serviceName);
        
        if (price != null) {
            totalBill += price;
        } else {
            // Handle case where a service name might not be in the map (optional)
            System.err.println("Warning: Price not found for service: " + serviceName);
        }
    }

    if (selectedServices.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No services were selected.", "Information", JOptionPane.INFORMATION_MESSAGE);
        // Reset the total if no services are selected
        totalBillTextfield.setText("0.00"); 
    } else {
        String verticalServices = String.join("\n", selectedServices);
jTextArea1.setText(verticalServices);

        // Pass selected services back to homeFrame's components
        /*String joinedServices = String.join(", ", selectedServices);
        jTextArea1.setText(joinedServices); // Update jTextArea1 with service list*/

        // Update the Total Bill TextField (Assuming it's named totalBillTextField)
        String formattedTotal = String.format("%.2f", totalBill);
        totalBillTextfield.setText(formattedTotal); 

        JOptionPane.showMessageDialog(this, "Selected services added: \n" + String.join("\n", selectedServices) + 
                                          "\n\nTotal Cost: " + formattedTotal, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
     appointmentsPanel.setVisible(true);
        recordsPanel.setVisible(false);
        servicesPanel.setVisible(false);
        settingsPanel.setVisible(false);
        servicesPanel.revalidate();
        servicesPanel.repaint();
        /* List<String> selectedServices = new ArrayList<>();

        // Collect selections from Grooming
        if (jCheckBox1.isSelected()) selectedServices.add(jCheckBox1.getText());
        if (jCheckBox2.isSelected()) selectedServices.add(jCheckBox2.getText());
        if (jCheckBox3.isSelected()) selectedServices.add(jCheckBox3.getText());
        if (jCheckBox4.isSelected()) selectedServices.add(jCheckBox4.getText());
        if (jCheckBox5.isSelected()) selectedServices.add(jCheckBox5.getText());
        if (jCheckBox6.isSelected()) selectedServices.add(jCheckBox6.getText());
        if (jCheckBox7.isSelected()) selectedServices.add(jCheckBox7.getText());

        // Collect selections from Pet Walking
        if (jCheckBox8.isSelected()) selectedServices.add(jCheckBox8.getText());
        if (jCheckBox9.isSelected()) selectedServices.add(jCheckBox9.getText());
        if (jCheckBox10.isSelected()) selectedServices.add(jCheckBox10.getText());

        // Collect selections from Day Care
        if (jCheckBox11.isSelected()) selectedServices.add(jCheckBox11.getText());
        if (jCheckBox12.isSelected()) selectedServices.add(jCheckBox12.getText());

        // Collect selections from Training
        if (jCheckBox13.isSelected()) selectedServices.add(jCheckBox13.getText());
        if (jCheckBox14.isSelected()) selectedServices.add(jCheckBox14.getText());
        if (jCheckBox15.isSelected()) selectedServices.add(jCheckBox15.getText());
        if (jCheckBox16.isSelected()) selectedServices.add(jCheckBox16.getText());
        if (jCheckBox17.isSelected()) selectedServices.add(jCheckBox17.getText());

        // Collect selections from Miscellaneous
        if (jCheckBox18.isSelected()) selectedServices.add(jCheckBox18.getText());
        if (jCheckBox19.isSelected()) selectedServices.add(jCheckBox19.getText());
        if (jCheckBox20.isSelected()) selectedServices.add(jCheckBox20.getText());
        if (jCheckBox21.isSelected()) selectedServices.add(jCheckBox21.getText());
        if (jCheckBox22.isSelected()) selectedServices.add(jCheckBox22.getText());

        // Collect selections from Boarding
        if (jCheckBox23.isSelected()) selectedServices.add(jCheckBox23.getText());
        if (jCheckBox24.isSelected()) selectedServices.add(jCheckBox24.getText());
        if (jCheckBox25.isSelected()) selectedServices.add(jCheckBox25.getText());


        if (selectedServices.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No services were selected.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Pass selected services back to homeFrame's setSelectedServices method
            String joinedServices = String.join(", ", selectedServices);
            jTextArea1.setText(joinedServices); // Update jTextArea1 in homeFrame

            JOptionPane.showMessageDialog(this, "Selected services added: \n" + String.join("\n", selectedServices), "Success", JOptionPane.INFORMATION_MESSAGE);
            
        }*/
        
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to exit?",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            logInFrame login = new logInFrame();
            login.setVisible(true);
            dispose();;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        aboutUsPanel.setVisible(true);
       
        aboutUsPanel.revalidate();
        aboutUsPanel.repaint();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void servicebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicebuttonActionPerformed
        appointmentsPanel.setVisible(false);
        recordsPanel.setVisible(false);
        servicesPanel.setVisible(true);
        settingsPanel.setVisible(false);
        servicesPanel.revalidate();
        servicesPanel.repaint();
    }//GEN-LAST:event_servicebuttonActionPerformed

    private void totalBillTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalBillTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalBillTextfieldActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox21ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void emailTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextfieldActionPerformed

    private void classNameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classNameTextfieldActionPerformed

    }//GEN-LAST:event_classNameTextfieldActionPerformed

    private void assistantTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assistantTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assistantTextfieldActionPerformed

    
       
    
    
    public void setSelectedServices(String services) {
        jTextArea1.setText(services);
    }

    private void jTable1MouseClicked() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            editingRow = selectedRow;
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            // Retrieve data from the selected row.
            // Column indices must match the order in your DefaultTableModel defined in initComponents().
            selectedAppointmentId = (int) model.getValueAt(selectedRow, 11); // Assuming ID is in column 11 (0-indexed)

            classNameTextfield.setText(model.getValueAt(selectedRow, 0).toString()); // Client Name
            addressTextfield.setText(model.getValueAt(selectedRow, 1).toString()); // Address
            emailTextfield.setText(model.getValueAt(selectedRow, 2).toString()); // Email
            contactTextfield.setText(model.getValueAt(selectedRow, 3).toString()); // Contact
            petNameTextfield.setText(model.getValueAt(selectedRow, 4).toString()); // Pet Name
            speciesTextField.setText(model.getValueAt(selectedRow, 5).toString()); // Species
            breedTextField.setText(model.getValueAt(selectedRow, 6).toString()); // Breed
            
            String servicesString = model.getValueAt(selectedRow, 7).toString();
            jTextArea1.setText(servicesString);
            /**String servicesString = model.getValueAt(selectedRow, 7).toString(); // Selected Services (comma-separated string)
            // Convert comma-separated string back to List<String> for ComboBoxMultiSelection
            // Handle potential null or empty string to avoid error with split()
            if (servicesString != null && !servicesString.trim().isEmpty()) {
                List<String> servicesList = new ArrayList<>(Arrays.asList(servicesString.split(",\\s*")));
                servicesComboBox.setSelectedItem(servicesList); // Set selected items in the multi-selection combo box
            } else {
                servicesComboBox.setSelectedIndex(0);// Clear if no services were stored
            }**/

            String scheduleString = model.getValueAt(selectedRow, 8).toString(); // Schedule as String
            if (scheduleString != null && !scheduleString.trim().isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); // Assuming "MM/dd/yyyy" format
                    Date date = sdf.parse(scheduleString);
                    jDateChooser1.setDate(date); // Set the parsed Date to JDateChooser
                } catch (Exception e    ) {
                    System.err.println("Error parsing schedule date from table: " + scheduleString + " - " + e.getMessage());
                    JOptionPane.showMessageDialog(this, "Error parsing schedule date. Format might be incorrect.", "Date Error", JOptionPane.WARNING_MESSAGE);
                    jDateChooser1.setDate(null); // Clear the date chooser on error
                }
            } else {
                jDateChooser1.setDate(null); // Clear the date chooser if no date is found
            }
            assistantTextfield.setText(model.getValueAt(selectedRow, 9).toString()); // Assistant
            totalBillTextfield.setText(model.getValueAt(selectedRow, 10).toString()); // Total Bill
        } else {
            // If no row is selected (e.g., selection cleared), reset editing state
            editingRow = -1;
            selectedAppointmentId = -1;
            clearFormFields(); // Optionally clear fields when selection is lost
        }
    }

    private void clearFormFields() {
        classNameTextfield.setText(""); // Client Name
        addressTextfield.setText(""); // Address
        emailTextfield.setText(""); // Email
        contactTextfield.setText(""); // Contact
        petNameTextfield.setText(""); // Pet Name
        speciesTextField.setText(""); // Species
        breedTextField.setText(""); // Breed
        //servicesComboBox.setSelectedIndex(0); // Clear selected items in the multi-selection combo box
        jTextArea1.setText("");
        jDateChooser1.setDate(null); // Schedule
        assistantTextfield.setText(""); // Assistant
        // Assuming jTextFieldTotalBill is the JTextField for Total Bill.
        // You need to add this JTextField in NetBeans Design View if it's not there.
        totalBillTextfield.setText("");
        selectedAppointmentId = -1; // Reset selected ID
        jTable1.clearSelection(); // Clear table selection
    }

    private void fetchAppointments(String clientNameFilter) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel model2 = (DefaultTableModel) recordsTable.getModel();
        model.setRowCount(0); // Clear existing rows
        model2.setRowCount(0);
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = jdbcConnection.getConnection();
            if (conn == null) {
                System.err.println("Database connection failed in fetchAppointments.");
                return;
            }

            String sql;
            if (clientNameFilter != null && !clientNameFilter.isEmpty()) {
                sql = "SELECT id, client_name, client_address, client_email, client_contact, "
                    + "pet_name, pet_species, pet_breed, selected_services, schedule, "
                    + "assigned_assistant, total_bill FROM appointments WHERE client_name LIKE ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "%" + clientNameFilter + "%");
            } else {
                sql = "SELECT id, client_name, client_address, client_email, client_contact, "
                    + "pet_name, pet_species, pet_breed, selected_services, schedule, "
                    + "assigned_assistant, total_bill FROM appointments";
                pstmt = conn.prepareStatement(sql);
            }     
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id"); // Optional
                String clientName = rs.getString("client_name");
                String address = rs.getString("client_address");
                String email = rs.getString("client_email");
                String contact = rs.getString("client_contact");

                String petName = rs.getString("pet_name");
                String species = rs.getString("pet_species");
                String breed = rs.getString("pet_breed");

                String services = rs.getString("selected_services");
                String schedule = rs.getString("schedule");
                String assistant = rs.getString("assigned_assistant");
                double totalBill = rs.getDouble("total_bill");

                // Adjust this based on your JTable structure.
                // If your table includes a hidden ID column:
                model.addRow(new Object[]{
                    clientName, address, email, contact,
                    petName, species, breed,
                    services, schedule, assistant, totalBill, id
                });
                model2.addRow(new Object[]{
                    clientName, address, email, contact,
                    petName, species, breed,
                    services, schedule, assistant, totalBill, id
                });

                // If your table does NOT include an ID column, use this instead:
                // model.addRow(new Object[] {
                //     clientName, address, email, contact,
                //     petName, species, breed,
                //     services, schedule, assistant, totalBill
                // });
            }

            System.out.println("Appointments successfully fetched and loaded into JTable.");

        } catch (SQLException e) {
            System.err.println("Error fetching appointments: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error fetching appointments: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            jdbcConnection.closeConnection(conn, pstmt, rs);
        }

        // Optionally hide the last column (ID) if your table has it
        // Uncomment this only if your JTable has the ID column as the last column:
        /*
    if (jTable1.getColumnCount() > 11) {
        jTable1.getColumnModel().getColumn(11).setMinWidth(0);
        jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(11).setWidth(0);
    }
         */
//        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//        model.setRowCount(0);
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = jdbcConnection.getConnection();
//            if (conn == null) {
//                return; // Exit if connection failed
//            }
//
//            String sql = "SELECT id, client_name, client_address, client_email, client_contact, "
//                    + "pet_name, pet_species, pet_breed, selected_services, schedule, "
//                    + "assigned_assistant, total_bill FROM appointments";
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                // Retrieve data from ResultSet. Ensure column names match your database.
//                int id = rs.getInt("id");
//                String clientName = rs.getString("client_name");
//                String address = rs.getString("client_address");
//                String email = rs.getString("client_email");
//                String contact = rs.getString("client_contact");
//                String petName = rs.getString("pet_name");
//                String species = rs.getString("pet_species");
//                String breed = rs.getString("pet_breed");
//                String services = rs.getString("selected_services");
//                String schedule = rs.getString("schedule");
//                String assistant = rs.getString("assigned_assistant");
//                double totalBill = rs.getDouble("total_bill");
//
//                // Add data to table model. Ensure order matches the table columns defined in initComponents().
//                model.addRow(new Object[]{
//                    clientName, address, email, contact, // Client Info
//                    petName, species, breed, // Pet Info
//                    services, schedule, assistant, totalBill, // Service/Schedule/Bill Info
//                    id // Hidden ID column
//                });
//            }
//
//            jTable1.getColumnModel().getColumn(11).setMinWidth(0);
//            jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
//            jTable1.getColumnModel().getColumn(11).setWidth(0);
//            System.out.println("Appointments fetched and displayed.");
//
//        } catch (SQLException e) {
//            System.err.println("Error fetching appointments: " + e.getMessage());
//            JOptionPane.showMessageDialog(this,
//                    "Error fetching appointments: " + e.getMessage(),
//                    "Database Error",
//                    JOptionPane.ERROR_MESSAGE);
//        } finally {
//            jdbcConnection.closeConnection(conn, pstmt, rs);
//        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            com.formdev.flatlaf.FlatLightLaf.setup();

        } catch (Exception ex) {
            Logger.getLogger(homeFrame.class.getName()).log(Level.SEVERE, "Failed to set FlatLaf Look and Feel", ex);

            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception fallbackEx) {
                Logger.getLogger(homeFrame.class.getName()).log(Level.SEVERE, "Failed to set cross-platform L&F", fallbackEx);
            }
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new homeFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel aboutPanel;
    private javax.swing.JPanel aboutUsPanel;
    private javax.swing.JButton addAppointmentButton;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addressTextfield;
    private javax.swing.JButton appointmentsButton;
    private javax.swing.JPanel appointmentsPanel;
    private javax.swing.JTextField assistantTextfield;
    private javax.swing.JButton boardingButton;
    private javax.swing.JPanel boardingServicesPanel;
    private javax.swing.JTextField breedTextField;
    private javax.swing.JTextField classNameTextfield;
    private javax.swing.JTextField contactTextfield;
    private javax.swing.JPanel dayCareServicesPanel;
    private javax.swing.JButton daycareButton;
    private javax.swing.JTextField emailTextfield;
    private javax.swing.JButton groomingButton;
    private javax.swing.JPanel groomingServicesPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton miscellaneousButton;
    private javax.swing.JPanel miscellaneousServicesPanel;
    private javax.swing.JTextField petNameTextfield;
    private javax.swing.JPanel petWalkingServicesPanel;
    private javax.swing.JButton petwalkingButton;
    private javax.swing.JButton recordsButton;
    private javax.swing.JPanel recordsPanel;
    private javax.swing.JTable recordsTable;
    private javax.swing.JButton servicebutton;
    private javax.swing.JPanel servicesPanel;
    private javax.swing.JButton settingsButton;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JTextField speciesTextField;
    private javax.swing.JTextField totalBillTextfield;
    private javax.swing.JButton trainingButton;
    private javax.swing.JPanel trainingServicesPanel;
    // End of variables declaration//GEN-END:variables

    private void testData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

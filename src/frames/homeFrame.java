/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import JDBC.jdbcConnection;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.sql.ResultSetMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author ADMIN
 */
public class homeFrame extends javax.swing.JFrame {

    private TableRowSorter<TableModel> recordsSorter;
    private CardLayout cards;
    private Connection conn; 
    private List<JButton> buttons = new ArrayList<>();
    private JButton active;
    private javax.swing.JTextField txtAppointmentId;
    private javax.swing.JTextField txtTime;
    private javax.swing.JComboBox<String> cmbStatus;
    private String currentUserRole;
    private int selectedAppointmentId = -1;
    private boolean fieldsLoaded = false; 
    private int lastAddedAppointmentId = -1;
    private List<JCheckBox> serviceCheckboxes = new ArrayList<>();
    private String originalServices = "";  // Track original services before editing
    private boolean servicesChanged = false;  // Track if services were modified
    private Map<String, String> originalAppointmentData = new HashMap<>();  // Track original data before editing
    
    
Color defaultcolor = new Color(0, 0, 0);
    Color clickedcolor = new Color(47, 110, 138);
    Color white = new Color(255, 255, 255);
    private DefaultTableModel table = new DefaultTableModel(new Object[]{"Client Name", "Address", "Email", "Contact", "Pet Name", "Species", "Breed", "Service/s", "Schedule", "Assistant", "Total Bill", "ID"}, 0);
    int editingRow = -1;
     

    private void setupServicesColumnRenderer() {
        recordsTable.getColumnModel().getColumn(6).setCellRenderer(new MultiLineCellRenderer());
    }
    
    
    public homeFrame() {
        initComponents();

    this.setSize(1400, 820);  // Set size FIRST
    this.setMinimumSize(new Dimension(1400, 820));
    this.setMaximumSize(new Dimension(1400, 820));
    this.setPreferredSize(new Dimension(1400, 820));
    this.pack(); 
    this.setLocationRelativeTo(null);  // Center AFTER setting size
    
    // ===== FIX PANEL SIZES =====
    // Fix jPanel1 (main container)
   jPanel1.setMinimumSize(new Dimension(1400, 820));
jPanel1.setMaximumSize(new Dimension(1400, 820));
jPanel1.setPreferredSize(new Dimension(1400, 820));

// Fix jPanel2 (header/navigation)
jPanel2.setMinimumSize(new Dimension(1400, 73));
jPanel2.setMaximumSize(new Dimension(1400, 73));
jPanel2.setPreferredSize(new Dimension(1400, 73));

// Fix jPanel10 (card layout container) - 820 - 73 = 747
jPanel10.setMinimumSize(new Dimension(1400, 747));
jPanel10.setMaximumSize(new Dimension(1400, 747));
jPanel10.setPreferredSize(new Dimension(1400, 747));

// Fix appointmentsPanel
appointmentsPanel.setMinimumSize(new Dimension(1400, 747));
appointmentsPanel.setMaximumSize(new Dimension(1400, 747));
appointmentsPanel.setPreferredSize(new Dimension(1400, 747));

// Fix recordsPanel
recordsPanel.setMinimumSize(new Dimension(1400, 747));
recordsPanel.setMaximumSize(new Dimension(1400, 747));
recordsPanel.setPreferredSize(new Dimension(1400, 747));

// Fix settingsPanel
settingsPanel.setMinimumSize(new Dimension(1400, 747));
settingsPanel.setMaximumSize(new Dimension(1400, 747));
settingsPanel.setPreferredSize(new Dimension(1400, 747));

// Fix servicesPanel
servicesPanel.setMinimumSize(new Dimension(1400, 747));
servicesPanel.setMaximumSize(new Dimension(1400, 747));
servicesPanel.setPreferredSize(new Dimension(1400, 747));
    
    jPanel10.setLayout(new CardLayout());
    
    // Revalidate to apply changes
    this.revalidate();
    this.repaint();
    
    recordsTable.setFillsViewportHeight(true);
    
    jTextArea1.setEditable(false);
    totalBillTextfield.setEditable(false);
    
    // Add this after initComponents(); in both constructors
appointmentsPanel.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        // Clear selection only if the click is not on the recordsTable
        if (e.getSource() != recordsTable) {
            recordsTable.clearSelection();
            selectedAppointmentId = -1;
        }
    }
});

    contactTextfield.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            String currentText = contactTextfield.getText();
            
            if (!Character.isDigit(c)) {
                e.consume();
                return;
            }
            
            if (currentText.length() >= 11) {
                e.consume();
            }
        }
    });


            buttons.add(appointmentsButton);
            buttons.add(recordsButton);
            buttons.add(settingsButton);
        
        
        
            buttons.add(appointmentsButton);
            buttons.add(recordsButton);
            buttons.add(settingsButton);
            active = appointmentsButton;
            active = recordsButton;
            active = settingsButton;

        setupRecordSearch();
//        setupRecordDoubleClick();
        setupDateColumnRenderer();
        setupServicesColumnRenderer();
        disableDateChooserTyping();
        disableRecordsTableEditing();

        recordsPanel.revalidate();
        recordsPanel.repaint();


        serviceCheckboxes.addAll(Arrays.asList(
        jCheckBox1, jCheckBox2, jCheckBox3, jCheckBox4, jCheckBox5, jCheckBox6, jCheckBox7,
        jCheckBox8, jCheckBox9, jCheckBox10, jCheckBox11, jCheckBox12, jCheckBox13, jCheckBox14,
        jCheckBox15, jCheckBox16, jCheckBox17, jCheckBox18, jCheckBox19, jCheckBox20, jCheckBox21,
        jCheckBox22, jCheckBox23, jCheckBox24, jCheckBox25
        ));

            txtAppointmentId = new javax.swing.JTextField();
            txtTime = new javax.swing.JTextField();
            cmbStatus = new javax.swing.JComboBox<>();

            cards = (CardLayout) jPanel10.getLayout();
            recordsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            recordsTable.setDefaultEditor(Object.class, null);

    JComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Billy","Abel","Bob","Caleb","Doris","Eddie"}));
    JComboBox.setSelectedIndex(-1);
    JComboBox.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, 
                int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (index == -1 && value == null) {
                setText("");
            }
            return this;
        }
    });
        
        this.currentUserRole = "Guest";
        System.out.println("Home frame initialized without a specific role. Defaulting to: " + this.currentUserRole);

        switchTab(appointmentsPanel, appointmentsButton);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(recordsTable.getModel());
        recordsTable.setRowSorter(sorter);
       
       // loadRecordsTable();
       loadRecordsAsCards();
        updateAppointmentCount();
        
//        setupRecordsTableSelection();
//        updateAppointmentCount();
        
        adjustRecordsTableColumnWidths();
        
        jScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

// Style the table header
JTableHeader header = recordsTable.getTableHeader();
header.setBackground(new Color(7, 122, 125));  // #077A7D
header.setForeground(Color.WHITE);
header.setFont(new Font("Segoe UI", Font.BOLD, 12));
header.setPreferredSize(new Dimension(header.getWidth(), 30));

// Set row selection color to #E6F7F7
recordsTable.setSelectionBackground(new Color(0xE6, 0xF7, 0xF7));
recordsTable.setSelectionForeground(Color.BLACK);
        
        
        
        recordsSorter = new TableRowSorter<>(recordsTable.getModel());
        recordsTable.setRowSorter(recordsSorter);
    
   
 
       
    highlightButtonBorder(appointmentsButton);
//    records();
    
    recordsTable.setMinimumSize(null);
    recordsTable.setMaximumSize(null);
    
    setupNavigationButtons();
    setupServiceButtons();

    boardingServicesPanel.setVisible(true);
    groomingServicesPanel.setVisible(false);
    petWalkingServicesPanel.setVisible(false);
    dayCareServicesPanel.setVisible(false);
    trainingServicesPanel.setVisible(false);
    miscellaneousServicesPanel.setVisible(false);
    highlightServiceButtonBorder(boardingButton);
    
    recordsTable.setMinimumSize(null);
    recordsTable.setMaximumSize(null);

    }

    public homeFrame(String userRole) { 
    initComponents();
    
   this.setSize(1400, 820);  // Set size FIRST
    this.setMinimumSize(new Dimension(1400, 820));
    this.setMaximumSize(new Dimension(1400, 820));  // Fixed typo: was 8500
    this.setPreferredSize(new Dimension(1400, 820));
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    
    // ===== FIX PANEL SIZES =====
    jPanel1.setMinimumSize(new Dimension(1400, 820));
    jPanel1.setMaximumSize(new Dimension(1400, 820));
    jPanel1.setPreferredSize(new Dimension(1400, 820));
    
    jPanel2.setMinimumSize(new Dimension(1400, 73));
    jPanel2.setMaximumSize(new Dimension(1400, 73));
    jPanel2.setPreferredSize(new Dimension(1400, 73));
    
    jPanel10.setMinimumSize(new Dimension(1400, 747));
    jPanel10.setMaximumSize(new Dimension(1400, 747));
    jPanel10.setPreferredSize(new Dimension(1400, 747));
    
    appointmentsPanel.setMinimumSize(new Dimension(1400, 747));
appointmentsPanel.setMaximumSize(new Dimension(1400, 747));
appointmentsPanel.setPreferredSize(new Dimension(1400, 747));

// Fix recordsPanel
recordsPanel.setMinimumSize(new Dimension(1400, 747));
recordsPanel.setMaximumSize(new Dimension(1400, 747));
recordsPanel.setPreferredSize(new Dimension(1400, 747));

// Fix settingsPanel
settingsPanel.setMinimumSize(new Dimension(1400, 747));
settingsPanel.setMaximumSize(new Dimension(1400, 747));
settingsPanel.setPreferredSize(new Dimension(1400, 747));

// Fix servicesPanel
servicesPanel.setMinimumSize(new Dimension(1400, 747));
servicesPanel.setMaximumSize(new Dimension(1400, 747));
servicesPanel.setPreferredSize(new Dimension(1400, 747));
    
    recordsTable.setFillsViewportHeight(true);
    
    this.currentUserRole = userRole;
    customizeUIBasedOnRole();
   
    
//    setupRecordsTableStyling();
// Add this after initComponents(); in both constructors
appointmentsPanel.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        // Clear selection only if the click is not on the recordsTable
        if (e.getSource() != recordsTable) {
            recordsTable.clearSelection();
            selectedAppointmentId = -1;
        }
    }
});
    
    // NOW adjust column widths AFTER table is loaded
    adjustRecordsTableColumnWidths();
    
    
    // Apply border to scroll pane
    jScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

    // Style the table header
    JTableHeader header = recordsTable.getTableHeader();
    header.setBackground(new Color(7, 122, 125));  // #077A7D
    header.setForeground(Color.WHITE);
    header.setFont(new Font("Segoe UI", Font.BOLD, 12));
    header.setPreferredSize(new Dimension(header.getWidth(), 30));

    // Set row selection color to #E6F7F7
    recordsTable.setSelectionBackground(new Color(0xE6, 0xF7, 0xF7));
    recordsTable.setSelectionForeground(Color.BLACK);
//    loadRecordsTable();
//    setupRecordsTableSelection();
//    updateAppointmentCount();
    
    //loadRecordsTable();
    loadRecordsAsCards();
    updateAppointmentCount(); 

    jTextArea1.setEditable(false);
    totalBillTextfield.setEditable(false);
    
//    jPanel1.setLayout(new BorderLayout());
    jPanel10.setLayout(new CardLayout());
    
    this.revalidate();
    this.repaint();
    
    contactTextfield.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            String currentText = contactTextfield.getText();
            
            // Only allow digits
            if (!Character.isDigit(c)) {
                e.consume();
                return;
            }
            
            // Limit to 11 digits
            if (currentText.length() >= 11) {
                e.consume();
            }
        }
    });
   
    this.currentUserRole = userRole;
    
    
    txtAppointmentId = new javax.swing.JTextField();
        txtTime = new javax.swing.JTextField();
        cmbStatus = new javax.swing.JComboBox<>();
        
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        // Just filter, don't fetch
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
        // Just filter, don't fetch
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
        // Just filter, don't fetch
    }
    });

    this.currentUserRole = userRole;
    System.out.println("User logged in with role: " + this.currentUserRole);
    customizeUIBasedOnRole();

  
    setupNavigationButtons();
    setupServiceButtons();

    boardingServicesPanel.setVisible(true);
    groomingServicesPanel.setVisible(false);
    petWalkingServicesPanel.setVisible(false);
    dayCareServicesPanel.setVisible(false);
    trainingServicesPanel.setVisible(false);
    miscellaneousServicesPanel.setVisible(false);
    highlightServiceButtonBorder(boardingButton);
    
    }
/**
 * Apply rounded border and header styling to recordsTable
 */
    private void setupRecordsTableStyling() {
    // Set border with #000000 color
    javax.swing.border.Border lineBorder = BorderFactory.createLineBorder(
        new Color(0, 0, 0), 2);  // Changed to black
    javax.swing.border.Border roundedBorder = new javax.swing.border.CompoundBorder(
        lineBorder,
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
    );
    jScrollPane2.setBorder(roundedBorder);
    
    // Style the header with #077A7D
    JTableHeader header = recordsTable.getTableHeader();
    header.setBackground(new Color(7, 122, 125));  // #077A7D
    header.setForeground(Color.WHITE);
    header.setFont(new Font("Segoe UI", Font.BOLD, 12));
    header.setPreferredSize(new Dimension(header.getWidth(), 30));
    
    // Set row selection color to #E6F7F7
    recordsTable.setSelectionBackground(new Color(0xE6, 0xF7, 0xF7));
    recordsTable.setSelectionForeground(Color.BLACK);
}
    
    private void setupRecordsTableSelection() {
    recordsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    recordsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = recordsTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Convert view row to model row (in case of sorting/filtering)
                    int modelRow = recordsTable.convertRowIndexToModel(selectedRow);
                    
                    // Get ID from the hidden first column (index 0)
                    selectedAppointmentId = (int) recordsTable.getModel().getValueAt(modelRow, 0);
                    
                    System.out.println("Selected appointment ID: " + selectedAppointmentId);
                } else {
                    selectedAppointmentId = -1;
                }
            }
        }
    });
}
    
    
    
    
   private void updateAppointmentCount() {
    int rowCount = recordsTable.getRowCount();
    appointmentCount.setText(String.valueOf("   "+rowCount));
    appointmentCount.setOpaque(true);
    appointmentCount.setBackground(Color.WHITE);
    appointmentCount.setForeground(Color.BLACK);
    appointmentCount.revalidate();
    appointmentCount.repaint();
}
   
   private void loadRecordsAsCards() {
    // Clear existing content
    recordsPanel.removeAll();
    
    // Set up the main layout
    recordsPanel.setLayout(new BorderLayout(10, 10));
    recordsPanel.setBackground(new Color(244, 247, 247));
    
    // Create header panel
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(new Color(244, 247, 247));
    headerPanel.setBorder(new EmptyBorder(20, 30, 10, 30));
    
    JLabel titleLabel = new JLabel("PET RECORDS");
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
    titleLabel.setForeground(new Color(7, 122, 125));
    
    // Search panel
    JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    searchPanel.setBackground(new Color(244, 247, 247));
    
    JTextField searchField = new JTextField(20);
    searchField.setBorder(new LineBorder(new Color(0, 51, 51), 1));
    searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    
    JLabel searchLabel = new JLabel("Search:");
    searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
    
    searchPanel.add(searchLabel);
    searchPanel.add(searchField);
    
    headerPanel.add(titleLabel, BorderLayout.WEST);
    headerPanel.add(searchPanel, BorderLayout.EAST);
    
    recordsPanel.add(headerPanel, BorderLayout.NORTH);
    
    // Create cards container with FlowLayout
    JPanel cardsContainer = new JPanel();
    cardsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
    cardsContainer.setBackground(new Color(244, 247, 247));
    
    // Wrap in scroll pane
    JScrollPane scrollPane = new JScrollPane(cardsContainer);
    scrollPane.setBorder(new EmptyBorder(0, 20, 20, 20));
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
    // Load appointments from database
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
        conn = jdbcConnection.getConnection();
        String sql = "SELECT id, pet_name, client_name, pet_species, pet_breed FROM appointments ORDER BY id DESC";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String petName = capitalizeFirstLetter(rs.getString("pet_name"));
            String ownerName = capitalizeFirstLetter(rs.getString("client_name"));
            String species = capitalizeFirstLetter(rs.getString("pet_species"));
            String breed = capitalizeFirstLetter(rs.getString("pet_breed"));
            
            PetCard card = new PetCard(id, petName, ownerName, species, breed);
            
            // Add double-click listener to open invoice
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        Invoice invoice = new Invoice(card.getAppointmentId());
                        invoice.setVisible(true);
                    }
                }
            });
            
            cardsContainer.add(card);
        }
        
        // If no records found
        if (cardsContainer.getComponentCount() == 0) {
            JLabel noRecordsLabel = new JLabel("No pet records found. Add an appointment to see cards here.");
            noRecordsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            noRecordsLabel.setForeground(Color.GRAY);
            cardsContainer.add(noRecordsLabel);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Error loading pet records: " + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE);
    } finally {
        jdbcConnection.closeConnection(conn, ps, rs);
    }
    
    // Add search functionality
    searchField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String searchText = searchField.getText().toLowerCase().trim();
            
            for (Component comp : cardsContainer.getComponents()) {
                if (comp instanceof PetCard) {
                    PetCard card = (PetCard) comp;
                    boolean matches = card.getPetName().toLowerCase().contains(searchText) ||
                                     card.getOwnerName().toLowerCase().contains(searchText);
                    comp.setVisible(matches);
                }
            }
            
            cardsContainer.revalidate();
            cardsContainer.repaint();
        }
    });
    
    recordsPanel.add(scrollPane, BorderLayout.CENTER);
    
    recordsPanel.revalidate();
    recordsPanel.repaint();
}

// ========================================
// MODIFY THESE EXISTING METHODS
// ========================================

// In recordsButtonActionPerformed method, change to:

    
     private void setupNavigationButtons() {
    // Remove background colors and set up borders only
    appointmentsButton.setContentAreaFilled(false);
    appointmentsButton.setOpaque(false);
    appointmentsButton.setFocusPainted(false);
    appointmentsButton.setBorderPainted(true);
    
    recordsButton.setContentAreaFilled(false);
    recordsButton.setOpaque(false);
    recordsButton.setFocusPainted(false);
    recordsButton.setBorderPainted(true);
    
    settingsButton.setContentAreaFilled(false);
    settingsButton.setOpaque(false);
    settingsButton.setFocusPainted(false);
    settingsButton.setBorderPainted(true);
    
    // Set initial borders (transparent)
    appointmentsButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3));
    recordsButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3));
    settingsButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3));
}

    private void setupServiceButtons() {
    // Set white backgrounds for all service buttons
    boardingButton.setBackground(Color.WHITE);
    petwalkingButton.setBackground(Color.WHITE);
    miscellaneousButton.setBackground(Color.WHITE);
    groomingButton.setBackground(Color.WHITE);
    daycareButton.setBackground(Color.WHITE);
    trainingButton.setBackground(Color.WHITE);
    
    // Make sure they're opaque
    boardingButton.setOpaque(true);
    petwalkingButton.setOpaque(true);
    miscellaneousButton.setOpaque(true);
    groomingButton.setOpaque(true);
    daycareButton.setOpaque(true);
    trainingButton.setOpaque(true);
    
    // Set initial borders
    boardingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    petwalkingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    miscellaneousButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    groomingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    daycareButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    trainingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
}
    
    private void adjustRecordsTableColumnWidths() {
    // Get the column model
    TableColumnModel columnModel = recordsTable.getColumnModel();
    
    columnModel.getColumn(0).setPreferredWidth(100);  // Owner
    columnModel.getColumn(1).setPreferredWidth(110);  // Address
    columnModel.getColumn(2).setPreferredWidth(125);  // Email
    columnModel.getColumn(3).setPreferredWidth(100);  // Number
    columnModel.getColumn(4).setPreferredWidth(90);   // Pet Name
    columnModel.getColumn(5).setPreferredWidth(75);   // Species
    columnModel.getColumn(6).setPreferredWidth(90);   // Breed
    columnModel.getColumn(7).setPreferredWidth(165);  // Services
    columnModel.getColumn(8).setPreferredWidth(90);   // Date
    columnModel.getColumn(9).setPreferredWidth(75);   // Assistant
    columnModel.getColumn(10).setPreferredWidth(85);  // Total Bill
    
    // Use AUTO_RESIZE_SUBSEQUENT_COLUMNS to fill remaining space
    recordsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    
}
    
    private void uncheckAllServices() {
    for (JCheckBox checkbox : serviceCheckboxes) {
        checkbox.setSelected(false);
    }
}
    
    public class Theme {
    public static final Color PRIMARY = new Color(0,128,129);
    public static final Color PRIMARY_DARK = new Color(0,109,111);
    public static final Color MINT = new Color(111,207,199);
    public static final Color BACKGROUND = new Color(244,247,247);
    public static final Color TEXT = new Color(46,46,46);
}

    private void highlightButtonBorder(javax.swing.JButton targetButton) {
    // Reset all buttons to transparent borders
    appointmentsButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3));
    recordsButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3));
    settingsButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3));
    
    targetButton.setBorder(javax.swing.BorderFactory.createLineBorder(
        new java.awt.Color(7, 122, 125), 3));
}
    
      private void highlightServiceButtonBorder(javax.swing.JButton targetButton) {
    // Reset all service buttons to default border, KEEP WHITE BACKGROUND
    boardingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    boardingButton.setBackground(Color.WHITE);
    
    petwalkingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    petwalkingButton.setBackground(Color.WHITE);
    
    miscellaneousButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    miscellaneousButton.setBackground(Color.WHITE);
    
    groomingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    groomingButton.setBackground(Color.WHITE);
    
    daycareButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    daycareButton.setBackground(Color.WHITE);
    
    trainingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    trainingButton.setBackground(Color.WHITE);
    
    // Highlight the target button with teal border, KEEP WHITE BACKGROUND
    targetButton.setBorder(javax.swing.BorderFactory.createLineBorder(
        new java.awt.Color(7, 122, 125), 2));
    targetButton.setBackground(Color.WHITE);
}
    
    private void disableRecordsTableEditing() {
      recordsTable.setDefaultEditor(Object.class, null);
    }

    private void addRecordsTableDoubleClick() {
     recordsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = recordsTable.getSelectedRow();
                    if (row == -1) return;
                    row = recordsTable.convertRowIndexToModel(row);
                    String id = recordsTable.getModel().getValueAt(row, 0).toString();
                    loadAppointmentById(id);
                    cards.show(jPanel10, "appointmentsPanel");
                }
            }
        });
    }
   
    private void openAppointmentFromRecords() {
    int row = recordsTable.getSelectedRow();
        if (row == -1) return;
        String Id = recordsTable.getValueAt(row, 0).toString();
        String petName = recordsTable.getValueAt(row, 1).toString();
        String ownerName = recordsTable.getValueAt(row, 2).toString();
        String service = recordsTable.getValueAt(row, 3).toString();
        String date = recordsTable.getValueAt(row, 4).toString();
        cards.show(jPanel10, "appointmentsPanel");
        txtAppointmentId.setText(Id);
        petNameTextfield.setText(petName);
        classNameTextfield.setText(ownerName);
        servicebutton.setText(service);
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            jDateChooser1.setDate(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
 * Capitalize the first letter of each word in a string
 */
private String capitalizeFirstLetter(String text) {
    if (text == null || text.trim().isEmpty()) {
        return text;
    }
    
    String[] words = text.trim().split("\\s+");
    StringBuilder capitalized = new StringBuilder();
    
    for (String word : words) {
        if (word.length() > 0) {
            capitalized.append(Character.toUpperCase(word.charAt(0)))
                       .append(word.substring(1).toLowerCase())
                       .append(" ");
        }
    }
    
    return capitalized.toString().trim();
} 

   private void loadAppointmentById(String id) {
     try (Connection con = jdbcConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM appointments WHERE id = ?");
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtAppointmentId.setText(rs.getString("id"));
                jDateChooser1.setDate(rs.getDate("date"));
                txtTime.setText(rs.getString("time"));
                cmbStatus.setSelectedItem(rs.getString("status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String selectedServices() {
    StringBuilder sb = new StringBuilder();
        if (boardingButton.isSelected()) sb.append("Boarding, ");
        if (petwalkingButton.isSelected()) sb.append("Petwalking, ");
        if (miscellaneousButton.isSelected()) sb.append("Miscellanous, ");
        if (groomingButton.isSelected()) sb.append("Grooming, ");
        if (daycareButton.isSelected()) sb.append("Daycare, ");
        if (trainingButton.isSelected()) sb.append("Training, ");
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
    
    
    private String formatServices(List<String> services) {
    return String.join("\n", services);
}

    
    private void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
    } 

    private JPanel currentPanel;

    private void switchPanel(JPanel target) {
        if (currentPanel == target) return;
        jPanel10.removeAll();
        jPanel10.add(target);
        jPanel10.revalidate();
        jPanel10.repaint();
        currentPanel = target;
    }

    private void setupRecordSearch() {
    DefaultTableModel model = (DefaultTableModel) recordsTable.getModel();
    recordsSorter = new TableRowSorter<>(model);
    recordsTable.setRowSorter(recordsSorter);

    jTextField1.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            String keyword = jTextField1.getText().trim();
            if (keyword.isEmpty()) {
                recordsSorter.setRowFilter(null);
            } else {
                recordsSorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
            }
        }
    });
}
    
  
private void loadFieldsFromDatabase(int id) {
    try (Connection con = jdbcConnection.getConnection()) {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM appointments WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            classNameTextfield.setText(capitalizeFirstLetter(rs.getString("client_name")));
    addressTextfield.setText(capitalizeFirstLetter(rs.getString("client_address")));
    emailTextfield.setText(rs.getString("client_email").toLowerCase());
            
            // *** KEY FIX: Preserve 11-digit contact with leading zero ***
            String contact = rs.getString("client_contact");
            if (contact != null && !contact.isEmpty()) {
                // Ensure exactly 11 digits
                contact = contact.replaceAll("\\D", "");
                if (contact.length() < 11) {
                    contact = String.format("%011d", Long.parseLong(contact));
                }
                if (contact.length() > 11) {
                    contact = contact.substring(0, 11);
                }
            } else {
                contact = "";
            }
            contactTextfield.setText(contact);
            
            petNameTextfield.setText(capitalizeFirstLetter(rs.getString("pet_name")));
    speciesTextField.setText(capitalizeFirstLetter(rs.getString("pet_species")));
    breedTextField.setText(capitalizeFirstLetter(rs.getString("pet_breed")));
            
            String servicesFromDB = rs.getString("selected_services");
            jTextArea1.setText(servicesFromDB);
            
            uncheckAllServices();
            String[] serviceLines = servicesFromDB.split("\n");
            for (String line : serviceLines) {
                String serviceName = line.split(" - ")[0].trim();
                for (JCheckBox checkbox : serviceCheckboxes) {
                    if (checkbox.getText().equals(serviceName)) {
                        checkbox.setSelected(true);
                        break;
                    }
                }
            }
            
            String totalBillStr = rs.getString("total_bill");
            try {
                double bill = Double.parseDouble(totalBillStr);
                totalBillTextfield.setText(String.format("₱%.2f", bill));
            } catch (NumberFormatException ex) {
                totalBillTextfield.setText("₱0.00");
            }
            
            try {
                Date date = new SimpleDateFormat("MM/dd/yyyy").parse(rs.getString("schedule"));
                jDateChooser1.setDate(date);
            } catch (ParseException ex) {
                jDateChooser1.setDate(null);
            }
            
            JComboBox.setSelectedItem(rs.getString("assigned_assistant"));
            fieldsLoaded = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading appointment: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}
   
    private void setupDateColumnRenderer() {
    TableColumn dateColumn =
        recordsTable.getColumnModel().getColumn(4);

    dateColumn.setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        protected void setValue(Object value) {
            if (value instanceof Date) {
                setText(new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss").format(value));
            } else {
                super.setValue(value);
            }
        }
    });
    }
    
    private void disableDateChooserTyping() {
    JTextField tf =
        (JTextField) jDateChooser1.getDateEditor()
            .getUiComponent();
    tf.setEditable(false);
    

    for (JButton btn : buttons) {
        btn.setBackground(new Color(240,240,240));
    }
    active.setBackground(new Color(100,149,237));

    recordsButton.addActionListener(e -> {
    switchPanel(recordsPanel);
    //highlightButton(recordsButton);
    });
    }
    
    
    private final Color COLOR_ACTIVE = new Color(0, 153, 153);   // Teal/Cyan accent
    private final Color COLOR_INACTIVE = new Color(51, 51, 51); // Dark grey background
    private final Color COLOR_TEXT = Color.WHITE;

   private void switchTab(javax.swing.JPanel targetPanel, javax.swing.JButton activeBtn) {
    appointmentsPanel.setVisible(false);
    recordsPanel.setVisible(false);
    settingsPanel.setVisible(false);
    targetPanel.setVisible(true);
    
    highlightButtonBorder(activeBtn);
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
            } else if ("Guest".equals(this.currentUserRole)) {
                System.out.println("Guest mode: Limited features.");
                jButton3.setEnabled(false);
                jButton4.setEnabled(false);
                jButton5.setEnabled(false);
            } else {
                System.out.println("Unknown role: " + this.currentUserRole);
            }
        } else {
            System.out.println("No role set for UI customization.");
        }
    }

class MultiLineCellRenderer extends JTextArea
        implements TableCellRenderer {

    public MultiLineCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {

        setText(value == null ? "" : value.toString());
        setBackground(isSelected
            ? table.getSelectionBackground()
            : table.getBackground());

        return this;
    }
}
    
    public void loadRecordsTable() {
    loadRecordsTable("");
}

     private void loadRecordsTable(String filter) {
    DefaultTableModel recordsModel = (DefaultTableModel) recordsTable.getModel();
    recordsModel.setRowCount(0);

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        conn = jdbcConnection.getConnection();
        if (conn == null) {
            System.err.println("Failed to connect to the database.");
            return;
        }

        String sql = "SELECT id, " +
                     "client_name AS Owner, " +
                     "client_address AS Address, " +
                     "client_email AS Email, " +
                     "client_contact AS Number, " +
                     "pet_name AS `Pet Name`, " +
                     "pet_species AS Species, " +
                     "pet_breed AS Breed, " +
                     "selected_services AS Services, " +
                     "schedule AS `Date`, " +
                     "assigned_assistant AS Assistant, " +
                     "total_bill AS `Total Bill` " +
                    //"'Pending' AS Status " +
                     "FROM appointments ORDER BY id ASC";

        if (filter != null && !filter.trim().isEmpty()) {
            sql += " WHERE client_name LIKE ? OR pet_name LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + filter + "%");
            pstmt.setString(2, "%" + filter + "%");
        } else {
            pstmt = conn.prepareStatement(sql);
        }

        rs = pstmt.executeQuery();

        while (rs.next()) {
            String dateStr = rs.getString("Date");
            double totalBill = rs.getDouble("Total Bill");
            String formattedBill = String.format("₱%.2f", totalBill);
            
            // *** KEY FIX: Use getString() for contact to preserve leading zeros ***
            String contact = rs.getString("Number");
            
            // Ensure contact is exactly 11 digits with leading zero if needed
            if (contact != null && !contact.isEmpty()) {
                // Remove any non-digit characters
                contact = contact.replaceAll("\\D", "");
                
                // Pad with leading zeros if less than 11 digits
                if (contact.length() < 11) {
                    contact = String.format("%011d", Long.parseLong(contact));
                }
                
                // Truncate if more than 11 digits
                if (contact.length() > 11) {
                    contact = contact.substring(0, 11);
                }
            } else {
                contact = "00000000000"; // Default if null
            }

            recordsModel.addRow(new Object[]{
            rs.getInt("id"),                                    // 0 – hidden ID
            capitalizeFirstLetter(rs.getString("Owner")),       // 1 – Client Name
            capitalizeFirstLetter(rs.getString("Address")),     // 2 – Address
            rs.getString("Email").toLowerCase(),                // 3 – Email (lowercase)
            contact,                                            // 4 – Contact
            capitalizeFirstLetter(rs.getString("Pet Name")),    // 5 – Pet Name
            capitalizeFirstLetter(rs.getString("Species")),     // 6 – Species
            capitalizeFirstLetter(rs.getString("Breed")),       // 7 – Breed
            rs.getString("Services"),                           // 8 – Services (no change)
            dateStr,                                            // 9 – Date
            capitalizeFirstLetter(rs.getString("Assistant")),   // 10 – Assistant
            formattedBill,                                      // 11 – Total Bill
        });
        }

       recordsTable.setModel(recordsModel);
recordsSorter = new TableRowSorter<>(recordsModel);
recordsTable.setRowSorter(recordsSorter);

// Reapply search filter if exists
String searchText = jTextField1.getText().trim();
if (searchText.isEmpty()) {
    recordsSorter.setRowFilter(null);
} else {
    recordsSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
}
        
        // Hide the ID column
        recordsTable.getColumnModel().getColumn(0).setMinWidth(0);
        recordsTable.getColumnModel().getColumn(0).setMaxWidth(0);
        recordsTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        recordsModel.fireTableDataChanged();
        recordsTable.revalidate();
        recordsTable.repaint();
//        jScrollPane2.revalidate();
//        jScrollPane2.repaint();
//        jScrollPane2.getViewport().revalidate();

        SwingUtilities.invokeLater(() -> {
            SwingUtilities.invokeLater(() -> {
                if (recordsTable.getRowCount() > 0) {
                    int lastRow = recordsTable.getRowCount() - 1;
                    recordsTable.setRowSelectionInterval(lastRow, lastRow);
                    Rectangle rect = recordsTable.getCellRect(lastRow, 0, true);
                    rect.y += rect.height * 2;
                    recordsTable.scrollRectToVisible(rect);
//                    JScrollBar vertical = jScrollPane2.getVerticalScrollBar();
//                    vertical.setValue(vertical.getMaximum());
                }
            });
        });

         updateAppointmentCount();
         
    } catch (SQLException e) {
        System.err.println("Error loading records table: " + e.getMessage());
        e.printStackTrace();
    } finally {
        jdbcConnection.closeConnection(conn, pstmt, rs);
    }
    
    
}

private void loadAppointmentForEditing(String Id) {
    try {
            Connection con = jdbcConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM appointments WHERE id = ?");
            ps.setString(1, Id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtAppointmentId.setText(rs.getString("id"));
                petNameTextfield.setText(rs.getString("pet_name"));
                classNameTextfield.setText(rs.getString("client_name"));
                servicebutton.setText(rs.getString("selected_services"));
                jDateChooser1.setDate(rs.getDate("schedule"));
                txtTime.setText(rs.getString("time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Add any other missing methods or the rest of your class here...
// (e.g., the rest of the generated code from initComponents, variables declaration, etc.)
    
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
        settingsPanel = new javax.swing.JPanel();
        aboutPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        aboutUsPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jTextArea3 = new javax.swing.JTextArea();
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
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox29 = new javax.swing.JCheckBox();
        jCheckBox30 = new javax.swing.JCheckBox();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        petWalkingServicesPanel = new javax.swing.JPanel();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        appointmentsPanel = new javax.swing.JPanel();
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
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        servicebutton = new javax.swing.JButton();
        JComboBox = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        totalBillTextfield = new javax.swing.JTextField();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        recordsTable = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        appointmentCount = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        recordsPanel = new javax.swing.JPanel();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("jMenu1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setPreferredSize(new java.awt.Dimension(1400, 820));
        setResizable(false);

        jPanel1.setEnabled(false);
        jPanel1.setMaximumSize(new java.awt.Dimension(1400, 820));
        jPanel1.setMinimumSize(new java.awt.Dimension(1400, 820));
        jPanel1.setPreferredSize(new java.awt.Dimension(1400, 820));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setMaximumSize(new java.awt.Dimension(2000, 1000));
        jPanel2.setMinimumSize(new java.awt.Dimension(2000, 1000));
        jPanel2.setPreferredSize(new java.awt.Dimension(1450, 73));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Green Modern Veterinary Clinic Logo.png"))); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(260, 53));
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        appointmentsButton.setBackground(new java.awt.Color(0, 0, 0));
        appointmentsButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        appointmentsButton.setForeground(new java.awt.Color(255, 255, 255));
        appointmentsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/appointments.png"))); // NOI18N
        appointmentsButton.setText("Appointments   ");
        appointmentsButton.setBorder(null);
        appointmentsButton.setSelected(true);
        appointmentsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                appointmentsButtonMouseEntered(evt);
            }
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
        jPanel2.add(appointmentsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, -1, -1));

        recordsButton.setBackground(new java.awt.Color(0, 0, 0));
        recordsButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        recordsButton.setForeground(new java.awt.Color(255, 255, 255));
        recordsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/records.png"))); // NOI18N
        recordsButton.setText("Records   ");
        recordsButton.setBorder(null);
        recordsButton.setSelected(true);
        recordsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                recordsButtonMouseEntered(evt);
            }
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
        jPanel2.add(recordsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 20, -1, -1));

        settingsButton.setBackground(new java.awt.Color(0, 0, 0));
        settingsButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        settingsButton.setForeground(new java.awt.Color(255, 255, 255));
        settingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N
        settingsButton.setText("Settings   ");
        settingsButton.setBorder(null);
        settingsButton.setSelected(true);
        settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsButtonMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                settingsButtonMousePressed(evt);
            }
        });
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        jPanel2.add(settingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 20, -1, -1));

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel10.setMaximumSize(new java.awt.Dimension(1400, 820));
        jPanel10.setMinimumSize(new java.awt.Dimension(1400, 820));
        jPanel10.setPreferredSize(new java.awt.Dimension(1400, 820));
        jPanel10.setLayout(new java.awt.CardLayout());

        settingsPanel.setBackground(new java.awt.Color(245, 245, 245));
        settingsPanel.setPreferredSize(new java.awt.Dimension(1400, 969));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jPanel24.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1371, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 947, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout aboutPanelLayout = new javax.swing.GroupLayout(aboutPanel);
        aboutPanel.setLayout(aboutPanelLayout);
        aboutPanelLayout.setHorizontalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        aboutPanelLayout.setVerticalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setPreferredSize(new java.awt.Dimension(130, 642));

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("About Us");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 122, 125), 4));
        jButton7.setPreferredSize(new java.awt.Dimension(130, 100));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Log out");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 122, 125), 4));
        jButton1.setPreferredSize(new java.awt.Dimension(130, 100));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        aboutUsPanel.setBackground(new java.awt.Color(244, 247, 247));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setText("MISSION");

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(244, 247, 247));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText(" Our vision is to be the most reliable and innovative digital pet care solution, to deliver compassionate, \n convenient, and high-quality pet services that ensure the health, happiness, and well-being of every pet in our care. \n\n We strive to create a trusted platform that supports pet owners with everything from scheduling appointments to tracking\n medical records empowering pet owners and professionals through smart tools, seamless experiences, and a shared love\n for animals to deliver compassionate,  convenient, and high-quality pet services that ensure the health, happiness, and\n well-being of every pet in our care. We strive to create a trusted platform that supports pet owners with everything from\n scheduling appointments to tracking medical records.\n\n");
        jTextArea2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setText("VISION");

        jTextArea3.setBackground(new java.awt.Color(244, 247, 247));
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextArea3.setRows(5);
        jTextArea3.setText(" At iPETCARE, our mission is to deliver compassionate,  convenient, and  high-quality\n pet services that ensure the health, happiness, and well-being of every pet in our care. \n\n We strive to create a trusted platform that supports pet owners with everything from\n scheduling appointments to tracking medical records.\n");
        jTextArea3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));

        javax.swing.GroupLayout aboutUsPanelLayout = new javax.swing.GroupLayout(aboutUsPanel);
        aboutUsPanel.setLayout(aboutUsPanelLayout);
        aboutUsPanelLayout.setHorizontalGroup(
            aboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutUsPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(aboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextArea3, javax.swing.GroupLayout.PREFERRED_SIZE, 972, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(aboutUsPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        aboutUsPanelLayout.setVerticalGroup(
            aboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutUsPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextArea3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jTextArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboutUsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(aboutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addComponent(aboutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                    .addComponent(aboutUsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel10.add(settingsPanel, "card6");

        servicesPanel.setBackground(new java.awt.Color(244, 247, 247));
        servicesPanel.setPreferredSize(new java.awt.Dimension(1400, 450));
        servicesPanel.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(244, 247, 247));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel8.setEnabled(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(242, 244, 245));
        jPanel12.setPreferredSize(new java.awt.Dimension(1400, 82));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SELECT SERVICE TYPES");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel14.setBackground(new java.awt.Color(244, 247, 247));
        jPanel14.setLayout(new java.awt.GridLayout(2, 0, 5, 0));

        jPanel21.setBackground(new java.awt.Color(242, 244, 245));
        jPanel21.setPreferredSize(new java.awt.Dimension(1400, 75));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        groomingButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        groomingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/groomss.png"))); // NOI18N
        groomingButton.setText("Grooming Services");
        groomingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        groomingButton.setFocusable(false);
        groomingButton.setPreferredSize(new java.awt.Dimension(180, 75));
        groomingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groomingButtonActionPerformed(evt);
            }
        });
        jPanel21.add(groomingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        boardingButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        boardingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boarding.png"))); // NOI18N
        boardingButton.setText("Boarding Services");
        boardingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        boardingButton.setOpaque(true);
        boardingButton.setPreferredSize(new java.awt.Dimension(180, 75));
        boardingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boardingButtonActionPerformed(evt);
            }
        });
        jPanel21.add(boardingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

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
        jPanel21.add(petwalkingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, -1, -1));

        daycareButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        daycareButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grooming.png"))); // NOI18N
        daycareButton.setText(" Daycare Services");
        daycareButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        daycareButton.setPreferredSize(new java.awt.Dimension(180, 75));
        daycareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daycareButtonActionPerformed(evt);
            }
        });
        jPanel21.add(daycareButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, -1, -1));

        miscellaneousButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        miscellaneousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/daycaree.png"))); // NOI18N
        miscellaneousButton.setText("Miscellaneous Services");
        miscellaneousButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        miscellaneousButton.setPreferredSize(new java.awt.Dimension(180, 75));
        miscellaneousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miscellaneousButtonActionPerformed(evt);
            }
        });
        jPanel21.add(miscellaneousButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, 210, -1));

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
        jPanel21.add(trainingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 210, -1));

        addButton.setBackground(new java.awt.Color(7, 122, 125));
        addButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add");
        addButton.setPreferredSize(new java.awt.Dimension(100, 25));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel21.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 110, 30));

        jPanel14.add(jPanel21);

        jPanel15.setBackground(new java.awt.Color(102, 102, 102));
        jPanel15.setPreferredSize(new java.awt.Dimension(1400, 334));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel19.setBackground(new java.awt.Color(242, 244, 245));
        jPanel19.setPreferredSize(new java.awt.Dimension(1400, 334));
        jPanel19.setLayout(new java.awt.GridLayout(2, 0));

        jPanel23.setBackground(new java.awt.Color(242, 244, 245));
        jPanel23.setPreferredSize(new java.awt.Dimension(1400, 167));

        jPanel18.setLayout(new java.awt.CardLayout());

        dayCareServicesPanel.setBackground(new java.awt.Color(255, 255, 255));
        dayCareServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dayCareServicesPanel.setFocusable(false);
        dayCareServicesPanel.setPreferredSize(new java.awt.Dimension(580, 170));
        dayCareServicesPanel.setLayout(new java.awt.GridBagLayout());

        jCheckBox11.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox11.setText("Pet Sitting (In-home care)");
        jCheckBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox11ActionPerformed(evt);
            }
        });
        dayCareServicesPanel.add(jCheckBox11, new java.awt.GridBagConstraints());

        jCheckBox12.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox12.setText("Feeding & Medication Management");
        dayCareServicesPanel.add(jCheckBox12, new java.awt.GridBagConstraints());

        jPanel18.add(dayCareServicesPanel, "card4");

        trainingServicesPanel.setBackground(new java.awt.Color(255, 255, 255));
        trainingServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        trainingServicesPanel.setPreferredSize(new java.awt.Dimension(580, 170));
        trainingServicesPanel.setLayout(new java.awt.GridBagLayout());

        jCheckBox13.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox13.setText("Basic Obedience Training");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        trainingServicesPanel.add(jCheckBox13, gridBagConstraints);

        jCheckBox14.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox14.setText("Potty Training");
        jCheckBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox14ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 59;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        trainingServicesPanel.add(jCheckBox14, gridBagConstraints);

        jCheckBox15.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox15.setText("Behavioral Correction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        trainingServicesPanel.add(jCheckBox15, gridBagConstraints);

        jCheckBox16.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox16.setText("Puppy Socialization");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        trainingServicesPanel.add(jCheckBox16, gridBagConstraints);

        jCheckBox17.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox17.setText("Trick Training");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 40);
        trainingServicesPanel.add(jCheckBox17, gridBagConstraints);

        jPanel18.add(trainingServicesPanel, "card5");

        miscellaneousServicesPanel.setBackground(new java.awt.Color(255, 255, 255));
        miscellaneousServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        miscellaneousServicesPanel.setFocusable(false);
        miscellaneousServicesPanel.setPreferredSize(new java.awt.Dimension(580, 170));
        miscellaneousServicesPanel.setLayout(new java.awt.GridBagLayout());

        jCheckBox18.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox18.setText("Pet Photography Session");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        miscellaneousServicesPanel.add(jCheckBox18, gridBagConstraints);

        jCheckBox19.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox19.setText("Pet Birthday Celebration");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        miscellaneousServicesPanel.add(jCheckBox19, gridBagConstraints);

        jCheckBox20.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox20.setText("Pet Massage or Spa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        miscellaneousServicesPanel.add(jCheckBox20, gridBagConstraints);

        jCheckBox21.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        jCheckBox22.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox22.setText("Pet Nutrition Consultation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 22);
        miscellaneousServicesPanel.add(jCheckBox22, gridBagConstraints);

        jPanel18.add(miscellaneousServicesPanel, "card6");

        boardingServicesPanel.setBackground(new java.awt.Color(244, 247, 247));
        boardingServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
        boardingServicesPanel.setFocusable(false);
        boardingServicesPanel.setPreferredSize(new java.awt.Dimension(580, 170));
        boardingServicesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 60));

        jCheckBox23.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox23.setText("Full-Day Boarding");
        boardingServicesPanel.add(jCheckBox23);

        jCheckBox24.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox24.setText("Half-Day Boarding");
        boardingServicesPanel.add(jCheckBox24);

        jCheckBox25.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox25.setText("Overnight Stay");
        boardingServicesPanel.add(jCheckBox25);

        jPanel18.add(boardingServicesPanel, "card7");

        groomingServicesPanel.setBackground(new java.awt.Color(255, 255, 255));
        groomingServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        groomingServicesPanel.setPreferredSize(new java.awt.Dimension(580, 170));
        groomingServicesPanel.setLayout(new java.awt.GridBagLayout());

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox1.setText("Full Grooming");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        groomingServicesPanel.add(jCheckBox1, gridBagConstraints);

        jCheckBox2.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox2.setText("Bath Only");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 30, 0, 0);
        groomingServicesPanel.add(jCheckBox2, gridBagConstraints);

        jCheckBox3.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox3.setText("Nail Clipping");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        groomingServicesPanel.add(jCheckBox3, gridBagConstraints);

        jCheckBox26.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox26.setText("Bath Only");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 30, 0, 0);
        groomingServicesPanel.add(jCheckBox26, gridBagConstraints);

        jCheckBox27.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox27.setText("Nail Clipping");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        groomingServicesPanel.add(jCheckBox27, gridBagConstraints);

        jCheckBox28.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox28.setText("De-shedding Treatment");
        jCheckBox28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox28ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 39, 0, 0);
        groomingServicesPanel.add(jCheckBox28, gridBagConstraints);

        jCheckBox29.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox29.setText("Flea/Tick Treatment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        groomingServicesPanel.add(jCheckBox29, gridBagConstraints);

        jCheckBox30.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox30.setText("Teeth Brushing");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 39, 0, 0);
        groomingServicesPanel.add(jCheckBox30, gridBagConstraints);

        jCheckBox31.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox31.setText("Ear Cleaning");
        jCheckBox31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox31ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 0, 0, 0);
        groomingServicesPanel.add(jCheckBox31, gridBagConstraints);

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
        jCheckBox5.setText("Teeth Brushing");
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

        petWalkingServicesPanel.setBackground(new java.awt.Color(255, 255, 255));
        petWalkingServicesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        petWalkingServicesPanel.setFocusable(false);
        petWalkingServicesPanel.setPreferredSize(new java.awt.Dimension(580, 170));
        petWalkingServicesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 60));

        jCheckBox8.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox8.setText("30-Minute Walk");
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });
        petWalkingServicesPanel.add(jCheckBox8);

        jCheckBox9.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox9.setText("1-Hour Walk");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });
        petWalkingServicesPanel.add(jCheckBox9);

        jCheckBox10.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox10.setText("Daily Walk Package");
        petWalkingServicesPanel.add(jCheckBox10);

        jPanel18.add(petWalkingServicesPanel, "card3");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1398, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(0, 400, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 399, Short.MAX_VALUE)))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel19.add(jPanel23);

        jPanel15.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel15);

        jPanel8.add(jPanel14, java.awt.BorderLayout.CENTER);

        servicesPanel.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel10.add(servicesPanel, "card5");

        appointmentsPanel.setBackground(new java.awt.Color(255, 255, 255));
        appointmentsPanel.setFocusable(false);
        appointmentsPanel.setMaximumSize(new java.awt.Dimension(1400, 850));
        appointmentsPanel.setMinimumSize(new java.awt.Dimension(1400, 850));
        appointmentsPanel.setPreferredSize(new java.awt.Dimension(1400, 850));
        appointmentsPanel.setRequestFocusEnabled(false);

        jButton3.setBackground(new java.awt.Color(211, 47, 47));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.setToolTipText("");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.setIconTextGap(0);
        jButton3.setMargin(new java.awt.Insets(5, 0, 5, 0));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(249, 168, 37));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_1.png"))); // NOI18N
        jButton4.setText("Update");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setIconTextGap(0);
        jButton4.setMargin(new java.awt.Insets(2, 0, 3, 0));
        jButton4.setVerifyInputWhenFocusTarget(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(84, 110, 122));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2.png"))); // NOI18N
        jButton5.setText("Edit");
        jButton5.setToolTipText("");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.setIconTextGap(0);
        jButton5.setMargin(new java.awt.Insets(2, 10, 3, 14));
        jButton5.setPreferredSize(new java.awt.Dimension(83, 58));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(63, 81, 181));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_2.png"))); // NOI18N
        jButton6.setText("Print Receipt");
        jButton6.setAlignmentY(0.1F);
        jButton6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.setIconTextGap(0);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(244, 247, 247));
        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 2, true));
        jPanel13.setAutoscrolls(true);
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(244, 247, 247));
        jPanel7.setToolTipText("");
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(46, 46, 46));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(46, 46, 46));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PET INFORMATION");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 50));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setText("Pet Name:");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 80, 20));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setText("Species:");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 70, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel10.setText("Breed:");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 60, -1));

        petNameTextfield.setBackground(new java.awt.Color(244, 247, 247));
        petNameTextfield.setForeground(new java.awt.Color(51, 51, 51));
        petNameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petNameTextfieldActionPerformed(evt);
            }
        });
        jPanel7.add(petNameTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 170, 30));

        speciesTextField.setBackground(new java.awt.Color(244, 247, 247));
        speciesTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speciesTextFieldActionPerformed(evt);
            }
        });
        jPanel7.add(speciesTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 170, 30));

        breedTextField.setBackground(new java.awt.Color(244, 247, 247));
        breedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breedTextFieldActionPerformed(evt);
            }
        });
        jPanel7.add(breedTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 170, 30));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel15.setText("Schedule:");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, 20));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel16.setText("Assign Assistant:");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, 20));

        servicebutton.setBackground(new java.awt.Color(7, 122, 125));
        servicebutton.setForeground(new java.awt.Color(255, 255, 255));
        servicebutton.setText("Select Service");
        servicebutton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        servicebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicebuttonActionPerformed(evt);
            }
        });
        jPanel7.add(servicebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 170, 30));

        JComboBox.setBackground(new java.awt.Color(244, 247, 247));
        JComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBoxActionPerformed(evt);
            }
        });
        jPanel7.add(JComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 170, 30));
        jPanel7.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 170, 30));

        jPanel13.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 530, 340));

        jScrollPane3.setViewportView(jPanel13);

        jPanel5.setBackground(new java.awt.Color(244, 247, 247));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 2, true));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(244, 247, 247));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("TOTAL BILL:");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 100, 30));

        totalBillTextfield.setEditable(false);
        totalBillTextfield.setBackground(new java.awt.Color(230, 230, 230));
        totalBillTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalBillTextfieldActionPerformed(evt);
            }
        });
        jPanel6.add(totalBillTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 140, 30));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(244, 247, 247));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)));
        jScrollPane4.setViewportView(jTextArea1);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 420, 230));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("SELECTED SERVICES");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 440, -1));

        addAppointmentButton.setBackground(new java.awt.Color(7, 122, 125));
        addAppointmentButton.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        addAppointmentButton.setForeground(new java.awt.Color(255, 255, 255));
        addAppointmentButton.setText("Add Appointment");
        addAppointmentButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAppointmentButtonActionPerformed(evt);
            }
        });
        jPanel6.add(addAppointmentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 140, 30));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 440, 330));

        jPanel9.setBackground(new java.awt.Color(244, 247, 247));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 2, true));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(244, 247, 247));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(46, 46, 46));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CLIENT INFORMATION");
        jPanel16.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 50));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setText("Client Name:");
        jPanel16.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 100, 30));

        classNameTextfield.setBackground(new java.awt.Color(244, 247, 247));
        classNameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classNameTextfieldActionPerformed(evt);
            }
        });
        jPanel16.add(classNameTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 140, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Address:");
        jPanel16.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 70, 30));

        addressTextfield.setBackground(new java.awt.Color(244, 247, 247));
        jPanel16.add(addressTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 140, 30));

        emailTextfield.setBackground(new java.awt.Color(244, 247, 247));
        emailTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextfieldActionPerformed(evt);
            }
        });
        jPanel16.add(emailTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 140, 30));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Email:");
        jPanel16.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 40, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Contact:");
        jPanel16.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 50, -1));

        contactTextfield.setBackground(new java.awt.Color(244, 247, 247));
        contactTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactTextfieldActionPerformed(evt);
            }
        });
        jPanel16.add(contactTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 140, 30));

        jPanel9.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 330));

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setMaximumSize(null);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane2.setName(""); // NOI18N
        jScrollPane2.setPreferredSize(null);
        jScrollPane2.setViewportView(recordsTable);
        jScrollPane2.setMaximumSize(null);

        recordsTable.setAutoCreateRowSorter(true);
        recordsTable.setBackground(new java.awt.Color(245, 245, 245));
        recordsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Owner", "Address", "Email", "Number", "Pet Name", "Species", "Breed", "Services", "Date", "Assistant", "Total Bill"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        recordsTable.setToolTipText("");
        recordsTable.setAlignmentX(2.0F);
        recordsTable.setAlignmentY(2.0F);
        recordsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        recordsTable.setFillsViewportHeight(true);
        recordsTable.setInheritsPopupMenu(true);
        recordsTable.setName(""); // NOI18N
        recordsTable.setShowHorizontalLines(true);
        recordsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recordsTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                recordsTableMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(recordsTable);

        // Force proper scrolling behavior
        jScrollPane2.setVerticalScrollBarPolicy(
            javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
        );
        jScrollPane2.setHorizontalScrollBarPolicy(
            javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        // Ensure table fills viewport
        recordsTable.setFillsViewportHeight(true);

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setText("Total Records:");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/recordsF.png"))); // NOI18N

        appointmentCount.setBackground(new java.awt.Color(255, 255, 255));
        appointmentCount.setForeground(new java.awt.Color(255, 255, 255));
        appointmentCount.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));

        jTextField1.setBackground(new java.awt.Color(244, 247, 247));
        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));
        jTextField1.setPreferredSize(new java.awt.Dimension(64, 24));
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField1MouseEntered(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SEARCH 2.png"))); // NOI18N
        jLabel24.setIconTextGap(0);
        jLabel24.setPreferredSize(new java.awt.Dimension(35, 40));

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel25.setText("Search:");

        javax.swing.GroupLayout appointmentsPanelLayout = new javax.swing.GroupLayout(appointmentsPanel);
        appointmentsPanel.setLayout(appointmentsPanelLayout);
        appointmentsPanelLayout.setHorizontalGroup(
            appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, appointmentsPanelLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(135, 135, 135)
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(appointmentCount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(appointmentsPanelLayout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appointmentsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(380, 380, 380))
        );
        appointmentsPanelLayout.setVerticalGroup(
            appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appointmentsPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(appointmentCount, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(appointmentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jPanel10.add(appointmentsPanel, "card3");

        recordsPanel.setBackground(new java.awt.Color(244, 247, 247));
        recordsPanel.setMaximumSize(new java.awt.Dimension(1400, 700));
        recordsPanel.setPreferredSize(new java.awt.Dimension(1450, 700));

        javax.swing.GroupLayout recordsPanelLayout = new javax.swing.GroupLayout(recordsPanel);
        recordsPanel.setLayout(recordsPanelLayout);
        recordsPanelLayout.setHorizontalGroup(
            recordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1400, Short.MAX_VALUE)
        );
        recordsPanelLayout.setVerticalGroup(
            recordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 787, Short.MAX_VALUE)
        );

        jPanel10.add(recordsPanel, "card4");

        jPanel1.add(jPanel10, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void appointmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentsButtonActionPerformed
        switchPanel(appointmentsPanel);
        highlightButtonBorder(appointmentsButton);
    }//GEN-LAST:event_appointmentsButtonActionPerformed
    
    private void recordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordsButtonActionPerformed
                                     
    switchPanel(recordsPanel);
    highlightButtonBorder(recordsButton);
    loadRecordsAsCards();  // Changed from loadRecordsTable()

    }//GEN-LAST:event_recordsButtonActionPerformed

    private void appointmentsButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentsButtonMousePressed
// REMOVED - background changes not needed//        
    }//GEN-LAST:event_appointmentsButtonMousePressed

    private void recordsButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recordsButtonMousePressed
// REMOVED - background changes not needed       
    }//GEN-LAST:event_recordsButtonMousePressed

    private void appointmentsButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentsButtonMouseReleased
      // REMOVED - background changes not needed
    }//GEN-LAST:event_appointmentsButtonMouseReleased

    private void recordsButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recordsButtonMouseReleased
   // REMOVED - background changes not needed
    }//GEN-LAST:event_recordsButtonMouseReleased

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        switchPanel(settingsPanel);
        highlightButtonBorder(settingsButton);
    }//GEN-LAST:event_settingsButtonActionPerformed
    
    private void appendIfSelected(StringBuilder builder, JCheckBox checkBox) {
        if (checkBox.isSelected()) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(checkBox.getText());
        }
    }    
    private void searchClientsByName() {
    String input = jTextField1.getText().trim();

    try {
        
        Connection conn = DriverManager.getConnection("jdbc:mysql:// 192.168.60.117:3306/petcareservices?allowPublicKeyRetrieval=true&useSSL=false", "root", "allen556");

       
        String sql = "SELECT clients_name FROM appointments WHERE client_name=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + input + "%");

        ResultSet rs = stmt.executeQuery();
       
//        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//        model.setRowCount(0); 

        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = rs.getObject(i + 1);
            }
//            model.addRow(row);
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading client data:\n" + ex.getMessage());
    }
}
    
    private void groomingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groomingButtonActionPerformed
        
        groomingServicesPanel.setVisible(true);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(false);
       
        groomingServicesPanel.revalidate();
        groomingServicesPanel.repaint();
        
          highlightServiceButtonBorder(groomingButton);
    }//GEN-LAST:event_groomingButtonActionPerformed

    private void boardingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boardingButtonActionPerformed
       
        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(true);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(false);
      
        boardingServicesPanel.revalidate();
        boardingServicesPanel.repaint();
        
         highlightServiceButtonBorder(boardingButton);
    }//GEN-LAST:event_boardingButtonActionPerformed

    private void petwalkingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petwalkingButtonActionPerformed
       
        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(true);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(false);
     
        petWalkingServicesPanel.revalidate();
        petWalkingServicesPanel.repaint();
        
        highlightServiceButtonBorder(petwalkingButton);
    }//GEN-LAST:event_petwalkingButtonActionPerformed

    private void trainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainingButtonActionPerformed
        
        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(true);
        miscellaneousServicesPanel.setVisible(false);
        trainingServicesPanel.repaint();
        trainingServicesPanel.revalidate();
        
        highlightServiceButtonBorder(trainingButton);
        
    }//GEN-LAST:event_trainingButtonActionPerformed

    private void daycareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daycareButtonActionPerformed
      
        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(true);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(false);
        dayCareServicesPanel.revalidate();
        dayCareServicesPanel.repaint();
        
        highlightServiceButtonBorder(daycareButton);
    }//GEN-LAST:event_daycareButtonActionPerformed

    private void miscellaneousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miscellaneousButtonActionPerformed

        groomingServicesPanel.setVisible(false);
        boardingServicesPanel.setVisible(false);
        petWalkingServicesPanel.setVisible(false);
        dayCareServicesPanel.setVisible(false);
        trainingServicesPanel.setVisible(false);
        miscellaneousServicesPanel.setVisible(true);
        miscellaneousServicesPanel.revalidate();
        miscellaneousServicesPanel.repaint();
        
        highlightServiceButtonBorder(miscellaneousButton);
    }//GEN-LAST:event_miscellaneousButtonActionPerformed

    private final Map<String, Double> servicePrices = createServicePricesMap();

    private Map<String, Double> createServicePricesMap() {
    Map<String, Double> prices = new HashMap<>();
    
    prices.put("Full-Day Boarding", 100.00); 
    prices.put("Half-Day Boarding", 50.00);
    prices.put("Overnight Stay", 200.00); 
    
    prices.put("Pet Photography Session", 100.00); 
    prices.put("Pet Massage or Spa", 150.00); 
    prices.put("Pet Nutrition Consultation", 50.00); 
    prices.put("Pet Birthday Celebration", 1000.00); 
    prices.put("Pet Taxi (Pickup & Drop-off service)", 100.00); 
    
    prices.put("Full Grooming", 65.00); 
    prices.put("Nail Clipping", 15.00); 
    prices.put("Teeth Brushing", 10.00); 
    prices.put("Flea/Tick Treatment", 25.00);
    prices.put("Bath Only", 35.00);
    prices.put("Ear Cleaning", 12.00);
    prices.put("De-shedding Treatment", 40.00);
    
    prices.put("Pet Sitting (In-home care)", 100.00); 
    prices.put("Feeding & Medication Management", 65.00); 

    prices.put("30-Minute Walk", 20.00); 
    prices.put("1-Hour Walk", 35.00); 
    prices.put("Daily Walk Package", 45.00); 
    
    prices.put("Basic Obedience Training", 100.00); 
    prices.put("Behavioral Correction", 100.00); 
    prices.put("Trick Training", 100.00); 
    prices.put("Potty Training", 100.00); 
    prices.put("Puppy Socialization", 65.00); 

    return prices;
}
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
                                         
    List<String> selectedServices = new ArrayList<>();
    double totalBill = 0.0;

    // Collect selected services and calculate total
    for (JCheckBox checkbox : serviceCheckboxes) {
        if (checkbox.isSelected()) {
            String serviceName = checkbox.getText();
            selectedServices.add(serviceName);
            
            Double price = servicePrices.get(serviceName);
            if (price != null) {
                totalBill += price;
            }
        }
    }

    if (selectedServices.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Please select at least one service before adding.", 
            "No Services Selected", 
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // Format services with prices and peso symbol
    List<String> detailedServiceList = new ArrayList<>();
    for (String serviceName : selectedServices) {
        Double price = servicePrices.get(serviceName);
        if (price != null) {
            detailedServiceList.add(String.format("%s - ₱%.2f", serviceName, price));
        } else {
            detailedServiceList.add(serviceName + " - Price N/A");
        }
    }

    String verticalServices = String.join("\n", detailedServiceList);
    
    // **IMPROVED: Check if services actually changed (compare line by line)**
    if (selectedAppointmentId != -1 && !originalServices.isEmpty()) {  // Editing mode
        // Extract service names from original (without prices) for comparison
        Set<String> originalServiceNames = new HashSet<>();
        for (String line : originalServices.split("\n")) {
            String serviceName = line.split(" - ")[0].trim();
            originalServiceNames.add(serviceName);
        }
        
        // Compare with newly selected services
        Set<String> newServiceNames = new HashSet<>(selectedServices);
        
        if (originalServiceNames.equals(newServiceNames)) {
            // No changes made - same services selected
            JOptionPane.showMessageDialog(this, 
                "No services have been changed.", 
                "No Changes", 
                JOptionPane.INFORMATION_MESSAGE);
            switchPanel(appointmentsPanel);
            highlightButtonBorder(appointmentsButton);
            return;
        } else {
            // Services were changed
            servicesChanged = true;
            jTextArea1.setText(verticalServices);
            String formattedTotal = String.format("₱%.2f", totalBill);
            totalBillTextfield.setText(formattedTotal);
            
            // **UPDATE: Save the new services as original for future comparisons**
            originalServices = verticalServices;
            
            JOptionPane.showMessageDialog(this, 
                "Service/s updated successfully!\n\nTotal: " + formattedTotal, 
                "Services Updated", 
                JOptionPane.INFORMATION_MESSAGE);
            switchPanel(appointmentsPanel);
            highlightButtonBorder(appointmentsButton);
            return;
        }
    }
    
    // **NEW APPOINTMENT MODE** (not editing)
    jTextArea1.setText(verticalServices);
    String formattedTotal = String.format("₱%.2f", totalBill);
    totalBillTextfield.setText(formattedTotal);

    JOptionPane.showMessageDialog(this, 
        "Services added successfully!\n\nTotal: " + formattedTotal, 
        "Success", 
        JOptionPane.INFORMATION_MESSAGE);

    switchPanel(appointmentsPanel);
    highlightButtonBorder(appointmentsButton);
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

    private void contactTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactTextfieldActionPerformed

    private void emailTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextfieldActionPerformed

    private void classNameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classNameTextfieldActionPerformed

    }//GEN-LAST:event_classNameTextfieldActionPerformed

    private void addAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAppointmentButtonActionPerformed
                                             
    String clientName = classNameTextfield.getText().trim();
    String address = addressTextfield.getText().trim();
    String email = emailTextfield.getText().trim();
    String contact = contactTextfield.getText().trim();
    String petName = petNameTextfield.getText().trim();
    String species = speciesTextField.getText().trim();
    String breed = breedTextField.getText().trim();
    
    String selectedServices = jTextArea1.getText().trim();
    Date schedule = jDateChooser1.getDate();
    Object selectedAssistant = JComboBox.getSelectedItem();
    String assistant = (selectedAssistant != null) ? selectedAssistant.toString().trim() : "";
    
    // Validation - Check all required fields
    List<String> missingFields = new ArrayList<>();
    if (clientName.isEmpty()) missingFields.add("Client Name");
    if (address.isEmpty()) missingFields.add("Address");
    if (email.isEmpty()) missingFields.add("Email");
    if (contact.isEmpty()) missingFields.add("Contact Number");
    if (petName.isEmpty()) missingFields.add("Pet Name");
    if (species.isEmpty()) missingFields.add("Species");
    if (breed.isEmpty()) missingFields.add("Breed");
    if (selectedServices.isEmpty()) missingFields.add("Selected Services");
    if (schedule == null) missingFields.add("Schedule Date");
    if (assistant.isEmpty()) missingFields.add("Assigned Assistant");
    
    if (!missingFields.isEmpty()) {
        String message = "Please fill in all required fields:\n\n• " + String.join("\n• ", missingFields);
        JOptionPane.showMessageDialog(this, message, "Required Fields Missing", JOptionPane.WARNING_MESSAGE);
        return;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String sched = sdf.format(schedule);

    // Contact validation - must be exactly 11 digits
    if (!contact.matches("\\d{11}")) {
        JOptionPane.showMessageDialog(this, 
            "Contact number must be exactly 11 digits.\nPlease enter a valid Philippine mobile number.", 
            "Invalid Contact Number", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Total bill validation
    double totalBill = 0.0;
    String totalBillInput = totalBillTextfield.getText().trim();
    if (totalBillInput.isEmpty() || totalBillInput.equals("0.00") || totalBillInput.equals("₱0.00")) {
        JOptionPane.showMessageDialog(this, 
            "Please select at least one service before adding the appointment.", 
            "No Services Selected", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        String cleanBillInput = totalBillInput.replaceAll("[^\\d\\.]", "");
        totalBill = Double.parseDouble(cleanBillInput);
        if (totalBill <= 0) {
            JOptionPane.showMessageDialog(this, "Total bill must be greater than zero.", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
            return;
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid amount for Total Bill.", "Input Error", JOptionPane.WARNING_MESSAGE);
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

        // **CHECK IF EDITING EXISTING APPOINTMENT**
        if (selectedAppointmentId != -1) {
            
            if (!hasDataChanged()) {
        JOptionPane.showMessageDialog(this, 
            "No data has been changed or modified.", 
            "No Changes", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // **UNLOAD FIELDS AFTER MESSAGE**
        clearFormFields();
        uncheckAllServices();
        JComboBox.setSelectedIndex(-1);
        selectedAppointmentId = -1;
        fieldsLoaded = false;
        originalAppointmentData.clear();
//        jTable1.clearSelection();
        
        return;
    }
            // UPDATE existing appointment
            String updateSql = "UPDATE appointments SET client_name=?, client_address=?, client_email=?, client_contact=?, pet_name=?, pet_species=?, pet_breed=?, selected_services=?, schedule=?, assigned_assistant=?, total_bill=? WHERE id=?";
            pstmt = conn.prepareStatement(updateSql);
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
            pstmt.setInt(12, selectedAppointmentId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Appointment updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                
                loadRecordsTable();
                clearFormFields();
                uncheckAllServices();
                JComboBox.setSelectedIndex(-1);
                selectedAppointmentId = -1;
                fieldsLoaded = false;
                originalAppointmentData.clear();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // INSERT new appointment
            String insertSql = "INSERT INTO appointments (client_name, client_address, client_email, client_contact, pet_name, pet_species, pet_breed, selected_services, schedule, assigned_assistant, total_bill) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
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
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            int generatedId = -1;
            if (generatedKeys.next()) {
            generatedId = generatedKeys.getInt(1);
            lastAddedAppointmentId = generatedId;
        }
            
            loadRecordsTable(null);
            updateAppointmentCount();  // Change this line

            JOptionPane.showMessageDialog(this, 
    "Appointment added successfully!","Success", JOptionPane.INFORMATION_MESSAGE);
            
            if (recordsPanel.isVisible()) {
    loadRecordsAsCards();
}

clearFormFields();
uncheckAllServices();
JComboBox.setSelectedIndex(-1);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
        }
    }                                        
    }//GEN-LAST:event_addAppointmentButtonActionPerformed

    private void totalBillTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalBillTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalBillTextfieldActionPerformed

    private void servicebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicebuttonActionPerformed
        switchPanel(servicesPanel);
    }//GEN-LAST:event_servicebuttonActionPerformed

    private void breedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breedTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_breedTextFieldActionPerformed

    private void speciesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speciesTextFieldActionPerformed

    }//GEN-LAST:event_speciesTextFieldActionPerformed

    private void petNameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petNameTextfieldActionPerformed

    }//GEN-LAST:event_petNameTextfieldActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
                                      
    // Check if an appointment is selected
    if (selectedAppointmentId == -1) {
        JOptionPane.showMessageDialog(this, 
            "Please select an appointment from the table to print a receipt.", 
            "No Selection", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Load data directly from database
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
        conn = jdbcConnection.getConnection();
        ps = conn.prepareStatement("SELECT * FROM appointments WHERE id = ?");
        ps.setInt(1, selectedAppointmentId);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            String clientName = rs.getString("client_name");
            String address = rs.getString("client_address");
            String email = rs.getString("client_email");
            String contact = rs.getString("client_contact");
            String petName = rs.getString("pet_name");
            String species = rs.getString("pet_species");
            String breed = rs.getString("pet_breed");
            String selectedServices = rs.getString("selected_services");
            String formattedSchedule = rs.getString("schedule");
            String assistant = rs.getString("assigned_assistant");
            double totalBill = rs.getDouble("total_bill");

            printFrame printer = new printFrame(
                clientName, address, email, contact, petName, species, breed, 
                selectedServices, formattedSchedule, assistant, totalBill
            );
            printer.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Could not load appointment data.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "Error loading appointment: " + e.getMessage(), 
            "Database Error", 
            JOptionPane.ERROR_MESSAGE);
    } finally {
        jdbcConnection.closeConnection(conn, ps, rs);
    }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
                                    
    if (selectedAppointmentId == -1) {
        JOptionPane.showMessageDialog(this, 
            "Please select an appointment from the table to edit.", 
            "No Selection", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Check if already in edit mode
    if (fieldsLoaded) {
        JOptionPane.showMessageDialog(this, 
            "You are already in edit mode.\n\nModify the fields and click Update to save changes,\nor select a different appointment to edit.", 
            "Already in Edit Mode", 
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    // Load data from database
    loadFieldsFromDatabase(selectedAppointmentId);
    
    // Store original data for change detection
    originalAppointmentData.clear();
    originalAppointmentData.put("clientName", classNameTextfield.getText().trim());
    originalAppointmentData.put("address", addressTextfield.getText().trim());
    originalAppointmentData.put("email", emailTextfield.getText().trim());
    originalAppointmentData.put("contact", contactTextfield.getText().trim());
    originalAppointmentData.put("petName", petNameTextfield.getText().trim());
    originalAppointmentData.put("species", speciesTextField.getText().trim());
    originalAppointmentData.put("breed", breedTextField.getText().trim());
    originalAppointmentData.put("services", jTextArea1.getText().trim());
    originalAppointmentData.put("assistant", JComboBox.getSelectedItem() != null ? JComboBox.getSelectedItem().toString().trim() : "");
    originalAppointmentData.put("schedule", jDateChooser1.getDate() != null ? new SimpleDateFormat("MM/dd/yyyy").format(jDateChooser1.getDate()) : "");
    originalAppointmentData.put("totalBill", totalBillTextfield.getText().trim());
    
    originalServices = jTextArea1.getText().trim();
    servicesChanged = false;
    
    // Enable editing
    classNameTextfield.setEditable(true);
    addressTextfield.setEditable(true);
    emailTextfield.setEditable(true);
    contactTextfield.setEditable(true);
    petNameTextfield.setEditable(true);
    speciesTextField.setEditable(true);
    breedTextField.setEditable(true);
    jDateChooser1.setEnabled(true);
    
    // Keep these uneditable
    jTextArea1.setEditable(false);
    totalBillTextfield.setEditable(false);
    
    JOptionPane.showMessageDialog(this, 
        "Edit mode enabled. Modify fields and click Update.", 
        "Edit Mode", 
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                                         
    if (selectedAppointmentId == -1) {
        JOptionPane.showMessageDialog(this, "Please select an appointment to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Collect data
    String clientName = classNameTextfield.getText().trim();
    String address = addressTextfield.getText().trim();
    String email = emailTextfield.getText().trim();
    String contact = contactTextfield.getText().trim();
    String petName = petNameTextfield.getText().trim();
    String species = speciesTextField.getText().trim();
    String breed = breedTextField.getText().trim();
    String selectedServices = jTextArea1.getText().trim();
    Date schedule = jDateChooser1.getDate();
    Object selectedAssistant = JComboBox.getSelectedItem();
    String assistant = (selectedAssistant != null) ? selectedAssistant.toString().trim() : "";

    // Validation
    List<String> missingFields = new ArrayList<>();
    if (clientName.isEmpty()) missingFields.add("Client Name");
    if (address.isEmpty()) missingFields.add("Address");
    if (email.isEmpty()) missingFields.add("Email");
    if (contact.isEmpty()) missingFields.add("Contact Number");
    if (petName.isEmpty()) missingFields.add("Pet Name");
    if (species.isEmpty()) missingFields.add("Species");
    if (breed.isEmpty()) missingFields.add("Breed");
    if (selectedServices.isEmpty()) missingFields.add("Selected Services");
    if (schedule == null) missingFields.add("Schedule Date");
    if (assistant.isEmpty()) missingFields.add("Assigned Assistant");
    
    if (!missingFields.isEmpty()) {
        String message = "Please fill in all required fields:\n\n• " + String.join("\n• ", missingFields);
        JOptionPane.showMessageDialog(this, message, "Required Fields Missing", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Contact validation
    if (!contact.matches("\\d{11}")) {
        JOptionPane.showMessageDialog(this, 
            "Contact number must be exactly 11 digits.\nPlease enter a valid Philippine mobile number.", 
            "Invalid Contact Number", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Total bill validation
    double totalBill = 0.0;
    String totalBillInput = totalBillTextfield.getText().trim();
    if (totalBillInput.isEmpty() || totalBillInput.equals("0.00") || totalBillInput.equals("₱0.00")) {
        JOptionPane.showMessageDialog(this, 
            "Total bill cannot be zero. Please select services.", 
            "Invalid Amount", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        String cleanBillInput = totalBillInput.replaceAll("[^\\d\\.]", "");
        totalBill = Double.parseDouble(cleanBillInput);
        if (totalBill <= 0) {
            JOptionPane.showMessageDialog(this, "Total bill must be greater than zero.", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
            return;
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid amount for Total Bill.", "Input Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String sched = sdf.format(schedule);

    if (!hasDataChanged()) {
    JOptionPane.showMessageDialog(this, 
        "No data has been changed or modified.", 
        "No Changes", 
        JOptionPane.INFORMATION_MESSAGE);
    
    // **UNLOAD FIELDS AFTER MESSAGE**
    clearFormFields();
    uncheckAllServices();
    JComboBox.setSelectedIndex(-1);
    selectedAppointmentId = -1;
    fieldsLoaded = false;
    originalAppointmentData.clear();
   // jTable1.clearSelection();
    
    return;
}

    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        conn = jdbcConnection.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Failed to establish database connection.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "UPDATE appointments SET client_name=?, client_address=?, client_email=?, client_contact=?, pet_name=?, pet_species=?, pet_breed=?, selected_services=?, schedule=?, assigned_assistant=?, total_bill=? WHERE id=?";
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
        pstmt.setInt(12, selectedAppointmentId);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(this, "Appointment updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        if (recordsPanel.isVisible()) {
    loadRecordsAsCards();
}

        loadRecordsTable();

        // UPDATE APPOINTMENT COUNT
        updateAppointmentCount();  // Change this line

        clearFormFields();
        uncheckAllServices();
        JComboBox.setSelectedIndex(-1);
        selectedAppointmentId = -1;
        fieldsLoaded = false;
        originalAppointmentData.clear();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update appointment.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            jdbcConnection.closeConnection(conn, pstmt, null);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            if (conn == null || !conn.isValid(5)) {
                JOptionPane.showMessageDialog(this, "Failed to establish a valid database connection.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "DELETE FROM appointments WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, selectedAppointmentId);

            int rowsAffected = pstmt.executeUpdate();

         if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(this, "Appointment deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    
    if (recordsPanel.isVisible()) {
    loadRecordsAsCards();
}
    
    loadRecordsTable();
    updateAppointmentCount();  // Make sure this line is here

    clearFormFields();
    uncheckAllServices();
    JComboBox.setSelectedIndex(-1);
    selectedAppointmentId = -1;
    fieldsLoaded = false;
    originalAppointmentData.clear();
} else {
    JOptionPane.showMessageDialog(this, "Failed to delete appointment.", "Error", JOptionPane.ERROR_MESSAGE);
}

        } catch (SQLException e) {
            System.err.println("Error deleting appointment: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error deleting appointment: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            jdbcConnection.closeConnection(conn, pstmt, null);
        }
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void JComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboBoxActionPerformed

    private void settingsButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButtonMousePressed
// REMOVED - background changes not needed  
    }//GEN-LAST:event_settingsButtonMousePressed

    private void settingsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButtonMouseEntered
// REMOVED - background changes not needed     
    }//GEN-LAST:event_settingsButtonMouseEntered

    private void recordsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recordsButtonMouseEntered
// REMOVED - background changes not needed        
    }//GEN-LAST:event_recordsButtonMouseEntered

    private void appointmentsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentsButtonMouseEntered

    }//GEN-LAST:event_appointmentsButtonMouseEntered

    private void jCheckBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox11ActionPerformed

    private void jCheckBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox14ActionPerformed

    private void jCheckBox28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox28ActionPerformed

    private void jCheckBox31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox31ActionPerformed

    private void recordsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recordsTableMouseClicked
         int selectedRow = recordsTable.getSelectedRow();
    if (selectedRow != -1) {
        // Convert view row to model row (in case of sorting/filtering)
        int modelRow = recordsTable.convertRowIndexToModel(selectedRow);
        
        // Get ID from the hidden first column (index 0)
        selectedAppointmentId = (int) recordsTable.getModel().getValueAt(modelRow, 0);
        
        System.out.println("Selected appointment ID: " + selectedAppointmentId);
    }
    }//GEN-LAST:event_recordsTableMouseClicked

    private void recordsTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recordsTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_recordsTableMouseEntered

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1MouseEntered

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
         String searchText = jTextField1.getText().trim();
    
    if (searchText.isEmpty()) {
        recordsSorter.setRowFilter(null);
    } else {
        recordsSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
         String searchText = jTextField1.getText().trim();
    
    if (searchText.isEmpty()) {
        recordsSorter.setRowFilter(null);
    } else {
        // Search across all visible columns
        recordsSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }
    }//GEN-LAST:event_jTextField1KeyReleased

    public void setSelectedServices(String services) {
        jTextArea1.setText(services);
    }
    private void clearFormFields() {
        classNameTextfield.setText(""); 
        addressTextfield.setText(""); 
        emailTextfield.setText(""); 
        contactTextfield.setText(""); 
        petNameTextfield.setText(""); 
        speciesTextField.setText(""); 
        breedTextField.setText(""); 
        
        jTextArea1.setText("");
        jDateChooser1.setDate(null); 
        JComboBox.setSelectedIndex(-1); // **ENSURE THIS LINE EXISTS**
        
        totalBillTextfield.setText("");
        selectedAppointmentId = -1;
//        jTable1.clearSelection();
        fieldsLoaded = false;
        
        originalServices = "";
        servicesChanged = false;
        
        originalAppointmentData.clear();
    } 

    private boolean hasDataChanged() {
    if (originalAppointmentData.isEmpty()) {
        return true; // If no original data, consider it as new/changed
    }
    
    // Compare current values with original values
    String currentClientName = classNameTextfield.getText().trim();
    String currentAddress = addressTextfield.getText().trim();
    String currentEmail = emailTextfield.getText().trim();
    String currentContact = contactTextfield.getText().trim();
    String currentPetName = petNameTextfield.getText().trim();
    String currentSpecies = speciesTextField.getText().trim();
    String currentBreed = breedTextField.getText().trim();
    String currentServices = jTextArea1.getText().trim();
    String currentAssistant = JComboBox.getSelectedItem() != null ? JComboBox.getSelectedItem().toString().trim() : "";
    String currentSchedule = jDateChooser1.getDate() != null ? new SimpleDateFormat("MM/dd/yyyy").format(jDateChooser1.getDate()) : "";
    String currentTotalBill = totalBillTextfield.getText().trim();
    
    // Check each field for changes
    boolean changed = !currentClientName.equals(originalAppointmentData.get("clientName")) ||
                      !currentAddress.equals(originalAppointmentData.get("address")) ||
                      !currentEmail.equals(originalAppointmentData.get("email")) ||
                      !currentContact.equals(originalAppointmentData.get("contact")) ||
                      !currentPetName.equals(originalAppointmentData.get("petName")) ||
                      !currentSpecies.equals(originalAppointmentData.get("species")) ||
                      !currentBreed.equals(originalAppointmentData.get("breed")) ||
                      !currentServices.equals(originalAppointmentData.get("services")) ||
                      !currentAssistant.equals(originalAppointmentData.get("assistant")) ||
                      !currentSchedule.equals(originalAppointmentData.get("schedule")) ||
                      !currentTotalBill.equals(originalAppointmentData.get("totalBill"));
    
    return changed;
}
   
    
    private void fetchAppointments(String search) throws SQLException {
    table.setRowCount(0);
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        conn = jdbcConnection.getConnection();
        if (conn == null) {
            System.err.println("Failed to connect to database.");
            return;
        }
        System.out.println("Connection established for fetchAppointments.");
        String sql = "SELECT client_name, client_address, client_email, client_contact, pet_name, pet_species, pet_breed, selected_services, schedule, assigned_assistant, total_bill, id FROM appointments";
    
        if (search != null && !search.trim().isEmpty()) {
            sql += " WHERE client_name LIKE ? OR pet_name LIKE ?";
        }
        
        pstmt = conn.prepareStatement(sql);
        System.out.println("Prepared statement created: " + sql);
        
        if (search != null && !search.trim().isEmpty()) {
            pstmt.setString(1, "%" + search + "%");
            pstmt.setString(2, "%" + search + "%");
        }
        
        rs = pstmt.executeQuery();
        System.out.println("Query executed. ResultSet is valid: " + (rs != null));
        
        int rowCount = 0;
        while (rs.next()) {
        System.out.println("Fetched row: " + rs.getString("client_name") + ", " + rs.getString("pet_name"));
        table.addRow(new Object[]{
        rs.getString("client_name"),
        rs.getString("pet_name"),
        rs.getString("pet_species"),
        rs.getString("selected_services"),
        rs.getInt("id")
    });
}
        System.out.println("Fetched " + rowCount + " rows.");
        
    } catch (SQLException e) {
        System.err.println("Error fetching appointments: " + e.getMessage());
        e.printStackTrace();  // Full stack trace for details
    } finally {
        jdbcConnection.closeConnection(conn, pstmt, rs);
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                logInFrame frame = new logInFrame();
                frame.setLocationRelativeTo(null);  // Center on screen
                frame.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JComboBox;
    private javax.swing.JPanel aboutPanel;
    private javax.swing.JPanel aboutUsPanel;
    private javax.swing.JButton addAppointmentButton;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addressTextfield;
    private javax.swing.JLabel appointmentCount;
    private javax.swing.JButton appointmentsButton;
    private javax.swing.JPanel appointmentsPanel;
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
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox29;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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

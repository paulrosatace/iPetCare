/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frames;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import JDBC.jdbcConnection;
/**
 *
 * @author Paul Adrian
 */
public class Invoice extends JFrame {
    private int appointmentId;
    
    public Invoice(int appointmentId) {
        this.appointmentId = appointmentId;
        initComponents();
        loadAppointmentDetails();
        
        setTitle("Appointment Invoice - #" + appointmentId);
        setSize(800, 900);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void initComponents() {
        getContentPane().setBackground(new Color(244, 247, 247));
        setLayout(new BorderLayout(20, 20));
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(7, 122, 125));
        headerPanel.setPreferredSize(new Dimension(800, 80));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("APPOINTMENT INVOICE", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel idLabel = new JLabel("ID: #" + appointmentId, JLabel.CENTER);
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        idLabel.setForeground(Color.WHITE);
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(idLabel, BorderLayout.SOUTH);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Main content panel (will be populated with data)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(244, 247, 247));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(new Color(244, 247, 247));
        
        JButton printButton = new JButton("Print Invoice");
        printButton.setBackground(new Color(7, 122, 125));
        printButton.setForeground(Color.WHITE);
        printButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        printButton.setPreferredSize(new Dimension(150, 40));
        printButton.setFocusPainted(false);
        printButton.addActionListener(e -> printInvoice());
        
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(new Color(84, 110, 122));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeButton.setPreferredSize(new Dimension(150, 40));
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadAppointmentDetails() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = jdbcConnection.getConnection();
            String sql = "SELECT * FROM appointments WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                // Get main content panel
                JScrollPane scrollPane = (JScrollPane) ((BorderLayout) getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER);
                JPanel mainPanel = (JPanel) scrollPane.getViewport().getView();
                
                // Client Information Section
                mainPanel.add(createSectionPanel("CLIENT INFORMATION",
                    new String[]{"Client Name", "Address", "Email", "Contact"},
                    new String[]{
                        capitalizeFirstLetter(rs.getString("client_name")),
                        capitalizeFirstLetter(rs.getString("client_address")),
                        rs.getString("client_email").toLowerCase(),
                        rs.getString("client_contact")
                    }
                ));
                
                mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                
                // Pet Information Section
                mainPanel.add(createSectionPanel("PET INFORMATION",
                    new String[]{"Pet Name", "Species", "Breed"},
                    new String[]{
                        capitalizeFirstLetter(rs.getString("pet_name")),
                        capitalizeFirstLetter(rs.getString("pet_species")),
                        capitalizeFirstLetter(rs.getString("pet_breed"))
                    }
                ));
                
                mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                
                // Appointment Details Section
                mainPanel.add(createSectionPanel("APPOINTMENT DETAILS",
                    new String[]{"Schedule", "Assigned Assistant"},
                    new String[]{
                        rs.getString("schedule"),
                        capitalizeFirstLetter(rs.getString("assigned_assistant"))
                    }
                ));
                
                mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                
                // Services Section
                String services = rs.getString("selected_services");
                JPanel servicesPanel = new JPanel();
                servicesPanel.setLayout(new BorderLayout());
                servicesPanel.setBackground(Color.WHITE);
                servicesPanel.setBorder(new CompoundBorder(
                    new LineBorder(new Color(0, 51, 51), 2, true),
                    new EmptyBorder(15, 20, 15, 20)
                ));
                
                JLabel servicesTitle = new JLabel("SELECTED SERVICES");
                servicesTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
                servicesTitle.setForeground(new Color(7, 122, 125));
                servicesTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
                
                JTextArea servicesArea = new JTextArea(services);
                servicesArea.setEditable(false);
                servicesArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                servicesArea.setBackground(Color.WHITE);
                servicesArea.setLineWrap(true);
                servicesArea.setWrapStyleWord(true);
                
                servicesPanel.add(servicesTitle, BorderLayout.NORTH);
                servicesPanel.add(servicesArea, BorderLayout.CENTER);
                
                mainPanel.add(servicesPanel);
                
                mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                
                // Total Bill Section
                double totalBill = rs.getDouble("total_bill");
                JPanel billPanel = new JPanel();
                billPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                billPanel.setBackground(new Color(7, 122, 125));
                billPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
                
                JLabel billLabel = new JLabel("TOTAL BILL: ");
                billLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
                billLabel.setForeground(Color.WHITE);
                
                JLabel billAmount = new JLabel(String.format("₱%.2f", totalBill));
                billAmount.setFont(new Font("Segoe UI", Font.BOLD, 24));
                billAmount.setForeground(Color.WHITE);
                
                billPanel.add(billLabel);
                billPanel.add(billAmount);
                
                mainPanel.add(billPanel);
                
                mainPanel.revalidate();
                mainPanel.repaint();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error loading appointment details: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            jdbcConnection.closeConnection(conn, ps, rs);
        }
    }
    
    private JPanel createSectionPanel(String title, String[] labels, String[] values) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BorderLayout());
        sectionPanel.setBackground(Color.WHITE);
        sectionPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(0, 51, 51), 2, true),
            new EmptyBorder(15, 20, 15, 20)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(7, 122, 125));
        titleLabel.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(labels.length, 2, 10, 10));
        contentPanel.setBackground(Color.WHITE);
        
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            label.setForeground(new Color(46, 46, 46));
            
            JLabel value = new JLabel(values[i]);
            value.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            
            contentPanel.add(label);
            contentPanel.add(value);
        }
        
        sectionPanel.add(titleLabel, BorderLayout.NORTH);
        sectionPanel.add(contentPanel, BorderLayout.CENTER);
        
        return sectionPanel;
    }
    
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
    
    private void printInvoice() {
        // Create a printFrame instance with the current appointment data
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = jdbcConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM appointments WHERE id = ?");
            ps.setInt(1, appointmentId);
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
                String schedule = rs.getString("schedule");
                String assistant = rs.getString("assigned_assistant");
                double totalBill = rs.getDouble("total_bill");
                
                printFrame printer = new printFrame(
                    clientName, address, email, contact, petName, species, breed,
                    selectedServices, schedule, assistant, totalBill
                );
                printer.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error printing invoice: " + e.getMessage(),
                "Print Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            jdbcConnection.closeConnection(conn, ps, rs);
        }
    }
}

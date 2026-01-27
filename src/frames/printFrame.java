/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FontMetrics;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 *
 * @author ADMIN
 */
public class printFrame extends javax.swing.JFrame implements Printable{

    

    /**
     * Creates new form printFrame
     */
public printFrame(String clientName, String address, String email, String contact, 
                      String petName, String species, String breed, 
                      String selectedServices, String formattedSchedule, 
                      String assistant, double totalBill) {
        initComponents();

        // Generate random receipt number
        receiptNumber.setText(generateReceiptNumber());
        
        // Get current date and time
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        receiptDate.setText(dateFormat.format(now));
        receiptTime.setText(timeFormat.format(now));
        
        // Set all appointment data
        receiptClientName.setText(clientName);
        receiptPetName.setText(petName);
        receiptSchedule.setText(formattedSchedule);
        receiptAssistant.setText(assistant);
        receiptTotalBill.setText(String.format("₱%.2f", totalBill));
        
        // Configure services text area FIRST
        receiptServices.setLineWrap(true);
        receiptServices.setWrapStyleWord(true);
        receiptServices.setEditable(false);
        receiptServices.setFocusable(false);
        receiptServices.setText(selectedServices);
        
        // Make fields non-editable
        setFieldsEditable(false);
        
        // **CRITICAL: Adjust height with accurate calculation**
    SwingUtilities.invokeLater(() -> {
        adjustReceiptHeight(selectedServices);
    });
    }

/**
 * CRITICAL: Recreate jPanel1 layout to accommodate dynamic service panel height
 */
    private void recreateMainPanelLayout(int servicesPanelHeight) {
        // Create new layout for jPanel1
    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(receiptServicesPanel, javax.swing.GroupLayout.Alignment.LEADING, 
                    javax.swing.GroupLayout.PREFERRED_SIZE, 280, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, 
                    javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addContainerGap(24, Short.MAX_VALUE))
    );
    
    jPanel1Layout.setVerticalGroup(
    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(receiptServicesPanel, 
            javax.swing.GroupLayout.PREFERRED_SIZE, servicesPanelHeight, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    
    System.out.println("jPanel1 layout recreated with services panel height: " + servicesPanelHeight);
    }

    private void adjustReceiptHeight(String selectedServices) {
            System.out.println("=== Adjusting Receipt Height ===");
        
        // Calculate required height for services text
        int textAreaWidth = 250;
        int calculatedHeight = calculateWrappedTextHeight(
            selectedServices, 
            textAreaWidth, 
            receiptServices.getFont()
        );
        
        // Add padding
       int servicesHeight = calculatedHeight + 2;  // Reduced from 80 to 40

// Ensure minimum
    servicesHeight = Math.max(50, servicesHeight);

    System.out.println("Final services height: " + servicesHeight);

// Always use direct layout (no scroll) for printing
recreateDirectLayout(servicesHeight);
        // Cap maximum to keep receipt reasonable (use scroll if needed)
        int maxServicesHeight = 500;
        boolean useScroll = servicesHeight > maxServicesHeight;
        
        if (useScroll) {
            System.out.println("Using scroll pane - content too tall");
            //recreateWithScrollPane(servicesHeight, maxServicesHeight);
        } else {
            System.out.println("Direct layout - height: " + servicesHeight);
            recreateDirectLayout(servicesHeight);
        }
        
        System.out.println("=== End Adjustment ===");
    }
    
    /**
     * Recreate layout WITHOUT scroll (direct text area)
     */
    private void recreateDirectLayout(int servicesHeight) {
    System.out.println("Creating direct layout with height: " + servicesHeight);
    
    // Remove existing components
    receiptServicesPanel.removeAll();
    
    // Set text area size explicitly
    receiptServices.setPreferredSize(new Dimension(250, servicesHeight));
    receiptServices.setMinimumSize(new Dimension(250, servicesHeight));
    receiptServices.setMaximumSize(new Dimension(250, servicesHeight));
    receiptServices.setSize(250, servicesHeight);
    
    // Re-add components
    receiptServicesPanel.add(jLabel7);
    receiptServicesPanel.add(receiptServices);
    
    // Create new GroupLayout
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(receiptServicesPanel);
    receiptServicesPanel.setLayout(layout);
    
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel7)
                .addComponent(receiptServices, 250, 250, 250))
            .addContainerGap(29, Short.MAX_VALUE))
    );
    
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel7, 
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(6, 6, 6)
            .addComponent(receiptServices, 
                servicesHeight, servicesHeight, servicesHeight)
            .addContainerGap())
    );
    
    
    // Update panel size
    int panelHeight = 16 + 6 + servicesHeight + 12;
    receiptServicesPanel.setPreferredSize(new Dimension(200, panelHeight));
    receiptServicesPanel.setMinimumSize(new Dimension(200, panelHeight));
    
    System.out.println("Services panel height: " + panelHeight);
    
    // Update frame
    updateFrameSize(panelHeight);
}
    
    /**
     * Update the overall frame size based on services panel height
     */
    private void updateFrameSize(int servicesPanelHeight) {
    // Calculate total height
    int jPanel2Height = 289;
    int jPanel4Height = 234;
    int gaps = 18;
    int containerPadding = 86;
    
    int totalHeight = jPanel2Height + servicesPanelHeight + jPanel4Height + gaps + containerPadding;
    
    // Update jPanel1
    int jPanel1Height = totalHeight - containerPadding;
    jPanel1.setPreferredSize(new Dimension(315, jPanel1Height));
    jPanel1.setMinimumSize(new Dimension(315, jPanel1Height));
    
    // Update frame
    this.setSize(315, totalHeight);
    this.setPreferredSize(new Dimension(315, totalHeight));
    
    System.out.println("Frame updated to: 315 x " + totalHeight);
    
    // **CRITICAL: Recreate main panel layout**
    recreateMainPanelLayout(servicesPanelHeight);
    
    // Force complete revalidation
    receiptServices.invalidate();
    receiptServicesPanel.invalidate();
    jPanel1.invalidate();
    this.invalidate();
    
    receiptServices.validate();
    receiptServicesPanel.validate();
    jPanel1.validate();
    this.validate();
    
    receiptServices.repaint();
    receiptServicesPanel.repaint();
    jPanel1.repaint();
    this.repaint();
    
    this.setLocationRelativeTo(null);
    
    // Debug
    System.out.println("receiptServices actual size: " + receiptServices.getSize());
    System.out.println("receiptServices bounds: " + receiptServices.getBounds());
}
    
    private void debugServicesDisplay() {
        System.out.println("=== Services Display Debug ===");
        System.out.println("Text length: " + receiptServices.getText().length());
        System.out.println("Line count: " + receiptServices.getLineCount());
        System.out.println("Bounds: " + receiptServices.getBounds());
        System.out.println("Preferred size: " + receiptServices.getPreferredSize());
        System.out.println("Actual size: " + receiptServices.getSize());
        System.out.println("Parent panel size: " + receiptServicesPanel.getSize());
        System.out.println("=== End Debug ===");
    }
    
    private void setFieldsEditable(boolean editable) {
        receiptClientName.setEditable(false);
        receiptPetName.setEditable(false);
        receiptServices.setEditable(false);
        receiptSchedule.setEditable(false);
        receiptTotalBill.setEditable(false);
        receiptAssistant.setEditable(false);
        receiptNumber.setEditable(false);
        receiptDate.setEditable(false);
        receiptTime.setEditable(false);
    }
    
    private void initiatePrintJob() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        boolean doPrint = job.printDialog();

        if (doPrint) {
            try {
                job.print();
                JOptionPane.showMessageDialog(this, "Receipt sent to printer!", "Print Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Error printing receipt: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } finally {
                this.dispose();
            }
        } else {
            this.dispose();
        }
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        if (jPanel1 == null) {
            System.err.println("ERROR: jPanel1 is null in print() method.");
            return NO_SUCH_PAGE;
        }

        jPanel1.printAll(g2d);

        return PAGE_EXISTS;
    }



private int calculateWrappedTextHeight(String text, int width, java.awt.Font font) {
    if (text == null || text.isEmpty()) {
        return 50;
    }
    
    // Create a properly configured temporary text area
    JTextArea temp = new JTextArea();
    temp.setFont(font);
    temp.setLineWrap(true);
    temp.setWrapStyleWord(true);
    temp.setSize(width, 1);
    temp.setText(text);
    
    // Force layout
    temp.doLayout();
    
    // Get preferred size
    Dimension preferredSize = temp.getPreferredSize();
    int height = preferredSize.height;
    
    // Also calculate by line count as backup
    int lineCount = temp.getLineCount();
    FontMetrics fm = temp.getFontMetrics(font);
    int lineHeight = fm.getHeight();
    int heightByLines = lineCount * lineHeight + 20;
    
    // Use the larger value
    int finalHeight = Math.max(height, heightByLines);
    
    System.out.println("=== Height Calculation ===");
    System.out.println("Text length: " + text.length());
    System.out.println("Preferred height: " + height);
    System.out.println("Line count: " + lineCount);
    System.out.println("Height by lines: " + heightByLines);
    System.out.println("Final height: " + finalHeight);
    
    return finalHeight;
}

    private String generateReceiptNumber() {
        Random random = new Random();
        int receiptNum = 100000 + random.nextInt(900000);
        return String.valueOf(receiptNum);
    }

private void configureServicesDisplay() {
    receiptServices.setLineWrap(true);
    receiptServices.setWrapStyleWord(true);
    receiptServices.setEditable(false);
    receiptServices.setFocusable(false);
    receiptServices.setOpaque(true);
    receiptServices.setBackground(new java.awt.Color(239, 238, 234));
}

    /**
 * Debug method to print all component positions
        */
       private void debugComponentPositions() {
        System.out.println("=== Component Positions ===");
        java.awt.Component[] components = jPanel2.getComponents();
        for (java.awt.Component comp : components) {
        java.awt.Rectangle bounds = comp.getBounds();
        String name = comp.getName();
        String className = comp.getClass().getSimpleName();
        System.out.println(className + " at Y=" + bounds.y + " height=" + bounds.height);
        
        if (comp instanceof javax.swing.JLabel) {
            javax.swing.JLabel label = (javax.swing.JLabel) comp;
            System.out.println("  Text: " + label.getText());
        }
    }
    System.out.println("jPanel2 size: " + jPanel2.getSize());
    System.out.println("jPanel1 size: " + jPanel1.getSize());
    System.out.println("Frame size: " + this.getSize());
    System.out.println("=== End Debug ===");
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        receiptClientName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        receiptPetName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        receiptSchedule = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        receiptAssistant = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        receiptNumber = new javax.swing.JTextField();
        receiptDate = new javax.swing.JTextField();
        receiptTime = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        receiptServicesPanel = new javax.swing.JPanel();
        receiptServices = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        receiptTotalBill = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jPanel1.setMaximumSize(new java.awt.Dimension(315, 32767));
        jPanel1.setMinimumSize(new java.awt.Dimension(310, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jPanel2.setMinimumSize(new java.awt.Dimension(300, 350));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 0));
        jPanel2.setVerifyInputWhenFocusTarget(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(46, 46, 46));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Client Name:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        receiptClientName.setEditable(false);
        receiptClientName.setBackground(new java.awt.Color(255, 255, 255));
        receiptClientName.setBorder(null);
        receiptClientName.setFocusable(false);
        receiptClientName.setPreferredSize(new java.awt.Dimension(200, 30));
        receiptClientName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptClientNameActionPerformed(evt);
            }
        });
        jPanel2.add(receiptClientName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 160, 15));

        jLabel4.setBackground(new java.awt.Color(46, 46, 46));
        jLabel4.setText("Pet Name:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        receiptPetName.setEditable(false);
        receiptPetName.setBackground(new java.awt.Color(255, 255, 255));
        receiptPetName.setBorder(null);
        receiptPetName.setFocusable(false);
        receiptPetName.setPreferredSize(new java.awt.Dimension(200, 30));
        receiptPetName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptPetNameActionPerformed(evt);
            }
        });
        jPanel2.add(receiptPetName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 160, 15));

        jLabel8.setBackground(new java.awt.Color(46, 46, 46));
        jLabel8.setText("Assistant:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        receiptSchedule.setEditable(false);
        receiptSchedule.setBackground(new java.awt.Color(255, 255, 255));
        receiptSchedule.setBorder(null);
        receiptSchedule.setFocusable(false);
        receiptSchedule.setPreferredSize(new java.awt.Dimension(200, 30));
        receiptSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptScheduleActionPerformed(evt);
            }
        });
        jPanel2.add(receiptSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 160, 15));

        jLabel12.setBackground(new java.awt.Color(46, 46, 46));
        jLabel12.setText("Schedule:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        receiptAssistant.setEditable(false);
        receiptAssistant.setBackground(new java.awt.Color(255, 255, 255));
        receiptAssistant.setBorder(null);
        receiptAssistant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptAssistantActionPerformed(evt);
            }
        });
        jPanel2.add(receiptAssistant, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 160, 15));

        jLabel15.setText("Time:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 90, -1));

        jLabel16.setText("Receipt No:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 80, -1));

        jLabel17.setText("Date:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 70, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("--------------------------------------------");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 270, -1));

        receiptNumber.setEditable(false);
        receiptNumber.setBackground(new java.awt.Color(255, 255, 255));
        receiptNumber.setBorder(null);
        receiptNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptNumberActionPerformed(evt);
            }
        });
        jPanel2.add(receiptNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 160, 15));

        receiptDate.setEditable(false);
        receiptDate.setBackground(new java.awt.Color(255, 255, 255));
        receiptDate.setBorder(null);
        jPanel2.add(receiptDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 160, 15));

        receiptTime.setEditable(false);
        receiptTime.setBackground(new java.awt.Color(255, 255, 255));
        receiptTime.setBorder(null);
        jPanel2.add(receiptTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 160, 15));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("--------------------------------------------");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 298, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("--------------------------------------------");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 298, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("===================================");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 280, -1));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Green Modern Veterinary Clinic Logo.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-11, 40, 290, -1));

        receiptServicesPanel.setBackground(new java.awt.Color(255, 255, 255));
        receiptServicesPanel.setMaximumSize(new java.awt.Dimension(300, 32767));
        receiptServicesPanel.setMinimumSize(new java.awt.Dimension(300, 100));
        receiptServicesPanel.setPreferredSize(new java.awt.Dimension(300, 0));

        receiptServices.setEditable(false);
        receiptServices.setBackground(new java.awt.Color(255, 255, 255));
        receiptServices.setColumns(20);
        receiptServices.setRows(5);
        receiptServices.setBorder(null);
        receiptServices.setFocusable(false);

        jLabel7.setBackground(new java.awt.Color(46, 46, 46));
        jLabel7.setText("SERVICES:");

        javax.swing.GroupLayout receiptServicesPanelLayout = new javax.swing.GroupLayout(receiptServicesPanel);
        receiptServicesPanel.setLayout(receiptServicesPanelLayout);
        receiptServicesPanelLayout.setHorizontalGroup(
            receiptServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiptServicesPanelLayout.createSequentialGroup()
                .addGroup(receiptServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(receiptServicesPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(receiptServices, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(receiptServicesPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        receiptServicesPanelLayout.setVerticalGroup(
            receiptServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiptServicesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receiptServices, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(300, 32767));
        jPanel4.setMinimumSize(new java.awt.Dimension(300, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 0));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Thank you for trusting iPetCare!");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("=====================================");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("--------------------------------------------");

        jLabel19.setBackground(new java.awt.Color(46, 46, 46));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("TOTAL BILL:");

        receiptTotalBill.setEditable(false);
        receiptTotalBill.setBackground(new java.awt.Color(255, 255, 255));
        receiptTotalBill.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        receiptTotalBill.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        receiptTotalBill.setBorder(null);
        receiptTotalBill.setFocusable(false);
        receiptTotalBill.setPreferredSize(new java.awt.Dimension(200, 30));
        receiptTotalBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptTotalBillActionPerformed(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(46, 46, 46));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("For inquiries:");

        jLabel9.setBackground(new java.awt.Color(46, 46, 46));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("0912-345-6789");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("--------------------------------------------");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(receiptTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9))
                    .addComponent(jLabel13)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(176, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(receiptTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(jLabel21)
                    .addContainerGap(146, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(receiptServicesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receiptServicesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(5, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(301, 589));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void receiptTotalBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptTotalBillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receiptTotalBillActionPerformed

    private void receiptPetNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptPetNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receiptPetNameActionPerformed

    private void receiptClientNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptClientNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receiptClientNameActionPerformed

    private void receiptAssistantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptAssistantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receiptAssistantActionPerformed

    private void receiptScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptScheduleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receiptScheduleActionPerformed

    private void receiptNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receiptNumberActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(printFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(printFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(printFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(printFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new printFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField receiptAssistant;
    public javax.swing.JTextField receiptClientName;
    private javax.swing.JTextField receiptDate;
    private javax.swing.JTextField receiptNumber;
    public javax.swing.JTextField receiptPetName;
    public javax.swing.JTextField receiptSchedule;
    public javax.swing.JTextArea receiptServices;
    private javax.swing.JPanel receiptServicesPanel;
    private javax.swing.JTextField receiptTime;
    public javax.swing.JTextField receiptTotalBill;
    // End of variables declaration//GEN-END:variables
}

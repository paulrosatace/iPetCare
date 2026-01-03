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
    
    // Configure services text area
    receiptServices.setLineWrap(true);
    receiptServices.setWrapStyleWord(true);
    receiptServices.setEditable(false);
    receiptServices.setFocusable(false);
    
    // Set services text
    receiptServices.setText(selectedServices);
    
    // Make fields non-editable
    setFieldsEditable(false);
    
    // Adjust height based on services
    adjustFrameHeight(selectedServices);
}
  
    private String generateReceiptNumber() {
        Random random = new Random();
        int receiptNum = 100000 + random.nextInt(900000);
        return String.valueOf(receiptNum);
    }
    
    /**
 * Adjust frame, panel, and services height based on content
 * Precisely repositions all elements below the services section
 */
/**
 * Adjust frame, panel, and services height based on content
 * Ensures all services are visible and properly positioned
 */
/**
 * Adjust frame with explicit component tracking
 */
    
/**
 * Enhanced adjustment with forced visibility
 */
private void adjustFrameHeight(String selectedServices) {
    // Split services to count lines
    String[] lines = selectedServices.split("\n");
    int lineCount = lines.length;
    
    // Configure text area
    receiptServices.setText(selectedServices);
    receiptServices.setLineWrap(true);
    receiptServices.setWrapStyleWord(true);
    
    // Calculate required height based on font metrics
    java.awt.FontMetrics fm = receiptServices.getFontMetrics(receiptServices.getFont());
    int lineHeight = fm.getHeight();
    int padding = 20; // Extra padding for spacing
    
    // Calculate height needed for all lines
    int calculatedServicesHeight = (lineCount * lineHeight) + padding;
    
    // Ensure minimum height
    calculatedServicesHeight = Math.max(64, calculatedServicesHeight);
    
    System.out.println("=== Receipt Adjustment ===");
    System.out.println("Line count: " + lineCount);
    System.out.println("Line height: " + lineHeight);
    System.out.println("Calculated services height: " + calculatedServicesHeight);
    
    // **KEY FIX: Update receiptServices bounds within the GroupLayout**
    receiptServices.setPreferredSize(new Dimension(250, calculatedServicesHeight));
    receiptServices.setMinimumSize(new Dimension(250, calculatedServicesHeight));
    receiptServices.setMaximumSize(new Dimension(250, calculatedServicesHeight));
    
    // Calculate base heights from initComponents
    int baseServicesHeight = 64; // Original receiptServices height
    int heightDifference = calculatedServicesHeight - baseServicesHeight;
    
    System.out.println("Height difference: " + heightDifference);
    
    // Calculate new receiptServicesPanel height
    // Panel structure: jLabel7 (16) + gap (12) + receiptServices (variable) + gap (16)
    int basePanelHeight = 108; // Original panel height from initComponents
    int newPanelHeight = 16 + 12 + calculatedServicesHeight + 16; // Label + gaps + services + bottom gap
    
    receiptServicesPanel.setPreferredSize(new Dimension(301, newPanelHeight));
    receiptServicesPanel.setMinimumSize(new Dimension(301, newPanelHeight));
    receiptServicesPanel.setMaximumSize(new Dimension(301, newPanelHeight));
    
    System.out.println("New services panel height: " + newPanelHeight);
    
    // Calculate panel height difference
    int panelHeightDifference = newPanelHeight - basePanelHeight;
    
    // Calculate new jPanel1 (main container) height
    // Original: jPanel2 (349) + receiptServicesPanel (108) + jPanel4 (242) + gaps
    int basePanel1Height = 711;
    int newPanel1Height = basePanel1Height + panelHeightDifference;
    
    jPanel1.setPreferredSize(new Dimension(315, newPanel1Height));
    jPanel1.setMinimumSize(new Dimension(315, newPanel1Height));
    jPanel1.setMaximumSize(new Dimension(315, newPanel1Height));
    
    System.out.println("New panel1 height: " + newPanel1Height);
    
    // Calculate new frame height
    int baseFrameHeight = 711;
    int newFrameHeight = baseFrameHeight + panelHeightDifference;
    
    this.setSize(313, newFrameHeight);
    this.setPreferredSize(new Dimension(313, newFrameHeight));
    
    System.out.println("New frame height: " + newFrameHeight);
    
    // **CRITICAL: Force GroupLayout to recalculate**
    receiptServicesPanel.invalidate();
    receiptServicesPanel.validate();
    receiptServicesPanel.doLayout();
    
    receiptServices.invalidate();
    receiptServices.validate();
    
    jPanel1.invalidate();
    jPanel1.validate();
    jPanel1.doLayout();
    
    this.invalidate();
    this.validate();
    
    // Repaint everything
    receiptServices.repaint();
    receiptServicesPanel.repaint();
    jPanel1.repaint();
    this.repaint();
    
    // Center on screen
    this.setLocationRelativeTo(null);
    
    System.out.println("=== End Adjustment ===");
}

private void configureServicesDisplay() {
    receiptServices.setLineWrap(true);
    receiptServices.setWrapStyleWord(true);
    receiptServices.setEditable(false);
    receiptServices.setFocusable(false);
    receiptServices.setOpaque(true);
    receiptServices.setBackground(new java.awt.Color(239, 238, 234));
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
//    private void setFieldsEditable(boolean editable) {
//        receiptClientName.setEditable(false);
//        receiptPetName.setEditable(false);
//        receiptServices.setEditable(false);
//        receiptSchedule.setEditable(false);
//        receiptTotalBill.setEditable(false);
//        receiptAssistant.setEditable(false);
//        receiptNumber.setEditable(false);
//        receiptDate.setEditable(false);
//        receiptTime.setEditable(false);
//    }
    
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


    // --- IMPLEMENTATION OF THE PRINTABLE INTERFACE ---
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
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        receiptNumber = new javax.swing.JTextField();
        receiptDate = new javax.swing.JTextField();
        receiptTime = new javax.swing.JTextField();
        receiptServicesPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        receiptServices = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        receiptTotalBill = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(315, 2147483647));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMaximumSize(new java.awt.Dimension(315, 32767));
        jPanel1.setMinimumSize(new java.awt.Dimension(315, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(315, 280));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jPanel2.setMinimumSize(new java.awt.Dimension(300, 350));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 0));
        jPanel2.setVerifyInputWhenFocusTarget(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(46, 46, 46));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Client Name:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

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
        jPanel2.add(receiptClientName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 160, 15));

        jLabel4.setBackground(new java.awt.Color(46, 46, 46));
        jLabel4.setText("Pet Name:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

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
        jPanel2.add(receiptPetName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 160, 15));

        jLabel8.setBackground(new java.awt.Color(46, 46, 46));
        jLabel8.setText("Assistant:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

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
        jPanel2.add(receiptSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 160, 15));

        jLabel12.setBackground(new java.awt.Color(46, 46, 46));
        jLabel12.setText("Schedule:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        receiptAssistant.setEditable(false);
        receiptAssistant.setBackground(new java.awt.Color(255, 255, 255));
        receiptAssistant.setBorder(null);
        receiptAssistant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptAssistantActionPerformed(evt);
            }
        });
        jPanel2.add(receiptAssistant, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 160, 15));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Green Modern Veterinary Clinic Logo.png"))); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(320, 53));
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 20, 320, 80));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("===========================================");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 10, 330, -1));

        jLabel15.setText("Time:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 90, -1));

        jLabel16.setText("Receipt No:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, -1));

        jLabel17.setText("Date:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 70, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("-----------------------------------------------------");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 110, 320, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("-----------------------------------------------------");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 210, 320, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("-----------------------------------------------------");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 350, -1));

        receiptNumber.setEditable(false);
        receiptNumber.setBackground(new java.awt.Color(255, 255, 255));
        receiptNumber.setBorder(null);
        receiptNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptNumberActionPerformed(evt);
            }
        });
        jPanel2.add(receiptNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 160, 15));

        receiptDate.setEditable(false);
        receiptDate.setBackground(new java.awt.Color(255, 255, 255));
        receiptDate.setBorder(null);
        jPanel2.add(receiptDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 160, 15));

        receiptTime.setEditable(false);
        receiptTime.setBackground(new java.awt.Color(255, 255, 255));
        receiptTime.setBorder(null);
        jPanel2.add(receiptTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 160, 15));

        receiptServicesPanel.setBackground(new java.awt.Color(255, 255, 255));
        receiptServicesPanel.setMaximumSize(new java.awt.Dimension(300, 32767));
        receiptServicesPanel.setMinimumSize(new java.awt.Dimension(300, 100));
        receiptServicesPanel.setPreferredSize(new java.awt.Dimension(300, 0));

        jLabel7.setBackground(new java.awt.Color(46, 46, 46));
        jLabel7.setText("SERVICES:");

        receiptServices.setEditable(false);
        receiptServices.setBackground(new java.awt.Color(255, 255, 255));
        receiptServices.setColumns(20);
        receiptServices.setRows(5);
        receiptServices.setBorder(null);
        receiptServices.setFocusable(false);

        javax.swing.GroupLayout receiptServicesPanelLayout = new javax.swing.GroupLayout(receiptServicesPanel);
        receiptServicesPanel.setLayout(receiptServicesPanelLayout);
        receiptServicesPanelLayout.setHorizontalGroup(
            receiptServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiptServicesPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(receiptServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(receiptServices, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        receiptServicesPanelLayout.setVerticalGroup(
            receiptServicesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiptServicesPanelLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receiptServices, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(300, 32767));
        jPanel4.setMinimumSize(new java.awt.Dimension(300, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 0));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("-----------------------------------------------------");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Thank you for trusting iPetCare!");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("===========================================");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("-----------------------------------------------------");

        jLabel19.setBackground(new java.awt.Color(46, 46, 46));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("TOTAL BILL:");

        receiptTotalBill.setEditable(false);
        receiptTotalBill.setBackground(new java.awt.Color(255, 255, 255));
        receiptTotalBill.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(receiptTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(receiptTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(receiptServicesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receiptServicesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(330, 735));
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JTextArea receiptServices;
    private javax.swing.JPanel receiptServicesPanel;
    private javax.swing.JTextField receiptTime;
    public javax.swing.JTextField receiptTotalBill;
    // End of variables declaration//GEN-END:variables
}

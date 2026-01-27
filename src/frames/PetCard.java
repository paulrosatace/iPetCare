/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 *
 * @author Paul Adrian
 */
public class PetCard extends JPanel { 
   private int appointmentId;
    private String petName; 
    private String ownerName;
    private String species;
    private String breed;
    private ImageIcon petImage;
    private Color hoverColor = new Color(230, 247, 247);
    private Color defaultColor = Color.WHITE;
    private boolean isHovered = false;
    
    public PetCard(int appointmentId, String petName, String ownerName, String species, String breed) {
        this.appointmentId = appointmentId;
        this.petName = petName;
        this.ownerName = ownerName;
        this.species = species;
        this.breed = breed;
        
        // Set pet image based on species
        setPetImageBySpecies(species);
        
        initComponents();
        setupHoverEffect();
    }
    
    private void setPetImageBySpecies(String species) {
        String imagePath = "/images/";
        
        if (species == null || species.trim().isEmpty()) {
            imagePath += "default_pet.png";
        } else {
            String lowerSpecies = species.toLowerCase().trim();
            
            switch (lowerSpecies) {
                case "dog":
                    imagePath += "dog_default.png";
                    break;
                case "cat":
                    imagePath += "cat_default.png";
                    break;
                case "bird":
                    imagePath += "bird_default.png";
                    break;
                case "fish":
                    imagePath += "fish_default.png";
                    break;
                case "reptile":
                case "lizard":
                case "snake":
                    imagePath += "reptile_default.png";
                    break;
                default:
                    imagePath += "default_pet.png";
                    break;
            }
        }
        
        try {
            petImage = new ImageIcon(getClass().getResource(imagePath));
            // Resize image to fit card
            Image img = petImage.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            petImage = new ImageIcon(img);
        } catch (Exception e) {
            // If image not found, create a placeholder
            petImage = createPlaceholderImage();
        }
    }
    
    private ImageIcon createPlaceholderImage() {
        // Create a simple colored rectangle as placeholder
        Image placeholder = new java.awt.image.BufferedImage(180, 180, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics g = placeholder.getGraphics();
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, 180, 180);
        g.setColor(new Color(150, 150, 150));
        g.drawString("No Image", 60, 90);
        g.dispose();
        return new ImageIcon(placeholder);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(defaultColor);
        setBorder(new CompoundBorder(
            new LineBorder(new Color(0, 51, 51), 2, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        setPreferredSize(new Dimension(250, 350));
        setMaximumSize(new Dimension(250, 350));
        setMinimumSize(new Dimension(250, 350));
        
        // Image panel
        JLabel imageLabel = new JLabel(petImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(new LineBorder(new Color(0, 51, 51), 1));
        add(imageLabel, BorderLayout.NORTH);
        
        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(defaultColor);
        infoPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
        
        // Pet Name
        JLabel nameLabel = new JLabel(petName);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(new Color(7, 122, 125));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Species
        JLabel speciesLabel = new JLabel("Species: " + species);
        speciesLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        speciesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Breed
        JLabel breedLabel = new JLabel("Breed: " + breed);
        breedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        breedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Owner
        JLabel ownerLabel = new JLabel("Owner: " + ownerName);
        ownerLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ownerLabel.setForeground(new Color(46, 46, 46));
        ownerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        infoPanel.add(speciesLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(breedLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(ownerLabel);
        
        add(infoPanel, BorderLayout.CENTER);
        
        // Click instruction
        JLabel clickLabel = new JLabel("Double-click to view details");
        clickLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        clickLabel.setForeground(Color.GRAY);
        clickLabel.setHorizontalAlignment(JLabel.CENTER);
        add(clickLabel, BorderLayout.SOUTH);
    }
    
    private void setupHoverEffect() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                setBackground(hoverColor);
                updateComponentBackground(PetCard.this, hoverColor);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBorder(new CompoundBorder(
                    new LineBorder(new Color(7, 122, 125), 3, true),
                    new EmptyBorder(14, 14, 14, 14)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                setBackground(defaultColor);
                updateComponentBackground(PetCard.this, defaultColor);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setBorder(new CompoundBorder(
                    new LineBorder(new Color(0, 51, 51), 2, true),
                    new EmptyBorder(15, 15, 15, 15)
                ));
            }
        });
    }
    
    private void updateComponentBackground(Container container, Color color) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JPanel) {
                comp.setBackground(color);
                if (comp instanceof Container) {
                    updateComponentBackground((Container) comp, color);
                }
            }
        }
    }
    
    public int getAppointmentId() {
        return appointmentId;
    }
    
    public String getPetName() {
        return petName;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
}




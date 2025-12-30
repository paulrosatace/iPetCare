/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package petcareservicesystem;


//import com.formdev.flatlaf.FlatLightLaf;
import frames.logInFrame;
import frames.homeFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class PetCareServiceSystem {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
     /*   try {
            FlatLightLaf.setup(); // Ensure this is the first UI-related call
        } catch (Exception ex) {
            // Log the error if FlatLaf setup fails
            Logger.getLogger(PetCareServiceSystem.class.getName()).log(Level.SEVERE, "Failed to set FlatLaf Look and Feel", ex);
            // Fallback to default L&F if FlatLaf fails
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception fallbackEx) {
                Logger.getLogger(PetCareServiceSystem.class.getName()).log(Level.SEVERE, "Failed to set cross-platform L&F", fallbackEx);
            }
        }

        /*
         * Create and display the main application frame on the Event Dispatch Thread (EDT).
         * This ensures all UI updates are handled safely.
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new homeFrame().setVisible(true);
            }
        });
    }
}

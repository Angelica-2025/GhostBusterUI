package dev.lanny.ghost_busters.view;

import javax.swing.*;


public class MainFrame extends JFrame {

    public MainFrame() {
        // Configuración de la ventana principal
        setTitle("👻 GhostBusters Asturias - Base de Operaciones");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setResizable(false); 

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}


    

package dev.lanny.ghost_busters.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("👻 GhostBusters Asturias - Base de Operaciones");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Usar JLayeredPane para manejar capas
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1200, 600));
        layeredPane.setBackground(Color.BLACK);
        layeredPane.setOpaque(true); // Asegurar que el fondo negro se muestre

        // Agregar layeredPane al JFrame
        setContentPane(layeredPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}


    

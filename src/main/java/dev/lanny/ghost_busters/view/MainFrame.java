package dev.lanny.ghost_busters.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

public class MainFrame extends JFrame {
    private ImageIcon originalIcon;
    private JLabel backgroundLabel;

    public MainFrame() {
        setTitle("👻 GhostBusters Asturias - Base de Operaciones");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1200, 600));
        layeredPane.setBackground(Color.BLACK);
        layeredPane.setOpaque(true);

        // Cargar y escalar imagen de fondo
        originalIcon = new ImageIcon("src/main/resources/background.png");
        Image backgroundImage = originalIcon.getImage().getScaledInstance(1200, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(backgroundImage);
        backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 1200, 600);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // TÍTULO SUPERIOR
        JLabel titleLabel = new JLabel("👻 GhostBusters Asturias - Base de Operaciones 👻", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setBounds(0, 0, 1200, 80);
        layeredPane.add(titleLabel, JLayeredPane.MODAL_LAYER);

        setContentPane(layeredPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}

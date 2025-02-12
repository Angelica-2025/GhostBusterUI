package dev.lanny.ghost_busters.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;

public class MainFrame extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;

    private JLabel backgroundLabel;

    public MainFrame(HunterController hunterController) {
        setTitle("👻 GhostBusters Asturias - Base de Operaciones");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        layeredPane.setOpaque(true);
        layeredPane.setBackground(Color.BLACK);

        setupBackground(layeredPane);

        JLabel titleLabel = new JLabel("👻 GhostBusters Asturias - Base de Operaciones 👻", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setBounds(0, 0, WIDTH, 80);
        layeredPane.add(titleLabel, JLayeredPane.MODAL_LAYER);

        JPanel buttonPanel = createButtonPanel();
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        setContentPane(layeredPane);
        setVisible(true);
    }

    private void setupBackground(JLayeredPane layeredPane) {
        ImageIcon backgroundIcon = loadImage("background.png");
        if (backgroundIcon != null) {
            Image backgroundImage = backgroundIcon.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
            backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            backgroundLabel.setBounds(0, 0, WIDTH, HEIGHT);
            layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        } else {
            System.err.println("No se pudo cargar la imagen de fondo.");
        }
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 0, WIDTH, HEIGHT);

        JButton captureButton = createStyledButton("📷 Capturar Fantasma", 450, 200, () -> new CaptureGhostFrame());
        JButton listButton = createStyledButton("📜 Ver Lista de Fantasmas", 450, 270, () -> showGhostList());
        JButton deleteButton = createStyledButton("🔍 Eliminar Fantasmas", 450, 340, () -> showDeleteGhosts());
        JButton exitButton = createStyledButton("🚪 Salir", 450, 410, () -> System.exit(0));

        buttonPanel.add(captureButton);
        buttonPanel.add(listButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        return buttonPanel;
    }

    private JButton createStyledButton(String text, int x, int y, Runnable action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 50);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.GREEN);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        button.addActionListener(e -> action.run());

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.BLACK);
                button.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.GREEN);
                button.setBackground(Color.BLACK);
            }
        });

        return button;
    }

    private void showGhostList() {
        JOptionPane.showMessageDialog(this, "Aquí se mostrará la lista de fantasmas.", "Lista de Fantasmas",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showDeleteGhosts() {
        JOptionPane.showMessageDialog(this, "Aquí se gestionará la eliminación de fantasmas.", "Eliminar Fantasmas",
                JOptionPane.WARNING_MESSAGE);
    }

    private ImageIcon loadImage(String filename) {
        try {
            return new ImageIcon(getClass().getClassLoader().getResource(filename));
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + filename);
            return null;
        }
    }

    public static void main(String[] args) {
        HunterModel hunterModel = new HunterModel("Egon Spengler", new ArrayList<>());
        HunterController hunterController = new HunterController(hunterModel);

        SwingUtilities.invokeLater(() -> new MainFrame(hunterController));
    }
}

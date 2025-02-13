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
    private HunterController hunterController;

    private JLabel backgroundLabel;

    public MainFrame(HunterController hunterController) {
        if (hunterController == null) {
            throw new IllegalArgumentException("❌ ERROR: hunterController no puede ser NULL en MainFrame");
        }

        this.hunterController = hunterController;
        setTitle("👻 GhostBusters Asturias - Base de Operaciones");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //layeredPane.setOpaque(true);
        //layeredPane.setBackground(Color.BLACK);

        setupBackground(layeredPane);

        JLabel titleLabel = new JLabel("👻 GhostBusters Asturias - Base de Operaciones 👻", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 0, 0, 160));
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
            JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen de fondo.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.err.println("No se pudo cargar la imagen de fondo.");
        }
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 0, WIDTH, HEIGHT);

        JButton captureButton = createStyledButton("📷 Capturar Fantasma", 450, 200, () -> {
            if (this.hunterController == null) {
                System.err.println("❌ ERROR: hunterController es NULL antes de abrir CaptureGhostFrame");
                return;
            }
            new CaptureGhostFrame(this.hunterController);
        });
        captureButton.setName("captureButton");

        JButton listButton = createStyledButton("📜 Ver Lista de Fantasmas", 450, 270, () -> {

            if (this.hunterController == null) {
                System.err.println("❌ ERROR: hunterController es NULL antes de abrir ListGhostsFrame");
                return;
            }
            new ListGhostsFrame();
        });
        listButton.setName("listButton");

        JButton deleteButton = createStyledButton("🔍 Eliminar Fantasmas", 450, 340, () ->{
            if (this.hunterController == null) {
                System.err.println("❌ ERROR: hunterController es NULL antes de abrir DeleteGhostFrame");
                return;
            }
            new DeleteGhostFrame(this.hunterController).setVisible(true);
        
        });       
        deleteButton.setName("deleteButton");

        JButton exitButton = createStyledButton("🚪 Salir", 450, 410, this::exitApplication);
        exitButton.setName("exitButton");

        buttonPanel.add(captureButton);
        buttonPanel.add(listButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        return buttonPanel;
    }

    private JButton createStyledButton(String text, int x, int y, Runnable action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 50);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(new Color(50, 50, 50));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 180, 180), 2));

        button.addActionListener(e -> action.run());

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 180, 180));
                button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(50, 50, 50));
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

     

    private void exitApplication() {
        dispose(); // Cierra solo la ventana en lugar de terminar la aplicación
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

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(hunterController);
            mainFrame.setVisible(true); // Ahora sí usamos la variable
        });
    }

}

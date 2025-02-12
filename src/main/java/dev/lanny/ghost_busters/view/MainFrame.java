package dev.lanny.ghost_busters.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        // Panel de botones
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 0, 1200, 600);

        // Crear y agregar botones
        JButton captureButton = createStyledButton("📷 Capturar Fantasma", 450, 200);
        JButton listButton = createStyledButton("📜 Ver Lista de Fantasmas", 450, 270);
        JButton deleteButton = createStyledButton("🔍 Eliminar Fantasmas", 450, 340);
        JButton exitButton = createStyledButton("🚪 Salir", 450, 410);

        buttonPanel.add(captureButton);
        buttonPanel.add(listButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        // Acciones de Botones
        captureButton.addActionListener(e -> new CaptureGhostFrame());
        listButton.addActionListener(e -> System.out.println("Abrir lista de fantasmas"));
        deleteButton.addActionListener(e -> System.out.println("Abrir filtro de fantasmas"));
        exitButton.addActionListener(e -> System.exit(0));

        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        setContentPane(layeredPane);
        setVisible(true);
    }

    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 50);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.GREEN);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        // Efecto Hover (cambia color al pasar el ratón)
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}

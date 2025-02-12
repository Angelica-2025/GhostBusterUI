package dev.lanny.ghost_busters.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Random;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.ThreatLevel;


public class CaptureGhostFrame extends JFrame {
    private JTextField nameField;
    private JComboBox<GhostClass> ghostClassComboBox;
    private JComboBox<ThreatLevel> threatLevelComboBox;
    private JTextField abilityField;
    private JTextField dateField;
    private JLabel statusLabel;
    
    private HunterController hunterController;

    public CaptureGhostFrame(HunterController hunterController) {
        this.hunterController = hunterController;

        setTitle("👻 Capturar un Nuevo Fantasma");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel con fondo negro y bordes verdes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.BLACK);

        // Crear campos con estilo
        nameField = createStyledTextField();
        abilityField = createStyledTextField();
        dateField = createStyledTextField();
        dateField.setText(LocalDate.now().toString());

        // Crear JComboBox con estilo
        ghostClassComboBox = createStyledComboBox(GhostClass.values());
        threatLevelComboBox = createStyledComboBox(ThreatLevel.values());

        // Etiquetas en verde
        panel.add(createStyledLabel("👻 Nombre del Fantasma:"));
        panel.add(nameField);
        panel.add(createStyledLabel("👻 Clase del Fantasma:"));
        panel.add(ghostClassComboBox);
        panel.add(createStyledLabel("⚠ Nivel de Peligro:"));
        panel.add(threatLevelComboBox);
        panel.add(createStyledLabel("✨ Habilidad Especial:"));
        panel.add(abilityField);
        panel.add(createStyledLabel("📅 Fecha de Captura (YYYY-MM-DD):"));
        panel.add(dateField);

        // Botón de captura con estilo
        JButton captureButton = createStyledButton("📷 Capturar Fantasma");
        captureButton.addActionListener(new CaptureButtonListener());
        panel.add(captureButton);

        // Etiqueta de estado (errores)
        statusLabel = createStyledLabel("");
        statusLabel.setForeground(Color.RED);
        panel.add(statusLabel);

        add(panel);
        setVisible(true);
    }

    private class CaptureButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText().trim();
            GhostClass ghostClass = (GhostClass) ghostClassComboBox.getSelectedItem();
            ThreatLevel threatLevel = (ThreatLevel) threatLevelComboBox.getSelectedItem();
            String ability = abilityField.getText().trim();
            String captureDate = dateField.getText().trim();

            if (name.isEmpty() || ability.isEmpty()) {
                statusLabel.setText("❌ Nombre y habilidad no pueden estar vacíos.");
                return;
            }

            if (!captureDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                statusLabel.setText("❌ Fecha inválida. Use formato YYYY-MM-DD.");
                return;
            }

            int ectoplasmicAffinity = new Random().nextInt(10) + 1;

            try {
                GhostModel capturedGhost = new GhostModel(name, ghostClass, threatLevel, ability, captureDate);
                hunterController.captureGhost(capturedGhost);
                
                // Mostrar la ventana emergente con los botones de acción
                showGhostDetailsDialog(capturedGhost, ectoplasmicAffinity);
                
                dispose(); // Cierra la ventana de captura después de añadir el fantasma
            } catch (IllegalArgumentException ex) {
                statusLabel.setText("❌ Error: " + ex.getMessage());
            }
        }
    }

    // 🟢 Método para mostrar la ventana emergente con botones de acción
    private void showGhostDetailsDialog(GhostModel ghost, int ectoplasmicAffinity) {
        JDialog dialog = new JDialog(this, "Fantasma Capturado", true);
        dialog.setSize(350, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        panel.setBackground(Color.BLACK);
        
        panel.add(createStyledLabel("✅ ¡Fantasma capturado exitosamente!"));
        panel.add(createStyledLabel("📌 Nombre: " + ghost.getName()));
        panel.add(createStyledLabel("📌 Clase: " + ghost.getGhostClass()));
        panel.add(createStyledLabel("📌 Nivel de Peligro: " + ghost.getThreatLevel()));
        panel.add(createStyledLabel("📌 Habilidad: " + ghost.getSpecialAbility()));
        panel.add(createStyledLabel("📌 Fecha de Captura: " + ghost.getCaptureDate()));
        panel.add(createStyledLabel("📌 Afinidad Ectoplásmica: " + ectoplasmicAffinity + "/10"));

        // Botones de acción
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);

        JButton addAnotherButton = createStyledButton("📜 Añadir Otro Fantasma");
        addAnotherButton.addActionListener(e -> {
            dialog.dispose();
            new CaptureGhostFrame(hunterController); // Abre otra instancia para añadir otro fantasma
        });

        JButton menuButton = createStyledButton("🏠 Volver al Menú");
        menuButton.addActionListener(e -> {
            dialog.dispose();
            new MainFrame(hunterController); // Vuelve al menú principal
        });

        buttonPanel.add(addAnotherButton);
        buttonPanel.add(menuButton);

        panel.add(buttonPanel);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    // 🟢 Métodos de estilo
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.GREEN);
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.GREEN);
        textField.setCaretColor(Color.GREEN);
        textField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        return textField;
    }

    private <T> JComboBox<T> createStyledComboBox(T[] items) {
        JComboBox<T> comboBox = new JComboBox<>(items);
        comboBox.setBackground(Color.BLACK);
        comboBox.setForeground(Color.GREEN);
        comboBox.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        return comboBox;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.GREEN);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLACK);
                button.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.GREEN);
                button.setBackground(Color.BLACK);
            }
        });

        return button;
    }
}

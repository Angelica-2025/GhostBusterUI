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
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.BLACK);

       
        nameField = createStyledTextField();
        abilityField = createStyledTextField();
        dateField = createStyledTextField();
        dateField.setText(LocalDate.now().toString());

        
        ghostClassComboBox = createStyledComboBox(GhostClass.values());
        threatLevelComboBox = createStyledComboBox(ThreatLevel.values());

     
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createStyledLabel("👻 Nombre del Fantasma:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createStyledLabel("👻 Clase del Fantasma:"), gbc);
        gbc.gridx = 1;
        panel.add(ghostClassComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createStyledLabel("⚠ Nivel de Peligro:"), gbc);
        gbc.gridx = 1;
        panel.add(threatLevelComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(createStyledLabel("✨ Habilidad Especial:"), gbc);
        gbc.gridx = 1;
        panel.add(abilityField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(createStyledLabel("📅 Fecha de Captura (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        panel.add(dateField, gbc);

      
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JButton captureButton = createStyledButton("📷 Capturar Fantasma");
        captureButton.addActionListener(new CaptureButtonListener());
        panel.add(captureButton, gbc);

      
        gbc.gridy = 6;
        statusLabel = createStyledLabel("");
        statusLabel.setForeground(Color.RED);
        panel.add(statusLabel, gbc);

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
                showGhostDetailsDialog(capturedGhost, ectoplasmicAffinity);
                dispose();
            } catch (IllegalArgumentException ex) {
                statusLabel.setText("❌ Error: " + ex.getMessage());
            }
        }
    }

    private void showGhostDetailsDialog(GhostModel ghost, int ectoplasmicAffinity) {
        JDialog dialog = new JDialog(this, "Fantasma Capturado", true);
        dialog.setSize(450, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
    
        JPanel panel = new JPanel(new GridLayout(9, 1));
        panel.setBackground(Color.BLACK);
    
        panel.add(createStyledLabel("✅ ¡Fantasma capturado exitosamente!"));
        panel.add(createStyledLabel("📌 Nombre: " + ghost.getName()));
        panel.add(createStyledLabel("📌 Clase: " + ghost.getGhostClass()));
        panel.add(createStyledLabel("📌 Nivel de Peligro: " + ghost.getThreatLevel()));
        panel.add(createStyledLabel("📌 Habilidad: " + ghost.getSpecialAbility()));
        panel.add(createStyledLabel("📌 Fecha de Captura: " + ghost.getCaptureDate()));
        panel.add(createStyledLabel("📌 Afinidad Ectoplásmica: " + ectoplasmicAffinity + "/10"));
    
        // 🔹 Botones de acción
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.BLACK);
    
        JButton addAnotherButton = createStyledButton("📜 Añadir Otro Fantasma");
        addAnotherButton.addActionListener(e -> {
            dialog.dispose();
            new CaptureGhostFrame(hunterController); 
        });
    
        JButton menuButton = createStyledButton("🏠 Volver al Menú");
        menuButton.addActionListener(e -> {
            dialog.dispose();
            new MainFrame(hunterController); 
        });
    
        buttonPanel.add(addAnotherButton);
        buttonPanel.add(menuButton);
        panel.add(buttonPanel);
    
        dialog.add(panel);
        dialog.setVisible(true);
    }
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.GREEN);
        label.setFont(new Font("Arial", Font.BOLD, 16)); 
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(20);
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
        button.setBackground(new Color(0, 150, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18)); // 🔺 Aumentamos tamaño de fuente
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        return button;
    }
}

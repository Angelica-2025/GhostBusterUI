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
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Campos de entrada
        panel.add(new JLabel("👻 Nombre del Fantasma:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("👻 Clase del Fantasma:"));
        ghostClassComboBox = new JComboBox<>(GhostClass.values());
        panel.add(ghostClassComboBox);

        panel.add(new JLabel("⚠ Nivel de Peligro:"));
        threatLevelComboBox = new JComboBox<>(ThreatLevel.values());
        panel.add(threatLevelComboBox);

        panel.add(new JLabel("✨ Habilidad Especial:"));
        abilityField = new JTextField();
        panel.add(abilityField);

        panel.add(new JLabel("📅 Fecha de Captura (YYYY-MM-DD):"));
        dateField = new JTextField(LocalDate.now().toString());
        panel.add(dateField);

        // Botón de captura
        JButton captureButton = new JButton("📷 Capturar Fantasma");
        captureButton.addActionListener(new CaptureButtonListener());
        panel.add(captureButton);

        // Etiqueta de estado
        statusLabel = new JLabel("");
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
                JOptionPane.showMessageDialog(CaptureGhostFrame.this,
                        "✅ ¡Fantasma capturado exitosamente!\n\n" +
                        "📌 Nombre: " + capturedGhost.getName() + "\n" +
                        "📌 Clase: " + capturedGhost.getGhostClass() + "\n" +
                        "📌 Nivel de Peligro: " + capturedGhost.getThreatLevel() + "\n" +
                        "📌 Habilidad: " + capturedGhost.getSpecialAbility() + "\n" +
                        "📌 Fecha de Captura: " + capturedGhost.getCaptureDate() + "\n" +
                        "📌 Afinidad Ectoplásmica: " + ectoplasmicAffinity + "/10",
                        "Fantasma Capturado", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cierra la ventana después de capturar el fantasma
            } catch (IllegalArgumentException ex) {
                statusLabel.setText("❌ Error: " + ex.getMessage());
            }
        }
    }
}

package dev.lanny.ghost_busters.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostModel;

public class ListGhostsFrame extends JFrame {
    private HunterController hunterController;

    public ListGhostsFrame(HunterController hunterController) {
        if (hunterController == null) {
            throw new IllegalArgumentException("❌ ERROR: hunterController no puede ser NULL en ListGhostsFrame");
        }

        this.hunterController = hunterController;
        setTitle("📜 Lista de Fantasmas Capturados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK); // Fondo negro

        // Título
        JLabel titleLabel = new JLabel("📜 Lista de Fantasmas Capturados", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Tabla de fantasmas
        JTable ghostsTable = createGhostsTable();
        JScrollPane scrollPane = new JScrollPane(ghostsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Botón para salir
        JButton backButton = new JButton("🚪 Volver");
        backButton.setFont(new Font("Sans", Font.BOLD, 16));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.blue);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra este frame y vuelve al MainFrame
            }
        });

        // Panel para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK); // Fondo negro
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JTable createGhostsTable() {
        String[] columnNames = {"ID", "Nombre","Peligro", "Clase","Habilidad", "Fecha/Captura"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<GhostModel> ghosts = hunterController.getCapturedGhosts();
        for (GhostModel ghost : ghosts) {
            Object[] row = {
                ghost.getId(),
                ghost.getName(),
                ghost.getThreatLevel()
                //ghost.getGhostClass().toString(),
                //ghost.getSpecialAbility
               // ghost.getCaptureDate().toString()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(Color.blue);
        table.setBackground(Color.black);
        table.setGridColor(Color.GREEN);
        table.setSelectionBackground(Color.blue);
        table.setSelectionForeground(Color.blue);
        table.setRowHeight(25);

        return table;
    }
}

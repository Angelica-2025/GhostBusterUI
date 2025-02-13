package dev.lanny.ghost_busters.view;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListGhostsFrame extends JFrame {
    private HunterController hunterController;
    private JTextArea ghostListArea;
    private JButton listButton;
    private JButton exitButton;

    public ListGhostsFrame(HunterController hunterController) {
        this.hunterController = hunterController;

        setTitle("Lista de Fantasmas Atrapados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());

        ghostListArea = new JTextArea();
        ghostListArea.setBackground(Color.BLACK);
        ghostListArea.setForeground(Color.WHITE);
        ghostListArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ghostListArea.setEditable(false); // No editable
        JScrollPane scrollPane = new JScrollPane(ghostListArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new FlowLayout());

        listButton = new JButton("Listar Fantasmas");
        listButton.setBackground(new Color(0, 255, 0));
        listButton.setForeground(Color.BLACK);
        listButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(listButton);

        exitButton = new JButton("Salir");
        exitButton.setBackground(new Color(0, 255, 0));
        exitButton.setForeground(Color.BLACK);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

    }

    public List<GhostModel> getCapturedGhosts() {
        return hunterController.getCapturedGhosts();
        // List<hunterControler> ghosts = hunterController.getCapturedGhosts();

        // if (ghosts.isEmpty()) {
        // ghostListArea.append("No hay fantasmas capturados.\n");
        // } else {
        // ghostListArea.append("Fantasmas capturados:\n\n");
        // for (GhostModel ghost : ghosts) {
        // ghostListArea.append("ID: " + ghost.getId() + "\n");
        // ghostListArea.append("Nombre: " + ghost.getName() + "\n");
        // ghostListArea.append("Clase: " + ghost.getGhostClass() + "\n");
        // ghostListArea.append("Fecha de captura: " + ghost.getCaptureDate() + "\n");
        // }
        // }
        // }

    }
}

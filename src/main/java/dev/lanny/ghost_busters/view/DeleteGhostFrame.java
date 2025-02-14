package dev.lanny.ghost_busters.view;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeleteGhostFrame extends JFrame {

    // Global Styles
    private static final Color backgroundColor = new Color(34, 34, 34);
    private static final Color textWhite = Color.WHITE;
    private static final Color buttonBlue = new Color(0, 180, 180);
    private static final Color buttonBG = new Color(50, 50, 50);

    private DefaultListModel<String> ghostListModel;
    private JList<String> ghostList;
    private JButton deleteButton;
    private JButton backButton;
    private HunterController hunterController;

    public DeleteGhostFrame(HunterController hunterController) {
        if (hunterController == null) {
            throw new IllegalArgumentException("HunterController cannot be null");
        }
        this.hunterController = hunterController;

        setTitle("Fantasmas Capturadas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set Background Color
        getContentPane().setBackground(backgroundColor);

        // Create the ghost list
        ghostListModel = new DefaultListModel<>();
        ghostList = new JList<>(ghostListModel);
        ghostList.setBackground(backgroundColor);
        ghostList.setForeground(textWhite);
        ghostList.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(ghostList);
        scrollPane.getViewport().setBackground(backgroundColor);

        // "Release Ghost" button
        deleteButton = createStyledButton("Liberar Fantasma");
        deleteButton.addActionListener(e -> {
            String selectedGhost = ghostList.getSelectedValue();
            if (selectedGhost != null) {
                int ghostId = extractGhostId(selectedGhost);

                // Apply styles to JOptionPane
                applyDialogStyles();

                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "Esta seguro de que desea liberar este fantasma?",
                        "Confirmacion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (choice == JOptionPane.YES_OPTION) {
                    boolean isDeleted = hunterController.freedomGhost(ghostId);
                    if (isDeleted) {
                        updateGhostList();
                        JOptionPane.showMessageDialog(this, 
                            "La liberacion del fantasma fue exitosa!", 
                            "Exito", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, 
                            "Fallado de liberar el fantasma!", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                applyDialogStyles();
                JOptionPane.showMessageDialog(this, 
                    "Por favor, seleccione un fantasma para liberar!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // "Back to Menu" button
        backButton = createStyledButton("Volver al Menu");
        backButton.addActionListener(e -> {
            this.dispose(); // Close this window and return to menu
            new MainFrame(hunterController).setVisible(true);
        });

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        updateGhostList(); // Load the ghost list on startup

        // Ensure that closing the window reopens the main menu
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new MainFrame(hunterController).setVisible(true);
            }
        });
    }

    // Update the ghost list
    private void updateGhostList() {
        ghostListModel.clear();
        List<GhostModel> ghosts = hunterController.getCapturedGhosts();
        for (GhostModel ghost : ghosts) {
            ghostListModel.addElement(ghost.getId() + " - " + ghost.getName() + " (" + ghost.getThreatLevel() + ")");
        }
    }

    // Extract the ghost ID from the format "ID - Name"
    private int extractGhostId(String ghostInfo) {
        try {
            return Integer.parseInt(ghostInfo.split(" - ")[0]);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Create a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(buttonBG);
        button.setForeground(textWhite);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(buttonBlue, 2));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(buttonBlue);
                button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(buttonBG);
                button.setForeground(textWhite);
            }
        });

        return button;
    }

    // Apply styles to JOptionPane dialogs
    private void applyDialogStyles() {
        UIManager.put("OptionPane.background", backgroundColor);
        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("OptionPane.messageForeground", textWhite);
        UIManager.put("Button.background", buttonBlue);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.border", BorderFactory.createLineBorder(buttonBlue, 2));
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));
    }
}

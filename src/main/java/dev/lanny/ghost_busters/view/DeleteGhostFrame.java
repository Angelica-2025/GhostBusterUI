package dev.lanny.ghost_busters.view;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.HunterModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteGhostFrame extends JFrame {

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

        setTitle("Captured Ghosts");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the ghost list
        ghostListModel = new DefaultListModel<>();
        ghostList = new JList<>(ghostListModel);
        JScrollPane scrollPane = new JScrollPane(ghostList);

        // Delete button
        deleteButton = new JButton("Release Ghost");
        deleteButton.addActionListener(e -> {
            String selectedGhost = ghostList.getSelectedValue();
            if (selectedGhost != null) {
                int ghostId = extractGhostId(selectedGhost);
                int choice = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to release this ghost?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
);
boolean isDeleted = false;
if (choice == JOptionPane.YES_OPTION) {
    isDeleted = hunterController.freedomGhost(ghostId);
    if (isDeleted) {
        updateGhostList(); // Refresh the list after deletion
        JOptionPane.showMessageDialog(this, 
            "The ghost has been successfully released!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, 
            "Failed to release the ghost!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
                
                if (isDeleted) {
                    updateGhostList(); // Refresh the list after deletion
                    JOptionPane.showMessageDialog(DeleteGhostFrame.this, 
                        "The ghost has been successfully released!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(DeleteGhostFrame.this, 
                        "Failed to release the ghost!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(DeleteGhostFrame.this, 
                    "Please select a ghost to release!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });


         // "Back to Menu" button
         backButton = new JButton("Back to Menu");
         backButton.addActionListener(e -> {
             this.dispose(); // Close this window and return to menu
         });
 
         // Panel for buttons
         JPanel buttonPanel = new JPanel();
         buttonPanel.add(deleteButton);
         buttonPanel.add(backButton);
 

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        updateGhostList(); // Load the ghost list on startup
    }

    // Update the ghost list
    private void updateGhostList() {
        ghostListModel.clear();
        List<GhostModel> ghosts = hunterController.getCapturedGhosts();
        for (GhostModel ghost : ghosts) {
            ghostListModel.addElement(ghost.getId() + " - " + ghost.getName() + " (" + ghost.getGhostClass() + ")"); // ID + name
        }
    }

    // Extract the ghost ID from the format "ID - Name"
    private int extractGhostId(String ghostInfo) {
        try {
            return Integer.parseInt(ghostInfo.split(" - ")[0]);
        } catch (NumberFormatException e) {
            return -1; // Return -1 if parsing fails
        }
    }

    
}

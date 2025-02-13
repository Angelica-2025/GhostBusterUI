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
                boolean isDeleted = hunterController.freedomGhost(ghostId);
                
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

        add(scrollPane, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        updateGhostList(); // Load the ghost list on startup
    }

    // Update the ghost list
    private void updateGhostList() {
        ghostListModel.clear();
        List<GhostModel> ghosts = hunterController.getCapturedGhosts();
        for (GhostModel ghost : ghosts) {
            ghostListModel.addElement(ghost.getId() + " - " + ghost.getName()); // ID + name
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Initializing HunterModel and HunterController...");

                // transfer of hunter's name with an empty ghost list
                List<GhostModel> emptyGhostList = new ArrayList<>();
                HunterModel hunterModel = new HunterModel("Ghostbuster John", emptyGhostList);
                HunterController hunterController = new HunterController(hunterModel);

                System.out.println("Creating GUI window...");
                DeleteGhostFrame frame = new DeleteGhostFrame(hunterController);

                Thread.sleep(500); // delay in case of errors with a flow

                frame.setVisible(true);
                System.out.println("GUI should be visible now!");

            } catch (Exception e) {
                e.printStackTrace(); // show erros in console
            }
        });
    }
}

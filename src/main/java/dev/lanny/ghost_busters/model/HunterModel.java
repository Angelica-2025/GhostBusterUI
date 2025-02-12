package dev.lanny.ghost_busters.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HunterModel {
    private String name;
    private List<GhostModel> capturedGhosts;

    public HunterModel(String name, List<GhostModel> capturedGhosts) {
        this.name = name;
        this.capturedGhosts = capturedGhosts != null ? capturedGhosts : new ArrayList<>();
    }

    public void captureGhost(GhostModel ghost) {
        if (ghost != null) {
            capturedGhosts.add(ghost);
        }
    }

    public List<GhostModel> getCapturedGhosts() {
        return new ArrayList<>(capturedGhosts);
    }

    public List<GhostModel> filterGhostsByClass(GhostClass ghostClass) {
        return capturedGhosts.stream()
                .filter(ghost -> ghost.getGhostClass() == ghostClass)
                .collect(Collectors.toList());
    }

    public List<GhostModel> filterGhostsByMonth(int month, int year) {
        return capturedGhosts.stream()
                .filter(ghost -> {
                    LocalDate date = ghost.getCaptureDate();
                    return date != null && date.getMonthValue() == month && date.getYear() == year;
                })
                .collect(Collectors.toList());
    }

    public boolean deleteGhost(int ghostId) {
        return capturedGhosts.removeIf(ghost -> ghost.getId() == ghostId);
    }

}
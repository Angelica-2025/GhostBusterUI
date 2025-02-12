package dev.lanny.ghost_busters.model;

public enum ThreatLevel {
    LOW("Amenaza de bajo nivel", 1),
    MEDIUM("Amenaza de nivel medio", 2),
    HIGH("Amenaza de nivel alto", 3),
    CRITICAL("Amenaza de nivel crítico", 4);

    private final String description;


    private final int affinityMultiplier;


    ThreatLevel(String description, int affinityMultiplier) {
        this.description = description;
        this.affinityMultiplier = affinityMultiplier;
    }

    public String getDescription() {
        return description;
    }
    public int getAffinityMultiplier() {
        return affinityMultiplier;
    }

    @Override
    public String toString() {
        return name() + " - " + description;
    }
}
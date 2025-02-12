package dev.lanny.ghost_busters.model;

public enum GhostClass {
    CLASS_I(" Manifestación menor", 1),
    CLASS_II("Aparición móvil", 2),
    CLASS_III("Entidad inteligente", 3),
    CLASS_IV("Fantasma histórico", 4),
    CLASS_V("Espíritu antropomorfo", 5),
    CLASS_VI("Espíritu demoníaco", 6),
    CLASS_VII("Entidad ultraterrena", 7);

    private final String description;
    private final int affinityMultiplier;

    GhostClass(String description, int affinityMultiplier) {
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

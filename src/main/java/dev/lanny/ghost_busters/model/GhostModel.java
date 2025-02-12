package dev.lanny.ghost_busters.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class GhostModel {
    private static int idCounter = 1;

    private final int id;
    private  String name;
    private  GhostClass ghostClass;
    private  ThreatLevel threatLevel;
    private  String specialAbility;
    private  LocalDate captureDate;

    public GhostModel( String name,  GhostClass ghostClass,  ThreatLevel threatLevel, String specialAbility,  String captureDate) {
        this.id = idCounter++;
        this.name = validateNotEmpty(name, "El nombre del fantasma no puede estar vacío.");
        this.ghostClass = Objects.requireNonNull(ghostClass, "La clase del fantasma no puede ser nula.");
        this.threatLevel = Objects.requireNonNull(threatLevel, "El nivel de amenaza no puede ser nulo."); 
        this.specialAbility = validateNotEmpty(specialAbility, "La habilidad especial no puede estar vacía.");
        this.captureDate = parseCaptureDate(captureDate);
    }

    public int getId() { return id; }
    public String getName() { return name;}
    public GhostClass getGhostClass() { return ghostClass; }
    public ThreatLevel getThreatLevel() { return threatLevel; }
    public String getSpecialAbility() { return specialAbility; }
    public LocalDate getCaptureDate() { return captureDate; }


    private String validateNotEmpty(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

    private LocalDate parseCaptureDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha debe estar en formato YYYY-MM-DD.");
        }
    }
    
    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GhostModel that = (GhostModel) o;
    return id == that.id; 
}

@Override
public int hashCode() {
    return Objects.hash(id);
}
}

    

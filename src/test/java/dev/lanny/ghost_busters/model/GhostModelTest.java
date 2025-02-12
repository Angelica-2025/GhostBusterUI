package dev.lanny.ghost_busters.model;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GhostModelTest {
    @Test
    @DisplayName("Se valida que un fantasma se crea correctamente con datos válidos")
    public void testGhostCreationSuccess() {
        GhostModel ghost = new GhostModel("Phantom", GhostClass.CLASS_I, ThreatLevel.LOW, "Whispering", "2025-02-10");

        assertThat(ghost.getName(), is("Phantom"));
        assertThat(ghost.getGhostClass(), is(GhostClass.CLASS_I));
        assertThat(ghost.getThreatLevel(), is(ThreatLevel.LOW));
        assertThat(ghost.getSpecialAbility(), is("Whispering"));
        assertThat(ghost.getCaptureDate(), is(LocalDate.parse("2025-02-10")));
    }

    @Test
    @DisplayName("Se valida que no se puede crear un fantasma con un nombre vacío")
    public void testInvalidGhostCreation_EmptyName() {

        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> new GhostModel("", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Haunting", "2025-02-10"));

        assertThat(exception.getMessage(), containsString("El nombre del fantasma no puede estar vacío."));
    }

    @Test
    @DisplayName("Se valida que no se puede crear un fantasma con una fecha inválida")
    public void testInvalidGhostCreation_InvalidDate() {

        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> new GhostModel("Phantom", GhostClass.CLASS_III, ThreatLevel.HIGH, "Screaming", "INVALID_DATE"));

        assertThat(exception.getMessage(), containsString("La fecha debe estar en formato YYYY-MM-DD."));
    }

    @Test
    @DisplayName("Se valida que no se permite un specialAbility vacío")
    public void testInvalidGhostCreation_EmptySpecialAbility() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GhostModel("Phantom", GhostClass.CLASS_III, ThreatLevel.HIGH, "", "2025-02-10");
        });

        assertThat(exception.getMessage(), containsString("La habilidad especial no puede estar vacía."));
    }

    @Test
    @DisplayName("Se valida que no se permite crear un fantasma con una clase nula")
    void testNullGhostClassThrowsException() {

        Exception exception = assertThrows(NullPointerException.class, () -> {
            new GhostModel("Phantom", null, ThreatLevel.HIGH, "Invisibility", "2025-02-07");
        });
        assertEquals("La clase del fantasma no puede ser nula.", exception.getMessage());
    }

    @Test
    @DisplayName("Se valida que no se permite crear un fantasma con un nivel de amenaza nulo")
    void testNullThreatLevelThrowsException() {

        Exception exception = assertThrows(NullPointerException.class, () -> {
            new GhostModel("Phantom", GhostClass.CLASS_VI, null, "Invisibility", "2025-02-07");
        });
        assertEquals("El nivel de amenaza no puede ser nulo.", exception.getMessage());
    }

    @Test
    @DisplayName("Se valida que cada fantasma generado tiene un ID único")
    void testUniqueIDs() {

        GhostModel ghost1 = new GhostModel("Phantom", GhostClass.CLASS_VI, ThreatLevel.HIGH, "Invisibility",
                "2025-02-07");
        GhostModel ghost2 = new GhostModel("Specter", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Phasing", "2025-02-07");
        assertNotEquals(ghost1.getId(), ghost2.getId());
    }

    @Test
    @DisplayName("Se valida que los IDs generados son únicos y consecutivos")
    public void testUniqueIDs_Consecutive() {
        GhostModel ghost1 = new GhostModel("Phantom", GhostClass.CLASS_III, ThreatLevel.HIGH, "Haunting", "2025-02-10");
        GhostModel ghost2 = new GhostModel("Specter", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Phasing", "2025-02-12");

        assertThat(ghost2.getId(), is(ghost1.getId() + 1));
    }

    @Test
    @DisplayName("Se valida que equals funciona correctamente para fantasmas con el mismo ID")
    public void testEquals_SameID() {
        GhostModel ghost1 = new GhostModel("Phantom", GhostClass.CLASS_III, ThreatLevel.HIGH, "Haunting", "2025-02-10");
        GhostModel ghost2 = ghost1;

        assertThat(ghost1.equals(ghost2), is(true));
    }

    @Test
    @DisplayName("Se valida que equals devuelve false para fantasmas con diferentes IDs")
    public void testEquals_DifferentIDs() {
        GhostModel ghost1 = new GhostModel("Phantom", GhostClass.CLASS_III, ThreatLevel.HIGH, "Haunting", "2025-02-10");
        GhostModel ghost2 = new GhostModel("Specter", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Phasing", "2025-02-12");

        assertThat(ghost1.equals(ghost2), is(false));
    }

    @Test
    @DisplayName("Se valida que equals devuelve false para objetos de la misma clase con diferente ID")
    void testEquals_DifferentID() {
        GhostModel ghost1 = new GhostModel("Phantom", GhostClass.CLASS_I, ThreatLevel.MEDIUM, "Haunting", "2025-02-10");
        GhostModel ghost2 = new GhostModel("Specter", GhostClass.CLASS_II, ThreatLevel.HIGH, "Phasing", "2025-02-11");
        boolean result = ghost1.equals(ghost2);
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("Se valida que equals devuelve false cuando el objeto comparado es null")
    void testEquals_NullObject() {
        GhostModel ghost = new GhostModel("Phantom", GhostClass.CLASS_I, ThreatLevel.MEDIUM, "Haunting", "2025-02-10");
        boolean result = ghost.equals(null);
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("Se valida que validateNotEmpty lanza excepción con valor null")
    void testValidateNotEmpty_NullValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GhostModel(null, GhostClass.CLASS_I, ThreatLevel.LOW, "Ability", "2025-02-10");
        });
        assertThat(exception.getMessage(), is("El nombre del fantasma no puede estar vacío."));
    }

    @Test
    @DisplayName("Se valida que validateNotEmpty lanza excepción con valor vacío")
    void testValidateNotEmpty_EmptyValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GhostModel("", GhostClass.CLASS_I, ThreatLevel.LOW, "Ability", "2025-02-10");
        });
        assertThat(exception.getMessage(), is("El nombre del fantasma no puede estar vacío."));
    }

    @Test
    @DisplayName("Se valida que validateNotEmpty lanza excepción con valor de solo espacios en blanco")
    void testValidateNotEmpty_WhitespaceValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GhostModel("   ", GhostClass.CLASS_I, ThreatLevel.LOW, "Ability", "2025-02-10");
        });
        assertThat(exception.getMessage(), is("El nombre del fantasma no puede estar vacío."));
    }

    @Test
    @DisplayName("Se valida que validateNotEmpty acepta valores válidos")
    void testValidateNotEmpty_ValidValue() {
        GhostModel ghost = new GhostModel("Phantom", GhostClass.CLASS_I, ThreatLevel.LOW, "Ability", "2025-02-10");
        assertThat(ghost.getName(), is("Phantom"));
    }

    @Test
    @DisplayName("Se valida que hashCode coincide para objetos iguales")
    public void testHashCode_EqualsConsistency() {
        GhostModel ghost1 = new GhostModel("Phantom", GhostClass.CLASS_III, ThreatLevel.HIGH, "Haunting", "2025-02-10");
        GhostModel ghost2 = ghost1;

        assertThat(ghost1.hashCode(), is(ghost2.hashCode()));
    }

}

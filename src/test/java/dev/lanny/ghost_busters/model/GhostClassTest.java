package dev.lanny.ghost_busters.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GhostClassTest {

    @Test
    @DisplayName("Validar valores de las enumeraciones en GhostClass")
    public void testEnumValues() {
        assertThat(GhostClass.CLASS_I, is(GhostClass.CLASS_I));
        assertThat(GhostClass.CLASS_II, is(GhostClass.CLASS_II));
        assertThat(GhostClass.CLASS_III, is(GhostClass.CLASS_III));
        assertThat(GhostClass.CLASS_IV, is(GhostClass.CLASS_IV));
        assertThat(GhostClass.CLASS_V, is(GhostClass.CLASS_V));
        assertThat(GhostClass.CLASS_VI, is(GhostClass.CLASS_VI));
        assertThat(GhostClass.CLASS_VII, is(GhostClass.CLASS_VII));
    }

    @Test
    @DisplayName("Validar descripción de las clases de fantasmas")
    public void testGetDescription() {
        assertThat(GhostClass.CLASS_I.getDescription(), is(" Manifestación menor"));
        assertThat(GhostClass.CLASS_II.getDescription(), is("Aparición móvil"));
        assertThat(GhostClass.CLASS_III.getDescription(), is("Entidad inteligente"));
        assertThat(GhostClass.CLASS_IV.getDescription(), is("Fantasma histórico"));
        assertThat(GhostClass.CLASS_V.getDescription(), is("Espíritu antropomorfo"));
        assertThat(GhostClass.CLASS_VI.getDescription(), is("Espíritu demoníaco"));
        assertThat(GhostClass.CLASS_VII.getDescription(), is("Entidad ultraterrena"));
    }

    @Test
    @DisplayName("Validar multiplicador de afinidad de las clases de fantasmas")
    public void testGetAffinityMultiplier() {
        assertThat(GhostClass.CLASS_I.getAffinityMultiplier(), is(1));
        assertThat(GhostClass.CLASS_II.getAffinityMultiplier(), is(2));
        assertThat(GhostClass.CLASS_III.getAffinityMultiplier(), is(3));
        assertThat(GhostClass.CLASS_IV.getAffinityMultiplier(), is(4));
        assertThat(GhostClass.CLASS_V.getAffinityMultiplier(), is(5));
        assertThat(GhostClass.CLASS_VI.getAffinityMultiplier(), is(6));
        assertThat(GhostClass.CLASS_VII.getAffinityMultiplier(), is(7));
    }

    @Test
    @DisplayName("Validar método toString de las clases de fantasmas")
    public void testToString() {
        assertThat(GhostClass.CLASS_I.toString(), is("CLASS_I -  Manifestación menor"));
        assertThat(GhostClass.CLASS_II.toString(), is("CLASS_II - Aparición móvil"));
        assertThat(GhostClass.CLASS_III.toString(), is("CLASS_III - Entidad inteligente"));
        assertThat(GhostClass.CLASS_IV.toString(), is("CLASS_IV - Fantasma histórico"));
        assertThat(GhostClass.CLASS_V.toString(), is("CLASS_V - Espíritu antropomorfo"));
        assertThat(GhostClass.CLASS_VI.toString(), is("CLASS_VI - Espíritu demoníaco"));
        assertThat(GhostClass.CLASS_VII.toString(), is("CLASS_VII - Entidad ultraterrena"));
    }
}

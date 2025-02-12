package dev.lanny.ghost_busters.model;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HunterModelTest {
    private HunterModel hunterModel;
    private GhostModel ghost1;
    private GhostModel ghost2;
    private GhostModel ghost3;

    @BeforeEach
    @DisplayName("Configuración inicial de HunterModel para pruebas")
    void setUp() {
        ghost1 = new GhostModel("Espíritu del Mar", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Aparece en tormentas",
                "2025-01-15");
        ghost2 = new GhostModel("Sombra del Monte", GhostClass.CLASS_IV, ThreatLevel.HIGH, "Ecos fantasmales",
                "2025-01-28");
        ghost3 = new GhostModel("Espectro de la Mina", GhostClass.CLASS_VI, ThreatLevel.LOW, "Sombras", "2024-12-05");

        List<GhostModel> capturedGhosts = new ArrayList<>();
        capturedGhosts.add(ghost1);
        capturedGhosts.add(ghost2);
        capturedGhosts.add(ghost3);

        hunterModel = new HunterModel("Egon Spengler", capturedGhosts);
    }

    @Test
    @DisplayName("Validar que se puede capturar un fantasma")
    void testCaptureGhost() {
        hunterModel.captureGhost(ghost1);
        List<GhostModel> capturedGhosts = hunterModel.getCapturedGhosts();
        assertThat(capturedGhosts, hasSize(4));
        assertThat(capturedGhosts.get(0), is(ghost1));
    }

    @Test
    @DisplayName("Validar que se filtran correctamente los fantasmas por clase")
    void testFilterGhostsByClass() {
        List<GhostModel> filteredGhosts = hunterModel.filterGhostsByClass(GhostClass.CLASS_II);

        assertThat(filteredGhosts.size(), is(1));
        assertThat(filteredGhosts.get(0).getName(), is("Espíritu del Mar"));
    }

    @Test
    @DisplayName("Validar que se filtran fantasmas por clase sin coincidencias")
    void testFilterGhostsByClass_NoMatches() {
        List<GhostModel> filteredGhosts = hunterModel.filterGhostsByClass(GhostClass.CLASS_III);

        assertThat(filteredGhosts.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Validar que getCapturedGhosts devuelve una copia de la lista")
    void testGetCapturedGhosts_Immutable() {
        List<GhostModel> capturedGhosts = hunterModel.getCapturedGhosts();
        capturedGhosts.clear();
        assertThat(hunterModel.getCapturedGhosts(), hasSize(3));
    }

    @Test
    @DisplayName("Validar que se inicializa la lista de fantasmas si se pasa null al constructor")
    void testConstructor_NullCapturedGhosts() {
        HunterModel newHunterModel = new HunterModel("New Hunter", null);
        List<GhostModel> capturedGhosts = newHunterModel.getCapturedGhosts();

        assertThat(capturedGhosts.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Filtrar fantasmas por mes y año con resultados")
    public void testFilterGhostsByMonth_WithResults() {
        List<GhostModel> filteredGhosts = hunterModel.filterGhostsByMonth(1, 2025);

        assertThat(filteredGhosts, hasSize(2));
        assertThat(filteredGhosts, contains(ghost1, ghost2));
    }

    @Test
    @DisplayName("Filtrar fantasmas por mes y año sin resultados")
    public void testFilterGhostsByMonth_NoResults() {
        List<GhostModel> filteredGhosts = hunterModel.filterGhostsByMonth(2, 2025);
        assertThat(filteredGhosts, is(empty()));
    }

    @Test
    @DisplayName("Filtrar fantasmas por un mes de un año diferente")
    public void testFilterGhostsByMonth_DifferentYear() {
        List<GhostModel> filteredGhosts = hunterModel.filterGhostsByMonth(1, 2024);
        assertThat(filteredGhosts, is(empty()));
    }

    @Test
    @DisplayName("validar que elimine un fantasma correctamente")
    public void testDeleteGhost_Succes() {
        GhostModel ghost = new GhostModel("Marck", GhostClass.CLASS_III, ThreatLevel.MEDIUM, "volar", "2025-09-02");

        hunterModel.captureGhost(ghost);
        boolean succes = hunterModel.deleteGhost(ghost.getId());
        assertThat(succes, is(true));
        List<GhostModel> capturedGhosts = hunterModel.getCapturedGhosts();
        assertThat(capturedGhosts, not(hasItem(ghost)));
    }
}

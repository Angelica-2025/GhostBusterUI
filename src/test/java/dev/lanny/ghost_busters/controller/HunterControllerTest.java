package dev.lanny.ghost_busters.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.HunterModel;
import dev.lanny.ghost_busters.model.ThreatLevel;

public class HunterControllerTest {

    private HunterModel hunterModel;
    private HunterController hunterController;
    private GhostModel ghost1;
    private GhostModel ghost2;
    private GhostModel ghost3;

    @BeforeEach
    public void setUp() {
        hunterModel = new HunterModel("Carlos", new ArrayList<>());
        hunterController = new HunterController(hunterModel);
        ghost1 = new GhostModel("Marinero", GhostClass.CLASS_I, ThreatLevel.MEDIUM, "Aparece en tormentas",
                "2025-02-03");
        ghost2 = new GhostModel("Specter", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Invisibility", "2025-02-12");
        ghost3 = new GhostModel("Wraith", GhostClass.CLASS_I, ThreatLevel.HIGH, "Ethereal", "2025-02-14");
    }

    @Test
    @DisplayName("Validar que el constructor lanza excepción con HunterModel nulo")
    public void testConstructor_NullHunterModel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new HunterController(null);
        });
        assertThat(exception.getMessage(), is("HunterModel no puede ser nulo"));
    }

    @Test
    @DisplayName("Validar que un fantasma puede ser capturado correctamente")
    public void testCaptureGhost_Success() {
        hunterController.captureGhost(ghost1);

        List<GhostModel> capturedGhosts = hunterModel.getCapturedGhosts();
        assertThat(capturedGhosts, hasSize(1));
        assertThat(capturedGhosts.get(0), is(ghost1));
    }

    @Test
    @DisplayName("Validar que inicialmente no hay fantasmas capturados")
    public void testGetCapturedGhostsInitiallyEmpty() {
        List<GhostModel> capturedGhosts = hunterController.getCapturedGhosts();
        assertThat(capturedGhosts, is(empty()));
    }

    @Test
    @DisplayName("Validar que no se puede capturar un fantasma nulo")
    public void testCaptureGhost_Null() {
        hunterController.captureGhost(null);
        assertThat(hunterController.getCapturedGhosts().isEmpty(), is(true));
    }

    @Test
    @DisplayName("Validar que los fantasmas capturados aparecen en la lista")
    public void testGetCapturedGhostsAfterCapture() {
        GhostModel ghost = new GhostModel("Marinero", GhostClass.CLASS_I, ThreatLevel.MEDIUM, "Aparece en tormentas",
                "2025-02-03");
        hunterModel.captureGhost(ghost);
        List<GhostModel> capturedGhosts = hunterController.getCapturedGhosts();
        assertThat(capturedGhosts, contains(ghost));
    }

    @Test
    @DisplayName("Validar que se pueden filtrar fantasmas por clase con coincidencias")
    public void testFilterGhostsByClass() {

        hunterController.captureGhost(ghost1);
        hunterController.captureGhost(ghost3);

        List<GhostModel> filteredGhosts = hunterController.filterGhostsByClass(GhostClass.CLASS_I);
        assertThat(filteredGhosts.size(), is(2));
        assertThat(filteredGhosts.get(0).getName(), is("Marinero"));
        assertThat(filteredGhosts.get(1).getName(), is("Wraith"));
    }

    @Test
    @DisplayName("Validar que filtrar fantasmas por clase sin coincidencias devuelve una lista vacía")
    public void testFilterGhostsByClass_NoMatches() {

        List<GhostModel> filteredGhosts = hunterController.filterGhostsByClass(GhostClass.CLASS_III);
        assertThat(filteredGhosts.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Validar que filtrar fantasmas por clase en una lista vacía devuelve lista vacía")
    public void testFilterGhostsByClass_EmptyList() {
        List<GhostModel> filteredGhosts = hunterController.filterGhostsByClass(GhostClass.CLASS_I);
        assertThat(filteredGhosts.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Validar filtrado de fantasmas por mes y año")
    public void testFilterGhostsByMonth_WithResults() {

        GhostModel ghost1 = new GhostModel("Espectro del Faro", GhostClass.CLASS_I, ThreatLevel.MEDIUM, "Luz fantasma",
                "2025-02-10");
        GhostModel ghost2 = new GhostModel("Sombras del Castillo", GhostClass.CLASS_III, ThreatLevel.HIGH,
                "Ecos de la historia", "2025-02-15");
        GhostModel ghost3 = new GhostModel("Fantasma de la Mina", GhostClass.CLASS_VI, ThreatLevel.LOW,
                "Aparición espectral", "2024-12-05");

        hunterController.captureGhost(ghost1);
        hunterController.captureGhost(ghost2);
        hunterController.captureGhost(ghost3);

        List<GhostModel> filteredGhosts = hunterController.filterGhostsByMonth(2, 2025);

        assertThat(filteredGhosts, hasSize(2));
        assertThat(filteredGhosts.get(0).getName(), is("Espectro del Faro"));
        assertThat(filteredGhosts.get(1).getName(), is("Sombras del Castillo"));
    }

    @Test
    @DisplayName("Validar que no devuelve resultados cuando no hay fantasmas en el mes dado")
    public void testFilterGhostsByMonth_NoResults() {
        List<GhostModel> filteredGhosts = hunterController.filterGhostsByMonth(10, 2026);
        assertThat(filteredGhosts.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Validar que se elimina satisfactoriamente")
    public void testDelete_Succes() {
        hunterController.captureGhost(ghost1);
        boolean succes = hunterController.freedomGhost(ghost1.getId());
        assertThat(succes, is(true));
        assertThat(hunterController.getCapturedGhosts(), not(contains(ghost1)));
    }

}

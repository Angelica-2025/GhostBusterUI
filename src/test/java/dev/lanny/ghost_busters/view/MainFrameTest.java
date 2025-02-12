package dev.lanny.ghost_busters.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;

public class MainFrameTest {
    private FrameFixture window;

    @BeforeEach
    void setUp() throws InterruptedException {
        MainFrame mainFrame = GuiActionRunner.execute(() -> new MainFrame(
                new HunterController(new HunterModel("Egon", new ArrayList<>()))));
        window = new FrameFixture(mainFrame);
        window.show();
        Thread.sleep(500);
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }

    @Test
    void shouldCreateMainFrame() {
        assertNotNull(window);
        window.requireVisible();
        window.requireTitle("👻 GhostBusters Asturias - Base de Operaciones");
    }

    @Test
    void shouldContainAllButtons() {
        window.requireVisible();
        window.button("captureButton").requireVisible();
        window.button("listButton").requireVisible();
        window.button("deleteButton").requireVisible();
        window.button("exitButton").requireVisible();
    }

    @Test
    void shouldTriggerCaptureButtonAction() {
        window.button("captureButton").click();

        // Aquí puedes agregar una verificación adicional si la ventana de captura
        // realmente se abre.
        // Por ejemplo:
        // assertTrue(isCaptureWindowOpen());
    }

}

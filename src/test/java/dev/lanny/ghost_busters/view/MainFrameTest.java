package dev.lanny.ghost_busters.view;

import java.util.ArrayList;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;

public class MainFrameTest {
   private FrameFixture window;

    @BeforeEach
    void setUp() throws InterruptedException {
        MainFrame mainFrame = GuiActionRunner.execute(() -> new MainFrame(
            new HunterController(new HunterModel("Egon", new ArrayList<>()))
        ));
        window = new FrameFixture(mainFrame);
        window.show();
        Thread.sleep(500);
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }
}

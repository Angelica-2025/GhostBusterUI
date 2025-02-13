package dev.lanny.ghost_busters.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;
import org.junit.jupiter.api.DisplayName;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MainFrameTest {

    private HunterController hunterController;
    private MainFrame mainFrame;

    @BeforeEach
    @DisplayName("Se Inicializa MainFrame antes de cada test")
    void setUp() {
        HunterModel hunterModel = new HunterModel("Egon Spengler", new ArrayList<>());
        hunterController = new HunterController(hunterModel);
        mainFrame = new MainFrame(hunterController);
    }

    @Test
    @DisplayName("Validad que  lanza  una excepción si el controlador es null")
    void testNull_HunterController() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new MainFrame(null));
        assertEquals("❌ ERROR: hunterController no puede ser NULL en MainFrame", exception.getMessage());
    }

    @Test
    @DisplayName("Verificar que la ventana principal se crea correctamente")
    void testMainFrameCreation() {
        assertNotNull(mainFrame, "La ventana principal no debería ser nula.");
    }

    @Test
    @DisplayName("Verifica que la ventana tiene el título correcto")
    void testWindowTitle() {
        assertEquals("👻 GhostBusters Asturias - Base de Operaciones", mainFrame.getTitle(),
                "El título de la ventana no coincide con el esperado.");
    }

    @Test
    @DisplayName("Verifica que la ventana tiene el tamaño correcto")
    void testWindowSize() {
        assertEquals(1200, mainFrame.getWidth(), "El ancho del frame no es el esperado.");
        assertEquals(600, mainFrame.getHeight(), "La altura del frame no es la esperada.");
    }

    @Test
    @DisplayName("Verifica que los botones están presentes en la interfaz")
    void testButtonsExist() {
        assertNotNull(findButton("📷 Capturar Fantasma"), "El botón de capturar fantasma no está presente.");
        assertNotNull(findButton("📜 Ver Lista de Fantasmas"), "El botón de ver lista de fantasmas no está presente.");
        assertNotNull(findButton("🔍 Eliminar Fantasmas"), "El botón de eliminar fantasmas no está presente.");
        assertNotNull(findButton("🚪 Salir"), "El botón de salir no está presente.");
    }

    @Test
    @DisplayName("Verifica que los botones tienen las acciones correctamente configuradas")
    void testButtonsActions() {
        JButton captureButton = findButton("📷 Capturar Fantasma");
        JButton listButton = findButton("📜 Ver Lista de Fantasmas");
        JButton deleteButton = findButton("🔍 Eliminar Fantasmas");
        JButton exitButton = findButton("🚪 Salir");

        assertNotNull(captureButton.getActionListeners(), "El botón de capturar no tiene acción.");
        assertNotNull(listButton.getActionListeners(), "El botón de ver lista no tiene acción.");
        assertNotNull(deleteButton.getActionListeners(), "El botón de eliminar no tiene acción.");
        assertNotNull(exitButton.getActionListeners(), "El botón de salir no tiene acción.");
    }

    // Método auxiliar para buscar botones dentro del MainFrame por nombre
    private JButton findButton(String text) {
        for (Component comp : mainFrame.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                for (Component subComp : ((JPanel) comp).getComponents()) {
                    if (subComp instanceof JButton && text.equals(((JButton) subComp).getText())) {
                        return (JButton) subComp;
                    }
                }
            }
        }
        return null;
    }

}

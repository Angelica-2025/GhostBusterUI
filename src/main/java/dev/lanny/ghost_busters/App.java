package dev.lanny.ghost_busters;

import java.util.ArrayList;
import java.util.Scanner;

import dev.lanny.ghost_busters.view.MainView;
import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;

public final class App {

    private App() {

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HunterModel hunterModel = new HunterModel("Egon Spengler", new ArrayList<>());
        HunterController hunterController = new HunterController(hunterModel);
        MainView mainView = new MainView(hunterController, scanner);

        try {
            mainView.showMainMenu();
        } catch (Exception e) {
            System.err.println("❌ Se ha producido un error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("🔚 Programa finalizado. ¡Nos vemos en la próxima cacería paranormal!");
        }
    }
}

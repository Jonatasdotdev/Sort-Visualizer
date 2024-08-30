package src.main.java.sortvisualiser;

import src.main.java.sortvisualiser.screens.MainMenuScreen;
import src.main.java.sortvisualiser.screens.Screen;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.ArrayList;

/**
 * A classe MainApp é a aplicação principal para o visualizador de algoritmos de ordenação.
 * Ela gerencia a janela principal e as telas (screens) que serão exibidas.
 */
public class MainApp {
    private final JFrame window;

    public static final int WIN_WIDTH = 1280; // Largura da janela
    public static final int WIN_HEIGHT = 720; // Altura da janela

    private final ArrayList<Screen> screens; // Lista de telas gerenciadas pela aplicação

    /**
     * Construtor da classe MainApp.
     * Inicializa a janela e a lista de telas.
     */
    public MainApp() {
        screens = new ArrayList<>();
        window = new JFrame("Sort Visualiser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIN_WIDTH, WIN_HEIGHT);
        window.setVisible(true);
    }

    /**
     * Retorna a tela atual que está sendo exibida na janela.
     *
     * @return A tela atual.
     */
    public Screen getCurrentScreen() {
        return screens.get(screens.size() - 1);
    }

    /**
     * Adiciona uma nova tela à aplicação e a exibe na janela.
     *
     * @param screen A tela a ser adicionada.
     */
    public void pushScreen(Screen screen) {
        if (!screens.isEmpty()) {
            window.remove(getCurrentScreen());
        }
        screens.add(screen);
        window.setContentPane(screen);
        window.validate();
        screen.onOpen();
    }

    /**
     * Remove a tela atual e retorna à tela anterior.
     * Se não houver telas anteriores, a janela é fechada.
     */
    public void popScreen() {
        if (!screens.isEmpty()) {
            Screen prev = getCurrentScreen();
            screens.remove(prev);
            window.remove(prev);
            if (!screens.isEmpty()) {
                Screen current = getCurrentScreen();
                window.setContentPane(current);
                window.validate();
                current.onOpen();
            } else {
                window.dispose();
            }
        }
    }

    /**
     * Inicia a aplicação, exibindo o menu principal.
     */
    public void start() {
        pushScreen(new MainMenuScreen(this));
        window.pack();
    }

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String... args) {
        System.setProperty("sun.java2d.opengl", "true");
        SwingUtilities.invokeLater(() -> {
            new MainApp().start();
        });
    }
}

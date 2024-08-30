package src.main.java.sortvisualiser.screens;

import static src.main.java.sortvisualiser.MainApp.WIN_HEIGHT;
import static src.main.java.sortvisualiser.MainApp.WIN_WIDTH;

import java.awt.Dimension;
import javax.swing.JPanel;
import src.main.java.sortvisualiser.MainApp;

/**
 * A classe abstrata Screen representa uma tela no aplicativo de visualização de algoritmos de ordenação.
 * Ela estende JPanel e fornece uma estrutura básica para as telas do aplicativo, incluindo
 * a definição do tamanho preferido e um método para ações ao abrir a tela.
 */
public abstract class Screen extends JPanel {
    protected MainApp app; // Instância do aplicativo principal

    /**
     * Construtor da classe Screen.
     * Inicializa a tela com a instância do aplicativo fornecida.
     *
     * @param app A instância do aplicativo principal.
     */
    public Screen(MainApp app) {
        this.app = app;
    }

    /**
     * Retorna o tamanho preferido da tela.
     * O tamanho é definido pelas constantes WIN_WIDTH e WIN_HEIGHT.
     *
     * @return O tamanho preferido da tela como um objeto Dimension.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIN_WIDTH, WIN_HEIGHT);
    }

    /**
     * Método abstrato a ser chamado quando a tela for aberta.
     * As subclasses devem implementar esse método para definir o comportamento desejado.
     */
    public abstract void onOpen();
}

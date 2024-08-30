package src.main.java.sortvisualiser.screens;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import src.main.java.sortvisualiser.MainApp;
import src.main.java.sortvisualiser.SortArray;
import src.main.java.sortvisualiser.algoritimos.UiSortAlgoritimo;

/**
 * A classe SortingVisualiserScreen é responsável por exibir a tela de visualização
 * dos algoritmos de ordenação. Ela configura a interface gráfica do usuário (GUI)
 * e executa os algoritmos de ordenação selecionados em uma fila.
 */
public final class SortingVisualiserScreen extends Screen {
    private final SortArray sortArray; // Componente que representa a visualização do array a ser ordenado
    private final ArrayList<UiSortAlgoritimo> sortQueue; // Lista de algoritmos de ordenação a serem executados

    /**
     * Construtor da classe SortingVisualiserScreen.
     * Inicializa a tela de visualização de algoritmos com os algoritmos fornecidos
     * e configura a GUI.
     *
     * @param algorithms A lista de algoritmos de ordenação a serem executados.
     * @param app A instância do aplicativo principal.
     */
    public SortingVisualiserScreen(ArrayList<UiSortAlgoritimo> algorithms, MainApp app) {
        super(app);
        setLayout(new BorderLayout());
        sortArray = new SortArray(false); // Inicializa o componente de visualização de array
        add(sortArray, BorderLayout.CENTER); // Adiciona o componente à tela
        sortQueue = algorithms; // Armazena a lista de algoritmos
    }

    /**
     * Pausa a execução por um segundo.
     */
    private void longSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Embaralha o array e aguarda a execução do tempo de pausa.
     */
    private void shuffleAndWait() {
        sortArray.shuffle(); // Embaralha os elementos do array
        sortArray.resetColours(); // Reseta as cores dos elementos
        longSleep(); // Pausa a execução
    }

    @Override
    public void onOpen() {
        // Bloqueia a Event Dispatch Thread e precisa rodar em uma worker thread.

        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Thread.sleep(250); // Pausa inicial antes de começar a execução dos algoritmos
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                for (UiSortAlgoritimo algorithm : sortQueue) {
                    shuffleAndWait(); // Embaralha e aguarda

                    sortArray.setName(algorithm.getName()); // Define o nome do algoritmo na visualização
                    sortArray.setAlgorithm(algorithm); // Define o algoritmo a ser executado

                    algorithm.runSort(sortArray); // Executa o algoritmo de ordenação
                    sortArray.resetColours(); // Reseta as cores após a execução
                    sortArray.highlightArray(); // Destaca o array ordenado
                    sortArray.resetColours(); // Reseta as cores novamente
                    longSleep(); // Pausa a execução
                }
                return null; // Retorna null após a conclusão
            }

            @Override
            public void done() {
                app.popScreen(); // Remove a tela de visualização após a conclusão
            }
        };

        swingWorker.execute(); // Executa a tarefa em segundo plano
    }
}

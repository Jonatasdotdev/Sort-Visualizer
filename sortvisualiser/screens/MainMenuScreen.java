package src.main.java.sortvisualiser.screens;

import src.main.java.sortvisualiser.MainApp;
import src.main.java.sortvisualiser.algoritimos.BubbleSort;
import src.main.java.sortvisualiser.algoritimos.QuickSort;
import src.main.java.sortvisualiser.algoritimos.SelectionSort;
import src.main.java.sortvisualiser.algoritimos.UiSortAlgoritimo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * A classe MainMenuScreen representa a tela principal do menu do visualizador de algoritmos de ordenação.
 * Ela permite que o usuário selecione quais algoritmos deseja visualizar e inicia a tela de visualização
 * ao clicar no botão de início.
 */
public final class MainMenuScreen extends Screen {
    private static final Color BACKGROUND_COLOUR = Color.DARK_GRAY; // Cor de fundo da tela
    private final ArrayList<AlgorithmCheckBox> checkBoxes; // Lista de caixas de seleção para os algoritmos
    private final MainApp app; // Instância do aplicativo principal

    /**
     * Construtor da classe MainMenuScreen.
     * Inicializa a tela do menu principal e configura a interface gráfica.
     *
     * @param app A instância do aplicativo principal.
     */
    public MainMenuScreen(MainApp app) {
        super(app); // Certifique-se de chamar o construtor da classe base
        this.app = app;
        checkBoxes = new ArrayList<>();
        setUpGUI();
    }

    /**
     * Adiciona uma caixa de seleção para um algoritmo de ordenação ao painel fornecido.
     *
     * @param algorithm O algoritmo a ser associado à caixa de seleção.
     * @param panel     O painel onde a caixa de seleção será adicionada.
     */
    private void addCheckBox(UiSortAlgoritimo algorithm, JPanel panel) {
        JCheckBox box = new JCheckBox(algorithm.getName(), true);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setBackground(BACKGROUND_COLOUR);
        box.setForeground(Color.WHITE);
        checkBoxes.add(new AlgorithmCheckBox(algorithm, box));
        panel.add(box);
    }

    /**
     * Inicializa o layout do painel para usar um BoxLayout com orientação vertical.
     *
     * @param p O painel a ser inicializado.
     */
    private void initContainer(JPanel p) {
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBackground(BACKGROUND_COLOUR);
    }

    /**
     * Configura a interface gráfica da tela principal do menu.
     */
    public void setUpGUI() {
        JPanel sortAlgorithmContainer = new JPanel();
        JPanel optionsContainer = new JPanel();
        JPanel outerContainer = new JPanel();
        initContainer(this);
        initContainer(optionsContainer);
        initContainer(sortAlgorithmContainer);

        outerContainer.setBackground(BACKGROUND_COLOUR);
        outerContainer.setLayout(new BoxLayout(outerContainer, BoxLayout.LINE_AXIS));

        sortAlgorithmContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCheckBox(new BubbleSort(), sortAlgorithmContainer);
        addCheckBox(new SelectionSort(), sortAlgorithmContainer);
        addCheckBox(new QuickSort(), sortAlgorithmContainer);

        JButton startButton = new JButton("Begin Visual Sorter");
        startButton.addActionListener((ActionEvent e) -> {
            ArrayList<UiSortAlgoritimo> algorithms = new ArrayList<>();
            for (AlgorithmCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    algorithms.add(cb.getAlgorithm());
                }
            }
            app.pushScreen(
                    new SortingVisualiserScreen(
                            algorithms,
                            app
                    ));
        });
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        outerContainer.add(optionsContainer);
        outerContainer.add(Box.createRigidArea(new Dimension(5, 0)));
        outerContainer.add(sortAlgorithmContainer);

        int gap = 15;
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(outerContainer);
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(startButton);
    }

    /**
     * Ação a ser executada quando a tela é aberta.
     * Desmarca todas as caixas de seleção.
     */
    @Override
    public void onOpen() {
        checkBoxes.forEach((box) -> {
            box.unselect();
        });
    }

    /**
     * Classe interna que representa uma caixa de seleção para um algoritmo de ordenação.
     */
    private class AlgorithmCheckBox {
        private final UiSortAlgoritimo algorithm; // Algoritmo associado à caixa de seleção
        private final JCheckBox box; // Caixa de seleção

        /**
         * Construtor da classe AlgorithmCheckBox.
         *
         * @param algorithm O algoritmo associado.
         * @param box       A caixa de seleção.
         */
        public AlgorithmCheckBox(UiSortAlgoritimo algorithm, JCheckBox box) {
            this.algorithm = algorithm;
            this.box = box;
            this.box.setText(algorithm.getName());
        }

        /**
         * Desmarca a caixa de seleção.
         */
        public void unselect() {
            box.setSelected(false);
        }

        /**
         * Verifica se a caixa de seleção está marcada.
         *
         * @return true se a caixa de seleção estiver marcada, false caso contrário.
         */
        public boolean isSelected() {
            return box.isSelected();
        }

        /**
         * Retorna o algoritmo associado à caixa de seleção.
         *
         * @return O algoritmo associado.
         */
        public UiSortAlgoritimo getAlgorithm() {
            return algorithm;
        }
    }
}

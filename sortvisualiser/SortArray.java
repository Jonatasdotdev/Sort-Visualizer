package src.main.java.sortvisualiser;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import src.main.java.sortvisualiser.algoritimos.UiSortAlgoritimo;

/**
 * A classe SortArray é um painel que visualiza um array de barras, representando os valores
 * de um algoritmo de ordenação. Ela permite a manipulação e visualização das mudanças no array
 * durante a execução do algoritmo.
 */
public class SortArray extends JPanel {
    public static final int DEFAULT_WIN_WIDTH = 1280; // Largura padrão da janela
    public static final int DEFAULT_WIN_HEIGHT = 720; // Altura padrão da janela
    private static final int DEFAULT_BAR_WIDTH = 5; // Largura padrão das barras

    /**
     * Percentagem do painel que as barras vão consumir com base no número original de barras.
     * Cada barra tem 2x sua própria altura em um painel de 720 pixels de altura.
     */
    private static final double BAR_HEIGHT_PERCENT = 512.0 / 720.0;
    private static final int NUM_BARS = DEFAULT_WIN_WIDTH / DEFAULT_BAR_WIDTH; // Número de barras

    private final int[] array; // Array que contém os valores a serem ordenados
    private final int[] barColours; // Array que contém as cores das barras
    private int spinnerValue = 0; // Valor do spinner para atraso do algoritmo
    public String algorithmName = ""; // Nome do algoritmo atual
    private UiSortAlgoritimo algorithm; // Instância do algoritmo de ordenação
    private long algorithmDelay = 0; // Atraso do algoritmo em milissegundos

    private JSpinner spinner; // Componente spinner para ajustar o atraso do algoritmo
    private int arrayChanges = 0; // Número de mudanças no array

    /**
     * Construtor da classe SortArray.
     * Inicializa o painel, cria o array de barras e configura o spinner para o atraso do algoritmo.
     *
     * @param b Parâmetro booleano (não utilizado no código atual).
     */
    public SortArray(boolean b) {
        setBackground(Color.DARK_GRAY);
        array = new int[NUM_BARS];
        barColours = new int[NUM_BARS];
        for (int i = 0; i < NUM_BARS; i++) {
            array[i] = i;
            barColours[i] = 0;
        }
        spinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        spinner.addChangeListener((event) -> {
            algorithmDelay = (Integer) spinner.getValue();
            algorithm.setDelay(algorithmDelay);
        });
        add(spinner, BorderLayout.LINE_START);
    }

    /**
     * Retorna o tamanho do array.
     *
     * @return O tamanho do array.
     */
    public int arraySize() {
        return array.length;
    }

    /**
     * Retorna o valor no índice especificado do array.
     *
     * @param index O índice do valor a ser retornado.
     * @return O valor no índice especificado.
     */
    public int getValue(int index) {
        return array[index];
    }

    /**
     * Retorna o valor máximo do array.
     *
     * @return O valor máximo do array ou Integer.MIN_VALUE se o array estiver vazio.
     */
    public int getMaxValue() {
        return Arrays.stream(array).max().orElse(Integer.MIN_VALUE);
    }

    /**
     * Finaliza a atualização do painel, executando o repaint e aguardando um tempo especificado.
     *
     * @param value          O valor a ser usado na atualização.
     * @param millisecondDelay O atraso em milissegundos.
     * @param isStep        Indica se a atualização é um passo no algoritmo.
     */
    private void finaliseUpdate(int value, long millisecondDelay, boolean isStep) {
        repaint();
        try {
            Thread.sleep(millisecondDelay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        if (isStep) {
            arrayChanges++;
        }
    }

    /**
     * Troca os valores em dois índices no array e atualiza as cores das barras trocadas,
     * usando finaliseUpdate para refletir a troca na interface.
     *
     * @param firstIndex      O primeiro índice a ser trocado.
     * @param secondIndex     O segundo índice a ser trocado.
     * @param millisecondDelay O atraso em milissegundos.
     * @param isStep          Indica se a troca é um passo no algoritmo.
     */
    public void swap(int firstIndex, int secondIndex, long millisecondDelay, boolean isStep) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;

        barColours[firstIndex] = 100;
        barColours[secondIndex] = 100;

        finaliseUpdate((array[firstIndex] + array[secondIndex]) / 2, millisecondDelay, isStep);
    }

    /**
     * Atualiza um valor em um índice específico do array e atualiza a cor da barra correspondente.
     *
     * @param index          O índice a ser atualizado.
     * @param value          O novo valor.
     * @param millisecondDelay O atraso em milissegundos.
     * @param isStep        Indica se a atualização é um passo no algoritmo.
     */
    public void updateSingle(int index, int value, long millisecondDelay, boolean isStep) {
        array[index] = value;
        barColours[index] = 100;

        finaliseUpdate(value, millisecondDelay, isStep);
        repaint();
    }

    /**
     * Embaralha os valores no array usando um gerador de números aleatórios.
     */
    public void shuffle() {
        arrayChanges = 0;
        Random rng = new Random();
        for (int i = 0; i < arraySize(); i++) {
            int swapWithIndex = rng.nextInt(arraySize() - 1);
            swap(i, swapWithIndex, 5, false);
        }
        arrayChanges = 0;
    }

    /**
     * Destaca todos os valores do array atualizando suas cores.
     */
    public void highlightArray() {
        for (int i = 0; i < arraySize(); i++) {
            updateSingle(i, getValue(i), 5, false);
        }
    }

    /**
     * Define o tamanho preferido do painel.
     *
     * @return O tamanho preferido do painel.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIN_WIDTH, DEFAULT_WIN_HEIGHT);
    }

    /**
     * Reseta as cores das barras para a cor padrão.
     */
    public void resetColours() {
        for (int i = 0; i < NUM_BARS; i++) {
            barColours[i] = 0;
        }
        repaint();
    }

    /**
     * Desenha o array no painel.
     *
     * @param g O objeto Graphics utilizado para desenhar.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D panelGraphics = (Graphics2D) g.create();

        try {
            Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
            renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            panelGraphics.addRenderingHints(renderingHints);
            panelGraphics.setColor(Color.WHITE);
            panelGraphics.setFont(new Font("Monospaced", Font.BOLD, 20));
            panelGraphics.drawString(" Current algorithm: " + algorithmName, 10, 30);
            panelGraphics.drawString("Current step delay: " + algorithmDelay + "ms", 10, 55);
            panelGraphics.drawString("     Array Changes: " + arrayChanges, 10, 80);

            drawBars(panelGraphics);
        } finally {
            panelGraphics.dispose();
        }
    }

    /**
     * Desenha as barras representando os valores do array.
     *
     * @param panelGraphics O objeto Graphics2D utilizado para desenhar as barras.
     */
    private void drawBars(Graphics2D panelGraphics) {
        int barWidth = getWidth() / NUM_BARS;
        int bufferedImageWidth = barWidth * NUM_BARS;
        int bufferedImageHeight = getHeight();

        if (bufferedImageHeight > 0 && bufferedImageWidth > 0) {
            if (bufferedImageWidth < 256) {
                bufferedImageWidth = 256;
            }

            double maxValue = getMaxValue();

            BufferedImage bufferedImage = new BufferedImage(bufferedImageWidth, bufferedImageHeight, BufferedImage.TYPE_INT_ARGB);
            makeBufferedImageTransparent(bufferedImage);
            Graphics2D bufferedGraphics = null;
            try {
                bufferedGraphics = bufferedImage.createGraphics();

                for (int x = 0; x < NUM_BARS; x++) {
                    double currentValue = getValue(x);
                    double percentOfMax = currentValue / maxValue;
                    double heightPercentOfPanel = percentOfMax * BAR_HEIGHT_PERCENT;
                    int height = (int) (bufferedImageHeight * heightPercentOfPanel);
                    int y = bufferedImageHeight - height;

                    int barColor = barColours[x];
                    Color barFillColor;
                    if (barColor == 0) {
                        barFillColor = new Color(255, 255, 255);
                    } else if (barColor == 100) {
                        barFillColor = new Color(255, 0, 0);
                    } else {
                        barFillColor = new Color(0, 255, 0);
                    }

                    bufferedGraphics.setColor(barFillColor);
                    bufferedGraphics.fillRect(x * barWidth, y, barWidth, height);
                }

                panelGraphics.drawImage(bufferedImage, 0, 0, null);
            } finally {
                if (bufferedGraphics != null) {
                    bufferedGraphics.dispose();
                }
            }
        }
    }

    /**
     * Torna a imagem do buffer transparente.
     *
     * @param bufferedImage A imagem a ser tornada transparente.
     */
    private void makeBufferedImageTransparent(BufferedImage bufferedImage) {
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g2d.setComposite(AlphaComposite.Src);
        g2d.dispose();
    }

    /**
     * Define o algoritmo de ordenação a ser utilizado e o seu nome.
     *
     * @param algorithm O algoritmo de ordenação a ser definido.
     */
    public void setAlgorithm(UiSortAlgoritimo algorithm) {
        this.algorithm = algorithm;
        this.algorithmName = getName();
        resetColours();
    }
}

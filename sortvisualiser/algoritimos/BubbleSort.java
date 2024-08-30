package src.main.java.sortvisualiser.algoritimos;

import src.main.java.sortvisualiser.SortArray;

/**
 * A classe BubbleSort implementa o algoritmo de ordenação Bubble Sort
 * e a interface UiSortAlgoritimo. Este algoritmo organiza os elementos
 * de um array em ordem crescente, utilizando comparações sucessivas e
 * trocas.
 */
public class BubbleSort implements UiSortAlgoritimo {

    private long stepDelay = 2;

    /**
     * Este método implementa o algoritmo Bubble Sort. Ele organiza os
     * elementos de um objeto SortArray seguindo a teoria de "menos que".
     * Para mais informações, consulte
     * <a href="https://en.wikipedia.org/wiki/Order_theory">teoria de ordem</a>.
     *
     * @param array O objeto SortArray a ser ordenado.
     */
    @Override
    public void runSort(SortArray array) {
        int len = array.arraySize();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array.getValue(j) > array.getValue(j + 1)) {
                    array.swap(j, j + 1, getDelay(), true);
                }
            }
        }
    }

    /**
     * Retorna o nome do algoritmo de ordenação.
     *
     * @return O nome do algoritmo como "Bubble Sort".
     */
    @Override
    public String getName() {
        return "Bubble Sort";
    }

    /**
     * Retorna o atraso em milissegundos entre as etapas do algoritmo de
     * ordenação.
     *
     * @return O atraso em milissegundos.
     */
    @Override
    public long getDelay() {
        return stepDelay;
    }

    /**
     * Define o atraso em milissegundos entre as etapas do algoritmo de
     * ordenação.
     *
     * @param delay O atraso em milissegundos a ser definido.
     */
    @Override
    public void setDelay(long delay) {
        this.stepDelay = delay;
    }
}

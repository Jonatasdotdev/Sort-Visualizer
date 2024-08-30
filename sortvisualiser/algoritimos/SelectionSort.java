package src.main.java.sortvisualiser.algoritimos;

import src.main.java.sortvisualiser.SortArray;

/**
 * A classe SelectionSort implementa o algoritmo de ordenação Selection Sort
 * e a interface UiSortAlgoritimo. Este algoritmo organiza os elementos
 * de um array em ordem crescente, selecionando repetidamente o menor
 * elemento da parte não ordenada do array.
 */
public class SelectionSort implements UiSortAlgoritimo {

    private long stepDelay = 120;

    /**
     * Este método implementa o algoritmo Selection Sort. Ele organiza os
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
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array.getValue(j) < array.getValue(minIndex)) {
                    minIndex = j;
                }
            }
            array.swap(i, minIndex, getDelay(), true);
        }
    }

    /**
     * Retorna o nome do algoritmo de ordenação.
     *
     * @return O nome do algoritmo como "Selection Sort".
     */
    @Override
    public String getName() {
        return "Selection Sort";
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

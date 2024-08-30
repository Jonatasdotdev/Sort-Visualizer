package src.main.java.sortvisualiser.algoritimos;

import src.main.java.sortvisualiser.SortArray;

/**
 * A classe QuickSort implementa o algoritmo de ordenação Quick Sort
 * e a interface UiSortAlgoritimo. Este algoritmo organiza os elementos
 * de um array em ordem crescente utilizando a técnica de divisão e conquista.
 */
public class QuickSort implements UiSortAlgoritimo {

    private long stepDelay = 30;

    /**
     * Encontra o ponto de pivô para a partição do array.
     *
     * @param array O objeto SortArray a ser ordenado.
     * @param lowIndex O índice mais à esquerda da partição.
     * @param highIndex O índice mais à direita da partição.
     * @return O índice do pivô após a partição.
     */
    private int findPivotPoint(SortArray array, int lowIndex, int highIndex) {
        int pivotValue = array.getValue(highIndex);
        int i = lowIndex - 1;
        for (int j = lowIndex; j <= highIndex - 1; j++) {
            if (array.getValue(j) <= pivotValue) {
                i++;
                array.swap(i, j, getDelay(), true);
            }
        }
        array.swap(i + 1, highIndex, getDelay(), true);
        return i + 1;
    }

    /**
     * Executa o algoritmo Quick Sort recursivamente.
     *
     * @param array O objeto SortArray a ser ordenado.
     * @param lowIndex O índice mais à esquerda da partição.
     * @param highIndex O índice mais à direita da partição.
     */
    private void quickSort(SortArray array, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int pivotPoint = findPivotPoint(array, lowIndex, highIndex);
            quickSort(array, lowIndex, pivotPoint - 1);
            quickSort(array, pivotPoint + 1, highIndex);
        }
    }

    /**
     * Este método organiza os elementos de um objeto SortArray de acordo com a teoria
     * de "menos que". Para mais informações, consulte
     * <a href="https://en.wikipedia.org/wiki/Order_theory">teoria de ordem</a>.
     *
     * @param array O objeto SortArray a ser ordenado.
     */
    @Override
    public void runSort(SortArray array) {
        quickSort(array, 0, array.arraySize() - 1);
    }

    /**
     * Retorna o nome do algoritmo de ordenação.
     *
     * @return O nome do algoritmo como "Quick Sort".
     */
    @Override
    public String getName() {
        return "Quick Sort";
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

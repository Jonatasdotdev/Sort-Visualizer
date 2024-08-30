package src.main.java.sortvisualiser.algoritimos;

import src.main.java.sortvisualiser.SortArray;

/**
 * A interface UiSortAlgoritimo define o contrato para algoritmos de ordenação
 * que podem ser utilizados na visualização. Qualquer classe que implemente esta
 * interface deve fornecer uma implementação para os métodos definidos.
 */
public interface UiSortAlgoritimo {

    /**
     * Retorna o nome do algoritmo de ordenação.
     *
     * @return O nome do algoritmo como uma string.
     */
    public String getName();

    /**
     * Retorna o atraso em milissegundos entre as etapas do algoritmo de ordenação.
     *
     * @return O atraso em milissegundos.
     */
    public long getDelay();

    /**
     * Define o atraso em milissegundos entre as etapas do algoritmo de ordenação.
     *
     * @param delay O atraso em milissegundos a ser definido.
     */
    public void setDelay(long delay);

    /**
     * Executa o algoritmo de ordenação no array fornecido.
     *
     * @param array O array a ser ordenado.
     */
    public void runSort(SortArray array);
}

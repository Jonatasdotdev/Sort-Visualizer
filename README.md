# Sort Visualizer

Sort Visualizer é um aplicativo Java Swing para visualização de algoritmos de ordenação. Este projeto está em desenvolvimento e possui funcionalidades básicas para demonstrar como diferentes algoritmos de ordenação operam.

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:
- `src/main/java/sortvisualiser`: Contém a classe principal `MainApp` e as telas (`screens`) do aplicativo.
- `src/main/java/sortvisualiser/screens`: Contém as diferentes telas utilizadas no aplicativo, como `MainMenuScreen`.

## Funcionalidades

- Interface gráfica utilizando Java Swing.
- Troca dinâmica de telas.
- Visualização de algoritmos de ordenação.

## Como Executar

1. Certifique-se de ter o JDK instalado.
2. Clone este repositório.
3. Compile o código-fonte:
    ```sh
    javac -d bin src/main/java/sortvisualiser/*.java src/main/java/sortvisualiser/screens/*.java
    ```
4. Execute o aplicativo:
    ```sh
    java -cp bin src.main.java.sortvisualiser.MainApp
    ```

## Planejamento Futuro

Este projeto ainda está em desenvolvimento e há várias funcionalidades planejadas para as próximas versões:

- Implementação de passagem de parâmetros via CLI.
- Melhoria na interface gráfica.
- Inclusão de testes automatizados.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests com sugestões e melhorias.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
```

# Arquimedes

O sistema Arquimedes é uma ferramenta para a resolução de problemas de programação linear com N variáveis através da aplicação do método simplex revisado. 

A entrada de dados no programa se da por meio de arquivos de texto (.txt) que podem ser carregados na tela principal. Nesta mesma tela é possível criar um novo problema sem a utilização de um arquivo de texto existente. Para evitar erros de leitura, os arquivos devem seguir o seguinte padrão:

![Estrutura do arquivo](https://github.com/caioqazz/Arquimedes/blob/master/imgs/img1.png?raw=true)

Exemplo:

![Estrutura do arquivo](https://github.com/caioqazz/Arquimedes/blob/master/imgs/img2.png?raw=true)

Deve-se sempre indicar o tipo da função objetivo (Max/Min). Um numero de restrições maior do que o informado faz com que algumas sejam ignoradas, um valor menor causa erro de leitura. O programa sempre considera o numero de variáveis informado como verdadeiro, mesmo se um numero menor aparecer no problema. Um numero de variáveis maior do que o informado causará problemas de execução e, consequentemente, resultados incorretos.

A saída de dados se da por meio da tela de saída, que informa o resultado da função objetivo e os valores das variáveis. Variáveis de largura não são informadas. Os mesmos dados apresentados na tela de saída são salvos em um arquivo de texto na pasta do programa (resposta.txt) que pode ser guardado caso necessário. A execução de outro problema sobrescreve o arquivo de resposta.

O programa Arquimedes foi criado utilizando a linguagem de programação Java e, portanto, se utiliza de técnicas de orientação a objetos. O programa contem três classes de objetos com suas respectivas funções, leitor, escritor e algoritmo.

A classe Leitor lê os dados do problema e os organiza em três matrizes, a matriz de restrições A(numero de restrições X numero de restrições + numero de variáveis), a Matriz de pesos na função objetivo C(1 X numero de restrições + numero de variáveis) e a matriz de limites das restrições b(numero de restrições X 1). Durante a execução estas matrizes são acessadas, mas nunca alteradas. A classe leitor também inicializa as variáveis de folga.

A classe SimplexRevisado contem o algoritmo simplex e efetua sua execução. Inicialmente a classe inicializa suas variáveis utilizando-se das informações adquiridas pelo leitor. Segue uma lista de todas as variáveis da classe e seus respectivos papeis:

*	A[ ][ ]: Copia da matriz A existente na classe leitor;
*	Base[ ][ ]: Equivalente a B^(-1), inicializado como identidade;
*	b[ ]: Equivalente a b , inicializado como uma copia do vetor b presente na classe leitor. Ao fim da execução contemos valores das variaveis;
*	C[ ]: Copia do vetor C presente na classe leitor;
*	CbB[ ]: Equivalente a Cb B^(-1), inicializado como um vetor de zeros;
*	Y[ ]: equivalente a y ;
*	Z[ ]: Vetor auxiliar utilizado na escolha da variável que sai da base;
*	numVar: Numero de variáveis do problema;
*	numRes: Numero de restrições do problema;
*	entra: Posição no vetor varNBase da variável escolhida para entrar na base;
*	sai: Posição no vetor varBase da variável escolhida para sair da base;
*	auxNum: Auxiliar utilizado na atualização dos vetores varBase e varNBase;
*	varBase[ ]: Vetor que contem as variáveis atualmente na base;
*	varNBase[ ]: Vetor que contem as variáveis atualmente fora da base;
*	CbBb: Equivalente a Cb B^(-1) b, inicializado como zero. Ao fim da execução contem a resposta;
*	valEntra: Menor valor no conjunto C〖bB〗^(-1) A^i-C^i  ∀i∈I;
*	valSai: Menor valor no conjunto b ÷ y ;
*	sumAux: Auxiliar utilizado na escolha das variáveis de entrada e saída;
*	w: Boolean auxiliar utilizado para indicar o fim da execução. 

A classe também contem quatro métodos. O método run( ) é chamado para iniciar a execução do algoritmo, o método escolhaEntrada( ) compara os valores de C〖bB〗^(-1) A^i-C^i para escolher a variável que entra na base, o método escolhaSaida( ) compara os valores de b ÷ y  para escolher a variável que sai da base e o método trocaBase( ) utiliza as informações coletadas em escolhaSaida( ) e escolhaEntrada( ) para atualizar os valores de Base, b, CbB e CbBb.

Quando valEntra for positivo ou valSai for negativo, uma serie de testes no método run irá parar a execução e chamar um objeto da classe Escritor. A classe Escritor contem os métodos utilizados para gravar a resposta (ou ausência desta) no arquivo de texto.


package arquimedes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Leitor {

    private static BufferedReader reader;
    private static int numVar, numRes;
    private static double[][] A;
    private static double[] C, b;
    private static String[] aux, aux2;

    public Leitor() {

    }

    public Leitor(String caminho) { //funcionando
        try {
            reader = new BufferedReader(new FileReader(new File(caminho)));
            inicializaVariaveis();
            inicializaVetorC();
            inicializaAb();
            reader.close();
            //teste();
        } catch (IOException e) {
            System.out.println("Erro de leitura");
            System.exit(1);
        }
    }

    public String leituraResposta() throws FileNotFoundException, IOException {
        File arquivo = new File("resposta.txt");
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        while (br.ready()) {
//lê a proxima linha
            linha +=  br.readLine()+"\n";

//faz algo com a linha
        }
        br.close();
        fr.close();

        

        return linha;
    }

    private static void inicializaVariaveis() throws IOException { //Funcionando
        numVar = Integer.valueOf(reader.readLine());
        numRes = Integer.valueOf(reader.readLine());
    }

    private static void inicializaVetorC() throws IOException { //Funcionando
        C = new double[numVar + numRes];
        aux = reader.readLine().split(" ");

        for (int aux3 = 0; aux3 < numVar + numRes; aux3++) {
            C[aux3] = 0;
        }
        //System.out.println(aux.length);
        for (int aux3 = 1; aux3 < aux.length; aux3++) {

            aux2 = aux[aux3].split("x");

            if (aux2[0].length() != 0) {
                switch (aux2[0]) {
                    case "+":
                        C[Integer.valueOf(aux2[1]) - 1] = 1;
                        break;
                    case "-":
                        C[Integer.valueOf(aux2[1]) - 1] = -1;
                        break;
                    default:
                        C[Integer.valueOf(aux2[1]) - 1] = Double.valueOf(aux2[0]);
                    //System.out.println(C[Integer.valueOf(aux2[1]) - 1]);
                }
            }
        }

        if (aux[0].equals("Min") || aux[0].equals("min")) {
            for (int teste = 0; teste < numVar; teste++) {
                C[teste] = C[teste] * (-1);
                //System.out.println(C[teste]);
            }
        }
    }

    private static void inicializaAb() throws IOException { //funcionando
        b = new double[numRes];
        A = new double[numRes][numRes + numVar];

        for (int aux3 = 0; aux3 < numRes; aux3++) {
            for (int aux4 = 0; aux4 < numRes + numVar; aux4++) {
                if (aux4 == numVar + aux3) {
                    A[aux3][aux4] = 1;
                } else {
                    A[aux3][aux4] = 0;
                }
            }
        }

        for (int aux3 = 0; aux3 < numRes; aux3++) {
            aux[0] = reader.readLine();
            aux = aux[0].split("<=");
            if (aux.length > 1) {
                b[aux3] = Double.valueOf(aux[1]);
                aux = aux[0].split(" ");
                for (int aux4 = 0; aux4 < numVar; aux4++) {
                    if (aux4 < aux.length) {
                        aux2 = aux[aux4].split("x");
                        if (!aux2[0].isEmpty()) {
                            switch (aux2[0]) {
                                case "+":
                                    A[aux3][Integer.valueOf(aux2[1]) - 1] = 1;
                                    break;
                                case "-":
                                    A[aux3][Integer.valueOf(aux2[1]) - 1] = -1;
                                    break;
                                default:
                                    A[aux3][Integer.valueOf(aux2[1]) - 1] = Double.valueOf(aux2[0]);
                                    break;
                            }
                        } else {
                            A[aux3][Integer.valueOf(aux2[1]) - 1] = 1;
                        }
                    } else {
                        break;
                    }
                }
            } else {

                aux = aux[0].split(">=");
                if (aux.length > 1) {
                    b[aux3] = Double.valueOf(aux[1]) * (-1);
                    aux = aux[0].split(" ");
                    for (int aux4 = 0; aux4 < numVar; aux4++) {
                        if (aux4 < aux.length) {
                            aux2 = aux[aux4].split("x");
                            if (!aux2[0].isEmpty()) {
                                switch (aux2[0]) {
                                    case "+":
                                        A[aux3][Integer.valueOf(aux2[1]) - 1] = -1;
                                        break;
                                    case "-":
                                        A[aux3][Integer.valueOf(aux2[1]) - 1] = +1;
                                        break;
                                    default:
                                        A[aux3][Integer.valueOf(aux2[1]) - 1] = Double.valueOf(aux2[0]) * (-1);
                                        break;
                                }
                            } else {
                                A[aux3][Integer.valueOf(aux2[1]) - 1] = -1;
                            }
                        } else {
                            break;
                        }
                    }
                } else {

                    aux = aux[0].split("<");
                    if (aux.length > 1) {
                        b[aux3] = Double.valueOf(aux[1]);
                        aux = aux[0].split(" ");
                        for (int aux4 = 0; aux4 < numVar; aux4++) {
                            if (aux4 < aux.length) {
                                aux2 = aux[aux4].split("x");
                                if (!aux2[0].isEmpty()) {
                                    switch (aux2[0]) {
                                        case "+":
                                            A[aux3][Integer.valueOf(aux2[1]) - 1] = 1;
                                            break;
                                        case "-":
                                            A[aux3][Integer.valueOf(aux2[1]) - 1] = -1;
                                            break;
                                        default:
                                            A[aux3][Integer.valueOf(aux2[1]) - 1] = Double.valueOf(aux2[0]);
                                            break;
                                    }
                                } else {
                                    A[aux3][Integer.valueOf(aux2[1]) - 1] = 1;
                                }
                            } else {
                                break;
                            }
                        }
                    } else {

                        aux = aux[0].split(">");
                        if (aux.length > 1) {
                            b[aux3] = Double.valueOf(aux[1]) * (-1);
                            aux = aux[0].split(" ");
                            for (int aux4 = 0; aux4 < numVar; aux4++) {
                                if (aux4 < aux.length) {
                                    aux2 = aux[aux4].split("x");
                                    if (!aux2[0].isEmpty()) {
                                        switch (aux2[0]) {
                                            case "+":
                                                A[aux3][Integer.valueOf(aux2[1]) - 1] = -1;
                                                break;
                                            case "-":
                                                A[aux3][Integer.valueOf(aux2[1]) - 1] = +1;
                                                break;
                                            default:
                                                A[aux3][Integer.valueOf(aux2[1]) - 1] = Double.valueOf(aux2[0]) * (-1);
                                                break;
                                        }
                                    } else {
                                        A[aux3][Integer.valueOf(aux2[1]) - 1] = -1;
                                    }
                                } else {
                                    break;
                                }
                            }
                        } else {

                            aux = aux[0].split("=");
                            b[aux3] = Double.valueOf(aux[1]);
                            aux = aux[0].split(" ");
                            for (int aux4 = 0; aux4 < numVar; aux4++) {
                                if (aux4 < aux.length) {
                                    aux2 = aux[aux4].split("x");
                                    switch (aux2[0]) {
                                        case "+":
                                            A[aux3][Integer.valueOf(aux2[1]) - 1] = 1;
                                            break;
                                        case "-":
                                            A[aux3][Integer.valueOf(aux2[1]) - 1] = -1;
                                            break;
                                        default:
                                            A[aux3][Integer.valueOf(aux2[1]) - 1] = Double.valueOf(aux2[0]);
                                            break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int getNumVar() {
        return numVar;
    }

    public static int getNumRes() {
        return numRes;
    }

    public static double[][] getA() {
        return A;
    }

    public static double[] getC() {
        return C;
    }

    public static double[] getB() {
        return b;
    }

    private static void teste() {
        System.out.println(numVar);
        System.out.println(numRes);
        System.out.println("C:");
        for (int aux3 = 0; aux3 < numVar + numRes; aux3++) {
            System.out.println(C[aux3]);
        }
        System.out.println("b:");
        for (int aux3 = 0; aux3 < numRes; aux3++) {
            System.out.println(b[aux3]);
        }
        System.out.println("A:");
        for (int aux3 = 0; aux3 < numRes; aux3++) {
            for (int aux4 = 0; aux4 < numRes + numVar; aux4++) {
                System.out.print(A[aux3][aux4] + " ");
            }
            System.out.print("\n");
        }
    }
}

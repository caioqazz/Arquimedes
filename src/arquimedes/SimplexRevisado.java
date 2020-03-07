package arquimedes;

public class SimplexRevisado {

    Leitor leitor;
    Escritor escritor;
    private final double[][] A, Base; //Base==B^-1
    private final double[] b, C, CbB, Y, Z; //b == b barrado, varBase==variaveis ativas, varNBase==variaveis Inativas
    private final int numVar, numRes;
    private int numIterações;
    private int entra, sai, auxNum, lastOut, lastIn;
    private final int[] varBase, varNBase;
    private double CbBb, valEntra, valSai, sumAux;
    private Boolean w;

    public SimplexRevisado(String caminho) {
        numIterações = 0;
        leitor = new Leitor(caminho);
        w = true;
        lastIn = -1;
        lastOut = -1;
        numVar = Leitor.getNumVar();
        numRes = Leitor.getNumRes();
        A = Leitor.getA();
        CbBb = 0;
        CbB = new double[numRes];
        b = new double[numRes];
        System.arraycopy(leitor.getB(), 0, b, 0, numRes);
        C = Leitor.getC();
        Y = new double[numRes];
        Z = new double[numRes];
        Base = new double[numRes][numRes];
        varNBase = new int[numVar];
        varBase = new int[numRes];
        for (int i = 0; i < numRes; i++) {
            for (int j = 0; j < numRes; j++) {
                if (i == j) {
                    Base[i][j] = 1;
                } else {
                    Base[i][j] = 0;
                }
            }
            CbB[i] = 0;
        }
        //System.out.print("\n");
        for (int i = 0; i < numVar; i++) {
            varNBase[i] = i;
        }
        for (int i = 0; i < numRes; i++) {
            varBase[i] = i + numVar;
        }
        entra = -1;
        sai = -1;
        valEntra = Double.MAX_VALUE;
        valSai = Double.MAX_VALUE;
    }

    public void run() {
        while (w == true) {
            escolhaEntrada();
            if (valEntra < 0) {
                escolhaSaida();
                if (valSai>0) {
                    trocaBase();
                    lastOut = varBase[sai];
                    lastIn = varNBase[entra];
                    entra = -1;
                    sai = -1;
                    valEntra = Double.MAX_VALUE;
                    valSai = Double.MAX_VALUE;
                    sumAux = 0;
                    auxNum = 0;
                    numIterações++;
                    //System.out.println(numIterações);
                } else {
                    w = false;
                }
            } else {
                w = false;
            }
        }
        escritor = new Escritor(CbBb, b, varBase, numVar);
    }

    private void escolhaEntrada() {
        for (int i = 0; i < numVar; i++) {
            sumAux = 0;
            for (int j = 0; j < numRes; j++) {
                sumAux += CbB[j] * A[j][varNBase[i]];
            }
            sumAux -= C[varNBase[i]];
            //System.out.println(C[varNBase[i]]);
            //System.out.println(sumAux);
            if (sumAux < valEntra) {
                valEntra = sumAux;
                entra = i;
            }
        }
        //System.out.println(entra);
        //System.out.println(valEntra);
        //System.out.println(varNBase[entra]+1);
    }

    private void escolhaSaida() {
        for (int i = 0; i < numRes; i++) {
            sumAux = 0;
            for (int j = 0; j < numRes; j++) {
                //System.out.println(Base[i][j]);
                //System.out.println(A[j][entra]);
                sumAux = sumAux + Base[i][j] * A[j][entra];
            }
            Y[i] = sumAux;
            //System.out.println(Y[i]);
        }
        for (int i = 0; i < numRes; i++) {
            Z[i] = b[i] / Y[i];
            //System.out.println(Z[i]);
            if (Z[i] > 0 && Z[i] < valSai) {
                valSai = Z[i];
                sai = i;
            }
        }
        //System.out.println(sai);
        //System.out.println(valSai);
        //System.out.println(varBase[sai]+1);
    }

    private void trocaBase() {
        //System.out.println(Y[sai]);
        
        for (int i = 0; i < numRes; i++) { //divide a linha de saida pelo pivo
            Base[sai][i] = Base[sai][i] / Y[sai];
            //System.out.println(Base[sai][i]);
        }
        b[sai] = b[sai] / Y[sai];
        
        Y[sai]=1;

        for (int i = 0; i < numRes; i++) {//atualiza B e a Base
            for (int j = 0; j < numRes; j++) {
                if (i != sai) {
                    //System.out.println(Y[j]/Y[sai]);
                    //System.out.println(Base[sai][j]);
                    Base[i][j] = Base[i][j] - Base[sai][j] * (Y[i] / Y[sai]);
                    //System.out.print(Base[sai][j]+" ");
                    //System.out.println(Base[sai][j] * (Y[i] / Y[sai]));
                }
            }
            //System.out.print("\n");
            if (i != sai) {
                //System.out.println(b[i]);
                b[i] = b[i] - b[sai] * (Y[i] / Y[sai]);
            }
        }
        
        //System.out.println(valEntra);
        CbBb = CbBb - b[sai] * (valEntra / Y[sai]); //Atualiza o CbBb

        for (int i = 0; i < numRes; i++) {
            CbB[i] = CbB[i] - Base[sai][i] * (valEntra / Y[sai]);
        }

        auxNum = varBase[sai];
        varBase[sai] = varNBase[entra];
        varNBase[entra] = auxNum;
        
        /*
        
        System.out.print("\n");
        for(int i=0;i<varNBase.length;i++){
            System.out.print((varNBase[i]+1)+" ");
        }
        System.out.print("\n");
        for(int i=0;i<varBase.length;i++){
            System.out.print((varBase[i]+1)+" ");
        }
        System.out.print("\n");
        
        */
        /*
        for (int i = 0; i < numRes; i++) {
            System.out.print(CbB[i] + " ");
        }
        System.out.print("\n");
        System.out.println(CbBb);
        for (int i = 0; i < numRes; i++) {
            for (int j = 0; j < numRes; j++) {
                System.out.print(Base[i][j] + " ");
            }
            System.out.print("\n");
        }
        for (int i = 0; i < numRes; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.print("\n");
        */
        
    }
}

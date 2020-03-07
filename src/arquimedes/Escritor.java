package arquimedes;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Escritor {
    private BufferedWriter writer;
    public Escritor(){
        
    }
    public Escritor(double res, double[] var, int[] varAtivas, int numVar){
        try {
            writer=new BufferedWriter(new FileWriter(new File("resposta.txt")));
            writer.append("Resultado: "+res);
            writer.newLine();
            writer.append("Variaveis: ");
            writer.newLine();
            for(int aux=0;aux<var.length;aux++){
                if(varAtivas[aux]<numVar){
                    writer.append("X"+(varAtivas[aux]+1)+": "+var[aux]+" ");
                }
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void escreveInserção(int num_var, int num_res,String func_obj,DefaultTableModel restr){
          try {
            writer=new BufferedWriter(new FileWriter(new File("problema_geral.txt")));
           writer.append(num_var+"");
           writer.newLine();
           writer.append(num_res+"");
           writer.newLine();
           writer.append(func_obj+"");
           writer.newLine();
           DefaultTableModel modelo = restr;
              for (int i = 0; i < modelo.getRowCount(); i++) {
                   writer.append(modelo.getValueAt(i, 0)+"");
                   writer.newLine();
              }
           
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rmachado
 */
public class LerArquivo {
    private double matriz[][] = new double[58][2];
    private ArrayList<Ponto> pontos = new ArrayList<>();
    private String virgula = ",";
    
    public ArrayList<Ponto> ler (String nomeArquivo) {
        String fileName = nomeArquivo;
        
        //Referencia uma linha por vez.
        String linha = null;

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            linha = bufferedReader.readLine();
            int cont = 0;
            while(cont < 58) {
                linha = bufferedReader.readLine();
                this.lerPontos(linha, cont);
                cont++;
            }

            //Fechando o arquivo.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Impossível abrir o arquivo '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Erro ao ler o arquivo '" 
                + fileName + "'");
        }
        
        //cria os objetos
        for (int i = 0; i < 58; i++) {
            Ponto c = new Ponto(matriz[i][0], matriz[i][1], i);
            
            pontos.add(c);
        }
        
        System.out.println("\nArquivo lido!\n");
        return pontos;
    }

    private void lerPontos(String linha, int id) {
        String[] pontosStr = linha.split(virgula);
        double[] pnts = new double[pontosStr.length];
        for (int i = 0; i < pontosStr.length; i++) {
            pnts[i] = Double.parseDouble(pontosStr[i]);
        }
        
        for (int i = 0; i < pnts.length; i++) {
            matriz[id][i] = pnts[i];
        }
    }
}

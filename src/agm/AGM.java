/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author rmachado
 */
public class AGM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         String nomeArquivo = "pontos.agm1";
     
        LerArquivo arquivo = new LerArquivo();
        ArrayList<Ponto> pontos = arquivo.ler(nomeArquivo);
        
        Grafo grafo = new Grafo(pontos);
        //prim(grafo);
        kruskal(grafo);
        
        //print matriz adjacencia
        /*for (int i = 0; i < grafo.getMatrizAdjacencia().length; i++) {
            for (int j = 0; j < grafo.getMatrizAdjacencia().length; j++) {
                System.out.print(grafo.getMatrizAdjacencia()[i][j]+" ");
            }
            System.out.println();
        }*/
    }
    
    private static void prim(Grafo grafo) {
        double[] chave = new double[58];
        Ponto[] pi = new Ponto[58];
        Ponto[] Q = new Ponto[58];
        Ponto[] V = new Ponto[58];
        ArrayList<Ponto> S = new ArrayList();
        
        Random rand = new Random();
        int n = rand.nextInt(58);
        
        for (int i = 0; i < grafo.getPontos().size(); i++) {
            V[i] = grafo.getPontos().get(i);
        }
        
        for (int u = 0; u < 58; u++) {
            chave[u] = Double.POSITIVE_INFINITY;
            pi[u] = null;
        }
        
        chave[n] = 0;
        Q = V;
        
        boolean nulo = false;
        while (!nulo) {
            double min = chave[0];
            int aux = 0;
            for (int i = 0; i < chave.length; i++) {
                if (chave[i] < min) {
                    min = chave[i];
                    aux = i;
                }
            }
            
            Ponto v = Q[aux]; //extrai o mínimo
            Q[aux] = null;
            S.add(v);
            
            for (Aresta a : v.getArestas()) {
                Ponto u = a.getB();
                
                if (!S.contains(u) && pi[u.getId()].getArestas().get(aux) != null && v.getArestas().get(aux) != null
                        && pi[u.getId()].getArestas().get(aux).getDistancia() > v.getArestas().get(aux).getDistancia()) {
                    chave[u.getId()] = v.getArestas().get(aux).getDistancia();
                    pi[u.getId()] = v;
                }
            }
            
            nulo = true;
            for (int j = 0; j < Q.length; j++) {
                if (Q[j] != null) {
                    nulo = false;
                }
            }
        }
        
    }
    
    private static void kruskal(Grafo grafo) {
        ArrayList<Aresta> X = new ArrayList();
        ArrayList<Aresta> A  = new ArrayList();
        ArrayList<Ponto> V = new ArrayList();
        ArrayList<Arvore> trees = new ArrayList();
        
        for (Ponto v : grafo.getPontos()) {
            V.add(v);
            Arvore a = new Arvore(v);
            trees.add(a);
        }
        
        //acrescenta as arestas únicas a A
        for (int i = 0; i < grafo.getPontos().size(); i++) {
            for (Aresta a : grafo.getPontos().get(i).getArestas()) {
                
                //se a aresta não estiver contida em A, ela recebe a flag true
                boolean flag = false;
                for (Aresta b : grafo.getPontos().get(i).getArestas()) {
                    if (a.getA() != b.getA() && a.getB() != b.getB() && /*esse só pra garantir*/ a.getDistancia() != b.getDistancia()) {
                        flag = true;
                    }
                }
                if (flag) {
                    A.add(a); //aresta a é adicionada em A
                }
            }
        }
        
        //ordena as arestas de A por peso crescente
        Comparador c = new Comparador();
        A.sort(c);
        
        for (Aresta a : A) {
            Ponto p1 = a.getA();
            Ponto p2 = a.getB();
            
            //compara se árvore de p1 é igual à árvore de p2
            boolean igual = false;
            int posP1 = 0, posP2 = 0;
            for (Arvore t : trees) {
                for (int i = 0; i < t.getPontos().size(); i++) {
                    if (p1 != null && p2 != null && (t.getPontos().get(i).getId() == p1.getId() || t.getPontos().get(i).getId() == p2.getId())) {
                        if (t.getPontos().get(i).getId() == p1.getId()) {
                            posP1 = i;
                            for (int j = i; j < t.getPontos().size(); j++) {
                                if (t.getPontos().get(i).getId() == p2.getId()) {
                                    igual = true;
                                    posP2 = j;
                                }
                            }
                        } else {
                            posP2 = i;
                            for (int j = i; j < t.getPontos().size(); j++) {
                                if (t.getPontos().get(i).getId() == p1.getId()) {
                                    igual = true;
                                    posP1 = j;
                                }
                            }
                        }
                    }
                }
            }
            
            //se a árvore de p1 e de p2 forem diferentes:
            if (!igual) {
                X.add(a);
                trees = aplicarUniaoArvores(trees, posP1, posP2);
            }
        }
    }

    private static ArrayList<Arvore> aplicarUniaoArvores(ArrayList<Arvore> trees, int posP1, int posP2) {
        for (Ponto p : trees.get(posP2).getPontos()) {
            trees.get(posP1).addPonto(p);
        }
        trees.remove(posP2);
        
        return trees;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agm;

import java.util.ArrayList;

/**
 *
 * @author rmachado
 */
class Grafo {
    private ArrayList<Ponto> pontos;
    private double[][] matrizAdjacencia;

    public Grafo(ArrayList<Ponto> pontos) {
        this.pontos = pontos;
        this.matrizAdjacencia = new double[58][58];
        for (int i = 0; i < 58; i++) {
            matrizAdjacencia[i][i] = Double.POSITIVE_INFINITY;
        }
        calculaArestas();
    }
    
    private void calculaArestas() {
        int cont = 0;
        for (Ponto p1 : pontos) {
            for (int i = 0; i < pontos.size(); i++) {
                Ponto p2 = pontos.get(i);
                
                if (i != cont) {
                    double dist = Math.sqrt((p2.getX() - p1.getX())*(p2.getX() - p1.getX()) + (p2.getY() - p1.getY())*(p2.getY() - p1.getY()));
                    
                    Aresta aresta = new Aresta(p1, p2, dist);
                    p1.addAresta(aresta);
                    
                    matrizAdjacencia[cont][i] = dist;
                } else {
                    Aresta aresta = new Aresta(null, null, 0);
                    p1.addAresta(aresta);
                }
            }
            cont++;
        }
    }

    public ArrayList<Ponto> getPontos() {
        return pontos;
    }

    public void setPontos(ArrayList<Ponto> pontos) {
        this.pontos = pontos;
    }

    public double[][] getMatrizAdjacencia() {
        return matrizAdjacencia;
    }

    public void setMatrizAdjacencia(double[][] matrizAdjacencia) {
        this.matrizAdjacencia = matrizAdjacencia;
    }
    
    
}

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
public class Arvore {
    private ArrayList<Ponto> pontos = new ArrayList();
    private Ponto raiz;
    

    public Arvore(Ponto raiz) {
        this.raiz = raiz;
        pontos.add(this.raiz);
    }

    public ArrayList<Ponto> getPontos() {
        return pontos;
    }

    public void setPontos(ArrayList<Ponto> pontos) {
        this.pontos = pontos;
    }

    public Ponto getRaiz() {
        return raiz;
    }

    public void setRaiz(Ponto raiz) {
        this.raiz = raiz;
    }
    
    public void addPonto (Ponto p) {
        pontos.add(p);
    }
}

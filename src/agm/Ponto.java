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
public class Ponto {
    private double x, y;
    private int id;
    private ArrayList<Aresta> arestas;

    public Ponto(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.arestas = new ArrayList();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }
    
    public void addAresta(Aresta aresta) {
        this.arestas.add(aresta);
    }
 }

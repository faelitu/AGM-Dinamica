/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agm;

import java.util.Comparator;

/**
 *
 * @author rmachado
 */
public class Comparador implements Comparator<Aresta> {
    public int compare (Aresta a, Aresta b) {
        if (a.getDistancia() < b.getDistancia()) return -1;
        if (a.getDistancia() > b.getDistancia()) return 1;
        return 0;
    }
}

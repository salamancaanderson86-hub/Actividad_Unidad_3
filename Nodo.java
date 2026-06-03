import java.util.*;
import java.util.Objects;

/**
 * Clase NODO (vértice) del grafo.
 * Genérica: puede guardar cualquier tipo de dato.
 */
public class Nodo<V> {

    private V dato;
    private List<Arista<V>> aristas;

    // ── CONSTRUCTOR ──
    public Nodo(V dato) {
        this.dato = dato;
        this.aristas = new ArrayList<>();
    }

    // ── GETTERS ──
    public V getDato() {
        return this.dato;
    }

    public List<Arista<V>> getAristas() {
        return this.aristas;
    }

    // ── AGREGAR ARISTA DESDE ESTE NODO ──
    public void agregarArista(Nodo<V> destino, double peso) {
        this.aristas.add(new Arista<>(this.dato, destino.getDato(), peso));
    }

    // ── EQUALS Y HASHCODE (basados en el dato) ──
    @Override
    public boolean equals(Object obj) {
        if (this.dato == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return Objects.equals(this.dato, ((Nodo<?>) obj).dato);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.dato);
    }

    @Override
    public String toString() {
        return "Nodo(" + this.dato + ")";
    }
}

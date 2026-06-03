import java.util.Objects;

/**
 * Clase ARISTA (edge) del grafo.
 * Conecta origen -> destino con un peso numérico.
 * Para grafos no ponderados, peso = 1.0 por defecto.
 */
public class Arista<V> {

    private V origen;
    private V destino;
    private double peso;

    // ── CONSTRUCTOR COMPLETO ──
    public Arista(V origen, V destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    // ── CONSTRUCTOR SIN PESO (asume 1.0) ──
    public Arista(V origen, V destino) {
        this(origen, destino, 1.0);
    }

    // ── GETTERS ──
    public V getOrigen() {
        return this.origen;
    }

    public V getDestino() {
        return this.destino;
    }

    public double getPeso() {
        return this.peso;
    }

    // ── SETTER ──
    public void setPeso(double peso) {
        this.peso = peso;
    }

    // ── EQUALS ──
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Arista<?> otro = (Arista<?>) obj;
        return Objects.equals(this.origen, otro.origen)
            && Objects.equals(this.destino, otro.destino);
    }

    @Override
    public String toString() {
        return this.origen + " -> " + this.destino
            + " (peso: " + this.peso + ")";
    }
}

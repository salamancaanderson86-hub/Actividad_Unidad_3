import java.util.*;

/**
 * Implementación de GRAFO con lista de adyacencia.
 * Mapa: dato del vértice -> objeto Nodo.
 */
public class Grafo<V> {

    private Map<V, Nodo<V>> nodos;
    private boolean dirigido;

    // ── CONSTRUCTOR ──
    public Grafo(boolean dirigido) {
        this.dirigido = dirigido;
        this.nodos = new HashMap<>();
    }

    // ── CREAR NODO ──
    public void agregarNodo(V dato) {
        this.nodos.putIfAbsent(dato, new Nodo<>(dato));
    }

    // ── CREAR ARISTA CON PESO ──
    public void agregarArista(V origen, V destino, double peso) {
        this.agregarNodo(origen);
        this.agregarNodo(destino);
        // Arista en un sentido
        this.nodos.get(origen).agregarArista(this.nodos.get(destino), peso);
        // Si NO es dirigido: agregar arista inversa
        if (!this.dirigido) {
            this.nodos.get(destino).agregarArista(this.nodos.get(origen), peso);
        }
    }

    // ── CREAR ARISTA SIN PESO (peso = 1.0) ──
    public void agregarArista(V origen, V destino) {
        this.agregarArista(origen, destino, 1.0);
    }

    // ── OBTENER VECINOS ──
    public List<V> obtenerVecinos(V dato) {
        Nodo<V> n = this.nodos.get(dato);
        if (n == null) return Collections.emptyList();
        
        List<V> vecinos = new ArrayList<>();
        for (Arista<V> a : n.getAristas()) {
            vecinos.add(a.getDestino());
        }
        return vecinos;
    }

    // ── EXISTE NODO / ARISTA ──
    public boolean existeNodo(V dato) {
        return this.nodos.containsKey(dato);
    }

    public boolean existeArista(V origen, V destino) {
        if (!this.existeNodo(origen) || !this.existeNodo(destino)) return false;
        for (Arista<V> a : this.nodos.get(origen).getAristas()) {
            if (a.getDestino().equals(destino)) return true;
        }
        return false;
    }

    // ── GRADO / NUM NODOS ──
    public int getNumNodos() {
        return this.nodos.size();
    }

    public int grado(V dato) {
        Nodo<V> n = this.nodos.get(dato);
        return (n == null) ? 0 : n.getAristas().size();
    }

    // ── BFS (recorrido en anchura) ──
    public List<V> bfs(V inicio) {
        List<V> resultado = new ArrayList<>();
        if (!this.existeNodo(inicio)) return resultado;

        Set<V> visitados = new HashSet<>();
        Queue<V> cola = new LinkedList<>();

        cola.offer(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            V actual = cola.poll();
            resultado.add(actual);
            for (V vecino : this.obtenerVecinos(actual)) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.offer(vecino);
                }
            }
        }
        return resultado;
    }

    // ── DFS (recorrido en profundidad) ──
    public List<V> dfs(V inicio) {
        List<V> resultado = new ArrayList<>();
        this.dfsRec(inicio, new HashSet<>(), resultado);
        return resultado;
    }

    private void dfsRec(V actual, Set<V> visitados, List<V> resultado) {
        visitados.add(actual);
        resultado.add(actual);
        for (V vecino : this.obtenerVecinos(actual)) {
            if (!visitados.contains(vecino)) {
                this.dfsRec(vecino, visitados, resultado);
            }
        }
    }

    // ── IMPRIMIR EL GRAFO ──
    public void imprimir() {
        System.out.println("=== "
            + (this.dirigido ? "Grafo Dirigido" : "Grafo No Dirigido")
            + " ===");
        for (Nodo<V> n : this.nodos.values()) {
            System.out.print(n.getDato() + " -> ");
            List<String> ady = new ArrayList<>();
            for (Arista<V> a : n.getAristas()) {
                ady.add(a.getDestino() + "(" + a.getPeso() + ")");
            }
            System.out.println(String.join(", ", ady));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // ── DIJKSTRA (Ruta más corta) AGREGADO AQUÍ ──
    // ══════════════════════════════════════════════════════════════
    public void dijkstra(V inicio) {
        if (!this.existeNodo(inicio)) {
            System.out.println("El nodo de inicio no existe.");
            return;
        }

        Map<V, Double> distancias = new HashMap<>();
        Map<V, V> predecesores = new HashMap<>();
        
        // Cola de prioridad basada en la distancia acumulada
        PriorityQueue<Par<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(p -> p.distancia));

        // 1. Inicializar todas las distancias en infinito
        for (V nodo : this.nodos.keySet()) {
            distancias.put(nodo, Double.MAX_VALUE);
            predecesores.put(nodo, null);
        }

        // 2. La distancia al origen es 0
        distancias.put(inicio, 0.0);
        pq.offer(new Par<>(inicio, 0.0));

        // 3. Evaluar caminos
        while (!pq.isEmpty()) {
            Par<V> actual = pq.poll();
            V u = actual.vertice;

            if (actual.distancia > distancias.get(u)) continue;

            Nodo<V> nodoU = this.nodos.get(u);
            for (Arista<V> arista : nodoU.getAristas()) {
                V v = arista.getDestino();
                double peso = arista.getPeso();
                double nuevaDist = distancias.get(u) + peso;

                if (nuevaDist < distancias.get(v)) {
                    distancias.put(v, nuevaDist);
                    predecesores.put(v, u);
                    pq.offer(new Par<>(v, nuevaDist));
                }
            }
        }

        // 4. Imprimir resultados formateados
        System.out.println("\n=== RUTAS ÓPTIMAS DESDE " + inicio.toString().toUpperCase() + " ===");
        for (V destino : this.nodos.keySet()) {
            if (!destino.equals(inicio)) {
                System.out.print("Destino: " + destino + " | Distancia total: " + distancias.get(destino) + " km | Ruta: ");
                imprimirRuta(destino, predecesores);
            }
        }
    }

    // Método auxiliar para reconstruir e imprimir la flecha de la ruta
    private void imprimirRuta(V destino, Map<V, V> predecesores) {
        List<V> ruta = new ArrayList<>();
        for (V at = destino; at != null; at = predecesores.get(at)) {
            ruta.add(at);
        }
        Collections.reverse(ruta); // Para que vaya de Origen a Destino
        for (int i = 0; i < ruta.size(); i++) {
            System.out.print(ruta.get(i) + (i < ruta.size() - 1 ? " -> " : ""));
        }
        System.out.println();
    }

    // Clase auxiliar interna para manejar la cola de prioridad
    private static class Par<V> {
        V vertice;
        double distancia;
        Par(V v, double d) { 
            this.vertice = v; 
            this.distancia = d; 
        }
    }
}

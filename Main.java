/**
 * Punto de entrada del programa.
 * Crea el grafo, lo pobla con datos de AgroLogístic Casanare y ejecuta Dijkstra.
 */
public class Main {

    public static void main(String[] args) {

        // ══ PASO 1: Crear grafo NO DIRIGIDO ══
        Grafo<String> redVial = new Grafo<>(false);

        // ══ PASO 2: Agregar aristas con datos reales de Casanare (en km) ══
        redVial.agregarArista("Yopal", "Aguazul", 48.0);
        redVial.agregarArista("Yopal", "Maní", 62.0);
        redVial.agregarArista("Yopal", "Monterrey", 75.0);
        
        redVial.agregarArista("Aguazul", "Tauramena", 35.0);
        redVial.agregarArista("Aguazul", "Maní", 40.0);
        redVial.agregarArista("Aguazul", "Villanueva", 55.0);
        redVial.agregarArista("Aguazul", "Monterrey", 60.0);
        
        redVial.agregarArista("Tauramena", "Villanueva", 42.0);
        redVial.agregarArista("Tauramena", "Orocué", 58.0);
        
        redVial.agregarArista("Maní", "Orocué", 70.0);
        
        redVial.agregarArista("Villanueva", "Monterrey", 38.0);

        // ══ PASO 3: Imprimir el grafo para ver sus conexiones ══
        redVial.imprimir();

        // ══ PASO 4: Consultas Básicas ══
        System.out.println("\nNodos totales (Municipios de la red): " + redVial.getNumNodos());
        
        // ══ PASO 5: Ejecutar Dijkstra ══
        System.out.println("Calculando el algoritmo de Dijkstra...");
        redVial.dijkstra("Yopal");
    }
}

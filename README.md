# Optimización de Rutas - AgroLogístic Casanare

Proyecto desarrollado para optimizar la red de distribución de insumos en el departamento de Casanare mediante la implementación del **Algoritmo de Dijkstra**.

## Objetivo
Calcular la ruta más eficiente (en kilómetros) desde la sede principal en Yopal hacia seis municipios estratégicos de la red vial, permitiendo minimizar tiempos y costos operativos.

## Aspectos Técnicos
* **Algoritmo:** Dijkstra, optimizado con una cola de prioridad (`PriorityQueue`) para garantizar un tiempo de ejecución eficiente: $O((V+E) \log V)$.
* **Estructura:** Grafo no dirigido ponderado, representado internamente mediante una lista de adyacencia.
* **Tecnologías:** Java (POO) y gestión de dependencias con Maven.

## Estructura del Repositorio
* `/src/main/java/co/casanare/agrolagistica/`: Código fuente principal (Grafo, nodos, aristas y lógica de Dijkstra).
* `/src/test/java/co/casanare/agrolagistica/`: Pruebas unitarias con JUnit 5 para asegurar la precisión de los cálculos.

## Ejecución
1. **Compilar:** Utilice Maven para gestionar las dependencias y compilar el proyecto:
   ```bash
   mvn clean install

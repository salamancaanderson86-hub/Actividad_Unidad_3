# Optimización de Rutas - AgroLogístic Casanare

Proyecto desarrollado para optimizar la red de distribución de insumos en el departamento de Casanare mediante la implementación del **Algoritmo de Dijkstra**.

## Objetivo
Calcular la ruta más eficiente (en kilómetros) desde la sede principal en Yopal hacia seis municipios estratégicos de la red vial, permitiendo minimizar tiempos y costos operativos.

## Aspectos Técnicos
* **Algoritmo:** Dijkstra, optimizado con una cola de prioridad (`PriorityQueue`) para garantizar un tiempo de ejecución eficiente: $O((V+E) \log V)$.
* **Estructura:** Grafo no dirigido ponderado, representado internamente mediante una lista de adyacencia.
* **Tecnologías:** Java (POO) y gestión de dependencias con Maven.

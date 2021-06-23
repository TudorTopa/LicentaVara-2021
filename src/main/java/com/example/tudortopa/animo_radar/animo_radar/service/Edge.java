package com.example.tudortopa.animo_radar.animo_radar.service;

import java.util.Objects;

class Edge {
    String source;
    String destination;
    int weight;

    public Edge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public void incrementWeight() {
        this.weight += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", weight=" + weight +
                '}';
    }
}
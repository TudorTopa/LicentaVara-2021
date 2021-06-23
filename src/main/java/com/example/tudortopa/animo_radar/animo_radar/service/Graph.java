package com.example.tudortopa.animo_radar.animo_radar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph {
    private Map<Vertex, List<Edge>> adjVertices = new HashMap<>() {
    };

    public Graph() {
    }

    public Map<Vertex, List<Edge>> getAdjVertices() {
        return adjVertices;
    }

    public void setAdjVertices(Map<Vertex, List<Edge>> adjVertices) {
        this.adjVertices = adjVertices;
    }

    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(label));
    }

    void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Edge edge = new Edge(label1, label2, 1);
        for (int i = 0; i < adjVertices.get(v1).size(); i++) {
            if (adjVertices.get(v1).get(i).equals(edge)) {
                adjVertices.get(v1).get(i).incrementWeight();
            }
            return;
        }
        adjVertices.get(v1).add(new Edge(label1, label2, 1));
    }


    @Override
    public String toString() {
        return "Graph{" +
                "adjVertices=" + adjVertices +
                '}';
    }
}
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Created by tkalbar on 3/2/19.
 */

public class Program2 {
    //public ArrayList<ArrayList<node>> adjList;
    private Integer cols;
    private Integer rows;

    public int constructIntensityGraph(int[][] image) {
        int sum = 0;
        //adjList = new ArrayList<ArrayList<node>>();
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                // ArrayList<node> row = new ArrayList<node>();
                int currWeight = 0;
                if (i > 0) {
                    currWeight = Math.abs(image[i][j] - image[i - 1][j]);
                    // row.add(new node(currWeight,i-1, j));
                    sum += currWeight;
                }
             /*   if(i< image.length-1){
                    currWeight = Math.abs(image[i][j] - image[i+1][j]);
                    row.add(new node(currWeight, i+1, j));
                } */
                if (j > 0) {
                    currWeight = Math.abs(image[i][j] - image[i][j - 1]);
                    // row.add(new node(currWeight, i, j-1));
                    sum += currWeight;
                }
               /* if(j<image[i].length-1){
                    currWeight =Math.abs(image[i][j] - image[i][j+1]);
                    row.add(new node(currWeight, i , j+1));
                }
                adjList.add(row); */
            }
        }

        return sum;
    }

    public int constructPrunedGraph(int[][] image) {
        int sum = 0;
        HashSet<Integer> unVisited = new HashSet<Integer>();
        HashSet<Integer> visited = new HashSet<Integer>();
        cols = image[0].length;
        rows = image.length;
        int total = rows * cols;

        for (int i = 0; i < total; i++) {
            unVisited.add(i);
        }
       // System.out.println(unVisited.toString());
        unVisited.remove(0);
        visited.add(0);
        while (!unVisited.isEmpty()) {
            int minNode = 0;
            int minVal = Integer.MAX_VALUE;
            for (Integer curr : visited) {
                ArrayList<Integer> neighbors = getNeighbors(curr);
           //     System.out.println(neighbors.toString() + " curr :  " + curr + "  ___________________intensity: " + image[row(curr)][col(curr)]);
                for (Integer i : neighbors) {
                    int distance = Math.abs(image[row(curr)][col(curr)] - image[row(i)][col(i)]);
                    if (distance < minVal && unVisited.contains(i)) {
                        minVal = distance;
                        minNode = i;
                    }
                }
            }
            unVisited.remove(minNode);
            visited.add(minNode);
            sum += minVal;

        }
        return sum;
    }

    private int row(int k) {
        return k / cols;
    }

    private int col(int k) {
        return k % cols;
    }

    private ArrayList<Integer> getNeighbors(int k) {
        ArrayList<Integer> mine = new ArrayList<Integer>();
        if (col(k) > 0) {
            mine.add(k - 1);
        }
        if (col(k) < cols - 1) {
            mine.add(k + 1);
        }
        if (row(k) > 0) {
            mine.add(k - cols);
        }
        if (row(k) < rows - 1) {
            mine.add(k + cols);
        }
        return mine;
    }
}
       /* int sum = 0;
        Graph g = makeGraph(image);

        HashSet<Vertex> unVisited = new HashSet<Vertex>();
        HashSet<Vertex> visited = new HashSet<Vertex>();
        for(ArrayList<Vertex> i : g.Nodes) {
            unVisited.addAll(i);
        }
        Vertex curr = g.Nodes.get(0).get(0);
        unVisited.remove(curr);
        visited.add(curr);
        while(!unVisited.isEmpty()){
            int min =Integer.MAX_VALUE;
            Vertex remove = curr;
          for(int i = 0; i< curr.edges.size(); i++){
              Edge k = curr.edges.get(i);
              if(min > k.weight && unVisited.contains(k.second)){
                  min = k.weight;
                  remove = k.second;
              }
          }
            visited.add(remove);
            unVisited.remove(remove);
            sum += min;
            curr = remove;
        }

        return sum; */

  /*  private Graph makeGraph(int[][] image){
        Graph g = new Graph();
        for(int i=0; i< image.length; i++){ // make all of the nodes
             ArrayList<Vertex> row = new ArrayList<Vertex>();
            for(int j=0; j < image[i].length; j++){
                row.add(new Vertex(i, j, image[i][j]));
            }
            g.Nodes.add(row);
        }


        for(int i=0; i< g.Nodes.size(); i++){ // connect all of the nodes
            for(int j = 0; j<g.Nodes.get(i).size(); j++){
                Vertex curr = g.Nodes.get(i).get(j);
                if(i>0){
                    curr.edges.add(new Edge(curr, g.Nodes.get(i-1).get(j)));
                }
                if(i< g.Nodes.size()-1){
                    curr.edges.add(new Edge(curr, g.Nodes.get(i+1).get(j)));
                }
                if(j>0){
                    curr.edges.add(new Edge(curr, g.Nodes.get(i).get(j-1)));
                }
                if(j<g.Nodes.get(i).size()-1){
                    curr.edges.add(new Edge(curr, g.Nodes.get(i).get(j+1)));
                }
            }
        }
        return g;
    }

}


  class Vertex {
    public int row;
    public int col;
    public int intensity;
    public ArrayList<Edge> edges;

    Vertex(int r, int c, int i){
        this.row = r;
        this.col = c;
        this.intensity = i;
        edges = new ArrayList<Edge>();
    }
  }

  class Edge {
    public Vertex first;
    public Vertex second;
    public int weight;

    Edge(Vertex f, Vertex s){
        this.first = f;
        this.second = s;
        weight = Math.abs(f.intensity - s.intensity);
    }

      @Override
      public String toString() {
        return (first.col + first.row + " :  " + second.col + second.row + "  " + weight);
      }
  }

  class Graph {
      public ArrayList<ArrayList<Vertex>> Nodes;

      Graph() {
          Nodes = new ArrayList<ArrayList<Vertex>>();
      }

      public void print() {
          System.out.println(this.Nodes.toString());
          }
      }


    /*class node {
    public int weight;
    public int row;
    public int col;

    node(int weight, int row, int col){
        this.weight= weight;
        this.row = row;
        this.col = col;
    }

      @Override
      public String toString() {
        return "weight is: " + weight + " row & col " + row + " " + col;
      }
  }*/


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by tkalbar on 3/2/19.
 */

public class Program2 {
    //public ArrayList<ArrayList<node>> adjList;

    public int constructIntensityGraph(int[][] image){
        int sum =0;
        //adjList = new ArrayList<ArrayList<node>>();
        for(int i=0; i< image.length; i++){
            for(int j = 0; j<image[i].length; j++){
               // ArrayList<node> row = new ArrayList<node>();
                int currWeight = 0;
                if(i>0){
                    currWeight = Math.abs(image[i][j] - image[i-1][j]);
                   // row.add(new node(currWeight,i-1, j));
                    sum+=currWeight;
                }
             /*   if(i< image.length-1){
                    currWeight = Math.abs(image[i][j] - image[i+1][j]);
                    row.add(new node(currWeight, i+1, j));
                } */
                if(j>0){
                    currWeight = Math.abs(image[i][j] - image[i][j-1]);
                   // row.add(new node(currWeight, i, j-1));
                    sum+= currWeight;
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

    public int constructPrunedGraph(int[][] image){
        Graph g = makeGraph(image);
        HashSet<Vertex> unVisited = new HashSet<Vertex>();
        HashSet<Vertex> visited = new HashSet<Vertex>();
        for(ArrayList<Vertex> i : g.Nodes) {
            unVisited.addAll(i);
        }


        return 0;
    }

    private Graph makeGraph(int[][] image){
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


package heyitsap.flowgraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception
    {
        FlowGraph graph = new FlowGraph();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/flytgraf3.txt"))){
            br.readLine(); // To skip first line about amount of edges and nodes.

            String line;
            while ((line = br.readLine()) != null){
                line = line.trim();
                if (line.isEmpty()) continue;
                System.out.println(line);

                String[] splittedline = line.split("\\s+");
                if (splittedline.length != 3){
                    System.out.println("SKIPPED: [" + line + "]");
                    continue;
                }

                int IdNode = Integer.parseInt(splittedline[0]);
                int ToNode = Integer.parseInt(splittedline[1]);
                int EdgeCapacity = Integer.parseInt(splittedline[2]);

                Node From = graph.getOrCreateNode(IdNode);
                Node To = graph.getOrCreateNode(ToNode);
                graph.addEdge(From, To, EdgeCapacity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int SourceId = 0;
        int SinkId = 1;
        graph.setSource(graph.getOrCreateNode(SourceId));
        graph.setSink(graph.getOrCreateNode(SinkId));

    }
}
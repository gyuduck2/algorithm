package baekjoon.b2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2178 {
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String[] split = st.nextToken().split("");
            for(int j=0; j<split.length; j++) {
                graph[i][j] = Integer.valueOf(split[j]);
            }
        }

        boolean[][] visit = new boolean[N][M];

        dfs(graph, visit, 0, 0, 0);
        System.out.println(min);

        int[][] visitCount = new int[N][M];
        bfs(graph, visitCount);
    }

    public static void bfs(int[][] graph, int[][] visitCount) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));
        visitCount[0][0] = 1;
        int count = 0;
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            int x = n.x;
            int y = n.y;

            count = visitCount[x][y];
            System.out.println("[방문]x="+x+" y="+y+ " count="+visitCount[x][y]);
            if(x == graph.length-1 && y == graph[x].length-1) {
                System.out.println(visitCount[x][y]);
                return;
            }

            if(x < graph.length-1 && graph[x+1][y] == 1 && visitCount[x+1][y] == 0) {
                queue.offer(new Node(x+1, y));
                visitCount[x+1][y] = count+1;
            }
            if(x > 0 && graph[x-1][y] == 1 && visitCount[x-1][y] == 0) {
                queue.offer(new Node(x-1, y));
                visitCount[x-1][y] = count+1;
            }
            if(y < graph[x].length-1 && graph[x][y+1] == 1 && visitCount[x][y+1] == 0) {
                queue.offer(new Node(x, y + 1));
                visitCount[x][y+1] = count+1;
            }
            if(y > 0 && graph[x][y-1] == 1 && visitCount[x][y-1] == 0) {
                queue.offer(new Node(x, y-1));
                visitCount[x][y-1] = count+1;
            }
        }
    }

    public static void dfs(int[][] graph, boolean[][] visit, int x, int y, int count) {
        visit[x][y] = true;
        count++;
//        System.out.println("x="+x+" y="+y+" count="+count);
        if(count > min) {
            return;
        }
        if(x == graph.length-1 && y == graph[x].length-1) {
//            System.out.println("!!"+count);
            if(min > count) {
                min = count;
            }
        }

        if(x < graph.length-1 && graph[x+1][y] == 1 && visit[x+1][y] == false) {
//            System.out.println("down");
            dfs(graph, visit, x+1, y, count);
            visit[x+1][y] = false;
        }
        if(x > 0 && graph[x-1][y] == 1 && visit[x-1][y] == false) {
//            System.out.println("up");
            dfs(graph, visit, x-1, y, count);
            visit[x-1][y] = false;
        }
        if(y < graph[x].length-1 && graph[x][y+1] == 1 && visit[x][y+1] == false) {
//            System.out.println("right");
            dfs(graph, visit, x, y+1, count);
            visit[x][y+1] = false;
        }
        if(y > 0 && graph[x][y-1] == 1 && visit[x][y-1] == false) {
//            System.out.println("left");
            dfs(graph, visit, x, y-1, count);
            visit[x][y-1] = false;
        }
    }

}

class Node {
    int x;
    int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

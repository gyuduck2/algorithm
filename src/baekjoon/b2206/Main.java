package baekjoon.b2206;

import java.util.*;
import java.io.*;

public class Main {

    public static int[] xv = new int[]{-1, 1, 0, 0};
    public static int[] yv = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());

        int count = 0;
        int[][] g = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j=0; j<s.length(); j++) {
                g[i][j] = s.charAt(j) - '0';
                if(g[i][j] == 1){
                    count++;
                    if(count == 2*M) {
                        System.out.println("-1");
                        return;
                    }
                }else {
                    count = 0;
                }
            }
        }
        boolean[][][] visit = new boolean[2][N][M];
        int[][] visitCount = new int[N][M];

        System.out.println(bfs(g, visit, visitCount));
    }

    public static int bfs(int[][] g, boolean[][][] visit, int[][] visitCount) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, false));
        visit[0][0][0] = true;
        visitCount[0][0] = 1;
        while(!queue.isEmpty()) {
            Node poll = queue.poll();
            if(poll.x == g.length-1 && poll.y == g[0].length-1) {
                return visitCount[poll.x][poll.y];
            }
            for(int i=0; i<4; i++) {
                int x = poll.x + xv[i];
                int y = poll.y + yv[i];
                int count = visitCount[poll.x][poll.y] + 1;
                if(x >= 0 && x < g.length && y >= 0 && y < g[x].length) {
                    if(g[x][y] == 0) {
                        if(!poll.broken && !visit[0][x][y]) {
                            queue.offer(new Node(x, y, false));
                            visit[0][x][y] = true;
                            visitCount[x][y] = count;
                        }else if(poll.broken && !visit[1][x][y]) {
                            queue.offer(new Node(x, y, true));
                            visit[1][x][y] = true;
                            visitCount[x][y] = count;
                        }
                    }else if(g[x][y] == 1) {
                        if(!poll.broken) {
                            queue.offer(new Node(x, y, true));
                            visit[1][x][y] = true;
                            visitCount[x][y] = count;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static boolean canMove(int[][] g, int x, int y) {
        boolean move = false;
        for(int i=0; i<4; i++) {
            int nx = x + xv[i];
            int ny = y + yv[i];
            if(nx >= 0 && nx < g.length && ny >= 0 && ny < g[nx].length) {
                if(g[nx][ny] == 0) {
                    return true;
                }
            }
        }
        return move;
    }
}

class Node {
    int x;
    int y;
    boolean broken;
    public Node(int x, int y, boolean broken) {
        this.x = x;
        this.y = y;
        this.broken = broken;
    }
    public String toString() {
        return "x="+x+" y="+y;
    }
}


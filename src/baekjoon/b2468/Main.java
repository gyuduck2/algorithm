package baekjoon.b2468;

import java.io.*;
import java.util.*;

public class Main {
    public static int[] xv = new int[]{-1, 1, 0, 0};
    public static int[] yv = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());

        int max = Integer.MIN_VALUE;
        int[][] map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());
                if(max < map[i][j]) {
                    max = map[i][j];
                }
            }
        }

        int min = 0;

        for(int i=0; i <= max; i++) {
            int count = 0;
            boolean[][] visit = new boolean[N][N];
            for(int j=0; j < map.length; j++) {
                for (int k=0; k<map[j].length; k++) {
                    if(map[j][k] > i && !visit[j][k]) {
                        bfs(map, visit, j, k, i);
                        count++;
                    }
                }
            }
            if(min < count) {
                min = count;
            }
        }
        System.out.println(min);
    }

    public static void bfs(int[][] map, boolean[][] visit, int x, int y, int r) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        visit[x][y] = true;

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = now.x + xv[i];
                int ny = now.y + yv[i];

                if(nx < 0 || nx > map.length-1 || ny <  0 || ny > map[nx].length-1) continue;

                if(map[nx][ny] > r && !visit[nx][ny]) {
                    queue.offer(new Node(nx, ny));
                    System.out.println("nx="+nx+" ny="+ny);
                    visit[nx][ny] = true;
                }
            }
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

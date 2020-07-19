import java.util.*;

public class Maze {
    static int N, M;
    static int[][] maze;
    static boolean[][] check;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        N = 5;
        M = 5;
        maze = new int[N][M];
        check = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                maze[i][j] = console.nextInt();
            }
        }
        check[0][0] = true;
        BFS(0, 0);
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void BFS(int x, int y) {
        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(x, y));
        while(!q.isEmpty()) {
            Dot d = q.poll();
            for(int i=0; i<4; i++) {
                int NextX = d.x + dx[i];
                int NextY = d.y + dy[i];

                if(NextX < 0 || NextY < 0 || NextX >= N || NextY >= M) {
                    continue;
                }

                if(check[NextX][NextY] || maze[NextX][NextY] == 0) {
                    continue;
                }

                q.add(new Dot(NextX, NextY));
                maze[NextX][NextY] = maze[d.x][d.y] + 1;
                check[NextX][NextY] = true;
            }
        }
    }



    private static class Dot {
        int x;
        int y;
        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

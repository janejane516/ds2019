import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Maze2 {
    static int N, M;
    static boolean[][] chk;
    static int[][] maze;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        N = console.nextInt();
        M = console.nextInt();
        maze = new int[N][M];
        chk = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                maze[i][j] = console.nextInt();
            }
        }
        String s = console.next();
        s = s.substring(1, s.length()-1);
        String[] tmp = s.split(",");
        int start_x = Integer.parseInt(tmp[0]);
        int start_y = Integer.parseInt(tmp[1]);
        s = console.next();
        s = s.substring(1, s.length()-1);
        tmp = s.split(",");
        int end_x = Integer.parseInt(tmp[0]);
        int end_y = Integer.parseInt(tmp[1]);
        chk[start_x][start_y] = true;
        System.out.println(BFS(start_x,start_y, end_x, end_y));
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int BFS(int x, int y, int end_x, int end_y) {
        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(x,y));
        while(!q.isEmpty()) {
            Dot d = q.poll();
            for(int i=0; i<4; i++) {
                int NextX = d.x + dx[i];
                int NextY = d.y + dy[i];

                if(NextX < 0 || NextY < 0 || NextX >= N || NextY >= M) {
                    continue;
                }

                if(chk[NextX][NextY] || maze[NextX][NextY] == 0) {
                    continue;
                }

                q.add(new Dot(NextX, NextY));
                maze[NextX][NextY] = maze[d.x][d.y] + 1;
                chk[NextX][NextY] = true;
            }
            if(chk[end_x][end_y]) {
                return maze[end_x][end_y];
            }
        }
        return -1;
    }

    static class Dot {
        int x;
        int y;
        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

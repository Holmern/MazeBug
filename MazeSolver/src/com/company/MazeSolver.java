package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class MazeSolver {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Maze> mazes = new ArrayList<Maze>();

        Maze m = new Maze();
        Scanner in = new Scanner(new File("C:\\Users\\chr19\\OneDrive\\Skrivebord\\MazeSolver\\src\\com\\company\\Mazes.txt"));
        int rows = Integer.parseInt(in.nextLine());
        m.maze = new int[rows][];

        for (int i = 0; i< rows; i++){
            String line = in.nextLine();
            m.maze[i] = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();

        }
        m.start= new Position(Integer.parseInt(in.nextLine()),Integer.parseInt(in.nextLine()));
        mazes.add(m);

        int i = 0;
        while(i < mazes.size()){
            if (solveMaze(mazes.get(i))){
                System.out.println("Du VANDT");
            }else{
                System.out.println("Blindgyde");
            }
            i++;
        }



    }

    private static boolean solveMaze(Maze m) {
        Position p = m.start;
        m.path.push(p);





        while (true){
            int y = m.path.peek().y;
            int x = m.path.peek().x;
            m.maze[y][x]=0;

            //Ned
            if (isValid(y+1,x,m)){

                if (m.maze[y+1][x]==2) {
                    System.out.println("Ned");
                    return true;
                }else if (m.maze[y+1][x]==1) {
                    System.out.println("Du Gik ned");
                    m.path.push(new Position(y+1, x));
                    continue;
                }}

            //Venstre
            if (isValid(y,x-1,m)){

                if (m.maze[y][x-1]==2) {
                    System.out.println("Venstre");
                    return true;
                }else if (m.maze[y][x-1]==1) {
                    System.out.println("Du Gik til Venstre");
                    m.path.push(new Position(y, x-1));
                    continue;
                }}
            //Op
            if (isValid(y-1,x,m)){

                if (m.maze[y-1][x]==2) {
                    System.out.println("Op");
                    return true;
                }else if (m.maze[y-1][x]==1) {
                    System.out.println("Du Gik Op");
                    m.path.push(new Position(y-1, x));
                    continue;
                }
            }
            //Højre
            if(isValid(y,x+1,m)){

                if (m.maze[y][x+1]==2) {
                    System.out.println("Højre");
                    return true;
                }else if (m.maze[y][x+1]==1) {
                    System.out.println("Du Gik Til Højre");
                    m.path.push(new Position(y, x+1));
                    continue;
                }
            }

            m.path.pop();
            System.out.println("Går tilbage");
            if (m.path.size()<=0){
                return false;
            }
        }
    }

    public static boolean isValid(int y, int x, Maze m){
        if (y<0 ||
                y >= m.maze.length ||
                x < 0 ||
                x >= m.maze[y].length) {
            return false;
        }
        return true;
        }

    }


package com.example.finalprojectshared;

public class Maze {

    private int[][] maze;
    private int row;
    private int column;
    private String traveledDirection;

    public Maze(int[][] maze, int row, int column) {
        this.maze = maze;
        this.row = row;
        this.column = column;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRoomNumber() {
        return maze[row][column];
    }

    public String getTraveledDirection() {
        return traveledDirection;
    }

    public void setTraveledDirection(String traveledDirection) {
        this.traveledDirection = traveledDirection;
    }
}

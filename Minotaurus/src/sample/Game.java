package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {

    int[][] maze;
    final int backgroundCode = 0;
    final int wallCode = 1;
    final int emptyCode = 3;
    public static Scene geemu;
    public Pane root;
    Canvas canvas;      // the canvas where the maze is drawn and which fills the whole window
    GraphicsContext g;  // graphics context for drawing on the canvas

    Color[] color;          // colors associated with the preceding 5 constants;
    int rows = 31;          // number of rows of cells in maze, including a wall around edges
    int columns = 51;       // number of columns of cells in maze, including a wall around edges
    int blockSize = 20;     // size of each cell
    int speedSleep = 0;    // short delay between steps in making and solving maze


    public Game(){



        color = new Color[] {
                Color.rgb(200,0,0),
                Color.rgb(200,0,0),
                Color.rgb(128,128,255),
                Color.WHITE,
                Color.rgb(200,200,200)
        };

    }
    void gaming(){
        maze = new int[rows][columns];
        canvas = new Canvas(columns*blockSize, rows*blockSize);
        g = canvas.getGraphicsContext2D();
        g.setFill(color[backgroundCode]);
        g.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        root = new Pane(canvas);
        geemu = new Scene(root);
        makeMaze();
/*        stage.setScene(scene);

        stage.setTitle("Maze Generator/Solve");
        stage.show();*/

    }
    void drawSquare( int row, int column, int colorCode ) {
        // Fill specified square of the grid with the
        // color specified by colorCode, which has to be
        // one of the constants emptyCode, wallCode, etc.
        Platform.runLater( () -> {
            g.setFill( color[colorCode] );
            int x = blockSize * column;
            int y = blockSize * row;
            g.fillRect(x,y,blockSize,blockSize);
        });
    }

    public void run() {


    }

    void makeMaze() {

        int i,j;
        int emptyCt = 0; // number of rooms
        int wallCt = 0;  // number of walls
        int[] wallrow = new int[(rows*columns)/2];  // position of walls between rooms
        int[] wallcol = new int[(rows*columns)/2];
        for (i = 0; i<rows; i++)  // start with everything being a wall
            for (j = 0; j < columns; j++)
                maze[i][j] = wallCode;
        for (i = 1; i<rows-1; i += 2)  { // make a grid of empty rooms
            for (j = 1; j<columns-1; j += 2) {
                emptyCt++;
                maze[i][j] = -emptyCt;  // each room is represented by a different negative number
                if (i < rows-2) {  // record info about wall below this room
                    wallrow[wallCt] = i+1;
                    wallcol[wallCt] = j;
                    wallCt++;
                }
                if (j < columns-2) {  // record info about wall to right of this room
                    wallrow[wallCt] = i;
                    wallcol[wallCt] = j+1;
                    wallCt++;
                }
            }
        }
        Platform.runLater( () -> {
            g.setFill( color[emptyCode] );
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    if (maze[r][c] < 0)
                        g.fillRect( c*blockSize, r*blockSize, blockSize, blockSize );
                }
            }
        });
        synchronized(this) {
            try { wait(1000); }
            catch (InterruptedException e) { }
        }
        int r;
        for (i=wallCt-1; i>0; i--) {
            r = (int)(Math.random() * i);  // choose a wall randomly and maybe tear it down
            tearDown(wallrow[r],wallcol[r]);
            wallrow[r] = wallrow[i];
            wallcol[r] = wallcol[i];
        }
        for (i=1; i<rows-1; i++)  // replace negative values in maze[][] with emptyCode
            for (j=1; j<columns-1; j++)
                if (maze[i][j] < 0)
                    maze[i][j] = emptyCode;
        synchronized(this) {
            try { wait(1000); }
            catch (InterruptedException e) { }
        }
    }

    void tearDown(int row, int col) {



        if (row % 2 == 1 && maze[row][col-1] != maze[row][col+1]) {
            // row is odd; wall separates rooms horizontally
            fill(row, col-1, maze[row][col-1], maze[row][col+1]);
            maze[row][col] = maze[row][col+1];
            drawSquare(row,col,emptyCode);

        }
        else if (row % 2 == 0 && maze[row-1][col] != maze[row+1][col]) {
            // row is even; wall separates rooms vertically
            fill(row-1, col, maze[row-1][col], maze[row+1][col]);
            maze[row][col] = maze[row+1][col];
            drawSquare(row,col,emptyCode);
        }
    }

    void fill(int row, int col, int replace, int replaceWith) {
        if (maze[row][col] == replace) {
            maze[row][col] = replaceWith;
            fill(row+1,col,replace,replaceWith);
            fill(row-1,col,replace,replaceWith);
            fill(row,col+1,replace,replaceWith);
            fill(row,col-1,replace,replaceWith);
        }
    }



}







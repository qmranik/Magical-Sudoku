package sample;

//import sample.GameBoard;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static boolean notSolvable;
    @FXML
    Button button_one;
    @FXML
    Button button_two;
    @FXML
    Button button_three;
    @FXML
    Button button_four;
    @FXML
    Button button_five;
    @FXML
    Button button_six;
    @FXML
    Button button_seven;
    @FXML
    Button button_eight;
    @FXML
    Button button_nine;
    @FXML
    Canvas canvas;
    GameBoard gameboard;
    // Player selected cell integers
    int player_selected_row;
    int player_selected_col;
    int x, y;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gameboard = new GameBoard();
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
        player_selected_row = 0;
        player_selected_col = 0;
        notSolvable = false;
    }


    public void drawOnCanvas(GraphicsContext context) {

        context.clearRect(0, 0, 450, 450);
        // draw white rounded rectangles for our board
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // finds the y position of the cell, by multiplying the row number by 50, which is the height of a row in pixels
                // then adds 2, to add some offset
                int position_y = row * 50 + 2;
                // finds the x position of the cell, by multiplying the column number by 50, which is the width of a column in pixels
                // then add 2, to add some offset
                int position_x = col * 50 + 2;
                // defines the width of the square as 46 instead of 50, to account for the 4px total of blank space caused by the offset
                // as we are drawing squares, the height is going to be the same
                int width = 46;
                // set the fill color to white (you could set it to whatever you want)
                context.setFill(Color.WHITE);
                // draw a rounded rectangle with the calculated position and width. The last two arguments specify the rounded corner arcs width and height.
                // Play around with those if you want.
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }

        // draw highlight around selected cell
        // set stroke color to res
        context.setStroke(Color.RED);
        // set stroke width to 5px
        context.setLineWidth(5);
        // draw a strokeRoundRect using the same technique we used for drawing our board.
        context.strokeRoundRect(player_selected_col * 50 + 2, player_selected_row * 50 + 2, 46, 46, 10, 10);


        int[][] intro = gameboard.getSolution();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // finds the y position of the cell, by multiplying the row number by 50, which is the height of a row in pixels
                // then adds 2, to add some offset
                int position_y = row * 50 + 30;
                // finds the x position of the cell, by multiplying the column number by 50, which is the width of a column in pixels
                // then add 2, to add some offset
                int position_x = col * 50 + 20;
                // set the fill color to black (you could set it to whatever you want)
                context.setFill(Color.BLACK);
                // set the font, from a new font, constructed from the system one, with size 20
                context.setFont(new Font(20));
                // check if value of coressponding array position is not 0
                if (intro[row][col] != 0) {
                    // draw the number
                    context.fillText(intro[row][col] + "", position_x, position_y);
                }
            }
        }

        // draw the players numbers from our GameBoard instance
        int[][] player = gameboard.getPlayer();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // finds the y position of the cell, by multiplying the row number by 50, which is the height of a row in pixels
                // then adds 2, to add some offset
                int position_y = row * 50 + 30;
                // finds the x position of the cell, by multiplying the column number by 50, which is the width of a column in pixels
                // then add 2, to add some offset
                int position_x = col * 50 + 20;
                // set the fill color to purple (you could set it to whatever you want)
                context.setFill(Color.PURPLE);
                // set the font, from a new font, constructed from the system one, with size 20
                context.setFont(new Font(22));
                // check if value of coressponding array position is not 0
                if (player[row][col] != 0) {
                    // draw the number
                    context.fillText(player[row][col] + "", position_x, position_y);
                }
            }
        }


        if (notSolvable == true) {

            // Can't be solved state

            context.clearRect(0, 0, 450, 450);
            context.setFill(Color.RED);
            context.setFont(new Font(36));
            context.fillText("CAN'T BE SOLVED!!!", 150, 250);
        }
    }

    public void canvasMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                int mouse_x = (int) event.getX();
                int mouse_y = (int) event.getY();

                // convert the mouseX and mouseY into rows and cols
                // we are going to take advantage of the way integers are treated and we are going to divide by a cell's width
                // this way any value between 0 and 449 for x and y is going to give us an integer from 0 to 8, which is exactly what we are after
                player_selected_row = mouse_y / 50;
                player_selected_col = mouse_x / 50;

                //get the canvas graphics context and redraw

                drawOnCanvas(canvas.getGraphicsContext2D());
            }
        });
    }


    public void buttonOnePressed() {
        gameboard.modifyPlayer(1, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonTwoPressed() {
        gameboard.modifyPlayer(2, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonThreePressed() {
        gameboard.modifyPlayer(3, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonFourPressed() {
        gameboard.modifyPlayer(4, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonFivePressed() {
        gameboard.modifyPlayer(5, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonSixPressed() {
        gameboard.modifyPlayer(6, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }


    public void buttonSevenPressed() {
        gameboard.modifyPlayer(7, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonEightPressed() {
        gameboard.modifyPlayer(8, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonNinePressed() {
        gameboard.modifyPlayer(9, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonRandPressed() {

        // Randomly fills the array from library

        int[][] samp = gameboard.getInitial();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameboard.player[i][j] = samp[i][j];
                gameboard.solution[i][j] = samp[i][j];
            }
        }

        drawOnCanvas(canvas.getGraphicsContext2D());

    }

    public void buttonSolvePressed() {

        // Here the Magic happens
        // this function call tries to solve the sudoku using backtracking

        if (SolveIt()) {
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            notSolvable = true;
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }


    public Boolean SolveIt() {

        if (!Pruning()) {
            return true;
        }

        for (int k = 1; k <= 9; k++) {
            if (isOk(x, y, k)) {
                gameboard.player[x][y] = k;
                if (SolveIt()) {
                    return true;
                }
                gameboard.player[x][y] = 0;
            }
        }

        return false;
    }

    // this function Predicts x and y so that the sudoku can be solved very efficiently

    public Boolean Pruning() {

        // maxa helps to predict points
        int maxa = 100;
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                if (gameboard.player[i][j] != 0) continue;

                int lowestCountPossible = 0;
                for (int k = 1; k <= 9; k++) {
                    if (isOk(i, j, k))
                        lowestCountPossible++;
                }
                if (lowestCountPossible < maxa) {
                    maxa = lowestCountPossible;
                    x = i;
                    y = j;
                }
            }
        }
        return maxa != 100;
    }

    public boolean checkCol(int i, int num) {
        for (int j = 0; j < 9; j++)
            if (gameboard.player[j][i] == num) {
                return true;
            }
        return false;
    }

    public boolean checkRow(int i, int num) {
        for (int j = 0; j < 9; j++)
            if (gameboard.player[i][j] == num) {
                return true;
            }
        return false;
    }

    public boolean checkGrid(int i, int j, int num) {
        int row = i - (i % 3);
        int col = j - (j % 3);

        for (int k = row; k < row + 3; k++) {
            for (int l = col; l < col + 3; l++) {
                if (gameboard.player[k][l] == num)
                    return true;
            }
        }
        return false;
    }

    public boolean isOk(int i, int j, int num) {
        return (!(checkCol(j, num))) && (!(checkRow(i, num))) && (!(checkGrid(i, j, num)));
    }

    public void ClearAll() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameboard.player[i][j] = 0;
            }
        }
        gameboard = new GameBoard();
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
        player_selected_row = 0;
        player_selected_col = 0;
        notSolvable = false;
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    class GameBoard {

        private final int[][] solution;
        private final int[][][] initial;
        private final int[][] player;

        public GameBoard() {

            solution = new int[9][9];

            initial = new int[][][]
                    {{
                            {0, 0, 0, 4, 0, 0, 0, 9, 0},
                            {6, 0, 7, 0, 0, 0, 8, 0, 4},
                            {0, 1, 0, 7, 0, 9, 0, 0, 3},
                            {9, 0, 1, 0, 7, 0, 0, 3, 0},
                            {0, 0, 2, 0, 0, 0, 9, 0, 0},
                            {0, 5, 0, 0, 4, 0, 1, 0, 7},
                            {3, 0, 0, 5, 0, 2, 0, 7, 0},
                            {4, 0, 6, 0, 0, 0, 3, 0, 1},
                            {0, 7, 0, 0, 0, 4, 0, 0, 0}
                    }, {
                            {0, 3, 8, 0, 6, 0, 7, 0, 2},
                            {6, 9, 0, 3, 2, 0, 0, 1, 4},
                            {0, 1, 0, 7, 8, 9, 5, 0, 3},
                            {9, 0, 1, 2, 0, 8, 0, 3, 5},
                            {0, 6, 0, 0, 5, 3, 9, 4, 0},
                            {8, 0, 3, 9, 0, 0, 1, 0, 0},
                            {0, 8, 0, 5, 1, 0, 4, 7, 6},
                            {4, 2, 0, 0, 9, 7, 0, 5, 1},
                            {0, 0, 5, 6, 0, 4, 2, 0, 0}
                    }, {
                            {0, 3, 8, 0, 6, 0, 0, 9, 2},
                            {6, 9, 0, 3, 2, 0, 8, 1, 0},
                            {0, 0, 4, 7, 0, 9, 5, 0, 3},
                            {9, 4, 0, 2, 7, 0, 0, 3, 0},
                            {0, 6, 0, 1, 0, 3, 9, 0, 8},
                            {8, 5, 0, 0, 4, 0, 1, 2, 0},
                            {3, 8, 0, 5, 1, 2, 0, 7, 6},
                            {0, 2, 6, 0, 9, 0, 3, 0, 1},
                            {0, 7, 0, 6, 0, 0, 2, 8, 0}
                    }, {
                            {0, 0, 0, 4, 0, 0, 0, 9, 0},
                            {0, 9, 0, 3, 2, 5, 8, 0, 4},
                            {0, 0, 4, 0, 0, 0, 0, 6, 3},
                            {0, 4, 0, 2, 7, 8, 6, 0, 0},
                            {0, 0, 2, 1, 0, 3, 9, 0, 8},
                            {8, 0, 0, 0, 4, 0, 1, 0, 7},
                            {0, 0, 9, 5, 0, 2, 0, 7, 0},
                            {0, 2, 0, 8, 9, 7, 3, 0, 1},
                            {0, 0, 0, 6, 0, 4, 0, 0, 0}
                    }};

            player = new int[9][9];
        }

        public int[][] getInitial() {
            Random rand = new Random();
            int k = rand.nextInt(3);
            return initial[k];
        }

        public int[][] getSolution() {
            return solution;
        }

        public int[][] getPlayer() {
            return player;
        }

        public void modifyPlayer(int val, int row, int col) {

            if (player[row][col] == 0) {

                if (val >= 0 && val <= 9)
                    player[row][col] = val;
                else
                    System.out.println("Value passed to player falls out of range");
            }

        }

    }

}


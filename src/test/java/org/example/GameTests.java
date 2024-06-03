package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GameTests {
    private Game game;

    @BeforeEach
    public void Setup() {
        game = new Game();
    }

    @Test
    public void TestCrossWins() {
        char[] board = {
                'X', 'O', 'X',
                'O', 'X', 'O',
                ' ', ' ', 'X'
        };
        game.symbol = 'X';
        assertEquals(game.checkState(board), State.XWIN);
    }

    @Test
    public void TestZerosWins() {
        char[] board = {
                'O', 'X', ' ',
                ' ', 'O', 'X',
                'X', ' ', 'O'
        };
        game.symbol = 'O';
        assertEquals(game.checkState(board), State.OWIN);
    }

    @Test
    public void TestDraw() {
        char[] board = {
                'X', 'O', 'X',
                'X', 'O', 'O',
                'O', 'X', 'X'
        };
        assertEquals(game.checkState(board), State.DRAW);
    }

    @Test
    public void TestGameStarted() {
        char[] board = {
                ' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        };
        assertEquals(game.checkState(board), State.PLAYING);
    }

    @Test
    public void TestGameInit() {
        assertEquals(game.state, State.PLAYING);
        assertEquals(game.player1.symbol, 'X');
        assertEquals(game.player2.symbol, 'O');
        assertEquals(game.board[0], ' ');
        assertEquals(game.board.length, 9);
    }

    
    @Test
    public void TestGenMovesEmpty() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(9, moves.size());
    }

    @Test
    public void TestGenMovesRow() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(6, moves.size());
    }

    @Test
    public void TestPlayingPos() {
        int result = game.evaluatePosition(game.board, game.player1);
        assertEquals(-1, result);
    }

    @Test
    public void TestDrawPos() {
        game.symbol = 'X';
        game.board = new char[] {
                'X', 'X', 'O',
                'O', 'O', 'X',
                'X', 'X', 'O'
        };
        int result = game.evaluatePosition(game.board, game.player1);
        assertEquals(0, result);
    }

    @Test
    public void TestPosPlayerOneWin() {
        game.symbol = 'X';
        game.board[0] = game.player1.symbol;
        game.board[1] = game.player1.symbol;
        game.board[2] = game.player1.symbol;
        int result = game.evaluatePosition(game.board, game.player1);
        assertEquals(+Game.INF, result);
    }

    @Test
    public void TestPosPlayerOneLoose() {
        game.symbol = 'O';
        game.board[0] = game.player2.symbol;
        game.board[1] = game.player2.symbol;
        game.board[2] = game.player2.symbol;
        int result = game.evaluatePosition(game.board, game.player1);
        assertEquals(-Game.INF, result);
    }

    @Test
    public void TestCorrMoves() {
        game.board = new char[] {
                'X', 'X', ' ',
                'O', 'O', 'X',
                'O', 'O', 'X'};
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move >= 1 && move <= 9);
    }
}

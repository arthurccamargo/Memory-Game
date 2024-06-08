package com.memory.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final int rows;
    private final int columns;
    private Piece[][] pieces;
    private List<Integer> pairPieces = new ArrayList<>();

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public Piece[][] getPieces() {return pieces;}

    public void createPieces() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pieces[i][j] = new Piece();
            }
        }
    }

    public void randomPieces() {
        Random rand = new Random();
        int cont=0;
        int flag;

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                flag = 0;
                while (flag == 0) {
                    // I need pairs pieces - depends on the board
                    int random = rand.nextInt((rows * columns) / 2);
                    pairPieces.add(random);
                    // counts how many times the random number is in the list to form pairs,
                    // there can be 2 identical numbers in the list
                    for (Integer number : pairPieces) {
                        // if this number is already in the list
                        if (number == random) {
                            cont++;
                        }
                    }
                    // if there is more than one pair
                    if (cont > 2) {
                        // remove the last number you added in list
                        pairPieces.remove(pairPieces.size() - 1);
                    } else {
                        flag = 1;
                        // set id piece
                        pieces[i][j].setIdPiece(random);
                        System.out.println(pieces[i][j].getIdPiece() + ", ");
                    }
                    cont = 0;
                }
            }
        }
        pairPieces.clear();
    }
}

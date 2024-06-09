package com.memory.Models;

import com.memory.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final GameTimer timer;
    private final Board board;
    private final User user;
    private int startGame;

    private Model() {
        this.board = new Board(4, 4);
        this.viewFactory = new ViewFactory();
        this.timer = new GameTimer();
        this.user = new User();
        this.startGame = 0;
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {return viewFactory;}

    public GameTimer getGameTimer() {return timer;}

    public Board getBoard() {return board;}

    public User getUser() {return user;}

    public int getStartGame() {return startGame;}

    public void setStartGame(int startGame) {
        this.startGame = startGame;
    }
}

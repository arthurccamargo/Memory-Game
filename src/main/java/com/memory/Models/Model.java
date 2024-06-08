package com.memory.Models;

import com.memory.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final GameTimer timer;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.timer = new GameTimer();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {return viewFactory;}

    public GameTimer getGameTimer() {return timer;
    }
}

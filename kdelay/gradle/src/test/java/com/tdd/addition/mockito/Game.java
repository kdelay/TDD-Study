package com.tdd.addition.mockito;

public class Game {

    private GameNumGen gameNumGen;

    public Game(GameNumGen gameNumGen) {
        this.gameNumGen = gameNumGen;
    }

    public void init(GameLevel level) {
        gameNumGen.generate(level);
    }
}

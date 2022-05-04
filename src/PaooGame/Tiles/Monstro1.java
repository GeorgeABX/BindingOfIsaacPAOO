package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Monstro1 extends Tile {
    public Monstro1(int id) {
        super(Assets.monstro1, id);
    }
    @Override
    public boolean IsSolid()
    {
        return false;
    }
}

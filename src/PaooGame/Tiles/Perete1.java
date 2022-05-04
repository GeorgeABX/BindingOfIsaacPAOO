package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Perete1 extends Tile {
    public Perete1(int id) {
        super(Assets.perete1, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}

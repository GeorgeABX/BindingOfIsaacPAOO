package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Isaac1 extends Tile {
    public Isaac1(int id) {
        super(Assets.isaac1, id);
    }
    @Override
    public boolean IsSolid()
    {
        return false;
    }
}

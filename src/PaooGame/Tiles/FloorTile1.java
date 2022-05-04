package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class FloorTile1 extends Tile {
    public FloorTile1(int id) {
        super(Assets.floorTile1, id);
    }
    @Override
    public boolean IsSolid()
    {
        return false;
    }
}

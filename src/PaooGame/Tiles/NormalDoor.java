package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class NormalDoor extends Tile {
    public NormalDoor(int id) {
        super(Assets.normalDoor, id);
    }
    @Override
    public boolean IsSolid()
    {
        return false;
    }
}

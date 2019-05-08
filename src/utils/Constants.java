package utils;

import model.Direction;

import java.util.HashMap;

public class Constants {
    public static final int FIELDWIDTH = 20;
    public static final int DIMENSION = 400;
    public static final int OFFSET = 20;
    public static final int FIELDCOUNT = DIMENSION / FIELDWIDTH;
    public static final int SPEED = 1000 / (100 / FIELDWIDTH + 10);
    public static final int BLOCKCOUNT = 10;

    static final HashMap<Character, Direction> KEYDIRECTIONBINDING = new HashMap<Character, Direction>() {{
        put('w', Direction.UP);
        put('s', Direction.DOWN);
        put('a', Direction.LEFT);
        put('d', Direction.RIGHT);
    }};
}

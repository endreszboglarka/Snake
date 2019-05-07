package utils;

import model.Direction;

import java.util.HashMap;

public class Constants {
    public static final int FIELDWIDTH = 50;
    public static final int DIMENSION = 500;
    public static final int SPEED = 1000 / 5;
    public static final HashMap<Character, Direction> KEYDIRECTIONBINDING = new HashMap<Character, Direction>() {{
        put('w', Direction.UP);
        put('s', Direction.DOWN);
        put('a', Direction.LEFT);
        put('d', Direction.RIGHT);
    }};
}

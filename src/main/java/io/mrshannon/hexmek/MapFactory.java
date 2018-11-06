package io.mrshannon.hexmek;

import java.io.IOException;

public interface MapFactory {

    /**
     * Create hexagonal map.
     *
     * @return new game map
     */
    public abstract HexMap createMap() throws IOException;

}

package io.mrshannon.hexmek;

import java.io.IOException;

public interface MapFactoryInterface {

    /**
     * Create hexagonal map.
     *
     * @return new game map
     */
    public abstract HexMap createMap() throws IOException;

}

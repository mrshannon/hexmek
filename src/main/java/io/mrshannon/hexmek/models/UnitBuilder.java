package io.mrshannon.hexmek.models;

/**
 * Unit builder interface.
 */
public interface UnitBuilder {

    /**
     * Build a new unit.
     *
     * @param id unique, single character id of unit
     * @param map map to place the unit on
     * @param hex location to put the unit
     * @param facing the facing direction of the new unit
     * @return the new unit
     */
    Unit build(char id, HexMap map, Hex hex, Direction facing);

}

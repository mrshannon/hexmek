package io.mrshannon.hexmek.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MapLoader implements MapFactory {

    private String mapName;

    public MapLoader(String mapName) {
        this.mapName = mapName;
    }

    @Override
    public HexMap createMap() throws IOException {
        String parts[];
        String line;
        int width = 0;
        int height = 0;
        Tile tile;
        var tiles = new HashMap<Hex, Tile>();

        // open file
        var classLoader = ClassLoader.getSystemClassLoader();
        var stream = classLoader.getResourceAsStream("maps/" + mapName + ".hmap");
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        if (in == null) {
            throw new IOException("Cannot load map: " + mapName);
        }

        // parse each line
        while ((line = in.readLine()) != null) {

            if (line.startsWith("size")) { // parse size line
                parts = line.split("\\s+");
                if (parts.length != 3) {
                    throw new IOException("Invalid line: " + line);
                }
                try {
                    width = Integer.parseInt(parts[1]);
                    height = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid line: " + line);
                }

            } else if (line.startsWith("hex")) { // parse hex line
                parts = line.split("\\s+");
                if (parts.length != 3 || parts[1].length() != 4) {
                    throw new IOException("Invalid line: " + line);
                }
                tile = TileFactory.createTile(parts[2]);
                if (tile == null) {
                    throw new IOException("Invalid line: " + line);
                }
                try {
                    tiles.put(new Hex(Integer.parseInt(parts[1].substring(0, 2)),
                            Integer.parseInt(parts[1].substring(2, 4))), tile);
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid line: " + line);
                }

            } else { // invalid line
                throw new IOException("Invalid line: " + line);
            }
        }

        // construct map
        return new HexMap(width, height, TileFactory.createTile("clear:1"), tiles);
    }
}

package io.mrshannon.hexmek;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            var map = (new MapLoader("default")).createMap();
            var view = new MapView(map);
            view.render();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

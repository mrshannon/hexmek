package io.mrshannon.hexmek;

public class MapView implements View {

    private HexMap map;

    public MapView(HexMap map) {
        this.map = map;
    }

    @Override
    public void render() {
        printColumnHeadings();
        printTop();
        for (int row = 1; row <= map.getHeight(); ++row) {
            printRow(row);
        }
    }

    private void printColumnHeadings() {
        System.out.print("     ");
        for (int i = 1; i <= map.getWidth(); ++i) {
            System.out.print(String.format(" %2d", i));
        }
        System.out.print("\n");
    }

    private void printTop() {
        System.out.print("     ");
        for (int i = 1; i <= map.getWidth()/2; ++i) {
            System.out.print(" __   ");
        }
        if ((map.getWidth() & 1) == 1) {
            System.out.print(" __");
        }
        System.out.print("\n");
    }

    private void printRow(int rowNumber) {
        System.out.print("     ");
        for (int i = 1; i <= map.getWidth()/2; ++i) {
            System.out.print(String.format("/  \\__", i));
        }
        if ((map.getWidth() & 1) == 1) {
            System.out.print("/  \\");
        }
        System.out.print("\n");
        System.out.print(String.format(" %2d  ", rowNumber));
        for (int i = 1; i <= map.getWidth()/2; ++i) {
            System.out.print(String.format("\\__/  ", i));
        }
        if ((map.getWidth() & 1) == 1) {
            System.out.print("\\__/");
        }
        System.out.print("\n");
    }

}

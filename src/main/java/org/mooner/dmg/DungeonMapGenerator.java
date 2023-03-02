package org.mooner.dmg;

import java.util.Random;
import java.util.Scanner;

public class DungeonMapGenerator {
    public final char[][] map;
    private final int level;

    //    public int roomL;
    public int puzzle;
    public int room2;
    public int room3;
    public int room4;
    public int roomFull;

    public DungeonMapGenerator(int level) {
        this.level = level;
        this.map = new char[level][level];

        Random random = new Random();
        if (level >= 5) {
            roomFull = (int) Math.ceil((level - 4) / 1.5);
//            for (int i = 0; i < level - 4; i++) {
//                switch (random.nextInt(2)) {
//                    case 0 -> roomL = 1;
//                    case 1 -> roomFull = 1;
//                }
//            }
        }
        puzzle = 2 + (int) Math.ceil(Math.max(0, level - 3) / 2d);
        for (int i = 0; i < level - 1; i++) {
            int next = random.nextInt(16);
            if (next < 10) {
                room2++;
            } else if (next < 13) {
                room3++;
            } else if (next < 15) {
                room4++;
            }
        }
    }

    private void generate() {
        Random random = new Random();
        map[level - 1][random.nextInt(level)] = '#';
        map[0][random.nextInt(level)] = '$';

        int x, y;
        char index = 'A';
        main:
        while (true) {
            x = random.nextInt(level);
            y = random.nextInt(level);
            if (roomFull > 0) {
//                System.out.println("ROOM FULL");
                x = Math.min(x, level - 2);
                y = Math.min(y, level - 2);
                for (int x1 = 0; x1 < 2; x1++) {
                    for (int y1 = 0; y1 < 2; y1++) {
                        if (map[x + x1][y + y1] != 0) continue main;
                    }
                }
                roomFull--;
                for (int x1 = 0; x1 < 2; x1++) {
                    for (int y1 = 0; y1 < 2; y1++) {
                        map[x + x1][y + y1] = index;
                    }
                }
            } else if (room4 > 0) {
//                System.out.println("ROOM 4");
                switch (random.nextInt(2)) {
                    case 0 -> {
                        x = random.nextInt(level - 3);
                        y = random.nextInt(level - 1);
                        for (int i = 0; i < 4; i++) {
                            if (map[x + i][y] != 0) continue main;
                        }
                        room4--;
                        for (int i = 0; i < 4; i++) {
                            map[x + i][y] = index;
                        }
                    }
                    case 1 -> {
                        x = random.nextInt(level - 1);
                        y = random.nextInt(level - 3);
                        for (int i = 0; i < 4; i++) {
                            if (map[x][y + i] != 0) continue main;
                        }
                        room4--;
                        for (int i = 0; i < 4; i++) {
                            map[x][y + i] = index;
                        }
                    }
                }
            } else if (room3 > 0) {
//                System.out.println("ROOM 3");
                switch (random.nextInt(2)) {
                    case 0 -> {
                        x = random.nextInt(level - 1);
                        y = random.nextInt(level - 2);
                        for (int i = 0; i < 3; i++) {
                            if (map[x][y + i] != 0) continue main;
                        }
                        room3--;
                        for (int i = 0; i < 3; i++) {
                            map[x][y + i] = index;
                        }
                    }
                    case 1 -> {
                        x = random.nextInt(level - 2);
                        y = random.nextInt(level - 1);
                        for (int i = 0; i < 3; i++) {
                            if (map[x + i][y] != 0) continue main;
                        }
                        room3--;
                        for (int i = 0; i < 3; i++) {
                            map[x + i][y] = index;
                        }
                    }
                }
            } else if (room2 > 0) {
//                System.out.println("ROOM 2");
                switch (random.nextInt(2)) {
                    case 0 -> {
                        x = random.nextInt(level - 1);
                        y = random.nextInt(level - 1);
                        for (int i = 0; i < 2; i++) {
                            if (map[x][y + i] != 0) continue main;
                        }
                        room2--;
                        for (int i = 0; i < 2; i++) {
                            map[x][y + i] = index;
                        }
                    }
                    case 1 -> {
                        x = random.nextInt(level - 1);
                        y = random.nextInt(level - 1);
                        for (int i = 0; i < 2; i++) {
                            if (map[x + i][y] != 0) continue main;
                        }
                        room2--;
                        for (int i = 0; i < 2; i++) {
                            map[x + i][y] = index;
                        }
                    }
                }
            } else break;
            index++;
//            Thread.sleep(250);
//            print();
//            break;
        }
    }

    private void print() {
        for (int x = 0; x < level; x++) {
            for (int y = 0; y < level; y++) {
                if (map[x][y] == 0) {
                    System.out.print("0 ");
                    continue;
                }
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            while (true) {
                try {
                    DungeonMapGenerator generator = new DungeonMapGenerator(scanner.nextInt());
                    generator.generate();
                    generator.print();
                    break;
                } catch (Throwable ignore) {
                }
            }
        }
    }
}

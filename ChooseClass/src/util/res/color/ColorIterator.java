package util.res.color;

import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.LinkedList;

public class ColorIterator implements Iterable<Color>{
    private static LinkedList<Color> colors;
    private Iterator<Color> iterator;

    public ColorIterator(){
       colors = new LinkedList<>();
        colors.add(Color.valueOf("#FFD700"));
        colors.add(Color.valueOf("#FFAEB9"));
        colors.add(Color.valueOf("#B0E2FF"));
        colors.add(Color.valueOf("#9F79EE"));
        colors.add(Color.valueOf("#DA70D6"));

        colors.add(Color.valueOf("#87CEEB"));
        colors.add(Color.valueOf("#AFEEEE"));
        colors.add(Color.valueOf("#98FB98"));
        colors.add(Color.valueOf("#FFFACD"));
        colors.add(Color.valueOf("#FFDEAD"));

        colors.add(Color.valueOf("#FFA07A"));
        colors.add(Color.valueOf("#FFF5EE"));
        colors.add(Color.valueOf("#98FB98"));
        colors.add(Color.valueOf("#40E0D0"));
        colors.add(Color.valueOf("#FFF0F5"));

        colors.add(Color.valueOf("#D1EEEE"));
        colors.add(Color.valueOf("#FFD700"));
        colors.add(Color.valueOf("#FFAEB9"));
        colors.add(Color.valueOf("#B0E2FF"));
        colors.add(Color.valueOf("#9F79EE"));
        colors.add(Color.valueOf("#87CEEB"));
        colors.add(Color.valueOf("#AFEEEE"));
        colors.add(Color.valueOf("#98FB98"));
        colors.add(Color.valueOf("#FFFACD"));
        colors.add(Color.valueOf("#FFDEAD"));

        colors.add(Color.valueOf("#FFA07A"));
        colors.add(Color.valueOf("#FFF5EE"));
        colors.add(Color.valueOf("#98FB98"));
        colors.add(Color.valueOf("#40E0D0"));
        colors.add(Color.valueOf("#FFF0F5"));

        colors.add(Color.valueOf("#D1EEEE"));
        colors.add(Color.valueOf("#FFD700"));
        colors.add(Color.valueOf("#FFAEB9"));
        colors.add(Color.valueOf("#B0E2FF"));
        colors.add(Color.valueOf("#9F79EE"));
        colors.add(Color.valueOf("#87CEEB"));
        colors.add(Color.valueOf("#AFEEEE"));
        colors.add(Color.valueOf("#98FB98"));
        colors.add(Color.valueOf("#FFFACD"));
        colors.add(Color.valueOf("#FFDEAD"));

        colors.add(Color.valueOf("#FFA07A"));
        colors.add(Color.valueOf("#FFF5EE"));
        colors.add(Color.valueOf("#98FB98"));
        colors.add(Color.valueOf("#40E0D0"));
        colors.add(Color.valueOf("#FFF0F5"));

        colors.add(Color.valueOf("#D1EEEE"));
        colors.add(Color.valueOf("#FFD700"));
        colors.add(Color.valueOf("#FFAEB9"));
        colors.add(Color.valueOf("#B0E2FF"));
        colors.add(Color.valueOf("#9F79EE"));
        colors.add(Color.valueOf("#87CEEB"));
        colors.add(Color.valueOf("#AFEEEE"));
        colors.add(Color.valueOf("#98FB98"));
        colors.add(Color.valueOf("#FFFACD"));
        colors.add(Color.valueOf("#FFDEAD"));

        colors.add(Color.valueOf("#FFA07A"));
        colors.add(Color.valueOf("#FFF5EE"));
        colors.add(Color.valueOf("#98FB98"));
        colors.add(Color.valueOf("#40E0D0"));
        colors.add(Color.valueOf("#FFF0F5"));

        colors.add(Color.valueOf("#D1EEEE"));
        colors.add(Color.valueOf("#FFD700"));
        colors.add(Color.valueOf("#FFAEB9"));
        colors.add(Color.valueOf("#B0E2FF"));
        colors.add(Color.valueOf("#9F79EE"));



        iterator = colors.iterator();

    }

    @Override
    public Iterator<Color> iterator() {
        return iterator;
    }
}

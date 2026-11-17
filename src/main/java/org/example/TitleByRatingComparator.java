package org.example;

import java.util.Comparator;

public class TitleByRatingComparator implements Comparator<Title> {
    @Override
    public int compare(Title t1, Title t2) {
        return Double.compare(t2.getRating(), t1.getRating());
    }
}
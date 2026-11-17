package org.example;

import java.util.Comparator;

public class TitleByYearComparator implements Comparator<Title> {
    @Override
    public int compare(Title t1, Title t2) {
        return Integer.compare(t1.getYear(), t2.getYear());
    }
}
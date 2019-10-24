package model;

import java.util.Comparator;

public  class SortById implements Comparator<Fauna> {
    public int compare(Fauna a, Fauna b)
    {
        return a.id - b.id;
    }
}

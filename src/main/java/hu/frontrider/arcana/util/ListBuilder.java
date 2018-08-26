package hu.frontrider.arcana.util;

import com.google.common.base.Function;

import java.util.List;


/**
 * This class is used to make it easier to fill up lists.
 * Mostly for reccipes.
 * */
public class ListBuilder<T> {


    private List<T> list;

    public ListBuilder(List<T> list) {
        this.list = list;
    }

    public ListBuilder<T> add(T element) {
        list.add(element);
        return this;
    }

    public List<T>  build() {

        return list;
    }

    public ListBuilder<T> print(Function<T,String> printer) {

        list.forEach((t)-> System.out.println(printer.apply(t)));

        return this;
    }
}

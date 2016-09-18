package com.enjoyxstudy.streamer;

import java.util.Comparator;
import java.util.stream.Stream;

public class SortedByComparatorStreamer<T, R> extends Streamer<T, R> {

    private final Streamer<T, R> parentStreamer;

    private final Comparator<? super R> comparator;

    public SortedByComparatorStreamer(Streamer<T, R> parentStreamer, Comparator<? super R> comparator) {
        super();
        this.parentStreamer = parentStreamer;
        this.comparator = comparator;
    }

    @Override
    protected Stream<R> apply(Stream<T> stream) {

        return parentStreamer.apply(stream).sorted(comparator);
    }
}

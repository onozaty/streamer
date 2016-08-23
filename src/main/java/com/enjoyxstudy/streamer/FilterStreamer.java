package com.enjoyxstudy.streamer;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterStreamer<T, R> extends Streamer<T, R> {

    private final Streamer<T, R> parentStreamer;

    private final Predicate<? super R> predicate;

    public FilterStreamer(Streamer<T, R> parentStreamer, Predicate<? super R> predicate) {
        super();
        this.parentStreamer = parentStreamer;
        this.predicate = predicate;
    }

    @Override
    protected Stream<R> apply(Stream<T> stream) {
        return parentStreamer.apply(stream).filter(predicate);
    }
}

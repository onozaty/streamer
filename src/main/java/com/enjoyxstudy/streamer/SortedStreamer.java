package com.enjoyxstudy.streamer;

import java.util.stream.Stream;

public class SortedStreamer<T, R> extends Streamer<T, R> {

    private final Streamer<T, R> parentStreamer;

    public SortedStreamer(Streamer<T, R> parentStreamer) {
        super();
        this.parentStreamer = parentStreamer;
    }

    @Override
    protected Stream<R> apply(Stream<T> stream) {

        return parentStreamer.apply(stream).sorted();
    }
}

package com.enjoyxstudy.streamer;

import java.util.stream.Stream;

public class DistinctStreamer<T, R> extends Streamer<T, R> {

    private final Streamer<T, R> parentStreamer;

    public DistinctStreamer(Streamer<T, R> parentStreamer) {
        super();
        this.parentStreamer = parentStreamer;
    }

    @Override
    protected Stream<R> apply(Stream<T> stream) {

        return parentStreamer.apply(stream).distinct();
    }
}

package com.enjoyxstudy.streamer;

import java.util.stream.Stream;

public class LimitStreamer<T, R> extends Streamer<T, R> {

    private final Streamer<T, R> parentStreamer;

    private final long maxSize;

    public LimitStreamer(Streamer<T, R> parentStreamer, long maxSize) {
        super();
        this.parentStreamer = parentStreamer;
        this.maxSize = maxSize;
    }

    @Override
    protected Stream<R> apply(Stream<T> stream) {

        return parentStreamer.apply(stream).limit(maxSize);
    }
}

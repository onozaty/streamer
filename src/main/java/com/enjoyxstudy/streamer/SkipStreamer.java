package com.enjoyxstudy.streamer;

import java.util.stream.Stream;

public class SkipStreamer<T, R> extends Streamer<T, R> {

    private final Streamer<T, R> parentStreamer;

    private final long num;

    public SkipStreamer(Streamer<T, R> parentStreamer, long num) {
        super();
        this.parentStreamer = parentStreamer;
        this.num = num;
    }

    @Override
    protected Stream<R> apply(Stream<T> stream) {

        return parentStreamer.apply(stream).skip(num);
    }
}

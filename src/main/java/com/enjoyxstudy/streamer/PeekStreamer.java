package com.enjoyxstudy.streamer;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class PeekStreamer<T, R> extends Streamer<T, R> {

    private final Streamer<T, R> parentStreamer;

    private final Consumer<? super R> action;

    public PeekStreamer(Streamer<T, R> parentStreamer, Consumer<? super R> action) {
        super();
        this.parentStreamer = parentStreamer;
        this.action = action;
    }

    @Override
    protected Stream<R> apply(Stream<T> stream) {

        return parentStreamer.apply(stream).peek(action);
    }
}

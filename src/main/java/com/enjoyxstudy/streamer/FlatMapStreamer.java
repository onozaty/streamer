package com.enjoyxstudy.streamer;

import java.util.function.Function;
import java.util.stream.Stream;

public class FlatMapStreamer<T, S, R> extends Streamer<T, R> {

    private final Streamer<T, S> parentStreamer;

    private final Function<? super S, Stream<? extends R>> mapper;

    public FlatMapStreamer(Streamer<T, S> parentStreamer, Function<? super S, Stream<? extends R>> mapper) {
        super();
        this.parentStreamer = parentStreamer;
        this.mapper = mapper;
    }

    @Override
    protected Stream<R> apply(Stream<T> stream) {
        return parentStreamer.apply(stream).flatMap(mapper);
    }
}

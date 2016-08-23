package com.enjoyxstudy.streamer;

import java.util.function.Function;
import java.util.stream.Stream;

public class MapStreamer<T, S, R> extends Streamer<T, R> {

    private final Streamer<T, S> parentStreamer;

    private final Function<? super S, ? extends R> mapper;

    public MapStreamer(Streamer<T, S> parentStreamer, Function<? super S, ? extends R> mapper) {
        super();
        this.parentStreamer = parentStreamer;
        this.mapper = mapper;
    }

    @Override
    protected Stream<R> apply(Stream<T> stream) {
        return parentStreamer.apply(stream).map(mapper);
    }
}

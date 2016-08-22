package com.enjoyxstudy.streamer;

import java.util.Collection;
import java.util.stream.Stream;

public abstract class Streamer<T, R> {

    public static <T> Streamer<T, T> create(Class<T> sourceClass) {
        return new BaseStreamer<T>();
    }

    protected abstract Stream<R> apply(Stream<T> stream);

    public Stream<R> build(Collection<T> source) {
        return build(source.stream());
    }

    public Stream<R> build(Stream<T> source) {
        return apply(source);
    }
}

package com.enjoyxstudy.streamer;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Streamer<T, R> {

    public static <T> Streamer<T, T> create(Class<T> sourceClass) {
        return new BaseStreamer<T>();
    }

    public static <T> Streamer<T, T> create() {
        return new BaseStreamer<T>();
    }

    protected abstract Stream<R> apply(Stream<T> stream);

    public Stream<R> build(Collection<T> source) {
        return build(source.stream());
    }

    public Stream<R> build(Stream<T> source) {
        return apply(source);
    }

    public Streamer<T, R> distinct() {
        return new DistinctStreamer<T, R>(this);
    }

    public <N> Streamer<T, N> flatMap(Function<? super R, Stream<? extends N>> mapper) {
        return new FlatMapStreamer<T, R, N>(this, mapper);
    }

    public Streamer<T, R> limit(long maxSize) {
        return new LimitStreamer<T, R>(this, maxSize);
    }

    public Streamer<T, R> filter(Predicate<? super R> predicate) {
        return new FilterStreamer<T, R>(this, predicate);
    }

    public <N> Streamer<T, N> map(Function<? super R, ? extends N> mapper) {
        return new MapStreamer<T, R, N>(this, mapper);
    }

    public Streamer<T, R> peek(Consumer<? super R> action) {
        return new PeekStreamer<T, R>(this, action);
    }

    public Streamer<T, R> skip(long num) {
        return new SkipStreamer<T, R>(this, num);
    }

    public Streamer<T, R> sorted() {
        return new SortedStreamer<T, R>(this);
    }

    public Streamer<T, R> sorted(Comparator<? super R> comparator) {
        return new SortedByComparatorStreamer<T, R>(this, comparator);
    }

}

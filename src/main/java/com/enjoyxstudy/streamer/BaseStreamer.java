package com.enjoyxstudy.streamer;

import java.util.stream.Stream;

public class BaseStreamer<T> extends Streamer<T, T> {

    @Override
    protected Stream<T> apply(Stream<T> stream) {

        return stream;
    }
}

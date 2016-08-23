package com.enjoyxstudy.streamer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamerTest {

    @Test
    public void testCreate() {

        Streamer<String, String> streamer = Streamer.create(String.class);

        assertThat(streamer, instanceOf(BaseStreamer.class));
    }

    @Test
    public void testFilter() {

        Streamer<String, String> streamer =
                Streamer.create(String.class).filter(x -> x.startsWith("*"));

        {
            List<String> actual =
                    streamer.build(Arrays.asList("aaa", "***", "*1", "2*"))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList("***", "*1")));
        }

        {
            List<String> actual =
                    streamer.build(Arrays.asList("*", ""))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList("*")));
        }
    }

    @Test
    public void testMap() {

        Streamer<String, Integer> streamer =
                Streamer.create(String.class).map(x -> Integer.parseInt(x));

        {
            List<Integer> actual =
                    streamer.build(Arrays.asList("1", "-1", "10", "2"))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList(1, -1, 10, 2)));
        }

        {
            List<Integer> actual =
                    streamer.build(Arrays.asList("1", "2"))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList(1, 2)));
        }
    }

}

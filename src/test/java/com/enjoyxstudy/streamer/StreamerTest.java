package com.enjoyxstudy.streamer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamerTest {

    @Test
    public void testCreate() {

        assertThat(Streamer.create(String.class), instanceOf(BaseStreamer.class));
        assertThat(Streamer.<String>create(), instanceOf(BaseStreamer.class));
    }

    @Test
    public void testDistinct() {

        Streamer<String, String> streamer =
                Streamer.create(String.class).distinct();

        {
            List<String> actual =
                    streamer.build(Arrays.asList("a", "a", "b", "c", "b"))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList("a", "b", "c")));
        }

        {
            List<String> actual =
                    streamer.build(Arrays.asList("a", "b"))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList("a", "b")));
        }
    }

    @Test
    public void testFlatMap() {

        Streamer<List<String>, String> streamer =
                Streamer.<List<String>>create().flatMap(x -> x.stream());

        {
            List<String> actual =
                    streamer.build(Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d")))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList("a", "b", "c", "d")));
        }

        {
            List<String> actual =
                    streamer.build(Arrays.asList(Arrays.asList("a")))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList("a")));
        }
    }

    @Test
    public void testLimit() {

        Streamer<Integer, Integer> streamer =
                Streamer.create(Integer.class).limit(2);

        {
            List<Integer> actual =
                    streamer.build(Arrays.asList(1))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList(1)));
        }

        {
            List<Integer> actual =
                    streamer.build(Arrays.asList(1, 2, 3, 4, 5))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList(1, 2)));
        }
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

    @Test
    public void testPeek() {

        List<Integer> peekResults = new ArrayList<Integer>();

        Streamer<Integer, Integer> streamer =
                Streamer.create(Integer.class).peek(x -> {
                    peekResults.add(x);
                });

        {
            peekResults.clear();
            List<Integer> actual =
                    streamer.build(Arrays.asList(1, 2, 3, 4))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList(1, 2, 3, 4)));
            assertThat(actual, is(peekResults));
        }
    }

    @Test
    public void testSkip() {

        Streamer<Integer, Integer> streamer =
                Streamer.create(Integer.class).skip(2);

        {
            List<Integer> actual =
                    streamer.build(Arrays.asList(1))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList()));
        }

        {
            List<Integer> actual =
                    streamer.build(Arrays.asList(1, 2, 3, 4, 5))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList(3, 4, 5)));
        }
    }

    @Test
    public void testSorted() {

        Streamer<Integer, Integer> streamer =
                Streamer.create(Integer.class).sorted();

        {
            List<Integer> actual =
                    streamer.build(Arrays.asList(2, 1, 5, 4, 3))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList(1, 2, 3, 4, 5)));
        }
    }

    @Test
    public void testSortedByComparator() {

        Streamer<Integer, Integer> streamer =
                Streamer.create(Integer.class).sorted(Comparator.reverseOrder());

        {
            List<Integer> actual =
                    streamer.build(Arrays.asList(2, 1, 5, 4, 3))
                            .collect(Collectors.toList());

            assertThat(actual, is(Arrays.asList(5, 4, 3, 2, 1)));
        }
    }
}

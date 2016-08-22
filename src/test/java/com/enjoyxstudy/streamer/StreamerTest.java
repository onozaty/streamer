package com.enjoyxstudy.streamer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StreamerTest {

    @Test
    public void testCreate() {

        Streamer<String, String> streamer = Streamer.create(String.class);

        assertThat(streamer, instanceOf(BaseStreamer.class));
    }

}

# streamer

```java
Streamer<String, Integer> streamer =
        Streamer.create(String.class)
                .map(x -> {
                    try {
                        return Integer.parseInt(x);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(x -> x != null)
                .sorted();

List<Integer> actual1 =
        streamer.build(Arrays.asList("a", "2", "1", "", "000"))
                .collect(Collectors.toList());
assertThat(actual1, is(Arrays.asList(0, 1, 2)));

List<Integer> actual2 =
        streamer.build(Arrays.asList("100", "abc"))
                .collect(Collectors.toList());
assertThat(actual2, is(Arrays.asList(100)));
```

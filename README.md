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

streamer.build(Arrays.asList("a", "2", "1", "", "000"))
        .collect(Collectors.toList()); // -> [0, 1, 2]

streamer.build(Arrays.asList("1", "2", "-1"))
        .collect(Collectors.toList()); // -> [-1, 1, 2]

streamer.build(Arrays.asList("a"))
        .collect(Collectors.toList()); // -> []

streamer.build(Arrays.asList("100", "abc"))
        .collect(Collectors.toList()); // -> [100]
```

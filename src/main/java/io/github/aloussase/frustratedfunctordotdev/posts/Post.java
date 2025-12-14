package io.github.aloussase.frustratedfunctordotdev.posts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record Post(
        String title,
        String date,
        String content,
        String summary,
        String path
) implements Comparable<Post> {

    @Override
    public int compareTo(Post o) {
        final var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        final var d1 = LocalDate.from(formatter.parse(this.date));
        final var d2 = LocalDate.from(formatter.parse(o.date));
        return d2.compareTo(d1);
    }
}

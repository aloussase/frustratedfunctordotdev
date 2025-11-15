package io.github.aloussase.frustratedfunctordotdev.posts;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostManagerImpl implements PostManager {
    private static final List<String> paths = List.of(
            "14112025-sealed-classes-in-java.md"
    );

    @Override
    public List<Post> getPosts() {
        return paths.stream().map(this::readPostFrom).filter(Optional::isPresent).map(Optional::get).toList();
    }

    @Override
    public Post getPost(String postPath) {
        return readPostFrom(postPath).orElseThrow(() -> new RuntimeException("Post does not exist"));
    }


    private Optional<Post> readPostFrom(String path) {
        try (
                final var st = Objects.requireNonNull(getClass().getResourceAsStream("/posts/" + path));
                final var reader = new BufferedReader(new InputStreamReader(st))
        ) {
            final var contents = reader.lines().collect(Collectors.joining("\n"));
            final var title = contents.lines().findFirst().map(s -> s.substring(2));

            final var date = contents.lines()
                    .filter(line -> line.startsWith("Date:"))
                    .map(line -> line.substring("Date: ".length()))
                    .findFirst();

            final var summary = contents.lines()
                    .skip(7)
                    .collect(Collectors.joining())
                    .chars()
                    .limit(50)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            if (title.isEmpty() || date.isEmpty())
                return Optional.empty();

            return Optional.of(new Post(title.get(), date.get(), contents, summary, path));
        } catch (IOException ignored) {
            return Optional.empty();
        }
    }
}

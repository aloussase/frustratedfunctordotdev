package io.github.aloussase.frustratedfunctordotdev.books;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookManagerImpl implements BookManager {

    private final JdbcTemplate jdbcTemplate;

    public BookManagerImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            final var id = rs.getInt("id");
            final var title = rs.getString("title");
            final var author = rs.getString("author");
            final var status = rs.getString("status");
            final var tags = (String[]) Optional.ofNullable(rs.getArray("tags"))
                    .map(ary -> {
                        try {
                            return ary.getArray();
                        } catch (SQLException e) {
                            return new String[0];
                        }
                    })
                    .orElseGet(() -> new String[0]);
            final var createdAt = rs.getTimestamp("created_at");
            final var lastUpdated = rs.getTimestamp("last_updated");
            return new Book(
                    id, author, title, status, tags.length == 1 && tags[0] == null ? List.of() : Arrays.asList(tags),
                    createdAt.toInstant(), lastUpdated.toInstant());
        }
    }

    @Override
    public List<Book> listBooks() {
        return jdbcTemplate.query("""
                select b.id, b.author, b.title, b.status, array_agg(t.name) as tags,
                       b.created_at, b.last_updated from
                    books b
                        left join books_tags bt on bt.tag_id = b.id
                        left join  book_tags t on t.id = bt.tag_id
                group by b.id
                order by last_updated desc;
                """, new BookRowMapper());
    }

    @Override
    public List<String> listTags() {
        return jdbcTemplate.query(
                "select name from book_tags",
                (ResultSet rs, int rowNum) -> rs.getString("name"));
    }
}

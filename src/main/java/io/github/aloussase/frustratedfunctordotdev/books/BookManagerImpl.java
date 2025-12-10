package io.github.aloussase.frustratedfunctordotdev.books;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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
            final var tags = (String[]) rs.getArray("tags").getArray();
            return new Book(
                    id, author, title, status, Arrays.asList(tags));
        }
    }

    @Override
    public List<Book> listBooks() {
        return jdbcTemplate.query("""
                                select b.id, b.author, b.title, b.status, array_agg(t.name) as tags from
                                    book_tags t
                                        inner join books_tags bt on bt.tag_id = t.id
                                        inner join books b on b.id = bt.book_id
                                group by b.id;
                """, new BookRowMapper());
    }
}

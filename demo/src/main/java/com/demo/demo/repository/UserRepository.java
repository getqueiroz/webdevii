package com.demo.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.demo.model.User;

@Repository
public class UserRepository {
    private static final String QUERY_INSERT_USER = """
            INSERT INTO users (username, created_at, updated_at)
            VALUES (:username, :createdAt, :updatedAt)
            """;

    private static final String QUERY_FIND_BY_ID = """
            SELECT id, username, created_at, updated_at FROM users WHERE id = :id
            """;

    private static final String QUERY_FIND_BY_USERNAME = """
            SELECT id, username, created_at, updated_at FROM users WHERE username = :username"
            """;

    private static final String QUERY_UPDATE_USER = """
            UPDATE users SET username  = :username, updated_at = :updatedAt WHERE id = :id
            """;

    private static final RowMapper<User> USER_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setCreatedAt(rs.getTimestamp("created_at")
                    .toLocalDateTime().atOffset(ZoneOffset.UTC));
            user.setUpdatedAt(rs.getTimestamp("updated_at")
                    .toLocalDateTime().atOffset(ZoneOffset.UTC));
            return user;
        }
    };

    private static final Number extractKey(KeyHolder kh, String key) {
        Number keyOutput = kh.getKey();
        if (keyOutput == null) {
            Object byName = kh.getKeys() != null ? kh.getKeys().get(key) : null;
            if (byName instanceof Number n) {
                keyOutput = n;
            }
        }
        if (keyOutput == null) {
            throw new IllegalStateException("ID não retornado pelo banco");
        }
        return keyOutput;
    }

    private final JdbcClient jdbc;

    public UserRepository(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public User insert(User user) {
        if (user == null || user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("username é obrigatório");
        }

        Timestamp nowTs = Timestamp.from(Instant.now());
        KeyHolder kh = new GeneratedKeyHolder();

        try {
            int rows = jdbc.sql(QUERY_INSERT_USER)
                    .param("username", user.getUsername().trim())
                    .param("createdAt", nowTs)
                    .param("updatedAt", nowTs)
                    .update(kh, "id");

            if (rows != 1) {
                throw new IllegalStateException("Falha ao inserir usuário (linhas afetadas != 1)");
            }

            OffsetDateTime now = nowTs.toLocalDateTime().atOffset(ZoneOffset.UTC);
            Number key = extractKey(kh, "id");
            user.setId(key.longValue());
            user.setCreatedAt(now);
            user.setUpdatedAt(now);
            return user;

        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Falha ao inserir usuário", ex);
        }
    }

    public Optional<User> findById(Long userId) {
        return jdbc.sql(QUERY_FIND_BY_ID)
                .param("id", userId)
                .query(USER_MAPPER)
                .optional();
    }

    public Optional<User> findByUsername(String username) {
        return jdbc.sql(QUERY_FIND_BY_USERNAME)
                .param("username", username)
                .query(USER_MAPPER)
                .optional();
    }

    @Transactional
    public User updateUser(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("id é obrigatório para atualização");
        }

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("username é obrigatório");
        }

        Timestamp nowTs = Timestamp.from(Instant.now());
        int rows = jdbc.sql(QUERY_UPDATE_USER)
                .param("username", user.getUsername().trim())
                .param("updatedAt", nowTs)
                .param("id", user.getId())
                .update();

        if (rows != 1) {
            throw new IllegalStateException("Falha ao atualizar: usuário não encontrado ou múltiplas linhas afetadas");
        }

        return findById(user.getId())
                .orElseThrow(() -> new IllegalStateException("Usuário atualizado não encontrado"));
    }

    public List<User> findAll() {
        //TODO: implement get all users

        throw new IllegalStateException("Não implementado");
    }

    @Transactional
    public void deleteById(Long id) {
        // TODO: implement delete process

        throw new IllegalStateException("Não implementado");
    }
}

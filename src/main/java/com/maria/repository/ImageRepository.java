package com.maria.repository;

import com.maria.model.image.SupportedMimeType;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * Created on 8/19/2017.
 */
@Repository
public class ImageRepository extends BaseRepository {

    public void saveImageForContestEntry(String fileName, SupportedMimeType mimeType, int contestEntryId) {
        String sql = "INSERT INTO contest_entry_images(file_name, mime_type, contest_entry_id) VALUES (:file_name, :mime_type, :contest_entry_id)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("file_name", fileName)
                .addValue("mime_type", mimeType.getContentType())
                .addValue("contest_entry_id", contestEntryId);
        namedJdbcTemplate.update(sql, parameterSource);
    }

    public void saveLogoForContest(String fileName, int contestId) {
        String sql = "UPDATE contests SET logo=:logo_name WHERE id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("logo_name", fileName)
                .addValue("id", contestId);
        namedJdbcTemplate.update(sql, parameterSource);
    }

    public void saveLogoForUser(String fileName, int userId) {
        String sql = "UPDATE users SET logo=:logo_name WHERE id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("logo_name", fileName)
                .addValue("id", userId);
        namedJdbcTemplate.update(sql, parameterSource);
    }

    public boolean imageNameExistsInContestEntry(String imageName) {
        String sql = "SELECT count(*) FROM contest_entry_images WHERE file_name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{imageName}, Integer.class) > 0;
    }

    public boolean imageNameExistsInUserLogo(String imageName) {
        String sql = "SELECT count(*) FROM users WHERE logo=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{imageName}, Integer.class) > 0;
    }

    public boolean imageNameExistsInContestLogo(String imageName) {
        String sql = "SELECT count(*) FROM contests WHERE logo=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{imageName}, Integer.class) > 0;
    }
}

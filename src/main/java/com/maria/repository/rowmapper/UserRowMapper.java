package com.maria.repository.rowmapper;

import com.maria.model.account.Gender;
import com.maria.model.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created on 8/10/2017.
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String name = resultSet.getString("name");
        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
        Gender gender = Gender.valueOf(resultSet.getString("gender"));

        return new User()
                .setBirthday(birthday)
                .setEmail(email)
                .setGender(gender)
                .setId(id)
                .setName(name);
    }
}

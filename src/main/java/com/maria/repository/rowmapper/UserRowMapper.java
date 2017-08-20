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
        int id = resultSet.getInt("u.id");
        String email = resultSet.getString("u.email");
        String name = resultSet.getString("u.name");
        LocalDate birthday = resultSet.getDate("u.birthday").toLocalDate();
        Gender gender = Gender.valueOf(resultSet.getString("u.gender"));
        String logoName = resultSet.getString("u.logo");

        return new User()
                .setBirthday(birthday)
                .setEmail(email)
                .setGender(gender)
                .setId(id)
                .setLogoName(logoName)
                .setName(name);
    }
}

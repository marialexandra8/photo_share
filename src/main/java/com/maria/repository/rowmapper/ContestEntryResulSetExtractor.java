package com.maria.repository.rowmapper;

import com.maria.model.account.Gender;
import com.maria.model.contest.ContestEntry;
import com.maria.model.user.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 8/20/2017.
 */
public class ContestEntryResulSetExtractor implements ResultSetExtractor<List<ContestEntry>> {
    @Override
    public List<ContestEntry> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Integer, List<String>> usersWithImagesMap = new HashMap<>();
        Map<Integer, User> usersMap = new HashMap<>();
        while (resultSet.next()) {
            int userId = resultSet.getInt("u.id");
            String imageName = resultSet.getString("ce.file_name");
            if (!usersMap.containsKey(userId)) {
                String name = resultSet.getString("u.name");
                String email = resultSet.getString("u.email");
                LocalDate birthday = resultSet.getDate("u.birthday").toLocalDate();
                Gender gender = Gender.valueOf(resultSet.getString("u.gender"));
                User user = new User()
                        .setEmail(email)
                        .setName(name)
                        .setBirthday(birthday)
                        .setGender(gender)
                        .setId(userId);
                usersMap.put(user.getId(), user);
            }

            if (usersWithImagesMap.containsKey(userId)) {
                usersWithImagesMap.get(userId).add(imageName);
            } else {
                usersWithImagesMap.put(userId, new ArrayList<>());
            }
        }
        return usersMap.values()
                .stream()
                .map(user -> new ContestEntry()
                        .setUser(user)
                        .setImagesName(usersWithImagesMap.get(user.getId())))
                .collect(Collectors.toList());
    }
}

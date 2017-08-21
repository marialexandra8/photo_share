package com.maria.repository.rowmapper;

import com.maria.model.account.Gender;
import com.maria.model.contest.ContestEntry;
import com.maria.model.review.Review;
import com.maria.model.user.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 8/20/2017.
 */
public class ContestEntryResulSetExtractor implements ResultSetExtractor<List<ContestEntry>> {
    @Override
    public List<ContestEntry> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Integer, ContestEntry> contestEntriesMap = new HashMap<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("ce.id");
            String imageName = resultSet.getString("cei.file_name");
            List<String> imagesName = new ArrayList<>();
            LocalDate check = resultSet.getDate("r.created_on").toLocalDate();
            Review review;
            if (check != null) {
                Integer reviewId = resultSet.getInt("r.id");
                int userReviewerId = resultSet.getInt("r.user_id");
                int rate = resultSet.getInt("r.rate");
                review = new Review()
                        .setContestEntryId(id)
                        .setId(reviewId)
                        .setRate(rate)
                        .setUserId(userReviewerId);
            }

            if (imageName != null) {
                imagesName.add(imageName);
            }

            if (!contestEntriesMap.containsKey(id)) {
                int contestId = resultSet.getInt("ce.contest_id");

                int userId = resultSet.getInt("u.id");
                String email = resultSet.getString("u.email");
                String name = resultSet.getString("u.name");
                LocalDate birthday = resultSet.getDate("u.birthday").toLocalDate();
                Gender gender = Gender.valueOf(resultSet.getString("u.gender"));
                String logoName = resultSet.getString("u.logo");

                User user = new User()
                        .setBirthday(birthday)
                        .setEmail(email)
                        .setGender(gender)
                        .setId(userId)
                        .setLogoName(logoName)
                        .setName(name);


                contestEntriesMap.put(id, new ContestEntry()
                        .setContestId(contestId)
                        .setId(id)
                        .setUser(user)
                        .setImagesName(imagesName));
            } else {
                ContestEntry contestEntry = contestEntriesMap.get(id);
                boolean checkForDuplicates = contestEntry.getImagesName().stream()
                        .noneMatch(addImageName -> addImageName.equals(imageName));
                if (checkForDuplicates) {
                    imagesName.addAll(contestEntry.getImagesName());
                    contestEntriesMap.get(id)
                            .setImagesName(imagesName);
                }
            }

        }
        return new ArrayList<>(contestEntriesMap.values());
    }
}

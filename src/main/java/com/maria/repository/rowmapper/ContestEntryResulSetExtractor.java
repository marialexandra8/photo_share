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
            int reviewId = resultSet.getInt("r.id");
            int userIdForReview = resultSet.getInt("r.user_id");
            int contestEntryId = resultSet.getInt("r.contest_entry_id");

            Review review = new Review()
                    .setUserId(userIdForReview)
                    .setId(reviewId)
                    .setContestEntryId(contestEntryId);

            List<Review> reviews = new ArrayList<>();
            if (review.getId() > 0) {
                reviews.add(review);
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
                        .setImagesName(imagesName)
                        .setReviews(reviews));
            } else {
                ContestEntry contestEntry = contestEntriesMap.get(id);
                boolean checkForImageNameDuplicates = contestEntry.getImagesName().stream()
                        .noneMatch(addImageName -> addImageName.equals(imageName));
                if (checkForImageNameDuplicates) {
                    imagesName.addAll(contestEntry.getImagesName());
                    contestEntriesMap.get(id)
                            .setImagesName(imagesName);
                }

                boolean checkForReviewDuplicates = contestEntry.getReviews().stream()
                        .noneMatch(insertedReview -> insertedReview.getId() == review.getId());

                if (checkForReviewDuplicates) {
                    reviews.addAll(contestEntry.getReviews());
                    contestEntriesMap.get(id)
                            .setReviews(reviews);
                }
            }

        }
        return new ArrayList<>(contestEntriesMap.values());
    }
}

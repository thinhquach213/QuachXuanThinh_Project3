package k23cnt3.qxtWebbansach.service;

import k23cnt3.qxtWebbansach.model.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByBook(Long bookId);
    Review createReview(Long userId, Long bookId, int rating, String comment);
    Review updateReview(Long id, int rating, String comment);
    void deleteReview(Long id);
}

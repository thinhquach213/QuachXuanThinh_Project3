package k23cnt3.qxtWebbansach.service.impl;

import k23cnt3.qxtWebbansach.model.Review;
import k23cnt3.qxtWebbansach.model.Book;
import k23cnt3.qxtWebbansach.model.User;
import k23cnt3.qxtWebbansach.repository.ReviewRepository;
import k23cnt3.qxtWebbansach.repository.BookRepository;
import k23cnt3.qxtWebbansach.repository.UserRepository;
import k23cnt3.qxtWebbansach.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             BookRepository bookRepository,
                             UserRepository userRepository){
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Review> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Override
    public Review createReview(Long userId, Long bookId, int rating, String comment) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Review review = new Review();
        review.setBook(book);
        review.setUser(user);
        review.setRating(rating);
        review.setComment(comment);
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, int rating, String comment) {
        return reviewRepository.findById(id).map(review -> {
            review.setRating(rating);
            review.setComment(comment);
            return reviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

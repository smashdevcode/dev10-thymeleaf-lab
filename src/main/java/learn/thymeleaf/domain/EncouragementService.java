package learn.thymeleaf.domain;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class EncouragementService {
    static final List<String> encouragements = List.of(
            "You can do it!",
            "Keep at it!",
            "I believe in you!",
            "You're the best!",
            "You smell nice.",
            "Wow. Cute outfit.",
            "Don't stop now!",
            "You. Are. Enough.",
            "You've done so much work! Keep going.",
            "You're nice.",
            "You're fast.",
            "You're smart.",
            "You're kind.",
            "Thank you so much.",
            "You look like you've lost weight.",
            "Did you get your hair cut? Cute!",
            "Great job today!",
            "You're killing it over there.",
            "Wow, that's impressive.",
            "I really, really love your work."
    );

    static final Random random = new Random();

    public String getRandomEncouragement() {
        return encouragements.get(random.nextInt(encouragements.size()));
    }
}

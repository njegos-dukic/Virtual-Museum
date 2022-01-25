package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.exceptions.UnauthorizedException;
import org.unibl.etf.virtualmuseumapi.model.entities.LogEntity;
import org.unibl.etf.virtualmuseumapi.model.entities.UserEntity;
import org.unibl.etf.virtualmuseumapi.repositories.UserRepository;
import org.unibl.etf.virtualmuseumapi.utils.RunnableEmail;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final LogService logService;

    private final static int PASSWORD_LENGTH = 16;

    public boolean credentialsValid(String username, String password) {
        return userRepository.getUserByUsernameAndPasswordAndIsApprovedAndIsBlocked(username, password, true, false) == null;
    }

    public UserEntity loginUser(String username, String password) {
        if (!credentialsValid(username, password)) {
            UserEntity userEntity = userRepository.getUserByUsernameAndPasswordAndIsApprovedAndIsBlocked(username, password, true, false);
            userEntity.setIsLoggedIn(true);
            if (userEntity.getIsPasswordReset()) {
                String newPassword = this.generateRandomAlphaNumericString();
                userEntity.setPassword(newPassword);
                userEntity.setIsPasswordReset(false);
                scheduleNotification(Instant.now().plusSeconds(10), userEntity.getEmail(), "", "Your password was reset.\n\nNew password is: " + newPassword);
            }

            userRepository.saveAndFlush(userEntity);

            try {
                String message = username + " logged in with password: " + password;
                logService.insert(new LogEntity(0, "LOGIN", message, Instant.now()));
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error while logging action.");
            }

            return userEntity;
        }

        throw new UnauthorizedException("Invalid credentials.");
    }

    public boolean logoutUser(String username, String password) {
        if (!credentialsValid(username, password)) {
            UserEntity userEntity = userRepository.getUserByUsernameAndPassword(username, password);
            userEntity.setIsLoggedIn(false);
            userRepository.saveAndFlush(userEntity);
            return true;
        }

        return false;
    }

    private String generateRandomAlphaNumericString() {
        int leftLimit = '0';
        int rightLimit = 'z';
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) // Exclude unicode chars;
                .limit(PASSWORD_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public void scheduleNotification(Instant instant, String recipient, String tourInfo, String specifics) {
        taskScheduler.schedule(
                new RunnableEmail(mailService, recipient, tourInfo, specifics),
                Date.from(instant));
    }
}

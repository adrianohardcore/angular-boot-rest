package br.com.adrianohardcore.service;

import br.com.adrianohardcore.DTOUtils;
import br.com.adrianohardcore.domain.User;
import br.com.adrianohardcore.exception.PasswordMismatchedException;
import br.com.adrianohardcore.exception.ResourceNotFoundException;
import br.com.adrianohardcore.exception.UsernameAlreadyUsedException;
import br.com.adrianohardcore.model.*;
import br.com.adrianohardcore.repository.UserRepository;
import br.com.adrianohardcore.repository.UserSpecifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@Service
@Transactional
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRepository userRepository;

    public Page<UserDetails> findAll(String q, String role, Pageable page) {

        log.debug("find all users by keyword@" + q + ", role:" + role);

        Page<User> users = userRepository.findAll(UserSpecifications.filterUsersByKeyword(q, role), page);

        return DTOUtils.mapPage(users, UserDetails.class);
    }

    public UserDetails registerUser(SignupForm form) {
        Assert.notNull(form, " @@ SignupForm is null");

        log.debug("saving user@" + form);

        if (userRepository.findByUsername(form.getUsername()) != null) {
            throw new UsernameAlreadyUsedException(form.getUsername());
        }

        User user = DTOUtils.map(form, User.class);
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        User saved = userRepository.save(user);

        //TODO sending an activation email.
        return DTOUtils.map(saved, UserDetails.class);
    }

    public UserDetails saveUser(UserForm form) {
        Assert.notNull(form, " @@ UserForm is null");

        log.debug("saving user@" + form);

        if (userRepository.findByUsername(form.getUsername()) != null) {
            throw new UsernameAlreadyUsedException(form.getUsername());
        }

        User user = DTOUtils.map(form, User.class);
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        User saved = userRepository.save(user);

        return DTOUtils.map(saved, UserDetails.class);
    }

    public void updateUser(Long id, UserForm form) {
        Assert.notNull(id, "user id can not be null");

        log.debug("update user by id @" + id);

        User user = userRepository.findOne(id);

        if (user == null) {
            throw new ResourceNotFoundException(id);
        }

        DTOUtils.mapTo(form, user);

        User userSaved = userRepository.save(user);

        if (log.isDebugEnabled()) {
            log.debug("updated user @" + userSaved);
        }
    }

    public void updatePassword(Long id, PasswordForm form) {
        Assert.notNull(id, "user id can not be null");

        log.debug("update user password by id @" + id);

        User user = userRepository.findOne(id);

        if (!passwordEncoder.matches(form.getOldPassword(), user.getPassword())) {
            throw new PasswordMismatchedException();
        }

        user.setPassword(passwordEncoder.encode(form.getNewPassword()));

        User saved = userRepository.save(user);

        if (log.isDebugEnabled()) {
            log.debug("updated user @" + saved);
        }
    }

    public void updateProfile(Long id, ProfileForm form) {
        Assert.notNull(id, "user id can not be null");

        log.debug("update profile for user @" + id + ", profile form@" + form);

        User user = userRepository.findOne(id);

        DTOUtils.mapTo(form, user);

        User saved = userRepository.save(user);

        if (log.isDebugEnabled()) {
            log.debug("updated user @" + saved);
        }
    }

    public UserDetails findUserById(Long id) {
        Assert.notNull(id, "user id can not be null");

        log.debug("find user by id @" + id);

        User user = userRepository.findOne(id);

        if (user == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(user, UserDetails.class);
    }

    public void deleteUser(Long id) {
        Assert.notNull(id, "user id can not be null");

        log.debug("delete user by id @" + id);

        userRepository.delete(id);
    }

}

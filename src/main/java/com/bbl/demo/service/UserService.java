package com.bbl.demo.service;

import com.bbl.demo.dto.UserRequest;
import com.bbl.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(3);

    public UserService() {
        //initialize 3 users in hashMap
        users.put(1L, new User(
                1L,
                "Leanne Graham",
                "Bret",
                "Sincere@april.biz",
                "1-770-736-8031 x56442",
                "hildegard.org"
        ));

        users.put(2L, new User(
                2L,
                "Ervin Howell",
                "Antonette",
                "Shanna@melissa.tv",
                "010-692-6593 x09125",
                "anastasia.net"
        ));

        users.put(3L, new User(
                3L,
                "Clementine Bauch",
                "Samantha",
                "Nathan@yesenia.net",
                "1-463-123-4447",
                "ramiro.info"
        ));
    }

    /**
     * Finds all users in memory
     *
     * @return list of all users
     */
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    /**
     * Finds a specific user by id
     *
     * @param id id
     * @return user domain
     */
    public User findById(Long id) {
        return users.get(id);
    }

    public User createUser(UserRequest request) {
        Long id = idGenerator.incrementAndGet();
        User user = new User(id, request.getName(), request.getUsername(), request.getEmail(), request.getPhone(), request.getWebsite());
        users.put(id, user);
        return user;
    }

    public User updateUser(Long id, UserRequest request) {
        User existing = users.get(id);
        if (existing == null) {
            return null;
        }
        existing.setName(request.getName());
        existing.setUsername(request.getUsername());
        existing.setEmail(request.getEmail());
        existing.setPhone(request.getPhone());
        existing.setWebsite(request.getWebsite());
        return existing;
    }

    /**
     * Deletes user by id
     * true -> existing user is removed
     * false -> no existing user is found
     *
     * @param id id
     */
    public boolean deleteUserById(Long id) {
        return users.remove(id) != null;
    }
}

package com.Bhautik.socialMediaApp.service.serviceImpl;
import com.Bhautik.socialMediaApp.Repository.userRepository;
import com.Bhautik.socialMediaApp.entity.user;
import com.Bhautik.socialMediaApp.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements userService {

    private static final PasswordEncoder PASSWORDENCODER = new BCryptPasswordEncoder();

    @Autowired
    private userRepository repository;

    @Override
    public void saveNewUser(user user) {
        user.setPassword(PASSWORDENCODER.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));
        repository.save(user);
    }

    @Override
    public void createAdmin(user user) {
        user.setPassword(PASSWORDENCODER.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User","ADMIN"));
        repository.save(user);
    }

    public void create(user user) {
        repository.save(user);
    }

    @Override
    public List<user> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<user> findById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(ObjectId id) {
        repository.deleteById(id);
    }

    @Override
    public user findByuserName(String userName) {
      return repository.findByUserName(userName);
    }


}

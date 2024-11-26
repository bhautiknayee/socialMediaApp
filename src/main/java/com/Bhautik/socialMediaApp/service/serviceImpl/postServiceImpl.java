package com.Bhautik.socialMediaApp.service.serviceImpl;

import com.Bhautik.socialMediaApp.Repository.postRepository;
import com.Bhautik.socialMediaApp.entity.post;
import com.Bhautik.socialMediaApp.entity.user;
import com.Bhautik.socialMediaApp.service.postService;
import com.Bhautik.socialMediaApp.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class postServiceImpl implements postService {

    @Autowired
    private postRepository repository;

    @Autowired
    private userService userService;

    @Transactional
    @Override
    public void create(String userName, post post) {
        user user = userService.findByuserName(userName);
        if(user != null){
            post.setLocalDate(LocalDate.now());
            post savedPost = repository.save(post);
            user.getPost().add(savedPost);
            userService.create(user);
        }
    }

    public void create(post post) {
        repository.save(post);
    }

    @Override
    public List<post> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<post> findById(ObjectId id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public boolean deleteById(ObjectId postId, String userName) {
        try {
            boolean removed = false;
            user user = userService.findByuserName(userName);
            if(user != null) {
                removed = user.getPost().removeIf(x -> x.getId().equals(postId));
                if (removed) {
                    userService.create(user);
                    repository.deleteById(postId);
                }
            }
            return removed;
        } catch (Exception e){
            System.out.println(e);
            throw  new RuntimeException("An Error occured while deleting the entry."+e);
        }
    }

    public List<post> findByUserName(String userName){
       return null;
    }
}

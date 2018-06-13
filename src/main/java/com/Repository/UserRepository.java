package com.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query("select u from User u where u.username=?1 AND password=?2")
	List<User> findByUsernameAndPassword(String username, String password);

	List<User> findByUsernameAndQuestionAndAnswer(String username,String question,String answer);

}

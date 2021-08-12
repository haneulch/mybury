package com.mybury.bucketlist.core.service;

import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.Sequence;
import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.domain.UserMapping;
import com.mybury.bucketlist.core.exception.UserAlreadyExistsException;
import com.mybury.bucketlist.core.repository.CategoryRepository;
import com.mybury.bucketlist.core.repository.UserRepository;
import com.mybury.bucketlist.core.repository.SequenceRepository;
import com.mybury.bucketlist.core.util.DateUtil;
import com.mybury.bucketlist.core.vo.CreateProfileRequestVO;
import com.mybury.bucketlist.core.vo.HostSignInRequestVO;
import com.mybury.bucketlist.core.vo.HostSignUpRequestVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagerImpl implements UserManager {

	private String authServerAddress = "https://www.my-bury.com";

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SequenceRepository sequenceRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FileUploadService fileUploadService;

	@Override
	public User getUserById(String userId) {
		return userRepository.getOne(userId);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	@Override
	@Transactional
	public User signup(HostSignUpRequestVO requestVO) {
		if (userRepository.getUserByEmail(requestVO.getEmail()) != null)
			throw new UserAlreadyExistsException("user already exists");

		User user = new User();
		user.setAccountType(requestVO.getAccountType());
		user.setEmail(requestVO.getEmail());
		
		Sequence userSeq = sequenceRepository.getOne("userSeq");
		
		if(userSeq == null) {
			userSeq = new Sequence("userSeq", 1);
			sequenceRepository.save(userSeq);
		}
		
		int nextVal = userSeq.getNextVal() + 1;
		userSeq.setNextVal(nextVal);
		sequenceRepository.save(userSeq);
		
		user.setUserSeq(nextVal);

		Category category = new Category();
		category.setName("없음");
		category.setPriority(0);
		category.setUser(user);
		categoryRepository.save(category);

		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User signin(HostSignInRequestVO requestVO) {
		User user = userRepository.getOne(requestVO.getUserId());
		user.setLastLoginDt(DateUtil.getDate());
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void createProfile(CreateProfileRequestVO requestVO) {
		User user = userRepository.getOne(requestVO.getUserId());
		user.setName(requestVO.getName());

		if (requestVO.isDefaultImg())
			user.setImgUrl(null);

		if (requestVO.getMultipartFile() != null) {
			String imgUrl = fileUploadService.upload(requestVO.getMultipartFile());
			user.setImgUrl(imgUrl);
		}

		userRepository.save(user);
	}

	@Override
	public void remove(String userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public Page<UserMapping> getUser(String search, Pageable page) {
		return userRepository.findByIdContainsOrEmailContainsOrNameContainsOrderByName(search, search, search, page);
	}

	@Override
	public UserMapping getUserMappingById(String userId) {
		return userRepository.findOneById(userId);
	}
	
	
}

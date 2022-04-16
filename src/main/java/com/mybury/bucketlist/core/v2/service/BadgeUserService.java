package com.mybury.bucketlist.core.v2.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import com.mybury.bucketlist.core.domain.Badge;
import com.mybury.bucketlist.core.domain.BadgeUser;
import com.mybury.bucketlist.core.v2.repository.BadgeRepository;
import com.mybury.bucketlist.core.v2.repository.BadgeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BadgeUserService {
	@PersistenceContext
	private EntityManager em;

	private final BadgeUserRepository repository;
	private final BadgeRepository badgeRepository;

	public void save(Badge badge) {

	}

	@Transactional
	public BadgeUser findByUseYnAndUserId(String userId) {

		BadgeUser badgeUser = repository.findByUserIdAndUseYn(userId, 'Y');

		if(badgeUser == null) {
			Badge badge = em.getReference(Badge.class, "default");

			badgeUser = BadgeUser.builder()
				.userId(userId)
				.badge(badge)
				.susYn('Y')
				.useYn('Y')
				.build();

			em.persist(badgeUser);
		}

		return badgeUser;
	}
}

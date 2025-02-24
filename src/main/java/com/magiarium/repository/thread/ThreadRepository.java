package com.magiarium.repository.thread;

import com.magiarium.domain.entity.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    Thread findByItemId(Long threadId);
}

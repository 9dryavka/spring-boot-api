package com.magiarium.repository.thread_comment;

import com.magiarium.domain.entity.ThreadComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadCommentRepository extends JpaRepository<ThreadComment, Long> {

    List<ThreadComment> findByThreadId(Long threadId);

}

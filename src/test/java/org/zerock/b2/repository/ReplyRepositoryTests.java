package org.zerock.b2.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b2.entity.Board;
import org.zerock.b2.entity.Reply;
import org.zerock.b2.repository.ReplyRepository;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void testInsert() {

        Board board = Board.builder().bno(105).build();

        for (int i = 0; i < 100; i++) {
            Reply reply = Reply.builder()
                    .board(board)
                    .replyText(i + "100번의 댓글")
                    .replyer("replyer00")
                    .build();

            replyRepository.save(reply);
        }//end for
    }

    @Test
    public void getList(){

        Integer bno = 100;
        Pageable pageable =
                PageRequest.of(0,10, Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listOfBoard(bno,pageable);
    }
}
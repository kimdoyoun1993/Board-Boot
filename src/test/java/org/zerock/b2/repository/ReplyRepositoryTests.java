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
    public void testInsert2() {

        for (int i = 1; i <= 20; i++) {

            Board board = Board.builder().bno(i).build();

            int count = i%5;

            for(int j = 0; j < count ; j++){

                Reply reply = Reply.builder()
                        .board(board)
                        .replyText(i+"--"+"댓글")
                        .replyer("user00")
                        .build();
                replyRepository.save(reply);
            }//end for j
        }//end for i
    }

    @Test
    public void getList(){

        Integer bno = 100;
        Pageable pageable =
                PageRequest.of(0,10, Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listOfBoard(bno,pageable);
    }
}
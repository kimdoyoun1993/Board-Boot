package org.zerock.b2.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b2.dto.BoardListReplyCountDTO;
import org.zerock.b2.entity.Board;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository repository;

    @Test
    public void testInsert() {

        log.info("-------------------------");
        log.info("-------------------------");
        log.info(repository);
        log.info("-------------------------");

        //100개의 데이터를 밀어 넣는다
        IntStream.rangeClosed(1,100).forEach(i ->{
            Board board = Board.builder()
                    .title("Title..."+i)
                    .content("Content...."+i)
                    .writer("user"+(i%10))
                    .build();

            repository.save(board);
        });
    }

    @Test
    public void testRead(){
        Integer bno = 100;

        Optional<Board> result =  repository.findById(bno);

        Board board =result.orElseThrow();
        board.changeTitle("100title...updated");
        board.changeContent("100Content...updated");

        repository.save(board);

        log.info(board);
    }

    @Test
    public void testDelete(){

        Integer bno = 100;

        repository.deleteById(bno);
    }

    @Test
    public void testPage1(){
        Pageable pageable = PageRequest.of(0,10 , Sort.by("bno").descending());

        repository.findAll(pageable);
    }

    @Test
    public void testPage2(){
        Pageable pageable = PageRequest.of(0,10 , Sort.by("bno").descending());

        Page<Board> result = repository.findAll(pageable);

        log.info("TOTAL"+result.getTotalElements());
        log.info("TOTALPAGES:"+result.getTotalPages());
        log.info("CURRENT"+result.getNumber());
        log.info("SIZE"+result.getSize());

        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void testWithReplyCount(){

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());

        Page<BoardListReplyCountDTO> result =

                repository.searchWithreplyCount(null,null,pageable);
    }
}

package org.zerock.b2.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b2.dto.BoardListReplyCountDTO;
import org.zerock.b2.entity.Board;
import org.zerock.b2.entity.BoardImage;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository repository;

    @Transactional
    @Test
    public void testWithImage(){

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());

        repository.searchWithImage(null,null,pageable);
    }

    @Transactional
    @Test
    public void testWithQuery(){

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());

        Page<Board> result = repository.getList1(pageable);

        result.getContent().forEach(board -> {
            log.info(board);
            log.info(board.getBoardImages());
            log.info("==================================");
        });
    }

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

    @Test
    public void testInsertWithImage() {

        for (int i = 0; i < 20; i++) {

            Board board = Board.builder()
                    .title("fileTest.." +i)
                    .content("fileTest")
                    .writer("user" + (i % 10))
                    .build();
            //게시물 하나당 이미지 2개씩 추가
            for (int j = 0; j < 2; j++) {
                BoardImage boardImage = BoardImage.builder()
                        .fileLink(i+"aaa.jpg")
                        .build();
                board.addImage(boardImage); //관리의 주체가 board기 때문에 board에 추가해주어야한다
            }//image

            repository.save(board);
        }

    }
    @Transactional
    @Test
    public void testPageImage(){

        //1페이지를 가져오고 Sort 로 역순으로 가져온다
        Pageable pageable = PageRequest.of(0,10 , Sort.by("bno").descending());

        //findAll 로 Board 를 가져온다 , 검색조건이 없는 상태로 목록을 가져온다
        Page<Board> result = repository.findAll(pageable);

        //게시물을 로그로 찍어보자
        result.getContent().forEach(board -> {
            log.info(board);
        });
    }

    @Test
    public void testEager() {

        Integer bno = 124;

        Optional<Board> result = repository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);

    }
}

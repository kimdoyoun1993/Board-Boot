package org.zerock.b2;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b2.entity.Board;
import org.zerock.b2.repository.BoardRepository;

@SpringBootTest
@Log4j2
class B2ApplicationTests {


    @Autowired
    private BoardRepository repository;


    @Test
    void contextLoads() {
    }

    @Test
    public void testSearch1(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        repository.search1(pageable);
    }

    @Test
    public void testSearchAll(){
        String[] types = new String[]{"t","c"};
        String keyword = "5";

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        Page<Board> result = repository.searchAll(types,keyword,pageable);

        log.info(result);
    }
}

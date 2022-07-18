package org.zerock.b2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b2.entity.Board;
import org.zerock.b2.repository.search.BoardSearch;

//JpaRepository CRUD 페이징 등 모든 기능을 다 가지고있다  < > 안에는 하나는 엔티티 타입 하나는 pk
public interface BoardRepository extends JpaRepository<Board , Integer>, BoardSearch {


    @Query("select b from Board  b" )
    Page<Board> getList1(Pageable pageable);

}

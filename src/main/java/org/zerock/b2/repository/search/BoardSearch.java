package org.zerock.b2.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b2.dto.BoardListReplyCountDTO;
import org.zerock.b2.dto.BoardListWithImageDTO;
import org.zerock.b2.entity.Board;

public interface BoardSearch {

    void search1(Pageable pageable);

    //모든 검색조건
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithreplyCount(String[] type , String keyword , Pageable pageable);

    Page<BoardListWithImageDTO> searchWithImage(String[] types, String keyword, Pageable pageable);
}

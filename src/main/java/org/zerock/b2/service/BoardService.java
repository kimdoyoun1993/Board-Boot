package org.zerock.b2.service;

import org.zerock.b2.dto.BoardDTO;
import org.zerock.b2.dto.BoardListReplyCountDTO;
import org.zerock.b2.dto.PageRequestDTO;
import org.zerock.b2.dto.PageResponseDTO;

public interface BoardService {

    Integer register(BoardDTO boardDTO);

    BoardDTO readOne(Integer bno);

    void modify(BoardDTO boardDTO);

    void remove(Integer bno);

    PageResponseDTO<BoardListReplyCountDTO> list(PageRequestDTO pageRequestDTO);

}

package org.zerock.b2.service;

import org.zerock.b2.dto.*;

public interface BoardService {

    Integer register(BoardDTO boardDTO);

    BoardDTO readOne(Integer bno);

    void modify(BoardDTO boardDTO);

    void remove(Integer bno);

    PageResponseDTO<BoardListWithImageDTO> list(PageRequestDTO pageRequestDTO);

}

package org.zerock.b2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b2.dto.BoardDTO;
import org.zerock.b2.dto.BoardListReplyCountDTO;
import org.zerock.b2.dto.PageRequestDTO;
import org.zerock.b2.dto.PageResponseDTO;
import org.zerock.b2.entity.Board;
import org.zerock.b2.repository.BoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Integer register(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO,Board.class);
        log.info("register..."+board);

        //save하면 똑같은 타입이 반환된다
        Board result = boardRepository.save(board);

        return result.getBno();
    }

    @Override
    public BoardDTO readOne(Integer bno) {

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);

        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        Board board = result.orElseThrow();

        //제목하고 내용을 수정가능하게
        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        boardRepository.save(board);
    }
    @Override
    public void remove(Integer bno) {

        boardRepository.deleteById(bno);
    }

    @Override
    public PageResponseDTO<BoardListReplyCountDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<BoardListReplyCountDTO> result = boardRepository.searchWithreplyCount(types, keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board,BoardDTO.class)).collect(Collectors.toList());


        return PageResponseDTO.<BoardListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.toList())
                .total((int)result.getTotalElements())
                .build();

    }
}


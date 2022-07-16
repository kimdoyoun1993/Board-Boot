package org.zerock.b2.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b2.dto.BoardListReplyCountDTO;
import org.zerock.b2.entity.Board;
import org.zerock.b2.entity.QBoard;
import org.zerock.b2.entity.QReply;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{


    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public void search1(Pageable pageable ) {


        log.info("search1.................");

        //이건 규칙이다
        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        getQuerydsl().applyPagination(pageable,query);

        query.fetchCount();

        //fetch 는 가져온다는 뜻
        //DB에서 SQL을 실행해서 가져옴
        query.fetch();
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        //types가 not null 일때
        if(types != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type:types) {
                if(type.equals("t")){
                    booleanBuilder.or(board.title.contains(keyword));
                }else if (type.equals("c")){
                    booleanBuilder.or(board.content.contains(keyword));
                }else if (type.equals("w")){
                    booleanBuilder.or(board.writer.contains(keyword));
                }
            }//end for
            query.where(booleanBuilder);
        }//end if
        query.where(board.bno.gt(0)); //bno 가 0보다 크다는 조건을 추가


        //페이징 처리
        getQuerydsl().applyPagination(pageable,query);

        List<Board> list = query.fetch();

        //jpa에서 count 나 숫자와 관련된 애들은 전부 long 로 처리됨 int는 거의 없다
        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithreplyCount(String[] type, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply =QReply.reply;

        JPQLQuery<Board> query = from(board);
        //on~ 은 leftJoin 의 조건문 //reply.board 는 eq(board)여야한다 는 조건
        query.leftJoin(reply).on(reply.board.eq(board));

        query.groupBy(board); //board 로 해주면 board_bno 로 진행됨

        //Projections
        //Projection은 Entity의 속성이 많을 때 일부 데이터만 조회하는 방법입니다
        JPQLQuery<BoardListReplyCountDTO> dtojplQuery =
                query.select(Projections.bean(BoardListReplyCountDTO.class,
                        board.bno ,
                        board.title ,
                        board.writer ,
                        board.regDate ,
                        reply.count().as("replyCount")) );
        //페이징
        this.getQuerydsl().applyPagination(pageable,dtojplQuery);

        //list 데이터 뽑기
        List<BoardListReplyCountDTO> list = dtojplQuery.fetch();

        //totalCount 뽑기
        long totalCount = dtojplQuery.fetchCount();


        return new PageImpl<>(list,pageable,totalCount);
    }
}
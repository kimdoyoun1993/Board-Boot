package org.zerock.b2.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_board")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude = "boardImages") //N+1 해결하기 위해
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto_Increment 기능
    private Integer bno;


    //컬럼의 사이즈 지정해주고 not null 지정
    @Column(length = 200, nullable = false)
    private String title;

    private String content;

    private String writer;



    public void changeTitle(String title){
        this.title = title;
    }
    public void changeContent(String content){
        this.content = content;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    @Builder.Default //자료구조를 반환할때 null값을 체크안해주게 만들어주기
    private Set<BoardImage> boardImages = new HashSet<>();

    //이미지를 추가
    public void addImage(BoardImage boardImage){

        //처음에 들어갈때 size가 0번으로 들어간다
        boardImage.fixOrd(boardImages.size());
        boardImages.add(boardImage);

    }

    //새로운 이미지를 넣는다는건 기존 이미지를 지우고 업로드 하는 개념이기에 clear<삭제>를 해준다
    public void clearImages(){

        boardImages.clear();
    }

}
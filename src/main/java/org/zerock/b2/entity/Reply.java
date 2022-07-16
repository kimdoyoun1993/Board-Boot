package org.zerock.b2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")})
@Getter
@ToString(exclude = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)//연관관계 //lazy<지연로딩>
    private Board board;

    //수정할때 사용
    public void changeText(String text){
        this.replyText = text;
    }
}
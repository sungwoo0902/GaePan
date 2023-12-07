package co.kr.gaepan.entity.admin;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "gp_board_cate")
public class GP_AdminBoardCateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    private int cate;
    private String cateName;
}

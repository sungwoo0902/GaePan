package co.kr.gaepan.entity.admin;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "gp_board_type")
public class GP_AdminBoardTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tno;
    private int cate;
    private int type;
    private String typeName;
}

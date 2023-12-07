package co.kr.gaepan.dto.pet;

import co.kr.gaepan.entity.pet.PetRegisterEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetTypeDTO {


    private int cate;
    private int type;
    private String tName;


}

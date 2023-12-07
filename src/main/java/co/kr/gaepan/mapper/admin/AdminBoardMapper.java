package co.kr.gaepan.mapper.admin;

import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import co.kr.gaepan.entity.admin.GP_AdminBoardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminBoardMapper {

    public GP_AdminBoardDTO toDTO(GP_AdminBoardEntity entity);

    public GP_AdminBoardEntity toEntity(GP_AdminBoardDTO dto);
}

package co.kr.gaepan.mapper.admin;

import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import co.kr.gaepan.dto.admin.GP_AdminBoardDTO.GP_AdminBoardDTOBuilder;
import co.kr.gaepan.entity.admin.GP_AdminBoardEntity;
import co.kr.gaepan.entity.admin.GP_AdminBoardEntity.GP_AdminBoardEntityBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-01T16:36:20+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class AdminBoardMapperImpl implements AdminBoardMapper {

    @Override
    public GP_AdminBoardDTO toDTO(GP_AdminBoardEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GP_AdminBoardDTOBuilder gP_AdminBoardDTO = GP_AdminBoardDTO.builder();

        gP_AdminBoardDTO.bno( entity.getBno() );
        gP_AdminBoardDTO.uid( entity.getUid() );
        gP_AdminBoardDTO.title( entity.getTitle() );
        gP_AdminBoardDTO.content( entity.getContent() );
        gP_AdminBoardDTO.group( entity.getGroup() );
        gP_AdminBoardDTO.cate( entity.getCate() );
        gP_AdminBoardDTO.type( entity.getType() );
        gP_AdminBoardDTO.regIP( entity.getRegIP() );
        gP_AdminBoardDTO.regDate( entity.getRegDate() );
        gP_AdminBoardDTO.hit( entity.getHit() );
        gP_AdminBoardDTO.parent( entity.getParent() );
        gP_AdminBoardDTO.comment( entity.getComment() );

        return gP_AdminBoardDTO.build();
    }

    @Override
    public GP_AdminBoardEntity toEntity(GP_AdminBoardDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GP_AdminBoardEntityBuilder gP_AdminBoardEntity = GP_AdminBoardEntity.builder();

        gP_AdminBoardEntity.bno( dto.getBno() );
        gP_AdminBoardEntity.uid( dto.getUid() );
        gP_AdminBoardEntity.title( dto.getTitle() );
        gP_AdminBoardEntity.content( dto.getContent() );
        gP_AdminBoardEntity.group( dto.getGroup() );
        gP_AdminBoardEntity.cate( dto.getCate() );
        gP_AdminBoardEntity.type( dto.getType() );
        gP_AdminBoardEntity.regIP( dto.getRegIP() );
        gP_AdminBoardEntity.regDate( dto.getRegDate() );
        gP_AdminBoardEntity.hit( dto.getHit() );
        gP_AdminBoardEntity.parent( dto.getParent() );
        gP_AdminBoardEntity.comment( dto.getComment() );

        return gP_AdminBoardEntity.build();
    }
}

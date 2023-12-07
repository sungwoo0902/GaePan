package co.kr.gaepan.repository.cs;

import co.kr.gaepan.entity.admin.GP_AdminBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsBoardRepository extends JpaRepository<GP_AdminBoardEntity, Integer> {

}

package co.kr.gaepan.dto.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data // Getter, Setter, ToString
@Builder
@AllArgsConstructor // 속성 초기화 생성자
@NoArgsConstructor // 기본 생성자
public class PageRequestDTO {

    @Builder.Default // 어노테이션 있을 때 없을 때 차이 확인하기 => 똑같다
    private int pg = 1;

    @Builder.Default
    private int size = 10; // (pg-1) * size로 인덱스 표현한다 => 첫페이지는 시작 인덱스 0, 두번째페이지는 10, 세번째페이지는 20

    private String group;
    private int cate;
    // 각 페이지 시작 인덱스번호
    private int offset;
    // 페이지마다 표시할 최대 개수
    @Builder.Default
    private int limit = 10;

    // pg 및 size의 현재 값에 기반하여 offset를 계산하는 메서드 추가
    public void updateOffset() {
        this.offset = (this.pg - 1) * this.size;
    }

    /*public Pageable getPageable(String sort){ // sort에 "no" 값 넣음

        // pageable 객체는 JPA 쓸 때 이용
        return PageRequest.of(this.pg - 1, this.size, Sort.by(sort).descending());
    }
    */
}
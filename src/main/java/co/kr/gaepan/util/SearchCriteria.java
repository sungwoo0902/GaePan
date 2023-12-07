package co.kr.gaepan.util;

import lombok.*;
import net.koreate.common.utils.Criteria;

@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor        // 기본 생성자 추가
@ToString(callSuper=true) // callSuper : 부모 생성자의 toString도 해줌
public class SearchCriteria extends Criteria {

    /*private final String searchType;
    private final String keyword;
    private final String group;*/

    private String searchType;
    private String keyword;
    // board 땜에 group 추가
    private String group;
    private int cate;


    // 기존 Criteria가 가지고 있는 속성
    // + SearchCriteria에서 추가한 속성
    public SearchCriteria(int page, int perPageNum,
                          String searchType, String keyword,
                          String group, int cate) {
        super(page, perPageNum);
        this.searchType = searchType;
        this.keyword = keyword;
        this.group = group;
        this.cate = cate;
    }

    public SearchCriteria(int page, int perPageNum,
                          String searchType, String keyword) {
        super(page, perPageNum);
        this.searchType = searchType;
        this.keyword = keyword;
    }
}

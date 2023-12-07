package co.kr.gaepan.util;

import lombok.extern.log4j.Log4j2;
import net.koreate.common.utils.PageMaker;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Log4j2
public class SearchPageMaker extends PageMaker {
    @Override
    public String makeQuery(int page) {
        SearchCriteria sCriteria = (SearchCriteria)super.getCri();

        UriComponents uri
                // 처음 들어온 queryParam에는 ?를 붙여주고,
                // 그 다음 queryParam에는 이어지니깐 &를 자동으로 붙여주는 친구
                = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", sCriteria.getPerPageNum())
                .queryParam("searchType",sCriteria.getSearchType())
                .queryParam("keyword", sCriteria.getKeyword())
                .build();
        String query = uri.toUriString();

        if (query.contains("?")) {
            query = query.replaceFirst("\\?", "");
        }
        log.info("SearchPageMaker query : "+query);
        return query;
    }
}

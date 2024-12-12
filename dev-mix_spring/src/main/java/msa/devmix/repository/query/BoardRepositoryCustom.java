package msa.devmix.repository.query;

import com.querydsl.core.BooleanBuilder;

import java.util.List;
import java.util.Map;

public interface BoardRepositoryCustom {
    List<BoardQueryDto> findBoards(int pageNumber, int pageSize);
    Long countBoards();
    Map<Long, List<BoardPositionQueryDto>> findBoardPositionQueryDtos(List<Long> boardIds);
    Map<Long, List<BoardTechStackQueryDto>> findBoardTechStackQueryDtos(List<Long> boardIds);
    List<BoardQueryDto> findBoardQueryDtoBySearch(int pageNumber,
                                                  int pageSize,
                                                  BooleanBuilder builder);
}

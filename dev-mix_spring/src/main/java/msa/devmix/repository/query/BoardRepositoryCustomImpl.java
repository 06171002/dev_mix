package msa.devmix.repository.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import msa.devmix.domain.constant.Location;
import msa.devmix.domain.constant.RecruitmentStatus;
import msa.devmix.domain.user.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static msa.devmix.domain.board.QBoard.board;
import static msa.devmix.domain.board.QBoardPosition.boardPosition;
import static msa.devmix.domain.board.QBoardTechStack.boardTechStack;
import static msa.devmix.domain.board.QScrap.scrap;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    // Board 조회
    @Override
    public List<BoardQueryDto> findBoards(int pageNumber, int pageSize) {

        return em.createQuery("SELECT new msa.devmix.repository.query.BoardQueryDto(" +
                "b.id, b.title, b.createdBy, b.viewCount, b.commentCount, b.recruitEndDate, b.location)" +
                " FROM Board b", BoardQueryDto.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

    }

    @Override
    public Long countBoards() {

        return em.createQuery("SELECT count(b) FROM Board b", Long.class)
                .getSingleResult();
    }

    @Override
    public Map<Long, List<BoardPositionQueryDto>> findBoardPositionQueryDtos(List<Long> boardIds) {

        List<BoardPositionQueryDto> boardPositionQueryDtos = em.createQuery(
                 " SELECT new msa.devmix.repository.query.BoardPositionQueryDto(" +
                        " bp.board.id, p.positionName, bp.requiredCount, bp.currentCount)" +
                        " FROM BoardPosition bp " +
                        " JOIN bp.position p" +
                        " WHERE bp.board.id IN :boardIds", BoardPositionQueryDto.class)
                .setParameter("boardIds", boardIds)
                .getResultList();

        return boardPositionQueryDtos.stream().collect(Collectors.groupingBy(BoardPositionQueryDto::getBoardId));

    }

    @Override
    public Map<Long, List<BoardTechStackQueryDto>> findBoardTechStackQueryDtos(List<Long> boardIds) {

        List<BoardTechStackQueryDto> boardTechStackQueryDtos = em.createQuery("SELECT new msa.devmix.repository.query.BoardTechStackQueryDto(bt.board.id, t.imageUrl)" +
                        " FROM BoardTechStack bt" +
                        " JOIN bt.techStack t WHERE bt.board.id IN :boardIds", BoardTechStackQueryDto.class)
                .setParameter("boardIds", boardIds)
                .getResultList();

        return boardTechStackQueryDtos.stream().collect(Collectors.groupingBy(BoardTechStackQueryDto::getBoardId));
    }

    @Override
    public List<BoardQueryDto> findBoardQueryDtoBySearch(int pageNumber,
                                                         int pageSize,
                                                         BooleanBuilder builder) {

        List<BoardQueryDto> boardQueryDtos = queryFactory.selectDistinct(Projections
                        .bean(BoardQueryDto.class,
                                board.id.as("boardId"),
                                board.title,
                                board.createdBy,
                                board.viewCount,
                                board.commentCount,
                                board.recruitEndDate,
                                board.location))
                .from(board)
                .innerJoin(boardPosition).on(board.id.eq(boardPosition.board.id))
                .innerJoin(boardTechStack).on(board.id.eq(boardTechStack.board.id))
                .leftJoin(scrap).on(board.id.eq(scrap.board.id))
                .where(builder)
                .limit(pageSize)
                .offset((long) (pageNumber - 1) * pageSize)
                .groupBy(board.id)
                .orderBy(board.id.desc())
                .fetch();

        getBoardPositionAndBoardTechStack(boardQueryDtos);

        return boardQueryDtos;
    }

    @Override
    public Long countBySearch(BooleanBuilder builder) {

        return queryFactory.select(board.id.countDistinct())
                .from(board)
                .innerJoin(boardPosition).on(board.id.eq(boardPosition.board.id))
                .innerJoin(boardTechStack).on(board.id.eq(boardTechStack.board.id))
                .leftJoin(scrap).on(board.id.eq(scrap.board.id))
                .where(builder)
                .fetchOne();

    }

    private void getBoardPositionAndBoardTechStack(List<BoardQueryDto> boardQueryDtos) {
        List<BoardPositionQueryDto> boardPositionQueryDtos = queryFactory.select(Projections.bean(
                        BoardPositionQueryDto.class,
                        boardPosition.board.id.as("boardId"),
                        boardPosition.position.positionName,
                        boardPosition.requiredCount,
                        boardPosition.currentCount))
                .from(boardPosition)
                .where(boardPosition.board.id.in(boardQueryDtos.stream().map(BoardQueryDto::getBoardId).toList()))
                .fetch();

        if (boardPositionQueryDtos != null && !boardPositionQueryDtos.isEmpty()) {
            Map<Long, List<BoardPositionQueryDto>> boardPositionMap = boardPositionQueryDtos
                    .stream().collect(Collectors.groupingBy(BoardPositionQueryDto::getBoardId));
            boardQueryDtos.forEach(boardQueryDto ->
                    boardQueryDto.setPositions(boardPositionMap.get(boardQueryDto.getBoardId())));
        }

        List<BoardTechStackQueryDto> boardTechStackQueryDtos = queryFactory.select(Projections.bean(
                        BoardTechStackQueryDto.class,
                        boardTechStack.board.id.as("boardId"),
                        boardTechStack.techStack.techStackName,
                        boardTechStack.techStack.imageUrl.as("techStackImageUrl")))
                .from(boardTechStack)
                .where(boardTechStack.board.id.in(boardQueryDtos.stream().map(BoardQueryDto::getBoardId).toList()))
                .fetch();

        if (boardTechStackQueryDtos != null && !boardTechStackQueryDtos.isEmpty()) {
            Map<Long, List<BoardTechStackQueryDto>> boardTechStackMap = boardTechStackQueryDtos
                    .stream().collect(Collectors.groupingBy(BoardTechStackQueryDto::getBoardId));
            boardQueryDtos.forEach(boardQueryDto ->
                    boardQueryDto.setTechStacks(boardTechStackMap.get(boardQueryDto.getBoardId())));
        }
    }

    public BooleanExpression isBookmarked(Boolean bookmarked, User user){
        if (!(bookmarked && user != null)) return null;
        return scrap.user.eq(user);
    }

    public BooleanExpression checkRecruitmentStatus(String recruitmentStatus){
        if (StringUtils.isNullOrEmpty(recruitmentStatus)) return null;
        return board.recruitmentStatus.eq(RecruitmentStatus.getRecruitmentStatusFromString(recruitmentStatus));
    }

    public BooleanExpression checkTechStacks(List<String> techStacks){
        if (techStacks == null || techStacks.isEmpty()) return null;
        return boardTechStack.techStack.techStackName.in(techStacks);
    }

    public BooleanExpression checkPositions(List<String> positions){
        if (positions == null || positions.isEmpty()) return null;
        return boardPosition.position.positionName.in(positions);
    }

    public BooleanExpression checkLocation(String location) {
        if (location == null || location.isEmpty()) return null;
        return board.location.eq(Location.fromString(location));
    }


}

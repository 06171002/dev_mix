package msa.devmix.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.devmix.config.oauth.userinfo.UserPrincipal;
import msa.devmix.domain.user.User;
import msa.devmix.dto.response.BoardListResponse;
import msa.devmix.dto.response.ResponseDto;
import msa.devmix.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v3/boards")
@Slf4j
@RequiredArgsConstructor
public class BoardV3Controller {

    private final BoardService boardService;

    @GetMapping()
    public ResponseEntity<?> boards(@RequestParam(defaultValue = "1") int pageNumber,
                                    @RequestParam(defaultValue = "16") int pageSize,
                                    @RequestParam(required = false, value = "location") String location,
                                    @RequestParam(required = false) List<String> positions,
                                    @RequestParam(required = false) List<String> techStacks,
                                    @RequestParam(required = false) boolean bookmarked,
                                    @RequestParam(required = false, value = "recruitmentStatus") String recruitmentStatus,
                                    @AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = null;
        if(userPrincipal != null) user = userPrincipal.getUser();

        return ResponseEntity
                .ok()
                .body(ResponseDto
                        .success(boardService
                                .getBoardsWithUser(
                                        pageNumber,
                                        pageSize,
                                        location,
                                        positions,
                                        techStacks,
                                        bookmarked,
                                        recruitmentStatus,
                                        user)
                                .stream()
                                .map(BoardListResponse::from)
                                .toList()));

    }

    @GetMapping("/total-boards")
    public ResponseEntity<?> totalBoards(@RequestParam(required = false, value = "location") String location,
                                         @RequestParam(required = false) List<String> positions,
                                         @RequestParam(required = false) List<String> techStacks,
                                         @RequestParam(required = false) boolean bookmarked,
                                         @RequestParam(required = false, value = "recruitmentStatus") String recruitmentStatus,
                                         @AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = null;
        if(userPrincipal != null) user = userPrincipal.getUser();


        return ResponseEntity.ok().body(ResponseDto.success(boardService.getBoardsCount(
                location,
                positions,
                techStacks,
                bookmarked,
                recruitmentStatus,
                user)));
    }
}

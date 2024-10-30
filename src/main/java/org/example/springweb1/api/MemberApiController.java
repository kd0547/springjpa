package org.example.springweb1.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.springweb1.domain.Member;
import org.example.springweb1.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    /**
     * 회원 정보를 반환
     * @return
     */
    @GetMapping("/api/v1/members")
    public List<Member> Memberlist() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberListV2() {
        List<Member> findmembers = memberService.findMembers();
        List<MemberDto> memberDtoList= findmembers.stream()
                .map(member -> new MemberDto(member.getUsername()))
                .toList();

        return new Result<>(memberDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String username;
    }



    /**
     * 회원 가입
     * @param request
     * @return
     */
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setUsername(request.getUsername());

        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    /**
     * 회원 수정
     * @param request
     * @return
     */
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id")Long id,
            @RequestBody @Valid CreateMemberRequest request) {
        memberService.update(id,request.getUsername());
        Member findMember= memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(),findMember.getUsername());
    }


    @Data
    static class UpdateMemberRequest {
        private String username;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String username;
    }


    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String username;

    }


    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

}

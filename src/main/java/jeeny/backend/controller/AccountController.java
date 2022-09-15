package jeeny.backend.controller;

import io.jsonwebtoken.Claims;
import jeeny.backend.entity.Item;
import jeeny.backend.entity.Member;
import jeeny.backend.repository.ItemRepository;
import jeeny.backend.repository.MemberRepository;
import jeeny.backend.service.JwtService;
import jeeny.backend.service.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class AccountController {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(
            @RequestBody Map<String, String> params,
            HttpServletResponse res) {
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

        if (member != null) {
            //JwtService jwtService = new JwtServiceImpl();10.로그인
            int id = member.getId();
            String token = jwtService.getToken("id", id);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }
//        return 0;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

//이주소로 post로 데이터 요청받음
//메서드정의
//인자를 임의정의한 params로 받고
//이메일과 패스워드를 findByEmailAndPassword메서드에 인자값대체할당
//결과값을 member로 받음
//member값이 null이 아닐때
//결과값의 id값을 리턴해줌
//member값이 null이면 0


//check로 쿠키값받음 쿠키이름 설정한 token 필수는 아니야
//jwtService통해 token값 가져오고 Claims에 저장
//만약 이값이 null이 아니면
//id값 가져오고 toString으로 컨버팅하고
//id 값
//아니면  null


package com.spring.myweb.controller;

import javax.servlet.http.HttpSession;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.command.KakaoUserVO;
import com.spring.myweb.command.UserVO;
import com.spring.myweb.freeboard.service.IFreeBoardService;
import com.spring.myweb.user.service.IUserService;
import com.spring.myweb.util.KakaoService;
import com.spring.myweb.util.MailSenderService;
import com.spring.myweb.util.PageCreator;
import com.spring.myweb.util.PageVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	@Autowired
	private IFreeBoardService boardService;
	@Autowired
	private MailSenderService mailService;
	@Autowired
	private KakaoService kakaoService;
	
	
	
	//회원가입 페이지로 이동
	@GetMapping("/userJoin")
	public void userJoin() {}
	
	
	//아이디 중복 확인(비동기)
	@ResponseBody
	@PostMapping("/idCheck")
	public String idCheak(@RequestBody String userId) {
		log.info("화면단으로부터 전달된 값: "+userId);
		
		int result = service.idCheck(userId);
		if(result == 1) return "dublicated";
		else return "ok";
	}
	
	
	//이메일 인증
	@ResponseBody
	@GetMapping("/mailCheck")
	public String mailCheak(String email) {
		log.info("이메일 인증 요청 들어옴."+email);
		
		return mailService.joinEmail(email);
	}
	
	//회원 가입 처리
	@PostMapping("/join")
	public String join(UserVO vo, RedirectAttributes ra) {
//		log.info("param: "+vo);
		log.info("param: {}", vo.toString());
		service.join(vo);
		ra.addFlashAttribute("msg", "joinSuccess"); 
		//회원가입을 통해 이동한 경우 msg가 있다. 그냥 로그인으로 가면 문구가 없음. 
		
		return "redirect:/user/userLogin";
	}
	
	
	//로그인 페이지로 이동 요청
	@GetMapping("/userLogin")
	public void login(Model model, HttpSession session) {
		/* 카카오 URL을 만들어서 user.Login.jsp로 보내야 한다. */
		
		String kakaoAuthUrl = kakaoService.getAuthorizationUrl(session);
		log.info("카카오 로그인 url: {}", kakaoAuthUrl);
		model.addAttribute("urlkakao", kakaoAuthUrl);
		
	}
	
	//카카오 로그인 성공 시 callback
	@GetMapping("/kakao_callback")
	public void callbackKakao(String code, String state, HttpSession session, Model model) {
		log.info("로그인 성공! callbackKakao 호출!");
		log.info("인가 코드: {}", code);
		String accessToken = kakaoService.getAccessToken(session, code, state);
		log.info("access 토큰 값: "+accessToken);
		
		//accessToken을 이용하여 로그인 사용자 정보를 읽어 오자
		KakaoUserVO vo = kakaoService.getUserProfile(accessToken);
		
		//~~여기까지가 카카오 로그인 api가 제공하는 기능들이다.
		//추가 입력 정보가 필요하다면 추가 입력할 수 있는 페이지로 보내 입력을 더 받는다.
		//데이터 베이스에 데이터를 집어 넣어야 한다.
		

//		return "";
	}
	
	
	//로그인 요청
	@PostMapping("/userLogin")
	public void userLogin(String userId, String userPw, Model model) {
		log.info("userLogin 컨트롤러 실행중...");
		model.addAttribute("user", service.login(userId, userPw));
		//로그인에 성공했다면 UserVO, 실패했다면 null이 리턴된다.
		
	}
	
	
	//마이페이지 이동 요청
	@GetMapping("/userMypage")
	public void userMypage(HttpSession session, Model model, PageVO vo) {
		
		//세션 데이터에서 id를 뽑아야 sql을 돌릴 수 있다!
		String id = (String) session.getAttribute("login");
		
		vo.setLoginId(id); //현재 로그인중인 사용자 전달
		PageCreator pc = new PageCreator(vo, boardService.getTotal(vo) );
		model.addAttribute("userInfo", service.getInfo(id, vo));
		model.addAttribute("pc", pc);
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

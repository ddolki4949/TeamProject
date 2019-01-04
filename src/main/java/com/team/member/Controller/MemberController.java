package com.team.member.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team.follow.Service.FollowService;
import com.team.follow.VO.FollowVO;
import com.team.member.Service.MemberServiceImpl;
import com.team.member.VO.MemberVO;

@Controller
@SessionAttributes("member")
public class MemberController {

	@Autowired
	MemberServiceImpl memberService;
	
	@Autowired
	FollowService followService;
	
	@RequestMapping("/check")
	public String check() {
		return "member/check";
	}
		
	@RequestMapping("/signUp")
	public String insertMember(Model model) {
		model.addAttribute("member", new MemberVO());
		return "member/signUp";
	}
	

	@RequestMapping("/insertOk")
	//@Valid(벨리드:미리체크 하겠다는 뜻)어노테이션기호를 사용하여 
	//작성한 회원 내용정보가 있는 MybatisMember(DTO)객체의 유효성 체크를 한다.
	//BindingResult result로 유효성체크의 결과정보를 전달 받는다.
	public String insertOk(@Valid MemberVO member, BindingResult result, Map<String, BindingResult> model) {
		
		model.put(BindingResult.class.getName()+".member", result);
		if(result.hasErrors()) { //에러가 있으면
			System.out.println(result.toString());
			System.out.println("회원가입시 오류가 발생했습니다.");		
			return "member/signUp";
		}else {
			//에러가 없으면 DB에 작성한 내용 insert
			memberService.InsertMember(member);
			return "login";
		}
	}
	
	@RequestMapping("{ID}/mypage")
	public String mypageMember(@PathVariable String ID, HttpSession session, Model model) {
		MemberVO vo = memberService.getMember(ID);
		model.addAttribute("profile",vo);
		
		MemberVO sessionVO = (MemberVO)session.getAttribute("member");
		
		// uri의 ID값과 로그인 정보랑 다를때 main으로 이동하게 변경
		if(!ID.equals(sessionVO.getID())) {
			return "redirect:/main";
		}
		//model.addAttribute("member",vo);
		
		return "member/mypage";
	}
	
	@RequestMapping("/mypageOk")
	public String mypageOk(@Valid MemberVO member,BindingResult result, Map<String, BindingResult> model) {
		
		model.put(BindingResult.class.getName()+".member", result);
		if(result.hasErrors()) {
			System.out.println(result.toString());
			System.out.println("회원정보 수정시 오류가 발생");
			return "member/mypage";
		}else {
			memberService.updateMember(member);
			return "login";
		}
	}
	
	//search?keyword=~~~ 로 요청시 처리
	@RequestMapping("/search")
	public String getSearchList(@RequestParam String keyword, HttpSession session ,Model model) {
		model.addAttribute("profile", (MemberVO) session.getAttribute("member"));
		model.addAttribute("keyword", keyword);
		
		return "main.jsp?center=member/search";
	}
	
	@RequestMapping("/getSearchList")
	@ResponseBody
	public List<Map<String, String>> SearchMemberList(@RequestBody Map<String, String> searchInfo, HttpSession session) {
		// 맵에 담긴 정보 <검색어, 페이지번호>
		
		// 리턴 해줘야 하는 정보 (아이디,이메일,프로필사진,팔로우여부)
		System.out.println("getSeachList 호출완료.");
		//System.out.println("getSearchList " + searchInfo.get("keyword") + " " + searchInfo.get("pageNum"));
		
		// 정보를 12개씩 표현하겠다는 뜻.
		String pageNum = String.valueOf(Integer.parseInt(searchInfo.get("pageNum")) * 12);
		
		searchInfo.put("pageNum", pageNum);
		
		// 검색값 얻어오기
		List<MemberVO> member = memberService.GetSearchMember(searchInfo);
		
		// 로그인 여부 체크하기 위해 세션값 받아옴.
		MemberVO memVO = (MemberVO)session.getAttribute("member");
		
		// 팔로우 여부를 맵으로 저장. <아이디, 팔로우여부>
		List<Map<String, String>> searchInfoList = new ArrayList<>();
		
		
			
		for (MemberVO temp : member) {
			Map<String, String> tempMap = new HashMap<>();
			String isFollowed = "";
			// 세션 member가 존재할때. = 로그인 되어있을때만.
			if (memVO != null) {
				
				

				FollowVO fVo = new FollowVO();
				fVo.setFollower_id(memVO.getID());
				fVo.setFollowing_id(temp.getID());

				// temp의 ID가 로그인된 ID와 같으면 팔로우 여부를 체크 할 필요없다.
				if (memVO.getID().equals(temp.getID()))
					tempMap.put("isFollowed", "");
				else
					isFollowed = String.valueOf(followService.IsFollowing(fVo));
			}


			// 이미지 비어있을때 기본 이미지로 변경.
			if(temp.getPIC() == null) {
				temp.setPIC("/resources/img/baby.jpg");
			}
			
			tempMap.put("sMem_id", temp.getID());
			tempMap.put("sMem_email", temp.getEMAIL());
			tempMap.put("sMem_pic", temp.getPIC());
			tempMap.put("isfollowed", isFollowed);
			searchInfoList.add(tempMap);
		}
		
		
		
		return searchInfoList;
	}

}


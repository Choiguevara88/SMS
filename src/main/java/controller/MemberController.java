package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Member;
import logic.ProjectService;


@Controller
public class MemberController {
	@Autowired
	ProjectService service;
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	
	@ModelAttribute
	public Member getMember() {
		return new Member();
	}
	
	
	@RequestMapping(value="joinForm") //����媛��� �쇱�쇰� 
	public String joinForm() {
		return "member/joinForm";
	}

	
	@RequestMapping("member/join") //����媛���
	public ModelAndView join(@Valid Member member, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		try {
		service.joinsms(member);
		mav.setViewName("redirect: ../login.sms");
		mav.addObject("member",member);
		} catch(DataIntegrityViolationException e) {
			bindingResult.reject("error.duplicate.user");
		}
		return mav;
	}
	@RequestMapping(value = "login", method = RequestMethod.GET) //URL濡� 寃����댁�� �ㅼ�� ���� ��
	public String loginForm() {
		return "member/loginpage";
	}
	@RequestMapping(value = "loginbyNaver")
	public ModelAndView loginbysns(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException, ParseException {
		String clientId = "Gq6yEGwFqkB9pHnvlf6E";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "GPb0l9WxBS";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    System.out.println(code);
	    String state = request.getParameter("state");
	    System.out.println(state);
	    String redirectURI = URLEncoder.encode("http://localhost:8080/TestProject/loginbysns.sms", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    System.out.println("code="+code+",state="+state);
	    String access_token = "";
	    String refresh_token = "";
	    StringBuffer res = new StringBuffer();
	    System.out.println("apiURL="+apiURL);
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	        System.out.println("\n===========res 1:");
	        System.out.println("res:" + res.toString());
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	    JSONParser parser = new JSONParser(); 
	    JSONObject json = (JSONObject)parser.parse(res.toString()); //json parsing
	    String token = (String)json.get("access_token");
	    System.out.println("\n=====token:"+token);
	    String header = "Bearer " + token; // Bearer 다음에 공백 추가
	    try {
	        apiURL = "https://openapi.naver.com/v1/nid/me";
//	        apiURL = "https://openapi.naver.com/v1/nid/getUserProfile";
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("Authorization", header);
	        int responseCode = con.getResponseCode();
	        BufferedReader br;
	        res = new StringBuffer();
	        if(responseCode==200) { // 정상 호출
	        	System.out.println("로그인 정보 정상 수신");
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {  // 에러 발생
	        	System.out.println("로그인 정보 오류 수신");
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        String inputLine;
	        while ((inputLine = br.readLine()) != null) {
	            res.append(inputLine);
	        }
	        br.close();
	        System.out.println(res.toString());
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    json = (JSONObject)parser.parse(res.toString());
		System.out.println(json);  //json값으로 나옴	
		JSONObject jsondetail = (JSONObject)json.get("response");
		String email = (String)jsondetail.get("email");
		Member loginMember = service.find_member_by_email(email);
		System.out.println(loginMember);
		ModelAndView mav = new ModelAndView();
		if(loginMember == null) {
			mav.addObject("email", email);
			mav.addObject("name",(String)jsondetail.get("name"));
			mav.setViewName("member/joinForm");
		}
		else {
			session.setAttribute("loginMember", loginMember);
			mav.setViewName("redirect: main.sms");
		}
		return mav;
	}
	
	@RequestMapping(value = "facebooklogin")
	public String loginbyFB(HttpSession session) {
		String facebookurl = "https://www.facebook.com/v3.0/dialog/oauth?" + 
				"client_id=223500144933393" + 
				"&redirect_uri=http://localhost:8080/TestProject/facebookAccessToken.sms" + 
				"&scope=public_profile,email";
		return "redirect:" +facebookurl;
	}
	@RequestMapping(value="facebookAccessToken")
	public ModelAndView loginWithFB1(String code, HttpSession session, String state) throws ClientProtocolException, IOException, ParseException {
		System.out.println(session);
		System.out.println(code);
		System.out.println(state);
		String accessToken = requesFacebooktAccessToken(session,code); //이미 여기서 session으로 accessToken이 등록이 되고 여기로 넘어옴
		System.out.println("====");
		System.out.println(accessToken);
		//facebookUserDataLoadAndSave(accessToken, session);
		String facebookUrl = "https://graph.facebook.com/me?"+
	            "access_token="+ accessToken +
	            "&fields=id,name,email";
	    HttpClient client = HttpClientBuilder.create().build();
	    HttpGet getRequest = new HttpGet(facebookUrl);
	    String rawJsonString = client.execute(getRequest, new BasicResponseHandler());
	    System.out.println(rawJsonString); //결과값
	    JSONParser jsonParser = new JSONParser();
	    JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
	    System.out.println(jsonObject.get("id"));
	    System.out.println(jsonObject.get("name"));
	    System.out.println(jsonObject.get("email"));
	    String email = (String)jsonObject.get("email");
	    String name = (String)jsonObject.get("name");
	    Member member = service.find_member_by_email(email);
	    	ModelAndView mav = new ModelAndView();
	    if(member == null) {
	    		mav.addObject("name",name);
	    		mav.addObject("email",email);
	    		mav.setViewName("member/joinForm");
	    		return mav;
	    } else {
	    		session.setAttribute("loginMember", member);
	    		mav.setViewName("main");
	    		return mav;
	    }
	}



	private String requesFacebooktAccessToken(HttpSession session, String code) throws ClientProtocolException, IOException, ParseException {
		//code, client_id, client_secret을 이용한	AccessToken얻기
		//AccessToken이 있어야 라인 190에서 처럼 결과를 얻어올수 있다
		String facebookurl = "https://graph.facebook.com/v2.8/oauth/access_token?client_id=223500144933393"
				+ "&redirect_uri=http://localhost:8080/TestProject/facebookAccessToken.sms"
				+ "&&client_secret=60704d2d09d27d6fdbe2f034cf0ecdda&code="+code;
		System.out.println("a");
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(facebookurl);
		String rawJsonString = client.execute(getRequest, new BasicResponseHandler());
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
		String faceBookAccessToken = (String) jsonObject.get("access_token");
		session.setAttribute("faceBookAccessToken", faceBookAccessToken);
		
		return faceBookAccessToken;
	}
	
	@RequestMapping("loginwithGoogle")
	public String loginwithGoogle(HttpServletResponse response, Model model) throws Exception {
		 OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		 String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		 model.addAttribute("url", url);
		 System.out.println(url);
		 return "member/loginwithGoogle";
	}
	@RequestMapping(value ="startwithGoogle", method=RequestMethod.GET)
	public ModelAndView startwithGoogle(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code , googleOAuth2Parameters.getRedirectUri(),null);
		String accessToken = accessGrant.getAccessToken();
		Long expireTime = accessGrant.getExpireTime();
		if (expireTime != null && expireTime < System.currentTimeMillis()) {
		   accessToken = accessGrant.getRefreshToken();
		   System.out.printf("accessToken is expired. refresh token = {}", accessToken);
		}
		System.out.println("aaa");
		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();

		PlusOperations plusOperations = google.plusOperations();
		Person profile = plusOperations.getGoogleProfile();
		
		System.out.println("profile.getAccountEmail : " + profile.getAccountEmail());
		
		System.out.println("profile : " + profile);
		
		Member member = service.find_member_by_email(profile.getAccountEmail());
		if(member == null) {
			mav.addObject("name",profile.getDisplayName());
			mav.addObject("email",profile.getAccountEmail());
			mav.setViewName("member/joinForm");
			return mav;
		} else {
			System.out.println(profile.getDisplayName());
			System.out.println(profile.getAccountEmail());
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			mav.setViewName("redirect: main.sms");
			return mav;
//		vo.setUser_snsId("g"+profile.getId());
//		HttpSession session = request.getSession();
//		vo = service.googleLogin(vo);

		//session.setAttribute("login", vo );

		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST) //id, pw���ν�댁�� 濡�洹몄�� ��瑜� ��
	public ModelAndView loginForm(@Valid Member member, BindingResult bindingResult, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			mav.setViewName("member/loginpage");
			return mav;
		}
		
		Member dbmember = service.getMember(member.getId());
		
		if(dbmember == null) {
			bindingResult.reject("error.login.id");
			mav.setViewName("member/loginpage");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		if(dbmember.getPw().equals(member.getPw())) {
			mav.setViewName("redirect: main.sms");
			mav.addObject("dbmember",dbmember);
			session.setAttribute("loginMember", dbmember);
		} else {
			bindingResult.reject("error.login.password");
			mav.getModel().putAll(bindingResult.getModel());
			mav.setViewName("member/loginpage");
		}
		
		return mav;
	}
	
	@RequestMapping(value="logout") //�몄���� ��怨� 由щ�ㅼ�대���몃� �댁＜硫� ���댁�� String, redirect �ъ��
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect: main.sms";
	}
	@RequestMapping(value="personal_info")
	public ModelAndView personal_info(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member)session.getAttribute("loginMember");
		mav.addObject("member");
		mav.setViewName("member/personal_info");
		return mav;
	}
	@RequestMapping(value="personal_info_update")
	public ModelAndView personal_info_update(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/update");
		return mav;
	}
	@RequestMapping(value="personal_info_new")
	public ModelAndView personal_info_new(@Valid Member member, BindingResult bindingResult, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			mav.setViewName("personal_info_update");
			return mav;
		}
		service.updateMember(member);
		mav.setViewName("redirect: personal_info.sms?id=" + member.getId());
		return mav;
	}
	
	@RequestMapping(value="personal_info_delete")
	public ModelAndView personal_info_delete(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/delete_account");
		return mav;
	}
	
	@RequestMapping(value="personal_info_delete_confirm" )
	public ModelAndView personal_info_delete_confirm(String id, String pw, HttpSession session) {
		ModelAndView mav  = new ModelAndView();
		Member member = service.getMember(id);
		if(member.getPw().equals(pw)) {
			service.deleteAccount(member);
			session.invalidate();
			mav.setViewName("after_delete_account");
			return mav;
		}
		else {
			throw new ProjectException("비밀번호가 다릅니다.. 다시 시도해주세여","personal_info_delete.sms?id=" +member.getId());
		}
	}
	
	@RequestMapping(value="checkID") //ID중복확인. 여기서만 나오고 끝남
	public @ResponseBody String checkID(String id) {
		Member member = service.getMember(id);
		if (member == null) {
			return "0";
		}
		return "1";
	}
	@RequestMapping(value="becomeaHost")
	public ModelAndView becomeaHost(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member)session.getAttribute("loginMember");
		System.out.println("asdf");
		System.out.println(member);
		System.out.println(member.getRegStatus());
		if(member.getRegStatus() != null && member.getRegStatus() == 0) {
			mav.setViewName("member/waitforcompletion");
			return mav;
		}else {
		mav.setViewName("member/becomeaHost");
		return mav;
		}
	}
	@RequestMapping(value="addhostdata")
	public ModelAndView addhostdata(Member member, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println(member);
		service.becomeaHost(member,request);
		Member member1 = service.getMember(member.getId());
		session.setAttribute("loginMember", member1);
		mav.setViewName("redirect: becomeahost_complete.sms");
		return mav;
	}
	@RequestMapping(value="becomeahost_complete")
	public ModelAndView becomeahost_complete(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		System.out.println("AAAA");
		System.out.println(session.getAttribute("loginMember"));
		mav.setViewName("member/becomeahost_complete");
		return mav;
	}
	@RequestMapping(value="findmyID")
	public ModelAndView findmyID() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/findmyID");
		return mav;
	}
	
	@RequestMapping(value="letsfindID")
	public ModelAndView letsfindID(String name, String email) {
		Member member = service.find_member(name, email);
		System.out.println(member);
		ModelAndView mav = new ModelAndView();
		if(member == null) {
			mav.setViewName("member/findID_result");
			return mav;
		}
		else {
			String id = member.getId();
			mav.addObject("id",id);
			mav.setViewName("member/findID_result");
			return mav;
		}
	}
	
	@RequestMapping(value="findmypassword")
	public ModelAndView findmypassword() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/findmypassword");
		return mav;
	}
	
	@RequestMapping(value="letsfindmypassword")
	public ModelAndView letsfindmypassword(String name,String email, String id) {
		
		ModelAndView mav = new ModelAndView();		
		Member member = service.find_password(id, email, name);
		
		if(member == null) {
			mav.setViewName("member/findpassword_result");
			
			return mav;
			
		}else {
			mav.addObject("member",member);
			mav.setViewName("member/findpassword_result");
			return mav;
		}
	}
}
package aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import exception.ProjectException;
import logic.Member;

@Component // 객체화 1134567
@Aspect // AOP클래스 지칭 test
public class LoginAspect {

	// MemberController.mypage(String id, HttpSession session) 메서드 호출 전에
	// memberLoginCheck(..)메서드 호출
	
	@Around("execution(* controller.Member*.delete_con*(..))")
	public Object memberLoginCheck1(ProceedingJoinPoint joinPoint) throws Throwable {

		String id = null;
		HttpSession session = null;
		Member paramMember = null;
		if (joinPoint.getArgs()[0] instanceof Member) {
			paramMember = (Member) joinPoint.getArgs()[0];
			session = (HttpSession) joinPoint.getArgs()[2];
			id = paramMember.getId();
		} else {
			id = (String) joinPoint.getArgs()[0]; // 파라미터 id 값
			session = (HttpSession) joinPoint.getArgs()[2]; // session 값
		}

		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if (loginMember == null) {throw new ProjectException("로그인 안한거 다 알아요~ 로그인 하세요~ ><", "main.sms");}
		System.out.println(id);
		System.out.println(loginMember.getId());
		System.out.println(loginMember.getId().equals("admin"));
		if (!id.equals(loginMember.getId()) && !loginMember.getId().equals("admin")) {
			throw new ProjectException("본인 아닌거 다 알아여~ 본인꺼만 쓰세여~ ><", "main.sms");
		}
		Object ret = joinPoint.proceed(); // CoreAlgolism 실행
		return ret;
	}
	
	//개인정보 보기 눌렀을때 실행되는 aop
	@Around("execution(* controller.Member*.personal*(..))")
	public Object memberLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {

		String id = null;
		HttpSession session = null;
		Member paramMember = null;
		if (joinPoint.getArgs()[0] instanceof Member) {
			paramMember = (Member) joinPoint.getArgs()[0];
			session = (HttpSession) joinPoint.getArgs()[2];
			id = paramMember.getId();
		} else {
			id = (String) joinPoint.getArgs()[0]; // 파라미터 id 값
			session = (HttpSession) joinPoint.getArgs()[1]; // session 값
		}

		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if (loginMember == null) {throw new ProjectException("로그인 안한거 다 알아요~ 로그인 하세요~ ><", "main.sms");}
		if (!id.equals(loginMember.getId()) && !loginMember.getId().equals("admin")) {
			throw new ProjectException("본인 아닌거 다 알아여~ 본인꺼만 쓰세여~ ><", "main.sms");
		}
		
		Object ret = joinPoint.proceed(); // CoreAlgolism 실행
		return ret;
	}
	
	@Around("execution(* controller.Member*.become*(..))")
	public Object becomeahost(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpSession	session = (HttpSession) joinPoint.getArgs()[0];
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if (loginMember == null) {throw new ProjectException("로그인 안한거 다 알아요~ 로그인 하세요~ ><", "main.sms");}
		if (loginMember.getId().equals("admin")) {
			throw new ProjectException("본인 아닌거 다 알아여~ 본인꺼만 쓰세여~ ><", "main.sms");
		}
		Object ret = joinPoint.proceed(); // CoreAlgolism 실행
		return ret;
	}
	@Around("execution(* controller.Member*.addhost*(..))")
	public Object addhostdata(ProceedingJoinPoint joinPoint) throws Throwable {
		String id = null;
		HttpSession session = null;
		Member paramMember = null;
		if (joinPoint.getArgs()[0] instanceof Member) {
			paramMember = (Member) joinPoint.getArgs()[0];
			session = (HttpSession) joinPoint.getArgs()[1];
			id = paramMember.getId();
		} else {
			id = (String) joinPoint.getArgs()[0]; // 파라미터 id 값
			session = (HttpSession) joinPoint.getArgs()[1]; // session 값
		}

		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if (loginMember == null) {throw new ProjectException("로그인 안한거 다 알아요~ 로그인 하세요~ ><", "main.sms");}
		if (!id.equals(loginMember.getId()) && !loginMember.getId().equals("admin")) {
			throw new ProjectException("본인 아닌거 다 알아여~ 본인꺼만 쓰세여~ ><", "main.sms");
		}
		
		Object ret = joinPoint.proceed(); // CoreAlgolism 실행
		return ret;
	}
	@Around("execution(* controller.Member*.letsfindID(..))")
	public Object findid_result(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpSession session = null;
		Member paramMember = null;
		String name = (String)joinPoint.getArgs()[0];
		String email = (String)joinPoint.getArgs()[1];

		if (name == null && email == null) {
			throw new ProjectException("그전 단계를 거치셔야해요~ ><", "findmyID.sms");
			}

		Object ret = joinPoint.proceed(); // CoreAlgolism 실행
		return ret;
	}
	@Around("execution(* controller.Member*.letsfindmypassword(..))")
	public Object findpassword_result(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpSession session = null;
		Member paramMember = null;
		String name = (String)joinPoint.getArgs()[0];
		String email = (String)joinPoint.getArgs()[1];
		String id = (String)joinPoint.getArgs()[2];

		if (name == null && email == null && id == null) {
			throw new ProjectException("그전 단계를 거치셔야해요~ ><", "findmypassword.sms");
			}

		Object ret = joinPoint.proceed(); // CoreAlgolism 실행
		return ret;
	}
	
	//adminLoginCheck() 메서드 : 관리자 업무 메서드에 대한 AOP
	@Around("execution(* controller.Admin*.admin*(..))")
	public Object adminLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		
		HttpSession session = null;
		Member loginMember = null;
		boolean adminable = false;
		
		for(int i = 0; i < joinPoint.getArgs().length; i++) {
			if(joinPoint.getArgs()[i] instanceof HttpSession) {
				session = (HttpSession)joinPoint.getArgs()[i];
				loginMember = (Member)session.getAttribute("loginMember");
				
				if(loginMember == null) {
					throw new ProjectException("관리자로 로그인 하세요.", "../login.sms");
				}
				
				if(!loginMember.getId().equals("admin")) {
					throw new ProjectException("관리자만 가능한 거래입니다.", "../login.sms");
				}
				
				adminable = true; 
				break;
			}
		}
		
		if(!adminable) {
			throw new ProjectException("전산부로 전화하세요. 세션 객체가 요구됨.", "../main.sms");
		}
		
		Object ret = joinPoint.proceed();
		return ret;
	}
	
	//HostLoginCheck() 메서드 : HOST 계정 업무 메서드에 대한 AOP
	@Around("execution(* controller.*.host*(..))") // host로 시작하는 메서드에 적용
		public Object hostLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
			
			HttpSession session = null;
			Member loginMember = null;
			boolean hostable = false;
			
			for(int i = 0; i < joinPoint.getArgs().length; i++) {
				if(joinPoint.getArgs()[i] instanceof HttpSession) {
					session = (HttpSession)joinPoint.getArgs()[i];
					loginMember = (Member)session.getAttribute("loginMember");
					
					if(loginMember == null) {
						throw new ProjectException("로그인 하세요.", "../login.sms");
					}
					
					if(loginMember.getHostName() == null && !loginMember.getId().equals("admin")) {
						throw new ProjectException("호스트 계정으로 가능한 업무입니다.", "../login.sms");
					}
					
					hostable = true;
					break;
				}
			}
			
			if(!hostable) {
				throw new ProjectException("전산부로 전화하세요. 세션 객체가 요구됨.", "../main.sms");
			}
			
			Object ret = joinPoint.proceed();
			
			return ret;
		}
	
	//예약정보를 실행하였을 때 거쳐가는 유효성 검증
	@Around("execution(* controller.Reserve*.*(..))")
	public Object memberReserveCheck(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpSession session = null;
		Member loginMember = null;
		boolean reservable = false;
		
		for(int i = 0; i < joinPoint.getArgs().length; i++) {
			
			if(joinPoint.getArgs()[i] instanceof HttpSession) {
				session = (HttpSession)joinPoint.getArgs()[i];
				loginMember = (Member)session.getAttribute("loginMember");
				
				if(loginMember == null) {
					throw new ProjectException("로그인이 필요합니다.", "../login.sms");
				}
				reservable = true;
				break;
			}
		}
		
		if(!reservable) {
			throw new ProjectException("전산부로 전화하세요. 세션 객체가 요구됨.", "../main.sms");
		}
		
		Object ret = joinPoint.proceed(); // CoreAlgolism 실행
		return ret;
	}
	
	
	// 내가 찜한 공간 메서드 실행 할 때 중간에 갑자기 세션 나가버렸을 때를 대비한 체크
	@Around("execution(* controller.*.*WishList*(..))")
	public Object wishLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpSession session = null;
		Member loginMember = null;
		boolean reservable = false;
		
		for(int i = 0; i < joinPoint.getArgs().length; i++) {
			
			if(joinPoint.getArgs()[i] instanceof HttpSession) {
				session = (HttpSession)joinPoint.getArgs()[i];
				loginMember = (Member)session.getAttribute("loginMember");
				
				if(loginMember == null) {
					throw new ProjectException("로그인이 필요합니다.", "../login.sms");
				}
				reservable = true;
				break;
			}
		}
		
		if(!reservable) {
			throw new ProjectException("전산부로 전화하세요. 세션 객체가 요구됨.", "../main.sms");
		}
		
		Object ret = joinPoint.proceed(); // CoreAlgolism 실행
		return ret;
	}
}

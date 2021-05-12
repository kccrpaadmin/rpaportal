package com.kcc.interceptors;

import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.IMenuService;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.IRouteUtilService;

public class AccessLogInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AccessLogInterceptor.class);
	
	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@Resource(name="routeUtilService")
	private IRouteUtilService routeUtilService;

	@Resource(name="accessService")
	private IAccessService accessService;
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		logger.info("preHandle");
		
		boolean result = true;
		
		/*
	 	접근하고자 하는 Url의 권한을 체크하여 이전 페이지로 이동 시킨다.
	 	예외적으로 Ajax, Modal, Upload는 접속권한을 체크하지 않았다. (이유는 Url 마다 메뉴 및 권한을 등록해야됨) 
	 	Access 컨트롤러는 스프링 시큐티리 구간에서 관리자와 사용자로 구분하여 접속 차단하지만,
	 	로그를 남기려면 Access 컨트롤러도 인터셉터에서 캐치하고 권한을 체크한다. 
	 	즉 관리자만 접근가능한 메뉴도 메뉴 및 권한 등록이 되어야 한다. 
	
 		이전 페이지로 HttpServletResponse res 리다이렉트 루프에러가 난다.
	 	HttpServletResponse 를 재정의 하면 가능 하다. 
	 	HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
	 	response.sendRedirect(sb.toString());
        
     	이슈1 - 권한 없는 경우를 체크하고 이전 페이지로 리다이렉트 하려했으나, 브라우져에서 직접 치는 경우 
     	req.getHeader("referer") 해당 함수로는 null이 들어와서 처리 불가능
        
	 	방법1. 브라우져에서 직접 치는 경우, 보안상의 이유라는 문구와 함께 차단하고 홈으로 보낸다.
	 	방법2. 권한을 체크하고 이전 페이지 대신 홈으로 보낸다.
		
	 	※ 권한 체크가 빠질경우, 페이지 이동에 상당한 부하를 줄일 수 있다.
		
	 	방법1
 		if (req.getHeader("referer") == null) {
			RouteUtil.getInstance().MessageAndMove("보안상의 이유로 Url 호출은 불가능 합니다.", "/Home/Home.do");
		}
		
		방법2
		if (curUrl.equals("/Production/SiteCostTotal.do")) {
			RouteUtil.getInstance().MessageAndMove("접근금지", prevUrl);	
		}
		
		// 이전 페이지 체크는 가능 하나, 브라우져에 직접 치는 경우는 null 이라서 이전 페이지 리턴 불가 
		String[] prevUrls = req.getHeader("referer").split("/");
		String prevUrl = "/" + prevUrls[3] + "/" + prevUrls[4];
		String curUrl = req.getRequestURI();
				
		logger.info("이전url :" + prevUrl);
		logger.info("호출url :" + curUrl);
		
		결론 -
		직접적인 Url 접근은 req.getHeader("referer") 가 null 이라서 이 부분을 홈으로 리다이렉트
		1. 정상적인 접근은 Ajax를 통해서 권한체크 후, 리턴
		2. 정상적인 접근은 인터셉터에서 권한체크 후, req.getHeader("referer")를 통해 이전 페이지를 구해서 리다이렉트
		
		직접적인 Url 접근도 허용하고 이전 페이지 체크를 공통화 할 수 있다면 더 좋을것 같다.		
	    */
		
		// 직접적인 Url 접근 차단
		if (req.getHeader("referer") == null) {
			routeUtilService.MessageAndMove("직접적인 Url 호출은 불가능 합니다.", "/Main/Home.do");
		}
		
		// 권한 체크해서 req.getHeader("referer") 이전 페이지 확인 후, 리턴 가능하나 
		// 이미 직접적인 Url 접근 차단 처리 했음으로 Ajax 통해서 처리할 예정
        try {
        	// 사용자 정보 조회
    		UseCustomUserDetails useCustomUserDetails = (UseCustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
              		
    		// 파라미터 정의 변수
    		StringBuilder sbParams = new StringBuilder( "" );
    		
    		// 모든 파라미터(GET) 출력, POST는 별도 처리 필요 
    		Enumeration params = req.getParameterNames();
    		while (params.hasMoreElements()) {
    			String name = (String)params.nextElement();
    			sbParams.append(req.getParameter(name));
    			sbParams.append(";");
    		}
    		
    		// 파라미터가 Login으로 넘어온 경우 확인
    		String loginType = "";
    		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(req);
    		if (inputFlashMap != null) {
    			loginType =  (String) inputFlashMap.get("pParam");
    		}
    		
    		 // 사이트 로그인 여부 확인 변수
    		String loginYn = "N"; 
    		if (loginType.equals("Login")) {
    			loginYn = "Y";
    		}
			
			// Account, Common은 인터셉터 설정파일에서 제외 시킴
			// Ajax, Modal은 제외 해야함.
			AccessVO accessVO = new AccessVO();
			accessVO.setUserId(useCustomUserDetails.getUserId());
			accessVO.setRoleType(useCustomUserDetails.getRoleType());
			
			accessVO.setAccessUrl(req.getRequestURI());
			accessVO.setAccessIp(commonUtilService.getClientIp(req));
			accessVO.setAccessDevice(commonUtilService.getDeviceType(req));
			accessVO.setParams(sbParams.toString());
			accessVO.setLoginYn(loginYn);
			accessVO.setEmpNo(useCustomUserDetails.getEmpNo());
			
			accessService.createAccess(accessVO);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
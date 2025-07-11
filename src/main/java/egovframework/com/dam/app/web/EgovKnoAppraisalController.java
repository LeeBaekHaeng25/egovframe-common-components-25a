package egovframework.com.dam.app.web;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.app.service.EgovKnoAppraisalService;
import egovframework.com.dam.app.service.KnoAppraisal;
import egovframework.com.dam.app.service.KnoAppraisalVO;

/**
 * <pre>
 * 개요
 * - 지식정보평가에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 지식정보평가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식정보평가의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 박종선
 * @since 2010.08.12
 * @version 1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.12  박종선          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2025.06.13  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(지역 변수 명명 규칙)
 *
 *      </pre>
 */
@Controller
public class EgovKnoAppraisalController {

	@Resource(name = "KnoAppraisalService")
	private EgovKnoAppraisalService knoAppraisalService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 등록된 지식정보평가 정보를 조회 한다.
	 * 
	 * @param KnoAppraisalVO -app 지식정보평가 VO
	 * @return String - 리턴 Url
	 *
	 * @param KnoAppraisalVO
	 */
	@IncludedInfo(name = "지식평가관리", listUrl = "/dam/app/EgovComDamAppraisalList.do", order = 1290, gid = 80)
	@RequestMapping(value = "/dam/app/EgovComDamAppraisalList.do")
	public String selectKnoAppraisalList(@ModelAttribute("searchVO") KnoAppraisalVO searchVO, ModelMap model)
			throws Exception {

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/** EgovPropertyService.mapMaterial */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		searchVO.setEmplyrId(loginVO.getUniqId());

		List<EgovMap> resultList = knoAppraisalService.selectKnoAppraisalList(searchVO);
		model.addAttribute("resultList", resultList);

		int totCnt = knoAppraisalService.selectKnoAppraisalTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/dam/app/EgovComDamAppraisalList";
	}

	/**
	 * 지식정보평가 상세 정보를 조회 한다.
	 * 
	 * @param KnoAppraisalVO - 지식정보평가 VO
	 * @return String - 리턴 Url
	 *
	 * @param KnoAppraisalVO
	 */
	@RequestMapping(value = "/dam/app/EgovComDamAppraisal.do")
	public String selectKnoAppraisal(KnoAppraisal knoAppraisal, ModelMap model) throws Exception {
		KnoAppraisal vo = knoAppraisalService.selectKnoAppraisal(knoAppraisal);
		model.addAttribute("result", vo);
		return "egovframework/com/dam/app/EgovComDamAppraisalDetail";
	}

	/**
	 * 기 등록 된 지식정보평가 정보를 수정 한다.
	 * 
	 * @param AppraisalknoAps - 지식정보평가 model
	 * @return String - 리턴 Url
	 *
	 * @param knoAps
	 */
	@GetMapping(value = "/dam/app/EgovComDamAppraisalModify.do")
	public String updateKnoAppraisalView(@ModelAttribute("knoId") KnoAppraisal knoAppraisal, ModelMap model)
			throws Exception {

		KnoAppraisal vo = knoAppraisalService.selectKnoAppraisal(knoAppraisal);
		model.addAttribute("knoAppraisal", vo);
		return "egovframework/com/dam/app/EgovComDamAppraisalModify";
	}

	/**
	 * 기 등록 된 지식정보평가 정보를 수정 한다.
	 * 
	 * @param AppraisalknoAps - 지식정보평가 model
	 * @return String - 리턴 Url
	 *
	 * @param knoAps
	 */
	@PostMapping(value = "/dam/app/EgovComDamAppraisalModify.do")
	public String updateKnoAppraisal(@ModelAttribute("knoId") KnoAppraisal knoAppraisal, BindingResult bindingResult,
			ModelMap model) throws Exception {

		beanValidator.validate(knoAppraisal, bindingResult);
		if (bindingResult.hasErrors()) {
			KnoAppraisal vo = knoAppraisalService.selectKnoAppraisal(knoAppraisal);
			model.addAttribute("knoAppraisal", vo);
			return "egovframework/com/dam/app/EgovComDamAppraisalModify";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 아이디 설정
		if (loginVO != null) {
			knoAppraisal.setLastUpdusrId(loginVO.getUniqId());
			knoAppraisal.setSpeId(loginVO.getUniqId());
		}

		knoAppraisalService.updateKnoAppraisal(knoAppraisal);
		return "forward:/dam/app/EgovComDamAppraisalList.do";
	}

}
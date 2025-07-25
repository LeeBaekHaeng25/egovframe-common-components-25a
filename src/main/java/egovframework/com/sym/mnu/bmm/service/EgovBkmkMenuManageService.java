
package egovframework.com.sym.mnu.bmm.service;

import java.util.List;
import java.util.Map;

import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

/**
 * 바로가기메뉴관리 정보를 관리하기 위한 서비스 인터페이스 클래스
 * 
 * @author 공통컴포넌트팀 윤성록
 * @since 2009.09.25
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.09.25  윤성록          최초 생성
 *   2025.07.15  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FormalParameterNamingConventions(변수명에 밑줄 사용)
 *
 *      </pre>
 */
public interface EgovBkmkMenuManageService {

	/**
	 * 바로가기메뉴관리 정보를 삭제한다.
	 * 
	 * @param bkmkMenuManage
	 * @return
	 * @exception Exception
	 */
	public void deleteBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception;

	/**
	 * 바로가기메뉴관리 정보를 등록한다.
	 * 
	 * @param BkmkMenuManage
	 * @return
	 * @exception Exception
	 */
	public void insertBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception;

	/**
	 * 바로가기메뉴관리 정보의 전체목록을 조회한다.
	 * 
	 * @param BkmkMenuManage
	 * @return Map<String, Object>
	 * @exception Exception
	 */
	public Map<String, Object> selectBkmkMenuManageList(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

	/**
	 * 바로가기메뉴관리 정보를 조회한다.
	 * 
	 * @param BkmkMenuManageVO
	 * @return BkmkMenuManageVO
	 * @exception Exception
	 */
	public BkmkMenuManageVO selectBkmkMenuManageResult(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

	/**
	 * 등록할 메뉴정보 목록을 조회한다.
	 * 
	 * @param BkmkMenuManageVO
	 * @return Map<String, Object>
	 * @exception Exception
	 */
	public Map<String, Object> selectMenuList(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

	/**
	 * 미리보기를 할 바로가기메뉴관리의 목록을 조회한다.
	 *
	 * @param BkmkMenuManageVO
	 * @return List<MenuManageVO>
	 * @throws Exception
	 */
	public List<MenuManageVO> selectBkmkPreviewList(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

	/**
	 * 선택된 메뉴의 URL 을 조회한다.
	 *
	 * @param bkmkMenuManage
	 * @return
	 * @throws Exception
	 */
	public String selectUrl(BkmkMenuManage bkmkMenuManage) throws Exception;

}

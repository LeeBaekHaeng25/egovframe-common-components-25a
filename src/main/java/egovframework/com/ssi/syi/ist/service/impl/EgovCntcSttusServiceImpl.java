package egovframework.com.ssi.syi.ist.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.com.ssi.syi.ist.service.CntcSttus;
import egovframework.com.ssi.syi.ist.service.CntcSttusVO;
import egovframework.com.ssi.syi.ist.service.EgovCntcSttusService;

/**
 * 연계현황에 대한 서비스 구현클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2025.06.30  이백행          컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FormalParameterNamingConventions(변수명에 밑줄 사용)
 *
 *      </pre>
 */
@Service("CntcSttusService")
public class EgovCntcSttusServiceImpl extends EgovAbstractServiceImpl implements EgovCntcSttusService {

	@Resource(name = "CntcSttusDAO")
	private CntcSttusDAO cntcSttusDAO;

	/**
	 * 연계현황 상세항목을 조회한다.
	 */
	@Override
	public CntcSttus selectCntcSttusDetail(CntcSttus cntcSttus) throws Exception {
		CntcSttus ret = cntcSttusDAO.selectCntcSttusDetail(cntcSttus);
		return ret;
	}

	/**
	 * 연계현황 목록을 조회한다.
	 */
	@Override
	public List<EgovMap> selectCntcSttusList(CntcSttusVO searchVO) throws Exception {
		return cntcSttusDAO.selectCntcSttusList(searchVO);
	}

	/**
	 * 연계현황 총 개수를 조회한다.
	 */
	@Override
	public int selectCntcSttusListTotCnt(CntcSttusVO searchVO) throws Exception {
		return cntcSttusDAO.selectCntcSttusListTotCnt(searchVO);
	}

}

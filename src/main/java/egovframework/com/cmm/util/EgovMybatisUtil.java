package egovframework.com.cmm.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * EgovMybatisUtil 클래스
 *
 * @author 장동한
 * @since 2016.06.07
 * @version 1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2016.06.07  장동한          최초 생성
 *   2017.03.03  조성원          시큐어코딩(ES)-오류 메시지를 통한 정보노출[CWE-209]
 *   2017.07.21  장동한          isEquals에서 String Character 비교 가능하도록
 *   2023.05.01  이백행          컬렉션은 원시 유형입니다. 일반 유형 컬렉션 <e>에 대한 참조는 매개 변수화되어야합니다
 *   2025.05.28  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(필드 명명 규칙), UselessParentheses(쓸모없는 괄호)
 *
 *      </pre>
 */
public class EgovMybatisUtil {

	/**
	 * Empty 여부를 확인한다.
	 * 
	 * @param o Object
	 * @return boolean
	 * @exception IllegalArgumentException
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}

		if (o instanceof String) {
			if (((String) o).length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection<?>) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map<?, ?>) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}

		return false;
	}

	/**
	 * Not Empty 여부를 확인한다.
	 * 
	 * @param o Object
	 * @return boolean
	 * @exception IllegalArgumentException
	 */
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	/**
	 * Equal 여부를 확인한다.
	 * 
	 * @param obj Object, obj Object
	 * @return boolean
	 */

	public static boolean isEquals(Object obj, Object obj2) {
		if (isEmpty(obj)) {
			return false;
		}

		if (obj instanceof String && obj2 instanceof String) {
			if (String.valueOf(obj).equals(String.valueOf(obj2))) {
				return true;
			}
		} else if (obj instanceof String && obj2 instanceof Character) {
			if (String.valueOf(obj).equals(String.valueOf(obj2))) {
				return true;
			}
		} else if (obj instanceof String && obj2 instanceof Integer) {
			if (String.valueOf(obj).equals(String.valueOf(obj2))) {
				return true;
			}

		} else if (obj instanceof Integer && obj2 instanceof String) {
			if (String.valueOf(obj2).equals(String.valueOf(obj))) {
				return true;
			}
		} else if (obj instanceof Integer && obj2 instanceof Integer) {
			if ((Integer) obj == (Integer) obj2) {
				return true;
			}
		}

		return false;
	}

	/**
	 * String의 Equal 여부를 확인한다.
	 * 
	 * @param obj Object, obj Object
	 * @return boolean
	 */
	public static boolean isEqualsStr(Object obj, String s) {
		if (isEmpty(obj)) {
			return false;
		}

		if (String.valueOf(obj).equals(s)) {
			return true;
		}
		return false;
	}

}

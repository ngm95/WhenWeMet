package com.spring.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.project.dto.MeetingDTO;
import com.spring.project.util.MeetingRequest;

@Repository
public class MeetingDAO{
	private static final String NAMESPACE = "com.spring.project.mappers.sql.MeetingMapper";
	
	@Inject
	SqlSession sqlSession;
	
	/**
	 * MeetingDTO를 이용한 INSERT작업
	 * @param meetingDto 데이터베이스에 넣을 DTO
	 */
	public void create(MeetingDTO meetingDto) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", meetingDto);
	}
	
	/**
	 * MeetingRequest를 이용한 INSERT작업
	 * @param meRe 데이터베이스에 넣을 정보
	 */
	public void create(MeetingRequest meRe) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", meRe);
	}
	
	/**
	 * 모임 이름을 이용해 모임 정보를 반환.
	 * 중복되는 이름이 있으면 여러 개를 반환하게 바꿀 필요가 있음.
	 * @param mname 모임 이름
	 * @return MeetingDTO
	 */
	public MeetingDTO readByName(String mname) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".readByName", mname); 
	}
	
	/**
	 * 모임 생성자 ID를 이용해 모임 정보를 반환.
	 * 한 사람이 여러 모임을 생성할 경우 여러 개를 반환하게 바꿀 필요 있음.
	 * @param creator 모임 생성자
	 * @return MeetingDTO
	 */
	public MeetingDTO readByCreator(String creator) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".readByCreator", creator); 
	}
	
	//mid로 meetingdto 검색
	public MeetingDTO readByMid(int mid) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".readByMid", mid); 
	}
	
	/**
	 * 모임 이름과 모임 생성자 ID를 모두 사용해 유일한 모임 정보를 반환. 
	 * @param mname 모임 이름
	 * @param creator 모임 생성자
	 * @return MeetingDTO
	 */
	public MeetingDTO readUniqueMeeting(String mname, String creator) throws Exception {
		MeetingDTO meetingDto = new MeetingDTO();
		meetingDto.setMname(mname);
		meetingDto.setCreator(creator);
		return sqlSession.selectOne(NAMESPACE + ".readUniqueMeeting", meetingDto); 
	}
	
	public void update(MeetingDTO meetingDto) throws Exception {
		sqlSession.update(NAMESPACE + ".update", meetingDto);
	}
	
	public void delete(Integer mid) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", mid); 
	}
	
	public List<MeetingDTO> listAll(String userid) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listAll", userid);
	}
}
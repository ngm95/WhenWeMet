package mapper;

import java.util.List;

import dto.ScheduleDTO;

public interface ScheduleMapper {
	public void insert(ScheduleDTO dto);
	public void delete(long sid);
	public void update(ScheduleDTO dto);
	public List<ScheduleDTO> select(String userid);
}

package dao;

import java.util.List;

import logic.Building;

public interface BuildingDao {

	int hostBuildCnt(String hostName);

	List<Integer> hostHaveBuildsNo(String hostId);

	Building resInfo(String hostName, Integer sNo);

}

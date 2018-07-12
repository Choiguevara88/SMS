package dao;

import java.util.List;

import logic.Building;

public interface BuildingDao {

	int hostBuildCnt(String hostName);

	List<Integer> hostHaveBuildsNo(String hostId);

	Building resInfo(String hostName, Integer sNo);

	int maxNum();

	void buRegist(Building building);

	List<Building> getMyBuildings(String id);

	Building getMyBuildingOne(String sNo);

	void buUpdateReg(Building building);

	Building getbuilding_mainpage(int sNo);

	void budelete(Integer sNo);
}

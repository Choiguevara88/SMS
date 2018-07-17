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

	Building getbuilding_mainpage_reviewCount(Integer integer);

	List<Building> getMyWishBuildings(String id);

	Integer getBuildingCount();

	int getbuilding_mainpage_2(int sNo);

	int getBuildingCount(String searchType, String searchContent);

	List<Building> getBuildingList(String searchType, String searchContent, Integer pageNum, int limit);
}

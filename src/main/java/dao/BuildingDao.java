package dao;

import java.util.List;

import logic.Building;

public interface BuildingDao {

	List<Building> resList(String hostName);

	int hostBuildCnt(String hostName);

}

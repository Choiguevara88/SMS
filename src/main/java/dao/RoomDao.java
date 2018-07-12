package dao;

import java.util.List;

import logic.Building;
import logic.Room;

public interface RoomDao {

	void insertRoom(Room room);

	Room getMyRoom(Room room);

	List<Room> getmyRoomList(Integer sNo);

	void updateRoom(Room room);

	void deleteRoom(Room room);

	Room getmyRoom_lowestprice(int i);


	int maxNum();

	Room getRoom(Integer sNo, Integer sRNo);



	

}

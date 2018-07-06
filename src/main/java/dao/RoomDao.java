package dao;

import java.util.List;

import logic.Room;

public interface RoomDao {

	void insertRoom(Room room);

	Room getMyRoom(Integer sRNo);

	List<Room> getmyRoomList(Integer sNo);

	void updateRoom(Room room);

	void deleteRoom(Integer sRNo);



	

}

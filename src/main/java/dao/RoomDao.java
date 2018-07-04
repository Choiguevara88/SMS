package dao;

import java.util.List;

import logic.Room;

public interface RoomDao {

	Room getRoom(Integer srNo);

	void insertRoom(Room room);

	List<Room> getMyroom(Integer sNo);




	

	

}

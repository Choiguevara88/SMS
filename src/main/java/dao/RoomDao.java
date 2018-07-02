package dao;

import logic.Room;

public interface RoomDao {

	Room getRoom(Integer srNo);

	void insertRoom(Room room);

	

}

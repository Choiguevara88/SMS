package dao;

import logic.Favorite;

public interface FavoriteDao {

	Favorite find(String id, Integer sNo);

	void addFavorite(String id, Integer sNo);

	void deleteFavorite(String id, Integer sNo);

}

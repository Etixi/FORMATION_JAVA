package com.infotel.eshop.dao;

import com.infotel.eshop.model.ItemCounter;

public interface ItemCounterDao {
	// recherche par valeurs du numéro de la commande
	ItemCounter findOneByItem(String item);
	// Cette méthode crée le numéro de la commande
	void create(ItemCounter count);
	//cette méthode met à jour le numéro de la commande
	void update(ItemCounter count);
}

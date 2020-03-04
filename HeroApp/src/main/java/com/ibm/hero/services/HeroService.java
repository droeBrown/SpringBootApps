package com.ibm.hero.services;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.ibm.hero.models.HeroModel;

@Service
public class HeroService {
	Hashtable<String, HeroModel> heroes = new Hashtable<String, HeroModel>();

	public HeroService() {

		HeroModel insertNewHero = new HeroModel();

		insertNewHero.setHeroId("1");
		insertNewHero.setHeroName("Batman");
		insertNewHero.setHeroHouse("DC");
		insertNewHero.setHeroAge(25);

		heroes.put("1", insertNewHero);

		insertNewHero = new HeroModel();

		insertNewHero.setHeroId("2");
		insertNewHero.setHeroName("Dare Devil");
		insertNewHero.setHeroHouse("Marvel");
		insertNewHero.setHeroAge(24);

		heroes.put("2", insertNewHero);

		insertNewHero = new HeroModel();

		insertNewHero.setHeroId("3");
		insertNewHero.setHeroName("Superman");
		insertNewHero.setHeroHouse("DC");
		insertNewHero.setHeroAge(29);

		heroes.put("3", insertNewHero);
	}

	public HeroModel getHeroById(String heroId) {
		if (heroes.containsKey(heroId)) {
			return heroes.get(heroId);
		} else {
			return null;
		}
	}

	public HeroModel getHeroByName(String heroName) {
		if (heroes.contains(heroName)) {
			return heroes.get(heroName);
		} else {
			return null;
		}
	}

	public Hashtable<String, HeroModel> getAllHeroes() {
		return heroes;
	}
}

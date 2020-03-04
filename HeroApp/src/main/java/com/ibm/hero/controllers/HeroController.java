package com.ibm.hero.controllers;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.hero.models.HeroModel;
import com.ibm.hero.services.HeroService;

@RestController
public class HeroController {

	@Autowired
	private HeroService heroService;

	@RequestMapping("/heroes")
	public Hashtable<String, HeroModel> getAll() {
		return heroService.getAllHeroes();
	}

	@RequestMapping("/hero/{heroId}")
	public HeroModel getHero(@PathVariable("heroId") String heroId) {
		return heroService.getHeroById(heroId);
	}

	@RequestMapping("/heroName/{heroName}")
	public HeroModel getHeroByName(@PathVariable("heroName") String heroName) {
		return heroService.getHeroByName(heroName);
	}
}

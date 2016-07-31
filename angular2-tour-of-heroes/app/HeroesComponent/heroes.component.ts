/**
 * Created by maciek on 03.06.16.
 */
import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {Hero} from '../Model/hero'
import {HeroService} from '../HeroService/hero.service'
import {Router} from '@angular/router-deprecated'

@Component({
    moduleId: module.id,
    selector: "my-heroes",
    styleUrls: ['./heroes.component.css'],
    templateUrl: "./heroes.component.html"
})
export class HeroesComponent implements OnInit {

    heroes:Hero[];
    selectedHero:Hero;
    addingHero = false;
    error: any;


    constructor(private router:Router, private heroService:HeroService) {
    }


    ngOnInit() {
        this.getHeroes();
    }

    getHeroes() {
        this.heroService.getHeroes().then(heroes => this.heroes = heroes);
    }

    onSelect(hero:Hero) {
        this.selectedHero = hero;
    }

    goToDetail(){
        this.router.navigate(['HeroDetail', { id: this.selectedHero.id }]);
    }

    addHero() {
        this.addingHero = true;
        this.selectedHero = null;
    }

    close(savedHero: Hero) {
        this.addingHero = false;
        if (savedHero) { this.getHeroes(); }
    }

    delete(hero: Hero, event: any) {
        event.stopPropagation();
        this.heroService
            .delete(hero)
            .then(res => {
                this.heroes = this.heroes.filter(h => h !== hero);
                if (this.selectedHero === hero) { this.selectedHero = null; }
            })
            .catch(error => this.error = error); // TODO: Display error message
    }

}
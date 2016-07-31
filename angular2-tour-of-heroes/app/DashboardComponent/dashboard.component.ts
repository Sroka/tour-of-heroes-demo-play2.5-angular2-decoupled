/**
 * Created by maciek on 03.06.16.
 */
import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router-deprecated';
import {Hero} from '../Model/hero';
import {HeroService} from '../HeroService/hero.service';

@Component({
    moduleId: module.id,
    selector: 'my-dashboard',
    styleUrls: ["./dashboard.component.css"],
    templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
    heroes:Hero[] = [];

    constructor(
        private router: Router,
        private heroService:HeroService) {
    }

    ngOnInit() {
        this.heroService.getHeroes()
            .then(heroes => this.heroes = heroes.slice(0, 4));
    }

    gotoDetail(hero: Hero) {
        let link = ['HeroDetail', { id: hero.id }];
        this.router.navigate(link);
    }
}
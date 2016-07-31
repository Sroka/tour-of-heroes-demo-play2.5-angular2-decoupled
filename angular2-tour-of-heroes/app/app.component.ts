/**
 * Created by maciek on 31.05.16.
 */
import {Component} from '@angular/core';    
import {HeroService} from './HeroService/hero.service'
import {HeroesComponent} from "./HeroesComponent/heroes.component";
import {DashboardComponent} from './DashboardComponent/dashboard.component';
import {HeroDetailComponent} from './HeroDetailComponent/hero-detail.component'
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router-deprecated';

@RouteConfig([
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: DashboardComponent,
        useAsDefault: true
    },
    {
        path: '/heroes',
        name: 'Heroes',
        component: HeroesComponent
    },
    {
        path: '/detail/:id',
        name: 'HeroDetail',
        component: HeroDetailComponent
    }
])
@Component({
    moduleId: module.id,
    selector: 'my-app',
    directives: [ROUTER_DIRECTIVES],
    providers: [
        ROUTER_PROVIDERS,
        HeroService
    ],
    template: `
        <h1>{{title}}</h1>
         <nav>
             <a [routerLink]="['Dashboard']">Dashboard</a>
             <a [routerLink]="['Heroes']">Heroes</a>
        </nav>
        <router-outlet></router-outlet>
    `,
    styleUrls: ['./app.component.css']

})
export class AppComponent {

    title:string = 'Tour of Heroes';
    selectedHero

}
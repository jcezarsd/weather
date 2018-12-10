import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import * as L from 'leaflet';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.pug',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	leafletOptions = {
		layers: [
			L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
				attribution: '&copy; OpenStreetMap contributors'
			})
		],
		zoom: 7,
		center: L.latLng([46.879966, -121.726909])
	};

	dailyHistory = {};

	constructor(private http: HttpClient) {

		http.get('history/daily/seven').subscribe(data => this.dailyHistory = data);

	}

	getTemperature(value: number) {

		return Math.round(value);

	}

}

import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import 'leaflet';
declare const L: any;

import '../assets/js/leaflet-openweathermap.js';
import '../assets/js/leaflet-providers.js';
import { Map } from 'leaflet';
@Component({
	selector: 'app-root',
	templateUrl: './app.component.pug',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	dailyHistory;
	last24hours;
	currentForecast;

	apiKey = '1b30cfa15474fbfccaef6d753e4f52b3';

	leafletOptions = {};
	map: Map;
	osm;
	owmPrecipitation;
	owmRain;
	owmTemperature;
	owmWind;
	owmSnow;

	constructor(private http: HttpClient) {

		this.loadMapOptions();
		http.get('history/daily/seven').subscribe(data => this.dailyHistory = data);
		http.get('history/hourly/twentyFour').subscribe(data => this.last24hours = data);
		http.get('/weather/current').subscribe(data => this.currentForecast = data);

	}

	getTemperature(value: number) {

		return Math.round(value);

	}

	getPrecipitation(value: number) {

		return value.toFixed(1);

	}

	loadMapOptions() {

		this.osm = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
			attribution: '&copy; OpenStreetMap contributors'
		});

		this.owmPrecipitation = L.OWM.precipitationClassic({appId: this.apiKey, opacity: 0.5});

		this.owmRain = L.OWM.rain({appId: this.apiKey, opacity: 0.5});

		this.owmTemperature = L.OWM.temperature({appId: this.apiKey, opacity: 0.5});

		this.owmWind = L.OWM.wind({appId: this.apiKey, opacity: 0.5});

		this.owmSnow = L.OWM.snow({appId: this.apiKey, opacity: 0.5});

		this.leafletOptions = {
			layers: [this.osm],
			zoom: 12,
			center: L.latLng([59.4353, 24.7556])
		};

	}

	onMapReady(map: Map) {

		this.map = map;

		const baseMaps = {
			'OpenStreetMap': this.osm
		};

		const overlayMaps = {
			'OWM - Precipitation': this.owmPrecipitation,
			'OWM - Rain': this.owmRain,
			'OWM - Temperature': this.owmTemperature,
			'OWM - Wind': this.owmWind,
			'OWM - Snow': this.owmSnow
		};

		this.map.addLayer(this.owmTemperature);

		L.control.layers(baseMaps, overlayMaps).addTo(this.map);

	}

}

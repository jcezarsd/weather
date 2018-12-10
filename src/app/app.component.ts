import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import 'leaflet';
declare const L: any;

import '../assets/js/leaflet-providers.js';
import { Map } from 'leaflet';
@Component({
	selector: 'app-root',
	templateUrl: './app.component.pug',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	dailyHistory = {};
	leafletOptions = {};

	apiKey = '1b30cfa15474fbfccaef6d753e4f52b3';

	map: Map;

	osm;
	owmPrecipitation;
	owmRain;
	owmTemperature;
	owmWind;
	owmSnow;

	mapsVariants = {
		precipitation: 'precipitation_cls',
		rain: 'rain_cls',
		wind: 'wind',
		temperature: 'temp',
		snow: 'snow'
	};

	constructor(private http: HttpClient) {

		this.loadMapOptions();
		http.get('history/daily/seven').subscribe(data => this.dailyHistory = data);

	}

	getTemperature(value: number) {

		return Math.round(value);

	}

	loadMapOptions() {

		this.osm = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
			attribution: '&copy; OpenStreetMap contributors'
		});

		this.owmPrecipitation = L.tileLayer.provider('OpenWeatherMap', {
			variant: this.mapsVariants.precipitation,
			apikey: this.apiKey
		});

		this.owmRain = L.tileLayer.provider('OpenWeatherMap', {
			variant: this.mapsVariants.rain,
			apikey: this.apiKey
		});

		this.owmTemperature = L.tileLayer.provider('OpenWeatherMap', {
			variant: this.mapsVariants.temperature,
			apikey: this.apiKey
		});

		this.owmWind = L.tileLayer.provider('OpenWeatherMap', {
			variant: this.mapsVariants.wind,
			apikey: this.apiKey
		});

		this.owmSnow = L.tileLayer.provider('OpenWeatherMap', {
			variant: this.mapsVariants.snow,
			apikey: this.apiKey
		});

		this.leafletOptions = {
			layers: [
				this.osm,
				this.owmPrecipitation,
				this.owmRain,
				this.owmTemperature,
				this.owmWind,
				this.owmSnow
			],
			zoom: 7,
			center: L.latLng([59.467958, 25.057546])
		};

	}

	onMapReady(map: Map) {

		this.map = map;

		const baseMaps = {
			'OpenStreetMap': this.osm,
			'OWM - Precipitation': this.owmPrecipitation,
			'OWM - Rain': this.owmRain,
			'OWM - Temperature': this.owmTemperature,
			'OWM - Wind': this.owmWind,
			'OWM - Snow': this.owmSnow
		};

		L.control.layers(baseMaps).addTo(this.map);

	}

}

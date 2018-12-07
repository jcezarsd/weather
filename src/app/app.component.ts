import { Component } from '@angular/core';

import 'leaflet';
declare const L: any;

@Component({
	selector: 'app-root',
	templateUrl: './app.component.pug',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

	options = {
		layers: [
			L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
				attribution: '&copy; OpenStreetMap contributors'
			})
		],
		zoom: 7,
		center: L.latLng([46.879966, -121.726909])
	};

}

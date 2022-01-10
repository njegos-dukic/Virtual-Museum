package org.unibl.etf.virtualmuseum.main;

import org.unibl.etf.virtualmuseum.entities.MuseumEntity;
import org.unibl.etf.virtualmuseum.services.MuseumService;

public class Main {

	public static void main(String[] args) {
		
		for (MuseumEntity me : MuseumService.selectAll()) {
			System.out.println(me);
		}
	}
}

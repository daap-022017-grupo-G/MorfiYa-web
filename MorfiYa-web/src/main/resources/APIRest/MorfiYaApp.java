package APIRest

import appChino.Menu
import appChino.App
import org.uqbar.xtrest.api.XTRest
import appChino.Client
import appChino.CarritoVirtual

class MorfiYaApp {
	
	static Menu p;
	static Menu p2;
	static Menu p3;
	static Menu p4;
	static Menu p5;
	static Menu p6;
	static Menu p7;
	static Menu p8;
	static Menu p9;
	static Menu p10;
	static Client c1;
	static Client c2;
	static Client c3;
	
	static App morfi;
	def static void main(String[] args) {
		p = new Menu("Bebible", "Manaos", " Si tomas demasiado te convertiras en una tortuga ninja", 20, 1, 1);
		p.setImagen("images/Manaos.jpg");
		p2 = new Menu("Comestible", "Arroz Gallo  Oro", "bajo en calorias, arroz integral sin agregados ni conservantes, Colesterol:0g", 32, 12, 2);
		p2.setImagen("images/GalloOro.jpg");
		
		p3 = new Menu("Comestible", "La Serenisima", "bajo en calorias, Menu lacteo , Colesterol:0g, Grasas Trans: 12g", 55, 8, 3);
		p3.setImagen("images/dulceDeLeche.jpg");
		
		p4 = new Menu("Comestible", "La Serenisima", "bajo en calorias, Menu lacteo light , Colesterol:0g, Queso light para los gorditos ", 45, 7, 4);
		p4.setImagen("images/quesoFinlandia.jpg");
		
		p5 = new Menu("Comestible", "Luchetti", "fideos mostacholle, Menu hecho con trigo pampeano ", 95, 2, 5);
		p5.setImagen("images/fideosLuchetti.jpg");
		
		p6 = new Menu("Bebible", "Fernet Branca", "El elixir de la vida  ", 150, 5, 6);
		p6.setImagen("images/fernetBranca.jpg");
		
		p7 = new Menu("Tonico revitalizante", "simpson e hijo", "Hará bailar tus calsones", 50, 10, 7);
		p7.setImagen("images/tonico.jpg");
		
		p8 = new Menu("Comestible", "Presto pronta", "altas polentas", 25, 20, 8);
		p8.setImagen("images/polenta.jpg");
		
		p9 = new Menu("Bebible", "Brahma", "ideal para tomar con amigos", 23, 15, 9);
		p9.setImagen("images/brahma.jpg");
		
		p10 = new Menu("Bebible", "La Serenisima", "leche entera, fuente de vitaminas", 24, 20, 10);
		p10.setImagen("images/leche.jpg"); 
		
		morfi = new App();
		c1 = new Client("Juancho899","Juancho", "Talarga","25","Junacho67@gmail.com","Av.calchaquie 1065",morfi);
		c1.password = "lalala";
		c2 = new Client("Gago05","Fernando", "Gago","32","Gagito-kpo87@gmail.com","Av.calchaquie 1055",morfi);
		c2.password = "das";
		c3 = new Client("CarlitaTKM","Carla", "Succo","19","Carlita@gmail.com","Av.calchaquie 1075",morfi);
		c3.password = "calabreza15";
		c1.carritoVirtual = new CarritoVirtual();
		c1.saldoDisponible = 100;
		c2.carritoVirtual = new CarritoVirtual();
		c2.saldoDisponible = 100;
		c3.carritoVirtual = new CarritoVirtual();
		c3.saldoDisponible = 100;
		morfi.addClient(c1);
		morfi.addClient(c2);
		morfi.addClient(c3);
		morfi.addMenu(p);
		morfi.addMenu(p2);
		morfi.addMenu(p3);
		morfi.addMenu(p4);
		morfi.addMenu(p5);
		morfi.addMenu(p6);
		morfi.addMenu(p7);
		morfi.addMenu(p8);
		morfi.addMenu(p9);
		morfi.addMenu(p10);
		var morfiya = new MorfiYaAPIRest(morfi);
		XTRest.startInstance(morfiya, 9032)
	}
}

package com.infotel.eshop.memory;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.BookDetails;
import com.infotel.eshop.model.Category;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Merchant;
import com.infotel.eshop.model.User;




public class Database {
	
	static final Map<String, User> USERS_TABLE;
	static {
	USERS_TABLE = new HashMap<>(); //User[] 
		
		USERS_TABLE.put("amelie@yahoo.fr", new Customer("amelie@yahoo.fr", "cailloux", "Amélie", "Poulain", "miss", "0678244652"));
		USERS_TABLE.put("emelie@mailo.fr", new Customer("emelie@mailo.fr", "secret", "Emiie", "Lampion", "mister","0688554477"));
		USERS_TABLE.put("francois@free.fr", new Merchant("francois@free.fr", "jaune", "François", "Pignon"));
};

//	static final User[] USERS_TABLE = new User[] {
//			new Customer("amelie@yahoo.fr", "cailloux", "Amélie", "Poulain", "miss", "0678244652"),
//			new Customer("emelie@mailo.fr", "secret", "Emiie", "Lampion", "mister","0688554477"),
//			new Merchant("francois@free.fr", "jaune", "François", "Person")
//			
//	};

	static final Category[] CATEGORIES_TABLE = new Category[] {
	        new Category(21, "Aventure"),
	        new Category(29, "Fantatstique"),
	        new Category(36, "Manga"),
	        
	};

	static final Book[] BOOKS_TABLE = new Book[] {
			
			new Book(1, "Tintin au Tibet", 10.5),
			new Book(2, "Astérix en Corse", 11.2),
			new Book(3, "Adèle et la bête", 11.3),
			new Book(4, "Tintin au Congo", 10.8),
			new Book(5, "Astérix le Gaulois", 10.2),
			new Book(6, "Tintin en Amérique", 10.4),
			new Book(7, "Le Grimoire des Dieux", 10.8),		
	};
	
private static LocalDate releaseDate(String date) {
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate localdate = LocalDate.parse(date, sdf);
	return localdate ;
	
}

static {
	BOOKS_TABLE[0].setDetails(new BookDetails(1, "Un avion de ligne à bord duquel le jeune Chinois Tchang se rendait en Europe s'est écrasé dans l'Himalaya. Tintin au Tibet (1960), pure histoire d'amitié, sans le moindre méchant, décrit la recherche désespérée à laquelle Tintin se livre pour retrouver son ami. Ce récit pathétique, qui rompt avec le ton extraverti des épisodes précédents, démontre que la fidélité et l'espoir sont capables de vaincre tous les obstacles, et que les préjugés - en l'occurrence, à l'égard de l'\"abominable homme des neiges\" - sont bien souvent le fruit de l'ignorance.", 
			"978-2203001190", releaseDate("1993-05-04")));
	BOOKS_TABLE[1].setDetails(new BookDetails(2, "Pour célébrer l’anniversaire de la bataille de Gergovie, les Gaulois accueillent leurs nombreux amis et s’offrent en apéritif les malheureux légionnaires du camp fortifié de Babaorum. Là, ils libèrent Ocatarinetabelatchitchix, chef de clan corse prisonnier des Romains. Ce dernier, plus fier encore qu’un Gaulois, prétend alors que les Corses sont le « cauchemar des romains ». Curieux de découvrir comment les Corses pratiquent le latin au quotidien, Astérix et Obélix décident de s’embarquer pour l’Ile de beauté, histoire de prouver qu’ils sont les seuls à donner d’authentiques sueurs froides aux Romains. Et ce sont encore ces derniers qui, malgré eux, vont arbitrer les débats…", 
			"978-2012101524", releaseDate("2005-03-16")));
	BOOKS_TABLE[2].setDetails(new BookDetails(3, "Dans ce premier épisode des Aventures Extraordinaires d’Adèle Blanc-Sec , nous faisons connaissance avec la célèbre héroïne de Tardi. Dotée d’une personnalité hors du commun, Adèle nous entraîne dans un univers mystérieux (dans lequel Paris occupe une place de choix), peuplé de monstres et d’êtres étranges, qui fera le succès de cette série.", 
	"978-2203305014", releaseDate("1993-10-19")));
	BOOKS_TABLE[3].setDetails(new BookDetails(4, "Les Aventures de Tintin, reporter du Petit Vingtième au Congo (1931) est le reflet d'une époque coloniale et paternaliste. Pour ces nouvelles aventures, Hergé improvise encore le récit, mais plus pour longtemps. À peine revient-il d'URSS que Tintin se rend en Afrique. Devenu sorcier au royaume de Babaoro'm, Tintin déjouera les pièges d'une bande de gangsters qui souhaitaient contrôler la production de diamant au Congo. L'Afrique, représentée de manière naïve, reflète l'esprit paternaliste d'une Belgique colonialiste au début des années 1930.", 
	"978-2203001015", releaseDate("1993-05-04")));
	BOOKS_TABLE[4].setDetails(new BookDetails(5, "Dans le camp fortifié romain Petibonum, on se pose des questions : comment les Irréductibles Gaulois du village d’Astérix font-ils pour ridiculiser encore et toujours la puissance romaine ? Décidé à percer à jour le mystère de la force surhumaine de nos héros, le centurion Caius Bonus envoie un espion déguisé en Gaulois. C’est Caligula Minus qui s’y colle, et découvre bien vite l’existence de la potion magique préparée par Panoramix. Sans attendre, le centurion Caius Bonus fait enlever le druide pour s’emparer de la recette du fameux breuvage histoire, qui sait, de devenir César à la place de Jules ! Une première occasion pour Astérix de démontrer son courage et ses talents de stratège.", 
	"978-2012101333", releaseDate("2004-06-16")));
	BOOKS_TABLE[5].setDetails(new BookDetails(6, "Dans Tintin en Amérique (1932), le héros confirme sa vocation de redresseur de torts, en s'opposant au mafioso Al Capone, aux gangsters de Chicago et aux fripouilles de tout acabit. Déjà, Hergé témoigne d'une vision généreuse du monde, stigmatisant par exemple des Blancs pour leurs comportements envers les Indiens Peaux-Rouges. La renommée de Tintin s'étend au-delà de l'Atlantique. Si bien que lorsqu'il arrive à Chicago, en pleine prohibition, tous les bandits et malfaiteurs associés lui préparent une réception des moins confortables. Tintin devra user de tout son courage et de toute son intelligence pour survivre !", 
	"978-2203001022", releaseDate("1993-05-04")));
	BOOKS_TABLE[6].setDetails(new BookDetails(7, "Sur les terres d'Akbar, plusieurs meurtres d'enfants de Princes Sorciers entachent la \"relative\" quiétude de la région. Détail troublant, chaque victime porte un signe sur son corps : la mystérieuse secte de « l'Ordre des signes » serait-elle responsable de ces assassinats ? De son côté, depuis la mort de Javin, Bragon s'est endurci au contact de Frange qui lui a appris le maniement des armes. Sur les conseils de ce dernier, Bragon décide de partir dans la cité de Vaguamare afin de participer aux combats qui lui permettront de s'aguerrir plus encore. Et, peut-être, d'attirer l'attention du redoutable Rige même si certains affirment que ce personnage n'a jamais existé et qu'il ne serait qu'une légende. C'est aussi pour lui une façon de s'éloigner de la troublante princesse Mara dont il est secrètement amoureux. Mais comment lui, un roturier, pourrait-il séduire une dame de son rang ? Inquiet des événements, le père de Mara pense que « l'Ordre des signes » prépare une conspiration et décide d'envoyer sa fille retrouver un grimoire dans lequel les Dieux ont inscrit leurs pouvoirs magiques qui ont neutralisé le terrible dieu Ramor. Persuadé que ce dieu pourrait se réveiller, il est convaincu que seul le grimoire pourrait les sauver. Seul hic : ce précieux document se situe quelque part au coeur du Matbâta, une forteresse naturelle infranchissable et dangereuse...", 
	"978-2205056334", releaseDate("2007-11-30")));
	};
	


	



/*
static final Book[] BOOKS_TABLE = new Book[] {
        new Book(1, "Tintin au Tibet", 10.5, CATEGORIES_TABLE[4]),
        new Book(2, "Astérix en Corse", 11.2, CATEGORIES_TABLE[4]),
        new Book(3, "Astérix et Obélix : Mission Cléopâtre", 10, CATEGORIES_TABLE[4]),
        new Book(4, "Les aventures de Tintin : Objectif Lune", 9.5, CATEGORIES_TABLE[4]),
        new Book(5, "Harry Potter et la chambre des secrets", 9, CATEGORIES_TABLE[0]),
        new Book(6, "Harry Potter et la coupe de feu", 9, CATEGORIES_TABLE[0]),
        new Book(7, "Les Misérables", 8, CATEGORIES_TABLE[3]),
        new Book(8, "Le rouge et le noir", 8, CATEGORIES_TABLE[3])

};
*/


	static {
		for (int i = 0; i < BOOKS_TABLE.length; i++) {
			Book book = BOOKS_TABLE[i];
			switch (i) {
			case 0, 1, 3, 4, 5 -> book.setCategory(CATEGORIES_TABLE[0]);
			case 2, 6  -> book.setCategory(CATEGORIES_TABLE[1]);
			
			}
		}
	};
	
	
	// Conversion date
	/*
    private static Date convertDate(String day, String month, String year) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, Integer.parseInt(month));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        date = cal.getTime();
        return date;
       */
    }


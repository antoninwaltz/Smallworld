package view;

public enum EventType {
	
	//Evenement du menu
	NEWGAME,
	LOADGAME,
	NEWPLAYER,
	REMOVEPLAYER,
	
	//Evenement en jeu
	SELECTFOLKPOWER,
	REDEPLOY,
	ATTACKCASE,
	FOLKTODECLINE,
	CLICKPOLY,
	NEXTPLAYER,
	
	//Creation d'un evenement non défini
	UNDEFINE, 
	SAVE, 
	LOAD
}

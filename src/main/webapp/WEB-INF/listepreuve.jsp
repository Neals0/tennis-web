<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="starter-template.css">
    <title>Hello, world!</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
  <a class="navbar-brand" href="#">Menu</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ajouter</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="/AppJoueur/ajouterjoueur">Ajouter un joueur</a>
          <a class="dropdown-item" href="/AppJoueur/ajoutertournoi">Ajouter un tournoi</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Liste</a>
        <div class="dropdown-menu" aria-labelledby="dropdown02">
          <a class="dropdown-item" href="/AppJoueur/listjoueur">Liste joueurs</a>
          <a class="dropdown-item" href="/AppJoueur/listtournoi">Liste tournois</a>
          <a class="dropdown-item" href="/AppJoueur/listmatch">Liste matchs</a>
          <a class="dropdown-item" href="/AppJoueur/listepreuve">Liste épreuves</a>
        </div>
      </li>
	  
	  
	  	<li class="nav-item">
        <form class="form-inline my-2 my-lg-0" method="post" action="listepreuve">
        	<button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="deconnexion">Deconnexion</button>
        	 </form>
    	</li>
      
    </ul>
        
        <form class="form-inline my-2 my-lg-0" action="listepreuve" method="post" >
			<input class="form-control mr-sm-2" type="text" placeholder="Rechercher" aria-label="Search" name="txtSearch">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="rechercher">Search</button>
			
		</form>
	</div>
  </nav>
  <main role="main" class="container">

  <div class="starter-template">
  <p class="lead">Bienvenue <b><c:out value="${ connectedUser.login }"></c:out></b></p>
    <h1>Liste des épreuves</h1>
    <p class="lead">L'onglet "Epreuve" dont l'objectif est de permettre l'affichage des noms et prénoms des joueurs ayant participé à l'épreuve Y durant l'année X.</p>
  </div>

</main><!-- /.container -->
<div class="container">

<div style="    padding: 1.5rem;    margin-right: 0;    margin-left: 0;    border-width: .2rem;">
<!-- 	<a class="btn btn-primary" href="/AppJoueur/ajouterepreuve" role="button">Ajouter épreuve</a> -->
</div>
<table class="table">
  <thead>
    <tr>
      <th scope="col" style="width:10%">#</th>
      <th scope="col" style="width:25%">Joueur</th>
      <th scope="col" style="width:25%">Type d'épreuve</th>
      <th scope="col" style="width:20%">Année</th>
      <th scope="col" style="width:20%">Tournoi</th>
      <th scope="col" style="width:20%">Statut</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="epreuve" items = "${ epreuves }">
    	<tr>
      		<th scope="row">${ epreuve.id }</th>
      		<td><c:out value = "${ epreuve.joueur }" /></td>
      		<td><c:out value = "${ epreuve.type_epreuve }" /></td>
      		<td><c:out value = "${ epreuve.annee }" /></td>
      		<td><c:out value = "${ epreuve.nom_tournoi }" /></td>
      		<td><c:out value = "${ epreuve.statut }" /></td>
      			  		
    	</tr>
    </c:forEach>
  </tbody>
</table>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
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
        <form class="form-inline my-2 my-lg-0" action="listjoueur" method="post">
         <button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="deconnexion">Deconnexion</button>
         </form>
    	</li>
      
    </ul>
    
    
    <form class="form-inline my-2 my-lg-0" action="listjoueur" method="post" >
      <input class="form-control mr-sm-2" type="text" name="txtSearch" placeholder="Search" aria-label="Search">
      
      <button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="rechercher">Search</button>
      
    </form>
    
    
  </div>
</nav>


<main role="main" class="container">

  <div class="starter-template">
  <p class="lead">Bienvenue <b><c:out value="${ connectedUser.login }"></c:out></b></p>
    <h1>Liste des joueurs</h1>
    <p class="lead">L'onglet "Joueur" permet d'ajouter, de modifier et de supprimer des joueurs. Il dispose également d'un champ de recherche pour filtrer les joueurs par nom ou prénom, ainsi que par genre. L'onglet "Tournois" propose des fonctionnalités similaires pour ajouter, modifier et supprimer des tournois. Il dispose également d'un champ de recherche pour filtrer les tournois par nom ou code.</p>
  </div>

</main><!-- /.container -->
<div class="container">

<div style="    padding: 1.5rem;    margin-right: 0;    margin-left: 0;    border-width: .2rem;">
	<c:if test="${connectedUser.profil == 1}">
		<a type="button" class="btn btn-primary" href="/AppJoueur/ajouterjoueur">Ajouter joueur</a>
	</c:if>
</div>
<table class="table">
  <thead>
    <tr>
      <th scope="col" style="width:20%">#</th>
      <th scope="col" style="width:20%">Nom</th>
      <th scope="col" style="width:20%">Prenom</th>
      <th scope="col" style="width:20%">Sexe</th>
      <th scope="col" style="width:20%"></th>
    </tr>
  </thead>
  <tbody>
  
  
  <c:if test="${joueurs.size() == 0}" >
  	<tr>
  		<td colspan="5" style="text-align:center">
  			il n'y a aucune occurence trouvée
  		</td>
  	</tr>
  </c:if>
  
  
  <c:forEach var="joueur" items="${ joueurs }">
    <tr>
      <th scope="row">${ joueur.id }</th>
      <td><c:out value="${ joueur.nom }" /></td>
      <td><c:out value="${ joueur.prenom }" /></td>
      <td><c:out value="${ joueur.sexe }" /></td>
	  <td>
			<c:if test="${connectedUser.profil == 1}">
				<a type="button" class="btn btn-outline-primary" href="modifierjoueur?id=${joueur.id}" role="button">Modifier</a>
				<a type="button" class="btn btn-outline-warning" href="supprimerjoueur?id=${joueur.id}" role="button">Supprimer</a>
      </c:if>
	  </td>
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
</html>



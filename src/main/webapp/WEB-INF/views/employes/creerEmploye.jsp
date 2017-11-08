<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<%@page import="dev.paie.entite.RemunerationEmploye"%>
<%@page import="dev.paie.entite.Grade"%>
<%@page import="dev.paie.entite.Entreprise"%>
<%@page import="dev.paie.entite.ProfilRemuneration"%>
<!doctype html>
<html lang="en">
<head>
<title>Paie</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous">
</head>
<body>


	<div class="container-fluid">


		<div class="row">
			<div class="col-12">
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<a class="navbar-brand disable" href="#">Gestionnaire de paie</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active"><a class="nav-link"
								href="<c:url value='/mvc/employes/lister'/>">Employés <span
									class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/mvc/bulletins/lister'/>">Bulletins</a></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
		<div class="row">
			<div class="col-2 offset-1">
				<a href="<c:url value='/mvc/employes/lister'/>"><img
					src="http://pixsector.com/cache/a8009c95/av8a49a4f81c3318dc69d.png"
					alt="Flèche retour" class="rounded" width="100px" height="100px"></a>
			</div>
			<div class="col-8 offset-1">
				<h1>Ajouter un employé</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<form method="POST" action="<c:url value="/mvc/employes/creer"/>">

			<div class="form-group">
				<div class="row">
					<div class="col-4">
						<label class="text-right" for="Matricule">Matricule</label>
					</div>
					<div class="col-8">
						<input type="text" class="form-control" id="Matricule"
							name="Matricule" placeholder="ex:M00">
					</div>
				</div>


				<div class="row">
					<div class="col-4">
						<label class="text-right">Entreprise</label>
					</div>
					<div class="col-8">
						<select class="form-control" name="Entreprise">
							<c:forEach items="${entreprises}" var="entreprise">
								<option>${entreprise.denomination }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="col-4">
						<label class="text-right">Grade</label>
					</div>
					<div class="col-8">
						<select class="form-control" name="Grade">
							<c:forEach items="${grades}" var="grade">
								<option value="${grade.code}">${grade.code}-
									${grade.salaireAnnuel} &#8364/an</option>
							</c:forEach>
						</select>

					</div>
				</div>
				<div class="row">
					<div class="col-4">
						<label class="text-right">Profil</label>
					</div>
					<div class="col-8">
						<select class="form-control" name="Profil">
							<c:forEach items="${profils}" var="profil">
								<option>${profil.code }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="offset-9">
						<button type="submit" class="btn btn-primary">Ajouter</button>
						<sec:csrfInput />
					</div>
				</div>
			</div>
		</form>



	</div>





	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
		integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
		integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
		crossorigin="anonymous"></script>
</body>
</html>
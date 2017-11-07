<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
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
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/mvc/employes/lister'/>">Employés </a></li>
							<li class="nav-item active"><a class="nav-link"
								href="<c:url value='/mvc/bulletins/lister'/>">Bulletins<span
									class="sr-only">(current)</span></a></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
		<div class="row">
			<div class="col-8 offset-4">
				<h1>Lister les bulletins</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<a href="<c:url value="/mvc/bulletins/creer"/>"> <input
				type="button" value="Ajouter" class="btn btn-primary">
			</a>
		</div>
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Date et heure de création</th>
						<th scope="col">Periode</th>
						<th scope="col">Matricule</th>
						<th scope="col">Salaire Brut</th>
						<th scope="col">Net Imposable</th>
						<th scope="col">Net à payer</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bulletins}" var="bulletin">
						<tr>
							<td>${bulletin.dateCreation}</td>
							<td>${bulletin.periode.libelle }</td>
							<td>${bulletin.remunerationEmploye.matricule}</td>
							<c:forEach items="${resultats}" var="resultat">
								<c:if test="${resultat.key==bulletin}">

									<td>${resultat.value.salaireBrut}</td>
									<td>${resultat.value.netImposable}</td>
									<td>${resultat.value.netAPayer}</td>

								</c:if>
							</c:forEach>
							<td><a
								href="<c:url value="/mvc/bulletins/visualiser/${bulletin.id}"/>">Visualiser</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
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
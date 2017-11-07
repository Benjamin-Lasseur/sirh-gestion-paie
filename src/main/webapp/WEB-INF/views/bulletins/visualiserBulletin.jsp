<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<div class="col-2 offset-1">
				<a href="<c:url value='/mvc/bulletins/lister'/>"><img
					src="http://pixsector.com/cache/a8009c95/av8a49a4f81c3318dc69d.png"
					alt="Flèche retour" class="rounded" width="100px" height="100px"></a>
			</div>
			<div class="col-8 offset-1">
				<h1>Bulletin de salaire</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-3">
				<div class="row">
					<h2>Entreprise</h2>
				</div>
				<div class="row">
					${bulletin.remunerationEmploye.entreprise.denomination }</div>
				<div class="row">Siret :
					${bulletin.remunerationEmploye.entreprise.siret }</div>
			</div>
			<div class="col-3 offset-6">
				<div class="row">
					<h2>Periode</h2>
				</div>
				<div class="row">Du ${bulletin.periode.dateDebut} au
					${bulletin.periode.dateFin}</div>
				<div class="row">Matricule :
					${bulletin.remunerationEmploye.matricule}</div>
			</div>
		</div>
		<div class="row">
			<h2>Salaire</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Rubriques</th>
						<th>Base</th>
						<th>Taux salarial</th>
						<th>Montant Salarial</th>
						<th>Taux Patronal</th>
						<th>Cot. patronales</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="col">Salaire de base</th>
						<th>${bulletin.remunerationEmploye.grade.nbHeuresBase }</th>
						<th>${bulletin.remunerationEmploye.grade.tauxBase }</th>
						<th>${resultat.salaireDeBase }</th>
						<th></th>
						<th></th>
					</tr>
					<tr>
						<th scope="col">Prime excep.</th>
						<th></th>
						<th></th>
						<th>${bulletin.primeExceptionnelle}</th>
						<th></th>
						<th></th>
					</tr>
					<tr>
						<th scope="col"></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					<tr>
						<th scope="col">Salaire brut</th>
						<th></th>
						<th></th>
						<th>${resultat.salaireBrut }</th>
						<th></th>
						<th></th>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row">
			<h2>Cotisations</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Rubriques</th>
						<th>Base</th>
						<th>Taux salarial</th>
						<th>Montant Salarial</th>
						<th>Taux Patronal</th>
						<th>Cot. patronales</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cotisationsNonImp}" var="cotisation">
						<tr>
							<th scope="col">${cotisation.code}-${cotisation.libelle}</th>
							<th>${resultat.salaireBrut }</th>
							<th>${cotisation.tauxSalarial }</th>
							<th><c:if
									test="${cotisation.tauxSalarial*resultat.salaireBrut!=0}">
									<fmt:formatNumber
										value="${cotisation.tauxSalarial*resultat.salaireBrut}"
										type="NUMBER" maxIntegerDigits="9" minIntegerDigits="1"
										maxFractionDigits="2" minFractionDigits="2"></fmt:formatNumber>
								</c:if></th>
							<th>${cotisation.tauxPatronal }</th>
							<th><c:if
									test="${cotisation.tauxPatronal*resultat.salaireBrut!=0}">
									<fmt:formatNumber
										value="${cotisation.tauxPatronal*resultat.salaireBrut}"
										type="NUMBER" maxIntegerDigits="9" minIntegerDigits="1"
										maxFractionDigits="2" minFractionDigits="2"></fmt:formatNumber>
								</c:if></th>
						</tr>
					</c:forEach>
					<tr>
						<th scope="col">Total retenue</th>
						<th></th>
						<th></th>
						<th>${resultat.totalRetenueSalarial}</th>
						<th></th>
						<th>${resultat.totalCotisationsPatronales}</th>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row">
			<h2>Net imposable : ${resultat.netImposable}</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Rubriques</th>
						<th>Base</th>
						<th>Taux salarial</th>
						<th>Montant Salarial</th>
						<th>Taux Patronal</th>
						<th>Cot. patronales</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cotisationsImp}" var="cotisation">
						<tr>
							<th scope="col">${cotisation.code}-${cotisation.libelle}</th>
							<th>${resultat.salaireBrut }</th>
							<th>${cotisation.tauxSalarial }</th>
							<th><c:if
									test="${cotisation.tauxSalarial*resultat.salaireBrut!=0}">
									<fmt:formatNumber
										value="${cotisation.tauxSalarial*resultat.salaireBrut}"
										type="NUMBER" maxIntegerDigits="9" minIntegerDigits="1"
										maxFractionDigits="2" minFractionDigits="2"></fmt:formatNumber>
								</c:if></th>
							<th>${cotisation.tauxPatronal }</th>
							<th><c:if
									test="${cotisation.tauxPatronal*resultat.salaireBrut!=0}">
									<fmt:formatNumber
										value="${cotisation.tauxPatronal*resultat.salaireBrut}"
										type="NUMBER" maxIntegerDigits="9" minIntegerDigits="1"
										maxFractionDigits="2" minFractionDigits="2"></fmt:formatNumber>
								</c:if></th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-5 offset-7">
				<div class="row">
					<h2>Net à payer : ${resultat.netAPayer}</h2>
				</div>
			</div>
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
async function criar(e) {
	e.preventDefault();

	var formProduto =
		JSON.stringify($("#formVeiculo").serializeArray().reduce((a, x) => ({ ...a, [x.name]: x.value }), {}));

	await $.ajax({
		type: "POST",
		url: "http://localhost:8080/veiculo",
		data: formProduto,
		headers: {
			"Authorization": "Basic U3RlbGxhbnRpczpTdGVsbGFudGlz"
		},
		success: function() {
			alert("Veículo cadastrado");
			location.reload();
		},
		error: function() {
			alert("Ocorreu um erro ao registrar o veículo");
		},
		dataType: "json",
		contentType: "application/json"
	});
}

async function atualizar(e) {
	e.preventDefault();

	var formProduto =
		JSON.stringify($("#formVeiculo").serializeArray().reduce((a, x) => ({ ...a, [x.name]: x.value }), {}));

	await $.ajax({
		type: "PUT",
		url: "http://localhost:8080/veiculo",
		data: formProduto,
		headers: {
			"Authorization": "Basic U3RlbGxhbnRpczpTdGVsbGFudGlz"
		},
		success: function() {
			alert("Veículo atualizado");
			location.href = '/veiculo/lista';
		},
		error: function() {
			alert("Ocorreu um erro ao atualizar o veículo");
		},
		dataType: "json",
		contentType: "application/json"
	});
}

async function lista() {
	await $.ajax({
		type: "GET",
		url: "http://localhost:8080/veiculo/lista",
		headers: {
			"Authorization": "Basic U3RlbGxhbnRpczpTdGVsbGFudGlz"
		},
		success: function(data) {
			$.each(data, function(i, veiculo) {
				$(".dados-veiculos-js").append(
					"<tr><td>" + veiculo.id + "</td>" +
					"<td>" + veiculo.marca + "</td>" +
					"<td>" + veiculo.modelo + "</td>" +
					"<td>" + veiculo.placaVeiculo + "</td>" +
					"<td>" + veiculo.quilometragemPercorrida + "</td>" +
					"<td>" + veiculo.ambiente + "</td>" +
					"<td><div class='d-flex justify-content-around'><form action='/veiculo/alterar/" + veiculo.id + "' method='get'><button class='fas fa-edit' type='submit' title='Editar'></button></form><form><button class='fas fa-trash' type='submit' title='Excluir' onclick='excluir(event, " + `"` + veiculo.id + `"` + ")'></button></form></div></td></tr>"
				);
			});
		},
		error: function() {
			alert("Ocorreu um erro ao resgatar a lista de veículos");
		},
	});
}

async function excluir(e, id) {
	e.preventDefault();

	await $.ajax({
		type: "DELETE",
		url: "http://localhost:8080/veiculo/" + id,
		headers: {
			"Authorization": "Basic U3RlbGxhbnRpczpTdGVsbGFudGlz"
		},
		success: function() {
			alert("Veículo excluído");
			location.reload();
		},
		error: function() {
			alert("Ocorreu um erro ao excluir o veículo");
		},
	});
}


function handleCompleteAdd(xhr, status, args) {
	if (args.validationFailed) {
		// Validar Formulario
		jQuery('#modPass').effect("shake", {
			times : 4
		}, 10);
	} else {
		// Si es verdadero cerrar dialog
		if (args.validate) {
			confirmation.show();
		}
	}
}

function handleCompleteAccountMod(xhr, status, args) {
	if (args.validationFailed) {
		// Validar Formulario
		jQuery('#modAccount').effect("shake", {
			times : 4
		}, 10);
	} else {
		// Si es verdadero cerrar dialog
		if (args.validate) {
			confirmationAccount.show();
		}
	}
}

function handleLoginRequestAdd(xhr, status, args) {
	if (args.validationFailed || !args.lSuccessfull) {
		jQuery('#dialogAdd').effect("shake", {
			times : 4
		}, 10);
	} else {
		add.show();
	}
}

function handleSelectedRow(xhr, status, args) {
	if (args.validationFailed || !args.lSelected) {

	} else {
		update.show();
	}
}

function click() {
	if (event.button == 2) {
		alert(' Tu modificas este alerta');
	}
}
//document.onmousedown = click;


function handleDialogAdd(xhr, status, args) {
	if (args.validationFailed) {
		jQuery('#dialogAdd').effect("shake", {
			times : 4
		}, 10);
	} else {
		confirmation.show();
	}
}

function handleAddRow(xhr, status, args) {
	if (args.lSuccessfull) {
		add.hide();
	}
}
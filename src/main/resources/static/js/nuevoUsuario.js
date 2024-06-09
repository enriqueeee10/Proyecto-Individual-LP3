
	document.getElementById("btnAceptar").addEventListener("click", async (evento) => {
		let usuario = {
			"dni": document.getElementById("usuario_dni").value,
			"nombre": document.getElementById("usuario_nombre").value,
			"apellido": document.getElementById("usuario_apellido").value,
			"correo": document.getElementById("usuario_email").value,
			"telefono": document.getElementById("usuario_telefono").value,
			"direccion": document.getElementById("usuario_direccion").value,
			"roles": 1,
			"pass": document.getElementById("usuario_clave_1").value,
		};

		if (usuario.pass !== document.getElementById("usuario_clave_2").value) {
			alert("Las contraseñas no coinciden");
			return;
		}

		try {
			const responseRegistro = await fetch("http://localhost:9999/usuario/nuevousuario", {
				method: "POST",
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(usuario),
			});

			if (!responseRegistro.ok) {
				alert("No se pudo registrar el nuevo usuario");
			} else {
				alert("Usuario registrado exitosamente");
				window.location.href = "/usuario";
			}
		} catch (error) {
			alert("No se pudo registrar el nuevo usuario");
			console.error('Error durante el registro de nuevo usuario:', error);
		}
	});

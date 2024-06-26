document.getElementById("btnAceptar").addEventListener("click", async (evento) => {
    evento.preventDefault();  // Evitar el envío del formulario

    // Obtener valores de los campos de entrada
    const dni = document.getElementById("cliente_dni").value;
    const nombre = document.getElementById("cliente_nombre").value;
    const apellido = document.getElementById("cliente_apellido").value;
    const correo = document.getElementById("cliente_correo").value;
    const telefono = document.getElementById("cliente_telefono").value;
    const direccion = document.getElementById("cliente_direccion").value;

    // Crear el objeto cliente
    const cliente = {
        dni,
        nombre,
        apellido,
        correo,
        telefono,
        direccion,
    };

    // Enviar la solicitud POST al servidor
    try {
        const responseRegistro = await fetch("/cliente/nuevocliente", {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cliente),
        });

        // Manejar la respuesta del servidor
        if (!responseRegistro.ok) {
            const errorResponse = await responseRegistro.json();
            alert(`No se pudo registrar el nuevo cliente: ${errorResponse.message || responseRegistro.statusText}`);
        } else {
            alert("Cliente registrado exitosamente");
            window.location.href = "/cliente";
        }
    } catch (error) {
        alert("No se pudo registrar el nuevo cliente");
        console.error('Error durante el registro de nuevo cliente:', error);
    }
});

document.addEventListener("DOMContentLoaded", async function() {
    const urlParams = new URLSearchParams(window.location.search);
    const idUsuario = urlParams.get('idUsuario');

    try {
        const response = await fetch(`http://localhost:9999/usuario/verusuario/${idUsuario}`);
        if (!response.ok) {
            const responseText = await response.text();
            console.error("Error en la respuesta del servidor:", responseText);
            throw new Error("No se pudo obtener la información del usuario");
        }

        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            const cliente = await response.json();

            document.getElementById("usuario_dni").value = cliente.dni;
            document.getElementById("usuario_nombre").value = cliente.nombre;
            document.getElementById("usuario_apellido").value = cliente.apellido;
            document.getElementById("usuario_email").value = cliente.correo;
            document.getElementById("usuario_telefono").value = cliente.telefono;
            document.getElementById("usuario_direccion").value = cliente.direccion;
        } else {
            const responseText = await response.text();
            console.error("Respuesta no es JSON:", responseText);
            throw new Error("Respuesta no es JSON");
        }
    } catch (error) {
        console.error('Error al obtener la información del usuario:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Error al obtener la información del usuario',
        });
    }
});

document.getElementById("btnActualizar").addEventListener("click", async function(event) {
    event.preventDefault();

    const urlParams = new URLSearchParams(window.location.search);
    const idUsuario = urlParams.get('idUsuario');  // Asegúrate de que el parámetro es idUsuario
    const dni = document.getElementById("usuario_dni").value;
    const nombre = document.getElementById("usuario_nombre").value;
    const apellido = document.getElementById("usuario_apellido").value;
    const telefono = document.getElementById("usuario_telefono").value;
    const correo = document.getElementById("usuario_email").value;
    const direccion = document.getElementById("usuario_direccion").value;

    const request = {
        roles: 1,
        dni,
        nombre,
        apellido,
        telefono,
        correo,
        direccion
    };

    try {
        const response = await fetch(`http://localhost:9999/usuario/actualizarusuario/${idUsuario}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(request),
        });

        console.log('Respuesta del servidor:', response);

        if (!response.ok) {
            const responseText = await response.text();
            console.error("Error en la respuesta del servidor:", responseText);
            throw new Error("No se pudo actualizar el usuario");
        }

        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            const responseData = await response.json();
            console.log('Respuesta de actualización:', responseData);
        } else {
            const responseText = await response.text();
            console.log('Respuesta de actualización no JSON:', responseText);
        }

        Swal.fire({
            icon: 'success',
            title: 'Éxito',
            text: '¡Usuario actualizado correctamente!',
        }).then(() => {
            window.location.href = "/listarusuario";
        });

    } catch (error) {
        console.error('Error al actualizar el usuario:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Error al actualizar el usuario',
        });
    }
});

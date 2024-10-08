// Función para actualizar la cancha al enviar el formulario
document.getElementById("canchaForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const urlParams = new URLSearchParams(window.location.search);
    const idCancha = urlParams.get('idCancha');
    console.log('ID de la cancha a actualizar:', idCancha);
    if (!idCancha) {
        console.error('El ID de la cancha es nulo o no se encontró en la URL');
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'ID de la cancha no encontrado en la URL',
        });
        return;
    }

    const codigo = document.getElementById("cancha_codigo").value;
    const nombre = document.getElementById("cancha_nombre").value;
    const direccion = document.getElementById("cancha_direccion").value;
    const precio = document.getElementById("cancha_precio").value;

    const request = {
        codigo,
        nombre,
        direccion,
        precio
    };

    try {
        const response = await fetch(`http://localhost:9999/cancha/actualizarcancha/${idCancha}`, {
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
            throw new Error("No se pudo actualizar la cancha");
        }

        Swal.fire({
            icon: 'success',
            title: 'Éxito',
            text: '¡Cancha actualizada correctamente!',
        }).then(() => {
            window.location.href = "/listarcanchas";
        });

    } catch (error) {
        console.error('Error al actualizar la cancha:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Error al actualizar la cancha',
        });
    }
});

// Función para obtener los datos de la cancha al cargar la página
document.addEventListener("DOMContentLoaded", async function() {
    const urlParams = new URLSearchParams(window.location.search);
    const idCancha = urlParams.get('idCancha');
    console.log('ID de la cancha a obtener:', idCancha);

    if (!idCancha) {
        console.error('El ID de la cancha es nulo o no se encontró en la URL');
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'ID de la cancha no encontrado en la URL',
        });
        return;
    }

    try {
        const response = await fetch(`http://localhost:9999/cancha/vercancha/${idCancha}`);
        if (!response.ok) {
            const responseText = await response.text();
            console.error("Error en la respuesta del servidor:", responseText);
            throw new Error("No se pudo obtener la información de la cancha");
        }

        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            const cancha = await response.json();

            document.getElementById("cancha_codigo").value = cancha.codigo;
            document.getElementById("cancha_nombre").value = cancha.nombre;
            document.getElementById("cancha_direccion").value = cancha.direccion;
            document.getElementById("cancha_precio").value = cancha.precio;
        } else {
            const responseText = await response.text();
            console.error("Respuesta no es JSON:", responseText);
            throw new Error("Respuesta no es JSON");
        }
    } catch (error) {
        console.error('Error al obtener la información de la cancha:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Error al obtener la información de la cancha',
        });
    }
});

// Función para actualizar la cancha al hacer clic en el botón "Actualizar"
document.getElementById("btnActualizar").addEventListener("click", async function(event) {
    event.preventDefault();

    const urlParams = new URLSearchParams(window.location.search);
    const idCancha = urlParams.get('idCancha');
    console.log('ID de la cancha a actualizar:', idCancha);
    if (!idCancha) {
        console.error('El ID de la cancha es nulo o no se encontró en la URL');
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'ID de la cancha no encontrado en la URL',
        });
        return;
    }

    const codigo = document.getElementById("cancha_codigo").value;
    const nombre = document.getElementById("cancha_nombre").value;
    const direccion = document.getElementById("cancha_direccion").value;
    const precio = document.getElementById("cancha_precio").value;

    const request = {
        codigo,
        nombre,
        direccion,
        precio
    };

    try {
        const response = await fetch(`http://localhost:9999/cancha/actualizarcancha/${idCancha}`, {
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
            throw new Error("No se pudo actualizar la cancha");
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
            text: '¡Cancha actualizada correctamente!',
        }).then(() => {
            window.location.href = "/listarcanchas";
        });

    } catch (error) {
        console.error('Error al actualizar la cancha:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Error al actualizar la cancha',
        });
    }
});

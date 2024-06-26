document.addEventListener('DOMContentLoaded', function () {
    // Event listeners for modal forms submission
    const clienteForm = document.querySelector('#ModalCliente form');
    const canchaForm = document.querySelector('#ModalItem form');

    // Submit event for Cliente form
    clienteForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = new FormData(clienteForm);
        const cliente = formData.get('cliente');

        // Add cliente to the reservation summary (this should be dynamically updated with backend data)
        const clienteElement = document.createElement('div');
        clienteElement.innerHTML = `
            <span class="roboto-medium">CLIENTE:</span>
            <span class="text-danger">&nbsp; <i class="fas fa-exclamation-triangle"></i> ${cliente}</span>
            <form action="" style="display: inline-block !important;">
                ${cliente}
                <button type="button" class="btn btn-danger"><i class="fas fa-user-times"></i></button>
            </form>
        `;
        document.querySelector('.container-fluid form-neon').appendChild(clienteElement);

        // Close modal
        $('#ModalCliente').modal('hide');
    });

    // Submit event for Cancha form
    canchaForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = new FormData(canchaForm);
        const cancha = formData.get('cancha');
        const cantidad = formData.get('cantidad');

        // Calculate subtotal (example logic, adjust as needed)
        const subtotal = cantidad * 5.00;  // Assuming $5 per hour for simplicity

        // Add cancha to the reservation summary (this should be dynamically updated with backend data)
        const canchaElement = document.createElement('tr');
        canchaElement.classList.add('text-center');
        canchaElement.innerHTML = `
            <td>
                <button type="button" class="btn btn-info"><i class="fas fa-info-circle"></i></button> &nbsp; ${cancha}
            </td>
            <td>${cantidad}</td>
            <td>${cantidad} Horas</td>
            <td>$${subtotal.toFixed(2)}</td>
            <td>
                <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
            </td>
        `;
        document.querySelector('.table tbody').insertBefore(canchaElement, document.querySelector('.table tbody .bg-light'));

        // Update total (example logic, adjust as needed)
        const totalElement = document.querySelector('.table .bg-light');
        const totalCantidad = parseInt(totalElement.querySelector('td:nth-child(2) strong').textContent) + parseInt(cantidad);
        const totalHoras = parseInt(totalElement.querySelector('td:nth-child(3) strong').textContent.split(' ')[0]) + parseInt(cantidad);
        const totalSubtotal = parseFloat(totalElement.querySelector('td:nth-child(4) strong').textContent.slice(1)) + subtotal;

        totalElement.querySelector('td:nth-child(2) strong').textContent = totalCantidad;
        totalElement.querySelector('td:nth-child(3) strong').textContent = `${totalHoras} Horas`;
        totalElement.querySelector('td:nth-child(4) strong').textContent = `$${totalSubtotal.toFixed(2)}`;

        // Close modal
        $('#ModalItem').modal('hide');
    });
});

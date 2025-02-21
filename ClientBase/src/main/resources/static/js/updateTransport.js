

function updateTransport(transport, clientID) {
    document.getElementById('brand-name-update').value = transport.brandName || '';
    document.getElementById('model-update').value = transport.model || '';
    document.getElementById('VIN-update').value = transport.vin || '';
    document.getElementById('gos-number-update').value = transport.gosNumber || '';
    document.getElementById('year-update').value = transport.year || '';
    document.getElementById('info-update').value = transport.addInform || '';

    const modalUpdateTransport = document.getElementById('edit-transport-modal');
    modalUpdateTransport.style.display = 'block';

    // Закрытие модального окна при нажатии вне его
    window.onclick = function(event) {
        if (event.target == modalUpdateTransport) {
            modalUpdateTransport.style.display = 'none';
        }
    };

    // Перемещаем обработчик отправки формы в начало функции
    const formUpdateTransport = document.getElementById('edit-transport-form');
    formUpdateTransport.onsubmit = function(eventUpdateTransport) {
        eventUpdateTransport.preventDefault();
        const updatedTransport = {
            id: transport.id,
            brandName: document.getElementById('brand-name-update').value,
            model: document.getElementById('model-update').value,
            vin: document.getElementById('VIN-update').value,
            gosNumber: document.getElementById('gos-number-update').value,
            year: document.getElementById('year-update').value,
            addInform: document.getElementById('info-update').value,
        };

        saveTransportChanges(updatedTransport, clientID);
        modalUpdateTransport.style.display = 'none'; // Закрыть модальное окно
    };
}

function saveTransportChanges(updatedTransport, clientID) {
    fetch('http://192.168.1.135:9090/transport', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedTransport),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Некорректно заполнены поля');
            }
            return response.json();
        })
        .then(data => {
            if (data.id) {
                returnClientAfterCreate(clientID);
            } else {
                console.error('ID транспорта не найден в ответе:', data);
            }
        })
        .catch((error) => {
            console.error('Ошибка:', error);
            alert('Произошла ошибка: ' + error.message);
        });
}
function closeEditTransportModal() {
    document.getElementById('edit-transport-modal').style.display = 'none';
}